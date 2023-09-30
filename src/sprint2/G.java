package sprint2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class G {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            StackMax stackMax = new StackMax();

            for (int k = 0; k < n; k++) {

                List<String> rawAction = readList(reader);

                switch (rawAction.get(0)) {
                    case "get_max" :{
                        stackMax.get_max(writer);
                        break;
                    }
                    case "push":{
                        stackMax.push(Integer.parseInt(rawAction.get(1)));
                        break;
                    }
                    case "pop" : {
                        stackMax.pop(writer);
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

    public static class StackMax {

        List<Item> array;

        public StackMax() {
            this.array = new ArrayList<>();
        }

        public void push(int x) {
            if (!array.isEmpty()) {
                Integer max = Math.max(array.get(array.size() - 1).max, x);
                array.add(new Item(x, max));
            } else {
                array.add(new Item(x, x));
            }
        }

        public void pop(BufferedWriter writer) throws IOException {
            if (array.isEmpty()) {
                writer.write("error");
                writer.newLine();
                return;
            }

            array.remove(array.size() - 1);
        }

        public void get_max(BufferedWriter writer) throws IOException {
            if (array.isEmpty()) {
                writer.write("None");
                writer.newLine();
                return;
            }

            Integer max = array.get(array.size() - 1).max;
            writer.write(String.valueOf(max));
            writer.newLine();
        }

        private class Item {

            public Item(Integer value, Integer max) {
                this.value = value;
                this.max = max;
            }
            public Integer value;
            public Integer max;
        }
    }
}
