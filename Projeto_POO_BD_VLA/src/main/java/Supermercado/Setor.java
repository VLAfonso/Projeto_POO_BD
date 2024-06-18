package Supermercado;

import DAO.SetorDAO;

public class Setor {
    private int id;
    private String nome;

    //Conectar com o setor do banco de dados
    SetorDAO setorDAO = new SetorDAO();

    //Construtor do setor
    public Setor(String nome) {
        this.id = setorDAO.selectMaiorIdFuncionario()+1;
        this.nome = nome;
    }
    public Setor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
