package Practices.task4.self;

public class RBMain {

    public static void main(String[] args) {

        RBTree<Integer> rbTree = new RBTree<>();
        rbTree.add(1);
        rbTree.add(3);
        rbTree.add(5);
        rbTree.add(6);
        rbTree.add(8);
        System.out.println(rbTree.size());
        rbTree.remove(1);
        printList(rbTree);
        //TODO: rbTree's methods are shit
    }

    public static void printList(RBTree<Integer> rbTree) {
        for (int i = 0; i < rbTree.size(); i++) System.out.print(rbTree.get(i) + " ");
        System.out.println();
    }
}
