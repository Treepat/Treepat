import com.github.treepat.grammars.antlr.tree_format.TreeFormatVisitorImplementation
import com.github.treepat.grammars.antlr.treepat.TreepatVisitorImplementation
import com.github.treepat.grammars.ast.ASTNode
import kotlin.test.assertEquals
import com.github.treepat.expression.operators.createVisitorFunction
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import com.github.treepat.target_tree.TargetTreeNode
import antlr.tree_format.TreeFormatLexer
import antlr.tree_format.TreeFormatParser
import antlr.treepat.TreepatLexer
import antlr.treepat.TreepatParser
import java.io.File

typealias GivenTreepatExpressionAndTreeThenFindMatchesFunction = (String, String, String, String) -> Unit

/**
 * Given a treepat com.github.treepat.expression and a target_trees then find all matches of the com.github.treepat.expression in the target_trees.
 */
internal class GivenTreepatExpressionAndTreeThenFindMatchesKtTest {

    private val resourcesLocation = "./src/it/resources/expressions_trees_matches/"

    private fun parseTreeFile(tree: String): TargetTreeNode {
        val lexer = TreeFormatLexer(CharStreams.fromString(tree))
        val tokenStream = CommonTokenStream(lexer)
        val fileParser = TreeFormatParser(tokenStream)
        val treeFile: ParseTree = fileParser.subtree()
        val treeVisitor = TreeFormatVisitorImplementation()
        return treeVisitor.visit(treeFile)
    }

    private fun parseTreepat(tree: String): ASTNode {
        val lexer = TreepatLexer(CharStreams.fromString(tree))
        val tokenStream = CommonTokenStream(lexer)
        val treepatParser = TreepatParser(tokenStream)
        val treeTreepat: ParseTree = treepatParser.treepat()
        val treepatVisitor = TreepatVisitorImplementation()
        return treepatVisitor.visit(treeTreepat)
    }

    private val runTest: GivenTreepatExpressionAndTreeThenFindMatchesFunction =
        { treepatAntlrString, treeAntlrString, output, error ->
            // Treepat Parsing
            val astRoot: ASTNode = parseTreepat(treepatAntlrString)

            // Tree File Parsing
            val targetTreeNode: TargetTreeNode = parseTreeFile(treeAntlrString)

            val rootFunctionModule = createVisitorFunction(astRoot)

            // act
            val functionResult = rootFunctionModule.invoke(targetTreeNode)

            val solutions: List<String> = if (functionResult.hasMatch) {
                functionResult.responses.map { targetTreeNode.matchedNodesString(it.matches).trimIndent() }
            } else {
                listOf("Match not found")
            }

            // assert
            assertEquals(output, solutions.joinToString("\n-\n"), error)
        }

    @TestFactory
    fun `should run all node test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}node/"
        return makeTests(nodeTestCasesFolder)
    }

    @TestFactory
    fun `should run all child test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}child/"
        return makeTests(nodeTestCasesFolder)
    }

    @TestFactory
    fun `should run all sibling test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}sibling/"
        return makeTests(nodeTestCasesFolder)
    }

    @TestFactory
    fun `should run all breadth closure test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}breadth_closure/"
        return makeTests(nodeTestCasesFolder)
    }

    @TestFactory
    fun `should run all union test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}union/"
        return makeTests(nodeTestCasesFolder)
    }

    @TestFactory
    fun `should run all mix test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}mix/"
        return makeTests(nodeTestCasesFolder)
    }

    @TestFactory
    fun `should run all dot test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}dot/"
        return makeTests(nodeTestCasesFolder)
    }

    @TestFactory
    fun `should run all depth closure test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}depth_closure/"
        return makeTests(nodeTestCasesFolder)
    }

    private fun makeTests(folderPath: String): List<DynamicTest> {
        val tests = mutableListOf<DynamicTest>()
        File(folderPath).list { _, name -> name.endsWith(".tp") }!!.forEach {
            // arrange
            val expressionInput = CharStreams.fromFileName(folderPath + it).toString()
            val targetTreeInput = CharStreams.fromFileName(folderPath + it.replace(".tp", ".tef")).toString()

            var output = expressionInput
            getOutputExpected(folderPath + it.replace(".tp", ".out"))?.let { outputResult ->
                output = outputResult
            }

            output = output.trim()

            val fileMessage = File(folderPath + it.replace(".tp", ".msg")).readLines()
            val testName = fileMessage.first()

            tests.add(DynamicTest.dynamicTest(testName) { runTest(expressionInput, targetTreeInput, output, testName) })
        }
        return tests
    }
}
