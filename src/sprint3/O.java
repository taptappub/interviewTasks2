package sprint3;

/*
Гоша долго путешествовал и измерил площадь каждого из n островов Алгосов, но ему этого мало!
Теперь он захотел оценить, насколько разнообразными являются острова в составе архипелага.
Для этого Гоша рассмотрел все пары островов (таких пар, напомним, n * (n-1) / 2) и посчитал попарно
разницу площадей между всеми островами. Теперь он собирается упорядочить полученные разницы,
чтобы взять k-ую по порядку из них.

Помоги Гоше найти k-ю минимальную разницу между площадями эффективно.

Пояснения к примерам
Пример 1
Выпишем все пары площадей и найдём соответствующие разницы
|2 - 3| = 1
|3 - 4| = 1
|2 - 4| = 2
Так как нам нужна 2-я по величине разница, то ответ будет 1.

Пример 2
У нас есть два одинаковых элемента в массиве —– две единицы, поэтому минимальная (первая) разница равна нулю.

Формат ввода
В первой строке записано натуральное число n –— количество островов в архипелаге (2 ≤ n ≤ 100 000).
В следующей строке через пробел записаны n площадей островов — n натуральных чисел, каждое из которых не превосходит 1 000 000.
В последней строке задано число k. Оно находится в диапазоне от 1 до n(n - 1) / 2.

Формат вывода
Выведите одно число –— k-ую минимальную разницу.

Ввод
3
2 3 4
2

Вывод
1
 */
//https://www.geeksforgeeks.org/k-th-smallest-absolute-difference-two-elements-array/ РЕШЕНИЕ

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class O {

    // returns number of pairs with absolute
    // difference less than or equal to mid
    static int countPairs(int[] a, int n, int mid) {
        int res = 0, value;
        for (int i = 0; i < n; i++) {
            // Upper bound returns pointer to position
            // of next higher number than a[i]+mid in
            // a[i..n-1]. We subtract (ub + i + 1) from
            // this position to count
            if (a[i] + mid > a[n - 1]) {
                res += (n - (i + 1));
            } else {
                int ub = upperbound(a, n, a[i] + mid);
                res += (ub - (i + 1));
            }
        }
        return res;
    }

    // returns the upper bound
    static int upperbound(int a[], int n, int value) {
        int low = 0;
        int high = n;
        while (low < high) {
            final int mid = (low + high) / 2;
            if (value >= a[mid])
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }

    // Returns k-th absolute difference
    static int kthDiff(int a[], int n, int k) {
        // Sort array
        Arrays.sort(a);

        // Minimum absolute difference
        int low = a[1] - a[0];
        for (int i = 1; i <= n - 2; ++i)
            low = Math.min(low, a[i + 1] - a[i]);

        // Maximum absolute difference
        int high = a[n - 1] - a[0];

        // Do binary search for k-th absolute difference
        while (low < high) {
            int mid = (low + high) >> 1;
            if (countPairs(a, n, mid) < k)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }
//    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            //int n = readInt(reader);
//            int[] islandsCount = readList(reader);
            //int[] islandsCount = readList(reader);
            //int k = readInt(reader);
            int a[] = {1, 2, 30, 4, 5, 7, 23};
            int k = 2;
            int dif = kthDiff(a, a.length, k);
            writer.write(String.valueOf(dif));
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
