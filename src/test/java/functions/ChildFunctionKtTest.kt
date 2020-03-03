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

internal class ChildFunctionKtTest {

    @Mock
    private lateinit var mockTargetTreeNode: TargetTreeNode

    @Mock
    private lateinit var mockCurrentTargetTreeNode: TargetTreeNode

    private lateinit var mockFatherVisitorFunction: VisitorFunction
    private lateinit var mockChildVisitorFunction: VisitorFunction

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockFatherVisitorFunction = { listOf(mockTargetTreeNode) }
        mockChildVisitorFunction = { listOf(mockTargetTreeNode) }
    }

    @Test
    fun `should move down first and then up`() { // I think I need some help naming this test according to context.
        // arrange
        val children = listOf(mockCurrentTargetTreeNode, mockCurrentTargetTreeNode)
        val function = childFunction(mockFatherVisitorFunction, mockChildVisitorFunction)
        `when`(mockCurrentTargetTreeNode.moveUp()).thenReturn(mockCurrentTargetTreeNode)
        `when`(mockCurrentTargetTreeNode.moveDown()).thenReturn(mockCurrentTargetTreeNode)
        // act
        val result = function(mockCurrentTargetTreeNode)
        // assert
        verify(mockCurrentTargetTreeNode, times(0)).moveUp()
        verify(mockCurrentTargetTreeNode, times(0)).moveRight()
        verify(mockCurrentTargetTreeNode, times(0)).moveLeft()
        verify(mockCurrentTargetTreeNode, times(1)).moveDown()

        assertEquals(children.size, result.size)
    }
}
