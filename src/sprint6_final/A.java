package sprint6_final;

import java.io.*;
import java.util.*;

/*
-- ПРИНЦИП РАБОТЫ --
Для расчета сумм весов ветвей максимального остовного дерева используется алгоритм Прима. (https://practicum.yandex.ru/learn/algorithms/courses/7f101a83-9539-4599-b6e8-8645c3f31fad/sprints/108738/topics/45179065-a73b-473d-94d1-24774573f266/lessons/adb9a06e-f8a5-4d9b-b88a-2085cc8458f9/)
В основе алгоритма режит использование PriorityQueue, которая возвращает максимальное ребро.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Алгоритм состоит из:
 - построение списка смежности
 - построение масимального остовного дерева
 - проверка графа на связность

Для построения списка смежности используется массив, где индексы - это номера вершин в графе, а значения это массивы с
принадлежащими узлу ветками и их весами.
При построение масимального остовного дерева используется PriorityQueue, которая за O(logV), где V - количество узлов,
возвращает ветку с максимальным весом.
Для проверки графа на связность, используется массив длинной V + 1, где элемент 0 не используется, а индексы остальных элементов являются
номерами узлов в графе.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Алгоритм состоит из:
 - маппинг входных данных в удобный формат - O(E), где E - количество ветвей
 - построение максимального остовного дерева с вычислением суммы весов ветвей - О(ElogV), где V - количество узлов
 - проверка, что граф связный - O(V)
Следовательно общая сложность O(E) + О(ElogV) + O(V) -> О(ElogV)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм использует
 - О(VE) - пространственной сложности, для списка смежности
 - О(V + 1) - пространственной сложности, где хранения добавленных узлов,
 - O(V) - для хранения приоритетной очередь
где V - количество узлов, E - количество ветвей между ними
*/
//https://contest.yandex.ru/contest/24810/run-report/85228365/
public class A {

    private static final String ERROR = "Oops! I did it again";

    private static class Edge {
        public int vertex;
        public int weight;

        public Edge(int vertex, int weight) {
            this.weight = weight;
            this.vertex = vertex;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);

            List<Edge>[] map = readNodes(n, m, reader);
            int sum = prim(n, map);
            if (sum == -1) {
                writer.write(ERROR);
            } else {
                writer.write(sum + "");
            }
        }
    }

    //O(1)
    private static void putToMap(List<Edge>[] map, int v1, int v2, int weight) {
        if (map[v1] != null) {
            List<Edge> newList = map[v1];
            Edge edge = new Edge(v2, weight);
            newList.add(edge);
        } else {
            List<Edge> newList = new ArrayList<>();
            Edge edge = new Edge(v2, weight);
            newList.add(edge);
            map[v1] = newList;
        }
    }

    //O(ELogV + V)
    private static int prim(int n, List<Edge>[] map) {
        int maxSpanCounter = 0;
        boolean[] watchedVertices = new boolean[n + 1];
        PriorityQueue<Edge> maxHeap = new PriorityQueue<>(Comparator.comparingInt(o -> -o.weight));

        maxHeap.add(new Edge(1, 0));

        while (maxHeap.size() != 0) {
            Edge current = maxHeap.remove();
            int vertex = current.vertex;
            if (!watchedVertices[vertex]) {
                maxSpanCounter += current.weight;
                watchedVertices[vertex] = true;
                List<Edge> edges = map[vertex];
                if (edges != null) {
                    for (Edge edge : edges) {
                        int vertex2 = edge.vertex;
                        int weight = edge.weight;
                        if (!watchedVertices[vertex2]) {
                            maxHeap.add(new Edge(vertex2, weight));
                        }
                    }
                }
            }
        }

        for (int i = 1; i < watchedVertices.length; i++) {
            boolean watchedVertex = watchedVertices[i];
            if (!watchedVertex) {
                return -1;
            }
        }

        return maxSpanCounter;
    }

    //O(m)
    private static List<Edge>[] readNodes(int n, int m, BufferedReader reader) throws IOException {
        List<Edge>[] map = new List[n + 1];
        for (int i = 0; i < m; i++) {
            int[] list = readList(reader);
            int v1 = list[0];
            int v2 = list[1];
            int weight = list[2];
            putToMap(map, v1, v2, weight);
            putToMap(map, v2, v1, weight);
        }

        return map;
    }

    //O(3)
    private static int[] readList(BufferedReader reader) throws IOException {
        String[] s = reader.readLine().split(" ");
        int[] ints = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            ints[i] = Integer.parseInt(s[i]);
        }
        return ints;
    }
}
