package sprint3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
На клавиатуре старых мобильных телефонов каждой цифре соответствовало несколько букв. Примерно так:

2:'abc',
3:'def',
4:'ghi',
5:'jkl',
6:'mno',
7:'pqrs',
8:'tuv',
9:'wxyz'

Вам известно в каком порядке были нажаты кнопки телефона, без учета повторов. Напечатайте все комбинации букв, которые можно набрать такой последовательностью нажатий.

Ввод
23

Вывод
ad ae af bd be bf cd ce cf
 */
public class B {
    
    private static List<String> getCombinations(char[] numbers, String[] buttons) {
        int count = numbers.length;
        List<String> result = new ArrayList<>();
        getCombinations(result, count, "", numbers, buttons);
        return result;
    }

    private static void getCombinations(List<String> result, int counter, String str, char[] numbers, String[] buttons) {
        if (counter == 0) {
            result.add(str);
            return;
        }

        int number = Integer.parseInt(String.valueOf(numbers[numbers.length - counter]));
        for (char ch : buttons[number].toCharArray()) {
            String newStr = str + ch;
            getCombinations(result, counter - 1, newStr, numbers, buttons);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            char[] line = reader.readLine().toCharArray();

            String[] buttons = new String[10];
            buttons[2] = "abc";
            buttons[3] = "def";
            buttons[4] = "ghi";
            buttons[5] = "jkl";
            buttons[6] = "mno";
            buttons[7] = "pqrs";
            buttons[8] = "tuv";
            buttons[9] = "wxyz";

            List<String> result = getCombinations(line, buttons);
            String collect = result.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));

            writer.write(collect);
        }
    }
}
