package Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;
    private JPanel mainPanel;

    JFrame frame;

    /**
     * Constructor for the menu
     */
    public Menu() {
        frame = new JFrame("Menu");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(640, 480);
        frame.setVisible(true);

        /**
         * ActionListener for the client button
         * Opens the client menu
         * @param e
         */
        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientView();
            }
        });
        /**
         * ActionListener for the product button
         * Opens the product menu
         * @param e
         */
        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductView();
            }
        });
        /**
         * ActionListener for the order button
         * Opens the order menu
         * @param e
         */
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderView();
            }
        });
    }

}
