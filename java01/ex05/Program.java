package ex05;

public class Program {
    public static void main(String[] args) {

        boolean devMode = args.length == 1 && args[0].equals("--profile=dev");

        Menu menu = new Menu(devMode);
        menu.run();
    }
}
