package expression.operators

import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import target_tree.TargetTreeNode

@ExtendWith(MockKExtension::class)
internal class UnionFunctionKtTest {

    @MockK
    private lateinit var mockTargetTreeNode: TargetTreeNode

    private lateinit var expressionWithMatch: VisitorFunction
    private lateinit var expressionWithZeroMatch: VisitorFunction

    @BeforeEach
    fun setUp() {
        expressionWithZeroMatch = { VisitorFunctionResponseFactory.createResponseWithZeroMatches(mockTargetTreeNode) }
        expressionWithMatch = { VisitorFunctionResponseFactory.createResponseWithMatches(mockTargetTreeNode) }
    }

    @Test
    fun `unionFunction should return match with all the responses that match`() {
        // arrange
        val tExpressionsWithMatch = listOf(
            expressionWithMatch,
            expressionWithZeroMatch,
            expressionWithMatch,
            expressionWithZeroMatch
        )
        val tNumberOfMatches = tExpressionsWithMatch.filter { it.invoke(mockTargetTreeNode).hasMatch }.size
        // act
        val response = unionFunction(tExpressionsWithMatch).invoke(mockTargetTreeNode)
        // assert
        assert(response.hasMatch)
        assertEquals(tNumberOfMatches, response.responses.flatMap { it.matches }.size)
    }

    @Test
    fun `unionFunction should return zero matches if no match found`() {
        // arrange
        val tExpressionsWithNoMatch = listOf(
            expressionWithZeroMatch,
            expressionWithZeroMatch,
            expressionWithZeroMatch,
            expressionWithZeroMatch
        )
        val tNumberOfMatches = tExpressionsWithNoMatch.filter { it.invoke(mockTargetTreeNode).hasMatch }.size
        // act
        val response = unionFunction(tExpressionsWithNoMatch).invoke(mockTargetTreeNode)
        // assert
        assertFalse(response.hasMatch)
        assertEquals(tNumberOfMatches, response.responses.flatMap { it.matches }.size)
    }
}
