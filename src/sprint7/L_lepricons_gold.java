package sprint7;

import java.io.*;

public class L_lepricons_gold {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int weight = Integer.parseInt(s[1]);
            int[] gold = readList(reader);

            int maxWeightOfGold = getMaxWeightOfGold(n, weight, gold);

            writer.write(maxWeightOfGold + "");
            writer.newLine();
        }
    }

    private static int getMaxWeightOfGold(int n, int weight, int[] gold) {
//        int[][] dp = new int[n + 1][weight + 1];
        int[][] dp = new int[2][weight + 1];

        int newI = 0;
        int oldI = 1;

        for (int i = 1; i < n + 1; i++) {
            newI = i % 2;
            oldI = Math.abs(newI - 1);

            for (int j = 0; j < weight + 1; j++) {
                int pos = Math.max((j - gold[i - 1]), 0);
                dp[newI][j] = (gold[i - 1] > j)
                        ? dp[oldI][j]
                        : Math.max(dp[oldI][j], gold[i - 1] + dp[oldI][pos]);
            }
        }

        return dp[newI][weight];
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
