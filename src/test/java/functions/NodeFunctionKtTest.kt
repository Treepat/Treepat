package functions

import functions.MockValues.tNodeName
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import tree.TargetTreeNode

internal class NodeFunctionKtTest {

    @Mock
    private lateinit var mockCurrentTargetTreeNode: TargetTreeNode

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should not move and return a list containing current node`() {
        // arrange
        val function = nodeFunction(tNodeName)
        `when`(mockCurrentTargetTreeNode.name).thenReturn(tNodeName)
        // act
        val result = function(mockCurrentTargetTreeNode)
        // assert
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveRight()
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveUp()
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveLeft()
        Mockito.verify(mockCurrentTargetTreeNode, Mockito.times(0)).moveDown()

        assertEquals(1, result.size)
    }
}
