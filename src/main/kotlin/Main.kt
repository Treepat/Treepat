
import com.github.treepat.expression.TreepatExpression
import com.github.treepat.match_manager.MatchManager
import com.github.treepat.target_tree.default_tree.DefaultTargetTree
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JScrollPane
import org.antlr.v4.gui.TreeViewer
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree

object Main {

    private const val jFrameTitle = "Antlr AST"

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        // Treepat Parsing
        val treepatExpression = TreepatExpression.createFromFile(args[0])
        // Tree File Parsing
        val targetTree = DefaultTargetTree.createFromFile(args[1])
        // Match Manager
        val manager = MatchManager(treepatExpression, targetTree)
        var match = manager.getNextMatch()
        if (match == null) {
            print("Match not found")
        } else {
            print(manager.matchToString(match))
            match = manager.getNextMatch()
            while (match != null) {
                print("\n-\n" + manager.matchToString(match))
                match = manager.getNextMatch()
            }
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
}
