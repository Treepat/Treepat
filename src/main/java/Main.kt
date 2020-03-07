import antlr.treeFormatParser.TreeFormatVisitorImplementation
import antlr.treeFormatParser.generated.TreeFormatLexer
import antlr.treeFormatParser.generated.TreeFormatParser
import antlr.treepatParser.TreepatVisitorImplementation
import antlr.treepatParser.generated.TreepatLexer
import antlr.treepatParser.generated.TreepatParser
import functions.createVisitorFunction
import javax.swing.JFrame
import javax.swing.JPanel
import org.antlr.v4.gui.TreeViewer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree
import tree.TargetTreeNode

object Main {

    private const val jFrameTitle = "Antlr AST"

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        // TREE FORMAT PARSER TEST

        val tLexer = TreeFormatLexer(CharStreams.fromFileName(args[1]))
        val tTokenStream = CommonTokenStream(tLexer)
        val tParser = TreeFormatParser(tTokenStream)
        val tTree: ParseTree = tParser.subtree()

        val tVisitor = TreeFormatVisitorImplementation()
        val targetTreeNode: TargetTreeNode = tVisitor.visit(tTree).first()
        targetTreeNode.updateParent(null)

        targetTreeNode.preorder()

        showASTNodeFrame(tParser, tTree)

        // TEST END ---------------------------

        val treepatLexer = TreepatLexer(CharStreams.fromFileName(args.first()))
        val treepatTokenStream = CommonTokenStream(treepatLexer)
        val treepatParser = TreepatParser(treepatTokenStream)
        val treepatTree: ParseTree = treepatParser.subtree()

        val treepatVisitor = TreepatVisitorImplementation()
        val root = treepatVisitor.visit(treepatTree)
        // val targetTreeNode: TargetTreeNode = ImpTargetTreeNode()
        val rootFunctionModule = createVisitorFunction(root)

        rootFunctionModule.invoke(targetTreeNode)

        showASTNodeFrame(treepatParser, treepatTree)
    }

    private fun showASTNodeFrame(parser: Parser, tree: ParseTree) {
        val frame = JFrame(jFrameTitle)
        val panel = JPanel()
        val viewer = TreeViewer(listOf(*parser.ruleNames), tree)
        viewer.scale = 1.0
        panel.add(viewer)
        frame.add(panel)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.pack()
        frame.isVisible = true
    }
}
