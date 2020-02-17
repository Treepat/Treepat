package ast

import tree.TargetTreeNode

interface ASTNode {
    fun execute(targetTreeNode: TargetTreeNode): Any?
}