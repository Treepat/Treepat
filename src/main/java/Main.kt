import antlr.TreepatVisitorImplementation
import antlr.generate.TreepatLexer
import antlr.generate.TreepatParser
import functions.createVisitorFunction
import org.antlr.v4.gui.TreeViewer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import tree.ImpTargetTreeNode
import tree.TargetTreeNode
import javax.swing.JFrame
import javax.swing.JPanel

object Main {

    private const val jFrameTitle = "Antlr AST"

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val lexer = TreepatLexer(CharStreams.fromFileName(args.first()))
        val tokenStream = CommonTokenStream(lexer)
        val parser = TreepatParser(tokenStream)
        val tree: ParseTree = parser.subtree()

        val visitor = TreepatVisitorImplementation()
        val root = visitor.visit(tree)
        val targetTreeNode: TargetTreeNode = ImpTargetTreeNode()
        val rootFunctionModule = createVisitorFunction(root)

        rootFunctionModule.invoke(targetTreeNode)

        showASTNodeFrame(parser, tree)
    }

    private fun showASTNodeFrame(parser: TreepatParser, tree: ParseTree) {
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
