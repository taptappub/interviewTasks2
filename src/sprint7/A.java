package sprint7;

import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] array = readList(reader);

            int result = calcProfit(n, array);

            writer.write(String.valueOf(result));
            writer.newLine();
        }
    }

    private static int calcProfit(int n, int[] array) {
        //Стратегия - пока цена следующего дня больше, чем предыдущего,
        // мы будем продавать. Фактически, мы складываем все монотонно увеличивающиеся
        // сегменты на графике акций
        int profit = 0;
        for (int i = 1; i < n; i++) {
            if (array[i - 1] < array[i]) {
                profit += array[i] - array[i - 1];
            }
        }
        return profit;
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
