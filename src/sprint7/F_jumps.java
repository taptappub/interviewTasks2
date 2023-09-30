package sprint7;

import java.io.*;

public class F_jumps {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] s = reader.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            int module = 1000000007;
            long[] dp = new long[n + 1];
            long result = countWays(n, k, dp, module);

            writer.write(result + "");
            writer.newLine();
        }
    }

    private static long countWays(int n, int k, long[] dp, int module) {
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            if (dp[n] == 0) {
                for (int i = 1; i <= k; i++) {
                    dp[n] += countWays(n - i, k, dp, module) % module;
                }
            }
            return dp[n] % module;
        }
    }
}
