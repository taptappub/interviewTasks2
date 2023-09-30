package sprint7_final;

import java.io.*;

/*
-- ПРИНЦИП РАБОТЫ --
На основании двух сравниваемых строк, мы создаем опорную матрицу, где по одной ее стороне будет одна строка, а по другой другая.
В матрицу вписываем полученную дистанцию между двумя строками так, что правый нижний элемент всегда хранит искомое значение расстояния
двух строк по Левинштейну.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Идея реализация подсмотренна здесь https://www.educative.io/answers/the-levenshtein-distance-algorithm
В основе алгоритма находятся принципы динамического программирования, поэтому ответим на следующие вопросы:
 - Что будет храниться в dp?
 В этом случае dp[i][j] — наибольшее расстояние между двумя строками размерами i и j.

 - Каким будет базовый случай для задачи?
 Если обе строки будут размером 0, то дистанция тоже будет 0. Если 1 строка существует, а вторая пустая, то дистанция
 будет равна размеру первой строки.

 - Каким будет переход динамики?
 При посимвольном сравнении двух строк есть только 4 состояния, которые мы можем встретить. Либо символы равны, либо это:
  - Удаление
  - Вставка
  - Замена
 Если символы не равны, то надо найти минимальное значение из состояний и записать в текущую позицию dp.

 - Каким будет порядок вычисления данных в массиве dp?
 Заполняем сначала целиком первую строку матрицы, потом вторую и так далее — пока не заполним i-ю строку.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Алгоритм состоит из:
 - создание базового случая dp для обеих строк - O(N + M), где N и M длины строк
 - создание опорной матрицы dp - О(N * M)
Следовательно общая сложность O(N + M) + О(N * M) -> О(N * M)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм использует
 - О(2*(M + 1)) - пространственной сложности, для опорной матрицы dp, где M длина второй строки, а 2 - это текущая и предыдущая строки в dp

*/
//https://contest.yandex.ru/contest/25597/run-report/86745071/
public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s1 = reader.readLine();
            String s2 = reader.readLine();

            int distance = getLevenshteinDistance2(s1, s2);

            writer.write(distance + "");
            writer.newLine();
        }
    }

    //O(N) + O(M) + O(N*M)
    private static int getLevenshteinDistance2(String s1, String s2) {
        char[] charArray1 = s1.toCharArray();
        int n = charArray1.length;
        char[] charArray2 = s2.toCharArray();
        int m = charArray2.length;

        int[] prevDp = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            prevDp[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            int[] currentDp = new int[m + 1];
            currentDp[0] = i;

            for (int j = 1; j <= m; j++) {
                if (charArray1[i - 1] == charArray2[j - 1]) {
                    currentDp[j] = prevDp[j - 1];
                } else {
                    int deletion = prevDp[j];
                    int replacing = prevDp[j - 1];
                    int insertion = currentDp[j - 1];

                    currentDp[j] = 1 + Math.min(Math.min(deletion, replacing), insertion);
                }
            }
            prevDp = currentDp;
        }

        return prevDp[m];
    }
}
