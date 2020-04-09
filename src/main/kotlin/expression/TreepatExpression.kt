package expression

import grammars.antlr.treepat.TreepatVisitorImplementation
import grammars.ast.ASTNode
import operators.VisitorFunction
import operators.VisitorFunctionResponse
import operators.createVisitorFunction
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import target_tree.TargetTreeNode
import treepat.TreepatLexer
import treepat.TreepatParser
import java.nio.file.Path

class TreepatExpression(val expression: VisitorFunction) {

    companion object Factory {

        fun createFromFile(treepatFilePath: Path): TreepatExpression {
            val lexer = TreepatLexer(CharStreams.fromPath(treepatFilePath))
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

