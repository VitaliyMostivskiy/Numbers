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


    //new start f5
    public double getMaxWith()
    {
        double a = 0;
        for (int i=0; i<points.size()-1;i++)
            for(int j=1+1; j<points.size();j++){
                double xvi = points.get(i).getX();
                double xvj = points.get(j).getX();
                double a_Temp = Math.abs(xvi-xvj);
                if(a_Temp>a){
                    a = a_Temp;
                }
            }
        return a;
    }

    public double getMaxHeight()
    {
        double b = 0;
        for (int i=0; i<points.size()-1;i++)
            for(int j=1+1; j<points.size();j++){
                double yui = points.get(i).getY();
                double yuj = points.get(j).getY();
                double b_Temp = Math.abs(yui-yuj);
                if(b_Temp>b){
                    b = b_Temp;
                }
            }
        return b;
    }

    public double getDiagonal(){
        return Math.sqrt(Math.pow(getMaxWith(),2)+Math.pow(getMaxHeight(),2));
    }
    //f6
    public double notCentr(){
        double a = Math.max(getMaxWith(),getMaxHeight());
        double b = Math.min(getMaxWith(),getMaxHeight());
        return Math.sqrt(1-(Math.pow(b,2)/Math.pow(a,2)));
    }
    //f7
    public double F7()
    {
        return Math.min(getMaxWith(),getMaxHeight())/Math.max(getMaxWith(),getMaxHeight());
    }

    public double getMaxX46()
    {
        double x = Integer.MIN_VALUE;
        for (int i=0; i<points.size();i++)
        { double x1 = points.get(i).getX();
            if(x1>x){
                x = x1;
            }
        }
        return x;
    }
    public double getMinX47()
    {
        double x = Integer.MAX_VALUE;
        for (int i=0; i<points.size();i++)
        { double x1 = points.get(i).getX();
            if(x1<x){
                x = x1;
            }
        }
        return x;
    }
    public double getMaxY46()
    {
        double y = Integer.MIN_VALUE;
        for (int i=0; i<points.size();i++)
        { double y1 = points.get(i).getY();
            if(y1>y){
                y = y1;
            }
        }
        return y;
    }
    public double getMinY47()
    {
        double y = Integer.MAX_VALUE;
        for (int i=0; i<points.size();i++)
        { double y1 = points.get(i).getY();
            if(y1<y){
                y = y1;
            }
        }
        return y;
    }
    public double f12(){
        return getMinX47()+1/2*(getMaxX46()-getMinX47());
    }
    public double f13(){
        return getMinY47()+1/2*(getMaxY46()-getMinY47());
    }
    //new end




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



        double diagonal = getDiagonal();
        features.add(diagonal);

        double notCentr = notCentr();
        features.add(notCentr);

        double feat7 = F7();
        features.add(feat7);

        double feat12 = f12();
        features.add(feat12);

        double feat13 = f13();
        features.add(feat13);

        return features;
    }
}
