public class Printer {
    boolean eggTurn = true;
    final Object lock = new Object();

    public void print(String message) {
            System.out.println(message);
    }
}
