package tree

class ImpTargetTreeNode : TargetTreeNode {
    override fun moveRight(): TargetTreeNode {
        println("moveRight")
        return this
    }

    override fun moveLeft(): TargetTreeNode {
        println("moveLeft")
        return this
    }

    override fun moveUp(): TargetTreeNode {
        println("moveUp")
        return this
    }

    override fun moveDown(): TargetTreeNode {
        println("moveDown")
        return this
    }

    override val name: String
        get() = "name"

    override fun compareTo(other: TargetTreeNode?): Int {
        return 0
    }
}