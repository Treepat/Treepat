package antlr.treepat

import ast.ASTNode
import ast.BreadthClosure
import ast.Check
import ast.Child
import ast.Dot
import ast.Node
import ast.Sibling
import ast.Treepat
import ast.Union
import java.util.stream.Collectors
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import treepat.TreepatParser
import treepat.TreepatVisitor

class TreepatVisitorImplementation : TreepatVisitor<ASTNode> {

    override fun visit(parseTree: ParseTree): ASTNode = parseTree.accept<ASTNode>(this)

    override fun visitIndent(ctx: TreepatParser.IndentContext): ASTNode = Check(ctx.subtree().accept(this))

    override fun visitSubtree(ctx: TreepatParser.SubtreeContext): ASTNode = ctx.sibling().accept<ASTNode>(this)

    override fun visitTreepat(ctx: TreepatParser.TreepatContext): ASTNode = Treepat(Check(ctx.subtree().accept(this)))

    override fun visitNested(ctx: TreepatParser.NestedContext): ASTNode = ctx.subtree().accept(this)

    override fun visitNestedIndent(ctx: TreepatParser.NestedIndentContext): ASTNode = ctx.indent().accept(this)

    override fun visitDepthClosure(ctx: TreepatParser.DepthClosureContext): ASTNode = ctx.indentWrapper().accept(this)

    override fun visitDepthTerm(ctx: TreepatParser.DepthTermContext): ASTNode = ctx.node().accept(this)

    override fun visitAtomTerm(ctx: TreepatParser.AtomTermContext): ASTNode = ctx.atomTermWrapper().accept(this)

    override fun visitNode(ctx: TreepatParser.NodeContext): ASTNode = if (ctx.dot() != null) ctx.dot().accept(this) else Node(ctx.name.text)

    override fun visitDot(ctx: TreepatParser.DotContext): ASTNode = Dot()

    override fun visitIndentWrapper(ctx: TreepatParser.IndentWrapperContext): ASTNode {
        return when {
            ctx.indent() != null -> ctx.indent().accept<ASTNode>(this)
            else -> ctx.nestedIndent().accept<ASTNode>(this)
        }
    }

    override fun visitUnion(ctx: TreepatParser.UnionContext): ASTNode {
        val expressions = ctx.child()
            .stream()
            .map { node: TreepatParser.ChildContext -> node.accept<ASTNode>(this) }
            .collect(Collectors.toList())
        return when (expressions.size) {
            1 -> expressions.first()
            else -> Union(expressions)
        }
    }

    override fun visitSibling(ctx: TreepatParser.SiblingContext): ASTNode {
        val siblings = ctx.union()
            .stream()
            .map { instruction: TreepatParser.UnionContext -> instruction.accept<ASTNode>(this) }
            .collect(Collectors.toList())
        return when (siblings.size) {
            1 -> siblings.first()
            else -> createSiblingNode(siblings)
        }
    }

    private fun createSiblingNode(siblings: List<ASTNode>): ASTNode {
        if (siblings.size == 2) {
            return Sibling(siblings[0], siblings[1])
        }
        val secondParam = createSiblingNode(siblings.subList(1, siblings.size))
        return Sibling(siblings.first(), secondParam)
    }

    override fun visitAtomTermWrapper(ctx: TreepatParser.AtomTermWrapperContext): ASTNode {
        return when {
            ctx.depthClosure() != null -> ctx.depthClosure().accept(this)
            ctx.nested() != null -> ctx.nested().accept(this)
            ctx.depthTerm() != null -> ctx.depthTerm().accept(this)
            else -> ctx.node().accept<ASTNode>(this)
        }
    }

    override fun visitChild(ctx: TreepatParser.ChildContext): ASTNode {
        val expression = ctx.breadthClosure().accept(this)
        if (ctx.indentWrapper() == null) {
            return expression
        }
        val child = ctx.indentWrapper().accept(this)
        return Child(expression, child)
    }

    override fun visitBreadthClosure(ctx: TreepatParser.BreadthClosureContext): ASTNode {
        return when {
            ctx.ASTERISK() == null -> ctx.atomTerm().accept(this)
            else -> BreadthClosure(ctx.atomTerm().accept(this))
        }
    }

    override fun visitChildren(ruleNode: RuleNode): ASTNode? {
        throw NotImplementedError("This method is not supported.")
    }

    override fun visitTerminal(terminalNode: TerminalNode): ASTNode? {
        throw NotImplementedError("This method is not supported.")
    }

    override fun visitErrorNode(errorNode: ErrorNode): ASTNode? {
        throw NotImplementedError("This method is not supported.")
    }
}
