package sprint8_final;

import java.io.*;
import java.util.*;

//https://www.baeldung.com/trie-java
/*
-- ПРИНЦИП РАБОТЫ --
На основе опорной матрицы из boolean определяем, можно ли из слов составить требуемую строку.
Для удобства слова складываются в префиксное дерево.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Алгоритм состоит из 2 шагов: создание префиксного дерева и определение, можно ли из заданных слов сложить заданную строку.

 - Создание префиксного дерева: основная идея, это в качестве списка детей использовать HashMap, где ключом является буква.
 - Определение, можно ли из заданных слов сложить заданную строку:
   В основе алгоритма находятся принципы динамического программирования, поэтому ответим на следующие вопросы:
     - Что будет храниться в dp?
     В этом случае dp[i] — флаг, показывающий, что в данной позиции заканчивается одно из слов из словаря.

     - Каким будет базовый случай для задачи?
     Для 0 решение будет True. Другими словами, мы утвержадаем, что из пустого списка слов собрать пустую строку.

     - Каким будет переход динамики?
     Если i True, значит это конец слова из словаря в заданной строке, и значит от i можно искать новое слово из словаря.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Алгоритм состоит из:
 - создание префиксного дерева - O(N*M), где N - количество строк в словаре, M - длина самого длинного слова
 - создание опорной матрицы dp - О(K^2), где K - длина строки
Следовательно общая сложность O(N*M) + О(K^2), другими словами, что если слова в словаре будут подвторяющиеся,
то префиксное дерево будет создаваться дольше, чем работать сам алгоритм

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
O(N*M) - для хранения префиксного дерева, N - количество строк в словаре, M - длина самого длинного слова
dp - массив O(K), где K - длина заданной строки

*/
//https://contest.yandex.ru/contest/26133/run-report/87564707/
public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String str = reader.readLine();
            int n = readInt(reader);
            Node root = createTree(n, reader);
            boolean result = isStringValid(str, root);

            writer.write(result ? "YES" : "NO");
            writer.newLine();
        }
    }

    private static Node createTree(int n, BufferedReader reader) throws IOException {
        Node root = new Node();
        for (int i = 0; i < n; i++) {
            Node current = root;

            for (char l: reader.readLine().toCharArray()) {
                current = current.children.computeIfAbsent(l, c -> new Node());
            }
            current.isWord = true;
        }
        return root;
    }

    private static boolean isStringValid(String str, Node root) {
        boolean[] dp = new boolean[str.length() + 1];
        dp[0] = true;
        for (int i = 0; i < str.length(); i++) {
            Node node = root;
            if (dp[i]) {
                for (int j = i; j < str.length() + 1; j++) {
                    if (node.isWord) {
                        dp[j] = true;
                    }
                    if (j == str.length() || !node.children.containsKey(str.charAt(j))) {
                        break;
                    }
                    node = node.children.get(str.charAt(j));
                }
            }
        }

        return dp[dp.length - 1];
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static class Node {
        //первоначально, HashMap не было, и в Node было значение, признак терминальности и ссылки на детей
        public boolean isWord;
        public HashMap<Character, Node> children;

        public Node() {
            isWord = false;
            children = new HashMap<>();
        }
    }
}
