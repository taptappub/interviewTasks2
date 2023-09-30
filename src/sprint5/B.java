package sprint5;

import java.io.IOException;

public class B {

    public static boolean treeSolution(Node head) {
        if (head == null) {
            return true;
        }

        int leftHeight = countHeight(head.left);
        int rightHeight = countHeight(head.right);

        return Math.abs(rightHeight - leftHeight) < 2 && treeSolution(head.left) && treeSolution(head.right);
    }

    private static int countHeight(Node head) {
        if (head == null) {
            return 0;
        }

        return Math.max(countHeight(head.left), countHeight(head.right)) + 1;
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) throws IOException {
        test();
    }

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        treeSolution(node5);
    }
}