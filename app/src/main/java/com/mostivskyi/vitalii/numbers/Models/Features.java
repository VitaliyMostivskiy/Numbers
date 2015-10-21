package com.mostivskyi.vitalii.numbers.Models;

import com.mostivskyi.vitalii.numbers.Helpers.MathHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015-10-07.
 */
public class Features {

    private List<Point> points;

    public Features(List<Point> points) {
        this.points = points;
    }

    public double getFirstLastDist() {
        double distFL = 0;
        Point pointF = points.get(0);
        Point pointL = points.get(points.size() - 1);
        distFL = MathHelper.GetDistanceBetweenPoints(pointF, pointL);
        return distFL;
    }

    public double getLength() {
        double length = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + 1);
            length += MathHelper.GetDistanceBetweenPoints(point1, point2);
        }
        return length;
    }

    public double getFirstLastAngleX() {
        double angleFLX = 0;
        Point pointF = points.get(0);
        Point pointL = points.get(points.size() - 1);
        angleFLX = (pointL.getX() - pointF.getX()) / getFirstLastDist();
        return angleFLX;
    }

    public double getFirstLastAngleY() {
        double angleFLY = 0;
        Point pointF = points.get(0);
        Point pointL = points.get(points.size() - 1);
        angleFLY = (pointL.getY() - pointF.getY()) / getFirstLastDist();
        return angleFLY;
    }

    public List<Double> calculateFeatures() {
        List<Double> features = new ArrayList();

        //Length
        double length = getLength();
        features.add(length);

        // First - Last distance
        double distFL = getFirstLastDist();
        features.add(distFL);

        //Angle First - Last X
        double angleFLX = getFirstLastAngleX();
        features.add(angleFLX);

        //Angle First - Last Y
        double angleFLY = getFirstLastAngleY();
        features.add(angleFLY);

        return features;
    }


}
