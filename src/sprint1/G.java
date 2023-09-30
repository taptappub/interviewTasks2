package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class G {

    private static String getBinaryNumber(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        int currentNumber = n;

        if (currentNumber == 0) {
            return "0";
        }

        while (currentNumber != 0) {
            int d = currentNumber % 2;
            stringBuilder.insert(0, d);
            currentNumber = currentNumber / 2;
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            System.out.println(getBinaryNumber(n));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
