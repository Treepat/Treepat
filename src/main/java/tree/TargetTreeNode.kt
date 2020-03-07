package tree

interface TargetTreeNode {

    fun moveToRightSibling(): TargetTreeNode?
    fun moveToLeftSibling(): TargetTreeNode?
    fun moveToParent(): TargetTreeNode?
    fun moveToFirstChild(): TargetTreeNode?

    fun getName(): String
    fun getTag(): String
    fun getId(): Int
    fun setChildren(children: List<TargetTreeNode>)
    fun getChildren(): List<TargetTreeNode>?

    override fun toString(): String
}
