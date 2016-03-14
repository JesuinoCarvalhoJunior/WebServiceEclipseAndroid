package com.example.junior_carvalho.webserviceeclipseandroid.Helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.junior_carvalho.webserviceeclipseandroid.R;

/**
 * Created by Junior_Carvalho on 15/10/2015.
 */
public class MensagemHelper extends Activity {


    public ProgressDialog dialog = null;
    Context ct;
    private TextView versaoinfo;

    public void ProgressDialogo(String title, String message) {
        dialog = new ProgressDialog(ct);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setButton("Ok", new DialogInterface.OnClickListener() {
            //  dialog.setButton(buttonText,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// Use either finish() or return() to either close the activity or just the dialog
                dialog.dismiss();
              //  return;
            }
        });
        dialog.show();
    }

    /* Lê a versão do app */
    public static String getVersionName(Activity activity) {
        PackageManager pm = activity.getPackageManager();
        String packageName = activity.getPackageName();
        String versionName;
        try {
            PackageInfo info = pm.getPackageInfo(packageName, 0);
            versionName = info.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            versionName = "N/A";
        }
        return versionName;
    }

    /**
     * Limpa os ícones e as mensagens de erro dos campos desejados
     *
     * @param editTexts lista de campos do tipo EditText
     */
    public static void clearErrorFields(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setError(null);
        }
    }




}
