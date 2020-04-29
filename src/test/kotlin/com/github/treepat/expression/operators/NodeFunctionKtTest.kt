package expression.operators

import MockValues.tNodeName
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import target_tree.TargetTreeNode

@ExtendWith(MockKExtension::class)
internal class NodeFunctionKtTest {

    @MockK
    private lateinit var mockTargetTreeNode: TargetTreeNode

    @Test
    fun `nodeFunction should return a response with match if target node name matches with grammar node`() {
        // arrange
        every { mockTargetTreeNode.name } returns tNodeName
        // act
        val result = nodeFunction(tNodeName).invoke(mockTargetTreeNode)
        // assert
        assert(result.hasMatch)
        assertEquals(result.responses.size, 1)
        assert(result.responses.first().matches.contains(mockTargetTreeNode))
        assertEquals(mockTargetTreeNode, result.responses.first().lastVisitedSibling)
    }

    @Test
    fun `nodeFunction should return zero response when match if target node name doesn't matches with grammar node`() {
        // arrange
        val tOtherName = "other name"
        every { mockTargetTreeNode.name } returns tNodeName
        // act
        val result = nodeFunction(tOtherName).invoke(mockTargetTreeNode)
        // assert
        assertFalse(result.hasMatch)
        assertEquals(result.responses.size, 1)
        assertFalse(result.responses.first().matches.contains(mockTargetTreeNode))
        assertEquals(mockTargetTreeNode, result.responses.first().lastVisitedSibling)
    }
}
