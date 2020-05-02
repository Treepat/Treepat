package com.github.treepat.target_tree.default_tree

import antlr.tree_format.TreeFormatLexer
import antlr.tree_format.TreeFormatParser
import com.github.treepat.grammars.antlr.tree_format.TreeFormatVisitorImplementation
import com.github.treepat.target_tree.TargetTree
import com.github.treepat.target_tree.TargetTreeNode
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree

class DefaultTargetTree<T : TargetTreeNode>(override var root: T? = null) :
    TargetTree<T> {

    /**
     * Read tree from grammar.antlr.
     */
    constructor(tree_file: String) : this() {
        val lexer = TreeFormatLexer(CharStreams.fromFileName(tree_file))
        val tokenStream = CommonTokenStream(lexer)
        val fileParser = TreeFormatParser(tokenStream)
        val tree: ParseTree = fileParser.subtree()
        val treeVisitor = TreeFormatVisitorImplementation()
        @Suppress("UNCHECKED_CAST")
        root = treeVisitor.visit(tree) as? T
    }
}
