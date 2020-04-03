package operators

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import target_tree.TargetTreeNode

@ExtendWith(MockKExtension::class)
internal class ChildFunctionKtTest {

    @MockK
    private lateinit var mockTargetTreeNode: TargetTreeNode

    private lateinit var expressionWithMatch: VisitorFunction
    private lateinit var expressionWithZeroMatch: VisitorFunction
    private lateinit var expressionWithEmptyMatches: VisitorFunction

    @BeforeEach
    fun setUp() {
        expressionWithZeroMatch = { VisitorFunctionResponseFactory.createResponseWithZeroMatches(mockTargetTreeNode) }
        expressionWithMatch = { VisitorFunctionResponseFactory.createResponseWithMatches(mockTargetTreeNode) }
        expressionWithEmptyMatches = { VisitorFunctionResponseFactory.createResponseWithZeroMatches(hasMatch = true) }
    }

    @Test
    fun `childFunction should return empty response if father doesn't have matches`() {
        // act
        val result = childFunction(expressionWithZeroMatch, expressionWithMatch).invoke(mockTargetTreeNode)
        // assert
        assertFalse(result.hasMatch)
        assertEquals(0, result.responses.filter { it.matches.isNotEmpty() }.size)
        assert(result.responses.all { it.lastVisitedSibling == mockTargetTreeNode })
    }

    @Test
    fun `childFunction should return empty response if child doesn't have matches`() {
        // arrange
        every { mockTargetTreeNode.moveToFirstChild() } returns mockTargetTreeNode
        // act
        val result = childFunction(expressionWithMatch, expressionWithZeroMatch).invoke(mockTargetTreeNode)
        // assert
        assertFalse(result.hasMatch)
        assertEquals(0, result.responses.filter { it.matches.isNotEmpty() }.size)
        assert(result.responses.all { it.lastVisitedSibling == mockTargetTreeNode })
    }

    @Test
    fun `childFunction should return empty response if child answers is empty with matches`() {
        // arrange
        every { mockTargetTreeNode.moveToFirstChild() } returns mockTargetTreeNode
        // act
        val result = childFunction(expressionWithEmptyMatches, expressionWithEmptyMatches).invoke(mockTargetTreeNode)
        // assert
        assert(result.hasMatch)
        assertEquals(0, result.responses.filter { it.matches.isNotEmpty() }.size)
        assert(result.responses.all { it.lastVisitedSibling == mockTargetTreeNode })
    }

    @Test
    fun `childFunction should return response when father and child have matches`() {
        // arrange
        every { mockTargetTreeNode.moveToFirstChild() } returns mockTargetTreeNode
        // act
        val result = childFunction(expressionWithMatch, expressionWithMatch).invoke(mockTargetTreeNode)
        // assert
        assert(result.hasMatch)
        assertEquals(1, result.responses.filter { it.matches.isNotEmpty() }.size)
        assert(result.responses.all { it.lastVisitedSibling == mockTargetTreeNode })
    }
}
