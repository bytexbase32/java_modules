package fr._42.printer.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.diogonunes.jcdp.color.api.Ansi;
import fr._42.printer.logic.ImageConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {

    @Parameter(names = "--white", required = true)
    private String white;

    @Parameter(names = "--black", required = true)
    private String black;

    public static void main(String[] args) throws IOException {

        Main main = new Main();

        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);

        BufferedImage image = ImageIO.read(
                Main.class.getResourceAsStream("/it.bmp")
        );

        Ansi.FColor whiteColor =
                Ansi.FColor.valueOf(main.white);

        Ansi.FColor blackColor =
                Ansi.FColor.valueOf(main.black);

        ImageConverter converter =
                new ImageConverter(whiteColor, blackColor);

        converter.printImage(image);
    }
}