package sprint4;

/*
Жители Алгосов любят устраивать турниры по спортивному программированию.
Все участники разбиваются на пары и соревнуются друг с другом. А потом два самых сильных программиста
встречаются в финальной схватке, которая состоит из нескольких раундов. Если в очередном раунде
выигрывает первый участник, в таблицу с результатами записывается 0, если второй, то 1. Ничьей в раунде быть не может.

Нужно определить наибольший по длине непрерывный отрезок раундов, по результатам которого суммарно получается ничья.
Например, если дана последовательность 0 0 1 0 1 1 1 0 0 0, то раунды с 2-го по 9-й (нумерация начинается с единицы) дают ничью.

Формат ввода
В первой строке задаётся n (0 ≤ n ≤ 105) –— количество раундов.
Во второй строке через пробел записано n чисел –— результаты раундов. Каждое число равно либо 0, либо 1.

Формат вывода
Выведите длину найденного отрезка.

Ввод
2
0 1

Вывод
2
 */
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class G {
     public static int getMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int maxlen = 0, count = 0;

        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);

            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            if (n == 0) {
                writer.write("0");
            } else {
                int[] lens = readList(reader);
                int dif = getMaxLength(lens);
                writer.write(String.valueOf(dif));
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
