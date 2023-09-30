package sprint2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class I {

//    push(x) — добавить число x в очередь;
//    pop() — удалить число из очереди и вывести на печать;
//    peek() — напечатать первое число в очереди;
//    size() — вернуть размер очереди;
//    При превышении допустимого размера очереди нужно вывести «error».
//    При вызове операций pop() или peek() для пустой очереди нужно вывести «None».

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int m = readInt(reader);
            int n = readInt(reader);
            MyQueueSized myQueueSized = new MyQueueSized(n);

            for (int k = 0; k < m; k++) {
                List<String> rawAction = readList(reader);

                switch (rawAction.get(0)) {
                    case "peek" :{
                        try {
                            int value = myQueueSized.peek();
                            writer.write(value + "");
                            writer.newLine();
                        } catch (IllegalStateException e) {
                            writer.write("None");
                            writer.newLine();
                        }
                        break;
                    }
                    case "size" :{
                        int size = myQueueSized.getSize();
                        writer.write(size + "");
                        writer.newLine();
                        break;
                    }
                    case "push":{
                        try {
                            myQueueSized.push(Integer.parseInt(rawAction.get(1)));
                        } catch (IllegalStateException e) {
                            writer.write("error");
                            writer.newLine();
                        }
                        break;
                    }
                    case "pop" : {
                        try {
                            int value = myQueueSized.pop();
                            writer.write(value + "");
                            writer.newLine();
                        } catch (IllegalStateException e) {
                            writer.write("None");
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

    public static class MyQueueSized {

        private Item first = null;
        private Item last = null;
        private int size = 0;
        private final int capacity;

        public MyQueueSized(int capacity) {
            this.capacity = capacity;
        }

        public int getSize() {
            return size;
        }
        public void push(int x) throws IllegalStateException {
            int newSize = size + 1;
            if (newSize > capacity) {
                throw new IllegalStateException();
            }

            if (size == 0) {
                first = new Item(x, null, null);
                last = first;
            } else {
                Item newItem = new Item(x, first, null);
                first.prev = newItem;
                first = newItem;
            }
            size = newSize;
        }

        private void removeLast() {
            last = last.prev;
            size--;
        }

        public int pop() throws IllegalStateException {
            if (size == 0) {
                throw new IllegalStateException();
            }

            int value = last.value;
            removeLast();
            return value;
        }

        public int peek() throws IllegalStateException {
            if (size == 0) {
                throw new IllegalStateException();
            }

            return last.value;
        }

        private class Item {

            public Item(Integer value, Item next, Item prev) {
                this.value = value;
                this.next = next;
                this.prev = prev;
            }
            public Integer value;
            public Item next;
            public Item prev;
        }
    }
}
