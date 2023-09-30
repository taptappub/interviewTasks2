package sprint6;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

//dfs
/*
color = [white, white, ...]  # Длина массива равна числу вершин |V|.

функция DFS(v):  # v - номер вершины
  color[v] = gray  # Вершина посещена, но ещё не обработана.
  для каждого исходящего ребра (v,w):
    возьмём вершину w
    if color[w] == white:  # Если вершина не посещена, то
      DFS(w)             # запустим обход от найденной смежной вершины.
  color[v] = black  # Теперь вершина обработана.

функция MainDFS():
  для каждого i от 0 до |V| - 1:
    # Перебираем варианты стартовых вершин, пока они существуют.
    if color[i] == white:
      DFS(i)  # Запускаем обход, стартуя с i-й вершины.
 */
public class C {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);

            int start = readInt(reader);
            List<Integer>[] map = readNodes(n, m, reader);
            List<Integer> vertexes = dfs(n, start, map);
            writer.write(vertexes.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    /*
    4 4

    3 2
    4 3
    1 4
    1 2

    3
     */

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

    private static List<Integer> dfs(int n, int start, List<Integer>[] edges) {
//      colors = {0, 1, 2};
        int[] colors = new int[n + 1];
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
//        Queue<Integer> stack = new PriorityQueue<>();

        //stack.push(start);
        stack.add(start);
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
//            int vertex = stack.poll();
            switch (colors[vertex]) {
                //white
                case 0: {
                    // Красим вершину в серый. И сразу кладём её обратно в стек:
                    //это позволит алгоритму позднее вспомнить обратный путь по графу.
                    colors[vertex] = 1;
                    result.add(vertex);
                    stack.push(vertex);
//                    stack.add(vertex);
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
//                                stack.add(edgeVertex);
                            }
                        }
                    }
                    break;
                }
                //grey
                case 1: {
                    colors[vertex] = 2;
                    break;
                }
                //black
                case 2: {
                    //do nothing
                    break;
                }
            }
        }

        return result;
    }

    private static List<Integer>[] readNodes(int n, int m, BufferedReader reader) throws IOException {
        List<Integer>[] map = new List[n + 1];
        for (int i = 0; i < m; i++) {
            int[] list = readList(reader);
            int v1 = list[0];
            int v2 = list[1];
            putToMap(map, v1, v2);
            putToMap(map, v2, v1);
        }

        for (List<Integer> integers : map) {
            if (integers != null) {
                integers.sort(Comparator.reverseOrder());
            }
        }

        return map;
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
