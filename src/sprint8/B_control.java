package sprint8;

import java.io.*;

public class B_control {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s1 = reader.readLine();
            String s2 = reader.readLine();

            boolean result = areEqual(s1, s2);
            writer.write(result ? "OK" : "FAIL");

            writer.newLine();
        }
    }

    private static boolean areEqual(String s1, String s2) {
        int i = 0;
        int j = 0;

        int errors = 0;

        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        }

//        if (s1.length() > s2.length()) {
//            j = 1;
//            errors = 1;
//        } else if (s2.length() > s1.length()) {
//            i = 1;
//            errors = 1;
//        }

//        abcdefg
//        abdefg

        while (i < s1.length() || j < s2.length()) {
            if (s1.charAt(i) != s2.charAt(j)) {
                errors++;
                if (errors > 1) {
                    return false;
                }

                if (s1.length() > s2.length()) {
                    i++;
                } else if (s2.length() > s1.length()) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            } else {
                i++;
                j++;
            }
        }

        return true;
    }
}
