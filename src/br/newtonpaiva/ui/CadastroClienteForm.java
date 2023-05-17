package br.newtonpaiva.ui;

import javax.swing.*;
import java.awt.*;

public class CadastroClienteForm extends JFrame {
    private JPanel mainPanel;
    private JLabel labelTitulo;
    private JLabel labelNome;
    private JLabel labelTelefone;
    private JLabel labelEndereco;
    private JTextField textFieldNome;
    private JTextField textFieldTelefone;
    private JTextField textFieldEndereco;
    private JButton buttonCadastrar;

    public CadastroClienteForm() {
        setTitle("Cadastro do cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout()); // Usando GridBagLayout para alinhamento flexível

        labelTitulo = new JLabel("Cadastro do cliente");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        labelNome = new JLabel("Nome:");
        labelTelefone = new JLabel("Telefone:");
        labelEndereco = new JLabel("Endereço:");

        textFieldNome = new JTextField(20);
        textFieldTelefone = new JTextField(20);
        textFieldEndereco = new JTextField(20);

        buttonCadastrar = new JButton("Cadastrar");
    }

    private void setupLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Define o espaçamento interno dos componentes

        // Configuração para o labelTitulo
        gbc.gridx = 0; // Coluna 0
        gbc.gridy = 0; // Linha 0
        gbc.gridwidth = 2; // Span de 2 colunas
        gbc.anchor = GridBagConstraints.CENTER; // Alinhamento centralizado
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preenchimento horizontal
        gbc.weightx = 1.0; // Peso horizontal
        gbc.weighty = 1.0; // Peso vertical
        mainPanel.add(labelTitulo, gbc);

        // Demais componentes
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST; // Alinhamento à direita
        gbc.fill = GridBagConstraints.NONE; // Sem preenchimento
        gbc.weightx = 0.0; // Sem peso horizontal
        gbc.weighty = 0.0; // Sem peso vertical

        mainPanel.add(labelNome, gbc);

        gbc.gridy++;
        mainPanel.add(labelTelefone, gbc);

        gbc.gridy++;
        mainPanel.add(labelEndereco, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; // Alinhamento à esquerda
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preenchimento horizontal
        gbc.weightx = 1.0; // Peso horizontal

        mainPanel.add(textFieldNome, gbc);

        gbc.gridy++;
        mainPanel.add(textFieldTelefone, gbc);

        gbc.gridy++;
        mainPanel.add(textFieldEndereco, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Alinhamento centralizado
        gbc.fill = GridBagConstraints.NONE; // Sem preenchimento
        gbc.weightx = 0.0; // Sem peso horizontal

        mainPanel.add(buttonCadastrar, gbc);

        setContentPane(mainPanel);
        pack();
    }

    public static void main(String[]
                                    args) {
        SwingUtilities.invokeLater(() -> {
            CadastroClienteForm form = new CadastroClienteForm();
            form.setVisible(true);
        });
    }
}