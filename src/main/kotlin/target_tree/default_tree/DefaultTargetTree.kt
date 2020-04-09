package target_tree.default_tree

import grammars.antlr.tree_format.TreeFormatVisitorImplementation
import java.nio.file.Path
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import target_tree.TargetTree
import target_tree.TargetTreeNode
import tree_format.TreeFormatLexer
import tree_format.TreeFormatParser

class DefaultTargetTree<T : TargetTreeNode>(override var root: T? = null) :
    TargetTree<T> {

    /**
     * Read tree from grammar.antlr.
     */
    constructor(tree_file: Path) : this() {
        val lexer = TreeFormatLexer(CharStreams.fromPath(tree_file))
        val tokenStream = CommonTokenStream(lexer)
        val fileParser = TreeFormatParser(tokenStream)
        val tree: ParseTree = fileParser.subtree()
        val treeVisitor = TreeFormatVisitorImplementation()
        @Suppress("UNCHECKED_CAST")
        root = treeVisitor.visit(tree) as? T
    }
}
