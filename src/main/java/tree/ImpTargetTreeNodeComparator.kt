package tree

class ImpTargetTreeNodeComparator : Comparator<ImpTargetTreeNode> {
    override fun compare(o1: ImpTargetTreeNode?, o2: ImpTargetTreeNode?): Int {
        if (o1 == null || o2 == null)
            return 0
        return o1!!.getId().compareTo(o2!!.getId())
    }
}
