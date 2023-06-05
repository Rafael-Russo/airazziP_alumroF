package br.newtonpaiva.ui;

import br.newtonpaiva.dominio.Cliente;
import br.newtonpaiva.dominio.ConexaoBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        ConexaoBD conexao = new ConexaoBD();
        setTitle("Cadastro do cliente");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        initComponents();
        setupLayout();

        buttonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente c = new Cliente();
                c.setNome(textFieldNome.getText());
                c.setTelefone(textFieldTelefone.getText());
                c.setEndereco(textFieldEndereco.getText());
                conexao.InserirCliente(c);
            }
        });
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


}
