public class Hen implements Runnable {

    final private int count;
    final private Printer printer;

    Hen(int count, Printer printer) {
        this.count = count;
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            synchronized (printer.lock) {
                while (printer.eggTurn) {
                    try {
                        printer.lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                printer.print("Hen");
                printer.eggTurn = true;
                printer.lock.notifyAll();
            }
        }
    }
}
