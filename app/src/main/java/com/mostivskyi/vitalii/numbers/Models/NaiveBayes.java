package com.mostivskyi.vitalii.numbers.Models;

import java.util.List;

/**
 * Created by vitalii.mostivskyi on 22/11/2015.
 */
public class NaiveBayes {

    private double[][] averages;
    private double[][] variances;
    private double[] classDistribution;
    private List<Double> classes;
    private boolean isTrained;
    private double varianceNoice;

    public NaiveBayes() {
        isTrained = false;
        varianceNoice = 0.00001;
    }

    public void buildClassifier(DataCollection data) {
        averages = new double[data.getClassesCount()][data.getFeaturesCount()];
        variances = new double[data.getClassesCount()][data.getFeaturesCount()];
        classDistribution = new double[data.getClassesCount()];
        classes = data.getClasses();

        for (int i = 0; i < data.getClassesCount(); i++) {
            classDistribution[i] = ((double) data.getRecordsCountInClass(i)) / ((double) data.getRecordsCount());

            for (int j = 0; j < data.getFeaturesCount(); j++) {
                averages[i][j] = data.getAverageValue(j, i);
                variances[i][j] = data.getVariation(j, i) + varianceNoice;
            }
        }

        isTrained = true;
    }

    public double classifyInstance(Features features) {
        double currenProbability = 0;
        int currentId = 0;
        List<Double> featureValues = features.calculateFeatures();

        for (int i = 0; i < classes.size(); i++) {
            double py = classDistribution[i];

            for (int j = 0; j < featureValues.size(); j++) {
                py = py * (1 / (Math.sqrt(2 * Math.PI * variances[i][j])))
                        * Math.exp(-1 / (2 * variances[i][j]) * Math.pow((featureValues.get(j) - averages[i][j]), 2));
            }

            if (py > currenProbability)
            {
                currenProbability = py;
                currentId = i;
            }
        }

        return classes.get(currentId);
    }

    public boolean isTrained() {
        return isTrained;
    }
}
