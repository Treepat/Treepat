package expression.operators

import grammars.ast.ASTNode
import grammars.ast.BreadthClosure
import grammars.ast.Child
import grammars.ast.DepthClosure
import grammars.ast.DepthTerm
import grammars.ast.Dot
import grammars.ast.Node
import grammars.ast.Sibling
import grammars.ast.Treepat
import grammars.ast.Union
import target_tree.TargetTreeNode

typealias VisitorFunction = (TargetTreeNode?) -> VisitorFunctionResponse

fun createVisitorFunction(node: ASTNode): VisitorFunction = when (node) {
    is Treepat -> treepatFunction(createVisitorFunction(node.subtree))
    is Child -> childFunction(createVisitorFunction(node.father), createVisitorFunction(node.child))
    is Node -> nodeFunction(node.name)
    is Sibling -> siblingFunction(createVisitorFunction(node.firstSiblings), createVisitorFunction(node.secondSibling))
    is BreadthClosure -> breadthClosureFunction(createVisitorFunction(node.expression))
    is Union -> unionFunction(node.expressions.map(::createVisitorFunction))
    is Dot -> dotFunction()
    is DepthTerm -> depthTermFunction(createVisitorFunction(node.node))
    is DepthClosure -> depthClosureFunction(createVisitorFunction(node.expression))
    else -> throw IllegalArgumentException("This ASTNode subtype is not supported.")
}
