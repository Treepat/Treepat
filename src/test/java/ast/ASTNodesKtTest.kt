package ast

import antlr.treepat.TreepatVisitorImplementation
import java.io.File
import kotlin.test.assertEquals
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.junit.jupiter.api.Test
import treepat.TreepatLexer
import treepat.TreepatParser

internal class ASTNodesKtTest {

    private fun createParserTree(inputAntlrString: String): ParseTree {
        val lexer = TreepatLexer(CharStreams.fromString(inputAntlrString))
        val tokenStream = CommonTokenStream(lexer)
        val parser = TreepatParser(tokenStream)
        return parser.treepat()
    }

    private fun getOutputExpected(pathToOutputFile: String): String? {
        val fileOutput = File(pathToOutputFile)
        if (fileOutput.exists())
            return CharStreams.fromFileName(pathToOutputFile).toString()
        return null
    }

    private fun runAllTestInFolder(folderPath: String) {
        File(folderPath).list { _, name -> name.endsWith(".in") }!!.forEach {
            // arrange

            val inputAntlrString = CharStreams.fromFileName(folderPath + it).toString()

            var output = inputAntlrString
            getOutputExpected(folderPath + it.replace(".in", ".out"))?.let {
                outputResult -> output = outputResult
            }

            val fileMessage = File(folderPath + it.replace(".in", ".msg")).readLines()
            val error = fileMessage.first()

            val tree = createParserTree(inputAntlrString)
            val visitor = TreepatVisitorImplementation()

            // act
            val root = visitor.visit(tree)

            // assert
            assertEquals(root.toString(), output, error)
        }
    }

    @Test
    fun `should run all node test cases`() {
        val nodeTestCasesFolder = "./src/test/java/ast/node/"
        runAllTestInFolder(nodeTestCasesFolder)
    }

    @Test
    fun `should run all child test cases`() {
        val nodeTestCasesFolder = "./src/test/java/ast/child/"
        runAllTestInFolder(nodeTestCasesFolder)
    }
    @Test
    fun `should run all sibling test cases`() {
        val nodeTestCasesFolder = "./src/test/java/ast/sibling/"
        runAllTestInFolder(nodeTestCasesFolder)
    }
    @Test
    fun `should run all mix test cases`() {
        val nodeTestCasesFolder = "./src/test/java/ast/mix/"
        runAllTestInFolder(nodeTestCasesFolder)
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
