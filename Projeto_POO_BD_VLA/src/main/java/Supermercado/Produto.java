package Supermercado;

import DAO.ProdutoDAO;

public class Produto implements Comparable<Produto>{
    //Atributos do produto
    private String nome;
    private String codBarras;
    private int codInterno;
    private float preco;
    private int estoque;
    private Setor setor;
    private int idSetor;

    //Conectar com o produto do banco de dados
    ProdutoDAO produtoDAO = new ProdutoDAO();

    //Ordenar os produto em relação a quantidade no estoque
    @Override
    public int compareTo(Produto o) {
        return Integer.compare(this.estoque, o.getEstoque());
    }

    //Construtor do produto
    public Produto(String nome, String codBarras, float preco, int estoque, Setor setor) {
        this.nome = nome;
        this.codBarras = codBarras;
        this.codInterno = produtoDAO.selectMaiorCodInternoProduto()+1;
        this.preco = preco;
        this.estoque = estoque;
        this.setor = setor;
    }
    public Produto(String nome, String codBarras, int codInterno, float preco, int estoque, int idSetor) {
        this.nome = nome;
        this.codBarras = codBarras;
        this.codInterno = codInterno;
        this.preco = preco;
        this.estoque = estoque;
        this.idSetor = idSetor;
    }

    //Getters
    public String getNome() {
        return nome;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public int getCodInterno() {
        return codInterno;
    }

    public float getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public Setor getSetor() {
        return setor;
    }

    public int getIdSetor() {
        return idSetor;
    }
}
