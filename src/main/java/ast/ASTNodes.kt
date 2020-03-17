package ast

interface ASTNode

const val INDENT_STRING = "    "
const val END_LINE_STRING = "\n"

data class Child(var father: ASTNode, var child: ASTNode) : ASTNode {
    override fun toString(): String = "$father\n${child.toString().prependIndent(INDENT_STRING)}"
}
data class Node(var name: String) : ASTNode {
    override fun toString(): String = name
}
data class Sibling(var siblings: List<ASTNode>) : ASTNode {
    override fun toString(): String = siblings.joinToString(separator = END_LINE_STRING)
}
data class BreadthClosure(var expression: ASTNode) : ASTNode {
    override fun toString(): String = "$expression*"
}
