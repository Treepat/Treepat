package ast

interface ASTNode

data class Child(var father: ASTNode, var child: ASTNode) : ASTNode
data class Node(var name: String) : ASTNode
data class Sibling(var siblings: List<ASTNode>) : ASTNode
