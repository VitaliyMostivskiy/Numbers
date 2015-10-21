package com.mostivskyi.vitalii.numbers.Helpers;

import com.mostivskyi.vitalii.numbers.Models.Point;

/**
 * Created by student on 2015-10-07.
 */
public final class MathHelper {

    public static double GetDistanceBetweenPoints(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
    }
}
