import TestFunction
import antlr.tree_format.TreeFormatVisitorImplementation
import kotlin.test.assertEquals
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import tree_format.TreeFormatLexer
import tree_format.TreeFormatParser

/**
 * Given target target_trees then build implementation of target target_trees node.
 */
internal class GivenTargetTreeThenBuildImpTargetTreeKtTest {

    private val resourcesLocation = "./src/it/resources/target_trees/"

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
        assertEquals(output, root.toString(), error)
    }

    @TestFactory
    fun `should run all mix test cases for tree`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}mix/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }

    @TestFactory
    fun `should run all large stable test cases for tree`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}large_stable/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }

    @TestFactory
    fun `should run all large no stable test cases for tree`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}large_no_stable/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }
}
