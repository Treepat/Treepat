package ast

fun <T> List<T>.tail() = subList(1, size)

/**
 * Creates recursively a tree with the list of [siblings].
 * @return A the root the [Sibling]s tree.
 */
fun createSiblingNodes(siblings: List<ASTNode>): ASTNode {
    if (siblings.size < 2) {
        throw IllegalArgumentException("There must be at least 2 siblings.")
    }
    if (siblings.size == 2) {
        return Sibling(siblings[0], siblings[1])
    }
    val secondParam = createSiblingNodes(siblings.tail())
    return Sibling(siblings.first(), secondParam)
}
