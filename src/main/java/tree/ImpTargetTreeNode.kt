package tree

class ImpTargetTreeNode(
    override val name: String,
    override val tag: String,
    override val id: Int,
    override var children: List<TargetTreeNode>?,
    override var parent: TargetTreeNode?
) : TargetTreeNode {

    override fun moveToRightSibling(): TargetTreeNode? {
        println("moveRight")
        if(parent != null)
            return parent!!.getRightSibling(this)
        else
            return null
    }

    override fun getRightSibling(son: TargetTreeNode): TargetTreeNode? {
        if(children != null){
            val index = children!!.binarySearch(son)
            if(index >=0 && index+1 < children!!.size )
                return children!![index+1]
        }
        return null
    }

    override fun moveToLeftSibling(): TargetTreeNode? {
        println("moveLeft")
        if(parent != null)
            return parent!!.getLeftSibling(this)
        else
            return null
    }

    override fun getLeftSibling(son: TargetTreeNode): TargetTreeNode? {
        if(children != null){
            val index = children!!.binarySearch(son)
            if(index > 0)
                return children!![index-1]
        }
        return null
    }

    override fun moveToParent(): TargetTreeNode? {
        println("moveUp")
        return parent
    }

    override fun moveToFirstChild(): TargetTreeNode? {
        println("moveDown")
        if(children != null){
            return children!!.first()
        }
        return null
    }

    override fun updateParent(newParent: TargetTreeNode?) {
        parent = newParent
        if(children != null){
            for (child in children!!){
                child.updateParent(this)
            }
        }
    }

    override fun preorder() {
        println(name + ":" + tag + "(" + id + ")")
        if(children != null){
            for (child in children!!){
                child.preorder()
            }
        }
    }

    override fun compareTo(other: TargetTreeNode): Int {
        return 0
    }
}
