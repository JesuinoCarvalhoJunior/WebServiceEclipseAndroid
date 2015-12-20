package com.example.junior_carvalho.webserviceeclipseandroid.Dominio;

/**
 * Created by Junior_Carvalho on 19/09/2015.
 */
public class Usuario {

    private int id;
    private String nome;
    private int idade;
    private String login;
    private String senha;

    public Usuario() {

    }

    public Usuario(int id, String nome, int idade, String login, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.login = login;
        this.senha = senha;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String  getLogin() { return login;}

    public void  setLogin(String login) {this.login = login;}

    public String getSenha() {return senha; }

    public void  setSenha(String senha) {this.senha = senha;}


    //sobrescrecer metodo toString
/*
    public String toString() {
        return "Usuario [id=" + id + ",nome=" + nome + ", idade=" + idade + "]";
    }

*/
    public String toString() {
        return nome;

    }

}