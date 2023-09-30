package sprint1;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class F {

    private static boolean isPalindrome(String text) {
        String cleartext = text.replaceAll("[^a-zA-Z0-9]", "");
        char[] chars = cleartext.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length/2; i++) {
            int j = chars.length - 1 - i;

            if (chars[i] != chars[j]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException{
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine();
            if (isPalindrome(text)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }
}
