package br.newtonpaiva.dominio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexaoBD {
    private Connection conectar() {

        Connection conexao = null;
        String url = "jdbc:mysql://localhost:3306/pizzaria";
        String user = "root";
        String senha = "";

        try {
            String driverName = "com.mysql.cj.jdbc.Driver";

            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, user, senha);
            System.out.println("Conexão estabelecida!");

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("Erro com conexão: " + e.getMessage());

        }

        return conexao;
    }

    public Integer InserirCliente(Cliente cliente){
        Connection conexao = conectar();
        PreparedStatement statement = null;
        ResultSet result = null;

        String query = "INSERT INTO Cliente (id_cliente, nome_cliente, telefone_cliente, endereco_cliente) VALUES (null, ?, ?, ?)";

        try{
            if (!verificarClienteExistenteTelefone(cliente.getTelefone())){
                statement = conexao.prepareStatement(query);

                statement.setString(1, cliente.getNome());
                statement.setString(2, cliente.getTelefone());
                statement.setString(3, cliente.getEndereco());

                int linhas = statement.executeUpdate();

                if (linhas > 0){
                    result = statement.getGeneratedKeys();
                    int id = result.getInt(1);
                    System.out.println("Cliente inserido!");
                    return id;
                } else {
                    System.out.println("Falha ao inserir cliente!");
                }
            }else {
                System.out.println("Esse cliente ja existe!");
            }
        }catch (SQLException e){
            System.out.println("Erro ao inserir Cliente: " + e.getMessage());
        } finally {
            try{
                if (statement != null){
                    statement.close();
                }
                 conexao.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return null;
    }

    public void InserirIngrediente(Ingredient ingredient){
        Connection conexao = conectar();
        PreparedStatement statement = null;

        String query = "INSERT INTO Ingrediente (id_ingrediente, nome_ingrediente, preco_ingrediente) VALUES (null, ?, ?)";

        try{
            statement = conexao.prepareStatement(query);

            statement.setString(1, ingredient.getName());
            statement.setDouble(2, ingredient.getPreco());

            int linhas = statement.executeUpdate();

            if (linhas > 0){
                System.out.println("Ingrediente inserido!");
            } else {
                System.out.println("Falha ao inserir ingrediente!");
            }

        }catch (SQLException e){
            System.out.println("Erro ao inserir ingrediente: " + e.getMessage());
        } finally {
            try{
                if (statement != null){
                    statement.close();
                }
                conexao.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public void InserirCardapio(Cardapio cardapio){
        Connection conexao = conectar();
        PreparedStatement statement = null;

        String query = "INSERT INTO Cardapio (id_pizza, nome_pizza, preco_pizza) VALUES (null, ?, ?)";

        try{
            statement = conexao.prepareStatement(query);

            statement.setString(1, cardapio.getNomePizza());
            statement.setDouble(2, cardapio.getPreco());

            int linhas = statement.executeUpdate();

            if (linhas > 0){
                System.out.println("Pizza inserida!");
            } else {
                System.out.println("Falha ao inserir pizza!");
            }

        }catch (SQLException e){
            System.out.println("Erro ao inserir pizza: " + e.getMessage());
        } finally {
            try{
                if (statement != null){
                    statement.close();
                }
                conexao.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public Integer InserirQntAdicionais(Ingredient ingredient, Integer qnt){
        Connection conexao = conectar();
        PreparedStatement statement = null;
        ResultSet result = null;

        String query = "INSERT INTO Qnt_Adicionais (id_adicionais, id_ingredientes, qnt_adicionais) VALUES (null, ?, ?)";

        try{
            if (verificarIngredienteExistente(ingredient.getIdIngrediente())){
                statement = conexao.prepareStatement(query);

                statement.setString(1, ingredient.getIdIngrediente().toString());
                statement.setString(2, qnt.toString());

                int linhas = statement.executeUpdate();

                if (linhas > 0){
                    result = statement.getGeneratedKeys();
                    if (result.next()){
                        int id = result.getInt(1);
                        System.out.println("Qnt_Adicionais inserido! ID: " + id);
                        return id;
                    }
                } else {
                    System.out.println("Falha ao inserir Qnt_Adicionais!");
                }
            }else {
                InserirIngrediente(ingredient);
                InserirQntAdicionais(ingredient, qnt);
            }

        }catch (SQLException e){
            System.out.println("Erro ao inserir Qnt_Adicionais: " + e.getMessage());
        } finally {
            try{
                if (statement != null){
                    statement.close();
                }
                conexao.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return null;
    }

    public boolean verificarIngredienteExistente(int idIngrediente) {
        Connection conexao = conectar();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT id_ingrediente FROM Ingrediente WHERE id_ingrediente = ?";

        try {
            statement = conexao.prepareStatement(query);
            statement.setInt(1, idIngrediente);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // O ingrediente existe
                return true;
            } else {
                // O ingrediente não existe
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SELECT: " + e.getMessage());
            return false;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                conexao.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    public void InserirQntCardapio(Cardapio pizza, Integer qnt){
        Connection conexao = conectar();
        PreparedStatement statement = null;
        ResultSet result = null;

        String query = "INSERT INTO Qnt_Cardapio (id_cardapio, id_ingrediente_card, qnt_ingreediente) VALUES (null, ?, ?)";

        try{
            if (verificarPizzaExistente(pizza.getIdPizza())){
                statement = conexao.prepareStatement(query);

                statement.setString(1, pizza.getIdPizza().toString());
                statement.setString(2, qnt.toString());

                int linhas = statement.executeUpdate();

                if (linhas > 0){
                    System.out.println("Qnt_Cardapio inserido!");
                } else {
                    System.out.println("Falha ao inserir Qnt_Cardapio!");
                }
            }else {
                InserirCardapio(pizza);
                InserirQntCardapio(pizza, qnt);
            }

        }catch (SQLException e){
            System.out.println("Erro ao inserir Qnt_Cardapio: " + e.getMessage());
        } finally {
            try{
                if (statement != null){
                    statement.close();
                }
                conexao.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public boolean verificarPizzaExistente(int idPizza) {
        Connection connection = conectar();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT id_pizza FROM Cardapio WHERE id_pizza = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, idPizza);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // A pizza existe
                return true;
            } else {
                // A pizza não existe
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar o SELECT: " + e.getMessage());
            return false;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    public void InserirQntPedido(Cardapio pizza, Integer qnt, Integer id_qnt_adicionais){
        Connection conexao = conectar();
        PreparedStatement statement = null;
        ResultSet result = null;

        String query = "INSERT INTO Qnt_Pedido (id_qnt_pedido, id_pizza, qnt_pizza, id_qnt_adicionais) VALUES (null, ?, ?, ?)";

        try{
            if (verificarPizzaExistente(pizza.getIdPizza())){
                statement = conexao.prepareStatement(query);

                statement.setString(1, pizza.getIdPizza().toString());
                statement.setString(2, qnt.toString());
                statement.setString(3, id_qnt_adicionais.toString());

                int linhas = statement.executeUpdate();

                if (linhas > 0){
                    System.out.println("Qnt_Pedido inserido!");
                } else {
                    System.out.println("Falha ao inserir Qnt_Pedido!");
                }
            }else {
                InserirCardapio(pizza);
                InserirQntCardapio(pizza, qnt);
            }

        }catch (SQLException e){
            System.out.println("Erro ao inserir Qnt_Pedido: " + e.getMessage());
        } finally {
            try{
                if (statement != null){
                    statement.close();
                }
                conexao.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public void InserirPedido(Cliente cliente,Boolean hasBorda, Integer id_qnt_pedido, Double preco){
        Connection conexao = conectar();
        PreparedStatement statement = null;
        ResultSet result = null;

        String query = "INSERT INTO Pedido (id_pedido, id_cliente, has_borda, id_qnt_pedido, preco_pedido) VALUES (null, ?, ?, ?, ?)";

        try{
            if (verificarClienteExistenteTelefone(cliente.getTelefone())){
                statement = conexao.prepareStatement(query);

                statement.setString(1, cliente.getIdCliente().toString());
                statement.setString(2, hasBorda.toString());
                statement.setString(3, id_qnt_pedido.toString());
                statement.setString(4, preco.toString());

                int linhas = statement.executeUpdate();

                if (linhas > 0){
                    System.out.println("Pedido inserido!");
                } else {
                    System.out.println("Falha ao inserir Pedido!");
                }
            }else {
                System.out.println("Falha ao inserir Pedido!");
            }

        }catch (SQLException e){
            System.out.println("Erro ao inserir Pedido: " + e.getMessage());
        } finally {
            try{
                if (statement != null){
                    statement.close();
                }
                conexao.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public boolean verificarClienteExistenteTelefone(String telefone) {
        Connection connection = conectar();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Cliente WHERE telefone_cliente = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, telefone);
            resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }

        return false;
    }

    public List<Ingredient> selecionarIngredientes() {
        Connection connection = conectar();
        Statement statement = null;
        ResultSet resultSet = null;

        List<Ingredient> ingredientes = new ArrayList<>();

        String query = "SELECT * FROM Ingrediente";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_ingrediente");
                String nome = resultSet.getString("nome_ingrediente");
                Double preco = resultSet.getDouble("preco_ingrediente");

                Ingredient ingrediente = new Ingredient(id, nome);
                ingrediente.setPreco(preco);
                ingredientes.add(ingrediente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }

        return ingredientes;
    }

    public void adicionarIngredientesDoArquivo(String caminhoArquivo) {
        Connection connection = conectar();
        PreparedStatement statement = null;

        String queryVerificacao = "SELECT COUNT(*) FROM Ingrediente WHERE nome_ingrediente = ?";
        String queryInsercao = "INSERT INTO Ingrediente (nome_ingrediente, preco_ingrediente) VALUES (?, ?)";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                if (dados.length >= 2) {
                    String nome = dados[0];
                    double preco = Double.parseDouble(dados[1]);

                    // Verifica se o ingrediente já foi adicionado ao banco de dados
                    if (!verificarIngredienteExistente(connection, queryVerificacao, nome)) {
                        statement = connection.prepareStatement(queryInsercao);
                        statement.setString(1, nome);
                        statement.setDouble(2, preco);
                        statement.executeUpdate();

                        System.out.println("Ingrediente adicionado: " + nome);
                    } else {
                        System.out.println("Ingrediente já existe: " + nome);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao executar o INSERT: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    private boolean verificarIngredienteExistente(Connection connection, String query, String nomeIngrediente) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, nomeIngrediente);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return false;
    }

    public ArrayList<Cardapio> selecionarCardapio() {
        ArrayList<Cardapio> cardapios = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Cardapio";

        try {
            connection = conectar();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idPizza = resultSet.getInt("id_pizza");
                String nomePizza = resultSet.getString("nome_pizza");
                Double precoPizza = resultSet.getDouble("preco_pizza");

                Cardapio cardapio = new Cardapio(idPizza, nomePizza, null, null);
                cardapio.setPreco(precoPizza);
                cardapios.add(cardapio);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta SQL: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o ResultSet: " + e.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar o PreparedStatement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }

        return cardapios;
    }

    public Cliente buscarClientePorTelefone(String telefone) {
        Connection connection = conectar();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM Cliente WHERE telefone_cliente = ?";

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, telefone);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Integer idCliente = resultSet.getInt("id_cliente");
                String nome = resultSet.getString("nome_cliente");
                String endereco = resultSet.getString("endereco_cliente");

                // Crie um objeto Cliente com os valores do resultado da consulta
                Cliente cliente = new Cliente(idCliente, nome, telefone, endereco);

                return cliente;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }

        return null; // Retorna null se o cliente não for encontrado
    }
}
