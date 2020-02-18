package antlr

import antlr.generate.TreepatParser
import antlr.generate.TreepatVisitor
import functions.ChildFunction
import functions.NodeFunction
import functions.SiblingFunction
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import tree.TargetTreeNode
import java.util.stream.Collectors

class TreepatVisitorImplementationFunctions : TreepatVisitor<(TargetTreeNode) -> List<TargetTreeNode>> {
    override fun visitModel(ctx: TreepatParser.ModelContext?): (TargetTreeNode) -> List<TargetTreeNode> {
        return ctx!!.subtree().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
    }

    override fun visitSubtree(ctx: TreepatParser.SubtreeContext?): (TargetTreeNode) -> List<TargetTreeNode> {
        val expression = ctx!!.expression().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
        if (ctx.child() == null) {
            return expression
        }
        val child = ctx.child().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
        return ChildFunction().child(father = expression, child = child)
    }

    override fun visitExpression(ctx: TreepatParser.ExpressionContext?): (TargetTreeNode) -> List<TargetTreeNode> {
        return if (ctx!!.simple_expression() != null) {
            ctx.simple_expression().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
        } else ctx.depth_closure().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
    }

    override fun visitChild(ctx: TreepatParser.ChildContext?): (TargetTreeNode) -> List<TargetTreeNode> {
        return ctx!!.sibling().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
    }

    override fun visitSibling(ctx: TreepatParser.SiblingContext?): (TargetTreeNode) -> List<TargetTreeNode> {
        val siblings: List<(TargetTreeNode) -> List<TargetTreeNode>>
        siblings = ctx!!.union().stream()
            .map { instruction: TreepatParser.UnionContext -> instruction.accept<(TargetTreeNode) -> List<TargetTreeNode>>(this) }
            .collect(Collectors.toList())
        return SiblingFunction().sibling(siblings)
    }

    override fun visitUnion(ctx: TreepatParser.UnionContext?): (TargetTreeNode) -> List<TargetTreeNode> {
        val nodes: List<(TargetTreeNode) -> List<TargetTreeNode>>
        nodes = ctx!!.subtree_wrapper().stream()
            .map { node: TreepatParser.Subtree_wrapperContext -> node.accept<(TargetTreeNode) -> List<TargetTreeNode>>(this) }
            .collect(Collectors.toList())
        return nodes[0]
    }

    override fun visitSubtree_wrapper(ctx: TreepatParser.Subtree_wrapperContext?): (TargetTreeNode) -> List<TargetTreeNode> {
        return ctx!!.subtree().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
    }

    override fun visitDepth_closure(ctx: TreepatParser.Depth_closureContext?): (TargetTreeNode) -> List<TargetTreeNode> {
        return ctx!!.child().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
    }

    override fun visitDepth_term(ctx: TreepatParser.Depth_termContext?): (TargetTreeNode) -> List<TargetTreeNode> {
        return ctx!!.term().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
    }

    override fun visitTerm(ctx: TreepatParser.TermContext?): (TargetTreeNode) -> List<TargetTreeNode> {
        return ctx!!.node().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
    }

    override fun visitNode(ctx: TreepatParser.NodeContext?): (TargetTreeNode) -> List<TargetTreeNode> {
        return NodeFunction().node(ctx!!.name.text)
    }

    override fun visit(p0: ParseTree?): (TargetTreeNode) -> List<TargetTreeNode> {
        return p0!!.accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
    }

    override fun visitChildren(p0: RuleNode?): (TargetTreeNode) -> List<TargetTreeNode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitErrorNode(p0: ErrorNode?): (TargetTreeNode) -> List<TargetTreeNode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitTerminal(p0: TerminalNode?): (TargetTreeNode) -> List<TargetTreeNode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitSimple_expression(ctx: TreepatParser.Simple_expressionContext?): (TargetTreeNode) -> List<TargetTreeNode> {

        return when {
            ctx!!.term() != null -> {
                ctx.term().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
            }
            ctx.breadth_closure() != null -> {
                ctx.breadth_closure().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
            }
            else -> {
                ctx.depth_term().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
            }
        }
    }

    override fun visitBreadth_closure(ctx: TreepatParser.Breadth_closureContext?): (TargetTreeNode) -> List<TargetTreeNode> {
        return ctx!!.term().accept<(TargetTreeNode) -> List<TargetTreeNode>>(this)
    }
}