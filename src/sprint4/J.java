package sprint4;

/*
У Гоши есть любимое число S. Помогите ему найти все уникальные четвёрки чисел в массиве, которые в сумме дают заданное число S.

Формат ввода
В первой строке дано общее количество элементов массива n (0 ≤ n ≤ 1000).

Во второй строке дано целое число S  .

В третьей строке задан сам массив. Каждое число является целым и не превосходит по модулю 109.

Формат вывода
В первой строке выведите количество найденных четвёрок чисел.

В последующих строках выведите найденные четвёрки. Числа внутри одной четверки должны быть упорядочены по возрастанию. Между собой четвёрки упорядочены лексикографически.

Пример 1
Ввод:
8
10
2 3 2 4 1 10 3 0

Вывод:
3
0 3 3 4
1 2 3 4
2 2 3 3

 */

import java.io.*;
import java.util.*;
public class J {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int s = readInt(reader);
            try {
                int[] list = readList(reader);

                ArrayList<Four> matrix = fourSum(list, s);

                writer.write(String.valueOf(matrix.size()));
                writer.newLine();
                for (Four str : matrix) {
                    writer.write(str.toString());
                    writer.newLine();
                }
            } catch (Exception e) {
                writer.write("0");
            }
        }
    }

    private static ArrayList<Four> fourSum(int[] list, int s) {
        HashSet<Four> result = new HashSet<>();
        HashMap<Integer, Boolean> hashMap = new HashMap<>();

        int[] preFour = new int[4];

        for (int i = 0; i < list.length - 2; i++) {
            for (int j = i + 1; j < list.length - 1; j++) {
                for (int k = j + 1; k < list.length; k++) {
                    if (hashMap.containsKey(s - list[i] - list[j] - list[k])) {
                        preFour[0] = s - list[i] - list[j] - list[k];
                        preFour[1] = list[i];
                        preFour[2] = list[j];
                        preFour[3] = list[k];
                        Arrays.sort(preFour);
                        result.add(new Four(preFour[0], preFour[1], preFour[2], preFour[3]));
                    }
                }
            }
            hashMap.put(list[i], true);
        }

        ArrayList<Four> resultList = new ArrayList<>(result);
        resultList.sort(Four::compareTo);

        return resultList;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readList(BufferedReader reader) throws Exception {
        String[] s = reader.readLine().split(" ");
        int[] ints = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            ints[i] = Integer.parseInt(s[i]);
        }
        return ints;
    }

    static class Four implements Comparable {
        public int a;
        public int b;
        public int c;
        public int d;

        public Four(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Override
        public String toString() {
            return a + " " + b + " " + c + " " + d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Four four = (Four) o;
            return a == four.a && b == four.b && c == four.c && d == four.d;
        }

        @Override
        public int compareTo(Object o) {
            Four four = (Four) o;
            if (a != four.a) {
                return Integer.compare(a, four.a);
            }
            if (b != four.b) {
                return Integer.compare(b, four.b);
            }
            if (c != four.c) {
                return Integer.compare(c, four.c);
            }
            return Integer.compare(d, four.d);
        }
    }
}
