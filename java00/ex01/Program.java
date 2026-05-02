import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int number;
        boolean isPrime;
        int i;
        int operationsCount;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an integer:  ");
        number = scanner.nextInt();
        if (number <= 1) {
            System.out.println("IllegalArgument");
            scanner.close();
            System.exit(-1);
        }

        isPrime = true;
        operationsCount = 0;
        if (number != 2 && number % 2 == 0) {
            isPrime = false;
            operationsCount++;
        } else {
            i = 3;
            while (i * i <= number) {
                operationsCount++;
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
                i += 2;
            }
        }
        System.out.println(isPrime + " " + operationsCount);
        scanner.close();
    }
}
