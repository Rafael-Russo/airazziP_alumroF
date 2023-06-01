package br.newtonpaiva.ui;

import javafx.scene.control.Labeled;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PedidoForm {

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

    private String[] pizzaTypes = {"Cheese", "Pepperoni, Sausage", "Mushrooms, Onions"};
    private Labeled totalLabel;

    public PedidoForm() {
        frame = new JFrame("Pizza Order");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));

        pizzaPanel = new JPanel();
        pizzaPanel.add(new JLabel("Pizza Type:"));
        pizzaTypeComboBox = new JComboBox(pizzaTypes);
        pizzaPanel.add(pizzaTypeComboBox);

        toppingsPanel = new JPanel();
        toppingsPanel.add(new JLabel("Toppings:"));
        pepperoniCheckbox = new JCheckBox("Pepperoni");
        sausageCheckbox = new JCheckBox("Sausage");
        mushroomsCheckbox = new JCheckBox("Mushrooms");
        onionsCheckbox = new JCheckBox("Onions");
        toppingsPanel.add(pepperoniCheckbox);
        toppingsPanel.add(sausageCheckbox);
        toppingsPanel.add(mushroomsCheckbox);
        toppingsPanel.add(onionsCheckbox);

        sizePanel = new JPanel();
        sizePanel.add(new JLabel("Size:"));
        sizeComboBox = new JComboBox(new String[]{"Small", "Medium", "Large"});
        sizePanel.add(sizeComboBox);

        quantityPanel = new JPanel();
        quantityPanel.add(new JLabel("Quantity:"));
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        quantityPanel.add(quantitySpinner);

        totalPanel = new JPanel();
        submitButton = new JButton("Submit");
        totalPanel.add(submitButton);

        frame.add(pizzaPanel);
        frame.add(toppingsPanel);
        frame.add(sizePanel);
        frame.add(quantityPanel);
        frame.add(totalPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: submit order

                double totalPrice = 0.00;

                for (int i = 0; i < pizzaTypeComboBox.getItemCount(); i++) {
                    String[] pizzaType = ((String) pizzaTypeComboBox.getItemAt(i)).split(",");
                    for (String type : pizzaType) {
                        totalPrice += getPizzaPrice(type);
                    }
                }

                // Set the total price label
                totalLabel.setText("Total: $" + totalPrice);
            }
        });
    }

    public static void main(String[] args) {
        new PedidoForm();
    }

    private double getPizzaPrice(String type) {
        switch (type) {
            case "Cheese":
                return 10.00;
            case "Pepperoni":
                return 12.00;
            case "Sausage":
                return 14.00;
            case "Mushrooms":
                return 16.00;
            case "Onions":
                return 18.00;
            default:
                return 0.00;
        }
    }
}