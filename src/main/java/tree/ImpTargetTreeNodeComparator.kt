package tree

class ImpTargetTreeNodeComparator : Comparator<ImpTargetTreeNode> {
    override fun compare(node1: ImpTargetTreeNode?, node2: ImpTargetTreeNode?): Int {
        if (node1 == null || node2 == null)
            return 0
        return node1.id.compareTo(node2.id)
    }
}
