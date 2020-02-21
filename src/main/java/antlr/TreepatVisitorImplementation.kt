package antlr

import antlr.generate.TreepatParser
import antlr.generate.TreepatVisitor
import ast.ASTNode
import ast.Child
import ast.Node
import ast.Sibling
import java.util.stream.Collectors
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode

class TreepatVisitorImplementation : TreepatVisitor<ASTNode?> {
    override fun visitModel(ctx: TreepatParser.ModelContext): ASTNode {
        return ctx.subtree().accept<ASTNode>(this)
    }

    override fun visitSubtree(ctx: TreepatParser.SubtreeContext): ASTNode {
        val expression = ctx.expression().accept<ASTNode>(this)
        if (ctx.child() == null) {
            return expression
        }
        val child = ctx.child().accept<ASTNode>(this)
        return Child(expression, child)
    }

    override fun visitExpression(ctx: TreepatParser.ExpressionContext): ASTNode {
        return if (ctx.simpleExpression() != null) {
            ctx.simpleExpression().accept<ASTNode>(this)
        } else ctx.depthClosure().accept<ASTNode>(this)
    }

    override fun visitChild(ctx: TreepatParser.ChildContext): ASTNode {
        return ctx.sibling().accept<ASTNode>(this)
    }

    override fun visitSibling(ctx: TreepatParser.SiblingContext): ASTNode {
        val siblings: List<ASTNode>
        siblings = ctx.union().stream()
            .map { instruction: TreepatParser.UnionContext -> instruction.accept<ASTNode>(this) }
            .collect(Collectors.toList())
        return Sibling(siblings)
    }

    override fun visitUnion(ctx: TreepatParser.UnionContext): ASTNode {
        val nodes: List<ASTNode>
        nodes = ctx.subtreeWrapper().stream()
            .map { node: TreepatParser.SubtreeWrapperContext -> node.accept<ASTNode>(this) }
            .collect(Collectors.toList())
        return nodes[0]
    }

    override fun visitSubtreeWrapper(ctx: TreepatParser.SubtreeWrapperContext): ASTNode {
        return ctx.subtree().accept<ASTNode>(this)
    }

    override fun visitDepthClosure(ctx: TreepatParser.DepthClosureContext): ASTNode {
        return ctx.child().accept<ASTNode>(this)
    }

    override fun visitSimpleExpression(ctx: TreepatParser.SimpleExpressionContext): ASTNode {
        var valueTo_return: ASTNode? = null
        if (ctx.term() != null) {
            valueTo_return = ctx.term().accept<ASTNode>(this)
        } else if (ctx.breadthClosure() != null) {
            valueTo_return = ctx.breadthClosure().accept<ASTNode>(this)
        } else if (ctx.depthTerm() != null) {
            valueTo_return = ctx.depthTerm().accept<ASTNode>(this)
        }
        return valueTo_return!!
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
        return null
    }

    override fun visitTerminal(terminalNode: TerminalNode): ASTNode? {
        return null
    }

    override fun visitErrorNode(errorNode: ErrorNode): ASTNode? {
        return null
    }
}
