package ast

interface ASTNode

const val INDENT_STRING = "    "
const val END_LINE_STRING = "\n"
const val DOT = "."

data class Treepat(val subtree: ASTNode) : ASTNode {
    override fun toString(): String = "$subtree"
}

data class Check(val expression: ASTNode) : ASTNode {
    override fun toString(): String = "$expression"
}

data class Child(val father: ASTNode, val child: ASTNode) : ASTNode {
    override fun toString(): String = "$father\n${child.toString().prependIndent(INDENT_STRING)}"
}

data class Node(val name: String) : ASTNode {
    override fun toString(): String = name
}

data class Sibling(val firstSiblings: ASTNode, val secondSibling: ASTNode) : ASTNode {
    override fun toString(): String = "$firstSiblings$END_LINE_STRING$secondSibling"
}

data class BreadthClosure(val expression: ASTNode) : ASTNode {
    override fun toString(): String = "$expression*"
}

data class Union(val expressions: List<ASTNode>) : ASTNode {
    override fun toString(): String = expressions.joinToString(separator = "$END_LINE_STRING|$END_LINE_STRING")
}

class Dot : ASTNode {
    override fun toString(): String = DOT
}

data class DepthClosure(val expression: ASTNode) : ASTNode {
    override fun toString(): String =
        "($END_LINE_STRING${expression.toString().prependIndent(INDENT_STRING)}$END_LINE_STRING)#"
}

data class DepthTerm(val node: ASTNode) : ASTNode {
    override fun toString(): String = "@$node"
}
