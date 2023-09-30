package sprint3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
A. Генератор скобок
Язык	Ограничение времени	Ограничение памяти	Ввод	Вывод
Все языки	1 секунда	64Mb	стандартный ввод или input.txt	стандартный вывод или output.txt
C# (MS .Net 5.0)+ASP	1.5 секунд	64Mb
Oracle Java 8	1.5 секунд	64Mb
OpenJDK Java 11	1.5 секунд	64Mb
Рита по поручению Тимофея наводит порядок в правильных скобочных последовательностях (ПСП), состоящих только из круглых скобок (). Для этого ей надо сгенерировать все ПСП длины 2n в алфавитном порядке —– алфавит состоит из ( и ) и открывающая скобка идёт раньше закрывающей.

Помогите Рите —– напишите программу, которая по заданному n выведет все ПСП в нужном порядке.

Рассмотрим второй пример. Надо вывести ПСП из четырёх символов. Таких всего две:

(())
()()
(()) идёт раньше ()(), так как первый символ у них одинаковый, а на второй позиции у первой ПСП стоит (, который идёт раньше ).
Формат ввода
На вход функция принимает n — целое число от 0 до 10.

Формат вывода
Функция должна напечатать все возможные скобочные последовательности заданной длины в алфавитном (лексикографическом) порядке.

Пример 1
ввод 3

вывод
((()))
(()())
(())()
()(())
()()()
 */
public class A {

    private static List<String> bracketGenerator(int n) {
        List<String> result = new ArrayList<>();
        bracketGenerator(result, "(", n - 1, n);
        return result;
    }

    private static void bracketGenerator(List<String> result, String str, int lb, int rb) {
        if (lb > 0) {
            bracketGenerator(result, str + "(", lb - 1, rb);
        }
        if (rb > 0 && rb != lb) {
            bracketGenerator(result, str + ")", lb, rb - 1);
        }
        if (lb == 0 && rb == 0) {
            result.add(str);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);

            List<String> result = bracketGenerator(n);

            for (String element : result) {
                writer.write(element);
                writer.newLine();
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
