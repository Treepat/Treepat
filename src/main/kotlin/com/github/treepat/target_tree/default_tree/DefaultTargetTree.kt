package com.github.treepat.target_tree.default_tree

import antlr.tree_format.TreeFormatLexer
import antlr.tree_format.TreeFormatParser
import com.github.treepat.grammars.antlr.tree_format.TreeFormatVisitorImplementation
import com.github.treepat.target_tree.TargetTree
import com.github.treepat.target_tree.TargetTreeNode
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree

class DefaultTargetTree(override var root: TargetTreeNode? = null) : TargetTree {

    companion object Factory {

        fun createFromFile(treeFilePath: String): DefaultTargetTree {
            val lexer = TreeFormatLexer(CharStreams.fromFileName(treeFilePath))
            val tokenStream = CommonTokenStream(lexer)
            val fileParser = TreeFormatParser(tokenStream)
            val tree: ParseTree = fileParser.subtree()
            val treeVisitor = TreeFormatVisitorImplementation()
            return DefaultTargetTree(treeVisitor.visit(tree))
        }

        fun createFromString(treeString: String): DefaultTargetTree {
            val lexer = TreeFormatLexer(CharStreams.fromString(treeString))
            val tokenStream = CommonTokenStream(lexer)
            val fileParser = TreeFormatParser(tokenStream)
            val tree: ParseTree = fileParser.subtree()
            val treeVisitor = TreeFormatVisitorImplementation()
            return DefaultTargetTree(treeVisitor.visit(tree))
        }
    }
}
