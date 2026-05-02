import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        System.out.println("java00");

        Scanner scanner = new Scanner(System.in);
        String[] students = new String[10];
        String input;
        int count = 0;

        while (true) {
            input = scanner.nextLine();
            if (count == 10 || input.equals("."))
                break;
            students[count] = input;
            count++;
        }

        int[] classHour = new int[10];
        String[] classDay = new String[10];
        int classCount = 0;
        while (true) {
            input = scanner.nextLine();
            if (classCount == 10 || input.equals("."))
                break;
            String[] tmp = input.split(" ");
            classHour[classCount] = Integer.parseInt(tmp[0]);
            classDay[classCount] = tmp[1];
            classCount++;
        }
        
        int[] attendanceStudentIndex = new int[100];
        int[] attendanceSessionIndex = new int[100];
        int[] attendanceStatus = new int[100];
        int attendanceCount = 0;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("."))
                break;

            String[] tmp = input.split(" ");

            String name = tmp[0];
            int hour = Integer.parseInt(tmp[1]);
            int day = Integer.parseInt(tmp[2]);
            String statusStr = tmp[3];

            // 1. find student index
            int studentIndex = -1;
            for (int i = 0; i < count; i++) {
                if (students[i].equals(name)) {
                    studentIndex = i;
                    break;
                }
            }

            // 2. find session index
            int sessionIndex = -1;
            for (int i = 0; i < classCount; i++) {
                if (classHour[i] == hour && classDay[i].equals(String.valueOf(day))) {
                    sessionIndex = i;
                    break;
                }
            }

            // 3. convert status
            int status = statusStr.equals("HERE") ? 1 : -1;

            // 4. store
            attendanceStudentIndex[attendanceCount] = studentIndex;
            attendanceSessionIndex[attendanceCount] = sessionIndex;
            attendanceStatus[attendanceCount] = status;
            attendanceCount++;
        }



        // Step A:

        // Read students until .

        // Step B:

        // Read classes until .

        // Step C:

        // Read attendance until .

        // Step D:

        // Store everything in arrays

        // Step E:

        // Print formatted table
    }
}