package sprint8;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class E_string_inserting {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            /*
            //deque ab queue acaba stack
            abacaba
            3
            queue 2
            deque 0
            stack 7

            result:
            dequeabqueueacabastack

            */
            String mainString = reader.readLine();
            int n = readInt(reader);
            List<Pair> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] s = reader.readLine().split(" ");
                list.add(new Pair(s[0], Integer.parseInt(s[1])));
            }

            String result = makeString(mainString, list);
            writer.write(result);
            writer.newLine();
        }
    }

    private static String insert(String bag, String marble, int index) {
        String bagBegin = bag.substring(0,index);
        String bagEnd = bag.substring(index);
        return bagBegin + marble + bagEnd;
    }

    private static String makeString(String mainString, List<Pair> list) {
        StringBuilder sb = new StringBuilder(mainString);
        int shift = 0;

        list.sort(Comparator.comparingInt(o -> o.index));

//        String res = mainString;

        for (Pair pair : list) {
//            res = insert(res, pair.str, pair.index + shift);
            sb.insert(pair.index + shift, pair.str);
            shift += pair.str.length();
        }
//        return res;
        return sb.toString();
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static class Pair {
        public String str;
        public int index;

        public Pair(String str, int index) {
            this.str = str;
            this.index = index;
        }
    }
}
