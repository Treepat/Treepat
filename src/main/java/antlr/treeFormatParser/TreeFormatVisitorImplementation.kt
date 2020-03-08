package antlr.treeFormatParser

import antlr.treeFormatParser.generated.TreeFormatParser
import antlr.treeFormatParser.generated.TreeFormatParser.InformationContext
import antlr.treeFormatParser.generated.TreeFormatVisitor
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import tree.ImpTargetTreeNode
import tree.TargetTreeNode

class TreeFormatVisitorImplementation : TreeFormatVisitor<TargetTreeNode> {

    private var idCount: Int = 0

    override fun visitSubtree(ctx: TreeFormatParser.SubtreeContext): TargetTreeNode {
        val node: ImpTargetTreeNode = visitNode(ctx.node()) as ImpTargetTreeNode
        if (ctx.child() == null) {
            return node
        }
        node.setChildren(visitChild(ctx.child()).getChildren()!!)
        for (child in node.getChildren()!!) {
            (child as ImpTargetTreeNode).setParent(node)
        }
        return node
    }

    override fun visitChild(ctx: TreeFormatParser.ChildContext): TargetTreeNode {
        return visitSibling(ctx.sibling())
    }

    override fun visitSibling(ctx: TreeFormatParser.SiblingContext): TargetTreeNode {
        val siblings = ctx.subtree().toList()
        val siblingNodes = ArrayList<TargetTreeNode>()
        for (subtree in siblings) {
            siblingNodes.add(visitSubtree(subtree))
        }
        val aux = ImpTargetTreeNode()
        aux.setChildren(siblingNodes)
        return aux
    }

    override fun visitNode(ctx: TreeFormatParser.NodeContext): TargetTreeNode {
        return ImpTargetTreeNode(ctx.name.text, ctx.tag.text, idCount++)
    }

    override fun visitInformation(ctx: InformationContext): TargetTreeNode {
        return ImpTargetTreeNode()
    }

    override fun visit(p0: ParseTree): TargetTreeNode {
        return p0.accept(this)
    }

    override fun visitChildren(p0: RuleNode): TargetTreeNode {
        throw NotImplementedError("This method is not supported.")
    }

    override fun visitErrorNode(p0: ErrorNode?): TargetTreeNode {
        throw NotImplementedError("This method is not supported.")
    }

    override fun visitTerminal(p0: TerminalNode?): TargetTreeNode {
        throw NotImplementedError("This method is not supported.")
    }
}
