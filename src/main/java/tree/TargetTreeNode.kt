package tree

interface TargetTreeNode : Comparable<TargetTreeNode> {
    fun moveRight(): TargetTreeNode
    fun moveLeft(): TargetTreeNode
    fun moveUp(): TargetTreeNode
    fun moveDown(): TargetTreeNode
    val name: String
}
