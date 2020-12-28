package com.company;

public class Circle {

    double center_x;
    double center_y;
    double radius;

    /**
     * Instantiates a circle which intersects 3 points
     *
     * @param point1_x x coordinate of point 1
     * @param point1_y y coordinate of point 1
     * @param point2_x x coordinate of point 2
     * @param point2_y y coordinate of point 2
     * @param point3_x x coordinate of point 3
     * @param point3_y y coordinate of point 3
     */
    public Circle(double point1_x, double point1_y,
                  double point2_x, double point2_y,
                  double point3_x, double point3_y){

        var v = Math.pow(point2_x, 2) + Math.pow(point2_y, 2) - Math.pow(point1_x, 2) - Math.pow(point1_y, 2);
        this.center_y = (Math.pow(point3_x, 2) + Math.pow(point3_y, 2) - Math.pow(point1_x, 2) - Math.pow(point1_y, 2) + 2 * (point1_x - point3_x) *
                (v / (-2 * (point1_x - point2_x)))) /
                (-2 * (point1_y - point3_y) + (point1_x - point3_x) / (point1_x - point2_x) * 2 * (point1_y - point2_y));

        this.center_x = (v + 2 * this.center_y * (point1_y - point2_y)) /
                (-2 * (point1_x - point2_x));

        this.radius = Math.sqrt(Math.pow(point1_x - this.center_x, 2) + Math.pow(point1_y - this.center_y, 2));
    }

    /**
     * Checks if a point is in the closure of this circle
     *
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     * @return boolean of weather the point falls in the closure
     */
    public boolean inside(int x, int y){
        return radius >= Math.sqrt(Math.pow(x - center_x, 2) + Math.pow(y - center_y, 2));
    }

}
