import antlr.treepat.TreepatVisitorImplementation
import kotlin.test.assertEquals
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import treepat.TreepatLexer
import treepat.TreepatParser

/**
 * Integration tests that verifies the correct creation the ASTNode target_trees based on a Treepat expression.
 */
internal class GivenTreepatExpressionBuildASTNodeTreeKtTest {

    private val resourcesLocation = "./src/it/resources/ast/"

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

    @TestFactory
    fun `should run all node test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}node/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }

    @TestFactory
    fun `should run all child test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}child/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }

    @TestFactory
    fun `should run all sibling test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}sibling/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }

    @TestFactory
    fun `should run all breadth closure test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}breadth_closure/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }

    @TestFactory
    fun `should run all union test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}union/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }

    @TestFactory
    fun `should run all mix test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}mix/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }

    @TestFactory
    fun `should run all large stable test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}large_stable/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }

    @TestFactory
    fun `should run all large no stable test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}large_no_stable/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }

    @TestFactory
    fun `should run all large no stable test cases with only child operator`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}large_no_stable_only_child/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }

    @TestFactory
    fun `should run all large no stable test cases with mix`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}large_no_stable_mix/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }

    @TestFactory
    fun `should run all dot test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}dot/"
        return makeTestsFromResourceFolder(nodeTestCasesFolder, runTest)
    }
}
