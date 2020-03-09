package tree

class ImpTargetTreeNode(
    private val name: String = "",
    private val tag: String = "",
    private var id: Int = -1
) : TargetTreeNode {

    private var children: List<TargetTreeNode> = emptyList()
    private var parent: TargetTreeNode? = null

    override fun moveToRightSibling(): TargetTreeNode? {
        val rightSibling = parent as? ImpTargetTreeNode
        return rightSibling?.getRightSibling(this)
    }

    override fun moveToLeftSibling(): TargetTreeNode? {
        if (parent != null)
            return (parent!! as ImpTargetTreeNode).getLeftSibling(this)
        return null
    }

    override fun moveToParent(): TargetTreeNode? {
        return parent
    }

    override fun moveToFirstChild(): TargetTreeNode? {
        if (children.isNotEmpty())
            return children.first()
        return null
    }

    override fun getName(): String {
        return name
    }

    override fun getTag(): String {
        return tag
    }

    override fun getId(): Int {
        return id
    }

    override fun setChildren(children: List<TargetTreeNode>) {
        this.children = children
    }

    override fun getChildren(): List<TargetTreeNode> {
        return children
    }

    override fun toString(): String {
        var str = "$name:$tag($id)"
        for (child in children) {
            str += "(" + (child as ImpTargetTreeNode).toString() + ")"
        }
        return str
    }

    fun setParent(newParent: TargetTreeNode?) {
        parent = newParent
    }

    private fun getRightSibling(son: TargetTreeNode): TargetTreeNode? {
        if (children.isNotEmpty()) {
            val index = (children as List<ImpTargetTreeNode>).binarySearch(son as ImpTargetTreeNode, ImpTargetTreeNodeComparator())
            if (index >= 0 && index + 1 < children.size)
                return children[index + 1]
        }
        return null
    }

    private fun getLeftSibling(son: TargetTreeNode): TargetTreeNode? {
        if (children.isNotEmpty()) {
            val index = (children as List<ImpTargetTreeNode>).binarySearch(son as ImpTargetTreeNode, ImpTargetTreeNodeComparator())
            if (index > 0)
                return children[index - 1]
        }
        return null
    }

    fun preorder() {
        println("$name:$tag($id)")
        for (child in children) {
            (child as ImpTargetTreeNode).preorder()
        }
    }
}
