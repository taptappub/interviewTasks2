


import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class E {

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

            char[] arr = reader.readLine().toCharArray();
            int start = 0;
            int maxL = 0;

            while (true) {
                Pair res = fun(arr, start);

                if (res.curr == -1) {
                    writer.write(String.valueOf(Math.max(maxL, res.res)));
                    return;
                }
                start = res.curr + 1;
                maxL= Math.max(maxL, res.res);
            }
 /*              int maxLength = 0;
                int startInd = 0;
                int currl = 0;

                HashMap<Character, Integer> map = new HashMap<>();


                for (int i = 0; i < arr.length; i++) {

                    Integer curr = map.get(arr[i]);
                    if (curr == null) {
                        map.put(arr[i], i);
                        currl++;

                    } else {

                        maxLength = Math.max(maxLength, currl);

                        currl = i - curr;

                        startInd = curr;
                        map.remove(arr[i]);
                        map.put(arr[i], i);
                    }

                }
*/
            // writer.write(String.valueOf(Math.max(maxLength, currl)));


        }
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

    private static class Pair{
        public int res;
        public int curr;

        public Pair(int res, int curr) {
            this.res = res;
            this.curr = curr;
        }
    }
}
