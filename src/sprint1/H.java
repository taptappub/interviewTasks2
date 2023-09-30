package sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class H {
    private static String sumOfBinaries(String a, String b) {
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();

        int counterA = aArray.length;
        int counterB = bArray.length;

        StringBuilder stringBuilder = new StringBuilder();
        int mind = 0;

        while (counterA != 0 || counterB != 0) {
            int valueA = 0;
            int valueB = 0;

            if (counterA - 1 >= 0) {
                valueA = Integer.parseInt(String.valueOf(aArray[counterA - 1]));
                counterA--;
            }

            if (counterB - 1 >= 0) {
                valueB = Integer.parseInt(String.valueOf(bArray[counterB - 1]));
                counterB--;
            }

            int sum = valueA + valueB + mind;
            mind = sum / 2;
            int value = sum % 2;
            stringBuilder.insert(0, value);

        }
        if (mind != 0) {
            stringBuilder.insert(0, mind);
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String a = reader.readLine();
            String b = reader.readLine();
            System.out.println(sumOfBinaries(a, b));
        }
    }
}
