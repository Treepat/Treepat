package com.github.treepat.grammars.ast

import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class ASTNodeFactoryKtTest {

    @MockK
    private lateinit var mockASTNode: ASTNode

    @Test
    fun `createSiblingNodes should return a sibling with the the two nodes if the list is size 2`() {
        // arrange
        val tSiblings = listOf(mockASTNode, mockASTNode)
        // act
        val result = createSiblingNodes(tSiblings)
        // assert
        assertNotNull(result as? Sibling)
        result as Sibling
        assertEquals(mockASTNode, result.firstSiblings)
        assertEquals(mockASTNode, result.secondSibling)
    }

    @Test
    fun `createSiblingNodes should pair the siblings if list size is more that 2`() {
        // arrange
        val tSiblings = listOf(mockASTNode, mockASTNode, mockASTNode)
        // act
        val result = createSiblingNodes(tSiblings)
        // assert
        assert(result is Sibling)
        result as Sibling
        assertFalse(result.firstSiblings is Sibling)
        assert(result.secondSibling is Sibling)
        assertEquals(mockASTNode, (result.secondSibling as Sibling).firstSiblings)
        assertEquals(mockASTNode, (result.secondSibling as Sibling).secondSibling)
    }

    @Test
    fun `createSiblingNodes should throw IllegalArgumentException if list size is less that 2 `() {
        // arrange
        val tSibling = listOf(mockASTNode)
        // act
        val error = assertFailsWith<IllegalArgumentException> { createSiblingNodes(tSibling) }
        // assert
        assertEquals("There must be at least 2 siblings.", error.message)
    }
}
