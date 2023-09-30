//package sprint5;

public class C {
    public static boolean treeSolution(Node head) {
        return treeSolution(head.left, head.right);
    }

    private static boolean treeSolution(Node left, Node right) {
        if (left != null && right == null) {
            return false;
        }
        if (left == null && right != null) {
            return false;
        }
        if (left == null && right == null) {
            return true;
        }
        if (left.value != right.value) {
            return false;
        }

        boolean res1 = treeSolution(left.left, right.right);
        if (!res1) {
            return false;
        }
        boolean res2 = treeSolution(left.right, right.left);
        return res2;
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

    private static void test() {
        Node node1 = new Node(3, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(4, null, null);
        Node node4 = new Node(3, null, null);
        Node node5 = new Node(2, node1, node2);
        Node node6 = new Node(2, node3, node4);
        Node node7 = new Node(1, node5, node6);
        treeSolution(node7);
        int asd = 2;
    }
}
