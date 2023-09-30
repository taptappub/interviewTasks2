package sprint3;

/*
Тимофей решил купить несколько домов на знаменитом среди разработчиков Алгосском архипелаге.
Он нашёл n объявлений о продаже, где указана стоимость каждого дома в алгосских франках.
А у Тимофея есть k франков. Помогите ему определить, какое наибольшее количество домов на
Алгосах он сможет приобрести за эти деньги.

Формат ввода
В первой строке через пробел записаны натуральные числа n и k.
n — количество домов, которые рассматривает Тимофей, оно не превосходит 100000;
k — общий бюджет, не превосходит 100000;
В следующей строке через пробел записано n стоимостей домов. Каждое из чисел не превосходит 100000.
Все стоимости — натуральные числа.

Формат вывода
Выведите одно число —– наибольшее количество домов, которое может купить Тимофей.

Ввод
3 300
999 999 999

Вывод
0
 */
import java.io.*;
import java.util.Arrays;
public class E {
    private static int getHouseCount(int money, int[] prices) {
        Arrays.sort(prices);
        int houseCounter = 0;
        int realMoney = money;

        int i = 0;
        while (i < prices.length && realMoney >= 0) {
            int price = prices[i];
            if (realMoney >= price) {
                houseCounter++;
                realMoney -= price;
            }
            i++;
        }
        return houseCounter;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] data = readList(reader);
            int[] prices = readList(reader);

            int number = getHouseCount(data[1], prices);
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
