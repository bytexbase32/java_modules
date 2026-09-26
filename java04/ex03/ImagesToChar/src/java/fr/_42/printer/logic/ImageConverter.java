package fr._42.printer.logic;

import java.awt.image.BufferedImage;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

public class ImageConverter {

    private final Ansi.FColor white;
    private final Ansi.FColor black;

    public ImageConverter(Ansi.FColor white, Ansi.FColor black) {
        this.white = white;
        this.black = black;
    }

    public void printImage(BufferedImage image) {

        ColoredPrinter printer = new ColoredPrinter.Builder(1, false)
                .build();

        for (int y = 0; y < image.getHeight(); y++) {

            for (int x = 0; x < image.getWidth(); x++) {

                int pixel = image.getRGB(x, y);

                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;

                int gray = (red + green + blue) / 3;

                if (gray < 128) {
                    printer.print(
                        "0",
                        Ansi.Attribute.NONE,
                        black,
                        Ansi.BColor.NONE
                    );
                } else {
                    printer.print(
                        ".",
                        Ansi.Attribute.NONE,
                        white,
                        Ansi.BColor.NONE
                    );
                }
            }

            System.out.println();
        }
    }
}
