package sprint8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class L_prefix_func {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s = reader.readLine();

            List<Integer> result = getPrefixFuc(s);
            writer.write(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    private static List<Integer> getPrefixFuc(String s) {
        List<Integer> result = new ArrayList<>(s.length());
        result.add(0);

        for (int i = 1; i < s.length(); i++) {
            Integer k = result.get(i - 1);
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = result.get(k - 1);
            }
            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }
            result.add(i, k);
        }
        return result;
    }
}
