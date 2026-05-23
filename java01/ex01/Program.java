package ex01;

public class Program {

    public static void main(String[] args) {
        User me = new User("yassine", 1100);
        User karim = new User("karim",20000);

        System.out.println(me.getId() +": " + me.getName());
        System.out.println(karim.getId() + ": " + karim.getName());
    }
}   