package com.example.junior_carvalho.webserviceeclipseandroid.Dao.Interfaces;

import com.example.junior_carvalho.webserviceeclipseandroid.Dominio.Usuario;

import java.util.ArrayList;

/**
 * Created by Junior_Carvalho on 04/10/2015.
 */
public interface IUsuarioDao {

         boolean inserirUsuario(Usuario usuario);

        //
         boolean atualizarUsuario(Usuario usuario);
        //
         boolean excluirUsuario(Usuario usuario);
        //
         ArrayList<Usuario> buscarTodosUsuairos();
        //
         Usuario buscaUsuarioPorId(int id);

        // sobrecarga
         boolean excluirUsuario(int id);
// **
         Usuario Autenticar(String login, String senha);

}
