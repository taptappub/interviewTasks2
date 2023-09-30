

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A {
    static long compute_hash3(String s, int a, int m) {
// 104 97 115 104
        char[] arr = s.toCharArray();
        long temp = 0;
        if (arr.length == 1) {
            return arr[0] % m;
        }

        temp = 0;  //arr[arr.length-1];

        for (int i = 0; i < arr.length; i++) {

            temp =  (temp*a + arr[i]) % m;
        }
        return temp;
    }

    static long compute_hash2(String s, int a, int m) {
// 104 97 115 104
        char[] arr = s.toCharArray();
        long temp = 0;
        if (arr.length == 1) {
            return arr[0] % m;
        }

        temp = 0;  //arr[arr.length-1];
        int aa = 1;
        for (int i = arr.length-1; i >= 0; i--) {

            temp =  temp + arr[i]*aa;
            aa = aa * a;
              // a[n] + a[n-1]*a, 104 + 115*123 + 97*123*123 + 104**123*123*123
        }
        return temp % m;
    }

    static int compute_hash1(String s, int a, int m) {

        char[] arr = s.toCharArray();
        int temp = 0;

        for (int i = 0; i < arr.length; i++) {

            temp = (temp * a + arr[i]) % m;
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int a = readInt(reader);
            int m = readInt(reader);
            String arr = reader.readLine();

            writer.write(String.valueOf(compute_hash3(arr, a, m)));

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
