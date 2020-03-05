package tree

interface TargetTreeNode : Comparable<TargetTreeNode> {
    val name: String
    val tag: String
    val id: Int
    var children: List<TargetTreeNode>?
    var parent: TargetTreeNode?

    fun moveToRightSibling(): TargetTreeNode?
    fun moveToLeftSibling(): TargetTreeNode?
    fun moveToParent(): TargetTreeNode?
    fun moveToFirstChild(): TargetTreeNode?
    fun updateParent(newParent: TargetTreeNode?)
    fun preorder()
    
    fun getRightSibling(son: TargetTreeNode): TargetTreeNode?
    fun getLeftSibling(son: TargetTreeNode): TargetTreeNode?
}
