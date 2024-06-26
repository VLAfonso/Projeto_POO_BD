package DAO;

import Supermercado.Gerente;
import Supermercado.Setor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GerenteDAO extends ConnectionDAO{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertGerente(Gerente gerente) {

        connectToDB();

        String sql = "INSERT INTO Gerente (Registro, Nome, CPF, DataNascimento, Telefone, Email, Setor_ID) values(?,?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,gerente.getRegistro());
            pst.setString(2, gerente.getNome());
            pst.setString(3, gerente.getCPF());
            pst.setString(4, gerente.getDataNascimento());
            pst.setString(5, gerente.getTelefone());
            pst.setString(6, gerente.getEmail());
            pst.setInt(7, gerente.getSetor().getId());
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
    public boolean updateGerenteSetor(int registroGerente, int idSetor) {
        connectToDB();
        String sql = "UPDATE Gerente SET Setor_ID=? where Registro=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idSetor);
            pst.setInt(2, registroGerente);
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
    public boolean deleteGerente(int registro) {
        connectToDB();
        String sql = "DELETE FROM Gerente where registro=?";
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
    public Map<Integer,Gerente> selectGerente() {
        Map<Integer, Gerente> gerentes = new HashMap<>();
        connectToDB();
        String sql = "SELECT * FROM Gerente";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                Gerente gerenteAux = new Gerente(rs.getString("Nome"), rs.getString("DataNascimento"), rs.getString("CPF"), rs.getInt("Registro"), rs.getString("Telefone"), rs.getString("Email"), rs.getInt("Setor_ID"));

                gerentes.put(gerenteAux.getRegistro(), gerenteAux);
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
        return gerentes;
    }

    //Selecionar apenas um gerente
    public String selectNomeGerente(int registro) {
        String nome = "";
        connectToDB();
        String sql = "SELECT * FROM Gerente WHERE Registro = "+registro;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()){
                nome = rs.getString("Nome");
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
        return nome;
    }

    //Verificar se há um gerente responsável pelo setor
    public boolean selectSetorGerente(int idSetor) {
        boolean haGerente = false;
        String nomeSetor = "";
        connectToDB();
        String sql = "SELECT * FROM Gerente WHERE Setor_ID = "+idSetor;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()){
                nomeSetor = rs.getString("Setor_ID");

            }

            if (!Objects.equals(nomeSetor, "")) {
                haGerente = true;
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
        return haGerente;
    }

    //Selecionar maior registro
    public int selectMaiorRegistroGerente() {
        connectToDB();
        String sql = "SELECT MAX(Registro) FROM Gerente";
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

    //Contabilizar quantidade de gerentes
    public int qtdGerente() {
        connectToDB();
        String sql = "SELECT COUNT(Registro) FROM Gerente";
        int qtdGerentes = 0;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                qtdGerentes = rs.getInt("COUNT(Registro)");
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
        return qtdGerentes;
    }
}
