package operators

import ast.ASTNode
import ast.BreadthClosure
import ast.Child
import ast.DepthClosure
import ast.DepthTerm
import ast.Dot
import ast.Node
import ast.Sibling
import ast.Treepat
import ast.Union
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
