package antlr.treepat

import ast.ASTNode
import ast.BreadthClosure
import ast.Child
import ast.Dot
import ast.Node
import ast.Treepat
import ast.Union
import ast.createSiblingNodes
import java.util.stream.Collectors
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import treepat.TreepatParser
import treepat.TreepatVisitor

private const val UNSUPPORTED_METHOD_MESSAGE = "This method is not supported."

class TreepatVisitorImplementation : TreepatVisitor<ASTNode> {

    override fun visitSubtree(ctx: TreepatParser.SubtreeContext): ASTNode = ctx.sibling().accept(this)

    override fun visitDot(ctx: TreepatParser.DotContext): ASTNode = Dot()

    override fun visitDepthClosure(ctx: TreepatParser.DepthClosureContext): ASTNode = ctx.indentWrapper().accept(this)

    override fun visitDepthTerm(ctx: TreepatParser.DepthTermContext): ASTNode = ctx.node().accept(this)

    override fun visit(parseTree: ParseTree): ASTNode = parseTree.accept(this)

    override fun visitIndent(ctx: TreepatParser.IndentContext): ASTNode = ctx.subtree().accept(this)

    override fun visitNestedIndent(ctx: TreepatParser.NestedIndentContext): ASTNode = ctx.indent().accept(this)

    override fun visitAtomTerm(ctx: TreepatParser.AtomTermContext): ASTNode = ctx.atomTermWrapper().accept(this)

    override fun visitNested(ctx: TreepatParser.NestedContext): ASTNode = ctx.subtree().accept(this)

    override fun visitTreepat(ctx: TreepatParser.TreepatContext): ASTNode = Treepat(ctx.subtree().accept(this))

    override fun visitChildren(ruleNode: RuleNode): ASTNode? = throw NotImplementedError(UNSUPPORTED_METHOD_MESSAGE)

    override fun visitErrorNode(errorNode: ErrorNode): ASTNode? = throw NotImplementedError(UNSUPPORTED_METHOD_MESSAGE)

    override fun visitTerminal(terminalNode: TerminalNode): ASTNode? =
        throw NotImplementedError(UNSUPPORTED_METHOD_MESSAGE)

    override fun visitAtomTermWrapper(ctx: TreepatParser.AtomTermWrapperContext): ASTNode = when {
        ctx.depthClosure() != null -> ctx.depthClosure().accept(this)
        ctx.nested() != null -> ctx.nested().accept(this)
        ctx.depthTerm() != null -> ctx.depthTerm().accept(this)
        else -> ctx.node().accept(this)
    }

    override fun visitBreadthClosure(ctx: TreepatParser.BreadthClosureContext): ASTNode {
        return if (ctx.ASTERISK() == null) {
            ctx.atomTerm().accept(this)
        } else {
            BreadthClosure(ctx.atomTerm().accept(this))
        }
    }

    override fun visitNode(ctx: TreepatParser.NodeContext): ASTNode {
        return if (ctx.dot() != null) {
            ctx.dot().accept(this)
        } else {
            Node(ctx.name.text)
        }
    }

    override fun visitIndentWrapper(ctx: TreepatParser.IndentWrapperContext): ASTNode {
        return if (ctx.indent() != null) {
            ctx.indent().accept(this)
        } else {
            ctx.nestedIndent().accept(this)
        }
    }

    override fun visitUnion(ctx: TreepatParser.UnionContext): ASTNode {
        val expressions = ctx.child()
            .stream()
            .map { node: TreepatParser.ChildContext -> node.accept(this) }
            .collect(Collectors.toList())
        return expressions.singleOrNull() ?: Union(expressions)
    }

    override fun visitSibling(ctx: TreepatParser.SiblingContext): ASTNode {
        val siblings = ctx.union()
            .stream()
            .map { instruction: TreepatParser.UnionContext -> instruction.accept(this) }
            .collect(Collectors.toList())
        return siblings.singleOrNull() ?: createSiblingNodes(siblings)
    }

    override fun visitChild(ctx: TreepatParser.ChildContext): ASTNode {
        val expression = ctx.breadthClosure().accept(this)
        if (ctx.indentWrapper() == null) {
            return expression
        }
        val child = ctx.indentWrapper().accept(this)
        return Child(expression, child)
    }
}
