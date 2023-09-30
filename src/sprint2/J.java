package sprint2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class J {

//    get() — вывести элемент, находящийся в голове очереди, и удалить его. Если очередь пуста, то вывести «error».
//    put(x) — добавить число x в очередь
//    size() — вывести текущий размер очереди

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int m = readInt(reader);
            MyQueueSized myQueueSized = new MyQueueSized();

            for (int k = 0; k < m; k++) {
                List<String> rawAction = readList(reader);

                switch (rawAction.get(0)) {
                    case "size" :{
                        int size = myQueueSized.getSize();
                        writer.write(size + "");
                        writer.newLine();
                        break;
                    }
                    case "put":{
                        myQueueSized.put(Integer.parseInt(rawAction.get(1)));
                        break;
                    }
                    case "get" : {
                        try {
                            int value = myQueueSized.get();
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

    public static class MyQueueSized {

        private Item first = null;
        private Item last = null;
        private int size = 0;

        public MyQueueSized() {}

        public int getSize() {
            return size;
        }
        public void put(int x) {
            int newSize = size + 1;

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

        public int get() throws IllegalStateException {
            if (size == 0) {
                throw new IllegalStateException();
            }

            int value = last.value;
            removeLast();
            return value;
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

