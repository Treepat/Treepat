package grammars.antlr.tree_format

import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import target_tree.TargetTreeNode
import target_tree.default_tree.DefaultTargetTreeNode
import tree_format.TreeFormatParser
import tree_format.TreeFormatVisitor

class TreeFormatVisitorImplementation : TreeFormatVisitor<TargetTreeNode> {

    private var idCount: Int = 0

    override fun visitChild(context: TreeFormatParser.ChildContext): TargetTreeNode = context.sibling().accept(this)

    override fun visitNode(context: TreeFormatParser.NodeContext): TargetTreeNode =
        DefaultTargetTreeNode(context.name.text, context.tag.text, idCount++)

    override fun visitInformation(context: TreeFormatParser.InformationContext): TargetTreeNode =
        DefaultTargetTreeNode()

    override fun visit(parseTree: ParseTree): TargetTreeNode = parseTree.accept(this)

    override fun visitChildren(ruleNode: RuleNode): TargetTreeNode =
        throw NotImplementedError("This method is not supported.")

    override fun visitErrorNode(erroNode: ErrorNode?): TargetTreeNode =
        throw NotImplementedError("This method is not supported.")

    override fun visitTerminal(terminalNode: TerminalNode?): TargetTreeNode =
        throw NotImplementedError("This method is not supported.")

    override fun visitSubtree(context: TreeFormatParser.SubtreeContext): TargetTreeNode {
        val node: DefaultTargetTreeNode = context.node().accept(this) as DefaultTargetTreeNode
        if (context.child() == null) {
            return node
        }
        node.children = context.child().accept(this).children
        node.children.forEach { (it as DefaultTargetTreeNode).parent = node }
        return node
    }

    override fun visitSibling(context: TreeFormatParser.SiblingContext): TargetTreeNode {
        val siblings = context.subtree().toList()
        val siblingNodes = mutableListOf<TargetTreeNode>()
        siblings.forEach { siblingNodes.add(it.accept(this)) }
        return DefaultTargetTreeNode(children = siblingNodes)
    }
}
