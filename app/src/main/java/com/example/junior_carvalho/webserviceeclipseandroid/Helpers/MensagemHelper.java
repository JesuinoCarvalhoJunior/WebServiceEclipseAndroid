package com.example.junior_carvalho.webserviceeclipseandroid.Helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Junior_Carvalho on 15/10/2015.
 */
public class MensagemHelper {


    public ProgressDialog dialog = null;
    Context ct;

    public void ProgressDialogo(String title, String message) {
        dialog = new ProgressDialog(ct);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setButton("Ok", new DialogInterface.OnClickListener() {
      //  dialog.setButton(buttonText,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// Use either finish() or return() to either close the activity or just the dialog
                dialog.dismiss();
                return;
            }
        });
        dialog.show();
    }

}
