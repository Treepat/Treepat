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
        mockFatherVisitorFunction = { VisitorFunctionResponse(listOf(listOf(mockTargetTreeNode)), true) }
        mockChildVisitorFunction = { VisitorFunctionResponse(listOf(listOf(mockTargetTreeNode)), true) }
    }

    @Test
    fun `should move down first and then up`() { // I think I need some help naming this test according to context.
        // arrange
        val children = listOf(mockCurrentTargetTreeNode, mockCurrentTargetTreeNode)
        val function = childFunction(mockFatherVisitorFunction, mockChildVisitorFunction)
        `when`(mockCurrentTargetTreeNode.moveToParent()).thenReturn(mockCurrentTargetTreeNode)
        `when`(mockCurrentTargetTreeNode.moveToFirstChild()).thenReturn(mockCurrentTargetTreeNode)
        // act
        val result = function(mockCurrentTargetTreeNode)
        // assert
        verify(mockCurrentTargetTreeNode, times(0)).moveToParent()
        verify(mockCurrentTargetTreeNode, times(0)).moveToRightSibling()
        verify(mockCurrentTargetTreeNode, times(0)).moveToLeftSibling()
        verify(mockCurrentTargetTreeNode, times(1)).moveToFirstChild()

        assertEquals(children.size, result.matches.first().size)
        assertTrue(result.hasMatch)
    }
}
