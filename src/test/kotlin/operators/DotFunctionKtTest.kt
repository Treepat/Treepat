package operators

import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import target_tree.TargetTreeNode

@ExtendWith(MockKExtension::class)
internal class DotFunctionKtTest {

    @MockK
    private lateinit var mockTargetTreeNode: TargetTreeNode

    @Test
    fun `dotFunction should return matches with any targetTreeNode except null`() {
        // act
        val result = dotFunction().invoke(mockTargetTreeNode)
        // assert
        assert(result.hasMatch)
        assertEquals(result.responses.size, 1)
        assert(result.responses.first().matches.contains(mockTargetTreeNode))
        assertEquals(mockTargetTreeNode, result.responses.first().lastVisitedSibling)
    }

    @Test
    fun `dotFunction should return zero matches when targetTreeNode is null`() {
        // act
        val result = dotFunction().invoke(null)
        // assert
        assertFalse(result.hasMatch)
        assertEquals(result.responses.size, 1)
        assertFalse(result.responses.first().matches.contains(mockTargetTreeNode))
        assertNull(result.responses.first().lastVisitedSibling)
    }
}
