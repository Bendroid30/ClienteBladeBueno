package org.example.interfaz;

import org.example.modulo.Alumno;
import org.example.modulo.AlumnoExisteException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class AgregarAlum extends JDialog {
    private JPanel panelPrincipal;
    private JTextField nombreText;
    private JTextField cursoText;
    private JTextField dniText;
    private JTextField tlfText;
    private JTextField edadText;
    private JButton agregarButton;
    private JButton cancelButton;

    public AgregarAlum(Menu principal) {
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
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombreText.getText().isEmpty() || !nombreText.getText().matches("[A-Za-z]+") || !dniText.getText().matches("[0-9]{8}[A-Z]")) {
                    JOptionPane.showMessageDialog(null, "Por favor, es obligatorio poner un DNI y el nombre del alumno");
                } else {
                    List<Double> notas = new ArrayList<>();
                    //Últimas comprobaciones
                    if (tlfText.getText().matches("[0-9]{9}") && edadText.getText().matches("[0-9]{2}")) {
                        Alumno alumnoNuevo = new Alumno(nombreText.getText(), cursoText.getText(), dniText.getText(), tlfText.getText(), Integer.parseInt(edadText.getText()));

                        try {
                            principal.controladorAlumno.agregar(alumnoNuevo);
                        } catch (AlumnoExisteException ex) {
                            JOptionPane.showMessageDialog(null,"EL ALUMNO YA EXISTE");
                        }


                    } else {

                        Alumno alumnoNuevo = new Alumno(nombreText.getText(), cursoText.getText(), dniText.getText(), "0", 0);

                        try {
                            principal.controladorAlumno.agregar(alumnoNuevo);
                        } catch (AlumnoExisteException ex) {
                            JOptionPane.showMessageDialog(null,"EL ALUMNO YA EXISTE");
                        }

                    }

                }
                setVisible(false);
                principal.setVisible(true);
                principal.setEnabled(true);
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

