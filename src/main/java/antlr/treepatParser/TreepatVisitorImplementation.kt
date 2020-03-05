package antlr.treepatParser

import antlr.treepatParser.generate.TreepatParser
import antlr.treepatParser.generate.TreepatVisitor
import ast.ASTNode
import ast.Child
import ast.Node
import ast.Sibling
import java.util.stream.Collectors
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode

class TreepatVisitorImplementation : TreepatVisitor<ASTNode> {

    override fun visitSubtree(ctx: TreepatParser.SubtreeContext): ASTNode {
        val expression = ctx.expression().accept<ASTNode>(this)
        if (ctx.child() == null) {
            return expression
        }
        val child = ctx.child().accept<ASTNode>(this)
        return Child(expression, child)
    }

    override fun visitExpression(ctx: TreepatParser.ExpressionContext): ASTNode {
        return when {
            ctx.simpleExpression() != null -> ctx.simpleExpression().accept<ASTNode>(this)
            else -> ctx.depthClosure().accept<ASTNode>(this)
        }
    }

    override fun visitChild(ctx: TreepatParser.ChildContext): ASTNode {
        return ctx.sibling().accept<ASTNode>(this)
    }

    override fun visitSibling(ctx: TreepatParser.SiblingContext): ASTNode {
        val siblings = ctx.union()
            .stream()
            .map { instruction: TreepatParser.UnionContext -> instruction.accept<ASTNode>(this) }
            .collect(Collectors.toList())
        return Sibling(siblings)
    }

    override fun visitUnion(ctx: TreepatParser.UnionContext): ASTNode {
        val nodes = ctx.subtreeWrapper()
            .stream()
            .map { node: TreepatParser.SubtreeWrapperContext -> node.accept<ASTNode>(this) }
            .collect(Collectors.toList())
        // TODO - Change when union node has implemented.
        return nodes.first()
    }

    override fun visitSubtreeWrapper(ctx: TreepatParser.SubtreeWrapperContext): ASTNode {
        return ctx.subtree().accept<ASTNode>(this)
    }

    override fun visitDepthClosure(ctx: TreepatParser.DepthClosureContext): ASTNode {
        return ctx.child().accept<ASTNode>(this)
    }

    override fun visitSimpleExpression(ctx: TreepatParser.SimpleExpressionContext): ASTNode {
        return when {
            ctx.term() != null -> ctx.term().accept<ASTNode>(this)
            ctx.breadthClosure() != null -> ctx.breadthClosure().accept<ASTNode>(this)
            else -> ctx.depthTerm().accept<ASTNode>(this)
        }
    }

    override fun visitDepthTerm(ctx: TreepatParser.DepthTermContext): ASTNode {
        return ctx.term().accept<ASTNode>(this)
    }

    override fun visitBreadthClosure(ctx: TreepatParser.BreadthClosureContext): ASTNode {
        return ctx.term().accept<ASTNode>(this)
    }

    override fun visitTerm(ctx: TreepatParser.TermContext): ASTNode {
        return ctx.node().accept<ASTNode>(this)
    }

    override fun visitNode(ctx: TreepatParser.NodeContext): ASTNode {
        return Node(ctx.name.text)
    }

    override fun visit(parseTree: ParseTree): ASTNode {
        return parseTree.accept<ASTNode>(this)
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
