package sprint5;

public class D {

    public static boolean treeSolution(Node left, Node right) {
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

        boolean res1 = treeSolution(left.left, right.left);
        if (!res1) {
            return false;
        }
        boolean res2 = treeSolution(left.right, right.right);
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
}
