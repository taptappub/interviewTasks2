package sprint2;

public class E {

    public static Node<String> solution(Node<String> head) {
        Node<String> previos = null;
        Node<String> current = head;

        while (current != null) {
            Node<String> next = current.next;
            current.next = previos;
            current.prev = current.next;

            previos = current;
            current = next;
        }

        return previos;
    }

    public static void main(String[] args) {

        Node<String> node3 = new Node<>("node3", null, null);
        Node<String> node2 = new Node<>("node2", node3, null);
        Node<String> node1 = new Node<>("node1", node2, null);
        Node<String> node0 = new Node<>("node0", node1, null);
        node1.prev = node0;
        node2.prev = node1;
        node3.prev = node2;
        Node<String> newNode = solution(node0);

        System.out.println(newNode.toString());

        System.out.println("");
    }

    public static class Node<V> {
        public V value;
        public Node<V> next;
        public Node<V> prev;

        public Node(V value, Node<V> next, Node<V> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    ", prev=" + prev +
                    '}';
        }
    }
}
