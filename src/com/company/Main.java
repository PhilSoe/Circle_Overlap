package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import static java.awt.Color.*;

public class Main {

    static Random rand = new Random();

    static final int SIDE_LIM = 2000;
    static int[][] grid = new int[SIDE_LIM][SIDE_LIM];

    static final int POINTS = 6;
    static double[] points_x = new double[POINTS];
    static double[] points_y = new double[POINTS];

    public static void main(String[] args) {


        // Adds points randomly to the middle of the screen
        int i = 0;
        while (i < POINTS) {
            points_x[i] = rand.nextDouble() * SIDE_LIM / 2 + (SIDE_LIM >> 2);
            points_y[i] = rand.nextDouble() * SIDE_LIM / 2 + (SIDE_LIM >> 2);
            i += 1;
        }


        int p1 = 0;
        int p2 = 0;
        int p3 = 0;

        ArrayList<Circle> circles = new ArrayList<>();

        // Loops over all permutations of 3 points
        while (p1 < POINTS) {
            while (p2 < POINTS) {
                while (p3 < POINTS) {
                    // Ensures that only 1 permutation of each combination of points is used
                    if (p1 < p2 && p2 < p3) {

                        // Generates a circle which intersects with the three chosen points
                        Circle circle = new Circle(points_x[p1], points_y[p1],
                                                    points_x[p2], points_y[p2],
                                                    points_x[p3], points_y[p3]){};

                        circles.add(circle);
                    }
                    p3 += 1;
                }
                p2 += 1;
                p3 = 0;
            }
            p2 = 0;
            p1 += 1;
        }

        int x = 0;
        int y = 0;

        // loops over all circles
        for (Circle c:
             circles) {
            // loops over all the points in grid[][]
            while (x < SIDE_LIM) {
                while (y < SIDE_LIM) {

                    // for any point within the circle swap the value from 1 to 0 and from 0 to 1
                    if (c.inside(x, y)) {
                        switch (grid[x][y]) {
                            case 0:
                                grid[x][y] = 1;
                                break;
                            case 1:
                                grid[x][y] = 0;
                                break;
                        }
                    }
                    y += 1;
                }
                x += 1;
                y = 0;
            }
            x = 0;
        }


        // Saving the output image
        File file = new File("image.jpg");
        try {
            ImageIO.write(draw(), "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * Creates a image of the grid colored by the value of grid[x][y]
     *
     * @return Returns the image generated
     */
    public static BufferedImage draw() {
        BufferedImage img = new BufferedImage(SIDE_LIM + 1, SIDE_LIM + 1, BufferedImage.TYPE_INT_RGB);

        int x = 0;
        int y = 0;
        while (x < SIDE_LIM) {
            while (y < SIDE_LIM) {
                switch (grid[x][y]) {
                    case 0:
                        img.setRGB(x, y, BLACK.getRGB());
                        break;
                    case 1:
                        img.setRGB(x, y, RED.getRGB());
                        break;
                }
                y += 1;
            }
            x += 1;
            y = 0;
        }
        return img;
    }


}
