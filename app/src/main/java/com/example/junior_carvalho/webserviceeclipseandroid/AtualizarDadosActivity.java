package com.example.junior_carvalho.webserviceeclipseandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.junior_carvalho.webserviceeclipseandroid.Dao.UsuarioDao;
import com.example.junior_carvalho.webserviceeclipseandroid.Dominio.Usuario;

public class AtualizarDadosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityatualizardados);

        // recupera os dados
        final EditText edtCodId = (EditText) findViewById(R.id.edtCodId);
        final EditText edtNomeAlterar = (EditText) findViewById(R.id.edtNomeAlterar);
        final EditText edtIdadeAlterar = (EditText) findViewById(R.id.edtIdadeAlterar);


        Button btnPesqIdAlterar = (Button) findViewById(R.id.btnPesqIdAlterar);
        Button btnAlterarDados = (Button) findViewById(R.id.btnAlterarDados);
        Button btnVoltartelaListar = (Button) findViewById(R.id.btnVoltartelaListar);

        // onclick Cadastro
        btnPesqIdAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText edtCodigoId = (EditText) findViewById(R.id.edtCodId);

                if (!edtCodigoId.getText().toString().isEmpty()) {

                    UsuarioDao dao = new UsuarioDao();
                    Usuario user = dao.buscaUsuarioPorId(Integer.parseInt(edtCodId.getText().toString()));

                    edtNomeAlterar.setText(user.getNome());
                    //ed.setText(Integer.toString(x));
                    //ed.setText(String.valueOf(x));
                    edtIdadeAlterar.setText(Integer.toString(user.getIdade()));
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


        btnAlterarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer codigo = Integer.parseInt(edtCodId.getText().toString().trim());
                String nome = edtNomeAlterar.getText().toString().trim();
                String idade = edtIdadeAlterar.getText().toString().trim();

                if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(idade))
                {
                    if (nome.isEmpty()){
                        edtNomeAlterar.requestFocus();
                        edtNomeAlterar.setError(Html.fromHtml("<font color='#145A14'>Informe o nome</font>"));
                        return;
                    } else if (idade.isEmpty()){
                        edtIdadeAlterar.requestFocus();
                        edtIdadeAlterar.setError(Html.fromHtml("<font color='#145A14'>Informe a idade</font>"));
                        return;
                    }

                }

                UsuarioDao dao = new UsuarioDao();

                boolean retorno = dao.atualizarUsuario(
                        (new Usuario(codigo, nome, Integer.parseInt(idade),"","" )
                        )
                );


                if (retorno) {
                    Toast.makeText(AtualizarDadosActivity.this, "registro alterados com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(AtualizarDadosActivity.this, "Erro ao alterar registro", Toast.LENGTH_LONG).show();
                }
            }
        });

/*        btnLimparPesqId.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
               edtCodigoId.requestFocus();
                edtCodigoId.setError(null);
                edtCodigoId.setText("");
                edtNomePesquisado.setText("");
                edtIdadePesquisada.setText("");
            }
        });*/


        // click botao voltar
        btnVoltartelaListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             // Cadstro Activity  chama a MainActivity
                Intent it = new Intent(AtualizarDadosActivity.this, MainActivityLogin.class);
                startActivity(it);
            }
        });
    }
}
