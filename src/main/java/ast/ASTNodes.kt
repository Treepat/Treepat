package ast

interface ASTNode

const val INDENT_STRING = "    "
const val END_LINE_STRING = "\n"

data class Check(var expression: ASTNode) : ASTNode {
    override fun toString(): String = "$expression"
}

data class Child(var father: ASTNode, var child: ASTNode) : ASTNode {
    override fun toString(): String = "$father\n${child.toString().prependIndent(INDENT_STRING)}"
}

data class Node(var name: String) : ASTNode {
    override fun toString(): String = name
}

data class Sibling(var firstSiblings: ASTNode, var secondSibling: ASTNode) : ASTNode {
    override fun toString(): String = "$firstSiblings$END_LINE_STRING$secondSibling"
}

data class BreadthClosure(var expression: ASTNode) : ASTNode {
    override fun toString(): String = "$expression*"
}

data class Union(val expressions: List<ASTNode>) : ASTNode {
    override fun toString(): String = expressions.joinToString(separator = "$END_LINE_STRING|$END_LINE_STRING")
}
