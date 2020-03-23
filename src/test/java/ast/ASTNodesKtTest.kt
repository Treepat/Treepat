package ast

import TestFunction
import antlr.treepat.TreepatVisitorImplementation
import kotlin.test.assertEquals
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.junit.jupiter.api.Test
import runAllTestInFolder
import treepat.TreepatLexer
import treepat.TreepatParser

internal class ASTNodesKtTest {

    private fun createParserTree(inputAntlrString: String): ParseTree {
        val lexer = TreepatLexer(CharStreams.fromString(inputAntlrString))
        val tokenStream = CommonTokenStream(lexer)
        val parser = TreepatParser(tokenStream)
        return parser.treepat()
    }

    private val runTest: TestFunction = { inputAntlrString, output, error ->
        val tree = createParserTree(inputAntlrString)
        val visitor = TreepatVisitorImplementation()

        // act
        val root = visitor.visit(tree)

        // assert
        assertEquals(output, root.toString(), error)
    }

    @Test
    fun `should run all node test cases`() {
        val nodeTestCasesFolder = "./src/test/java/ast/node/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all child test cases`() {
        val nodeTestCasesFolder = "./src/test/java/ast/child/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all sibling test cases`() {
        val nodeTestCasesFolder = "./src/test/java/ast/sibling/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all breadth closure test cases`() {
        val nodeTestCasesFolder = "./src/test/java/ast/breadth_closure/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all union test cases`() {
        val nodeTestCasesFolder = "./src/test/java/ast/union/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all mix test cases`() {
        val nodeTestCasesFolder = "./src/test/java/ast/mix/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all large stable test cases`() {
        val nodeTestCasesFolder = "./src/test/java/ast/large_stable/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all large no stable test cases`() {
        val nodeTestCasesFolder = "./src/test/java/ast/large_no_stable/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all large no stable test cases with only child operator`() {
        val nodeTestCasesFolder = "./src/test/java/ast/large_no_stable_only_child/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }
    @Test
    fun `should run all large no stable test cases with mix`() {
        val nodeTestCasesFolder = "./src/test/java/ast/large_no_stable_mix/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all dot test cases`() {
        val nodeTestCasesFolder = "./src/test/java/ast/dot/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should have at least one sibling not end line`() {
        // Old version
        // arrange
        val inputAntlrString = """
            A
                B C
        """.trimIndent()
        val output = """
            A
                B
                C
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
