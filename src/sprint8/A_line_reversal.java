package sprint8;

import java.io.*;

public class A_line_reversal {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String[] s = reader.readLine().split(" ");

            for (int i = s.length - 1; i >= 0; i--) {
                writer.write(s[i] + " ");
            }
            writer.newLine();
        }
    }
}
