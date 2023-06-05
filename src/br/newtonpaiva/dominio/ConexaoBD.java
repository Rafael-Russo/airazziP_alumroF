package br.newtonpaiva.dominio;

import java.sql.*;

public class ConexaoBD {
    private Connection conectar() {

        Connection conexao = null;
        String url = "jdbc:mysql://localhost:3306/pizzaria";
        String user = "root";
        String senha = "";

        try {
            String driverName = "org.gjt.mm.mysql.Driver";

            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, user, senha);
            System.out.println("Conexão estabelecida!");

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("Erro com conexão: " + e.getMessage());

        }

        return conexao;
    }

    public void InserirCliente(Cliente cliente){
        Connection conexao = conectar();
        PreparedStatement statement = null;

        String query = "INSERT INTO Cliente (id_cliente, nome_cliente, telefone_cliente, endereco_cliente) VALUES (null, ?, ?, ?)";

        try{
            statement = conexao.prepareStatement(query);

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getTelefone());
            statement.setString(3, cliente.getEndereco());

            int linhas = statement.executeUpdate();

            if (linhas > 0){
                System.out.println("Cliente inserido!");
            } else {
                System.out.println("Falha ao inserir cliente!");
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
            if (verificarClienteExistente(cliente.getTelefone())){
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

    public boolean verificarClienteExistente(String telefone) {
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
}
