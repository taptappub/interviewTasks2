package sprint5;

import java.io.IOException;

public class J {
    public static Node insert(Node root, int key) {
        if (key >= root.getValue()) {
            if (root.getRight() == null) {
                root.setRight(new Node(null, null, key));
            } else {
                insert(root.getRight(), key);
            }
        } else {
            if (root.getLeft() == null) {
                root.setLeft(new Node(null, null, key));
            } else {
                insert(root.getLeft(), key);
            }
        }

        return root;
    }


    private static class Node {
        private int value;
        private Node left;
        private Node right;

        Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        test();
    }

    private static void test() {
        Node node1 = new Node(null, null, 7);
        Node node2 = new Node(node1, null, 8);
        Node node3 = new Node(null, node2, 7);
        Node newHead = insert(node3, 6);
        boolean adv = newHead == node3;
        //newHead.getLeft().getValue() == 6;
    }
}
