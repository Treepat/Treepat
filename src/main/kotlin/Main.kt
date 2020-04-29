package com.github.treepat

import com.github.treepat.expression.TreepatExpression
import com.github.treepat.grammars.antlr.tree_format.TreeFormatVisitorImplementation
import com.github.treepat.grammars.antlr.treepat.TreepatVisitorImplementation
import com.github.treepat.grammars.ast.ASTNode
import java.nio.file.Paths
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JScrollPane
import org.antlr.v4.gui.TreeViewer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree
import com.github.treepat.target_tree.TargetTreeNode
import com.github.treepat.target_tree.default_tree.DefaultTargetTree
import com.github.treepat.target_tree.default_tree.DefaultTargetTreeNode
import tree_format.TreeFormatLexer
import tree_format.TreeFormatParser
import treepat.TreepatLexer
import treepat.TreepatParser

object Main {

    private const val jFrameTitle = "Antlr AST"

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        // Treepat Parsing
        val treepatExpression = TreepatExpression.createFromFile(Paths.get(args[0]))
        // Tree File Parsing
        val targetTree =
            DefaultTargetTree<DefaultTargetTreeNode>(Paths.get(args[1]))

        val functionResult = targetTree.findMatchesRaw(treepatExpression)

        val solutions: List<String>
        solutions = if (functionResult.hasMatch) {
            functionResult.responses.map { targetTree.root?.matchedNodesString(it.matches)?.trimIndent() ?: "" }
        } else {
            listOf("Match not found")
        }
        print(solutions.joinToString("\n-\n"))
    }

    private fun showASTNodeFrame(parser: Parser, tree: ParseTree) {
        val frame = JFrame(jFrameTitle)
        val panel = JPanel()
        val viewer = TreeViewer(listOf(*parser.ruleNames), tree)
        viewer.scale = 1.0
        panel.add(viewer)
        val scrollPane = JScrollPane(panel)
        scrollPane.horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        scrollPane.verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        scrollPane.setBounds(50, 30, 300, 50)
        frame.add(JPanel().add(scrollPane))
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.pack()
        frame.isVisible = true
    }

    private fun parseTreeFile(fileName: String): TargetTreeNode {
        val lexer = TreeFormatLexer(CharStreams.fromFileName(fileName))
        val tokenStream = CommonTokenStream(lexer)
        val fileParser = TreeFormatParser(tokenStream)
        val tree: ParseTree = fileParser.subtree()
        val treeVisitor = TreeFormatVisitorImplementation()

        showASTNodeFrame(fileParser, tree)

        return treeVisitor.visit(tree)
    }

    private fun parseTreepat(fileName: String): ASTNode {
        val lexer = TreepatLexer(CharStreams.fromFileName(fileName))
        val tokenStream = CommonTokenStream(lexer)
        val treepatParser = TreepatParser(tokenStream)
        val tree: ParseTree = treepatParser.treepat()
        val treepatVisitor = TreepatVisitorImplementation()

        showASTNodeFrame(treepatParser, tree)

        return treepatVisitor.visit(tree)
    }
}
