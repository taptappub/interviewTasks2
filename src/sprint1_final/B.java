package sprint1_final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://contest.yandex.ru/contest/22450/run-report/82450001/
public class B {

    private static Integer getPointCount(int keysNumber, List<char[]> matrix) {
        int realKeysNumber = keysNumber * 2;// 2 мальчика
        int pointCounter = 0;

        int[] numbersMap = getNumbersMap(matrix);

        for (int t = 0; t < 10; t++) {
            if (numbersMap[t] > 0 && numbersMap[t] <= realKeysNumber) {
                pointCounter++;
            }
        }

        return pointCounter;
    }

    private static int[] getNumbersMap(List<char[]> matrix) {
        int[] pseudoNumberMap = new int[10];
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(0).length; j++) {
                char value = matrix.get(i)[j];
                if (Character.isDigit(value)) {
                    int intValue = Character.getNumericValue(value);
                    pseudoNumberMap[intValue] += 1;
                }
            }
        }
        return pseudoNumberMap;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int keysNumber = readInt(reader);
            List<char[]> matrix = readMatrix(reader, 4);
            Integer points = getPointCount(keysNumber, matrix);
            System.out.print(points);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static char[] readList(BufferedReader reader) throws IOException {
        return reader.readLine().toCharArray();
    }

    private static List<char[]> readMatrix(BufferedReader reader, int rowsCount) throws IOException {
        List<char[]> matrix = new ArrayList<>(rowsCount);
        for (int i = 0; i < rowsCount; i++) {
            matrix.add(readList(reader));
        }
        return matrix;
    }
}
