package operators

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import target_tree.TargetTreeNode

@ExtendWith(MockKExtension::class)
internal class TreepatFunctionKtTest {

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
    fun `treepatFunction should return empty response if targetTreeNode is null`() {
        // act
        val result = treepatFunction(expressionWithMatch).invoke(null)
        // assert
        assertFalse(result.hasMatch)
        assertEquals(0, result.responses.filter { it.matches.isNotEmpty() }.size)
        assert(result.responses.all { it.lastVisitedSibling == null })
    }

    @Test
    fun `treepatFunction should return all matches found in all the tree`() {
        // arrange
        every { mockTargetTreeNode.nextLeftmostPreorderNode() } returns null
        // act
        val result = treepatFunction(expressionWithMatch).invoke(mockTargetTreeNode)
        // assert
        verify { mockTargetTreeNode.nextLeftmostPreorderNode() }
        assert(result.hasMatch)
        assertEquals(1, result.responses.filter { it.matches.isNotEmpty() }.size)
        assert(result.responses.all { it.lastVisitedSibling == mockTargetTreeNode })
    }

    @Test
    fun `treepatFunction should return empty response if no matches found `() {
        // arrange
        every { mockTargetTreeNode.nextLeftmostPreorderNode() } returns null
        // act
        val result = treepatFunction(expressionWithZeroMatch).invoke(mockTargetTreeNode)
        // assert
        verify { mockTargetTreeNode.nextLeftmostPreorderNode() }
        assertFalse(result.hasMatch)
        assertEquals(0, result.responses.filter { it.matches.isNotEmpty() }.size)
        assert(result.responses.all { it.lastVisitedSibling == mockTargetTreeNode })
    }
}
