package sprint1_final;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// https://contest.yandex.ru/contest/22450/run-report/82337308/
public class A {

    private static int[] getNearestEmptyPlace(List<Integer> houses) {
        int[] distance = new int[houses.size()];

        int leftPointer = 0;
        int leftCounter = 0;

        int rightPointer = houses.size() - 1; // можно высчитывать из leftCounter
        int rightCounter = 0;

        while (leftPointer < houses.size() || rightPointer >= 0) {
            if (houses.get(leftPointer) == 0) {
                distance[leftPointer] = 0;
                leftCounter = 1;
            } else if (leftCounter == 0) {
                // пропускаем элемент
            } else {
                if (distance[leftPointer] == 0 || distance[leftPointer] > leftCounter) {
                    distance[leftPointer] = leftCounter;
                    leftCounter++;
                }
            }
            leftPointer++;

            if (houses.get(rightPointer) == 0) {
                distance[rightPointer] = 0;
                rightCounter = 1;
            } else if (rightCounter == 0) {
                // пропускаем элемент
            } else {
                if (distance[rightPointer] == 0 || distance[rightPointer] > rightCounter) {
                    distance[rightPointer] = rightCounter;
                    rightCounter++;
                }
            }
            rightPointer--;
        }

        return distance;
    }

    public static void main(String[] args) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int number = readInt(reader);
            List<Integer> houses = readList(reader);
            int[] distance = getNearestEmptyPlace(houses);

            printList(distance, writer);
        }
    }

    private static <T> void printList(int[] list, Writer writer) throws IOException {
        for (int element : list) {
            writer.write(String.valueOf(element));
            writer.write(" ");
        }
    }

    // Код взят из https://github.com/Yandex-Practicum/algorithms-templates/blob/main/java/sprint1_nonfinals/I.java
    // Я понимаю, что этот код может быть избыточным, но оставлю ваш ворнинг, т.к. хочу сохранить единообразие в вводе и выводе данных.
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

