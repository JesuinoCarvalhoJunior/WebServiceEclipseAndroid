package com.example.junior_carvalho.webserviceeclipseandroid.Dao.Servicos;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;


/**
 * Created by Junior_Carvalho on 04/10/2015.
 */
public class ServicosBase {

    public final String URL = "http://192.168.0.3:8080/WebServiceEclipseAndroid/services/UsuarioDao?wsdl";
    //    public static final String NAMESPACE = "http://android.webservice.com.br";
    public static final String NAMESPACE = "http://Dao.android.webservice.com.br";

    public static final String INSERIR = "inserirUsuario";
    public static final String EXCLUIR = "excluirUsuario";
    public static final String ATUALIZAR = "atualizarUsuario";
    public static final String BUSCAR_TODOS_USUARIOS = "buscarTodosUsuairos";
    public static final String BUSCAR_POR_ID = "buscaUsuarioPorId";
    public static final String AUTENTICAR = "Autenticar";

    //public static final String SOAP_ACTION = "http://192.168.254.1:8080/WebServiceEclipseAndroid/services/UsuarioDao";

}
