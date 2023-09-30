package sprint2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class H {

    /**
     * Будем придерживаться такого определения:
     * <p>
     * пустая строка —– правильная скобочная последовательность;
     * правильная скобочная последовательность, взятая в скобки одного типа, –— правильная скобочная последовательность;
     * правильная скобочная последовательность с приписанной слева или справа правильной скобочной последовательностью —– тоже правильная.
     * На вход подаётся последовательность из скобок трёх видов: [], (), {}.
     */
    public static Boolean solution(char[] array) {
        Stack<Character> stack = new Stack<>();
        for (char currentBracket : array) {
            switch (currentBracket) {
                case '[':
                case '{':
                case '(': {
                    stack.push(currentBracket);
                    break;
                }
                case ']': {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char popped = stack.pop();
                    if (popped != '[') {
                        return false;
                    }
                    break;
                }
                case '}': {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char popped = stack.pop();
                    if (popped != '{') {
                        return false;
                    }
                    break;
                }
                case ')': {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char popped = stack.pop();
                    if (popped != '(') {
                        return false;
                    }
                    break;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            char[] array = reader.readLine().toCharArray();
            Boolean result = solution(array);
            if (result) {
                writer.write("True");
            } else {
                writer.write("False");
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readList(BufferedReader reader) throws IOException {

        return new ArrayList<>(Arrays.asList(reader.readLine().split(" ")));
    }

}