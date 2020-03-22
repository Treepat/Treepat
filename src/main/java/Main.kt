import antlr.tree_format.TreeFormatVisitorImplementation
import antlr.treepat.TreepatVisitorImplementation
import ast.ASTNode
import functions.createVisitorFunction
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JScrollPane
import org.antlr.v4.gui.TreeViewer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree
import tree.TargetTreeNode
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
        val ASTRoot: ASTNode = parseTreepat(args[0])

        // Tree File Parsing
        val targetTreeNode: TargetTreeNode = parseTreeFile(args[1])

        //
        val rootFunctionModule = createVisitorFunction(ASTRoot)
        val functionResult = rootFunctionModule.invoke(targetTreeNode)

        val solution: String
        if (functionResult.hasMatch) {
            solution = targetTreeNode.matchedNodesString(functionResult.matches)
        } else {
            solution = "Match not found"
        }
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
