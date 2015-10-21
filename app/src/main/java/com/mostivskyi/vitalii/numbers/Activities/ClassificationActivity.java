package com.mostivskyi.vitalii.numbers.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mostivskyi.vitalii.numbers.Helpers.FileWriter;
import com.mostivskyi.vitalii.numbers.Models.Features;
import com.mostivskyi.vitalii.numbers.R;

import java.io.FileNotFoundException;
import java.util.List;


public class ClassificationActivity extends ActionBarActivity {

    private DrawingView drawView;
    private Button cleanButton;
    private Button addButton;
    private Button classifyButton;
    private Button trainButton;
    private EditText digitEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);
        setUpElements();
        setUpListeners();
    }

    private void setUpElements() {
        drawView = (DrawingView) findViewById(R.id.drawing);
        cleanButton = (Button) findViewById(R.id.CleanButton);
        addButton = (Button) findViewById(R.id.AddButton);
        classifyButton = (Button) findViewById(R.id.ClassifyButton);
        trainButton = (Button) findViewById(R.id.TrainButton);
        digitEditText = (EditText) findViewById(R.id.DigitEditText);
    }

    private void setUpListeners() {

        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.cleanDrawing();
            }
        });

        trainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        classifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (digitEditText.getText().toString().equals("")) {
                    new AlertDialog(ClassificationActivity.this, "Bład", "Nie podano cyfry do dodania")
                            .show();
                } else if (drawView.getPoints().size() == 0) {
                    new AlertDialog(ClassificationActivity.this, "Bład", "Obrazek nie został narysowany")
                            .show();
                } else {
                    Features features = new Features(drawView.getPoints());
                    List<Double> featureValues = features.calculateFeatures();
                    String digit = digitEditText.getText().toString();
                    String filePath = getString(R.string.train_data_file_name);
                    FileWriter.WriteFeatures(filePath, featureValues, digit);
                    drawView.cleanDrawing();
                }
            }
        });


        try {
            openFileOutput("", 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

  /* public void showAlertDialog(String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close current activity
                        dialog.dismiss();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_classification, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
