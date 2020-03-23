package e2e

import TestFunctionE2E
import antlr.tree_format.TreeFormatVisitorImplementation
import antlr.treepat.TreepatVisitorImplementation
import ast.ASTNode
import functions.createVisitorFunction
import kotlin.test.assertEquals
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.junit.jupiter.api.Test
import runAllTestInFolderE2E
import tree.TargetTreeNode
import tree_format.TreeFormatLexer
import tree_format.TreeFormatParser
import treepat.TreepatLexer
import treepat.TreepatParser

internal class E2EKtTest {

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

    private val runTest: TestFunctionE2E = { treepatAntlrString, treeAntlrString, output, error ->
        // Treepat Parsing
        val astRoot: ASTNode = parseTreepat(treepatAntlrString)

        // Tree File Parsing
        val targetTreeNode: TargetTreeNode = parseTreeFile(treeAntlrString)

        //
        val rootFunctionModule = createVisitorFunction(astRoot)

        // act
        val functionResult = rootFunctionModule.invoke(targetTreeNode)

        val solutions: List<String> = if (functionResult.hasMatch) {
            functionResult.responses.map { targetTreeNode.matchedNodesString(it.matches) }
        } else {
            listOf("Match not found")
        }

        // assert
        assertEquals(output, solutions.joinToString("\n-\n"), error)
    }

    @Test
    fun `should run all node test cases`() {
        val nodeTestCasesFolder = "./src/test/java/e2e/node/"
        runAllTestInFolderE2E(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all child test cases`() {
        val nodeTestCasesFolder = "./src/test/java/e2e/child/"
        runAllTestInFolderE2E(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all sibling test cases`() {
        val nodeTestCasesFolder = "./src/test/java/e2e/sibling/"
        runAllTestInFolderE2E(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all breadth closure test cases`() {
        val nodeTestCasesFolder = "./src/test/java/e2e/breadth_closure/"
        runAllTestInFolderE2E(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all union test cases`() {
        val nodeTestCasesFolder = "./src/test/java/e2e/union/"
        runAllTestInFolderE2E(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all mix test cases`() {
        val nodeTestCasesFolder = "./src/test/java/e2e/mix/"
        runAllTestInFolderE2E(nodeTestCasesFolder, runTest)
    }

    @Test
    fun `should run all dot test cases`() {
        val nodeTestCasesFolder = "./src/test/java/e2e/dot/"
        runAllTestInFolderE2E(nodeTestCasesFolder, runTest)
    }
}
