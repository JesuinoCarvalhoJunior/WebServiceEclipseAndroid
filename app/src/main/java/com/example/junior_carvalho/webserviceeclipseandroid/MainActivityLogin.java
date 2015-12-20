package com.example.junior_carvalho.webserviceeclipseandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.junior_carvalho.webserviceeclipseandroid.Dao.UsuarioDao;
import com.example.junior_carvalho.webserviceeclipseandroid.Dominio.Usuario;
import com.example.junior_carvalho.webserviceeclipseandroid.Helpers.MensagemHelper;

import java.util.ArrayList;

public class MainActivityLogin extends AppCompatActivity {

    //var global
    private ListView lvUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

         UsuarioDao dao = new UsuarioDao();

        //Usuario user = dao.buscaUsuarioPorId(29);
        //Log.d("ExemploWebService", user.toString());

        Usuario user = dao.Autenticar("abc","abc");
        Log.d("ExemploWebService", user.getId() + user.getNome() + user.getLogin() + user.getNome());


        Button btAbreCadastro = (Button) findViewById(R.id.btnAbreTelaCadastro);

        lvUsuario = (ListView) findViewById(R.id.lstvUsuarios);
        // new Sincronismo().execute();

        // click botao
        btAbreCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Vibrator vibrar = (Vibrator) getSystemService(MainActivityLogin.VIBRATOR_SERVICE);

          /*      ProgressDialog progressDialog = new ProgressDialog(MainActivityLogin.this);
                // progressDialog.setTitle("Sistema");
                progressDialog.setMessage("Aguarde...");
                //  progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.show();*/


             //  MensagemHelper msg = new MensagemHelper();

              //  msg.ProgressDialogo("Aguarde","Processando...","Ok");

                // MainActivity chama a Cadstro Activity
                Intent it = new Intent(MainActivityLogin.this, CadastroActivity.class);
                startActivity(it);
            }
        });


        lvUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapter, View viw, int posicao, long id) {

                Usuario user = (Usuario) adapter.getItemAtPosition(posicao);

                long codigo = user.getId();

                Intent it = new Intent(getBaseContext(), Detalhes_Activity.class);
                //     it.putExtra("idCliente", id);
                it.putExtra("Codigo", codigo);
                startActivityForResult(it, 1);
                startActivity(it);
            }
        });

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


        UsuarioDao dao = new UsuarioDao();
        ArrayList<Usuario> lista = dao.buscarTodosUsuairos();
        //      Log.d("ExemploWebService", lista.toString());
        ArrayAdapter<Usuario> adpUsuario = new ArrayAdapter<Usuario>(this, android.R.layout.simple_list_item_1, lista);
        lvUsuario.setAdapter(adpUsuario);
    }
}

//    boolean resultado = dao.inserirUsuario(new Usuario(0,"Lina", 34));
//      Log.d("ExemploWebService", resultado + "");

//      ArrayList<Usuario> lista = dao.buscarTodosUsuairos();
//    Log.d("ExemploWebService", lista.toString());

//Usuario user = dao.buscaUsuarioPorId(5);
//        Log.d("ExemploWebService", user.toString());

//   boolean retorno = dao.atualizarUsuario(new Usuario(4, "Lina 1", 35));
//   Log.d("ExemploWebService", retorno + "");

//  boolean res = dao.excluirUsuario(6);
// Log.d("ExemploWebService", res + "");


//      ArrayList<Usuario> lista = dao.buscarTodosUsuairos();
//    Log.d("ExemploWebService", lista.toString());
