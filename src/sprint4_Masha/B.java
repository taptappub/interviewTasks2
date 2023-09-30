package com.marpozh.chapter_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;

public class B {

    public static int a = 1000;
    public static int m = 123_987_123;

    static long compute_hash3(String s, int a, int m) {
        char[] arr = s.toCharArray();
        long temp = 0;
        if (arr.length == 1) {
            return arr[0] % m;
        }

        temp = 0;  //arr[arr.length-1];

        for (int i = 0; i < arr.length; i++) {

            temp = (temp * a + arr[i]) % m;
        }
        return temp;
    }

    public static String generatingRandomAlphabeticString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 15;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }

    public static void main(String[] args) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            // String generatedString = generatingRandomAlphabeticString();
            //  long hash1 = compute_hash3(generatedString, a, m);
            Map<Long, String> map = new HashMap<>();

            while (true) {

                String generatedString2 = generatingRandomAlphabeticString();
                long hash = compute_hash3(generatedString2, a, m);

                String value = map.get(hash);

                if (value != null) {

                    writer.write(value);
                    writer.newLine();
                    writer.write(generatedString2);
                    writer.newLine();
                    writer.write(String.valueOf(hash));
                    return;
                } else {
                    map.put(hash, generatedString2);
                }


            }


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

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

}
