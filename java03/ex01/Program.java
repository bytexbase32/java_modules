
public class Program {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: java Program --count=50");
            return;
        }
        String[] parts = args[0].trim().split("=");
        if (parts.length != 2 || !parts[0].equals("--count")) {
            System.out.println("usage: java Program --count=50");
            return;
        }

        int count;
        try {
            count = Integer.parseInt(parts[1]);
        } catch (Exception e) {
            System.out.println("usage: java Program --count=50");
            return;
        }
        
        Printer printer = new Printer();

        Thread egg = new Thread(new Egg(count, printer));
        Thread hen = new Thread(new Hen(count, printer));

        egg.start();
        hen.start();

        try {
            egg.join();
            hen.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
