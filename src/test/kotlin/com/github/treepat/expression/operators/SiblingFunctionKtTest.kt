package com.github.treepat.expression.operators

import com.github.treepat.target_tree.TargetTreeNode
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class SiblingFunctionKtTest {

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
    fun `siblingFunction should return a response with zero matches if the non sibling doesn't have matches`() {
        // act
        val result = siblingFunction(expressionWithZeroMatch, expressionWithZeroMatch).invoke(mockTargetTreeNode)
        // assert
        assertFalse(result.hasMatch)
        assertEquals(0, result.responses.filter { it.matches.isNotEmpty() }.size)
        assert(result.responses.all { it.lastVisitedSibling == mockTargetTreeNode })
    }

    @Test
    fun `siblingFunction should return a response with zero matches if the first sibling doesn't have matches`() {
        // act
        val result = siblingFunction(expressionWithZeroMatch, expressionWithMatch).invoke(mockTargetTreeNode)
        // assert
        assertFalse(result.hasMatch)
        assertEquals(0, result.responses.filter { it.matches.isNotEmpty() }.size)
        assert(result.responses.all { it.lastVisitedSibling == mockTargetTreeNode })
    }

    @Test
    fun `siblingFunction should return a response with zero matches if the second sibling doesn't have matches`() {
        // arrange
        every { mockTargetTreeNode.moveToRightSibling() } returns mockTargetTreeNode
        // act
        val result = siblingFunction(expressionWithMatch, expressionWithZeroMatch).invoke(mockTargetTreeNode)
        // assert
        verify { mockTargetTreeNode.moveToRightSibling() }
        assertFalse(result.hasMatch)
        assertEquals(0, result.responses.filter { it.matches.isNotEmpty() }.size)
        assert(result.responses.all { it.lastVisitedSibling == mockTargetTreeNode })
    }
}
