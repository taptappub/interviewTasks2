package sprint3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Вечером ребята решили поиграть в игру «Большое число».
Даны числа. Нужно определить, какое самое большое число можно из них составить.

Формат ввода
В первой строке записано n — количество чисел. Оно не превосходит 100.
Во второй строке через пробел записаны n неотрицательных чисел, каждое из которых не превосходит 1000.

Формат вывода
Нужно вывести самое большое число, которое можно составить из данных чисел.

ввод
3
15 56 2

вывод
56215
 */
public class H {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            List<String> list = readList(reader);

            String number = makeBiggestNumber(list);
            writer.write(number);
        }
    }

    private static String makeBiggestNumber(List<String> list) {
        list.sort((o1, o2) ->  Integer.parseInt(o2 + o1) - Integer.parseInt(o1 + o2));
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }

    //  35 359 12 2 -> 353 35 3 12 || 35 359 2 12

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return new ArrayList<>(Arrays.asList(reader.readLine().split(" ")));
    }
}
