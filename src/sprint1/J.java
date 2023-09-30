package sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class J {

    private static List<Integer> factorize(int N) {
        List<Integer> result = new ArrayList<>();
        byte[] isNotPrime = new byte[N + 1];

        int value = N;
        int number = 2;
        while (value != 1) {
            if (value % number == 0) {
                result.add(number);
                value = value / number;
            } else {
                number = getNextPrimeNumber(number, value, isNotPrime);
            }
        }


        return result;
    }

    private static int getNextPrimeNumber(int number, int value, byte[] isNotPrime) {
        for (int i = number; i * i < value; i++) {
            if (isNotPrime[i] == 0) {
                for (int j = i * i; j < value; j += i) {
                    isNotPrime[j] = 1;
                }
            }
        }

        for (int i = number + 1; i < isNotPrime.length && i < value; i++) {
            if (isNotPrime[i] == 0) {
                return i;
            }
        }
        return value;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<Integer> factorization = factorize(n);
            for (int elem : factorization) {
                writer.write(elem + " ");
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
