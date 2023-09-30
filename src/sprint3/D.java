package sprint3;

/*
К Васе в гости пришли одноклассники. Его мама решила угостить ребят печеньем.
Но не всё так просто. Печенья могут быть разного размера. А у каждого ребёнка есть фактор жадности —– минимальный
размер печенья, которое он возьмёт. Нужно выяснить, сколько ребят останутся довольными в лучшем случае, когда они действуют оптимально.

Каждый ребёнок может взять не больше одного печенья.

Формат ввода
В первой строке записано n —– количество детей.
Во второй —– n чисел, разделённых пробелом, каждое из которых –— фактор жадности ребёнка. Это натуральные числа,
не превосходящие 1000.

В следующей строке записано число m –— количество печенек.
Далее —– m натуральных чисел, разделённых пробелом —– размеры печенек. Размеры печенек не превосходят 1000.
Оба числа n и m не превосходят 10000.

Формат вывода
Нужно вывести одно число –— количество детей, которые останутся довольными

Пример 1
ввод
2
1 2
3
2 1 3

вывод
2
 */

import java.io.*;

public class D {
    private static int getHappyChildren(int[] wishes, int[] cookies) {
        int[] sortedWishes = linearSort(wishes);
        int[] sortedCookies = linearSort(cookies);

        int counter = 0;

        int wishI = 0;
        int cookiesI = 0;
        while (wishI < sortedWishes.length && cookiesI < sortedCookies.length) {
            int wish = sortedWishes[wishI];
            int cookie = sortedCookies[cookiesI];

            if (cookie >= wish) {
                wishI++;
                counter++;
            }
            cookiesI++;
        }

        return counter;
    }

    private static int[] linearSort(int[] list) {
        int[] counter = new int[1001];
        for (int i : list) {
            counter[i]++;
        }

        int[] result = new int[list.length];
        int resCounter = 0;
        for (int i = 0; i < counter.length; i++) {
            int count = counter[i];
            for (int j = 0; j < count; j++) {
                result[resCounter] = i;
                resCounter++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] wishes = readList(reader);
            int m = readInt(reader);
            int[] cookies = readList(reader);

            int number = getHappyChildren(wishes, cookies);
            writer.write(String.valueOf(number));
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
