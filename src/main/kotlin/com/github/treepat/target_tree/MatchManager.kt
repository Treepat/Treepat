package com.github.treepat.target_tree

import com.github.treepat.expression.TreepatExpression

class MatchManager(private val expression: TreepatExpression, private val tree: TargetTree) {

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
        var emptyMatches = response.responses.none { it.matches.isNotEmpty() }

        while ((!response.hasMatch || emptyMatches) && nextNodeToEvaluate != null) {
            response = expression.executeExpression(nextNodeToEvaluate)
            nextNodeToEvaluate = nextNodeToEvaluate!!.nextPreorderNode()

            emptyMatches = response.responses.none { it.matches.isNotEmpty() }
        }

        var foundResponse: List<TargetTreeNode>? = null

        if (response.hasMatch && !emptyMatches) {
            foundResponse = response.responses.maxBy { it.matches.size }?.matches
        }
        return foundResponse
    }

    fun getAllMatches(): List<List<TargetTreeNode>>? {

        nextNodeToEvaluate = tree.root

        var match: List<TargetTreeNode>? = getNextMatch() ?: return null

        val matches = mutableListOf<List<TargetTreeNode>>()
        while (match != null) {
            matches.add(match)
            match = getNextMatch()
        }
        return matches
    }
}
