
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class C_3 {


    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int a = readInt(reader);
            int m = readInt(reader);
            String str = reader.readLine();
            int t = readInt(reader);

            RollingHash rh = new RollingHash(str, a, m);

            int i = 0;
            while (i < t) {

                List<Integer> lAndR = readList2(reader);
                //   double hash = hashOfSubstring(str, lAndR.get(0),lAndR.get(1), a, m);
                long hash = rh.getHash(lAndR.get(0) - 1, lAndR.get(1));

                writer.write(String.valueOf(hash));
                writer.newLine();
                i++;
            }

        }
    }

    public static class RollingHash {
        private final int base; // = 31;
        private final int mod;// = (int) 1e9 + 9;
        private long[] power;
        private long[] hash;

        public RollingHash(String s, int base, int m) {
            int n = s.length();
            this.base = base;
            this.mod = m;
            power = new long[n + 1];
            hash = new long[n + 1];


            power[0] = 1;
            for (int i = 1; i <= n; i++) {
                power[i] = (power[i - 1] * base) % mod;

                // hash[i] = (hash[i - 1] * base + (s.charAt(i - 1) - 'a' + 1)) % mod;
                hash[i] = (hash[i - 1] * base + s.charAt(i - 1)) % mod;
            }
        }

        public long getHash(int i, int j) {

            return (hash[j] - hash[i] * power[j - i] % mod + mod) % mod;

        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return new ArrayList<>(Arrays.asList(reader.readLine().split(" ")));
    }

    private static Set<String> readSet(BufferedReader reader) throws IOException {
        return new HashSet<>(Arrays.asList(reader.readLine().split(" ")));
    }

    private static List<Integer> readList2(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }
}
