package com.mostivskyi.vitalii.numbers.Helpers;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by student on 2015-10-14.
 */
public final class FileWriter {

    public static void WriteFeatures(Context context, String filePath, List<Double> featureValues, String digit) {
        try {
            FileOutputStream fileOut = context.openFileOutput(filePath, context.MODE_PRIVATE | context.MODE_APPEND);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
            for (int i = 0; i < featureValues.size(); i++) {
                outputWriter.write(featureValues.get(i).toString());
                outputWriter.write(",");
            }
            outputWriter.write(digit);
            outputWriter.write("\n");
            outputWriter.close();
        } catch (Exception e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }

    public static void DeleteFile(Context context, String fileName) {

        File file = context.getFileStreamPath(fileName);

        if (file.exists()) {
            file.delete();
        }
    }
}
