package ast

import antlr.TreepatVisitorImplementation
import antlr.generate.TreepatLexer
import antlr.generate.TreepatParser
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ASTNodesKtTest {

    private fun createParserTree(inputAntlrString: String): ParseTree {
        val lexer = TreepatLexer(CharStreams.fromString(inputAntlrString))
        val tokenStream = CommonTokenStream(lexer)
        val parser = TreepatParser(tokenStream)
        return parser.subtree()
    }

    @Test
    fun `should have one node and then up`() {
        // arrange
        val inputAntlrString = """
            A
        """.trimIndent()
        val output = """
            A
        """.trimIndent()
        val tree = createParserTree(inputAntlrString)
        val visitor = TreepatVisitorImplementation()
        // act
        val root = visitor.visit(tree)
        // assert
        assertEquals(root.toString(), output)
    }

    @Test
    fun `should have one child and then up`() {
        // arrange
        val inputAntlrString = """
            A
                B
        """.trimIndent()
        val output = """
            A(B)
        """.trimIndent()
        val tree = createParserTree(inputAntlrString)
        val visitor = TreepatVisitorImplementation()
        // act
        val root = visitor.visit(tree)
        // assert
        assertEquals(root.toString(), output)
    }

    @Test
    fun `should have at least one sibling and then up`() {
        // arrange
        val inputAntlrString = """
            A
                B
                C
        """.trimIndent()
        val output = """
            A(B C)
        """.trimIndent()
        val tree = createParserTree(inputAntlrString)
        val visitor = TreepatVisitorImplementation()
        // act
        val root = visitor.visit(tree)
        // assert
        assertEquals(root.toString(), output)
        // TODO - sibling should have only one node
    }

    @Test
    fun `should have many node, three children, two sibling and then up`() {
        // arrange
        val inputAntlrString = """
            A
                B
                    C
                D
                E
                    F
                    G
        """.trimIndent()
        val output = """
            A(B(C) D E(F G))
        """.trimIndent()
        val tree = createParserTree(inputAntlrString)
        val visitor = TreepatVisitorImplementation()
        // act
        val root = visitor.visit(tree)
        // assert
        assertEquals(root.toString(), output)
        // TODO - sibling should have only one node
    }

    @Test
    fun `should have one child not tab only one space`() {
        // arrange
        val inputAntlrString = """
            A
             B
        """.trimIndent()
        val output = """
            A(B)
        """.trimIndent()
        val tree = createParserTree(inputAntlrString)
        val visitor = TreepatVisitorImplementation()
        // act
        val root = visitor.visit(tree)
        // assert
        assertEquals(root.toString(), output)
    }

    @Test
    fun `should have at least one sibling not end line`() {
        // arrange
        val inputAntlrString = """
            A
                B C
        """.trimIndent()
        val output = """
            A(B C)
        """.trimIndent()
        val tree = createParserTree(inputAntlrString)
        val visitor = TreepatVisitorImplementation()
        // act
        val root = visitor.visit(tree)
        // assert
        assertEquals(root.toString(), output)
        // TODO - sibling should have only one node
    }
}
