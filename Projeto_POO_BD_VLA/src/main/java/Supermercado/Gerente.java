package Supermercado;

public class Gerente extends Funcionario{
    //Construtor do gerente
    public Gerente(String nome, String dataNascimento, String CPF, String telefone, String email, Setor setor) {
        super(nome, dataNascimento, CPF, telefone, email, setor);
    }
    public Gerente(String nome, String dataNascimento, String CPF, int registro, String telefone, String email, int idSetor) {
        super(nome, dataNascimento, CPF, registro, telefone, email, idSetor);
    }
}
