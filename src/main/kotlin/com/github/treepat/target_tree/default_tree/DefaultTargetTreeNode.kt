package com.github.treepat.target_tree.default_tree

import com.github.treepat.target_tree.TargetTreeNode

data class MatchedResponse(val matchedString: String, val currentIndex: Int)

class DefaultTargetTreeNode(
    override val name: String = "",
    override val tag: String = "",
    override val id: Int = -1,
    override var children: List<TargetTreeNode> = mutableListOf()
) : TargetTreeNode, Comparable<DefaultTargetTreeNode> {

    var posAsChild: Int = -1
    var parent: DefaultTargetTreeNode? = null

    override fun moveToRightSibling(): TargetTreeNode? {
        if (posAsChild != -1 && posAsChild + 1 < parent?.children!!.size) {
            return this.parent?.children!![posAsChild + 1]
        }
        return null
    }

    override fun moveToLeftSibling(): TargetTreeNode? {
        if (posAsChild > 0) {
            return this.parent?.children!![posAsChild - 1]
        }
        return null
    }

    override fun moveToParent(): TargetTreeNode? = parent

    override fun moveToFirstChild(): TargetTreeNode? = children.firstOrNull()

    override fun matchedNodesString(selectedNodes: List<TargetTreeNode>): String =
        matchedNodesString(selectedNodes.sortedBy { it.id }, 0, Int.MAX_VALUE).matchedString.trimIndent()

    override fun nextPreorderNode(): TargetTreeNode? {
        if (children.isNotEmpty()) {
            return children.first()
        }
        return nextSiblingLeftmostChild()
    }

    fun nextSiblingLeftmostChild(): TargetTreeNode? {
        val rightSibling = moveToRightSibling()
        if (rightSibling != null) {
            return rightSibling
        }
        return nextUpwardPreorderNode()
    }

    private fun nextUpwardPreorderNode(): TargetTreeNode? {
        return parent?.nextSiblingLeftmostChild()
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
            var goalInd = (children as List<DefaultTargetTreeNode>).binarySearch(
                DefaultTargetTreeNode("", "", selectedNodes[currentIndex].id)
            )

            if (goalInd < 0 || (goalInd == 0 && selectedNodes[currentIndex].id != children[0].id)) {
                goalInd *= -1
                goalInd -= 2
            }

            val response = (children[goalInd] as DefaultTargetTreeNode).matchedNodesString(
                selectedNodes,
                currentIndex,
                if (goalInd + 1 == children.size) rightSiblingId else children[goalInd + 1].id
            )
            str += END_LINE_STRING + response.matchedString.prependIndent(
                INDENT_STRING
            )

            currentIndex = response.currentIndex
        }
        str = str.removePrefix(END_LINE_STRING)
        return MatchedResponse(str, currentIndex)
    }

    override fun getRoot(): DefaultTargetTreeNode {
        if (parent == null)
            return this
        return parent!!.getRoot()
    }

    override fun toString(): String {
        var str = "$name:$tag"
        if (children.isNotEmpty()) {
            str += END_LINE_STRING
            str += children.joinToString(separator = END_LINE_STRING).prependIndent(
                INDENT_STRING
            )
        }
        return str
    }

    companion object {
        const val INDENT_STRING = "    "
        const val END_LINE_STRING = "\n"
    }

    override fun compareTo(other: DefaultTargetTreeNode): Int = id.compareTo(other.id)
}
