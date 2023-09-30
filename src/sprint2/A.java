package sprint2;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;


public class A {

    private static int[][] func(Integer[][] matrix, int n, int m) {
        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[j][i];
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int m = readInt(reader);

            Integer[][] matrix = new Integer[n][m];

            int i = 0;
            while (i < n) {
                Integer[] arr = new Integer[m];
                matrix[i] = readList(reader, arr);

                i++;
            }

            int[][] result = func(matrix, n, m);

            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    writer.write(result[j][k] + " ");
                }
                writer.newLine();
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static Integer[] readList(BufferedReader reader, Integer[] arr) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList()).toArray(arr);
    }
}