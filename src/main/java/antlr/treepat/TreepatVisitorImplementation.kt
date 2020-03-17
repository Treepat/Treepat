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

    override fun visitSubtree(ctx: TreepatParser.SubtreeContext): ASTNode {
        val expression = ctx.expression().accept<ASTNode>(this)
        if (ctx.indented() == null) {
            return expression
        }
        val child = ctx.indented().accept<ASTNode>(this)
        return Child(expression, child)
    }

    override fun visitExpression(ctx: TreepatParser.ExpressionContext): ASTNode {
        return when {
            ctx.atomExpression() != null -> ctx.atomExpression().accept<ASTNode>(this)
            else -> ctx.depthClosure().accept<ASTNode>(this)
        }
    }

    override fun visitDepthClosure(ctx: TreepatParser.DepthClosureContext): ASTNode {
        return ctx.indented().accept<ASTNode>(this)
    }

    override fun visitDepthTerm(ctx: TreepatParser.DepthTermContext): ASTNode {
        return ctx.node().accept<ASTNode>(this)
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

    override fun visitComplexSibling(ctx: TreepatParser.ComplexSiblingContext): ASTNode {
        val siblings = ctx.complexUnion()
            .stream()
            .map { instruction: TreepatParser.ComplexUnionContext -> instruction.accept<ASTNode>(this) }
            .collect(Collectors.toList())
        return when (siblings.size) {
            1 -> siblings.first()
            else -> Sibling(siblings)
        }
    }

    override fun visitComplexUnion(ctx: TreepatParser.ComplexUnionContext): ASTNode {
        val nodes = ctx.subtree()
            .stream()
            .map { node: TreepatParser.SubtreeContext -> node.accept<ASTNode>(this) }
            .collect(Collectors.toList())
        // TODO - Change when union node has implemented.
        return nodes.first()
    }

    override fun visitAtomExpression(ctx: TreepatParser.AtomExpressionContext): ASTNode {
        return when {
            ctx.breadthClosure() != null -> ctx.breadthClosure().accept<ASTNode>(this)
            else -> ctx.atomSibling().accept<ASTNode>(this)
        }
    }

    override fun visitAtomSibling(ctx: TreepatParser.AtomSiblingContext): ASTNode {
        val siblings = ctx.atomUnion()
            .stream()
            .map { instruction: TreepatParser.AtomUnionContext -> instruction.accept<ASTNode>(this) }
            .collect(Collectors.toList())
        return when (siblings.size) {
            1 -> siblings.first()
            else -> Sibling(siblings)
        }
    }

    override fun visitAtomUnion(ctx: TreepatParser.AtomUnionContext): ASTNode {
        val nodes = ctx.term()
            .stream()
            .map { node: TreepatParser.TermContext -> node.accept<ASTNode>(this) }
            .collect(Collectors.toList())
        // TODO - Change when union node has implemented.
        return nodes.first()
    }

    override fun visitIndented(ctx: TreepatParser.IndentedContext): ASTNode {
        return ctx.complexSibling().accept(this)
    }
}
