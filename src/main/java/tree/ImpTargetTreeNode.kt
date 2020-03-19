package tree

class ImpTargetTreeNode(
    override val name: String = "",
    override val tag: String = "",
    override val id: Int = -1
) : TargetTreeNode {

    override var children: List<TargetTreeNode> = mutableListOf()
    private var parent: TargetTreeNode? = null

    override fun moveToRightSibling(): TargetTreeNode? {
        return (parent as? ImpTargetTreeNode)?.getRightSibling(this)
    }

    override fun moveToLeftSibling(): TargetTreeNode? {
        return (parent as? ImpTargetTreeNode)?.getLeftSibling(this)
    }

    override fun moveToParent(): TargetTreeNode? {
        return parent
    }

    override fun moveToFirstChild(): TargetTreeNode? {
        if (children.isNotEmpty())
            return children.first()
        return null
    }

    override fun toString(): String {
        var str = "$name:$tag"
        if (children.isNotEmpty()) {
            str += END_LINE_STRING
            str += children.joinToString(separator = END_LINE_STRING).prependIndent(INDENT_STRING)
        }
        return str
    }

    fun setParent(newParent: TargetTreeNode?) {
        parent = newParent
    }

    @Suppress("UNCHECKED_CAST")
    private fun getRightSibling(son: TargetTreeNode): TargetTreeNode? {
        if (children.isNotEmpty()) {
            val index = (children as List<ImpTargetTreeNode>).binarySearch(son as ImpTargetTreeNode, ImpTargetTreeNodeComparator())
            if (index >= 0 && index + 1 < children.size)
                return children[index + 1]
        }
        return null
    }

    @Suppress("UNCHECKED_CAST")
    private fun getLeftSibling(son: TargetTreeNode): TargetTreeNode? {
        if (children.isNotEmpty()) {
            val index = (children as List<ImpTargetTreeNode>).binarySearch(son as ImpTargetTreeNode, ImpTargetTreeNodeComparator())
            if (index > 0)
                return children[index - 1]
        }
        return null
    }

    fun preorder(): String {
        var str = "$name:$tag($id)"
        if (children.isNotEmpty())
            str += END_LINE_STRING
        str += children.joinToString(separator = END_LINE_STRING).prependIndent(INDENT_STRING)
        return str
    }

    companion object {
        const val INDENT_STRING = "    "
        const val END_LINE_STRING = "\n"
    }
}
