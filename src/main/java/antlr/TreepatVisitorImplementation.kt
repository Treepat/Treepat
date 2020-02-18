package antlr

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
        return if (ctx.simpleExpression() != null) {
            ctx.simpleExpression().accept<ASTNode>(this)
        } else ctx.depthClosure().accept<ASTNode>(this)
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
        nodes = ctx.subtreeWrapper().stream()
                .map { node: SubtreeWrapperContext -> node.accept<ASTNode>(this) }
                .collect(Collectors.toList())
        return nodes[0]
    }

    override fun visitSubtreeWrapper(ctx: SubtreeWrapperContext): ASTNode {
        return ctx.subtree().accept<ASTNode>(this)
    }

    override fun visitDepthClosure(ctx: DepthClosureContext): ASTNode {
        return ctx.child().accept<ASTNode>(this)
    }

    override fun visitSimpleExpression(ctx: SimpleExpressionContext): ASTNode {
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

    override fun visitDepthTerm(ctx: DepthTermContext): ASTNode {
        return ctx.term().accept<ASTNode>(this)
    }

    override fun visitBreadthClosure(ctx: BreadthClosureContext): ASTNode {
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