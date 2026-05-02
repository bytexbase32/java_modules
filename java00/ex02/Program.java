import java.util.Scanner;

public class Program {
    private static boolean isPrime(int n) {
        if (n < 2)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    private static int digitSum(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int coffeeCount = 0;
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        while (num != 42) {
            if (isPrime(digitSum(num)))
                coffeeCount++;
            num = scanner.nextInt();
        }
        scanner.close();
        System.out.println("Count of coffee-request : " + coffeeCount);
    }
}
