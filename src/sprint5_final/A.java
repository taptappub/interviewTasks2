package sprint5_final;

import java.io.*;

/*
-- ПРИНЦИП РАБОТЫ --
Алгоритм сортировки массива кучей (heapSort). В идее алгоритма лежит создание "кучи" из входного массива, а именно
такого бинарного дерева, чтобы дети узла были всегда меньше родителя. Это достигается проходом по дереву и сравнения
текущего узла и его детей, если хотя бы один из детей больше, то выбирается максимальный из них и меняется местами с
родительским, а алгоритм рекурсивно вызывается уже для поддерева с бывшим максимальным узлом.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Из принципа работы следует, что в корне кучи будет всегда максимальный элемент.
Доставая такой элемент, требуется еще N раз вызвать siftDown для оставшегося массива, чтобы каждый раз доставать максимальный элемент.
За основу взята статья https://habr.com/ru/companies/otus/articles/460087/, а также задание L из практических заданий спринта.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Метод heapSort состоит из 2 циклов:
 - Наполнение кучи за O((N/2)logN)
 - Получение элементов - O(NlogN)
Следовательно общая сложность O(NlogN)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм осуществляется на входном массиве и дополнительных структур данных не требует.
Следовательно памяти потребляется О(1), но из-за того что применяется рекурсия, будет затрачена дополнительная системная память
O(logN), где N - количество рекурсивных вызовов.
*/
//https://contest.yandex.ru/contest/24810/run-report/86118031/

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            Participant[] data = readMatrix(reader, n);
            heapsort(data);

            for (Participant participant : data) {
                writer.write(participant.name);
                writer.newLine();
            }
        }
    }

    //O(NlogN)
    private static void heapsort(Participant[] data) {
        for (int i = data.length / 2 - 1; i >= 0; i--) {
            siftDown(data, data.length, i);
        }

        for (int i = data.length - 1; i >= 0; i--) {
            swap(data, 0, i);
            siftDown(data, i, 0);
        }
    }

    private static void swap(Participant[] data, int x, int i) {
        Participant temp = data[x];
        data[x] = data[i];
        data[i] = temp;
    }

    public static void siftDown(Participant[] heap, int n, int idx) {
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        while (n > left) {
            int indexLargest;
            if (right < n && heap[left].compareTo(heap[right]) < 0) {
                indexLargest = right;
            } else {
                indexLargest = left;
            }

            if (heap[idx].compareTo(heap[indexLargest]) > 0) {
                break;
            }
            swap(heap, indexLargest, idx);
            idx = indexLargest;

            left = 2 * idx + 1;
            right = 2 * idx + 2;
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

    static class Participant implements Comparable<Participant> {
        private final String name;
        private final int solvedTasks;
        private final int fine;

        public Participant(String name, int solvedTasks, int fine) {
            this.name = name;
            this.solvedTasks = solvedTasks;
            this.fine = fine;
        }

        public String getName() {
            return name;
        }

        public int getFine() {
            return fine;
        }

        public int getSolvedTasks() {
            return solvedTasks;
        }

        /*
        Тимофей решил сортировать таблицу результатов следующим образом: при сравнении двух участников выше будет
        идти тот, у которого решено больше задач. При равенстве числа решённых задач первым идёт участник с меньшим штрафом.
        Если же и штрафы совпадают, то первым будет тот, у которого логин идёт раньше в алфавитном (лексикографическом) порядке.
         */
        @Override
        public int compareTo(Participant participant2) {
            int comparedBySolvedTasks = Integer.compare(participant2.getSolvedTasks(), this.getSolvedTasks());
            if (comparedBySolvedTasks != 0) {
                return comparedBySolvedTasks;
            }

            int comparedByFine = -Integer.compare(participant2.getFine(), this.getFine());
            if (comparedByFine != 0) {
                return comparedByFine;
            }

            return this.getName().compareTo(participant2.getName());
        }
    }
}
