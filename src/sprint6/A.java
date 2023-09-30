package sprint6;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);

            int[][] edges = new int[m][];
            for (int i = 0; i < m; i++) {
                edges[i] = readList(reader);
            }

            int[][] vertexes = mapToSmejnost(n, m, edges);

            for (int[] vertex : vertexes) {
                writer.write(IntStream.of(vertex)
                        .mapToObj(Integer::toString)
                        .collect(Collectors.joining(" ")));
                writer.newLine();
            }
        }
    }

    private static int[][] mapToSmejnost(int n, int m, int[][] edges) {
        HashMap<Integer, Integer> counter = new HashMap<>();
        LinkedList[] result = new LinkedList[n];
        for (int j = 0; j < m; j++) {
            int key = edges[j][0];
            if (counter.containsKey(key)) {
                counter.put(key, counter.get(key) + 1);
                result[key - 1].add(edges[j][1]);
            } else {
                counter.put(key, 1);
                LinkedList linkedList = new LinkedList<Integer>();
                linkedList.add(edges[j][1]);
                result[key - 1] = linkedList;
            }
        }

        int[][] result2 = new int[n][];
        for (int i = 1; i < n + 1; i++) {
            int count = 0;
            if (counter.containsKey(i)) {
                count = counter.get(i);
            }

            int[] subresult = new int[1 + count];
            subresult[0] = count;
            LinkedList linkedList = result[i - 1];
            for (int j = 0; j < count; j++) {
                Integer vertex = (Integer) linkedList.get(j);
                subresult[j + 1] = vertex;
            }

            result2[i - 1] = subresult;
        }
        return result2;
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
