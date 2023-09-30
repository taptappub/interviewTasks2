package sprint3;

/*
Алла захотела, чтобы у неё под окном были узкие клумбы с тюльпанам. На схеме земельного участка клумбы обозначаются
просто горизонтальными отрезками, лежащими на одной прямой. Для ландшафтных работ было нанято n садовников. Каждый
из них обрабатывал какой-то отрезок на схеме. Процесс был организован не очень хорошо, иногда один и тот же отрезок
или его часть могли быть обработаны сразу несколькими садовниками. Таким образом, отрезки, обрабатываемые двумя
разными садовниками, сливаются в один. Непрерывный обработанный отрезок затем станет клумбой.
Нужно определить границы будущих клумб.
Рассмотрим примеры.
Пример 1:
Два одинаковых отрезка [7,8] и [7,8] сливаются в один, но потом их накрывает отрезок [6,10].
Таким образом, имеем две клумбы с координатами [2,3] и [6,10].

Формат вывода
Нужно вывести координаты каждой из получившихся клумб в отдельных строках.
Данные должны выводится в отсортированном порядке —– сначала клумбы с меньшими координатами, затем —– с бОльшими.
Пример 1

ввод
4

1 2
3 4

5 6
7 8

9 10
11 12

13 14
14 15

-----
1 2
7 8
2 3
6 10
-----
2 3
6 10

вывод
2 3
6 10
 */


/*
     17481834
    1748 1834
   17 48 18 34
  1 7 4 8 1 8 3 4

  17 48 18 34
   1478 1348
   11344788
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class N {

    private static List<int[]> getIntervals(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);

        List<int[]> list = new ArrayList<>();
        int[] curr = intervals[0];

        for (int[] next : intervals) {
            if (curr[1] >= next[0]) {
                curr = new int[]{curr[0], Math.max(curr[1], next[1])};
            } else {
                list.add(curr);
                curr = next;
            }
        }
        list.add(curr);
        return list;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int rowsCount = readInt(reader);
            int[][] matrix = readMatrix(reader, rowsCount);
            List<int[]> intervals = getIntervals(matrix);

            for (int[] element : intervals) {
                writer.write(IntStream.of(element)
                        .mapToObj(Integer::toString)
                        .collect(Collectors.joining(" ")));
                writer.newLine();
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readList(BufferedReader reader) throws IOException {
        String[] s = reader.readLine().split(" ");
        int[] ret = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            ret[i] = Integer.parseInt(s[i]);
        }
        return ret;
    }

    private static int[][] readMatrix(BufferedReader reader, int rowsCount) throws IOException {
        int[][] matrix = new int[rowsCount][2];
        for (int i = 0; i < rowsCount; i++) {
            matrix[i] = readList(reader);
        }
        return matrix;
    }
}
