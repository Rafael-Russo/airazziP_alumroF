package br.newtonpaiva.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu {

    private JFrame frame = new JFrame("Menu - Alumrof Airazzip");
    private JLabel logo = new JLabel(new ImageIcon("..\\airazziP_alumroF\\imgs\\pizza.png"));
    private JPanel buttonsPanel = new JPanel();
    private JButton buttonCadastrarCliente = new JButton("Cadastrar Cliente");
    private JButton buttonCadastrarPizza = new JButton("Cadastrar Pizza");
    private JButton buttonFazerPedido = new JButton("Fazer PedidoMao");

    public Menu() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(logo, BorderLayout.NORTH);
        buttonsPanel.add(buttonCadastrarCliente);
        buttonsPanel.add(buttonCadastrarPizza);
        buttonsPanel.add(buttonFazerPedido);
        frame.add(buttonsPanel, BorderLayout.CENTER);
        frame.setSize(350, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buttonCadastrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    ClienteForm clienteForm = new ClienteForm();
                    clienteForm.setVisible(true);
                });
            }
        });

        buttonCadastrarPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    PizzaForm pizzaForm = new PizzaForm();
                    pizzaForm.setVisible(true);
                });
            }
        });

        buttonFazerPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    PedidoForm pedidoForm = new PedidoForm();
                    pedidoForm.setVisible(true);
                });
            }
        });
    }

    public static void main(String[] args) {
        new Menu();
    }
}
