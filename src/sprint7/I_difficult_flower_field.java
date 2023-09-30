package sprint7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class I_difficult_flower_field {
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

            Result result = difficultFlowersCounter(n, m, matrix);

            writer.write(result.sum + "");
            writer.newLine();

            while (!result.direction.isEmpty()) {
                writer.write(result.direction.pop());
            }
            writer.newLine();
        }
    }

    private static Result difficultFlowersCounter(int n, int m, List<List<Integer>> matrix) {
        int[][] dp = new int[n + 1][m + 1];
        Result result = new Result();

        result.setSum(getSum(dp, m, matrix));
        getWay(dp, result);

        return result;
    }

    private static void getWay(int[][] dp, Result result) {
        int i = 0;
        int j = dp[0].length - 1;

        while (i != dp.length - 2 || j != 1) {
            if (dp[i + 1][j] == dp[i][j - 1]) {
                if (i + 1 < dp.length - 1) {
                    result.addUp();
                    i += 1;
                } else {
                    result.addRight();
                    j -= 1;
                }
            } else if (dp[i + 1][j] > dp[i][j - 1]) {
                result.addUp();
                i += 1;
            } else {
                result.addRight();
                j -= 1;
            }
        }
    }

    private static int getSum(int[][] dp, int m, List<List<Integer>> matrix) {
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

    private static class Result {
        public int sum;
        public Stack<Character> direction;

        public Result() {
            this.sum = 0;
            this.direction = new Stack<>();
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public void addRight() {
            direction.push('R');
        }

        public void addUp() {
            direction.push('U');
        }
    }
}
//101
//110
//
//0101
//0110
//0000

//(n - 1),0