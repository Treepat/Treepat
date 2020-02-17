package antlr

import antlr.generate.TreepatParser
import antlr.generate.TreepatParser.*
import antlr.generate.TreepatVisitor
import ast.ASTNode
import ast.Child
import ast.Node
import ast.Sibling
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import java.util.stream.Collectors

class TreepatVisitorImplementation : TreepatVisitor<ASTNode?> {
    override fun visitModel(ctx: ModelContext): ASTNode {
        return ctx.subtree().accept<ASTNode>(this)
    }

    override fun visitSubtree(ctx: SubtreeContext): ASTNode {
        val expression = ctx.expression().accept<ASTNode>(this)
        if (ctx.child() == null) {
            return expression
        }
        val child = ctx.child().accept<ASTNode>(this)
        return Child(expression, child)
    }

    override fun visitExpression(ctx: ExpressionContext): ASTNode {
        return if (ctx.simple_expression() != null) {
            ctx.simple_expression().accept<ASTNode>(this)
        } else ctx.depth_closure().accept<ASTNode>(this)
    }

    override fun visitChild(ctx: ChildContext): ASTNode {
        return ctx.sibling().accept<ASTNode>(this)
    }

    override fun visitSibling(ctx: SiblingContext): ASTNode {
        val siblings: List<ASTNode>
        siblings = ctx.union().stream()
                .map { instruction: UnionContext -> instruction.accept<ASTNode>(this) }
                .collect(Collectors.toList())
        return Sibling(siblings)
    }

    override fun visitUnion(ctx: UnionContext): ASTNode {
        val nodes: List<ASTNode>
        nodes = ctx.subtree_wrapper().stream()
                .map { node: Subtree_wrapperContext -> node.accept<ASTNode>(this) }
                .collect(Collectors.toList())
        return nodes[0]
    }

    override fun visitSubtree_wrapper(ctx: Subtree_wrapperContext): ASTNode {
        return ctx.subtree().accept<ASTNode>(this)
    }

    override fun visitDepth_closure(ctx: Depth_closureContext): ASTNode {
        return ctx.child().accept<ASTNode>(this)
    }

    override fun visitSimple_expression(ctx: Simple_expressionContext): ASTNode {
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

    override fun visitDepth_term(ctx: Depth_termContext): ASTNode {
        return ctx.term().accept<ASTNode>(this)
    }

    override fun visitBreadth_closure(ctx: Breadth_closureContext): ASTNode {
        return ctx.term().accept<ASTNode>(this)
    }

    override fun visitTerm(ctx: TermContext): ASTNode {
        return ctx.node().accept<ASTNode>(this)
    }

    override fun visitNode(ctx: NodeContext): ASTNode {
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