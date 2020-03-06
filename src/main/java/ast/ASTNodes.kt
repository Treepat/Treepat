package ast

interface ASTNode

data class Child(var father: ASTNode, var child: ASTNode) : ASTNode {
    override fun toString(): String = "$father($child)"
}
data class Node(var name: String) : ASTNode {
    override fun toString(): String = name
}
data class Sibling(var siblings: List<ASTNode>) : ASTNode {
    override fun toString(): String = siblings.joinToString(separator = " ")
}
