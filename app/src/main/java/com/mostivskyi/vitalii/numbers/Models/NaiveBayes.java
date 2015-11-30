package com.mostivskyi.vitalii.numbers.Models;

import java.util.List;

/**
 * Created by vitalii.mostivskyi on 22/11/2015.
 */
public class NaiveBayes {

    private double[][] averages;
    private double[][] variances;
    //вірогідність апріорі - відношення к-сті елементів даного класу до загю к-сті ел.
    private double[] classDistribution;

    private List<Double> classes;

    private boolean isTrained;
    private double varianceNoise;

    public boolean isTrained() {
        return isTrained;
    }

    public NaiveBayes()
    {
        isTrained = false;
        varianceNoise = 0.00001;
    }

    public void buildClassifier(DataCollection data)
    {
        int classesCount = data.getClassesCount();
        int featuresCount = data.getFeaturesCount();
        int recordsCount = data.getRecordsCount();

        averages = new double[classesCount][featuresCount];
        variances = new double[classesCount][featuresCount];
        classDistribution = new double[classesCount];

        classes = data.getClasses();

        for (int i = 0; i < classesCount; i++)
        {
            classDistribution[i] = ((double) data.getRecordsCountInClass(i)) / ((double) recordsCount);

            for (int j = 0; j < featuresCount; j++)
            {
                averages[i][j] = data.getAverageValue(j, i);
                variances[i][j] = data.getVariation(j, i) + varianceNoise;
            }
        }

        isTrained = true;
    }

    public double classifyInstance(Features features)
    {
        List<Double> featureValues = features.calculateFeatures();
        int featuresCount = featureValues.size();
        int classesCount = classes.size();

        double maxProbability = 0;
        int maxClassId = 0;

        for (int i = 0; i < classesCount; i++)
        {
            double py = classDistribution[i];

            for (int j = 0; j < featuresCount; j++)
            {
                py = py * (1 / (Math.sqrt(2 * Math.PI * variances[i][j])))
                        * Math.exp(-1 / (2 * variances[i][j]) * Math.pow((featureValues.get(j) - averages[i][j]), 2));
            }

            if (py > maxProbability)
            {
                maxProbability = py;
                maxClassId = i;
            }
        }

        return classes.get(maxClassId);
    }
}
