package fr._42.printer.app;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Scanner;
import fr._42.printer.logic.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(Main.class.getResourceAsStream("/it.bmp"));

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter charachter for white color: ");
        char white = sc.next().charAt(0);
        
        System.out.print("Enter charachter for black color: ");
        char black = sc.next().charAt(0);
        
        sc.close();
        
        ImageConverter imageConverter = new ImageConverter(white, black);
        imageConverter.printImage(image);
    }
}