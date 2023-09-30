package sprint5;

import java.io.IOException;
import java.util.ArrayList;

public class E {
    public static boolean treeSolution(Node head) {
        ArrayList<Integer> array = new ArrayList<>();
        treeSolution(head, array);

        int current = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) <= current) {
                return false;
            }
            current = array.get(i);
        }
        return true;
    }

    public static void treeSolution(Node head, ArrayList<Integer> array) {
        if (head.left == null && head.right == null) {
            array.add(head.value);
            return;
        }
        if (head.left != null) {
            treeSolution(head.left, array);
        }
        array.add(head.value);
        if (head.right != null) {
            treeSolution(head.right, array);
        }
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

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        test();
    }

    private static void test() {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        boolean b = treeSolution(node5);
        node2.value = 5;
        boolean b1 = !treeSolution(node5);
    }
}