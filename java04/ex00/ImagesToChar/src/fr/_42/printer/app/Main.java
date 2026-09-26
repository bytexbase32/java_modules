package fr._42.printer.app;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import fr._42.printer.logic.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Missing target, check README.TXT");
            System.exit(1);
        }
        File file = new File(args[0]);
        BufferedImage image = ImageIO.read(file);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter charachter for white color: ");
        char white = sc.next().charAt(0);
        sc.nextLine();
        System.out.println("Enter charachter for black color: ");
        char black = sc.next().charAt(0);
        sc.nextLine();
        sc.close();
        ImageConverter imageConverter = new ImageConverter(white, black);
        imageConverter.printImage(image);
    }
}