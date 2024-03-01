package org.example.interfaz;

import org.example.controlador.ControladorAlumno;
import org.example.modulo.Alumno;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    public ControladorAlumno controladorAlumno = new ControladorAlumno();
    private JPanel panelPrincipal;
    private JTable tableAlumnos;
    private JButton anyadirButton;
    private JButton listarButton;
    private JButton modificarButton;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JScrollPane panelScroll;
    private JLabel idText;
    private JTextField nombreText;
    private JTextField cursoText;
    private JLabel dniText;
    private JTextField tlfText;
    private JTextField edadText;
    DefaultTableModel modeloTablaAlumnos;

    public Menu() {
        setBounds(420, 320, 500, 400);
        setVisible(true);

//        controladorAlumno.getListaAlumnos().addAll(controladorAlumno.listarAlumnos());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);

        setModeloTablaAlumnos();

        ListSelectionModel selectionModel = tableAlumnos.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Obtener la fila seleccionada
                    int selectedRow = tableAlumnos.getSelectedRow();

                    // Verificar si se seleccionó alguna fila
                    if (selectedRow != -1) {
                        // Obtener los valores de las celdas de la fila seleccionada
                        Object id = tableAlumnos.getValueAt(selectedRow, 0);
                        Object nombre = tableAlumnos.getValueAt(selectedRow, 1);
                        Object curso = tableAlumnos.getValueAt(selectedRow, 2);
                        Object dni = tableAlumnos.getValueAt(selectedRow, 3);
                        Object telefono = tableAlumnos.getValueAt(selectedRow, 4);
                        Object edad = tableAlumnos.getValueAt(selectedRow, 5);

                        // Establecer los valores en los TextField correspondientes
                        idText.setText(id.toString());
                        nombreText.setText(nombre.toString());
                        cursoText.setText(curso.toString());
                        dniText.setText(dni.toString());
                        tlfText.setText(telefono.toString());
                        edadText.setText(edad.toString());
                    }
                }
            }
        });
        anyadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarAlum agregarAlum = new AgregarAlum(Menu.this);
                agregarAlum.setVisible(true);
                setEnabled(false);
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarAlum buscarAlum = new BuscarAlum(Menu.this);
                buscarAlum.setVisible(true);
                setEnabled(false);
            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idText.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"NO HAY ALUMNO SELECCIONADO");
                }else {
                    Alumno alumnoModificar = new Alumno(Long.parseLong(idText.getText()), nombreText.getText(), cursoText.getText(), dniText.getText(), tlfText.getText(), Integer.parseInt(edadText.getText()));
                    controladorAlumno.modificar(alumnoModificar.getId(), alumnoModificar);

                    idText.setText(null);
                    nombreText.setText(null);
                    cursoText.setText(null);
                    dniText.setText(null);
                    tlfText.setText(null);
                    edadText.setText(null);

                    modeloTablaAlumnos.setRowCount(0);
                    for (Alumno alumno : controladorAlumno.listarAlumnos()) {
                        modeloTablaAlumnos.addRow(new Object[]{alumno.getId(), alumno.getNombre(), alumno.getCurso(), alumno.getDNI(), alumno.gettLF(), alumno.getEdad()});

                    }
                    tableAlumnos.setModel(modeloTablaAlumnos);
                }
            }
        });
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeloTablaAlumnos.setRowCount(0);
                for (Alumno alumno : controladorAlumno.listarAlumnos()) {
                    modeloTablaAlumnos.addRow(new Object[]{alumno.getId(), alumno.getNombre(), alumno.getCurso(), alumno.getDNI(), alumno.gettLF(), alumno.getEdad()});

                }
                tableAlumnos.setModel(modeloTablaAlumnos);
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modeloTablaAlumnos.getRowCount() != 0 && tableAlumnos.getSelectedRow() != -1) {
                    if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar a este alumno?") == 0) {
                        Alumno alumnoTemporal = new Alumno(Long.parseLong(idText.getText()), nombreText.getText(), cursoText.getText(), dniText.getText(), tlfText.getText(), Integer.parseInt(edadText.getText()));
                        controladorAlumno.eliminar(alumnoTemporal);

                        modeloTablaAlumnos.setRowCount(0);
                        for (Alumno alumno : controladorAlumno.listarAlumnos()) {
                            modeloTablaAlumnos.addRow(new Object[]{
                                    alumno.getId(), alumno.getNombre(), alumno.getCurso(), alumno.getDNI(), alumno.gettLF(), alumno.getEdad()
                            });

                        }
                        tableAlumnos.setModel(modeloTablaAlumnos);
                    }
                } else
                    JOptionPane.showMessageDialog(null, "No has seleccionado nada para eliminar", "WARNING", JOptionPane.WARNING_MESSAGE);

            }
        });
    }

    public void setModeloTablaAlumnos() {
        modeloTablaAlumnos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        modeloTablaAlumnos.addColumn("ID");
        modeloTablaAlumnos.addColumn("Nombre");
        modeloTablaAlumnos.addColumn("Curso");
        modeloTablaAlumnos.addColumn("DNI");
        modeloTablaAlumnos.addColumn("Teléfono");
        modeloTablaAlumnos.addColumn("Edad");
        tableAlumnos.setModel(modeloTablaAlumnos);
        tableAlumnos.getTableHeader().setReorderingAllowed(false);
        panelScroll.add(tableAlumnos);
        panelScroll.setViewportView(tableAlumnos);

    }
}
