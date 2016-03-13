package com.example.junior_carvalho.webserviceeclipseandroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.junior_carvalho.webserviceeclipseandroid.Dao.UsuarioDao;
import com.example.junior_carvalho.webserviceeclipseandroid.Dominio.Usuario;

import java.util.ArrayList;

public class MainActivityLogin extends AppCompatActivity {

    //
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
            // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private static boolean alreadyOpen = false;
    //
    //var global
    private ListView lvUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


       //  UsuarioDao dao = new UsuarioDao();
        //Usuario user = dao.buscaUsuarioPorId(29);
        //Log.d("ExemploWebService", user.toString());

        //  Usuario user = dao.Autenticar("abc","abc");
        // Log.d("ExemploWebService", user.getId() + user.getNome() + user.getLogin() + user.getNome());


        Button btAbreCadastro = (Button) findViewById(R.id.btnAbreTelaCadastro);

        lvUsuario = (ListView) findViewById(R.id.lstvUsuarios);
        // new Sincronismo().execute();

        // click botao
        btAbreCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //    Vibrator vibrar = (Vibrator) getSystemService(MainActivityLogin.VIBRATOR_SERVICE);

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
            public void onItemClick(AdapterView adapter, View view, int posicao, long id) {

                Usuario user = (Usuario) adapter.getItemAtPosition(posicao);

                long codigo = user.getId();

                Intent it = new Intent(getBaseContext(), Detalhes_Activity.class);
                //     it.putExtra("idCliente", id);
                it.putExtra("Codigo", codigo);
                startActivityForResult(it, 1);
                startActivity(it);

            }
        });

/*        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }*/
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (getIntent().getBooleanExtra("Exite", false)) {
//
        }


        UsuarioDao dao = new UsuarioDao();
        ArrayList<Usuario> lista = dao.buscarTodosUsuairos();
           Log.d("ExemploWebService", lista.toString());
        ArrayAdapter<Usuario> adpUsuario = new ArrayAdapter<Usuario>(this, android.R.layout.simple_list_item_1, lista);
        lvUsuario.setAdapter(adpUsuario);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // infla o menu com os botoes da action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // pesquisa
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView pesquisa = (SearchView) MenuItemCompat.getActionView(item);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            pesquisa.setOnQueryTextListener(onSearch());
        }
       /* // compartilhar
       MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider share = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        share.setShareIntent(getDefaultItent());
        */
        return true;
    }


    // pequisa
    private SearchView.OnQueryTextListener onSearch() {
        //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
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
        //}
    }

    // intent qe define o conteudo que sera compartilhado
    private Intent getDefaultItent() {
        Intent it = new Intent(Intent.ACTION_SEND);
        it.setType("text/*");
        it.putExtra(Intent.EXTRA_TEXT, "Texto para compartilhar");
        return it;
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
