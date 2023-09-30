package sprint1;

import java.util.Scanner;

public class B {

    private static boolean checkParity(int a, int b, int c) {
        boolean res1 = (a % 2 == 0) == (b % 2 == 0);
        boolean res2 = (a % 2 == 0) == (c % 2 == 0);
        return res1 == res2 && res1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if (checkParity(a, b, c)) {
            System.out.println("WIN");
        } else {
            System.out.println("FAIL");
        }
        scanner.close();
    }

}
