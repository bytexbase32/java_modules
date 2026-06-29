package ex02;

import java.util.concurrent.ThreadLocalRandom;

public class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage: java Program --arraySize=13 --threadsCount=3");
            System.exit(1);
        }

        int arrLength = 0;
        int numberOfThreads = 0;

        try {
            arrLength = Integer.parseInt(args[0].trim().split("=")[1]);
        } catch (Exception e) {
            System.out.println("Array length(first argument) is not a number");
            System.exit(1);
        }

        if (arrLength > 2_000_000) {
            System.out.println("Array length is greater than 2000000");
            System.exit(1);
        }
        try {
            numberOfThreads = Integer.parseInt(args[1].trim().split("=")[1]);
        } catch (Exception e) {
            System.out.println("Threads count(second argument) is not a number");
            System.exit(1);
        }
        if (numberOfThreads > arrLength) {
            System.out.println("Threads count is the greater than arry size");
            System.exit(1);
        }
        int[] numbers = new int[arrLength];
        for (int i = 0; i < arrLength; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt(1001);
        }

        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("sum : " + sum);

        int[] total = new int[numberOfThreads];
        int step = arrLength / numberOfThreads;
        int reminder = arrLength % numberOfThreads;
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0, start = 0; i < numberOfThreads; i++) {
            int last = start + step - 1;
            if (i == numberOfThreads - 1)
                last += reminder ;
            threads[i] = new Thread(new MyThread(start, last, numbers, i, total));
            threads[i].start();
            start += step;
        }

        for (int i = 0, start = 0; i < numberOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        int result = 0;
        for (int chunk : total) {
            result += chunk;
        }

        System.out.println("Sum by threads: " + result);
    }
}
