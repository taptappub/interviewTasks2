package sprint2_final;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
-- ПРИНЦИП РАБОТЫ --
Дек реализован на основе массива, на кольцевом буффере.
Основная задумка в том, что при добавлении и удалении есть 2 состояния:
1) дек пуст
2) дек не пуст
Если дек пуст, то указатели на начало и конец при добавлении элемента не меняются, а если нет, то
алгоритм добавляет в соседнюю по кольцу ячейку. Проверять пересечение указателей слева (pointerLeft)
и справа (pointerRight) нет необходимости, т.к. в случае пересечения размер дека (size) будет больше
вместимости (capacity), и алгоритм вернет ошибку.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Дек реализован на основе массива, на кольцевом буффере.
Если дек пуст, то указатели на начало и конец при добавлении элемента не меняются, а если нет, то
алгоритм добавляет в соседнюю по кольцу ячейку. Проверять пересечение указателей слева (pointerLeft)
и справа (pointerRight) нет необходимости, т.к. в случае пересечения размер дека (size) будет больше
вместимости (capacity), и алгоритм вернет ошибку.
Исходя из описания кольцевого буфера, все операции выполняются за O(1).

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Т.к. при добавлении и удалении нет сдвига элементов массива и прочих манипуляций, а только рассчитываются
указатели pointerLeft и pointerRight за один проход, то сложность каждого метода в Дек равна O(1)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Дек основывается на массив длинной capacity, и максимально может быть заполнено размером size, где size <= n.
Поэтому дек будет потреблять O(n) памяти, где n = [0, capacity]
*/

//https://contest.yandex.ru/contest/22781/run-report/82596660/
public class A {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int k = readInt(reader);
            Dec dec = new Dec(k);

            for (int i = 0; i < n; i++) {

                List<String> rawAction = readList(reader);

                switch (rawAction.get(0)) {
                    case "push_front" :{
                        try {
                            int value = Integer.parseInt(rawAction.get(1));
                            dec.pushFront(value);
                        } catch (IllegalStateException e) {
                            writer.write("error");
                            writer.newLine();
                        }
                        break;
                    }
                    case "push_back":{
                        try {
                            int value = Integer.parseInt(rawAction.get(1));
                            dec.pushBack(value);
                        } catch (IllegalStateException e) {
                            writer.write("error");
                            writer.newLine();
                        }
                        break;
                    }
                    case "pop_front" : {
                        try {
                            int value = dec.popFront();
                            writer.write(value + "");
                            writer.newLine();
                        } catch (IllegalStateException e) {
                            writer.write("error");
                            writer.newLine();
                        }
                        break;
                    }
                    case "pop_back" : {
                        try {
                            int value = dec.popBack();
                            writer.write(value + "");
                            writer.newLine();
                        } catch (IllegalStateException e) {
                            writer.write("error");
                            writer.newLine();
                        }
                        break;
                    }
                }
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return new ArrayList<>(Arrays.asList(reader.readLine().split(" ")));
    }

    public static class Dec {

        private final int[] array;
        private int pointerLeft = 0;
        private int pointerRight = 0;
        private int size;
        private final int capacity;

        public Dec(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.array = new int[capacity];
        }

        /**
         * O(1).
         * Названия брались из задания https://contest.yandex.ru/contest/22781/problems/A/
         * ... Методы push_back(x), push_front(x), pop_back(), pop_front() работали корректно ...
         */
        public void pushBack(int value) {
            if (size == capacity) {
                throw new IllegalStateException();
            }

            if (size > 0) {
                pointerRight = (pointerRight + 1) % capacity;
            }
            array[pointerRight] = value;
            size++;
        }

        /**
         * O(1)
         */
        public void pushFront(int value) {
            if (size == capacity) {
                throw new IllegalStateException();
            }

            if (size > 0) {
                if (pointerLeft - 1 < 0) {
                    pointerLeft = capacity - 1;
                } else {
                    pointerLeft = pointerLeft - 1;
                }
            }
            array[pointerLeft] = value;
            size++;
        }

        /**
         * O(1)
         */
        public int popFront() {
            if (size == 0) {
                throw new IllegalStateException();
            }

            int value = array[pointerLeft];
            size--;

            if (size != 0) {
                pointerLeft = (pointerLeft + 1) % capacity;
            }
            return value;
        }

        /**
         * O(1)
         */
        public int popBack() {
            if (size == 0) {
                throw new IllegalStateException();
            }

            int value = array[pointerRight];
            size--;

            if (size != 0) {
                if (pointerRight - 1 < 0) {
                    pointerRight = capacity - 1;
                } else {
                    pointerRight = pointerRight - 1;
                }
            }

            return value;
        }
    }
}

/*
14
3
push_back 1
push_front 2
push_front 3
push_front 4
pop_front
pop_front
pop_front
push_back 5
push_front 6
push_front 7
pop_back
pop_back
pop_back
pop_back
 */