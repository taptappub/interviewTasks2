package sprint8;

import java.io.*;

public class K_compare_2_strings {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s1 = reader.readLine();
            String s2 = reader.readLine();

            int result = compareStrings(s1, s2);
            writer.write(result + "");
            writer.newLine();
        }
    }

    private static int compareStrings(String s1, String s2) {
        String newS1 = removeSymbols(s1);
        String newS2 = removeSymbols(s2);

        int res = newS1.compareTo(newS2);

        return Integer.compare(res, 0);
    }

    private static String removeSymbols(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if ((c - 'a' - 1) % 2 == 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
