package sprint2;

import java.io.*;

public class B {

    public static void solution(Node<String> head) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            Node<String> current = head;
            while (current != null) {
                writer.write(current.value);
                writer.newLine();
                current = current.next;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }

    private static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        solution(node0);
        /*
        Output is:
        node0
        node1
        node2
        node3
        */
    }
}
