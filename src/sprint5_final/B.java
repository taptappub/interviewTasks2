package sprint5_final;

/*
-- ПРИНЦИП РАБОТЫ --
Алгоритм удаления узла дерева делится на 2 состояния: найден узел или не найден.
Если узел не найден, то т.к. входные данные уникальные и отсортированные, мы с уверенностью можем сказать в какой ветке
надо продолжить поиск.
Если узел найден, то у него, в свою очередь, может быть 4 состояния:
 - у него нет дочерних узлов -> мы просто его удаляем;
 - у него есть левый дочерний узел -> мы присоединяем его к родительскому узлу;
 - у него есть правый дочерний узел -> мы присоединяем его к родительскому узлу;
 - у него есть и левый и правый дочерние узлы -> тогда мы ищем подходящий узел на замену и меняем его с удаляемым.
Подходящий узел на замену, в моем алгоритме, это узел с минимальным значением в ветке от правого дочернего узла удаляемого узла.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Алгоритм удаления состоит из 3 алгоритмов:
 - двоичный поиск
 - поиск самого глубокого левого узла
 - удаление узла

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность:
 - двоичный поиск - О(N), N - количество элементов? т.к. дерево не сбалансировано
 - поиск самого глубокого левого узла - О(K) - где K количество левых узлов, но K < logN
 - удаление узла - О(1)
Следовательно сложность алгоритма О(logN)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм работает на рекурсии, а значит использует О(H) дополнительной памяти, где H - высота дерева
*/
//https://contest.yandex.ru/contest/24810/run-report/85038289/
import java.io.IOException;

public class B {

    // O(logN)
    public static Node remove(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (node.getValue() == key) {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getLeft() != null && node.getRight() == null) {
                return node.getLeft();
            } else if (node.getLeft() == null && node.getRight() != null) {
                return node.getRight();
            } else {
                Node minimumRightNode = findMinimumRightNode(node.getRight());

                Node newTree = remove(node.getRight(), minimumRightNode.getValue());
                minimumRightNode.setLeft(node.getLeft());
                minimumRightNode.setRight(newTree);
                return minimumRightNode;
            }
        } else {
            if (key < node.getValue()) {
                node.setLeft(remove(node.getLeft(), key));
            } else {
                node.setRight(remove(node.getRight(), key));
            }
            return node;
        }
    }

    //O(logN)
    private static Node findMinimumRightNode(Node node) {
        if (node.getLeft() != null) {
            return findMinimumRightNode(node.getLeft());
        }
        return node;
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
//        1 5 2 5
//        2 1 -1 3
//        3 3 4 -1
//        4 2 -1 -1
//        5 10 6 -1
//        6 8 7 -1
//        7 6 -1 -1
//        10
        Node node7 = new Node(null, null, 6);
        Node node6 = new Node(node7, null, 8);
        Node node5 = new Node(node6, null, 10);
        Node node4 = new Node(null, null, 2);
        Node node3 = new Node(node4, null, 3);
        Node node2 = new Node(null, node3, 1);
        Node node1 = new Node(node2, node5, 5);
        Node newHead = remove(node1, 10);
        boolean sdf = newHead.getValue() == 5;
    }
}
