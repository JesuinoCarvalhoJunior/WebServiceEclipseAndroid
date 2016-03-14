package com.example.junior_carvalho.webserviceeclipseandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.SyncStateContract;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;


import com.example.junior_carvalho.webserviceeclipseandroid.Dao.UsuarioDao;
import com.example.junior_carvalho.webserviceeclipseandroid.Dominio.Usuario;
import com.example.junior_carvalho.webserviceeclipseandroid.Helpers.MensagemHelper;

import org.w3c.dom.Text;


public class MainAcitivity extends AppCompatActivity {
    private LinearLayout ln;
    private EditText edtUser;
    private EditText edtPassword;
    private Resources resources;
    //    private ProgressDialog dialogo = null;
    private String versaoinfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //   Snackbar snackbar;

        //Initializing snackbar using Snacbar.make() method
        //   snackbar = Snackbar.make(coordinatorLayout,"Simple Snackbar",Snackbar.LENGTH_LONG);

        //Displaying the snackbar using the show method()
        //     snackbar.show();

        TextView versao = (TextView) findViewById(R.id.versao);

        versao.setText(MensagemHelper.getVersionName(this));

        versaoinfo = versao.getText().toString();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar titulo = getSupportActionBar();
        titulo.setTitle("Júnior");


        Button btnEnter = (Button) findViewById(R.id.login_btn_enter);

        initViews();

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.login_btn_enter)
                    //       Snackbar.make(ln, "Olá", Snackbar.LENGTH_SHORT).show();
                    if (validateFields()) {

                        //        Snackbar.make(ln, "Olá", Snackbar.LENGTH_SHORT).show();
                        // Toast.makeText(this, "Autenticado com sucesso!", Toast.LENGTH_LONG).show();
                        Toast.makeText(MainAcitivity.this, resources.getString(R.string.login_auth_ok), Toast.LENGTH_SHORT).show();

                        ProgressDialogo("Aguarde", "Processando....", "Ok");

                        //   Progressiva();
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
          MensagemHelper.clearErrorFields(edtUser);
        }
    }

    private boolean validateFields() {
        String login = edtUser.getText().toString().trim();
        String senha = edtPassword.getText().toString().trim();


        if (!login.isEmpty() && !senha.isEmpty()) {

            UsuarioDao dao = new UsuarioDao();

            Usuario usuario = new Usuario();

            Usuario user = dao.Autenticar(edtUser.getText().toString().trim(),
                    edtPassword.getText().toString().trim()
            );


/*
            if (dao == null){
                Toast.makeText(this, "Erro!", Toast.LENGTH_LONG).show();
            }*/

            if (user.getLogin().equals(edtUser.getText().toString().trim()) &&
                    (user.getSenha().equals(edtPassword.getText().toString().trim()))) {
            }
        } else {
            Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show();
            return (!isEmptyFields(login, senha) && hasSizeValid(login, senha));
        }
        //   String login = edtUser.getText().toString().trim();
        //   String senha = edtPassword.getText().toString().trim();
        return (!isEmptyFields(login, senha) && hasSizeValid(login, senha));
    }

    // private boolean isEmptyFields(String user, String pass) {
    public boolean isEmptyFields(String user, String pass) {
        if (TextUtils.isEmpty(user)) {
            edtUser.requestFocus(); //seta o foco para o campo user
            //edtUser.setError(resources.getString(R.string.login_user_required));
            edtUser.setError(Html.fromHtml("<font color='#00FF00'>informe o login</font>"));
            return true;

        } else if (TextUtils.isEmpty(pass)) {
            edtPassword.requestFocus(); //seta o foco para o campo password
            //edtPassword.setError(resources.getString(R.string.login_password_required));
            edtPassword.setError(Html.fromHtml("<font color='#00FF00'>informe a senha</font>"));
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
/*    private void clearErrorFields(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setError(null);
        }
    }*/


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

    //  private void ProgressDialogo(String title, String message) {
    private void ProgressDialogo(String title, String message, String buttonText) {
        dialog = new ProgressDialog(this);
        dialog.setTitle(title);
        dialog.setMessage(message);

        dialog.setButton(buttonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// Use either finish() or return() to either close the activity or just the dialog
                dialog.dismiss();
                //  return;
            }
        });
        dialog.show();
    }


    private void ProgressDialogo(String title, String message) {
        final ProgressDialog pd = new ProgressDialog(this);

        // Set progress dialog style spinner
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);

      /*  // Set the progress dialog title and message
        pd.setTitle("Aguarde");
        pd.setMessage("acessando...");*/

        // Set the progress dialog background color
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD4D9D0")));

        pd.setIndeterminate(false);

        // Finally, show the progress dialog
        pd.show();
    }
/*
    // intent qe define o conteudo que sera compartilhado
    private Intent getDefaultItent() {
        Intent it = new Intent(Intent.ACTION_SEND);
        it.setType("text*/
/*");
        it.putExtra(Intent.EXTRA_TEXT, "Texto para compartilhar");
        return it;
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // infla o menu com os botoes da action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);

        /*// pesquisa
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView pesquisa = (SearchView) MenuItemCompat.getActionView(item);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
           pesquisa.setOnQueryTextListener(onSearch());
        }*/

    /*    // compartilhar
       MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider share = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        share.setShareIntent(getDefaultItent());*/
        return true;
    }


    // pequisa
    private SearchView.OnQueryTextListener onSearch() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {
                    toast("Buscar o texto: " + query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // novo texto
                    return false;
                }
            };
        }
        return null;
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            toast("Pesquisar");
            return true;
        } else if (id == R.id.action_refresh) {
            toast("Atualizar");
            return true;
        } else if (id == R.id.action_settings) {
            toast("Configurar");
            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_versao) {
            toast("Versão " + versaoinfo);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent().getBooleanExtra("Exite", false)) {
//
        }

    }

    public void exibirMensagemRodape(View v) {
        // ab.make(ln, "Olá", Snackbar.LENGTH_SHORT).show();
    }


}