package antlr

import antlr.generate.TreepatParser
import antlr.generate.TreepatVisitor
import functions.VisitorFunction
import functions.childFunction
import functions.nodeFunction
import functions.siblingFunction
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
        return childFunction(father = expression, child = child)
    }

    override fun visitExpression(ctx: TreepatParser.ExpressionContext?): VisitorFunction {
        return if (ctx!!.simpleExpression() != null) {
            ctx.simpleExpression().accept<VisitorFunction>(this)
        } else ctx.depthClosure().accept<VisitorFunction>(this)
    }

    override fun visitChild(ctx: TreepatParser.ChildContext?): VisitorFunction {
        return ctx!!.sibling().accept<VisitorFunction>(this)
    }

    override fun visitSibling(ctx: TreepatParser.SiblingContext?): VisitorFunction {
        val siblings: List<VisitorFunction>
        siblings = ctx!!.union().stream()
            .map { instruction: TreepatParser.UnionContext -> instruction.accept<VisitorFunction>(this) }
            .collect(Collectors.toList())
        return siblingFunction(siblings)
    }

    override fun visitUnion(ctx: TreepatParser.UnionContext?): VisitorFunction {
        val nodes: List<VisitorFunction>
        nodes = ctx!!.subtreeWrapper().stream()
            .map { node: TreepatParser.SubtreeWrapperContext -> node.accept<VisitorFunction>(this) }
            .collect(Collectors.toList())
        return nodes[0]
    }

    override fun visitSubtreeWrapper(ctx: TreepatParser.SubtreeWrapperContext?): VisitorFunction {
        return ctx!!.subtree().accept<VisitorFunction>(this)
    }

    override fun visitDepthClosure(ctx: TreepatParser.DepthClosureContext?): VisitorFunction {
        return ctx!!.child().accept<VisitorFunction>(this)
    }

    override fun visitDepthTerm(ctx: TreepatParser.DepthTermContext?): VisitorFunction {
        return ctx!!.term().accept<VisitorFunction>(this)
    }

    override fun visitTerm(ctx: TreepatParser.TermContext?): VisitorFunction {
        return ctx!!.node().accept<VisitorFunction>(this)
    }

    override fun visitNode(ctx: TreepatParser.NodeContext?): VisitorFunction {
        return nodeFunction(ctx!!.name.text)
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

    override fun visitSimpleExpression(ctx: TreepatParser.SimpleExpressionContext?): VisitorFunction {

        return when {
            ctx!!.term() != null -> {
                ctx.term().accept<VisitorFunction>(this)
            }
            ctx.breadthClosure() != null -> {
                ctx.breadthClosure().accept<VisitorFunction>(this)
            }
            else -> {
                ctx.depthTerm().accept<VisitorFunction>(this)
            }
        }
    }

    override fun visitBreadthClosure(ctx: TreepatParser.BreadthClosureContext?): VisitorFunction {
        return ctx!!.term().accept<VisitorFunction>(this)
    }
}