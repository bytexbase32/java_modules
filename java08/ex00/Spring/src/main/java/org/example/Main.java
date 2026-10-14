package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("context.xml");

        Printer printer =
                context.getBean("printerWithDateTime", Printer.class);

        printer.print("Hello!");
    }
}
