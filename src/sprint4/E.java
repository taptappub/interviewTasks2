package sprint4;

/*
На вход подается строка. Нужно определить длину наибольшей подстроки, которая не содержит повторяющиеся символы.

Формат ввода
Одна строка, состоящая из строчных латинских букв. Длина строки не превосходит 10 000.

Формат вывода
Выведите натуральное число —– ответ на задачу.

Ввод
abcabcbb

Вывод
3
 */

import java.io.*;
import java.util.HashMap;

public class E {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String inputData = reader.readLine();

            int res = subStringCounter(inputData);
            writer.write(String.valueOf(res));
        }
    }

    private static int subStringCounter(String inputData) {
        int max = 0;
        char[] array = inputData.toCharArray();
        HashMap<Character, Boolean> hashMap = new HashMap<>();
        for (int i = 0; i < array.length - max; i++) {
            hashMap.clear();
            for (int j = i; j < array.length; j++) {
                char c = array[j];
                if (hashMap.containsKey(c)) {
                    break;
                }
                hashMap.put(c, true);
            }
            max = Math.max(max, hashMap.size());
        }
        return max;
    }
}
