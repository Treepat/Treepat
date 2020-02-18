package antlr

import antlr.generate.TreepatParser
import antlr.generate.TreepatVisitor
import functions.ChildFunction
import functions.NodeFunction
import functions.SiblingFunction
import functions.VisitorFunction
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import java.util.stream.Collectors


class TreepatVisitorImplementationFunctions : TreepatVisitor<VisitorFunction> {
    override fun visitModel(ctx: TreepatParser.ModelContext?): VisitorFunction {
        return ctx!!.subtree().accept<VisitorFunction>(this)
    }

    override fun visitSubtree(ctx: TreepatParser.SubtreeContext?): VisitorFunction {
        val expression = ctx!!.expression().accept<VisitorFunction>(this)
        if (ctx.child() == null) {
            return expression
        }
        val child = ctx.child().accept<VisitorFunction>(this)
        return ChildFunction().child(father = expression, child = child)
    }

    override fun visitExpression(ctx: TreepatParser.ExpressionContext?): VisitorFunction {
        return if (ctx!!.simple_expression() != null) {
            ctx.simple_expression().accept<VisitorFunction>(this)
        } else ctx.depth_closure().accept<VisitorFunction>(this)
    }

    override fun visitChild(ctx: TreepatParser.ChildContext?): VisitorFunction {
        return ctx!!.sibling().accept<VisitorFunction>(this)
    }

    override fun visitSibling(ctx: TreepatParser.SiblingContext?): VisitorFunction {
        val siblings: List<VisitorFunction>
        siblings = ctx!!.union().stream()
            .map { instruction: TreepatParser.UnionContext -> instruction.accept<VisitorFunction>(this) }
            .collect(Collectors.toList())
        return SiblingFunction().sibling(siblings)
    }

    override fun visitUnion(ctx: TreepatParser.UnionContext?): VisitorFunction {
        val nodes: List<VisitorFunction>
        nodes = ctx!!.subtree_wrapper().stream()
            .map { node: TreepatParser.Subtree_wrapperContext -> node.accept<VisitorFunction>(this) }
            .collect(Collectors.toList())
        return nodes[0]
    }

    override fun visitSubtree_wrapper(ctx: TreepatParser.Subtree_wrapperContext?): VisitorFunction {
        return ctx!!.subtree().accept<VisitorFunction>(this)
    }

    override fun visitDepth_closure(ctx: TreepatParser.Depth_closureContext?): VisitorFunction {
        return ctx!!.child().accept<VisitorFunction>(this)
    }

    override fun visitDepth_term(ctx: TreepatParser.Depth_termContext?): VisitorFunction {
        return ctx!!.term().accept<VisitorFunction>(this)
    }

    override fun visitTerm(ctx: TreepatParser.TermContext?): VisitorFunction {
        return ctx!!.node().accept<VisitorFunction>(this)
    }

    override fun visitNode(ctx: TreepatParser.NodeContext?): VisitorFunction {
        return NodeFunction().node(ctx!!.name.text)
    }

    override fun visit(p0: ParseTree?): VisitorFunction {
        return p0!!.accept<VisitorFunction>(this)
    }

    override fun visitChildren(p0: RuleNode?): VisitorFunction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitErrorNode(p0: ErrorNode?): VisitorFunction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitTerminal(p0: TerminalNode?): VisitorFunction {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitSimple_expression(ctx: TreepatParser.Simple_expressionContext?): VisitorFunction {

        return when {
            ctx!!.term() != null -> {
                ctx.term().accept<VisitorFunction>(this)
            }
            ctx.breadth_closure() != null -> {
                ctx.breadth_closure().accept<VisitorFunction>(this)
            }
            else -> {
                ctx.depth_term().accept<VisitorFunction>(this)
            }
        }
    }

    override fun visitBreadth_closure(ctx: TreepatParser.Breadth_closureContext?): VisitorFunction {
        return ctx!!.term().accept<VisitorFunction>(this)
    }
}