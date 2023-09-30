package sprint3;

/*
Задача повышенной сложности

На каждом острове в архипелаге Алгосы живёт какое-то количество людей или же остров необитаем
(тогда на острове живёт 0 людей). Пусть на i-м острове численность населения составляет ai.
Тимофей захотел найти медиану среди всех значений численности населения.

Определение: Медиана (https://ru.wikipedia.org/wiki/Медиана_(статистика)) массива чисел a_i —– это такое число,
что половина чисел из массива не больше него, а другая половина не меньше. В общем случае медиану массива можно найти,
отсортировав числа и взяв среднее из них. Если количество чисел чётно, то возьмём в качестве медианы полусумму
соседних средних чисел, (a[n/2] + a[n/2 + 1])/2.

У Тимофея уже есть отдельно данные по северной части архипелага и по южной, причём значения численности населения
в каждой группе отсортированы по неубыванию.
Определите медианную численность населения по всем островам Алгосов.
Подсказка: Если n –— число островов в северной части архипелага, а m –— в южной, то ваше решение должно работать
за O(log(N + M)).

Формат ввода
В первой строке записано натуральное число n, во второй —– натуральное число m. Они не превосходят 10 000.
Далее в строку через пробел записаны n целых неотрицательных чисел, каждое из которых не превосходит 10 000, –— значения
численности населения в северной части Алгосов.
В последней строке через пробел записаны m целых неотрицательных чисел, каждое из которых не
превосходит 10 000 –— значения численности населения в южной части Алгосов.
Значения в третьей и четвёртой строках упорядочены по неубыванию.
Формат вывода
Нужно вывести одной число — найденную медиану.

ввод
2
1
1 3
2

вывод
2
 */

import java.io.*;
public class M {
    private static int[] merge(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        int ai = 0;
        int bi = 0;

        int counter = 0;
        while (ai < a.length && bi < b.length) {
            if (a[ai] < b[bi]) {
                res[counter] = a[ai];
                ai++;
            } else {
                res[counter] = b[bi];
                bi++;
            }
            counter++;
        }

        while (ai < a.length) {
            res[counter] = a[ai];
            ai++;
            counter++;
        }

        while (bi < b.length) {
            res[counter] = b[bi];
            bi++;
            counter++;
        }

        return res;
    }

    private static Double getMediana(int[] a, int[] b) {
        int[] merged = merge(a, b);

        int n = merged.length;
        double mediana = 0;
        if (n % 2 == 0) {
            mediana = ((double) (merged[n / 2 - 1] + merged[n / 2])) / 2;
        } else {
            mediana = merged[n / 2];
        }
        return mediana;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int m = readInt(reader);
            int[] northPopulation = readList(reader);
            int[] southPopulation = readList(reader);

            Double mediana = getMediana(northPopulation, southPopulation);
            if (mediana % 1 == 0) {
                writer.write(String.valueOf(mediana.intValue()));
            } else {
                writer.write(mediana.toString());
            }
        }
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
