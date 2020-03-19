package tree

import TestFunction
import antlr.tree_format.TreeFormatVisitorImplementation
import antlr.treepat.TreepatVisitorImplementation
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.junit.jupiter.api.Test
import runAllTestInFolder
import tree_format.TreeFormatLexer
import tree_format.TreeFormatParser
import treepat.TreepatLexer
import treepat.TreepatParser
import kotlin.test.assertEquals

internal class ImpTargetTreeNodeTest {

    private fun createParserTree(inputAntlrString: String): ParseTree {
        val lexer = TreeFormatLexer(CharStreams.fromString(inputAntlrString))
        val tokenStream = CommonTokenStream(lexer)
        val parser = TreeFormatParser(tokenStream)
        return parser.subtree()
    }

    private val runTest: TestFunction = { inputAntlrString, output, error ->
        val tree = createParserTree(inputAntlrString)
        val visitor = TreeFormatVisitorImplementation()

        // act
        val root = visitor.visit(tree)

        // assert
        assertEquals(root.toString(), output, error)
    }

    @Test
    fun `should run all mix test cases for tree`() {
        val nodeTestCasesFolder = "./src/test/java/tree/mix/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all large stable test cases for tree`() {
        val nodeTestCasesFolder = "./src/test/java/tree/large_stable/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all large no stable test cases for tree`() {
        val nodeTestCasesFolder = "./src/test/java/tree/large_no_stable/"
        runAllTestInFolder(nodeTestCasesFolder, runTest)
    }
}
