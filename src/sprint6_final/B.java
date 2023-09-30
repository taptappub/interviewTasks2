package sprint6_final;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
-- ПРИНЦИП РАБОТЫ --
Основная идея сводится к приведению сходного графа с ребрами R и B, к ориентированному графу,
где R - это ребро от узла 1 к узлу 2, а B - от узла 2 к узлу 1.
После этого, задача сводится к поиску циклов в ориентированном графе. А именно, если при при поиске в глубину,
встречается уже серый узел, значит в графе есть циклы.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Алгоритм состоит из 2 шагов:
1) Представление входных данных в виде списка смежности
2) Поиск циклов в ориентированном графе https://practicum.yandex.ru/learn/algorithms/courses/7f101a83-9539-4599-b6e8-8645c3f31fad/sprints/108738/topics/45179065-a73b-473d-94d1-24774573f266/lessons/13789dd6-3b8b-4d85-92bb-4c256edc79c5/

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Алгоритм состоит из:
 - Представление входных данных в виде списка смежности - O(V+E), где V - количество узлов, E - количество ветвей
 - Поиск циклов в ориентированном графе - O(V+E)
Следовательно общая сложность O(V+E) + О(V+E) -> О(V+E)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм использует О(V) - для хранения цветов вершин, где V - количество вершин
Также алгоритм работает на рекурсии, а значит использует О(H) дополнительной памяти, где H - высота дерева
 */
//https://contest.yandex.ru/contest/25070/run-report/85820833/\

public class B {

    private enum Color { WHITE,GRAY,BLACK };

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            List<Integer>[] railway = createRailway(n, reader);

            boolean result = isRailwayOptimal(railway);

            writer.write(result ? "YES" : "NO");
            writer.newLine();
        }
    }

    //O(V * E)
    private static boolean isRailwayOptimal(List<Integer>[] railway) {
        Color[] colors = new Color[railway.length];
        Arrays.fill(colors, Color.WHITE);

        for (int i = 0; i < railway.length; i++) {
            if (colors[i] == Color.WHITE) {
                boolean isOptimal = isRailwayOptimal(railway, i, colors);
                if (!isOptimal) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isRailwayOptimal(List<Integer>[] railway, int vertex1, Color[] colors) {
        colors[vertex1] = Color.GRAY;
        List<Integer> roads = railway[vertex1];
        if (roads != null) {
            for (Integer vertex2 : roads) {
                switch (colors[vertex2]) {
                    case WHITE: {
                        if (!isRailwayOptimal(railway, vertex2, colors)) {
                            return false;
                        }
                        break;
                    }
                    case GRAY: {
                        return false;
                    }
                    case BLACK: {
                        // такого состояния не может быть, т.к. граф связный из условия
                    }
                }
            }
        }
        colors[vertex1] = Color.BLACK;
        return true;
    }

    //O(VE)
    private static List<Integer>[] createRailway(int n, BufferedReader reader) throws IOException {
        char roadTypeR = 'R';
        char roadTypeB = 'B';

        List<Integer>[] railway = new List[n];

        for (int i = 0; i < n - 1; i++) {
            char[] roads = reader.readLine().toCharArray();

            for (int j = 0; j < roads.length; j++) {
                if (roads[j] == roadTypeR) {
                    addWay(railway, i, i + j + 1);
                } else {
                    addWay(railway, i + j + 1, i);
                }
            }
        }

        return railway;
    }

    private static void addWay(List<Integer>[] railway, int from, int to) {
        if (railway[from] == null) {
            railway[from] = new ArrayList<>();
        }
        railway[from].add(to);
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
