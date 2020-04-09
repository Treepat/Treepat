import grammars.antlr.tree_format.TreeFormatVisitorImplementation
import grammars.antlr.treepat.TreepatVisitorImplementation
import grammars.ast.ASTNode
import expression.TreepatExpression
import org.antlr.v4.gui.TreeViewer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree
import target_tree.DefaultTargetTree
import target_tree.ImpTargetTreeNode
import target_tree.TargetTreeNode
import tree_format.TreeFormatLexer
import tree_format.TreeFormatParser
import treepat.TreepatLexer
import treepat.TreepatParser
import java.nio.file.Paths
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JScrollPane

object Main {

    private const val jFrameTitle = "Antlr AST"

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        // Treepat Parsing
        val treepatExpression = TreepatExpression.createFromFile(Paths.get(args[0]))
        // Tree File Parsing
        val targetTree = DefaultTargetTree<ImpTargetTreeNode>(Paths.get(args[1]))

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
