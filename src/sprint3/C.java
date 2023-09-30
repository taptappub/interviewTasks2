package sprint3;

import java.io.*;

/*
Гоша любит играть в игру «Подпоследовательность»: даны 2 строки, и нужно понять, является ли первая из них подпоследовательностью второй. Когда строки достаточно длинные, очень трудно получить ответ на этот вопрос, просто посмотрев на них. Помогите Гоше написать функцию, которая решает эту задачу.

Формат ввода
В первой строке записана строка s.

Во второй —- строка t.

Обе строки состоят из маленьких латинских букв, длины строк не превосходят 150000. Строки не могут быть пустыми.

Формат вывода
Выведите True, если s является подпоследовательностью t, иначе —– False.

Пример 1
ввод
abc
ahbgdcu

вывод
True

 */
public class C {
    private static boolean isSubSubsequence(String s, String t) {
        char[] tArray = t.toCharArray();
        char[] sArray = s.toCharArray();
        int sCounter = 0;
        for (char c : tArray) {
            if (sCounter == s.length()) {
                return true;
            }
            if (c == sArray[sCounter]) {
                sCounter++;
            }
        }
        return sCounter == s.length();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String s = reader.readLine();
            String t = reader.readLine();
            boolean res = isSubSubsequence(s, t);
            if (res) {
                writer.write("True");
            } else {
                writer.write("False");
            }
        }
    }
}
