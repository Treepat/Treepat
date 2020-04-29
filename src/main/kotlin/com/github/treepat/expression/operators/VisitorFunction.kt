package com.github.treepat.expression.operators

import com.github.treepat.grammars.ast.ASTNode
import com.github.treepat.grammars.ast.BreadthClosure
import com.github.treepat.grammars.ast.Child
import com.github.treepat.grammars.ast.DepthClosure
import com.github.treepat.grammars.ast.DepthTerm
import com.github.treepat.grammars.ast.Dot
import com.github.treepat.grammars.ast.Node
import com.github.treepat.grammars.ast.Sibling
import com.github.treepat.grammars.ast.Treepat
import com.github.treepat.grammars.ast.Union
import com.github.treepat.target_tree.TargetTreeNode

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
