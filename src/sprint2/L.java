package sprint2;

import java.util.Scanner;

public class L {

    public static int fib(int n, int k) {
        int prevSum = 0;
        int curSum = 0;

        if (n == 0 || n == 1) {
            return 1;
        }

        int realK = (int) Math.pow(10, k);

        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                prevSum = 1;
                curSum = 1;
            } else {
                int newSum = (prevSum + curSum) % realK;
                prevSum = curSum;
                curSum = newSum;
            }
        }
        return curSum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        System.out.println(fib(n, k));
        scanner.close();
    }
}