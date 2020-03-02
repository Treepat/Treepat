import antlr.TreepatVisitorImplementation
import antlr.generate.TreepatLexer
import antlr.generate.TreepatParser
import functions.createVisitorFunction
import java.util.Arrays
import javax.swing.JFrame
import javax.swing.JPanel
import org.antlr.v4.gui.TreeViewer
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import tree.ImpTargetTreeNode
import tree.TargetTreeNode

object Main {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        println(CharStreams.fromFileName("test.tp"))
        val lexer = TreepatLexer(CharStreams.fromFileName("test.tp"))
        val tokenStream = CommonTokenStream(lexer)
        val parser = TreepatParser(tokenStream)
        val tree: ParseTree = parser.model()
        val visitor = TreepatVisitorImplementation()
        val root = visitor.visit(tree)
        val targetTreeNode: TargetTreeNode = ImpTargetTreeNode()
        val rootFunctionModule = createVisitorFunction(root)
        rootFunctionModule.invoke(targetTreeNode)
        // System.out.println(tree.toStringTree());
        // System.out.println(tokenStream.getTokens().size());
        /*
        for(Token t : tokenStream.getTokens())
        {
            System.out.println(t);
            //System.out.println(t.getLine());
            //System.out.println(t.getText().getBytes().length);
            if( t.getType() > 0 )
                System.out.println(TreepatParser.tokenNames[t.getType()]);
        }
         */
        val frame = JFrame("Antlr AST")
        val panel = JPanel()
        val viewer = TreeViewer(
            Arrays.asList(
                *parser.ruleNames), tree)
        viewer.scale = 1.0 // Scale a little
        panel.add(viewer)
        frame.add(panel)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.pack()
        frame.isVisible = true
    }
}
