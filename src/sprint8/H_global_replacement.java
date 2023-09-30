package sprint8;

import java.io.*;
//https://practicum.yandex.ru/learn/algorithms/courses/7f101a83-9539-4599-b6e8-8645c3f31fad/sprints/108740/topics/c83dc564-7573-4f64-8d1f-032c4206fac1/lessons/de815984-f575-4226-9195-fa1ca74ad8c9/
public class H_global_replacement {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String mainString = reader.readLine();
            String subString = reader.readLine();
            String newSubString = reader.readLine();

            String result = repleaceSunString(mainString, subString, newSubString);
            writer.write(result);
            writer.newLine();
        }
    }
/*
функция поиск(p, text):
    # Функция возвращает все позиции вхождения шаблона в тексте.
    result = []
    s = p + '#' + text
    π = [0, None, None, ...]  # Массив длины |p|.
    π_prev = 0
    для i из [1 .. |s|):
        k = π_prev
        пока (k > 0) и (s[k] ≠ s[i]):
            k = π[k - 1]
        если s[k] == s[i], то:
            k += 1
        # Запоминаем только первые |p| значений π-функции.
        если i < |p|, то:
            π[i] = k
        # Запоминаем последнее значение π-функции.
        π_prev = k
        # Если значение π-функции равно длине шаблона, то вхождение найдено.
        если k == |p|, то:
            # i - это позиция конца вхождения шаблона.
            # Дважды отнимаем от него длину шаблона, чтобы получить позицию начала:
            #  - чтобы «переместиться» на начало найденного шаблона,
            #  - чтобы не учитывать добавленное "pattern#".
            result.добавить(i - 2 * |p|)
    вернуть result
 */
    private static String repleaceSunString(String mainString, String subString, String newSubString) {
        return mainString.replaceAll(subString, newSubString);
    }
}
