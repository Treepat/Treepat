import kotlin.test.assertEquals
import org.antlr.v4.runtime.CharStreams
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import com.github.treepat.extensions.matchToString
import com.github.treepat.expression.TreepatExpression
import com.github.treepat.target_tree.default_tree.DefaultTargetTree
import java.io.File

typealias GivenTreepatExpressionAndTreeThenFindMatchesFunction = (String, String, String, String) -> Unit

/**
 * Given a treepat com.github.treepat.expression and a target_trees then find all matches of the com.github.treepat.expression in the target_trees.
 */
internal class GivenTreepatExpressionAndTreeThenFindMatchesKtTest {

    private val resourcesLocation = "./src/it/resources/expressions_trees_matches/"

    private val runTest: GivenTreepatExpressionAndTreeThenFindMatchesFunction =
        { treepatAntlrString, treeAntlrString, output, error ->
            // Treepat Parsing
            val treepatExpression = TreepatExpression.createFromString(treepatAntlrString)
            // Tree File Parsing
            val targetTree = DefaultTargetTree.createFromString(treeAntlrString)

            val matches = targetTree.findMatches(treepatExpression)

            val printableMatches: List<String>

            if (matches != null) {
                printableMatches = matches.map{ it.matchToString() }
            } else {
                printableMatches = listOf("Match not found")
            }

            // assert
            assertEquals(output, printableMatches.joinToString("\n-\n"), error)
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
