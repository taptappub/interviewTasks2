package sprint4;

/*
Гоша написал программу, которая сравнивает строки исключительно по их хешам. Если хеш равен, то и строки равны.
Тимофей увидел это безобразие и поручил вам сломать программу Гоши, чтобы остальным неповадно было.

В этой задаче вам надо будет лишь найти две различные строки, которые для заданной хеш-функции будут давать одинаковое значение.

Гоша использует следующую хеш-функцию:
Полиномиальный хеш считается по формуле: h(s) = (s1*a^(n-1) + s2*a^(n-2) + ... + sn-1 * a + sn) mod M

для a = 1000 и m = 123 987 123.
В данной задаче необходимо использовать в качестве значений отдельных символов их коды в таблице ASCII.
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class B {

    public static String generatingString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 15;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int a = 1000;
            int m = 123987123;

            Map<Long, String> map = new HashMap<>();

            while (true) {
                String string = generatingString();
                long hash = calcHash(a, m, string);
                String value = map.get(hash);

                if (value != null) {
                    writer.write(value);
                    writer.newLine();
                    writer.write(string);
//                    writer.newLine();
//                    writer.write(String.valueOf(hash));
                    return;
                } else {
                    map.put(hash, string);
                }
            }
        }
    }

    private static long calcHash(int a, int m, String s) {
        long sum = 0;
        char[] chars = s.toCharArray();

        for (char aChar : chars) {
            sum = (aChar + sum * a) % m;
        }
        return sum;
    }
}
