package expression.operators

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import target_tree.TargetTreeNode

@ExtendWith(MockKExtension::class)
internal class BreadthClosureFunctionKtTest {

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
    fun `breadthClosureFunction should return empty response with match if targetTreeNode is null`() {
        // act
        val result = breadthClosureFunction(expressionWithMatch).invoke(null)
        // assert
        assert(result.hasMatch)
        assertEquals(0, result.responses.filter { it.matches.isNotEmpty() }.size)
        assert(result.responses.all { it.lastVisitedSibling == null })
    }

    @Test
    fun `breadthClosureFunction should return all matches found`() {
        // arrange
        every { mockTargetTreeNode.moveToRightSibling() } returns null
        // act
        val result = breadthClosureFunction(expressionWithMatch).invoke(mockTargetTreeNode)
        // assert
        verify { mockTargetTreeNode.moveToRightSibling() }
        assert(result.hasMatch)
        assertEquals(1, result.responses.filter { it.matches.isNotEmpty() }.size)
        assert(result.responses.all { it.lastVisitedSibling == mockTargetTreeNode })
    }
}
