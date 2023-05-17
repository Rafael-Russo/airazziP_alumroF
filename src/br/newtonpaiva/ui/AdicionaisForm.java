package br.newtonpaiva.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdicionaisForm extends JFrame {
    private ArrayList<Ingredient> ingredients;
    private JPanel ingredientsPanel;

    public AdicionaisForm() {
        // Configurações da janela
        setTitle("Painel de Ingredientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializa a lista de ingredientes
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Queijo"));
        ingredients.add(new Ingredient("Tomate"));
        ingredients.add(new Ingredient("Presunto"));
        ingredients.add(new Ingredient("Mussarela"));
        // Adicione mais ingredientes, se necessário

        // Título acima da tela de ingredientes
        JLabel titleLabel = new JLabel("Selecione os ingredientes:");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Cria o painel de ingredientes
        ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new GridLayout(0, 4, 10, 10)); // 4 colunas com espaçamento de 10 pixels

        // Adiciona os ingredientes ao painel
        for (Ingredient ingredient : ingredients) {
            JPanel ingredientItem = createIngredientPanel(ingredient);
            ingredientsPanel.add(ingredientItem);
        }

        // Adiciona o painel de ingredientes à janela
        add(ingredientsPanel, BorderLayout.CENTER);

        // Painel inferior para o botão "Concluir"
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton concluirButton = new JButton("Concluir");
        bottomPanel.add(concluirButton);

        // Adiciona o painel inferior à janela
        add(bottomPanel, BorderLayout.SOUTH);

        // Configuração do botão "Concluir"
        concluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para processar os ingredientes selecionados
                ArrayList<String> selectedIngredients = new ArrayList<>();
                for (Component component : ingredientsPanel.getComponents()) {
                    if (component instanceof JPanel) {
                        JPanel ingredientPanel = (JPanel) component;
                        JTextField quantityField = (JTextField) ingredientPanel.getComponent(2);
                        int quantity = getQuantityFromTextField(quantityField);
                        if (quantity > 0) {
                            JLabel nameLabel = (JLabel) ingredientPanel.getComponent(0);
                            String ingredientName = nameLabel.getText();
                            selectedIngredients.add(ingredientName + " (Quantidade: " + quantity + ")");
                        }
                    }
                }

                // Exemplo: exibe uma mensagem com os ingredientes selecionados
                StringBuilder message = new StringBuilder("Ingredientes selecionados:\n");
                for (String ingredient : selectedIngredients) {
                    message.append("- ").append(ingredient).append("\n");
                }
                JOptionPane.showMessageDialog(null, message.toString(), "Ingredientes Selecionados", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Configurações da janela
        pack();
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    private JPanel createIngredientPanel(Ingredient ingredient) {
        JPanel ingredientPanel = new JPanel(new BorderLayout());

        // Label para o nome do ingrediente
        JLabel nameLabel = new JLabel(ingredient.getName());
        ingredientPanel.add(nameLabel, BorderLayout.WEST);

        // Botões "+" e "-"
        JButton addButton = new JButton("+");
        JButton minusButton = new JButton("-");
        ingredientPanel.add(addButton, BorderLayout.EAST);
        ingredientPanel.add(minusButton, BorderLayout.CENTER);

        // Caixa de texto para a quantidade
        JTextField quantityField = new JTextField(5);
        ingredientPanel.add(quantityField, BorderLayout.SOUTH);

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

        return ingredientPanel;
    }

    private int getQuantityFromTextField(JTextField textField) {
        try {
            return Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdicionaisForm ingredientPanelDemo = new AdicionaisForm();
                ingredientPanelDemo.setVisible(true);
            }
        });
    }

    private class Ingredient {
        private String name;

        public Ingredient(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
