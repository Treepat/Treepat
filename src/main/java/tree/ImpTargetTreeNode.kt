package tree

data class MatchedResponse(val matchedString: String, val currentIndex: Int)

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

    override fun matchedNodesString(selectedNodes: List<TargetTreeNode>): String {
        return matchedNodesString(selectedNodes, 0, Int.MAX_VALUE).matchedString
    }

    @Suppress("UNCHECKED_CAST")
    fun matchedNodesString(selectedNodes: List<TargetTreeNode>, initialIndex: Int, rightSiblingId: Int): MatchedResponse {
        val iAmIn = selectedNodes[initialIndex].id == id
        var str = ""
        var currentIndex = initialIndex
        if (iAmIn)
            currentIndex++
        while (currentIndex < selectedNodes.size && selectedNodes[currentIndex].id < rightSiblingId) {
            var goalInd = (children as List<ImpTargetTreeNode>).binarySearch(ImpTargetTreeNode("", "", selectedNodes[currentIndex].id), ImpTargetTreeNodeComparator())

            if (goalInd < 0 || (goalInd == 0 && selectedNodes[currentIndex].id != children[0].id)) {
                goalInd *= -1
                goalInd -= 1 // <---------- This might not be needed in the future when searching for siblings but cannot be tested right now
            }

            val response = (children[goalInd] as ImpTargetTreeNode).matchedNodesString(selectedNodes, currentIndex, if (goalInd + 1 == children.size) Int.MAX_VALUE else children[goalInd + 1].id)
            str += response.matchedString + END_LINE_STRING

            currentIndex = response.currentIndex
            currentIndex++
        }
        str = str.removeSuffix(END_LINE_STRING)
        if (iAmIn)
            if (str.length > 0)
                str = "$name:$tag($id)\n" + str.prependIndent(INDENT_STRING)
            else
                str = "$name:$tag($id)"
        return MatchedResponse(str, currentIndex)
    }

    override fun toString(): String {
        var str = "$name:$tag"
        if (children.isNotEmpty())
            str += END_LINE_STRING
        str += children.joinToString(separator = END_LINE_STRING).prependIndent(INDENT_STRING)
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

    companion object {
        const val INDENT_STRING = "    "
        const val END_LINE_STRING = "\n"
    }
}
