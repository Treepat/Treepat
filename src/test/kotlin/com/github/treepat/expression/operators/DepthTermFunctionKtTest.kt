package expression.operators

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import target_tree.TargetTreeNode

@ExtendWith(MockKExtension::class)
internal class DepthTermFunctionKtTest {

    @MockK
    private lateinit var mockTargetTreeNode: TargetTreeNode

    private lateinit var expressionWithMatch: VisitorFunction

    @BeforeEach
    fun setUp() {
        expressionWithMatch = { VisitorFunctionResponseFactory.createResponseWithMatches(mockTargetTreeNode) }
    }

    @Test
    fun `depthTermFunction should create response with depth term`() {
        // arrange
        every { mockTargetTreeNode.moveToFirstChild() } returns mockTargetTreeNode
        // act
        val result = depthTermFunction(expressionWithMatch).invoke(mockTargetTreeNode)
        // assert
        verify(exactly = 1) { mockTargetTreeNode.moveToFirstChild() }
        assert(result.hasMatch)
        assert(result.responses.all { it.depthTerm == mockTargetTreeNode })
        assert(result.responses.all { it.lastVisitedSibling == mockTargetTreeNode })
    }
}
