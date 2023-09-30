//package com.marpozh.chapter_4;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class D {


    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            String[] res = new String[n];
            //  HashMap<String, Integer> map = new HashMap<>(n);
            int resind = 0;

            int i = 0;
            while (i++ < n) {

                String line = reader.readLine();
                // map.putIfAbsent(line, i);

               boolean exists =  findInArray(res, resind,  line);
               if(exists){
                   continue;
               }
                res[resind++] = line;
            }

            for (int k = 0; k < resind; k++) {

                writer.write(res[k]);
                writer.newLine();
            }
   /*

            map.entrySet().stream()..forEach(m -> {
                try {
                    writer.write(m.getKey());
                    writer.newLine();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
*/

        }
    }
    private static boolean findInArray(String [] arr, int n, String line){

        for (int k = 0; k < n; k++) {
            if (arr[k].equals(line)) {
              return true;
            }
        }
        return false;
    }


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
