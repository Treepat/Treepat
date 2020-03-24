package operators

import tree.TargetTreeNode

data class VisitorFunctionResponse(
    val responses: List<VisitorFunctionSimpleResponse> = listOf(VisitorFunctionSimpleResponse()),
    val hasMatch: Boolean = false
)

data class VisitorFunctionSimpleResponse(
    val matches: List<TargetTreeNode> = emptyList(),
    val lastVisitedSibling: TargetTreeNode? = null
) {
    constructor(targetTreeNode: TargetTreeNode) : this(listOf(targetTreeNode), targetTreeNode)
}

object VisitorFunctionResponseFactory {
    fun createResponseWithMatches(targetTreeNode: TargetTreeNode) =
        VisitorFunctionResponse(listOf(VisitorFunctionSimpleResponse(targetTreeNode)), true)

    fun createResponseWithZeroMatches(targetTreeNode: TargetTreeNode? = null, hasMatch: Boolean = false) =
        VisitorFunctionResponse(listOf(VisitorFunctionSimpleResponse(lastVisitedSibling = targetTreeNode)), hasMatch)

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
        lastVisitedSiblingFirst: Boolean = false
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
                    }
                )
            )
        }
        return VisitorFunctionResponse(answerMatches, secondResponse.hasMatch)
    }
}
