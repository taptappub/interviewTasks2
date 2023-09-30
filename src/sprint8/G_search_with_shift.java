package sprint8;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class G_search_with_shift {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] list1 = readList(reader);
            int m = readInt(reader);
            int[] list2 = readList(reader);

            List<Integer> result = findSubStringStartIndexes(list1, list2);
            writer.write(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    /*
    9
    3 9 1 2 5 10 9 1 7
    2
    4 10

    return:
    1 8
     */
    private static List<Integer> findSubStringStartIndexes(int[] list1, int[] list2) {
        List<Integer> result = new ArrayList<>();
        if (list2.length == 1) {
            for (int i = 0; i < list1.length; i++) {
                result.add(i + 1);
            }
            return result;
        }

        for (int i = 0; i < list1.length - list2.length + 1; i++) {
            int[] currentSubArray = Arrays.copyOfRange(list1, i, i + list2.length);
            if (equalsWithShift(currentSubArray, list2)) {
                result.add(i + 1);
            }
        }
        return result;
    }

    private static boolean equalsWithShift(int[] currentSubArray, int[] list2) {
        int shift = 0;
        for (int i = 0; i < currentSubArray.length; i++) {
            if (i == 0) {
                shift = currentSubArray[i] - list2[i];
            } else {
                if (currentSubArray[i] - list2[i] - shift != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readList(BufferedReader reader) throws IOException {
        String[] s = reader.readLine().split(" ");
        int[] ints = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            ints[i] = Integer.parseInt(s[i]);
        }
        return ints;
    }
}
