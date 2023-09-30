package sprint7;

import java.io.*;
import java.util.Stack;

public class K_horoscope {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            int[] string1 = readList(reader);
            int m = readInt(reader);
            int[] string2 = readList(reader);

            Result result = getHoroscope(n, string1, m, string2);

            writer.write(result.len + "");
            writer.newLine();

            while (!result.indexes1.isEmpty()) {
                writer.write(result.indexes1.pop() + " ");
            }
//            writer.write(result.indexes1.stream().map(Object::toString)
//                    .collect(Collectors.joining(" ")));
            writer.newLine();

//            writer.write(result.indexes2.stream().map(Object::toString)
//                    .collect(Collectors.joining(" ")));
            while (!result.indexes2.isEmpty()) {
                writer.write(result.indexes2.pop() + " ");
            }
            writer.newLine();
        }
    }

    private static Result getHoroscope(int n, int[] string1, int m, int[] string2) {
        Result result = new Result();

        int[][] dp = new int[n + 1][m + 1];
        result.len = getSequenceLen(dp, n, string1, m, string2);
        findIndexes(dp, n, string1, m, string2, result);

        return result;
    }

    private static void findIndexes(int[][] dp, int n, int[] string1, int m, int[] string2, Result result) {
        int i = n;
        int j = m;

        while (i != 0 && j != 0) {
            if (string1[i - 1] == string2[j - 1]) {
                result.indexes1.push(i);
                result.indexes2.push(j);

                i--;
                j--;
            } else {
                if (dp[i][j] == dp[i - 1][j]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
    }

    private static int getSequenceLen(int[][] dp, int n, int[] string1, int m, int[] string2) {
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (string1[i - 1] == string2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
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

    private static class Result {
        public int len;
        public Stack<Integer> indexes1;
        public Stack<Integer> indexes2;

        public Result() {
            indexes1 = new Stack<>();
            indexes2 = new Stack<>();
        }
    }
}
