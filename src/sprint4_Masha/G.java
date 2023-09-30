
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class G {

 static    public int findMaxLength(int[] nums) {

        Map < Integer, Integer > map = new HashMap < > ();
        map.put(0, -1);

        int maxlen = 0, count = 0;

        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);

            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            if (n==0){
                writer.write("0");
                return;
            }
            if (n==1){
                writer.write("0");
                return;
            }

            int[] games = readList(reader);

            writer.write(String.valueOf(findMaxLength(games)));
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
