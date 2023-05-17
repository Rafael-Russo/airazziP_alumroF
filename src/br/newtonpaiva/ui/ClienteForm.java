package br.newtonpaiva.ui;

import javax.swing.*;
import java.awt.*;

public class ClienteForm extends JFrame {
    private JPanel mainPanel;
    private JLabel labelNome;
    private JLabel labelTelefone;
    private JLabel labelEndereco;
    private JTextField textFieldNome;
    private JTextField textFieldTelefone;
    private JTextField textFieldEndereco;
    private JButton buttonCadastrar;

    public ClienteForm() {
        setTitle("Cadastro do cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        mainPanel = new JPanel();
        labelNome = new JLabel("Nome:");
        labelTelefone = new JLabel("Telefone:");
        labelEndereco = new JLabel("Endereço:");
        textFieldNome = new JTextField(20);
        textFieldTelefone = new JTextField(20);
        textFieldEndereco = new JTextField(20);
        buttonCadastrar = new JButton("Cadastrar");
    }

    private void setupLayout() {
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));
        mainPanel.add(labelNome);
        mainPanel.add(textFieldNome);
        mainPanel.add(labelTelefone);
        mainPanel.add(textFieldTelefone);
        mainPanel.add(labelEndereco);
        mainPanel.add(textFieldEndereco);
        mainPanel.add(new JLabel()); // Espaçamento
        mainPanel.add(buttonCadastrar);

        setContentPane(mainPanel);
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteForm form = new ClienteForm();
            form.setVisible(true);
        });
    }
}
