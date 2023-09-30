package sprint2;

import java.io.IOException;

public class D {

    public static int solution(Node<String> head, String elem) {
        Node<String> current = head;
        int idx = 0;
        while (current != null) {
            if (current.value.equals(elem)) {
                return idx;
            }
            current = current.next;
            idx++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {

        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        System.out.println(solution(node0, "node2"));
    }

    static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }
}
