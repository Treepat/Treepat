import antlr.generate.TreepatLexer;
import antlr.generate.TreepatParser;
import antlr.TreepatVisitorImplementation;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(CharStreams.fromFileName("test.tp"));
        TreepatLexer lexer = new TreepatLexer(CharStreams.fromFileName("test.tp"));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        TreepatParser parser = new TreepatParser(tokenStream);
        ParseTree tree = parser.model();
        TreepatVisitorImplementation visitor = new TreepatVisitorImplementation();
        visitor.visit(tree);
        //System.out.println(tree.toStringTree());
        //System.out.println(tokenStream.getTokens().size());
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


        JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(
                parser.getRuleNames()),tree);
        viewer.setScale(1); // Scale a little
        panel.add(viewer);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}