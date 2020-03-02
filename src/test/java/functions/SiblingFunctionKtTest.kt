package functions

import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import tree.TargetTreeNode

class SiblingFunctionKtTest {

    @Mock
    private lateinit var mockTargetTreeNode: TargetTreeNode

    @Mock
    private lateinit var mockCurrentTargetTreeNode: TargetTreeNode

    private lateinit var mockVisitorFunction: VisitorFunction

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockVisitorFunction = { listOf(mockTargetTreeNode) }
    }

    @Test
    fun `should not move right when siblings list has single element`() {
        // arrange
        val siblings = listOf(mockVisitorFunction)
        val function = siblingFunction(siblings)
        // act
        val result = function(mockCurrentTargetTreeNode)
        // assert
        verify(mockCurrentTargetTreeNode, times(0)).moveRight()
        verify(mockCurrentTargetTreeNode, times(0)).moveUp()
        verify(mockCurrentTargetTreeNode, times(0)).moveLeft()
        verify(mockCurrentTargetTreeNode, times(0)).moveDown()

        assertEquals(siblings.size, result.size)
    }

    @Test
    fun `should move to right when siblings list has multiple elements`() {
        // arrange
        val siblings = listOf(mockVisitorFunction, mockVisitorFunction)
        val function = siblingFunction(siblings)
        `when`(mockCurrentTargetTreeNode.moveRight()).thenReturn(mockCurrentTargetTreeNode)
        // act
        val result = function(mockCurrentTargetTreeNode)
        // assert
        verify(mockCurrentTargetTreeNode, times(1)).moveRight()
        verify(mockCurrentTargetTreeNode, times(0)).moveUp()
        verify(mockCurrentTargetTreeNode, times(0)).moveLeft()
        verify(mockCurrentTargetTreeNode, times(0)).moveDown()

        assertEquals(siblings.size, result.size)
    }
}
