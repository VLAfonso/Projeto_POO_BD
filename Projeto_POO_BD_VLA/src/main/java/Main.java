import DAO.*;
import Supermercado.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Criar DAOs
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        SetorDAO setorDAO = new SetorDAO();
        GerenteDAO gerenteDAO = new GerenteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();

        //Criar Arrays de cada elemento do supermercado
        Map<Integer, Funcionario> funcionarios;
        Map<Integer, Gerente> gerentes;
        Map<Integer, Produto> produtos;
        Map<Integer, Setor> setores;
        Map<Integer, Fornecedor> fornecedores;

        //Criar entrada de dados
        Scanner entrada = new Scanner(System.in);

        //Criar menu
        boolean flagMenu = true; //controle do menu
        boolean flagCadListAtAp = true; //controle de cadastrar, listar, atualizar e apagar
        boolean flagLeitura = true; //controle de leitura de id e registro
        boolean flagProduto = true; //controle de cadastro de produto
        int opMenu; //opção do menu
        int opCadListAtAp; //opção para cadastrar e listar
        int opLeitura; //opção para leitura de id e registro
        int opProduto; //opção para tipo de cadastro de produto
        while (flagMenu) {
            System.out.println("----------------------------------------------");
            System.out.println("Bem-vindo ao menu do Supermercado!");
            System.out.println("1- Cadastrar;");
            System.out.println("2- Listar;");
            System.out.println("3- Atualizar;");
            System.out.println("4- Apagar;");
            System.out.println("5- Ordenar os funcionários em ordem alfabética de nome;");
            System.out.println("6- Ordenar os produto em relação a quantidade no estoque;");
            System.out.println("7- Sair do menu.");
            opMenu = entrada.nextInt();
            System.out.println("----------------------------------------------");

            switch (opMenu){
                //Cadastrar
                case(1):
                    flagCadListAtAp = true;
                    while (flagCadListAtAp) {
                        System.out.println("O que você deseja cadastrar?");
                        System.out.println("1- Funcionário;");
                        System.out.println("2- Gerente;");
                        System.out.println("3- Produto;");
                        System.out.println("4- Setor;");
                        System.out.println("5- Fornecedor;");
                        System.out.println("6- Sair do cadastro;");
                        opCadListAtAp = entrada.nextInt();
                        System.out.println("----------------------------------------------");
                        switch (opCadListAtAp) {
                            //Cadastrar funcionário
                            case (1):
                                if(setorDAO.qtdSetor()==0 || gerenteDAO.qtdGerente()==0){
                                    System.out.println("Não é possível cadastrar um funcionário!");
                                    System.out.println("Não há setores ou gerentes cadastrados!");
                                } else{
                                    System.out.println("Adicione as informações do funcionário");
                                    System.out.println("Nome:");
                                    entrada.nextLine();
                                    String nome = entrada.nextLine();

                                    System.out.println("Data de nascimento:");
                                    String dataNascimento = entrada.nextLine();

                                    System.out.println("CPF:");
                                    String CPF = entrada.nextLine();

                                    System.out.println("Telefone:");
                                    String telefone = entrada.nextLine();

                                    System.out.println("E-mail:");
                                    String email = entrada.nextLine();

                                    System.out.println("Selecione um setor:");
                                    //Listar setores do BD
                                    setores = setorDAO.selectSetor();
                                    Setor setor = null;
                                    flagLeitura = true;
                                    while (flagLeitura) {
                                        setores.forEach((id, setorAux) -> {
                                            System.out.println("    "+id + " - " + setorAux.getNome() + ";");
                                        });
                                        opLeitura = entrada.nextInt();
                                        if (setores.get(opLeitura) != null) {
                                            setor = setores.get(opLeitura);
                                            flagLeitura = false;
                                        } else {
                                            System.out.println("Entre com uma opção válida!");
                                        }
                                    }

                                    System.out.println("Selecione um gerente:");
                                    //Listar gerentes do BD
                                    gerentes = gerenteDAO.selectGerente();
                                    Gerente gerente = null;
                                    flagLeitura = true;
                                    while (flagLeitura) {
                                        gerentes.forEach((id, gerenteAux) -> {
                                            System.out.println("    "+id + " - " + gerenteAux.getNome() + ";");
                                        });
                                        opLeitura = entrada.nextInt();
                                        if (gerentes.get(opLeitura) != null) {
                                            gerente = gerentes.get(opLeitura);
                                            flagLeitura = false;
                                        } else {
                                            System.out.println("Entre com uma opção válida!");
                                        }
                                    }

                                    //Adicionar funcionário ao BD
                                    funcionarioDAO.insertFuncionario(new Funcionario(nome, dataNascimento, CPF, telefone, email, gerente, setor));
                                }

                                flagCadListAtAp = false;
                                break;

                            //Cadastrar gerente
                            case (2):
                                if(setorDAO.qtdSetor()==0){
                                    System.out.println("Não é possível cadastrar um gerente!");
                                    System.out.println("Não há setores cadastrados!");
                                } else {
                                    System.out.println("Adicione as informações do gerente");
                                    System.out.println("Nome:");
                                    entrada.nextLine();
                                    String nomeG = entrada.nextLine();

                                    System.out.println("Data de nascimento:");
                                    String dataNascimentoG = entrada.nextLine();

                                    System.out.println("CPF:");
                                    String CPFG = entrada.nextLine();

                                    System.out.println("Telefone:");
                                    String telefoneG = entrada.nextLine();

                                    System.out.println("E-mail:");
                                    String emailG = entrada.nextLine();

                                    System.out.println("Selecione um setor:");
                                    //Listar setores do BD
                                    setores = setorDAO.selectSetor();
                                    Setor setorG = null;
                                    flagLeitura = true;
                                    while (flagLeitura) {
                                        setores.forEach((id, setorAux) -> {
                                            System.out.println("    "+id + " - " + setorAux.getNome() + ";");
                                        });
                                        opLeitura = entrada.nextInt();
                                        if ((setores.get(opLeitura) != null)&&(!gerenteDAO.selectSetorGerente(setores.get(opLeitura).getId()))) {
                                            setorG = setores.get(opLeitura);
                                            flagLeitura = false;
                                        } else if (gerenteDAO.selectSetorGerente(setores.get(opLeitura).getId())) {
                                            System.out.println("Esse setor já possui um gerente!");
                                        } else {
                                            System.out.println("Entre com uma opção válida!");
                                        }
                                    }

                                    //Adicionar gerente ao BD
                                    gerenteDAO.insertGerente(new Gerente(nomeG, dataNascimentoG, CPFG, telefoneG, emailG, setorG));

                                }

                                flagCadListAtAp = false;
                                break;

                            //Cadastrar produto
                            case (3):
                                if(fornecedorDAO.qtdFornecedor()==0){
                                    System.out.println("Não é possível cadastrar um produto!");
                                    System.out.println("Não há fornecedores cadastrados!");
                                } else {
                                    System.out.println("1- Novo produto;");
                                    System.out.println("2- Produto já existente;");
                                    opProduto = entrada.nextInt();
                                    flagProduto = true;
                                    while (flagProduto){
                                        switch (opProduto){
                                            case(1):
                                                if(setorDAO.qtdSetor()==0){
                                                    System.out.println("Não é possível cadastrar um produto!");
                                                    System.out.println("Não há setores cadastrados!");
                                                }
                                                else {
                                                    System.out.println("Adicione as informações do produto:");
                                                    System.out.println("Nome:");
                                                    entrada.nextLine();
                                                    String nomeP = entrada.nextLine();

                                                    System.out.println("Código de Barras:");
                                                    String codBarras = entrada.nextLine();

                                                    System.out.println("Preço:");
                                                    float preco = entrada.nextFloat();

                                                    System.out.println("Estoque:");
                                                    int estoque = entrada.nextInt();

                                                    System.out.println("Selecione um setor:");
                                                    //Listar setores do BD
                                                    setores = setorDAO.selectSetor();
                                                    Setor setorP = null;
                                                    flagLeitura = true;
                                                    while (flagLeitura) {
                                                        setores.forEach((id, setorAux) -> {
                                                            System.out.println("    "+id + " - " + setorAux.getNome() + ";");
                                                        });
                                                        opLeitura = entrada.nextInt();
                                                        if (setores.get(opLeitura) != null) {
                                                            setorP = setores.get(opLeitura);
                                                            flagLeitura = false;
                                                        } else {
                                                            System.out.println("Entre com uma opção válida!");
                                                        }
                                                    }

                                                    System.out.println("Selecione um fornecedor:");
                                                    //Listar fornecedores do BD
                                                    fornecedores = fornecedorDAO.selectFornecedor();
                                                    Fornecedor fornecedor = null;
                                                    flagLeitura = true;
                                                    while (flagLeitura) {
                                                        fornecedores.forEach((id, fornecedorAux)->{
                                                            System.out.println("    "+fornecedorAux.getId() + " - " + fornecedorAux.getNome() + ";");
                                                        });
                                                        opLeitura = entrada.nextInt();
                                                        if (fornecedores.get(opLeitura) != null) {
                                                            fornecedor = fornecedores.get(opLeitura);
                                                            flagLeitura = false;
                                                        } else {
                                                            System.out.println("Entre com uma opção válida!");
                                                        }
                                                    }

                                                    //Adicionar produto ao BD
                                                    Produto produto = new Produto(nomeP, codBarras, preco, estoque, setorP);
                                                    produtoDAO.insertProduto(produto);
                                                    //Relacionar produto com fornecedor
                                                    produtoDAO.insertProdutoFornecedor(produto, fornecedor);
                                                }

                                                flagProduto = false;
                                                break;
                                            case(2):
                                                if(produtoDAO.qtdProduto()==0){
                                                    System.out.println("Não é possível cadastrar um produto existente!");
                                                    System.out.println("Não há produtos cadastrados!");
                                                }
                                                else{
                                                    System.out.println("Selecione um produto:");
                                                    //Listar produtos do BD
                                                    produtos = produtoDAO.selectProduto();
                                                    Produto produto2 = null;
                                                    flagLeitura = true;
                                                    while (flagLeitura) {
                                                        produtos.forEach((id, produtoAux)->{
                                                            System.out.println("    "+produtoAux.getCodInterno() + " - " + produtoAux.getNome() + ";");
                                                        });
                                                        opLeitura = entrada.nextInt();
                                                        if (produtos.get(opLeitura) != null) {
                                                            produto2 = produtos.get(opLeitura);
                                                            flagLeitura = false;
                                                        } else {
                                                            System.out.println("Entre com uma opção válida!");
                                                        }
                                                    }

                                                    System.out.println("Selecione um fornecedor:");
                                                    //Listar fornecedores do BD
                                                    fornecedores = fornecedorDAO.selectFornecedor();
                                                    Fornecedor fornecedor2 = null;
                                                    flagLeitura = true;
                                                    while (flagLeitura) {
                                                        fornecedores.forEach((id, fornecedorAux)->{
                                                            System.out.println("    "+fornecedorAux.getId() + " - " + fornecedorAux.getNome() + ";");
                                                        });
                                                        opLeitura = entrada.nextInt();
                                                        if (fornecedores.get(opLeitura) != null) {
                                                            fornecedor2 = fornecedores.get(opLeitura);
                                                            flagLeitura = false;
                                                        } else {
                                                            System.out.println("Entre com uma opção válida!");
                                                        }
                                                    }

                                                    System.out.println("Quantidade do produto: ");
                                                    int quantidade = entrada.nextInt();

                                                    //Atualizar estoque
                                                    produtoDAO.updateProdutoEstoque(produto2.getCodInterno(), quantidade);

                                                    //Relacionar produto com fornecedor
                                                    produtoDAO.insertProdutoFornecedor(produto2, fornecedor2);
                                                }

                                                flagProduto = false;
                                                break;
                                            default:
                                                System.out.println("Entre com uma opção válida!");
                                                break;
                                        }
                                    }
                                }

                                flagCadListAtAp = false;
                                break;

                            //Cadastrar setor
                            case (4):
                                System.out.println("Adicione as informações do setor");
                                System.out.println("Nome:");
                                entrada.nextLine();
                                String nomeS = entrada.nextLine();

                                //Adicionar setor ao BD
                                setorDAO.insertSetor(new Setor(nomeS));

                                flagCadListAtAp = false;
                                break;

                            //Cadastrar fornecedor
                            case (5):
                                if(gerenteDAO.qtdGerente()==0){
                                    System.out.println("Não é possível cadastrar um fornecedos!");
                                    System.out.println("Não há gerentes cadastrados!");
                                }
                                else {
                                    System.out.println("Adicione as informações do fornecedor");
                                    System.out.println("Nome:");
                                    entrada.nextLine();
                                    String nomeF = entrada.nextLine();

                                    System.out.println("Telefone:");
                                    String telefoneF= entrada.nextLine();

                                    System.out.println("E-mail:");
                                    String emailF = entrada.nextLine();

                                    System.out.println("Empresa:");
                                    String empresaF = entrada.nextLine();

                                    System.out.println("Selecione um gerente:");
                                    //Listar gerentes do BD
                                    gerentes = gerenteDAO.selectGerente();
                                    Gerente gerenteF = null;
                                    flagLeitura = true;
                                    while (flagLeitura) {
                                        gerentes.forEach((id, gerenteAux) -> {
                                            System.out.println("    "+id + " - " + gerenteAux.getNome() + ";");
                                        });
                                        opLeitura = entrada.nextInt();
                                        if (gerentes.get(opLeitura) != null) {
                                            gerenteF = gerentes.get(opLeitura);
                                            flagLeitura = false;
                                        } else {
                                            System.out.println("Entre com uma opção válida!");
                                        }
                                    }

                                    //Adicionar fornecedor ao BD
                                    fornecedorDAO.insertFornecedor(new Fornecedor(nomeF, empresaF, telefoneF, emailF, gerenteF));

                                }

                                flagCadListAtAp = false;
                                break;
                            //Sair do cadastramento
                            case(6):
                                flagCadListAtAp = false;
                                break;
                            default:
                                System.out.println("Entre com uma opção válida!");
                                break;
                        }
                    }
                    break;

                //Listar
                case(2):
                    flagCadListAtAp = true;
                    while (flagCadListAtAp) {
                        System.out.println("O que você deseja listar?");
                        System.out.println("1- Funcionário;");
                        System.out.println("2- Gerente;");
                        System.out.println("3- Produto;");
                        System.out.println("4- Setor;");
                        System.out.println("5- Fornecedor;");
                        System.out.println("6- Sair da listagem;");
                        opCadListAtAp = entrada.nextInt();
                        System.out.println("----------------------------------------------");
                        switch (opCadListAtAp) {
                            //Listar funcionários
                            case (1):
                                funcionarios = funcionarioDAO.selectFuncionario();
                                funcionarios.forEach((registro, funcionario)->{
                                    System.out.println("Registro: " + funcionario.getRegistro());
                                    System.out.println("Nome: "+funcionario.getNome());
                                    System.out.println("CPF: "+funcionario.getCPF());
                                    System.out.println("Data de Nascimento: "+funcionario.getDataNascimento());
                                    System.out.println("Telefone: "+funcionario.getTelefone());
                                    System.out.println("E-mail: "+funcionario.getEmail());
                                    System.out.println("Gerente responsável: "+gerenteDAO.selectNomeGerente(funcionario.getRegistroGerente()));
                                    System.out.println("Setor: "+setorDAO.selectNomeSetor(funcionario.getIdSetor()));
                                    System.out.println("--------------------------------------");
                                });
                                flagCadListAtAp = false;
                                break;
                            //Listar gerentes
                            case (2):
                                gerentes = gerenteDAO.selectGerente();
                                gerentes.forEach((id, gerente)->{
                                    System.out.println("Registro: " + gerente.getRegistro());
                                    System.out.println("Nome: "+gerente.getNome());
                                    System.out.println("CPF: "+gerente.getCPF());
                                    System.out.println("Data de Nascimento: "+gerente.getDataNascimento());
                                    System.out.println("Telefone: "+gerente.getTelefone());
                                    System.out.println("E-mail: "+gerente.getEmail());
                                    System.out.println("Setor: "+setorDAO.selectNomeSetor(gerente.getIdSetor()));
                                    System.out.println("--------------------------------------");
                                });
                                flagCadListAtAp = false;
                                break;
                            //Listar produtos
                            case (3):
                                produtos = produtoDAO.selectProduto();
                                produtos.forEach((codInterno, produto)->{
                                    System.out.println("Nome: "+produto.getNome());
                                    System.out.println("Código Interno: "+produto.getCodInterno());
                                    System.out.println("Código de Barras: "+produto.getCodBarras());
                                    System.out.println("Preço: "+produto.getPreco());
                                    System.out.println("Estoque: "+produto.getEstoque());
                                    System.out.println("Setor: "+setorDAO.selectNomeSetor(produto.getIdSetor()));
                                    System.out.println("--------------------------------------");
                                });
                                flagCadListAtAp = false;
                                break;
                            //Listar setores
                            case (4):
                                setores = setorDAO.selectSetor();
                                setores.forEach((id, setor) ->{
                                    System.out.println("Nome: "+setor.getNome());
                                    System.out.println("ID: "+setor.getId());
                                    System.out.println("--------------------------------------");
                                });
                                flagCadListAtAp = false;
                                break;
                            //Listar fornecedores
                            case (5):
                                fornecedores = fornecedorDAO.selectFornecedor();
                                fornecedores.forEach((id, fornecedor)->{
                                    System.out.println("Nome: "+fornecedor.getNome());
                                    System.out.println("ID: "+fornecedor.getId());
                                    System.out.println("Telefone: "+fornecedor.getTelefone());
                                    System.out.println("E-mail: "+fornecedor.getEmail());
                                    System.out.println("Empresa: "+fornecedor.getEmpresa());
                                    System.out.println("Gerente: "+gerenteDAO.selectNomeGerente(fornecedor.getRegistroGerente()));
                                    System.out.println("--------------------------------------");
                                });

                                flagCadListAtAp = false;
                                break;
                            //Sair da listagem
                            case(6):
                                flagCadListAtAp = false;
                                break;
                            default:
                                System.out.println("Entre com uma opção válida!");
                                break;
                        }
                    }
                    break;

                //Atualizar
                case(3):
                    flagCadListAtAp = true;
                    while (flagCadListAtAp) {
                        System.out.println("O que você deseja atualizar?");
                        System.out.println("1- Gerente do funcionário;");
                        System.out.println("2- Setor do funcionário;");
                        System.out.println("3- Setor do gerente;");
                        System.out.println("4- Setor do produto;");
                        System.out.println("5- Gerente responsável pelo fornecedor;");
                        System.out.println("6- Sair da atualização;");
                        opCadListAtAp = entrada.nextInt();
                        System.out.println("----------------------------------------------");
                        switch (opCadListAtAp) {
                            //Gerente do funcionário
                            case(1):
                                System.out.println("Selecione um funcionário:");
                                //Listar funcionários do BD
                                funcionarios = funcionarioDAO.selectFuncionario();
                                Funcionario funcionario = null;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    funcionarios.forEach((id, funcionarioAux) -> {
                                        System.out.println("    "+id + " - " + funcionarioAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (funcionarios.get(opLeitura) != null) {
                                        funcionario = funcionarios.get(opLeitura);
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                System.out.println("Selecione um gerente:");
                                //Listar gerentes do BD
                                gerentes = gerenteDAO.selectGerente();
                                Gerente gerente = null;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    gerentes.forEach((id, gerenteAux) -> {
                                        System.out.println("    "+id + " - " + gerenteAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (gerentes.get(opLeitura) != null) {
                                        gerente = gerentes.get(opLeitura);
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                //Atualizar gerente do funcionário
                                funcionarioDAO.updateFuncionarioGerente(funcionario.getRegistro(), gerente.getRegistro());

                                flagCadListAtAp = false;
                                break;
                            //Setor do funcionário
                            case(2):
                                System.out.println("Selecione um funcionário:");
                                //Listar funcionários do BD
                                funcionarios = funcionarioDAO.selectFuncionario();
                                Funcionario funcionarioS = null;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    funcionarios.forEach((id, funcionarioAux) -> {
                                        System.out.println("    "+id + " - " + funcionarioAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (funcionarios.get(opLeitura) != null) {
                                        funcionarioS = funcionarios.get(opLeitura);
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                System.out.println("Selecione um setor:");
                                //Listar setores do BD
                                setores = setorDAO.selectSetor();
                                Setor setor = null;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    setores.forEach((id, setorAux) -> {
                                        System.out.println("    "+id + " - " + setorAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (setores.get(opLeitura) != null) {
                                        setor = setores.get(opLeitura);
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                //Atualizar setor do funcionário
                                funcionarioDAO.updateFuncionarioSetor(funcionarioS.getRegistro(), setor.getId());

                                flagCadListAtAp = false;
                                break;
                            //Setor do gerente
                            case(3):
                                System.out.println("Selecione um gerente:");
                                //Listar gerentes do BD
                                gerentes = gerenteDAO.selectGerente();
                                Gerente gerenteS = null;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    gerentes.forEach((id, gerenteAux) -> {
                                        System.out.println("    "+id + " - " + gerenteAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (gerentes.get(opLeitura) != null) {
                                        gerenteS = gerentes.get(opLeitura);
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                System.out.println("Selecione um setor:");
                                //Listar setores do BD
                                setores = setorDAO.selectSetor();
                                Setor setorG = null;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    setores.forEach((id, setorAux) -> {
                                        System.out.println("    "+id + " - " + setorAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (setores.get(opLeitura) != null) {
                                        setorG = setores.get(opLeitura);
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                //Atualizar setor do gerente
                                gerenteDAO.updateGerenteSetor(gerenteS.getRegistro(), setorG.getId());

                                flagCadListAtAp = false;
                                break;
                            //Setor do produto
                            case(4):
                                System.out.println("Selecione um produto:");
                                //Listar produtos do BD
                                produtos = produtoDAO.selectProduto();
                                Produto produto = null;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    produtos.forEach((id, produtoAux)->{
                                        System.out.println("    "+produtoAux.getCodInterno() + " - " + produtoAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (produtos.get(opLeitura) != null) {
                                        produto = produtos.get(opLeitura);
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                System.out.println("Selecione um setor:");
                                //Listar setores do BD
                                setores = setorDAO.selectSetor();
                                Setor setorP = null;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    setores.forEach((id, setorAux) -> {
                                        System.out.println("    "+id + " - " + setorAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (setores.get(opLeitura) != null) {
                                        setorP = setores.get(opLeitura);
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                //Atualizar setor do produto
                                produtoDAO.updateProdutoSetor(produto.getCodInterno(), setorP.getId());

                                flagCadListAtAp = false;
                                break;
                            //Gerente responsável pelo fornecedor
                            case(5):
                                System.out.println("Selecione um fornecedor:");
                                //Listar fornecedores do BD
                                fornecedores = fornecedorDAO.selectFornecedor();
                                Fornecedor fornecedor = null;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    fornecedores.forEach((id, fornecedorAux)->{
                                        System.out.println("    "+fornecedorAux.getId() + " - " + fornecedorAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (fornecedores.get(opLeitura) != null) {
                                        fornecedor = fornecedores.get(opLeitura);
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                System.out.println("Selecione um gerente:");
                                //Listar gerentes do BD
                                gerentes = gerenteDAO.selectGerente();
                                Gerente gerenteF = null;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    gerentes.forEach((id, gerenteAux) -> {
                                        System.out.println("    "+id + " - " + gerenteAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (gerentes.get(opLeitura) != null) {
                                        gerenteF = gerentes.get(opLeitura);
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                //Atualizar gerente responsável pelo fornecedor
                                fornecedorDAO.updateFornecedorGerente(fornecedor.getId(), gerenteF.getRegistro());

                                flagCadListAtAp = false;
                                break;
                            //Sair da atualização
                            case(6):
                                flagCadListAtAp = false;
                                break;
                            default:
                                System.out.println("Entre com uma opção válida!");
                                break;
                        }
                    }
                    break;

                //Apagar
                case(4):
                    flagCadListAtAp = true;
                    while (flagCadListAtAp) {
                        System.out.println("O que você deseja apagar?");
                        System.out.println("1- Funcionário;");
                        System.out.println("2- Gerente;");
                        System.out.println("3- Produto;");
                        System.out.println("4- Setor;");
                        System.out.println("5- Fornecedor;");
                        System.out.println("6- Sair do apagamento");
                        opCadListAtAp = entrada.nextInt();
                        System.out.println("----------------------------------------------");

                        switch (opCadListAtAp) {
                            //Funcionário
                            case(1):
                                System.out.println("Selecione um funcionário:");
                                //Listar funcionários do BD
                                funcionarios = funcionarioDAO.selectFuncionario();
                                int registroFuncionario = 0;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    funcionarios.forEach((id, funcionarioAux) -> {
                                        System.out.println("    "+id + " - " + funcionarioAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (funcionarios.get(opLeitura) != null) {
                                        registroFuncionario = opLeitura;
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                funcionarioDAO.deleteFuncionario(registroFuncionario);

                                flagCadListAtAp = false;
                                break;

                            //Gerente
                            case(2):
                                System.out.println("Selecione um gerente:");
                                //Listar gerentes do BD
                                gerentes = gerenteDAO.selectGerente();
                                int registroGerente = 0;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    gerentes.forEach((id, gerenteAux) -> {
                                        System.out.println("    "+id + " - " + gerenteAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (gerentes.get(opLeitura) != null) {
                                        registroGerente = opLeitura;
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                gerenteDAO.deleteGerente(registroGerente);

                                flagCadListAtAp = false;
                                break;

                            //Produto
                            case(3):
                                System.out.println("Selecione um produto:");
                                //Listar produtos do BD
                                produtos = produtoDAO.selectProduto();
                                int idProduto = 0;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    produtos.forEach((id, produtoAux)->{
                                        System.out.println("    "+produtoAux.getCodInterno() + " - " + produtoAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (produtos.get(opLeitura) != null) {
                                        idProduto = opLeitura;
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                produtoDAO.deleteProduto(idProduto);

                                flagCadListAtAp = false;
                                break;

                            //Setor
                            case(4):
                                System.out.println("Selecione um setor:");
                                //Listar setores do BD
                                setores = setorDAO.selectSetor();
                                int idSetor = 0;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    setores.forEach((id, setorAux) -> {
                                        System.out.println("    "+id + " - " + setorAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (setores.get(opLeitura) != null) {
                                        idSetor = opLeitura;
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                setorDAO.deleteSetor(idSetor);

                                flagCadListAtAp = false;
                                break;

                            //Fornecedor
                            case(5):
                                System.out.println("Selecione um fornecedor:");
                                //Listar fornecedores do BD
                                fornecedores = fornecedorDAO.selectFornecedor();
                                int idFornecedor = 0;
                                flagLeitura = true;
                                while (flagLeitura) {
                                    fornecedores.forEach((id, fornecedorAux)->{
                                        System.out.println("    "+fornecedorAux.getId() + " - " + fornecedorAux.getNome() + ";");
                                    });
                                    opLeitura = entrada.nextInt();
                                    if (fornecedores.get(opLeitura) != null) {
                                        idFornecedor = opLeitura;
                                        flagLeitura = false;
                                    } else {
                                        System.out.println("Entre com uma opção válida!");
                                    }
                                }

                                fornecedorDAO.deleteFornecedor(idFornecedor);

                                flagCadListAtAp = false;
                                break;

                            default:
                                System.out.println("Entre com uma opção válida!");
                                break;
                        }
                    }
                    break;

                //Ordenar os funcionários em ordem alfabética de nome
                case(5):
                    ArrayList<Funcionario> arrayFuncionarios = new ArrayList<>(funcionarioDAO.selectFuncionario().values());
                    Collections.sort(arrayFuncionarios);
                    for(Funcionario funcionario : arrayFuncionarios){
                        System.out.println("Registro: " + funcionario.getRegistro());
                        System.out.println("Nome: "+funcionario.getNome());
                        System.out.println("CPF: "+funcionario.getCPF());
                        System.out.println("Data de Nascimento: "+funcionario.getDataNascimento());
                        System.out.println("Telefone: "+funcionario.getTelefone());
                        System.out.println("E-mail: "+funcionario.getEmail());
                        System.out.println("Gerente responsável: "+gerenteDAO.selectNomeGerente(funcionario.getRegistroGerente()));
                        System.out.println("Setor: "+setorDAO.selectNomeSetor(funcionario.getIdSetor()));
                        System.out.println("--------------------------------------");
                    }
                    break;

                //Ordenar os produto em relação a quantidade no estoque
                case(6):
                    //Converter hashMap para ArrayList
                    ArrayList<Produto> arrayProdutos = new ArrayList<>(produtoDAO.selectProduto().values());
                    Collections.sort(arrayProdutos);
                    for(Produto produto: arrayProdutos){
                        System.out.println("Nome: "+produto.getNome());
                        System.out.println("Código Interno: "+produto.getCodInterno());
                        System.out.println("Código de Barras: "+produto.getCodBarras());
                        System.out.println("Preço: "+produto.getPreco());
                        System.out.println("Estoque: "+produto.getEstoque());
                        System.out.println("Setor: "+setorDAO.selectNomeSetor(produto.getIdSetor()));
                        System.out.println("--------------------------------------");
                    }
                    break;
                //Sair do menu
                case(7):
                    flagMenu = false;
                    System.out.println("Você saiu do menu!");
                    break;
                default:
                    System.out.println("Entre com uma opção válida!");
                    break;
            }
        }
        //Encerrar entrada de dados
        entrada.close();
    }
}