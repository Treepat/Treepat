package ast

import java.util.ArrayList
import tree.TargetTreeNode

class Child(var father: ASTNode, var child: ASTNode) : ASTNode {
    // / improve all of this shit
    override fun execute(targetTreeNode: TargetTreeNode): Any? {
        val fatherList = (father.execute(targetTreeNode) as List<*>).toMutableList()
        if (fatherList.isEmpty()) {
            return ArrayList<Any>()
        }
        val childList = child.execute(targetTreeNode.moveDown()) as List<*>
        if (childList.isEmpty()) {
            return ArrayList<Any>()
        }
        fatherList.addAll(childList)
        targetTreeNode.moveUp()
        return fatherList
    }
}
