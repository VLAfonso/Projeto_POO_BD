package Supermercado;

import DAO.FornecedorDAO;

import java.util.ArrayList;

public class Fornecedor {
    private String nome;
    private String empresa;
    private String telefone;
    private String email;
    private int id;
    private Gerente gerente;
    private int registroGerente;
    private ArrayList<Produto> produtos = new ArrayList<>();

    //Conectar fornecedor com o banco de dados
    FornecedorDAO fornecedorDAO = new FornecedorDAO();

    //Construtor do fornecedor
    public Fornecedor(String nome, String empresa, String telefone, String email, int id, Gerente gerente) {
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
        this.id = fornecedorDAO.selectMaiorIdFornecedor()+1;
        this.registroGerente = registroGerente;
    }

    //Adicionar produtos
    public void adicionarProduto(Produto produto){
        for (int i=0; i< produtos.size(); i++){
            if(produtos.get(i) ==null){
                produtos.set(i, produto);
                break;
            }
        }
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

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }
}
