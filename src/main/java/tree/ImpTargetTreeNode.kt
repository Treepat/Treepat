package tree

class ImpTargetTreeNode() : TargetTreeNode {

    private var name: String = ""
    private var tag: String = ""
    private var id: Int = -1
    private var children: List<TargetTreeNode>? = null
    private var parent: TargetTreeNode? = null

    constructor(name: String, tag: String, id: Int) : this() {
        this.name = name
        this.tag = tag
        this.id = id
    }

    override fun moveToRightSibling(): TargetTreeNode? {
        if(parent != null)
            return (parent!! as ImpTargetTreeNode).getRightSibling(this)
        return null
    }

    override fun moveToLeftSibling(): TargetTreeNode? {
        if(parent != null)
            return (parent!! as ImpTargetTreeNode).getLeftSibling(this)
        return null
    }

    fun getRightSibling(son: TargetTreeNode): TargetTreeNode? {
        if(children != null){
            val index = (children!! as List<ImpTargetTreeNode>).binarySearch(son as ImpTargetTreeNode, ImpTargetTreeNodeComparator())
            if(index >=0 && index+1 < children!!.size )
                return children!![index+1]
        }
        return null
    }

    fun getLeftSibling(son: TargetTreeNode): TargetTreeNode? {
        if(children != null){
            val index = (children!! as List<ImpTargetTreeNode>).binarySearch(son as ImpTargetTreeNode, ImpTargetTreeNodeComparator())
            if(index > 0)
                return children!![index-1]
        }
        return null
    }

    override fun moveToParent(): TargetTreeNode? {
        return parent
    }

    override fun moveToFirstChild(): TargetTreeNode? {
        if(children != null){
            return children!!.first()
        }
        return null
    }

    override fun getName(): String {
        return name
    }

    override fun getTag(): String {
        return tag
    }

    override fun getId(): Int {
        return id
    }

    override fun setChildren(children: List<TargetTreeNode>) {
        this.children = children
    }

    override fun getChildren(): List<TargetTreeNode>? {
        return children
    }

    override fun toString(): String {
        var str = "$name:$tag($id)"
        if(children != null){
            for (child in children!!){
                str += "(" + (child as ImpTargetTreeNode).toString() + ")"
            }
        }
        return str
    }

    fun setParent(newParent: TargetTreeNode?) {
        parent = newParent
    }

    fun preorder() {
        println("$name:$tag($id)")
        if(children != null){
            for (child in children!!){
                (child as ImpTargetTreeNode).preorder()
            }
        }
    }
}
