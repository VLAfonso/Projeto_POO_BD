package DAO;

import Supermercado.Fornecedor;
import Supermercado.Produto;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProdutoDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertProduto(Produto produto) {

        connectToDB();

        String sql = "INSERT INTO Produto (CodigoInterno, Nome, CodigoDeBarras, Preco, Estoque, Setor_ID) values(?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, produto.getCodInterno());
            pst.setString(2, produto.getNome());
            pst.setString(3, produto.getCodBarras());
            pst.setFloat(4, produto.getPreco());
            pst.setInt(5, produto.getEstoque());
            pst.setInt(6, produto.getSetor().getId());
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

    //Inserir produtos e sua relação com o fornecedor
    public boolean insertProdutoFornecedor(Produto produto, Fornecedor fornecedor) {

        connectToDB();

        String sql = "INSERT INTO Fornecedor_Produto (Fornecedor_ID, Produto_CodigoInterno) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, fornecedor.getId());
            pst.setInt(2, produto.getCodInterno());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
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

    //UPDATE estoque
    public boolean updateProdutoEstoque(int codInterno, int quantidade) {
        connectToDB();
        String sql = "UPDATE Produto SET Estoque=Estoque+? where CodigoInterno=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, quantidade);
            pst.setInt(2, codInterno);
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

    //UPDATE setor
    public boolean updateProdutoSetor(int codInterno, int idSetor) {
        connectToDB();
        String sql = "UPDATE Produto SET Setor_ID=? where CodigoInterno=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idSetor);
            pst.setInt(2, codInterno);
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
    public boolean deleteProduto(int codInterno) {
        connectToDB();
        String sql = "DELETE FROM Produto where CodigoInterno=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, codInterno);
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
    public Map<Integer, Produto> selectProduto() {
        Map<Integer, Produto> produtos = new HashMap<>();
        connectToDB();
        String sql = "SELECT * FROM Produto";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                Produto produtoAux = new Produto(rs.getString("Nome"), rs.getString("CodigoDeBarras"), rs.getInt("CodigoInterno"), rs.getFloat("Preco"), rs.getInt("Estoque"), rs.getInt("Setor_ID"));

                produtos.put(produtoAux.getCodInterno(), produtoAux);
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
        return produtos;
    }

    //Selecionar maior código interno
    public int selectMaiorCodInternoProduto() {
        connectToDB();
        String sql = "SELECT MAX(CodigoInterno) FROM Produto";
        int maiorCodInterno = 0;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                maiorCodInterno = rs.getInt("MAX(CodigoInterno)");
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
        return maiorCodInterno;
    }

    //Contabilizar quantidade de produtos
    public int qtdProduto() {
        connectToDB();
        String sql = "SELECT COUNT(CodigoInterno) FROM Produto";
        int qtdProdutos = 0;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                qtdProdutos = rs.getInt("COUNT(CodigoInterno)");
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
        return qtdProdutos;
    }
}

