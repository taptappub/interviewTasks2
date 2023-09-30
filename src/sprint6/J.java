package sprint6;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

//топологическая сортировка
public class J {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);

            List<Integer>[] map = new List[n + 1];
            int start = 0;
            for (int i = 0; i < m; i++) {
                int[] list = readList(reader);
                int v1 = list[0];
                int v2 = list[1];
                putToMap(map, v1, v2);
//                putToMap(map, v2, v1);
            }

            for (List<Integer> integers : map) {
                if (integers != null) {
                    integers.sort(Comparator.reverseOrder());
                }
            }

            Stack<Integer> vertexes = topologicalSort(n, map);
            for (int i = 0; i < n; i++) {
                writer.write(vertexes.pop() + " ");
            }
        }
    }

    private static Stack<Integer> topologicalSort(int n, List<Integer>[] edges) {
//      colors = {0, 1, 2};
        int[] colors = new int[n + 1];
        Stack<Integer> result = new Stack<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < n + 1; i++) {
            stack.push(i);
            while (!stack.isEmpty()) {
                int vertex = stack.pop();
                switch (colors[vertex]) {
                    //white
                    case 0: {
                        // Красим вершину в серый. И сразу кладём её обратно в стек:
                        //это позволит алгоритму позднее вспомнить обратный путь по графу.
                        colors[vertex] = 1;
                        stack.push(vertex);
                        // Теперь добавляем в стек все непосещённые соседние вершины,
                        // вместо вызова рекурсии
                        //                    для каждого исходящего ребра (v, w):
//                    возьмём вершину w
//                    если color[ w] ==white:
//                    stack.push(w)
                        List<Integer> vertexes = edges[vertex];
                        if (vertexes != null) {
                            for (Integer edgeVertex : vertexes) {
                                if (colors[edgeVertex] == 0) {
                                    stack.push(edgeVertex);
                                }
                            }
                        }
                        break;
                    }
                    //grey
                    case 1: {
                        colors[vertex] = 2;
                        result.push(vertex);
                        break;
                    }
                    //black
                    case 2: {
                        //do nothing
                        break;
                    }
                }
            }
        }

        return result;
    }

    private static void putToMap(List<Integer>[] map, int v1, int v2) {
        if (map[v1] != null) {
            List<Integer> newList = map[v1];
            newList.add(v2);
        } else {
            List<Integer> newList = new ArrayList<>();
            newList.add(v2);
            map[v1] = newList;
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readList(BufferedReader reader) throws IOException {
        String[] s = reader.readLine().split(" ");
        int[] ints = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            ints[i] = Integer.parseInt(s[i]);
        }
        return ints;
    }
}
