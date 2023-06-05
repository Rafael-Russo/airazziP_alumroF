package br.newtonpaiva.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PizzaForm extends JFrame {
    private JPanel ingredientsPanel;
    private JTextField pizzaNameField;
    private JTextField imageField;

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

        // Painel para adicionar o arquivo de imagem
        JPanel imagePanel = new JPanel(new FlowLayout());
        JLabel imageLabel = new JLabel("Imagem: ");
        imageField = new JTextField(20);
        JButton imageButton = new JButton("Selecionar Imagem");
        imageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(PizzaForm.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imageField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        imagePanel.add(imageLabel);
        imagePanel.add(imageField);
        imagePanel.add(imageButton);
        add(imagePanel, BorderLayout.SOUTH);

        // Cria o painel de ingredientes
        ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10); // Espaçamento entre os componentes

        // Painel com barra de rolagem para os ingredientes
        JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
        ingredientsScrollPane.setPreferredSize(new Dimension(400, 300));
        add(ingredientsScrollPane, BorderLayout.CENTER);

        // Adiciona ingredientes ao painel (Exemplo com ingredientes estáticos)
        addIngredient("Queijo", gbc);
        addIngredient("Tomate", gbc);
        addIngredient("Presunto", gbc);
        addIngredient("Mussarela", gbc);
        // Adicione mais ingredientes, se necessário

        // Botão de cadastrar
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pizzaName = pizzaNameField.getText();
                String imageUrl = imageField.getText();

                // Processar os ingredientes selecionados
                StringBuilder selectedIngredients = new StringBuilder();
                Component[] components = ingredientsPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof JPanel) {
                        JPanel ingredientPanel = (JPanel) component;
                        JTextField quantityField = (JTextField) ingredientPanel.getComponent(2);
                        int quantity = getQuantityFromTextField(quantityField);
                        if (quantity > 0) {
                            JLabel nameLabel = (JLabel) ingredientPanel.getComponent(0);
                            String ingredientName = nameLabel.getText();
                            selectedIngredients.append(ingredientName).append(" (Quantidade: ").append(quantity).append("), ");
                        }
                    }
                }
                // Exemplo: exibe uma mensagem com os dados da pizza
                StringBuilder message = new StringBuilder("Dados da Pizza:\n");
                message.append("- Nome: ").append(pizzaName).append("\n");
                message.append("- Ingredientes: ").append(selectedIngredients).append("\n");
                message.append("- Imagem: ").append(imageUrl);
                JOptionPane.showMessageDialog(null, message.toString(), "Dados da Pizza", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(cadastrarButton, BorderLayout.PAGE_END);

        // Configurações da janela
        pack();
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    private void addIngredient(String ingredientName, GridBagConstraints gbc) {
        JPanel ingredientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        ingredientPanel.setPreferredSize(new Dimension(400, 30));

        // Label para o nome do ingrediente
        JLabel nameLabel = new JLabel(ingredientName);
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