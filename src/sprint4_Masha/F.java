


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class F {

    // abc bdfea

    private static Pair fun(char[] arr, int start) {
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;

        for (int i = start; i < arr.length; i++) {

            Integer curr = map.get(arr[i]);
            if (curr == null) {
                map.put(arr[i], i);
                res++;
            } else {
                return new Pair(res, curr);
            }
        }
        return new Pair(res, -1);
    }

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            String[] words = reader.readLine().split(" ");

            HashMap<String, List<Integer>> map = new HashMap<>();

            for (int i = 0; i < words.length; i++) {

                String word = words[i];
                char[] wordArr = word.toCharArray();
                Arrays.sort(wordArr);
                String sortWord = new String(wordArr);

                List<Integer> val = map.get(sortWord);
                if (val == null) {

                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(sortWord, list);
                } else {
                    val.add(i);
                }
            }

            List<String> ss = map.values().stream()
                  
                    .sorted(Comparator.comparing(o -> o.get(0)))
                    .map(F::asString)
                    .collect(Collectors.toList());

            for (int i = 0; i < ss.size(); i++) {

                writer.write(ss.get(i));
                writer.newLine();
            }

        }
    }

    private static String asString(List<Integer> list) {

        StringBuilder b = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {

            b.append(list.get(i));
            b.append(" ");
        }
        return b.toString();
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

    private static int[] readList2(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
    }

    private static class Pair {
        public int res;
        public int curr;

        public Pair(int res, int curr) {
            this.res = res;
            this.curr = curr;
        }
    }
}
