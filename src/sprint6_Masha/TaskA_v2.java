package sprint6_Masha;


import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;


/**
 * Id отправки в Контест: https://contest.yandex.ru/contest/25070/run-report/85687711/
 * <p>
 * Принцип работы и алгоритм:
 * Реализован алгоритм Прима:
 * 1) Первым шагом обрабатывается одна любая вершина. Т.к основ должен включать все равно все вершины, без разницы с какой начинать.
 * 2)Обработка вершины:
 * Алгоритм берет все исходящие ребра из данной вершины, и добавляет их в кучу,
 * кроме тех, чьи конечные вершины уже включены в остов. Сама вершина добавляется в остов (пометка в added индекса значением true).
 * 3)далее в цикле, пока есть необработанные вершины и куча не пуста:
 * 3.1)из кучи удаляется наибольшее ребро
 * 3.2)если конец этого ребра еще не в остове -> это вершина обрабатывается (см п.2), т.е по сути добавляется в остов.
 * 3.3)при каждом добавлении вершины (и по сути ребра) - добавляем вес этого ребра к считаемой сумме.p
 * 4)если после работы цикла остались необработанные вершины, значит основ невозможно построить со всеми вершинами.
 * Если нет - итоговая сумма и есть искомый результат.
 * <p>
 * Обоснование:
 * Сам алгоритм обеспечивает включение всех вершин и выбор наибольшего ребра на каждом шаге.
 * Сумма наибольших ребер, соединяющие все вершины, как раз то, что нужно найти, чтобы решить задачу.
 * <p>
 * Пространственная сложность:
 * Мы используем дополнительные структуры данные для хранения вершин и ребер, т.е
 * cложность O (N+M), где  N - количество всех вершин, а M - количество всех ребер.
 * Также коллекция added уже проверяет, включена ли вершина в результат.
 * <p>
 * Временная сложность:
 * Алгоритм обрабатывает каждое ребро - O (M), где M - количество ребер.
 * Но для каждой итерации нужно выбрать ребро с наибольшим весом.
 * Т.к для их хранения используется приоритезированная очередь -> для java это означает, что в ее основе лежит
 * приоритезированная бинарная куча -> т.е сложность извлечения максимального элемента O(1)
 * + сложность восстановления кучи после извлечения элемента O (log (M)),
 * где M - количество элементов в куче (т.е в худшем случае все ребра).
 * Т.к граф неориентированный и размер кучи уменьшается на каждом шаге в итоге получается:
 * сложность: O (M * log (N)), где  N - количество всех вершин, а M - количество всех ребер.
 */
public class TaskA_v2 {


    private static void addVertex(int[][] matrix, Queue<Edge> edges, boolean[] added, int v) {

        added[v] = true;

        // добавляем все исходящие ребра в edges, кроме тех, где вершина конца еще не в added
        int[] raw = matrix[v];
        for (int i = 0; i < raw.length; i++) {
            if (!added[i] && raw[i] != 0) {
                Edge newEdge = new Edge(v, i, raw[i]);
                edges.add(newEdge);
            }
        }
    }

    private static Integer maxSpanningTree(int[][] matrix, int edgeCount, Queue<Edge> edges, boolean[] added, int addedCount, int v) {

        int sum = 0;
        addVertex(matrix, edges, added, v);
        addedCount++;

        while (addedCount < edgeCount && !edges.isEmpty()) {

            Edge e = edges.peek();  //выбираем ребро с наибольшим весом из edges
            edges.remove(e);

            //если вершина конца не в added -> учитываем weight ребра, сохраняем эту вершину
            if (!added[e.to]) {
                sum += e.weight;
                addVertex(matrix, edges, added, e.to);
                addedCount++;
            }
        }

        if (addedCount != matrix.length) { //если обработали не все вершины
            return null;
        } else {
            return sum;
        }
    }


    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] input = readList(reader);
            int n = input[0];
            int edgeCount = input[1];

            if (n >= 2 && edgeCount == 0) {
                writer.write("Oops! I did it again");
                return;
            }
            if (n < 2 && edgeCount == 0) {
                writer.write("0");
                return;
            }

            final int[][] matrix = new int[n][n];
            final Queue<Edge> edges = new PriorityQueue<>((i1, i2) -> i2.weight - i1.weight);
            final boolean[] added = new boolean[n];


            final int startVertex = initMatrixAndReturnStartVertex(matrix, edgeCount, reader);
            Integer addedCount = 0;

            Integer sum = maxSpanningTree(matrix, edgeCount, edges, added, addedCount, startVertex);

            if (sum == null) {
                writer.write("Oops! I did it again");
            } else {
                writer.write("" + sum);
            }
        }
    }

    private static int initMatrixAndReturnStartVertex(int[][] matrix, int edgeCount, BufferedReader reader) throws IOException {

        int startVertex = -1;

        int i = 0;
        while (i < edgeCount) {

            int[] edgeData = readList(reader);
            int u = edgeData[0] - 1;
            int v = edgeData[1] - 1;
            int weight = edgeData[2];

            add(matrix, u, v, weight);
            add(matrix, v, u, weight);

            if (startVertex == -1) {
                startVertex = u;
            }
            i++;
        }

        return startVertex;
    }

    private static void add(int[][] matrix, int x, int y, int weight) {
        if (matrix[x][y] == 0 || (matrix[x][y] != 0 && matrix[x][y] < weight)) {
            if (x != y) {
                matrix[x][y] = weight;
            }
        }
    }

    private static int[] readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .mapToInt(i -> Integer.parseInt(i)).toArray();
    }

    public static class Edge {
        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
