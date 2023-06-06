package br.newtonpaiva.ui;

import br.newtonpaiva.dominio.Cardapio;
import br.newtonpaiva.dominio.ConexaoBD;
import br.newtonpaiva.dominio.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class PizzaForm extends JFrame {
    private JPanel ingredientsPanel;
    private JTextField pizzaNameField;
    Cardapio pizza = new Cardapio();

    public PizzaForm() {
        // Configurações da janela
        setTitle("Cadastro de Pizza");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 500));

        // Título centralizado no topo da tela
        JLabel titleLabel = new JLabel("Cadastro de Pizza");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Painel para o nome da pizza
        JPanel namePanel = new JPanel(new FlowLayout());
        JLabel nameLabel = new JLabel("Nome da Pizza: ");
        pizzaNameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(pizzaNameField);
        add(namePanel, BorderLayout.CENTER);

        // Cria o painel de ingredientes
        ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new GridLayout(0, 1));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10); // Espaçamento entre os componentes

        // Adiciona o campo do nome da pizza ao painel de ingredientes
        JLabel pizzaNameLabel = new JLabel("Nome da Pizza: ");
        gbc.gridy++;
        ingredientsPanel.add(pizzaNameLabel, gbc);

        // Adiciona o campo de texto do nome da pizza ao painel de ingredientes
        pizzaNameField = new JTextField(20);
        gbc.gridy++;
        ingredientsPanel.add(pizzaNameField, gbc);

        // Painel com barra de rolagem para os ingredientes
        JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
        ingredientsScrollPane.setPreferredSize(new Dimension(400, 300));
        add(ingredientsScrollPane, BorderLayout.CENTER);

        ConexaoBD conexao = new ConexaoBD();
        conexao.adicionarIngredientesDoArquivo("..\\airazziP_alumroF\\Files\\Ingredientes");
        List<Ingredient> ingredientes = conexao.selecionarIngredientes();

        for (Ingredient temp : ingredientes) {
            addIngredient(temp.getIdIngrediente(), temp.getName(), gbc);

            // Atualize o valor de gbc.gridy
            gbc.gridy++;
        }

        // Botão de cadastrar
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pizza.setNomePizza(pizzaNameField.getText());

                Component[] components = ingredientsPanel.getComponents();

                StringBuilder ingredientText = new StringBuilder();

                for (Component component : components) {
                    if (component instanceof JPanel) {
                        JPanel ingredientPanel = (JPanel) component;

                        // Verifica se o painel tem componentes suficientes
                        if (ingredientPanel.getComponentCount() >= 3) {
                            Component component0 = ingredientPanel.getComponent(0);
                            Component component1 = ingredientPanel.getComponent(1);
                            Component component4 = ingredientPanel.getComponent(4);

                            if (component0 instanceof JLabel && component1 instanceof JLabel && component4 instanceof JTextField) {
                                JLabel idLabel = (JLabel) component0;
                                JLabel nameLabel = (JLabel) component1;
                                JTextField quantityField = (JTextField) component4;

                                int quantity = getQuantityFromTextField(quantityField);
                                if (quantity > 0) {
                                    for (Ingredient ing : ingredientes) {
                                        if (ing.comparaID(Integer.parseInt(idLabel.getText()))) {
                                            pizza.addIngrediente(ing);
                                            pizza.addIngredienteNQnt(ing.getIdIngrediente(), Integer.parseInt(quantityField.getText()));
                                        }
                                    }
                                    String ingredientName = nameLabel.getText();
                                    ingredientText.append(ingredientName).append(" (Quantidade: ").append(quantity).append("), ");
                                }
                            }
                        }
                    }
                }

                StringBuilder message = new StringBuilder("Dados da Pizza:\n");
                message.append("- Nome: ").append(pizza.getNomePizza()).append("\n");
                message.append("- Ingredientes: ").append(ingredientText).append("\n");

                JOptionPane.showMessageDialog(null, message.toString(), "Dados da Pizza", JOptionPane.INFORMATION_MESSAGE);
                conexao.InserirCardapio(pizza);
            }
        });
        add(cadastrarButton, BorderLayout.PAGE_END);

        // Configurações da janela
        pack();
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    private void addIngredient(Integer IngredientID, String ingredientName, GridBagConstraints gbc) {
        JPanel ingredientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        ingredientPanel.setPreferredSize(new Dimension(400, 30));

        // Label para o nome do ingrediente
        JLabel idLabel = new JLabel(IngredientID.toString());
        JLabel nameLabel = new JLabel(ingredientName);
        ingredientPanel.add(idLabel);
        ingredientPanel.add(nameLabel);

        // Botões "+" e "-"
        JButton addButton = new JButton("+");
        addButton.setPreferredSize(new Dimension(30, 30));
        addButton.setMargin(new Insets(0, 0, 0, 0)); // Reduz a margem interna do botão
        JButton minusButton = new JButton("-");
        minusButton.setPreferredSize(new Dimension(30, 30));
        minusButton.setMargin(new Insets(0, 0, 0, 0)); // Reduz a margem interna do botão
        ingredientPanel.add(addButton);
        ingredientPanel.add(minusButton);

        // Caixa de texto para a quantidade
        JTextField quantityField = new JTextField(5);
        quantityField.setPreferredSize(new Dimension(50, 25));
        ingredientPanel.add(quantityField);

        // Listener para o botão "+"
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = getQuantityFromTextField(quantityField) + 1;
                quantityField.setText(String.valueOf(quantity));
            }
        });

        // Listener para o botão "-"
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = getQuantityFromTextField(quantityField) - 1;
                if (quantity >= 0) {
                    quantityField.setText(String.valueOf(quantity));
                }
            }
        });

        gbc.gridy++;
        ingredientsPanel.add(ingredientPanel, gbc);
    }

    private int getQuantityFromTextField(JTextField textField) {
        try {
            return Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}