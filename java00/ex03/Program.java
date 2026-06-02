import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long storage = 0;

        String line;
        int week = 1;

        while (true) {
            if (!scanner.hasNextLine())
                break;
            line = scanner.nextLine();

            if (line.equals("42"))
                break;
            if (!line.equals("Week " + week)) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            int i = 0;
            int min = 9;
            int num;
            while (i < 5) {
                num = scanner.nextInt();
                if (num > 9 || num < 0) {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
                if (num < min)
                    min = num;
                i++;
            }
            scanner.nextLine();
            storage = storage * 10 + min;
            week++;
            if (week > 18)
                break;
        }
        long reversed = 0;
        while (storage > 0) {
            reversed = reversed * 10 + storage % 10;
            storage /= 10;
        }
        int printWeek = 1;
        while (reversed > 0) {
            long value = reversed % 10;
            reversed /= 10;
            System.out.print("Week " + printWeek++ + " ");
            for (int i = 0; i < value; i++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
        scanner.close();
    }
}
