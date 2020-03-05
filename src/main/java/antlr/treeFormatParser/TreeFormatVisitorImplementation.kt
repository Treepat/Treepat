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

    var idCount: Int = 0

    override fun visitSubtree(ctx: TreeFormatParser.SubtreeContext): TargetTreeNode {
        var node = visitNode(ctx.node())
        if(ctx.child() == null){
            return node
        }

        node.children = visitChild(ctx.child())
        return node
    }

    override fun visitChild(ctx: TreeFormatParser.ChildContext): List<TargetTreeNode> {
        return visitSibling(ctx.sibling())
    }

    override fun visitSibling(ctx: TreeFormatParser.SiblingContext): List<TargetTreeNode> {
        val siblings = ctx.subtree().toList()
        var siblingNodes = ArrayList<TargetTreeNode>()
        for(subtree in siblings){
            siblingNodes.add(visitSubtree(subtree))
        }
        return siblingNodes
    }

    override fun visitNode(ctx: TreeFormatParser.NodeContext): TargetTreeNode {
        return ImpTargetTreeNode(ctx.name.text, ctx.tag.text, idCount++, null, null);
    }

    override fun visitInformation(ctx: InformationContext) { }

    override fun visit(p0: ParseTree): TargetTreeNode {
        return p0.accept<TargetTreeNode>(this)
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