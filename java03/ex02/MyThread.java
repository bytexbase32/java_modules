package ex02;

public class MyThread implements Runnable {

    private final int startIndex;
    private final int endIndex;
    private final int[] elements;
    private final int threadIndex;
    private final int[] total;

    public MyThread(int start, int end, int[] arr, int threadIndex, int[] total) {
        this.startIndex = start;
        this.endIndex = end;
        this.elements = arr;
        this.threadIndex = threadIndex;
        this.total = total;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            sum += elements[i];
        }
        total[threadIndex] = sum;
        System.out.println(
                "Thread " + (threadIndex + 1)
                        + ": from " + startIndex
                        + " to " + endIndex
                        + " sum is " + sum);
    }
}
