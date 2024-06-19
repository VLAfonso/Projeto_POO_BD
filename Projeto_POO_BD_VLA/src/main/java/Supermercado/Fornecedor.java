package Supermercado;

import DAO.FornecedorDAO;

import java.util.ArrayList;

public class Fornecedor {
    //Atributos do fornecedor
    private String nome;
    private String empresa;
    private String telefone;
    private String email;
    private int id;
    private Gerente gerente;
    private int registroGerente;

    //Conectar fornecedor com o banco de dados
    FornecedorDAO fornecedorDAO = new FornecedorDAO();

    //Construtor do fornecedor
    public Fornecedor(String nome, String empresa, String telefone, String email, Gerente gerente) {
        this.nome = nome;
        this.empresa = empresa;
        this.telefone = telefone;
        this.email = email;
        this.id = fornecedorDAO.selectMaiorIdFornecedor()+1;
        this.gerente = gerente;
    }

    public Fornecedor(String nome, String empresa, String telefone, String email, int id, int registroGerente) {
        this.nome = nome;
        this.empresa = empresa;
        this.telefone = telefone;
        this.email = email;
        this.id = id;
        this.registroGerente = registroGerente;
    }

    //Getters
    public String getNome() {
        return nome;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public int getRegistroGerente() {
        return registroGerente;
    }
}
