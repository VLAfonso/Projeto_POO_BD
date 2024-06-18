package DAO;

import Supermercado.Funcionario;

import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertFuncionario(Funcionario funcionario) {

        connectToDB();

        String sql = "INSERT INTO Funcionario (Registro, Nome, CPF, DataNascimento, Telefone, Email, Gerente_Registro, Setor_ID) values(?,?,?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,funcionario.getRegistro());
            pst.setString(2, funcionario.getNome());
            pst.setString(3, funcionario.getCPF());
            pst.setString(4, funcionario.getDataNascimento());
            pst.setString(5, funcionario.getTelefone());
            pst.setString(6, funcionario.getEmail());
            pst.setInt(7, funcionario.getGerente().getRegistro());
            pst.setInt(8, funcionario.getSetor().getId());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //UPDATE
    public boolean updateFuncionarioNome(int registro, String nome) {
        connectToDB();
        String sql = "UPDATE Funcionario SET nome=? where registro=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setInt(2, registro);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //DELETE
    public boolean deleteFuncionario(int registro) {
        connectToDB();
        String sql = "DELETE FROM Funcionario where registro=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, registro);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //SELECT
    public ArrayList<Funcionario> selectFuncionario() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Funcionario";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                Funcionario funcionarioAux = new Funcionario(rs.getString("Nome"), rs.getString("DataNascimento"), rs.getString("CPF"), rs.getInt("Registro"), rs.getString("Telefone"), rs.getString("Email"), rs.getInt("Setor_ID"), rs.getInt("Gerente_Registro"));

                funcionarios.add(funcionarioAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return funcionarios;
    }

    //Selecionar maior registro
    public int selectMaiorRegistroFuncionario() {
        connectToDB();
        String sql = "SELECT MAX(Registro) FROM FUNCIONARIO";
        int maiorRegistro = 0;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                maiorRegistro = rs.getInt("MAX(Registro)");
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return maiorRegistro;
    }
}
