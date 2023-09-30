package sprint6_Masha;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Id отправки в Контест: https://contest.yandex.ru/contest/25070/run-report/85689028/
 * <p>
 * Принцип работы и алгоритм:
 * Чтобы понять, можно ли добраться из одного города в другой по разным дорогам, алгоритм переворачивает
 * направление всех дорог B и ищет циклы в графе, используя обход в глубину.
 * Сначала все вершины помечены белым (значение 0), как еще никак не обработанные.
 * Алгоритм обрабатывает каждую белую вершину,
 * - заходя на нее (помечаем серым цветом, значение 1),
 * - проверяя цвета всех конечных вершин исходящих ребер
 * - если встретился серый цвет, значит мы уже заходили на эту вершину - и это признак цикла.
 * - если вершина белая - проверяем ее рекурсивно на наличие цикла.
 * - если их не нашла - помечаем вершину как обработанную черным цветом (значение 2).

 * Обоснование:
 * Алгоритм размечает вершины таким образом, чтобы можно было понять когда мы возвращаемся на уже обработанную
 * вершину.

 * Пространственная сложность:
 * Нам необходимо O(N) доп. памяти для хранения разметки всех вершин (помимо хранения самих ребер).
 * Также присутствует "рекурсивный спуск", видимо в худшем случае их количество N-1, где N - количество вершин.
 * Итого - O (N), где N - количество вершин.
 *
 * Временная сложность:
 * Внешний цикл перебора белый вершин - O (N), где N - количество вершин.
 * Так как ребра предствлены как списки смежности - то при каждой обработке вершины, нужно перебрать их всех.
 * Т.е O (E), где E - количество ребер.
 * Итого -  O (N+E), где N - количество вершин, E - количество ребер.
 */

class TaskB_v1 {

    static private boolean hasCyclic(List<Integer>[] matrix, int fromVertex, int[] color) {

        color[fromVertex] = 1;
        if (matrix[fromVertex] != null) {
            // для всех исходящих ребер
            for (int toVertex : matrix[fromVertex]) {

                if (color[toVertex] == 1) { // gray
                    return true;
                }
                if (color[toVertex] == 0 && hasCyclic(matrix, toVertex, color)) {
                    return true;
                }
            }
        }
        color[fromVertex] = 2; // black
        return false;
    }

    static private boolean hasCyclic(List<Integer>[] matrix) {

        int[] color = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            if (color[i] == 0) {
                if (hasCyclic(matrix, i, color))
                    return true;
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            List<Integer>[] matrix = new ArrayList[n];


            int i = 0;
            while (i < n - 1) {

                char[] line = reader.readLine().toCharArray();

                int y = 0;
                while (y < line.length) {

                    if ('R' == line[y]) {
                        addEdge(matrix, i, y + i + 1);
                    } else {
                        addEdge(matrix, y + i + 1, i);
                    }
                    y++;
                }
                i++;
            }


            boolean result = hasCyclic(matrix);
            if (result) {
                writer.write("NO");
            } else {
                writer.write("YES");
            }
            writer.newLine();
        }
    }

    static private void addEdge(List<Integer>[] matrix, int fromVertex, int toVertex) {

        if (matrix[fromVertex] == null) {
            matrix[fromVertex] = new ArrayList<>();
        }
        matrix[fromVertex].add(toVertex);
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
