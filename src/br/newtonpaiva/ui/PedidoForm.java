package br.newtonpaiva.ui;

import br.newtonpaiva.dominio.*;
import javafx.scene.control.Labeled;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PedidoForm extends JFrame{

    private JFrame frame;
    private JPanel pizzaPanel;
    private JPanel toppingsPanel;
    private JPanel sizePanel;
    private JPanel quantityPanel;
    private JPanel totalPanel;

    private JComboBox pizzaTypeComboBox;
    private JCheckBox pepperoniCheckbox;
    private JCheckBox sausageCheckbox;
    private JCheckBox mushroomsCheckbox;
    private JCheckBox onionsCheckbox;
    private JComboBox sizeComboBox;
    private JSpinner quantitySpinner;
    private JButton submitButton;
    ConexaoBD conexao = new ConexaoBD();
    private String[] pizzaTypes;
    private Labeled totalLabel;
    private JPanel phonePanel;
    private JTextField phoneField;
    private JPanel bordaPanel;
    private JCheckBox bordaCheckbox;
    List<Ingredient> adicionais = conexao.selecionarIngredientes();
    ArrayList<Cardapio> pizzas = conexao.selecionarCardapio();

    private void setPizzaTypes(){

        pizzaTypes = new String[pizzas.size()];
        for (int i=0; i<pizzas.size(); i++){
            pizzaTypes[i] = pizzas.get(i).getNomePizza();
        }
    }

    public PedidoForm() {
        setPizzaTypes();
        frame = new JFrame("Pizza Order");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));

        phonePanel = new JPanel();
        phonePanel.add(new JLabel("Cliente (Telefone):"));
        phoneField = new JTextField(20);
        phonePanel.add(phoneField);

        pizzaPanel = new JPanel();
        pizzaPanel.add(new JLabel("Pizza Type:"));
        if (pizzaTypes == null){
            for (String pizzaType : pizzaTypes) {
                pizzaTypeComboBox.addItem(pizzaType);
            }
        }else {
            pizzaTypeComboBox = new JComboBox(pizzaTypes);
        }
        pizzaPanel.add(pizzaTypeComboBox);

        toppingsPanel = new JPanel();
        toppingsPanel.add(new JLabel("Adicionais:"));

        for (Ingredient temp : adicionais){
            JCheckBox adicionaisCheckbox = new JCheckBox(temp.getName());
            JLabel ingredienteID = new JLabel(temp.getIdIngrediente().toString());
            toppingsPanel.add(adicionaisCheckbox);
            ingredienteID.setVisible(false);
            toppingsPanel.add(ingredienteID);
        }

        pepperoniCheckbox = new JCheckBox("Pepperoni");
        sausageCheckbox = new JCheckBox("Sausage");
        mushroomsCheckbox = new JCheckBox("Mushrooms");
        onionsCheckbox = new JCheckBox("Onions");
        toppingsPanel.add(pepperoniCheckbox);
        toppingsPanel.add(sausageCheckbox);
        toppingsPanel.add(mushroomsCheckbox);
        toppingsPanel.add(onionsCheckbox);

        bordaPanel = new JPanel();
        bordaCheckbox = new JCheckBox("Borda Recheada");
        bordaPanel.add(bordaCheckbox);

        sizePanel = new JPanel();
        sizePanel.add(new JLabel("Tamanho:"));
        sizeComboBox = new JComboBox(new String[]{"Média", "Grande", "Gigante"});
        sizePanel.add(sizeComboBox);

        quantityPanel = new JPanel();
        quantityPanel.add(new JLabel("Quantity:"));
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        quantityPanel.add(quantitySpinner);

        totalPanel = new JPanel();
        submitButton = new JButton("Submit");
        totalPanel.add(submitButton);

        frame.add(phonePanel);
        frame.add(pizzaPanel);
        frame.add(toppingsPanel);
        frame.add(bordaPanel);
        frame.add(sizePanel);
        frame.add(quantityPanel);
        frame.add(totalPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!conexao.verificarClienteExistenteTelefone(phoneField.getText())){
                    System.out.println("Cliente não existe");
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado!\n\nCadastre o cliente primeiro!");
                    return;
                }
                Cliente c = conexao.buscarClientePorTelefone(phoneField.getText());
                Pedido p = new Pedido();
                p.setCliente(c);
                p.setHasBorda(bordaCheckbox.isSelected());
                for (Component component : toppingsPanel.getComponents()) {
                    if (component instanceof JCheckBox) {
                        JCheckBox checkbox = (JCheckBox) component;
                        // Obtém o índice do componente atual
                        int currentIndex = toppingsPanel.getComponentZOrder(checkbox);
                        // Obtém o JLabel correspondente ao ID do ingrediente
                        JLabel ingredientIDLabel = (JLabel) toppingsPanel.getComponent(currentIndex + 1);
                        // Obtém o ID do ingrediente do JLabel
                        int ingredientID = Integer.parseInt(ingredientIDLabel.getText());
                        // Verifica se o checkbox está marcado
                        if (checkbox.isSelected()) {
                            for (Ingredient tempAdicionais : adicionais){
                                if (tempAdicionais.comparaID(ingredientID)){
                                    p.addAdicionais(tempAdicionais);
                                }
                            }
                        }
                    }
                }
                p.setQntPizzas(Integer.parseInt(quantitySpinner.getValue().toString()));
                System.out.println(Integer.parseInt("quantidade: " + quantitySpinner.getValue().toString()));

                for (Cardapio tempPizza : pizzas){
                    if (tempPizza.getNomePizza().equals(pizzaTypeComboBox.getSelectedItem())){
                        p.setPedidoPizza(tempPizza);
                    }
                }
                p.setPrecoTotal(p.calcTotal());

                p.setIdPedido(conexao.InserirPedido(p.getCliente(), p.getHasBorda(), p.getPedidoPizza(), p.getQntPizzas(), p.getPrecoTotal()));

                for (Ingredient temp : p.adicionais){
                    conexao.InserirQntAdicionais(temp, p);
                }
            }
        });
    }
}