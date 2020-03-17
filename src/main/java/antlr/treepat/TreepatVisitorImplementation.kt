package antlr.treepat

import ast.ASTNode
import ast.Child
import ast.Node
import ast.Sibling
import java.util.stream.Collectors
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import treepat.TreepatParser
import treepat.TreepatVisitor

class TreepatVisitorImplementation : TreepatVisitor<ASTNode> {

    override fun visitExpression(ctx: TreepatParser.ExpressionContext): ASTNode {
        return ctx.sibling().accept(this)
    }

    override fun visitDepthClosure(ctx: TreepatParser.DepthClosureContext): ASTNode {
        return ctx.indentWrapper().accept(this)
    }

    override fun visitDepthTerm(ctx: TreepatParser.DepthTermContext): ASTNode {
        return ctx.node().accept(this)
    }

    override fun visitBreadthClosure(ctx: TreepatParser.BreadthClosureContext): ASTNode {
        return when {
            ctx.ASTERISK() == null -> ctx.child().accept(this)
            else -> ctx.child().accept(this)
        }
    }

    override fun visitNode(ctx: TreepatParser.NodeContext): ASTNode {
        return Node(ctx.name.text)
    }

    override fun visit(parseTree: ParseTree): ASTNode {
        return parseTree.accept<ASTNode>(this)
    }

    override fun visitIndentWrapper(ctx: TreepatParser.IndentWrapperContext): ASTNode {
        return when {
            ctx.indent() != null -> ctx.indent().accept<ASTNode>(this)
            else -> ctx.nestedIndent().accept<ASTNode>(this)
        }
    }

    override fun visitIndent(ctx: TreepatParser.IndentContext): ASTNode {
        return ctx.treepat().accept(this)
    }

    override fun visitNestedIndent(ctx: TreepatParser.NestedIndentContext): ASTNode {
        return ctx.indent().accept(this)
    }

    override fun visitUnion(ctx: TreepatParser.UnionContext): ASTNode {
        val nodes = ctx.breadthClosure()
            .stream()
            .map { node: TreepatParser.BreadthClosureContext -> node.accept<ASTNode>(this) }
            .collect(Collectors.toList())
        // TODO - Change when union node has implemented.
        return nodes.first()
    }

    override fun visitAtomTerm(ctx: TreepatParser.AtomTermContext): ASTNode {
        return ctx.atomTermWrapper().accept(this)
    }

    override fun visitNested(ctx: TreepatParser.NestedContext): ASTNode {
        return ctx.treepat().accept(this)
    }

    override fun visitSibling(ctx: TreepatParser.SiblingContext): ASTNode {
        val siblings = ctx.union()
            .stream()
            .map { instruction: TreepatParser.UnionContext -> instruction.accept<ASTNode>(this) }
            .collect(Collectors.toList())
        return when (siblings.size) {
            1 -> siblings.first()
            else -> Sibling(siblings)
        }
    }

    override fun visitAtomTermWrapper(ctx: TreepatParser.AtomTermWrapperContext): ASTNode {
        return when {
            ctx.depthClosure() != null -> ctx.depthClosure().accept(this)
            ctx.nested() != null -> ctx.nested().accept(this)
            ctx.depthTerm() != null -> ctx.depthTerm().accept(this)
            else -> ctx.node().accept<ASTNode>(this)
        }
    }

    override fun visitTreepat(ctx: TreepatParser.TreepatContext): ASTNode {
        return ctx.expression().accept<ASTNode>(this)
    }

    override fun visitChild(ctx: TreepatParser.ChildContext): ASTNode {
        val expression = ctx.atomTerm().accept(this)
        if (ctx.indentWrapper() == null) {
            return expression
        }
        val child = ctx.indentWrapper().accept(this)
        return Child(expression, child)
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
