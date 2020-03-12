package antlr.tree_format

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

    override fun visitSubtree(context: TreeFormatParser.SubtreeContext): TargetTreeNode {
        val node: ImpTargetTreeNode = visitNode(context.node()) as ImpTargetTreeNode
        if (context.child() == null) {
            return node
        }
        node.children = visitChild(context.child()).children
        for (child in node.children) {
            (child as ImpTargetTreeNode).setParent(node)
        }
        return node
    }

    override fun visitChild(context: TreeFormatParser.ChildContext): TargetTreeNode {
        return visitSibling(context.sibling())
    }

    override fun visitSibling(context: TreeFormatParser.SiblingContext): TargetTreeNode {
        val siblings = context.subtree().toList()
        val siblingNodes = mutableListOf<TargetTreeNode>()
        for (subtree in siblings) {
            siblingNodes.add(visitSubtree(subtree))
        }
        val aux = ImpTargetTreeNode()
        aux.children = siblingNodes
        return aux
    }

    override fun visitNode(context: TreeFormatParser.NodeContext): TargetTreeNode {
        return ImpTargetTreeNode(context.name.text, context.tag.text, idCount++)
    }

    override fun visitInformation(context: InformationContext): TargetTreeNode {
        return ImpTargetTreeNode()
    }

    override fun visit(parseTree: ParseTree): TargetTreeNode {
        return parseTree.accept(this)
    }

    override fun visitChildren(ruleNode: RuleNode): TargetTreeNode {
        throw NotImplementedError("This method is not supported.")
    }

    override fun visitErrorNode(erroNode: ErrorNode?): TargetTreeNode {
        throw NotImplementedError("This method is not supported.")
    }

    override fun visitTerminal(terminalNode: TerminalNode?): TargetTreeNode {
        throw NotImplementedError("This method is not supported.")
    }
}
