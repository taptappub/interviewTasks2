package sprint7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class C {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int capacity = readInt(reader);
            int n = readInt(reader);
            List<Pair> pairQueue = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String[] s = reader.readLine().split(" ");
                Pair pair = new Pair(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
                pairQueue.add(pair);
            }

            long result = getMaxPrice(capacity, pairQueue);

            writer.write(result + "");
            writer.newLine();
        }
    }

    private static long getMaxPrice(int capacity, List<Pair> data) {
        long price = 0;
        int currentCapacity = 0;
        data.sort((o1, o2) -> o2.start - o1.start);

        for (Pair pair : data) {
            if (currentCapacity + pair.end <= capacity) {
                price += (long) pair.start * pair.end;
                currentCapacity += pair.end;
            } else {
                price += (long) (capacity - currentCapacity) * pair.start;
                break;
            }
        }

        return price;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    static class Pair {
        Integer start;
        Integer end;

        public Pair(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }
}
