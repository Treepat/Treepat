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

    private var countNode: Int = 0
    private var countChild: Int = 0
    private var countSibling: Int = 0

    @BeforeEach
    fun setUp() {
        countNode = 0
        countChild = 0
        countSibling = 0
    }

    private fun createParserTree(inputAntlrString: String): ParseTree {
        val lexer = TreepatLexer(CharStreams.fromString(inputAntlrString))
        val tokenStream = CommonTokenStream(lexer)
        val parser = TreepatParser(tokenStream)
        return parser.subtree()
    }

    private fun countNodes(node: ASTNode) {
        when (node) {
            is Node -> countNode++
            is Child -> {
                countNodes(node.father)
                countNodes(node.child)
                countChild++
            }
            is Sibling -> {
                node.siblings.forEach { countNodes(it) }
                countSibling++
            }
            else -> throw IllegalArgumentException("This ASTNode subtype is not supported.")
        }
    }

    @Test
    fun `should have one node and then up`() {
        // arrange
        val inputAntlrString = """
            A
        """.trimIndent()
        val tree = createParserTree(inputAntlrString)
        val visitor = TreepatVisitorImplementation()
        val root = visitor.visit(tree)
        // act
        countNodes(root)
        // assert
        assertEquals(countNode, 1)
        assertEquals(countChild, 0)
        assertEquals(countSibling, 0)
    }

    @Test
    fun `should have one child and then up`() {
        // arrange
        val inputAntlrString = """
            A
                B
        """.trimIndent()
        val tree = createParserTree(inputAntlrString)
        val visitor = TreepatVisitorImplementation()
        val root = visitor.visit(tree)
        // act
        countNodes(root)
        // assert
        assertEquals(countNode, 2)
        assertEquals(countChild, 1)
    }

    @Test
    fun `should have at least one sibling and then up`() {
        // arrange
        val inputAntlrString = """
            A
                B
                C
        """.trimIndent()
        val tree = createParserTree(inputAntlrString)
        val visitor = TreepatVisitorImplementation()
        val root = visitor.visit(tree)
        // act
        countNodes(root)
        // assert
        assertEquals(countNode, 3)
        // TODO - sibling should have only one node
        assertTrue(countSibling >= 1)
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
        val tree = createParserTree(inputAntlrString)
        val visitor = TreepatVisitorImplementation()
        val root = visitor.visit(tree)
        // act
        countNodes(root)
        // assert
        assertEquals(countNode, 7)
        assertEquals(countChild, 3)
        // TODO - sibling should have only one node
        assertTrue(countSibling >= 2)
    }

    @Test
    fun `should have one child not tab only one space`() {
        // arrange
        val inputAntlrString = """
            A
             B
        """.trimIndent()
        val tree = createParserTree(inputAntlrString)
        val visitor = TreepatVisitorImplementation()
        val root = visitor.visit(tree)
        // act
        countNodes(root)
        // assert
        assertEquals(countNode, 2)
        assertEquals(countChild, 1)
    }

    @Test
    fun `should have at least one sibling not end line`() {
        // arrange
        val inputAntlrString = """
            A
                B C
        """.trimIndent()
        val tree = createParserTree(inputAntlrString)
        val visitor = TreepatVisitorImplementation()
        val root = visitor.visit(tree)
        // act
        countNodes(root)
        // assert
        assertEquals(countNode, 3)
        // TODO - sibling should have only one node
        assertTrue(countSibling >= 1)
    }
}
