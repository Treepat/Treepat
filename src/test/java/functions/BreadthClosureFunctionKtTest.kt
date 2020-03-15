package functions

import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import tree.TargetTreeNode

class BreadthClosureFunctionKtTest {

    @Mock
    private lateinit var mockTargetTreeNode: TargetTreeNode

    @Mock
    private lateinit var mockCurrentTargetTreeNode: TargetTreeNode

    private lateinit var mockVisitorFunction: VisitorFunction

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockVisitorFunction = { VisitorFunctionResponse(listOf(mockTargetTreeNode), true) }
    }

    @Test
    fun `should not move right when siblings list has single element`() {
        // arrange
        val expression = mockVisitorFunction
        val function = breadthClosureFunction(expression)
        // act
        val result = function(null)
        // assert
        verify(mockCurrentTargetTreeNode, times(0)).moveToRightSibling()
        verify(mockCurrentTargetTreeNode, times(0)).moveToParent()
        verify(mockCurrentTargetTreeNode, times(0)).moveToLeftSibling()
        verify(mockCurrentTargetTreeNode, times(0)).moveToFirstChild()

        assertEquals(0, result.matches.size)
        assertTrue(result.hasMatch)
    }

    @Test
    fun `should not move right when siblings list has multiple elements`() {
        // arrange
        val expression = mockVisitorFunction
        val function = breadthClosureFunction(expression)
        `when`(mockCurrentTargetTreeNode.moveToRightSibling()).thenReturn(mockTargetTreeNode)
        // act
        val result = function(mockCurrentTargetTreeNode)
        // assert
        verify(mockCurrentTargetTreeNode, times(1)).moveToRightSibling()
        verify(mockCurrentTargetTreeNode, times(0)).moveToParent()
        verify(mockCurrentTargetTreeNode, times(0)).moveToLeftSibling()
        verify(mockCurrentTargetTreeNode, times(0)).moveToFirstChild()

        assertEquals(2, result.matches.size)
        assertTrue(result.hasMatch)
    }
}
