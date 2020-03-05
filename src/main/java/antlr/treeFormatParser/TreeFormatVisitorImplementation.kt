package antlr.treeFormatParser

import antlr.treeFormatParser.generated.TreeFormatParser
import antlr.treeFormatParser.generated.TreeFormatParser.InformationContext
import antlr.treeFormatParser.generated.TreeFormatVisitor
import ast.ASTNode
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import tree.TargetTreeNode

class TreeFormatVisitorImplementation : TreeFormatVisitor<TargetTreeNode> {

    override fun visitSubtree(ctx: TreeFormatParser.SubtreeContext?): TargetTreeNode {
        TODO("Not yet implemented")
    }

    override fun visitChild(ctx: TreeFormatParser.ChildContext?): TargetTreeNode {
        TODO("Not yet implemented")
    }

    override fun visitSibling(ctx: TreeFormatParser.SiblingContext?): TargetTreeNode {
        TODO("Not yet implemented")
    }

    override fun visitNode(ctx: TreeFormatParser.NodeContext?): TargetTreeNode {
        TODO("Not yet implemented")
    }

    override fun visitInformation(ctx: InformationContext?): TargetTreeNode {
        TODO("Not yet implemented")
    }

    override fun visit(p0: ParseTree?): TargetTreeNode {
        return p0!!.accept<TargetTreeNode>(this)
    }

    override fun visitChildren(p0: RuleNode?): TargetTreeNode {
        throw NotImplementedError("This method is not supported.")
    }

    override fun visitErrorNode(p0: ErrorNode?): TargetTreeNode {
        throw NotImplementedError("This method is not supported.")
    }

    override fun visitTerminal(p0: TerminalNode?): TargetTreeNode {
        throw NotImplementedError("This method is not supported.")
    }
}