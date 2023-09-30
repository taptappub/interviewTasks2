package sprint0;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D {

    // Если ответ существует, верните список из двух элементов
    // Если нет - то верните пустой список 
    private static List<Integer> twoSum(List<Integer> arr, int targetSum) {

//        10
//-93 67 84 -30 -96 22 40 -11 -39 11
//-186

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Integer integer : arr) {
            hashMap.put(integer, integer);
        }

        for (Integer i : arr) {
            Integer res = hashMap.get(targetSum - i);
            if (res != null && !res.equals(i)) {
                List<Integer> resultList = new ArrayList<>();
                resultList.add(i);
                resultList.add(targetSum - i);
                return resultList;
            }
        }

        return new ArrayList<>();
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            int targetSum = readInt(reader);
            List<Integer> result = twoSum(arr, targetSum);
            if (result.isEmpty()) {
                System.out.println("None");
            } else {
                System.out.println(result.get(0) + " " + result.get(1));
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return  Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

}