
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class H {


    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            char[] a = reader.readLine().toCharArray();
            char[] b = reader.readLine().toCharArray();

            Map<Character, Character> aToB = new HashMap<>();
            Map<Character, Character> bToA = new HashMap<>();

            if (a.length != b.length){
                writer.write("NO");
                return;
            }

            for (int i = 0; i < a.length; i++) {

                Character expetedB = aToB.get(a[i]);
                if (expetedB == null) {
                    aToB.put(a[i], b[i]);
                } else {
                    if (expetedB.compareTo(b[i]) != 0) {
                        writer.write("NO");
                        return;
                    }
                }

             Character expectedA = bToA.get( b[i]);

                if (expectedA == null) {
                    bToA.put( b[i], a[i]);
                } else {
                    if (expectedA.compareTo(a[i]) != 0) {
                        writer.write("NO");
                        return;
                    }
                }

            }
            writer.write("YES");
            //   writer.write(String.valueOf(findMaxLength(games)));
        }
    }

    private static boolean findInArray(String[] arr, int n, String line) {

        for (int k = 0; k < n; k++) {
            if (arr[k].equals(line)) {
                return true;
            }
        }
        return false;
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .mapToInt(i -> Integer.parseInt(i)).toArray();
    }

}
