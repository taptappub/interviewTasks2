package sprint6;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class D {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);

            List<Integer>[] map = readNodes(n, m, reader);
            int start = readInt(reader);
            List<Integer> vertexes = bfs(n, start, map);
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

    private static List<Integer> bfs(int n, int start, List<Integer>[] edges) {
//      colors = {0, 1, 2};
        int[] colors = new int[n + 1];

        List<Integer> result = new ArrayList<>();
        Queue<Integer> planned = new LinkedList<>();
        planned.offer(start);
        colors[start] = 1;
        result.add(start);
        while (!planned.isEmpty()) {
            int vertex = planned.poll();
            List<Integer> vertexes = edges[vertex];
            if (vertexes != null) {
                for (Integer edgeVertex : vertexes) {
                    if (colors[edgeVertex] == 0) {
                        colors[edgeVertex] = 1;
                        result.add(edgeVertex);
                        planned.offer(edgeVertex);
                    }
                }
            }
            colors[vertex] = 2;
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
                integers.sort(Comparator.naturalOrder());
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
