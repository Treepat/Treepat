package com.github.treepat.match_manager

import com.github.treepat.expression.TreepatExpression
import com.github.treepat.target_tree.TargetTree
import com.github.treepat.target_tree.TargetTreeNode

class MatchManager(private var expression: TreepatExpression, private var tree: TargetTree) {

    private var nextNodeToEvaluate: TargetTreeNode? = null

    init {
        this.nextNodeToEvaluate = tree.root
    }

    fun getNextMatch(): List<TargetTreeNode>? {
        if (nextNodeToEvaluate == null) {
            nextNodeToEvaluate = tree.root
            return null
        }

        var response = expression.executeExpression(nextNodeToEvaluate)
        this.nextNodeToEvaluate = nextNodeToEvaluate!!.nextPreorderNode()

        while ((!response.hasMatch || response.responses.filter { it.matches.isNotEmpty() }.isEmpty()) && nextNodeToEvaluate != null) {
            response = expression.executeExpression(nextNodeToEvaluate)
            nextNodeToEvaluate = nextNodeToEvaluate!!.nextPreorderNode()
        }

        var foundResponse: List<TargetTreeNode>? = null

        if (response.hasMatch && response.responses.filter { it.matches.isNotEmpty() }.isNotEmpty()) {
            foundResponse = response.responses.maxBy { it.matches.size }?.matches
        }
        return foundResponse

    }

    fun getAllMatches(): List<List<TargetTreeNode>> {

        val matches = mutableListOf<List<TargetTreeNode>>()
        nextNodeToEvaluate = tree.root

        while (nextNodeToEvaluate != null) {

            var response = expression.executeExpression(nextNodeToEvaluate)
            this.nextNodeToEvaluate = nextNodeToEvaluate!!.nextPreorderNode()

            while ((!response.hasMatch || response.responses.filter { it.matches.isNotEmpty() }.isEmpty()) && nextNodeToEvaluate != null) {
                response = expression.executeExpression(nextNodeToEvaluate)
                nextNodeToEvaluate = nextNodeToEvaluate!!.nextPreorderNode()
            }

            if (response.hasMatch && response.responses.filter { it.matches.isNotEmpty() }.isNotEmpty()) {
                matches.add(response.responses.maxBy { it.matches.size }?.matches!!)
            }
        }
        nextNodeToEvaluate = tree.root

        return matches
    }

    fun matchToString(match: List<TargetTreeNode>): String {
        return tree.root!!.matchedNodesString(match)
    }
}
