import kotlin.test.assertEquals
import org.antlr.v4.runtime.CharStreams
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import com.github.treepat.expression.TreepatExpression
import com.github.treepat.extensions.matchToString
import com.github.treepat.target_tree.default_tree.DefaultTargetTree
import java.io.File

/**
 * Given a treepat com.github.treepat.expression and a target_trees then find all matches of the com.github.treepat.expression in the target_trees large test.
 */
internal class GivenTreepatExpressionAndTreeThenFindMatchesLargeKtTest {

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
    fun `should run all node large test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}node/large/"
        return makeTests(nodeTestCasesFolder)
    }

    @TestFactory
    fun `should run all child large test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}child/large/"
        return makeTests(nodeTestCasesFolder)
    }

    @TestFactory
    fun `should run all sibling large test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}sibling/large/"
        return makeTests(nodeTestCasesFolder)
    }

    @TestFactory
    fun `should run all breadth closure large test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}breadth_closure/large/"
        return makeTests(nodeTestCasesFolder)
    }

    @TestFactory
    fun `should run all union large test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}union/large/"
        return makeTests(nodeTestCasesFolder)
    }

    @TestFactory
    fun `should run all dot large test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}dot/large/"
        return makeTests(nodeTestCasesFolder)
    }

    @TestFactory
    fun `should run all depth closure large test cases`(): List<DynamicTest> {
        val nodeTestCasesFolder = "${resourcesLocation}depth_closure/large/"
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
