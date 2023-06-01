package br.newtonpaiva.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu {

    private JFrame frame = new JFrame("Menu Example");
    private JLabel logo = new JLabel(new ImageIcon("pizza.png"));
    private JPanel buttonsPanel = new JPanel();
    private JButton buttonCadastrarCliente = new JButton("Cadastrar Cliente");
    private JButton buttonCadastrarPizza = new JButton("Cadastrar Pizza");
    private JButton buttonFazerPedido = new JButton("Fazer Pedido");

    public Menu() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(logo, BorderLayout.NORTH);
        buttonsPanel.add(buttonCadastrarCliente);
        buttonsPanel.add(buttonCadastrarPizza);
        buttonsPanel.add(buttonFazerPedido);
        frame.add(buttonsPanel, BorderLayout.CENTER);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        buttonCadastrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cadastrar cliente");
            }
        });

        buttonCadastrarPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cadastrar pizza");
            }
        });

        buttonFazerPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Fazer pedido");
            }
        });
    }

    public static void main(String[] args) {
        new Menu();
    }
}
