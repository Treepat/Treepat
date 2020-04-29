package expression.operators

import target_tree.TargetTreeNode

data class VisitorFunctionResponse(
    val responses: List<VisitorFunctionSimpleResponse> = listOf(VisitorFunctionSimpleResponse()),
    val hasMatch: Boolean = false
)

data class VisitorFunctionSimpleResponse(
    val matches: List<TargetTreeNode> = emptyList(),
    val lastVisitedSibling: TargetTreeNode? = null,
    val depthTerm: TargetTreeNode? = null,
    val oddParent: TargetTreeNode? = null
) {
    constructor(targetTreeNode: TargetTreeNode) : this(listOf(targetTreeNode), targetTreeNode)
}

object VisitorFunctionResponseFactory {
    fun createResponseWithMatches(targetTreeNode: TargetTreeNode) =
        VisitorFunctionResponse(listOf(VisitorFunctionSimpleResponse(targetTreeNode)), true)

    fun createResponseWithZeroMatches(targetTreeNode: TargetTreeNode? = null, hasMatch: Boolean = false) =
        VisitorFunctionResponse(listOf(VisitorFunctionSimpleResponse(lastVisitedSibling = targetTreeNode)), hasMatch)

    fun createResponseWithDepthTerm(
        response: VisitorFunctionResponse,
        depthTerm: TargetTreeNode?,
        oddParent: TargetTreeNode?
    ): VisitorFunctionResponse {
        val responses = response.responses.map { it.copy(depthTerm = depthTerm, oddParent = oddParent) }
        return VisitorFunctionResponse(responses, response.hasMatch)
    }

    fun createResponse(
        responses: List<VisitorFunctionResponse>,
        targetTreeNode: TargetTreeNode?
    ): VisitorFunctionResponse {
        val allHasMatches = responses.filter { it.hasMatch }.flatMap { it.responses }
        return if (allHasMatches.isNotEmpty()) {
            VisitorFunctionResponse(allHasMatches, true)
        } else {
            createResponseWithZeroMatches(targetTreeNode)
        }
    }

    fun createMergeResponse(
        simpleResponse: VisitorFunctionSimpleResponse,
        secondResponse: VisitorFunctionResponse,
        lastVisitedSiblingFirst: Boolean = false,
        hasMatch: Boolean = false
    ): VisitorFunctionResponse {
        val answerMatches = mutableListOf<VisitorFunctionSimpleResponse>()
        for (secondIterator in secondResponse.responses) {
            answerMatches.add(
                VisitorFunctionSimpleResponse(
                    simpleResponse.matches + secondIterator.matches,
                    if (lastVisitedSiblingFirst || secondIterator.matches.isEmpty()) {
                        simpleResponse.lastVisitedSibling
                    } else {
                        secondIterator.lastVisitedSibling
                    },
                    if (lastVisitedSiblingFirst && simpleResponse.depthTerm != null) {
                        secondIterator.lastVisitedSibling?.moveToRightSibling()
                    } else {
                        simpleResponse.depthTerm ?: secondIterator.depthTerm
                    },
                    secondIterator.oddParent ?: simpleResponse.oddParent
                )
            )
        }
        return VisitorFunctionResponse(answerMatches, secondResponse.hasMatch || hasMatch)
    }
}
