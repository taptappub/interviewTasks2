package sprint5;
//https://leetcode.com/problems/unique-binary-search-trees/solutions/31707/fantastic-clean-java-dp-solution-with-detail-explaination/
/*
Ребятам стало интересно, сколько может быть различных деревьев поиска, содержащих в своих узлах все уникальные
числа от 1 до n. Помогите им найти ответ на этот вопрос.

Формат ввода
В единственной строке задано число n. Оно не превосходит 20.

Формат вывода
Нужно вывести число, равное количеству различных деревьев поиска, в узлах которых могут быть размещены числа от 1 до n включительно.
 */

import java.io.*;

public class I {
    static int getTreesNumber(int n) {
        int[] trees = new int[n + 1];
        trees[0] = 1;
        trees[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int root = 0; root < i; root++) {
                //сумма умножения количества узлов слева и справа от текущего!
                trees[i] += trees[i - root - 1] * trees[root];
            }
        }
        return trees[n];
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int a = readInt(reader);
            int res = getTreesNumber(a);
            writer.write(String.valueOf(res));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
