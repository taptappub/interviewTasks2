package sprint7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class B {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            List<Pair> pairQueue = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String[] s = reader.readLine().split(" ");
                Pair pair = new Pair(Float.parseFloat(s[0]), Float.parseFloat(s[1]));
                pairQueue.add(pair);
            }

            List<Pair> result = getSchedule(n, pairQueue);

            writer.write(String.valueOf(result.size()));
            writer.newLine();
            for (Pair pair : result) {
                writer.write(pair.start + " " + pair.end);
                writer.newLine();
            }
        }
    }

    private static List<Pair> getSchedule(int n, List<Pair> pairPriorityQueue) {
        List<Pair> result = new ArrayList<>();

        pairPriorityQueue.sort((o1, o2) -> {
            if (o1.end - o2.end != 0) {
                return Float.compare(o1.end, o2.end);
            } else {
                return Float.compare(o1.start, o2.start);
            }
        });

        //найти, который раньше всех заканчивается
        Pair firstData = pairPriorityQueue.get(0);
        result.add(firstData);
        pairPriorityQueue.remove(0);

        while (!pairPriorityQueue.isEmpty()) {
            Pair nextData = pairPriorityQueue.get(0);
            pairPriorityQueue.remove(0);
            if (nextData.start >= firstData.end) {
                firstData = nextData;
                result.add(firstData);
            }
        }

        //найти, который раньше всех заканчивается


        return result;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static class Pair {
        float start;
        float end;

        public Pair(float start, float end) {
            this.start = start;
            this.end = end;
        }
    }
}
