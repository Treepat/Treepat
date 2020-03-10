package tree

class ImpTargetTreeNodeComparator : Comparator<ImpTargetTreeNode> {
    override fun compare(node1: ImpTargetTreeNode?, node2: ImpTargetTreeNode?): Int {
        if (o1 == null || o2 == null)
            return 0
        return o1!!.getId().compareTo(o2!!.getId())
    }
}
