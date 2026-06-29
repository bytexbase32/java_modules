package ex00;
public class Egg implements Runnable {

    private final int count;

    Egg(int count) {
        this.count = count;
    }
    @Override
    public void run() {
        for (int i = 0; i < count; i++)
            System.out.println("Egg");
    }
    
}
