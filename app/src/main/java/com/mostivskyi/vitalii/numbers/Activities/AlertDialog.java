package com.mostivskyi.vitalii.numbers.Activities;

import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by student on 2015-10-14.
 */
public class AlertDialog extends android.app.AlertDialog {

    protected AlertDialog(Context context) {
        super(context);
        initialize();
    }

    protected AlertDialog(Context context, String title, String message) {
        super(context);
        initialize();
        super.setTitle(title);
        super.setMessage(message);
    }

    private void initialize() {
        setCancelable(true);
        setButton(BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
    }

    public AlertDialog setTitle(String title) {
        super.setTitle(title);
        return this;
    }

    public AlertDialog setMessage(String message) {
        super.setMessage(message);
        return this;
    }

    public void show() {
        super.show();
    }
}
