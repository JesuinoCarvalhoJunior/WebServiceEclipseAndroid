package com.example.junior_carvalho.webserviceeclipseandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.junior_carvalho.webserviceeclipseandroid.Dao.UsuarioDao;
import com.example.junior_carvalho.webserviceeclipseandroid.Dominio.Usuario;


public class BuscaPorIdActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitybuscaporid);

        // recupera os dados
        final EditText edtCodigoId = (EditText) findViewById(R.id.edtCodigoId);
        final EditText edtNomePesquisado = (EditText) findViewById(R.id.edtNomePesquisado);
        final EditText edtIdadePesquisada = (EditText) findViewById(R.id.edtIdadePesquisada);


        Button btnPesquisarId = (Button) findViewById(R.id.btnPesquisarId);
        Button btnVoltartelaListar = (Button) findViewById(R.id.btnVoltartelaListar);
        Button btnLimparPesqId = (Button) findViewById(R.id.btnLimparPesqId);

        // onclick Cadastro
        btnPesquisarId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText edtCodigoId = (EditText) findViewById(R.id.edtCodigoId);

                if (!edtCodigoId.getText().toString().isEmpty()) {

                    UsuarioDao dao = new UsuarioDao();
                    Usuario user = dao.buscaUsuarioPorId(Integer.parseInt(edtCodigoId.getText().toString().trim()));


                    edtNomePesquisado.setText(user.getNome());
                    //ed.setText(Integer.toString(x));
                    //ed.setText(String.valueOf(x));
                    edtIdadePesquisada.setText(Integer.toString(user.getIdade()));
                } else {
                   String codigo = edtCodigoId.getText().toString();

                    if (TextUtils.isEmpty(codigo)) {
                        edtCodigoId.setError(Html.fromHtml("<font color='#145A14'>Informe o código</font>"));
                        //edtCodigoId.requestFocus();
                        return;
                    }
                }
            }
        });


        btnLimparPesqId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtCodigoId.requestFocus();
                edtCodigoId.setError(null);
                edtCodigoId.setText("");
                edtNomePesquisado.setText("");
                edtIdadePesquisada.setText("");
            }
        });
        // click botao voltar
        btnVoltartelaListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cadstro Activity  chama a MainActivity
                Intent it = new Intent(BuscaPorIdActivity.this, MainActivityLogin.class);
                startActivity(it);
            }
        });
    }
}
