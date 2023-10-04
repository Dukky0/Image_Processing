package org.dukky;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String fileName;
        String operation;

        try {
            fileName = reader.readLine();
        } catch (IOException e) {
            System.out.println(e);

            fileName = "file";
        }

        File imageFile = new File("images/" + fileName + ".png");

        try {
            BufferedImage image = ImageIO.read(imageFile);

            Pixel[][] imageData = new Pixel[image.getWidth()][image.getHeight()];

            Color color;

            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    color = new Color(image.getRGB(x, y), true);
                    imageData[x][y] = new Pixel(color);
                }
            }

            try {
                operation = reader.readLine();
            } catch (IOException e) {
                System.out.println(e);
                operation = "";
            }

            switch (operation) {
                case "invert":
                    for (int x = 0; x < imageData.length; x++) {
                        for (int y = 0; y < imageData[x].length; y++) {
                            imageData[x][y].invert();
                        }
                    }
                    break;
                case "greyscale":
                    for (int x = 0; x < imageData.length; x++) {
                        for (int y = 0; y < imageData[x].length; y++) {
                            imageData[x][y].greyscale();
                        }
                    }
                    break;
                case "protonopia":
                    for (int x = 0; x < imageData.length; x++) {
                        for (int y = 0; y < imageData[x].length; y++) {
                            imageData[x][y].protonopia();
                        }
                    }

                default:
                    break;
            }

            for (int x = 0; x < imageData.length; x++) {
                for (int y = 0; y < imageData[x].length; y++) {
                    color = new Color(imageData[x][y].getRed(),
                            imageData[x][y].getGreen(),
                            imageData[x][y].getBlue(),
                            imageData[x][y].getAlpha());
                    image.setRGB(x, y, color.getRGB());
                }
            }

            File output = new File("images/" + fileName + "_" + operation + ".png");
            ImageIO.write(image, "png", output);

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}