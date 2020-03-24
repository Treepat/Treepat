package operators

import ast.ASTNode
import ast.BreadthClosure
import ast.Check
import ast.Child
import ast.Dot
import ast.Node
import ast.Sibling
import ast.Treepat
import ast.Union
import tree.TargetTreeNode

typealias VisitorFunction = (TargetTreeNode?) -> VisitorFunctionResponse

fun createVisitorFunction(node: ASTNode): VisitorFunction = when (node) {
    is Treepat -> treepatFunction(createVisitorFunction(node.subtree))
    is Child -> childFunction(createVisitorFunction(node.father), createVisitorFunction(node.child))
    is Node -> nodeFunction(node.name)
    is Sibling -> siblingFunction(createVisitorFunction(node.firstSiblings), createVisitorFunction(node.secondSibling))
    is BreadthClosure -> breadthClosureFunction(createVisitorFunction(node.expression))
    is Union -> unionFunction(node.expressions.map(::createVisitorFunction))
    is Check -> checkFunction(createVisitorFunction(node.expression))
    is Dot -> dotFunction()
    else -> throw IllegalArgumentException("This ASTNode subtype is not supported.")
}
