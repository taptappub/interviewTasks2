package sprint5;

import java.io.IOException;

public class A {

    public static int treeSolution(Node head) {
        if (head.left == null && head.right == null) {
            return head.value;
        }
        int leftMax = head.value;
        if (head.left != null) {
            leftMax = treeSolution(head.left);
        }
        int rightMax = head.value;
        if (head.right != null) {
            rightMax = treeSolution(head.right);
        }
        return Math.max(Math.max(leftMax, rightMax), head.value);
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
        Node node4 = new Node(2);
        node4.left = node3;
        int i = treeSolution(node4);
        assert i == 3;
    }
}
