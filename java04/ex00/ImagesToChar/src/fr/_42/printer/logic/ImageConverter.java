package fr._42.printer.logic;

import java.awt.image.BufferedImage;

public class ImageConverter {

    private final char white;
    private final char black;

    public ImageConverter(char white, char black) {
        this.white = white;
        this.black = black;
    }

    public void printImage(BufferedImage image) {

        for (int y = 0; y < image.getHeight(); y++) {

            for (int x = 0; x < image.getWidth(); x++) {

                int pixel = image.getRGB(x, y);

                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;

                int gray = (red + green + blue) / 3;

                if (gray < 128)
                    System.out.print(black);
                else
                    System.out.print(white);
            }

            System.out.println();
        }
    }
}