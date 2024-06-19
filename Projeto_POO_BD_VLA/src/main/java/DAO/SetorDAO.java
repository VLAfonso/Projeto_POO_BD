package DAO;

import Supermercado.Setor;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SetorDAO extends ConnectionDAO{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertSetor(Setor setor) {

        connectToDB();

        String sql = "INSERT INTO Setor (ID, Nome) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, setor.getId());
            pst.setString(2, setor.getNome());

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

    //DELETE
    public boolean deleteSetor(int id) {
        connectToDB();
        String sql = "DELETE FROM Setor where ID=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
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
    public Map<Integer, Setor> selectSetor() {
        Map<Integer, Setor> setores = new HashMap<>();
        connectToDB();
        String sql = "SELECT * FROM Setor";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                Setor setorAux = new Setor(rs.getInt("ID"), rs.getString("Nome"));

                setores.put(setorAux.getId(), setorAux);
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
        return setores;
    }

    //Selecionar apenas um setor pelo id
    public String selectNomeSetor(int id) {
        String nome = "";
        connectToDB();
        String sql = "SELECT * FROM Setor WHERE ID = "+id;

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

    //Selecionar maior id do setor
    public int selectMaiorIdSetor() {
        connectToDB();
        String sql = "SELECT MAX(ID) FROM SETOR";
        int maiorID = 0;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                maiorID = rs.getInt("MAX(ID)");
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
        return maiorID;
    }

    //Contabilizar quantidade de setores
    public int qtdSetor() {
        connectToDB();
        String sql = "SELECT COUNT(ID) FROM Setor";
        int qtdSetores = 0;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                qtdSetores = rs.getInt("COUNT(ID)");
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
        return qtdSetores;
    }
}
