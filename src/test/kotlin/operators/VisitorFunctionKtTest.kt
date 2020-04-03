package operators

import ast.ASTNode
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class VisitorFunctionKtTest {

    @MockK
    private lateinit var mockASTNode: ASTNode

    @Test
    fun `createVisitorFunction should throw IllegalArgumentException if node type is not handled`() {
        // assert
        val error = assertFailsWith<IllegalArgumentException> { createVisitorFunction(mockASTNode) }
        assertEquals("This ASTNode subtype is not supported.", error.message)
    }
}
