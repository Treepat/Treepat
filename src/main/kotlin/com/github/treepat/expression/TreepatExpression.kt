package com.github.treepat.expression

import antlr.treepat.TreepatLexer
import antlr.treepat.TreepatParser
import com.github.treepat.expression.operators.VisitorFunction
import com.github.treepat.expression.operators.VisitorFunctionResponse
import com.github.treepat.expression.operators.createVisitorFunction
import com.github.treepat.grammars.antlr.treepat.TreepatVisitorImplementation
import com.github.treepat.grammars.ast.ASTNode
import com.github.treepat.target_tree.TargetTreeNode
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree

class TreepatExpression(val expression: VisitorFunction) {

    companion object Factory {

        fun createFromFile(treepatFilePath: String): TreepatExpression {
            val lexer = TreepatLexer(CharStreams.fromFileName(treepatFilePath))
            val tokenStream = CommonTokenStream(lexer)
            val treepatParser = TreepatParser(tokenStream)
            val tree: ParseTree = treepatParser.treepat()
            val treepatVisitor = TreepatVisitorImplementation()
            val astRoot: ASTNode = treepatVisitor.visit(tree)
            return TreepatExpression(createVisitorFunction(astRoot))
        }
    }

    fun executeExpression(targetTreeNode: TargetTreeNode?): VisitorFunctionResponse = expression.invoke(targetTreeNode)
}
