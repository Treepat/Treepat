package expression

import expression.operators.VisitorFunction
import expression.operators.VisitorFunctionResponse
import expression.operators.createVisitorFunction
import grammars.antlr.treepat.TreepatVisitorImplementation
import grammars.ast.ASTNode
import java.nio.file.Path
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import target_tree.TargetTreeNode
import treepat.TreepatLexer
import treepat.TreepatParser

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
