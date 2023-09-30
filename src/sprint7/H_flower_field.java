package sprint7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class H_flower_field {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);

            List<List<Integer>> matrix = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                matrix.add(readList(reader));
            }

            int sum = flowersCounter(n, m, matrix);

            writer.write(sum + "");
            writer.newLine();
        }
    }

    private static int flowersCounter(int n, int m, List<List<Integer>> matrix) {
        int[][] dp = new int[n + 1][m + 1];

        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]) + matrix.get(i).get(j - 1);
            }
        }

        return dp[0][m];
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return reader.readLine().chars().mapToObj(c -> c - 48).collect(Collectors.toList());
    }
}
//101
//110
//
//0101
//0110
//0000

//(n - 1),0