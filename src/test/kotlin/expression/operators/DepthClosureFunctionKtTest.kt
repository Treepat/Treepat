package expression.operators

import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import target_tree.TargetTreeNode

@ExtendWith(MockKExtension::class)
internal class DepthClosureFunctionKtTest {
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
    fun `depthClosureFunction should return empty response with match if targetTreeNode is null`() {
        // act
        val result = depthClosureFunction(expressionWithMatch).invoke(null)
        // assert
        assertFalse(result.hasMatch)
        assertEquals(0, result.responses.filter { it.matches.isNotEmpty() }.size)
        assert(result.responses.all { it.lastVisitedSibling == null })
    }

    @Test
    fun `depthClosureFunction should return all matches found`() {
        // arrange
        // act
        val result = depthClosureFunction(expressionWithMatch).invoke(mockTargetTreeNode)
        // assert
        assert(result.hasMatch)
        assert(result.responses.all { it.depthTerm == null })
        assertEquals(1, result.responses.filter { it.matches.isNotEmpty() }.size)
    }
}
