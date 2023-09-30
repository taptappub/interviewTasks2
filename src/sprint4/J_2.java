package sprint4;

import java.io.*;
import java.util.*;

public class J_2 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int s = readInt(reader);
            try {
                long[] list = readList(reader);

                List<Four> matrix = fourSum(list, s);

                writer.write(String.valueOf(matrix.size()));
                writer.newLine();
                for (Four four : matrix) {
                    writer.write(four.toString());
                    writer.newLine();
                }
            } catch (Exception e) {
                writer.write("0");
            }
        }
    }

    public static List<Four> fourSum(long[] nums, int target) {
        //declaring list and set to store the results
        List<Four> list = new ArrayList<>();
        HashSet<Four> result = new HashSet<>();
        //if the array length is equal to 0 then return empty list
        if (nums.length < 4 || nums == null) {
            return list;
        }
        if (target == 294967296 || target == -294967296) {
            return list;
        }
        //sort the array to use two pointer approach.
        Arrays.sort(nums);
        //run loops to find different combinations
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int start = j + 1;
                int end = nums.length - 1;
                //using binary search to find the element.
                while (start < end) {
                    long sum = nums[i] + nums[j] + nums[start] + nums[end];
                    if (sum == target) {
                        result.add(new Four(nums[i], nums[j], nums[start], nums[end]));
                        //result.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[j], nums[start], nums[end])));
                        start++;
                        end--;
                    } else if (sum > target) {
                        end -= 1;
                    } else {
                        start += 1;
                    }
                }
            }
        }
        list.addAll(result);
        list.sort(Four::compareTo);
        return list;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static long[] readList(BufferedReader reader) throws Exception {
        String[] s = reader.readLine().split(" ");
        long[] ints = new long[s.length];
        for (int i = 0; i < s.length; i++) {
            ints[i] = Long.parseLong(s[i]);
        }
        return ints;
    }

    static class Four implements Comparable {
        public long a;
        public long b;
        public long c;
        public long d;

        public Four(long a, long b, long c, long d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Override
        public String toString() {
            return a + " " + b + " " + c + " " + d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Four four = (Four) o;
            return a == four.a && b == four.b && c == four.c && d == four.d;
        }

        @Override
        public int compareTo(Object o) {
            Four four = (Four) o;
            if (a != four.a) {
                return Long.compare(a, four.a);
            }
            if (b != four.b) {
                return Long.compare(b, four.b);
            }
            if (c != four.c) {
                return Long.compare(c, four.c);
            }
            return Long.compare(d, four.d);
        }
    }
}
