package org.example.interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BuscarAlum extends JDialog{
    private JPanel panelPrincipal;
    private JTextField textField1;
    private JButton buscarButton;
    private JButton cancelButton;

    public BuscarAlum(Menu principal) {
        setLocation(460, 260);
        setVisible(true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                principal.setEnabled(true);
                principal.setVisible(true);
            }


        });
        pack();


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                principal.setEnabled(true);
                setVisible(false);
            }
        });
    }
}
