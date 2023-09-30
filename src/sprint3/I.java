package sprint3;

/*
На IT-конференции присутствовали студенты из разных вузов со всей страны. Для каждого студента известен
ID университета, в котором он учится.

Тимофей предложил Рите выяснить, из каких k вузов на конференцию пришло больше всего учащихся.

Формат ввода
В первой строке дано количество студентов в списке —– n (1 ≤ n ≤ 15 000).

Во второй строке через пробел записаны n целых чисел —– ID вуза каждого студента.
Каждое из чисел находится в диапазоне от 0 до 10 000.

В третьей строке записано одно число k.

Формат вывода
Выведите через пробел k ID вузов с максимальным числом участников.
Они должны быть отсортированы по убыванию популярности (по количеству гостей от конкретного вуза).
Если более одного вуза имеет одно и то же количество учащихся, то выводить их ID нужно в порядке возрастания.

Ввод
7
1 2 3 1 2 3 4
3

Вывод
1 2 3
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class I {

    static class Univer {
        int id;
        int count = 0;

        public Univer(int id, int count) {
            this.id = id;
            this.count = count;
        }
    }

    private static List<Integer> getUnivercities(int[] ids, int k) {
        int[] indexes = toIndexes(ids);
        List<Univer> univerList = new ArrayList<>();
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i] > 0) {
                Univer univer = new Univer(i, indexes[i]);
                univerList.add(univer);
            }
        }

        univerList.sort((o1, o2) -> o2.count - o1.count);

        List<Integer> collect = univerList.subList(0, k)
                .stream()
                .map(univer -> univer.id)
                .collect(Collectors.toList());

        return collect;
    }

    private static int[] toIndexes(int[] ids) {
        int[] indexes = new int[10001];
        for (int id : ids) {
            indexes[id]++;
        }
        return indexes;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] ids = readList(reader);
            int k = readInt(reader);

            List<Integer> bestIds = getUnivercities(ids, k);
            writer.write(bestIds.stream().map(Object::toString)
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
