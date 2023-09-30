package sprint6;

import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
5 3
1 3
2 3
5 2
 */

/*
0 0 1 0 0
0 0 1 0 0
0 0 0 0 0
0 0 0 0 0
0 1 0 0 0
 */
public class B {
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
        int[][] result = new int[n][n];
        for (int[] edge : edges) {
            result[edge[0] - 1][edge[1] - 1] = 1;
        }
        return result;
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
