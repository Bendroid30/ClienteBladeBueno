package org.example.interfaz;

import org.example.modulo.Alumno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BuscarAlum extends JDialog{
    private JPanel panelPrincipal;
    private JTextField idText;
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
                String idDado = idText.getText();
                Alumno alumnoBuscar = new Alumno(Integer.parseInt(idDado),null, null, 0, "0", null, null);
                if (principal.controladorAlumno.buscar(alumnoBuscar) == null) {
                    JOptionPane.showMessageDialog(null, "LA ID DADA NO SE ENCUENTRA EN LA BASE DE DATOS");
                } else {
                    principal.modeloTablaAlumnos.setRowCount(0);
                    for (Alumno alumno : principal.controladorAlumno.listarAlumnos()) {
                        if (alumno.getId()==(alumnoBuscar.getId()))
                            principal.modeloTablaAlumnos.addRow(new Object[]{alumno.getId(), alumno.getNombre(), alumno.getCurso(), alumno.getDNI(), alumno.gettLF(), alumno.getEdad()});

                    }
                    principal.tableAlumnos.setModel(principal.modeloTablaAlumnos);


                }
                principal.setEnabled(true);
                principal.setVisible(true);
                setVisible(false);
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
