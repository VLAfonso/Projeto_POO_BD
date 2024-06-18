package Supermercado;

import DAO.FuncionarioDAO;
import DAO.GerenteDAO;

public class Funcionario implements Comparable<Funcionario> {
    //Atributos do funcionário
    private String nome;
    private String dataNascimento;
    private String  CPF;
    private int registro;
    private String telefone;
    private String email;
    private Gerente gerente;
    private Setor setor;
    private int idSetor;
    private int registroGerente;

    //Conectar funcionário e gerente com o banco de dados
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    GerenteDAO gerenteDAO = new GerenteDAO();

    //Ordenar os funcionários em ordem alfabética de nome
    @Override
    public int compareTo(Funcionario o) {
        return this.nome.compareTo(o.getNome());
    }

    //Construtores do funcionário
    public Funcionario(String nome, String dataNascimento, String CPF, String telefone, String email, Gerente gerente, Setor setor) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.CPF = CPF;
        if(funcionarioDAO.selectMaiorRegistroFuncionario() > gerenteDAO.selectMaiorRegistroGerente()){
            this.registro = funcionarioDAO.selectMaiorRegistroFuncionario()+1;
        }
        else{
            this.registro = gerenteDAO.selectMaiorRegistroGerente()+1;
        }
        this.telefone = telefone;
        this.email = email;
        this.gerente = gerente;
        this.setor = setor;
    }
    public Funcionario(String nome, String dataNascimento, String CPF, String telefone, String email, Setor setor) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.CPF = CPF;
        if(funcionarioDAO.selectMaiorRegistroFuncionario() > gerenteDAO.selectMaiorRegistroGerente()){
            this.registro = funcionarioDAO.selectMaiorRegistroFuncionario()+1;
        }
        else{
            this.registro = gerenteDAO.selectMaiorRegistroGerente()+1;
        }
        this.telefone = telefone;
        this.email = email;
        this.setor = setor;
    }
    public Funcionario(String nome, String dataNascimento, String CPF, int registro, String telefone, String email, int idSetor, int registroGerente) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.CPF = CPF;
        this.registro = registro;
        this.telefone = telefone;
        this.email = email;
        this.idSetor = idSetor;
        this.registroGerente = registroGerente;
    }
    public Funcionario(String nome, String dataNascimento, String CPF, int registro, String telefone, String email, int idSetor) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.CPF = CPF;
        this.registro = registro;
        this.telefone = telefone;
        this.email = email;
        this.idSetor = idSetor;
    }

    //Getters
    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getCPF() {
        return CPF;
    }

    public int getRegistro() {
        return registro;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public Setor getSetor() {
        return setor;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public int getRegistroGerente() {
        return registroGerente;
    }
}
