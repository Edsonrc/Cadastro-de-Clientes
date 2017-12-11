package com.edsonrczarneski.projetofinal;

/**
 * Created by Edson Ricardo Czarneski on 09/12/2017.
 */
public class Pessoa {
    //Declarando atributos
    private String nome;
    private String cpf;
    private String idade;
    private String telefone;
    private String email;

    //Criando o construtor
    Pessoa (String nome,String cpf, String idade, String telefone, String email ){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
        this.email = email;
    }

    //Criando Setters e Getters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNascimento() {
        return idade;
    }

    public void setNascimento(String nascimento) {
        this.idade = nascimento;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
