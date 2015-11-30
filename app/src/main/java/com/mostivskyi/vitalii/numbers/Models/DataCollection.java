package com.mostivskyi.vitalii.numbers.Models;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitalii.mostivskyi on 22/11/2015.
 */
public class DataCollection {

    private static String Tag = "DataCollection";

    private List<double[]> rawData;
    private List<Double> classes;

    private int featuresCount;

    public DataCollection() {
        rawData = new ArrayList();
        classes = new ArrayList();
    }

    public void build(InputStream inputStream) {
        try {
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ((receiveString = bufferedReader.readLine()) != null) {

                    String[] stringRecord = receiveString.split(",");
                    featuresCount = stringRecord.length - 1;

                    double[] doubleRecord = new double[stringRecord.length];

                    for (int i = 0; i < doubleRecord.length; i++) {
                        doubleRecord[i] = Double.parseDouble(stringRecord[i]);
                    }

                    double className = doubleRecord[doubleRecord.length - 1];

                    if (!classes.contains(className)) {
                        classes.add(className);
                    }

                    rawData.add(doubleRecord);
                }

                inputStream.close();
            }
        } catch (Exception e) {
            Log.e(Tag, e.getMessage());
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(Tag, e.getMessage());
                }
            }
        }
    }

    public double getAverageValue(int featureIndex, int classIndex) {
        double average = 0;

        for (int i = 0; i < rawData.size(); i++) {
            double[] record = rawData.get(i);

            if (record[featuresCount] == classes.get(classIndex)) {
                average += record[featureIndex];
            }
        }

        average = average / ((double) getRecordsCountInClass(classIndex));

        return average;
    }

    public double getVariation(int attributeIndex, int classIndex) {
        double var = 0;
        double averageValue = getAverageValue(attributeIndex, classIndex);

        for (int i = 0; i < rawData.size(); i++) {
            double[] record = rawData.get(i);

            if (record[featuresCount] == classes.get(classIndex)) {

                double diff = record[attributeIndex] - averageValue;
                var += Math.pow(diff, 2);
            }
        }

        var = var / ((double) getRecordsCountInClass(classIndex));

        return var;
    }

    public int getRecordsCount() {
        return rawData.size();
    }

    public int getRecordsCountInClass(int classIndex) {

        int numOfRecords = 0;

        for (int i = 0; i < rawData.size(); i++) {

            double[] record = rawData.get(i);

            if (record[featuresCount] == classes.get(classIndex)) {
                numOfRecords++;
            }
        }

        return numOfRecords;
    }

    public int getFeaturesCount() {
        return featuresCount;
    }

    public int getClassesCount() {
        return classes.size();
    }

    public List<Double> getClasses() {
        return classes;
    }
}
