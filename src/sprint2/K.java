package sprint2;

import java.util.Scanner;


public class K {

    public static int fib(int n) {
        if (n <= 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(fib(n));
        scanner.close();
    }

//    0 1 2 3 4 5  6
//    1 1 2 3 5 8 13
}

