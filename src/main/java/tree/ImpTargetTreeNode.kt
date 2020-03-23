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

    override fun nextLeftmostPreorderNode(): TargetTreeNode? {
        if (children.isNotEmpty()) {
            return children.first()
        }
        return nextSiblingLeftmostChild()
    }

    fun nextSiblingLeftmostChild(): TargetTreeNode? {
        val rightSibling = moveToRightSibling()
        if (rightSibling != null) {
            return rightSibling.nextLeftmostPreorderNode()
        }
        return nextUpwardPreorderNode()
    }

    private fun nextUpwardPreorderNode(): TargetTreeNode? {
        return (parent as ImpTargetTreeNode?)?.nextSiblingLeftmostChild()
    }

    @Suppress("UNCHECKED_CAST")
    fun matchedNodesString(
        selectedNodes: List<TargetTreeNode>,
        initialIndex: Int,
        rightSiblingId: Int
    ): MatchedResponse {
        var str = ""
        var currentIndex = initialIndex
        if (selectedNodes[initialIndex].id == id) {
            str = "$name:$tag($id)"
            currentIndex++
        }
        while (currentIndex < selectedNodes.size && selectedNodes[currentIndex].id < rightSiblingId) {
            var goalInd = (children as List<ImpTargetTreeNode>).binarySearch(
                ImpTargetTreeNode(
                    "",
                    "",
                    selectedNodes[currentIndex].id
                ), ImpTargetTreeNodeComparator()
            )

            if (goalInd < 0 || (goalInd == 0 && selectedNodes[currentIndex].id != children[0].id)) {
                goalInd *= -1
                goalInd -= 2 // TODO - This might not be needed in the future when searching for siblings but cannot be tested right now
            }

            val response = (children[goalInd] as ImpTargetTreeNode).matchedNodesString(
                selectedNodes,
                currentIndex,
                if (goalInd + 1 == children.size) rightSiblingId else children[goalInd + 1].id
            )
            str += END_LINE_STRING + response.matchedString.prependIndent(INDENT_STRING)

            currentIndex = response.currentIndex
        }
        str = str.removePrefix(END_LINE_STRING)
        return MatchedResponse(str, currentIndex)
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
            val index = (children as List<ImpTargetTreeNode>).binarySearch(
                son as ImpTargetTreeNode,
                ImpTargetTreeNodeComparator()
            )
            if (index >= 0 && index + 1 < children.size)
                return children[index + 1]
        }
        return null
    }

    @Suppress("UNCHECKED_CAST")
    private fun getLeftSibling(son: TargetTreeNode): TargetTreeNode? {
        if (children.isNotEmpty()) {
            val index = (children as List<ImpTargetTreeNode>).binarySearch(
                son as ImpTargetTreeNode,
                ImpTargetTreeNodeComparator()
            )
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
