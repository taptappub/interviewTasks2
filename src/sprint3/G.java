package sprint3;

/*
Рита решила оставить у себя одежду только трёх цветов: розового, жёлтого и малинового.
После того как вещи других расцветок были убраны, Рита захотела отсортировать свой новый гардероб по цветам.
Сначала должны идти вещи розового цвета, потом —– жёлтого, и в конце —– малинового.

Примечание: попробуйте решить задачу за один проход по массиву!

Формат ввода
В первой строке задано количество предметов в гардеробе: n –— оно не превосходит 1000000.
Во второй строке даётся массив, в котором указан цвет для каждого предмета.
Розовый цвет обозначен 0, жёлтый —– 1, малиновый –— 2.

Формат вывода
Нужно вывести в строку через пробел цвета предметов в правильном порядке.

Пример 1
ввод
7
0 2 1 2 0 0 1

вывод
0 0 0 1 1 2 2
 */
import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class G {

    private static int[] wardrobeCounter(int[] list) {
        int[] counter = new int[3];
        for (int i : list) {
            counter[i]++;
        }

        int[] result = new int[list.length];
        int resCounter = 0;
        for (int i = 0; i < counter.length; i++) {
            int count = counter[i];
            for (int j = 0; j < count; j++) {
                result[resCounter] = i;
                resCounter++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            if (n == 0) {
                return;
            }
            int[] list = readList(reader);

            int[] number = wardrobeCounter(list);
            writer.write(IntStream.of(number)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(" ")));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readList(BufferedReader reader) throws IOException {
        String[] s = reader.readLine().split(" ");
        int[] ints = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            ints[i] = Integer.parseInt(s[i]);
        }
        return ints;
    }
}
