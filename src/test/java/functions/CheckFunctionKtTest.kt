package functions

import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import tree.TargetTreeNode

internal class CheckFunctionKtTest {

    @Mock
    private lateinit var mockTargetTreeNode: TargetTreeNode

    @Mock
    private lateinit var mockCurrentTargetTreeNode: TargetTreeNode

    private val mockExpressionsOneMatch: VisitorFunction = { VisitorFunctionResponse(listOf(listOf(mockTargetTreeNode)), true) }

    private val mockExpressionsNoMatches: VisitorFunction = { VisitorFunctionResponse(emptyList(), false) }

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should return a response with match if finds any match with the first match`() {
        // arrange
        val function = checkFunction(mockExpressionsOneMatch)
        // act
        val result = function(mockCurrentTargetTreeNode)
        // assert
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(1)).moveToRightSibling()
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveToParent()
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveToLeftSibling()
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveToFirstChild()

        assertEquals(listOf(listOf(mockTargetTreeNode)), result.matches)
        assertTrue(result.hasMatch)
    }

    @Test
    fun `should return a no match and empty list if no matches found`() {
        // arrange
        val function = checkFunction(mockExpressionsNoMatches)
        // act
        val result = function(mockCurrentTargetTreeNode)
        // assert
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(1)).moveToRightSibling()
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveToParent()
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveToLeftSibling()
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveToFirstChild()

        assertEquals(listOf(emptyList()), result.matches)
        assertFalse(result.hasMatch)
    }

    @Test
    fun `should return a response with match if finds any match with the first match, whit null node`() {
        // arrange
        val function = checkFunction(mockExpressionsOneMatch)
        // act
        val result = function(null)
        // assert
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveToRightSibling()
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveToParent()
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveToLeftSibling()
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveToFirstChild()

        assertEquals(listOf(emptyList()), result.matches)
        assertFalse(result.hasMatch)
    }
}
