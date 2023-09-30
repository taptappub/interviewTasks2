package sprint4;

/*
Алле очень понравился алгоритм вычисления полиномиального хеша. Помогите ей написать функцию, вычисляющую
хеш строки s. В данной задаче необходимо использовать в качестве значений отдельных символов их коды в таблице ASCII.

Полиномиальный хеш считается по формуле: h(s) = (s1*a^(n-1) + s2*a^(n-2) + ... + sn-1 * a + sn) mod M


Формат ввода
В первой строке дано число a (1 ≤ a ≤ 1000) –— основание, по которому считается хеш.
Во второй строке дано число m (1 ≤ m ≤ 109) –— модуль.
В третьей строке дана строка s (0 ≤ |s| ≤ 106), состоящая из больших и маленьких латинских букв.

Формат вывода
Выведите целое неотрицательное число –— хеш заданной строки.

Ввод
123
100003
a

Вывод
97
 */
import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int a = readInt(reader);
            int m = readInt(reader);
            String s = reader.readLine();

            long res = calcHash(a, m, s);
            writer.write(String.valueOf(res));
        }
    }

    //h(s) = (s1*a^(n-1) + s2*a^(n-2) + ... + sn-1 * a + sn) mod M
    //h("bcdefg")=((h("abcdef")−a~q5)⋅q+g~​)modR
    private static long calcHash(int a, int m, String s) {
        long sum = 0;
        char[] chars = s.toCharArray();

        for (char aChar : chars) {
            //temp =  (temp*a + arr[i]) % m;
            sum = (aChar + sum * a) % m;
        }
        return sum ;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
