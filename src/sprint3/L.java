package sprint3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Вася решил накопить денег на два одинаковых велосипеда — себе и сестре. У Васи есть копилка, в которую каждый день он может добавлять деньги (если, конечно, у него есть такая финансовая возможность). В процессе накопления Вася не вынимает деньги из копилки.

У вас есть информация о росте Васиных накоплений — сколько у Васи в копилке было денег в каждый из дней.

Ваша задача — по заданной стоимости велосипеда определить

первый день, в которой Вася смог бы купить один велосипед,
и первый день, в который Вася смог бы купить два велосипеда.
Подсказка: решение должно работать за O(log n).

Формат ввода
В первой строке дано число дней n, по которым велись наблюдения за Васиными накоплениями. 1 ≤ n ≤ 106.

В следующей строке записаны n целых неотрицательных чисел. Числа идут в порядке неубывания. Каждое из чисел не превосходит 106.

В третьей строке записано целое положительное число s — стоимость велосипеда. Это число не превосходит 106.

Формат вывода
Нужно вывести два числа — номера дней по условию задачи.

Если необходимой суммы в копилке не нашлось, нужно вернуть -1 вместо номера дня.

Пример 1
Ввод
6
1 2 4 4 6 8
3

Вывод
3 5
 */
public class L {

    private static int[] getBestDays(List<String> list, int k) {
        int[] result = new int[2];
        Arrays.fill(result, -1);

        getBestDays(result, list, 0, list.size() - 1, k, k * 2);
        return result;
    }

    private static void getBestDays(
            int[] result,
            List<String> list,
            int leftIndex,
            int rightIndex,
            int k,
            int doubleK
    ) {
        if (leftIndex > rightIndex) {
            return;
        }

        int kIndex = (leftIndex + rightIndex) / 2;
        int kValue = Integer.parseInt(list.get(kIndex));

        int preKValue = -1;
        if (kIndex - 1 > -1) {
            preKValue = Integer.parseInt(list.get(kIndex - 1));
        }
        if (result[0] == -1) {
            if (kValue >= k && preKValue < k) {
                result[0] = kIndex + 1;
            } else if (kValue >= k) {
                getBestDays(result, list, leftIndex, kIndex - 1, k, doubleK);
            } else {
                getBestDays(result, list, kIndex + 1, rightIndex, k, doubleK);
                ;
            }
        }

        if (result[1] == -1) {
            if (kValue >= doubleK && preKValue < doubleK) {
                result[1] = kIndex + 1;
            } else if (kValue >= doubleK) {
                getBestDays(result, list, leftIndex, kIndex - 1, k, doubleK);
            } else {
                getBestDays(result, list, kIndex + 1, rightIndex, k, doubleK);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            List<String> list = readList(reader);
            int k = readInt(reader);

            int[] result = getBestDays(list, k);

            writer.write(result[0] + " " + result[1]);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return new ArrayList<>(Arrays.asList(reader.readLine().split(" ")));
    }
}
