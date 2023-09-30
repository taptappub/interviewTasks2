package sprint8_final;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
-- ПРИНЦИП РАБОТЫ --
Задача состоит из 2 шагов: распаковки начальных строк и нахождение максимального префикса у получившихся строк.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Распаковка начальных строк:
Идея состоит в том, чтобы держать 2 стека: один с множителями, другой с подстраками, которые надо размножить.
При нахождении ']' происходит умножение подстроки, которая находится на верху стека, на множитель.

Нахождение максимального префикса:
Первым шагом ищется максимальный префикс первой и второй строки, оставшиеся строки сравниваются с полученным префиксом.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Алгоритм состоит из:
 - распаковка начальных строк - мы проходим по N строкам, внутри которых есть множители, так что общая сложность будет O(N * M * Sum(ni * mi))?
 где M - длина самой большой подстроки, ni - это подстрока, mi - это множитель подстроки.
 - Нахождение максимального префикса - О(N)
Следовательно общая сложность O(N * M * Sum(ni * mi))) + О(N) -> О(N * M * Sum(ni * mi))).
Другими словами сложность сильно зависит от подстрок и их множителей, но базово зависит от количество строк и их длинны.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм использует O(N) - дополнительную коллекцию строк, а также O(M*K) - для хранение стека множителей и подстрок,
где К - глубина вложенности, M - длина строки.
Итого: О(N + (M * K)) -> O(N)

*/
//https://contest.yandex.ru/contest/26133/run-report/87436624/
public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<String> strings = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                strings.add(reader.readLine());
            }

            String result = getPrefix(strings);

            writer.write(result);
            writer.newLine();
        }
    }

    private static String getPrefix(List<String> strings) {
        List<String> unpackedStrings = unpack(strings);
        return getStringPrefix(unpackedStrings);
    }

    private static String getStringPrefix(List<String> unpackedStrings) {
        String prevString = unpackedStrings.get(0);

        StringBuilder result = new StringBuilder();
        for (int i = 1; i < unpackedStrings.size(); i++) {
            String string = unpackedStrings.get(i);

            int len = Math.min(prevString.length(), string.length());
            for (int j = 0; j < len; j++) {
                if (string.charAt(j) != prevString.charAt(j)) {
                    break;
                }
                result.append(string.charAt(j));
            }
            prevString = result.toString();
            result = new StringBuilder();
        }
        return prevString;
    }

    private static List<String> unpack(List<String> strings) {
        List<String> result = new ArrayList<>();
        for (String string : strings) {
            result.add(unpack(string));
        }
        return result;
    }

    private static String unpack(String string) {
        StringBuilder result = new StringBuilder();
        Stack<Integer> multipliers = new Stack();
        List<StringBuilder> substrings = new ArrayList<>();

        char[] charArray = string.toCharArray();
        for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
            char c = charArray[i];

            if (Character.isDigit(c) && charArray[i + 1] == '[') {
                multipliers.push(Character.getNumericValue(c));
                i++;
                substrings.add(new StringBuilder());
            } else if (c == ']') {
                String subString = substrings.get(substrings.size() - 1).toString();
                String multiString = subString.repeat(multipliers.pop());
                if (multipliers.size() > 0) {
                    substrings.get(substrings.size() - 2).append(multiString);
                } else {
                    result.append(multiString);
                }
                substrings.remove(substrings.size() - 1);
            } else {
                if (multipliers.size() > 0) {
                    substrings.get(substrings.size() - 1).append(c);
                } else {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
