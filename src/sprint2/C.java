package sprint2;

import java.io.IOException;

public class C {

    public static Node<String> solution(Node<String> head, int idx) {
        if (idx == 0) {
            return head.next;
        }

        Node<String> prev = head;
        Node<String> current = head;

        int counter = 0;
        while (counter < idx) {
            prev = current;
            current = current.next;
            counter++;
        }

        prev.next = current.next;
        return head;
    }

    public static void main(String[] args) throws IOException {

        Node<String> node6 = new Node<>("node6", null);
        Node<String> node5 = new Node<>("node5", node6);
        Node<String> node4 = new Node<>("node4", node5);
        Node<String> node3 = new Node<>("node3", node4);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        System.out.println(solution(node0, 4));
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