package tree

class TargetTreeNodeComparator: Comparator<TargetTreeNode> {
    override fun compare(o1: TargetTreeNode?, o2: TargetTreeNode?): Int {
        if(o1 == null || o2 == null)
            return 0
        return o1!!.id.compareTo(o2!!.id)
    }
}