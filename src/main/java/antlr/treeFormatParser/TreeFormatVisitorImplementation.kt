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

class TreeFormatVisitorImplementation : TreeFormatVisitor<List<TargetTreeNode>> {

    var idCount: Int = 0

    override fun visitSubtree(ctx: TreeFormatParser.SubtreeContext): List<TargetTreeNode> {
        var node = ArrayList<TargetTreeNode>()
        node.add(visitNode(ctx.node()).first())
        if(ctx.child() == null){
            return node
        }

        node.first().children = visitChild(ctx.child())
        return node
    }

    override fun visitChild(ctx: TreeFormatParser.ChildContext): List<TargetTreeNode> {
        return visitSibling(ctx.sibling())
    }

    override fun visitSibling(ctx: TreeFormatParser.SiblingContext): List<TargetTreeNode> {
        val siblings = ctx.subtree().toList()
        var siblingNodes = ArrayList<TargetTreeNode>()
        for(subtree in siblings){
            siblingNodes.add(visitSubtree(subtree).first())
        }
        return siblingNodes
    }

    override fun visitNode(ctx: TreeFormatParser.NodeContext): List<TargetTreeNode> {
        var aux = ArrayList<TargetTreeNode>()
        aux.add(ImpTargetTreeNode(ctx.name.text, ctx.tag.text, idCount++, null, null))
        return aux
    }

    override fun visitInformation(ctx: InformationContext): List<TargetTreeNode>{
        return ArrayList<TargetTreeNode>()
    }

    override fun visit(p0: ParseTree): List<TargetTreeNode> {
        return p0.accept<List<TargetTreeNode>>(this)
    }

    override fun visitChildren(p0: RuleNode): List<TargetTreeNode> {
        throw NotImplementedError("This method is not supported.")
    }

    override fun visitErrorNode(p0: ErrorNode?): List<TargetTreeNode> {
        throw NotImplementedError("This method is not supported.")
    }

    override fun visitTerminal(p0: TerminalNode?): List<TargetTreeNode> {
        throw NotImplementedError("This method is not supported.")
    }
}