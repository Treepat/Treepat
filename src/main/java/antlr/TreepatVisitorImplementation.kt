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
        return if (ctx.simple_expression() != null) {
            ctx.simple_expression().accept<ASTNode>(this)
        } else ctx.depth_closure().accept<ASTNode>(this)
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
        nodes = ctx.subtree_wrapper().stream()
                .map { node: TreepatParser.Subtree_wrapperContext -> node.accept<ASTNode>(this) }
                .collect(Collectors.toList())
        return nodes[0]
    }

    override fun visitSubtree_wrapper(ctx: TreepatParser.Subtree_wrapperContext): ASTNode {
        return ctx.subtree().accept<ASTNode>(this)
    }

    override fun visitDepth_closure(ctx: TreepatParser.Depth_closureContext): ASTNode {
        return ctx.child().accept<ASTNode>(this)
    }

    override fun visitSimple_expression(ctx: TreepatParser.Simple_expressionContext): ASTNode {
        var value_to_return: ASTNode? = null
        if (ctx.term() != null) {
            value_to_return = ctx.term().accept<ASTNode>(this)
        } else if (ctx.breadth_closure() != null) {
            value_to_return = ctx.breadth_closure().accept<ASTNode>(this)
        } else if (ctx.depth_term() != null) {
            value_to_return = ctx.depth_term().accept<ASTNode>(this)
        }
        return value_to_return!!
    }

    override fun visitDepth_term(ctx: TreepatParser.Depth_termContext): ASTNode {
        return ctx.term().accept<ASTNode>(this)
    }

    override fun visitBreadth_closure(ctx: TreepatParser.Breadth_closureContext): ASTNode {
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
