package sprint3_final;


import java.io.*;
import java.util.Random;
/*
-- ПРИНЦИП РАБОТЫ --
Quicksort, но с перестановкой элементов вокруг Pivot'a, которая реализована движением двух указателей: слева и справа массива.
Также, из-за того, что сравниваются объекты, а не числа, у класса Participant добавлен интерфейс Comparable

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Алгоритм сортировки работает как Quicksort с сортировкой в исходном массиве.
Сам Quicksort реализован с использованием рекурсии, которая завершается, когда встречаются левая и правая границы.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Сложность алгоритма O(NlogN). В худшем случае O(N^2), если pivot всегда самый большой элемент на сортируемом отрезке отрезке.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм осуществляется на входном массиве и дополнительных структур данных не требует.
Следовательно памяти потребляется О(1), но из-за того что применяется рекурсия, будет затрачена дополнительная системная память
O(logN), где N - количество рекурсивных вызовов.
*/
//https://contest.yandex.ru/contest/23815/run-report/83152897/
public class B {

    static class Participant implements Comparable<Participant> {
        private final String name;
        private final Integer p;
        private final Integer f;
        public Participant(String name, int p, int f) {
            this.name = name;
            this.p = p;
            this.f = f;
        }

        public String getName() {
            return name;
        }

        public Integer getF() {
            return f;
        }

        public Integer getP() {
            return p;
        }

        /*
        Тимофей решил сортировать таблицу результатов следующим образом: при сравнении двух участников выше
        будет идти тот, у которого решено больше задач. При равенстве числа решённых задач первым идёт
        участник с меньшим штрафом. Если же и штрафы совпадают, то первым будет тот, у которого логин идёт
        раньше в алфавитном (лексикографическом) порядке.
         */

        @Override
        public int compareTo(Participant participant2) {
            int comparedByP = Integer.compare(participant2.getP(), this.p);
            if (comparedByP != 0) {
                return comparedByP;
            }

            int comparedByF = -Integer.compare(participant2.getF(), this.f);
            if (comparedByF != 0) {
                return comparedByF;
            }

            return String.CASE_INSENSITIVE_ORDER.compare(this.name, participant2.getName());
        }
    }

    private static void quickSort(Participant[] data, int l, int r) {
        if (l > r) {
            return;
        }
        int pivot = getPivot(l, r);
        int left = l;
        int right = r;
        while (left <= right) {
            if (data[left].compareTo(data[pivot]) >= 0) {
                if (data[right].compareTo(data[pivot]) <= 0) {
                    swap(data, left, right);
                    left++;
                }
                right--;
            } else {
                left++;
            }
        }
        quickSort(data, l, right);
        quickSort(data, left, r);
    }

    private static void quickSort(Participant[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private static void swap(Participant[] data, int left, int right) {
        Participant temp = data[left];
        data[left] = data[right];
        data[right] = temp;
    }

    private static int getPivot(int l, int r) {
        return r;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            Participant[] data = readMatrix(reader, n);
            quickSort(data);
            for (Participant participant : data) {
                writer.write(participant.name);
                writer.newLine();
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static Participant readList(BufferedReader reader) throws IOException {
        String[] list = reader.readLine().split(" ");
        return new Participant(list[0], Integer.parseInt(list[1]), Integer.parseInt(list[2]));
    }

    private static Participant[] readMatrix(BufferedReader reader, int rowsCount) throws IOException {
        Participant[] matrix = new Participant[rowsCount];
        for (int i = 0; i < rowsCount; i++) {
            matrix[i] = readList(reader);
        }
        return matrix;
    }
}
