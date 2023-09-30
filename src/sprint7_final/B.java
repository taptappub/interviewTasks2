package sprint7_final;

import java.io.*;

/*
-- ПРИНЦИП РАБОТЫ --
На основе опорной матрицы из boolean определяем, можно ли из чисел составить полусумму всех чисел, что означает, что
можно в изначальный список чисел можно разделить на два списка с одинаковыми суммами

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Идея реализация подсмотренна здесь https://www.youtube.com/watch?v=s6FhG--P7z0&ab_channel=TusharRoy-CodingMadeSimple
В основе алгоритма находятся принципы динамического программирования, поэтому ответим на следующие вопросы:
 - Что будет храниться в dp?
 В этом случае dp[i] — это флаг, можно ли из чисел в списке получить i.

 - Каким будет базовый случай для задачи?
 Для 0 решение будет True.

 - Каким будет переход динамики?
 Если предыдущее значение по i True, значит и по следующему числу тоже будет i, либо,
 если dp[i - текущее число] равно True, значит и dp[i] будет True

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Алгоритм состоит из:
 - нахождение суммы всех чисел - O(N), где N - количество чисел
 - создание опорной матрицы dp - О(N * Sum/2), где Sum - сумма N чисел
Следовательно общая сложность O(N) + О(N * Sum/2) -> О(N * Sum/2)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм использует
 - О((N + 1)) - пространственной сложности, для опорной матрицы dp, где N количество чисел

*/
//https://contest.yandex.ru/contest/25597/run-report/86770906/
public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] list = readList(reader);

            boolean result = canFindEqualSets(list);

            writer.write(result ? "True" : "False");
            writer.newLine();
        }
    }

    //O(2N + Sum /2)
    private static boolean canFindEqualSets(int[] list) {
        int sum = sum(list);
        if (sum % 2 != 0) {
            return false;
        }

        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int num : list) {
            for (int i = sum; i > 0; i--) {
                if (i >= num) { // means required sum greater than num in nums
                    // dp[i] -  means if  num not inlcude ,
                    // dp[i-num] -  means if num included , ans will now depend on value of i-num in dp
                    dp[i] = dp[i] || dp[i - num];
                }
            }
        }
        return dp[sum];
    }

    //O(N)
    private static int sum(int[] list) {
        int sum = 0;
        for (int item : list) {
            sum += item;
        }
        return sum;
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
