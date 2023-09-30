package sprint4;

/*
Алла не остановилась на достигнутом –— теперь она хочет научиться быстро вычислять хеши произвольных подстрок данной строки. Помогите ей!
На вход поступают запросы на подсчёт хешей разных подстрок. Ответ на каждый запрос должен выполняться за O(1). Допустимо в начале работы программы сделать предподсчёт для дальнейшей работы со строкой.
Напомним, что полиномиальный хеш считается по формуле


В данной задаче необходимо использовать в качестве значений отдельных символов их коды в таблице ASCII.

Формат ввода
В первой строке дано число a (1 ≤ a ≤ 1000) –— основание, по которому считается хеш. Во второй строке дано число m (1 ≤ m ≤ 107) –— модуль. В третьей строке дана строка s (0 ≤ |s| ≤ 106), состоящая из больших и маленьких латинских букв.

В четвертой строке дано число запросов t –— натуральное число от 1 до 105. В каждой из следующих t строк записаны через пробел два числа l и r –— индексы начала и конца очередной подстроки. (1 ≤ l ≤ r ≤ |s|).

Формат вывода
Для каждого запроса выведите на отдельной строке хеш заданной в запросе подстроки.
 */

import java.io.*;
public class C {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int a = readInt(reader);
            int m = readInt(reader);
            String s = reader.readLine();

            int n = readInt(reader);
            int[][] matrix = readMatrix(reader, n);

            long[] res = calcHash(a, m, s, matrix);
            for (long hash : res) {
                writer.write(String.valueOf(hash));
                writer.newLine();
            }
        }
    }

    private static long[] calcHash(int a, int m, String s, int[][] matrix) {
        long[] power = new long[s.length() + 1];
        long[] hash = new long[s.length() + 1];

        power[0] = 1;
        for (int i = 1; i < s.length() + 1; i++) {
            power[i] = (power[i - 1] * a) % m;
            hash[i] = (hash[i - 1] * a + s.charAt(i - 1)) % m;
        }

        long[] result = new long[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int start = matrix[i][0] - 1;
            int end = matrix[i][1];

            result[i] = (hash[end] - hash[start] * power[end - start] % m + m) % m;
        }
        return result;
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

    private static int[][] readMatrix(BufferedReader reader, int rowsCount) throws IOException {
        int[][] matrix = new int[rowsCount][];
        for (int i = 0; i < rowsCount; i++) {
            matrix[i] = readList(reader);
        }
        return matrix;
    }
}
