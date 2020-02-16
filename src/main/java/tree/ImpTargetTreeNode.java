package tree;

public class ImpTargetTreeNode implements TargetTreeNode {
    @Override
    public TargetTreeNode moveRight() {
        System.out.println("moveRight");
        return this;
    }

    @Override
    public TargetTreeNode moveLeft() {
        System.out.println("moveLeft");
        return this;
    }

    @Override
    public TargetTreeNode moveUp() {
        System.out.println("moveUp");
        return this;
    }

    @Override
    public TargetTreeNode moveDown() {
        System.out.println("moveDown");
        return this;
    }

    @Override
    public String getName() {
        return "name";
    }

    @Override
    public int compareTo(TargetTreeNode o) {
        return 0;
    }
}
