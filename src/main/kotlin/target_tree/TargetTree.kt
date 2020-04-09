package target_tree

import expression.TreepatExpression
import expression.operators.VisitorFunctionResponse

interface TargetTree<T : TargetTreeNode> {
    var root: T?

    fun findMatchesRaw(treepatExpression: TreepatExpression): VisitorFunctionResponse =
        treepatExpression.executeExpression(root)

    fun hasMatch(treepatExpression: TreepatExpression): Boolean = findMatchesRaw(treepatExpression).hasMatch
    fun findMatches(treepatExpression: TreepatExpression): List<List<TargetTreeNode>> =
        findMatchesRaw(treepatExpression).responses.map { it.matches }
}
