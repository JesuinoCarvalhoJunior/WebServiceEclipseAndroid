package com.example.junior_carvalho.webserviceeclipseandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.junior_carvalho.webserviceeclipseandroid.Dao.UsuarioDao;
import com.example.junior_carvalho.webserviceeclipseandroid.Dominio.Usuario;
import com.example.junior_carvalho.webserviceeclipseandroid.Helpers.MensagemHelper;

public class MainAcitivity extends AppCompatActivity {

    private EditText edtUser;
    private EditText edtPassword;
    private Resources resources;
    private ProgressDialog dialogo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnter = (Button) findViewById(R.id.login_btn_enter);

        initViews();

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.login_btn_enter)
                    if (validateFields()) {

                        // Toast.makeText(this, "Autenticado com sucesso!", Toast.LENGTH_LONG).show();
                        Toast.makeText(MainAcitivity.this, resources.getString(R.string.login_auth_ok), Toast.LENGTH_SHORT).show();

                       ProgressDialogo("Aguarde", "Processando", "Ok");

                        //retorna lista inicial
                        Intent it = new Intent(MainAcitivity.this, MainActivityLogin.class);
                        startActivity(it);
                        finish();
                        // } else {
                        //  clearErrorFields(edtUser, edtPassword);
                        //Toast.makeText(LoginActivity.this, "Erro ao cadastrar", Toast.LENGTH_LONG).show();
                    }
            }
        });
    }

    private void callClearErrors(Editable s) {
        if (!s.toString().isEmpty()) {
            clearErrorFields(edtUser);
        }
    }

    private boolean validateFields() {
        String login = edtUser.getText().toString().trim();
        String senha = edtPassword.getText().toString().trim();


        if (!login.isEmpty() && !senha.isEmpty()) {

            UsuarioDao dao = new UsuarioDao();
            Usuario user = dao.Autenticar(edtUser.getText().toString().trim(),
                    edtPassword.getText().toString().trim()
            );

            if (user.getLogin().equals(edtUser.getText().toString().trim()) &&
                    (user.getSenha().equals(edtPassword.getText().toString().trim()))) {
            }
        }
        //   String login = edtUser.getText().toString().trim();
        //   String senha = edtPassword.getText().toString().trim();
        return (!isEmptyFields(login, senha) && hasSizeValid(login, senha));
    }

    private boolean isEmptyFields(String user, String pass) {
        if (TextUtils.isEmpty(user)) {
            edtUser.requestFocus(); //seta o foco para o campo user
            //edtUser.setError(resources.getString(R.string.login_user_required));
            edtUser.setError(Html.fromHtml("<font color='#145A14'>informe o login</font>"));
            return true;
        } else if (TextUtils.isEmpty(pass)) {
            edtPassword.requestFocus(); //seta o foco para o campo password
            //edtPassword.setError(resources.getString(R.string.login_password_required));
            edtPassword.setError(Html.fromHtml("<font color='#145A14'>informe a senha</font>"));
            return true;
        }
        return false;
    }

    private boolean hasSizeValid(String user, String pass) {

        if (!(user.length() > 1)) {
            edtUser.requestFocus();
            edtUser.setError(resources.getString(R.string.login_user_size_invalid));
            return false;
        } else if (!(pass.length() > 1)) {
            edtPassword.requestFocus();
            edtPassword.setError(resources.getString(R.string.login_pass_size_invalid));
            return false;
        }
        return true;
    }

    /**
     * Limpa os ícones e as mensagens de erro dos campos desejados
     *
     * @param editTexts lista de campos do tipo EditText
     */
    private void clearErrorFields(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setError(null);
        }
    }


    private void initViews() {
        resources = getResources();

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                callClearErrors(s);
            }
        };

        edtUser = (EditText) findViewById(R.id.login_edt_user);
        edtUser.addTextChangedListener(textWatcher);
        edtPassword = (EditText) findViewById(R.id.login_edt_password);
        edtPassword.addTextChangedListener(textWatcher);
        // Button btnEnter = (Button) findViewById(R.id.login_btn_enter);
        // btnEnter.setOnClickListener(this);
    }


    public ProgressDialog dialog = null;

    //private void ProgressDialogo(String title, String message) {
    private void ProgressDialogo(String title, String message, String buttonText) {
        dialog = new ProgressDialog(this);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setButton(buttonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// Use either finish() or return() to either close the activity or just the dialog
                dialog.dismiss();
                return;
            }
        });
        dialog.show();
    }


}