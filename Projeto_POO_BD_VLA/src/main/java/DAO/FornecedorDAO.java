package DAO;

import Supermercado.Fornecedor;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FornecedorDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertFornecedor(Fornecedor fornecedor) {

        connectToDB();

        String sql = "INSERT INTO Fornecedor (ID, Nome, Telefone, Email, Empresa, Gerente_Registro) values(?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,fornecedor.getId());
            pst.setString(2, fornecedor.getNome());
            pst.setString(3, fornecedor.getTelefone());
            pst.setString(4, fornecedor.getEmail());
            pst.setString(5, fornecedor.getEmpresa());
            pst.setInt(6, fornecedor.getGerente().getRegistro());
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
    public boolean deleteFornecedor(int id) {
        connectToDB();
        String sql = "DELETE FROM Fornecedor where ID=?";
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
    public Map<Integer, Fornecedor> selectFornecedor() {
        Map<Integer, Fornecedor> fornecedores = new HashMap<>();
        connectToDB();
        String sql = "SELECT * FROM Fornecedor";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                Fornecedor fornecedorAux = new Fornecedor(rs.getString("Nome"), rs.getString("Empresa"), rs.getString("Telefone"), rs.getString("Email"), rs.getInt("ID"), rs.getInt("Gerente_Registro"));

                fornecedores.put(fornecedorAux.getId(), fornecedorAux);
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
        return fornecedores;
    }

    //Selecionar maior id
    public int selectMaiorIdFornecedor() {
        connectToDB();
        String sql = "SELECT MAX(ID) FROM Fornecedor";
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

}
