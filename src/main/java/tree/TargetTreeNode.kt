package tree

interface TargetTreeNode {
    val name: String
    val tag: String
    val id: Int
    var children: List<TargetTreeNode>

    fun moveToRightSibling(): TargetTreeNode?
    fun moveToLeftSibling(): TargetTreeNode?
    fun moveToParent(): TargetTreeNode?
    fun moveToFirstChild(): TargetTreeNode?
    fun matchedNodesString(selectedNodes: List<TargetTreeNode>): String

    override fun toString(): String
}
