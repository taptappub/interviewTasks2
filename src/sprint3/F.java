package sprint3;

/*
Перед сном Рита решила поиграть в игру на телефоне. Дан массив целых чисел, в котором каждый элемент обозначает
длину стороны треугольника. Нужно определить максимально возможный периметр треугольника, составленного из сторон
с длинами из заданного массива. Помогите Рите скорее закончить игру и пойти спать.

Напомним, что из трёх отрезков с длинами a ≤ b ≤ c можно составить треугольник,
если выполнено неравенство треугольника: c < a + b

Разберём пример:
даны длины сторон 6, 3, 3, 2. Попробуем в качестве наибольшей стороны выбрать 6.
Неравенство треугольника не может выполниться, так как остались 3, 3, 2 —– максимальная сумма из них равна 6.

Без шестёрки оставшиеся три отрезка уже образуют треугольник со сторонами 3, 3, 2.
Неравенство выполняется: 3 < 3 + 2. Периметр равен 3 + 3 + 2 = 8.

Формат ввода
В первой строке записано количество отрезков n, 3≤ n≤ 10000.

Во второй строке записано n неотрицательных чисел, не превосходящих 10 000, –— длины отрезков.

Формат вывода
Нужно вывести одно число —– наибольший периметр треугольника.

Гарантируется, что тройка чисел, которая может образовать треугольник, всегда есть.
 */

import java.io.*;
import java.util.Arrays;
public class F {
    private static int getTrianglePerimeter(int[] lengths) {
        //сначала сортирую
        //потом иду с конца, беря наибольшее число, как опорное
        //c < a + b
        Arrays.sort(lengths);
        int i = lengths.length - 1;
        int bigSide = 0;
        while (i - 2 >= 0) {
            if (bigSide != lengths[i]) {
                bigSide = lengths[i];
                for (int j = i - 1; j >= 1; j--) {
                    if (bigSide < lengths[j] + lengths[j - 1]) {
                        return bigSide + lengths[j] + lengths[j - 1];
                    }
                }
            }
            i--;
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] lengths = readList(reader);

            int perimeter = getTrianglePerimeter(lengths);
            writer.write(String.valueOf(perimeter));
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
