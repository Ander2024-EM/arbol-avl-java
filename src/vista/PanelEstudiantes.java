package vista;

import Modelo.ArbolAVL;
import Modelo.Estudiante;
import dao.EstudianteDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelEstudiantes extends JPanel {

    private final ArbolAVL arbol;
    private final EstudianteDAO dao;
    private final PanelArbolAVL panelArbolAVL;

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtPromedio;
    private JComboBox<String> cmbCarrera;
    private JTextField txtTelefono;
    private JTextField txtCorreo;

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextArea txtRecorridos;

    private TarjetaResumen cardTotalNodos;
    private TarjetaResumen cardAltura;
    private TarjetaResumen cardEstado;

    public PanelEstudiantes(ArbolAVL arbol, EstudianteDAO dao, PanelArbolAVL panelArbolAVL) {
        this.arbol = arbol;
        this.dao = dao;
        this.panelArbolAVL = panelArbolAVL;

        setLayout(new BorderLayout());
        setBackground(new Color(240, 242, 245));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        initComponents();
        cargarDatosDesdeBD();
        generarIdAutomatico();
    }

    private void initComponents() {
        add(crearPanelResumen(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
    }
    private void generarIdAutomatico() {
    int siguienteId = dao.obtenerSiguienteId();
    txtId.setText(String.valueOf(siguienteId));
}

    private JPanel crearPanelResumen() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 15, 15));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(0, 0, 20, 0));

        cardTotalNodos = new TarjetaResumen("Total de Estudiantes", "0", new Color(0, 102, 204));
        cardAltura = new TarjetaResumen("Altura del Árbol", "0", new Color(0, 153, 102));
        cardEstado = new TarjetaResumen("Estado", "Sin datos", new Color(255, 140, 0));

        panel.add(cardTotalNodos);
        panel.add(cardAltura);
        panel.add(cardEstado);

        return panel;
    }

    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setOpaque(false);

        JPanel izquierda = new JPanel(new BorderLayout(0, 15));
        izquierda.setOpaque(false);
        izquierda.setPreferredSize(new Dimension(430, 0));

        izquierda.add(crearPanelFormulario(), BorderLayout.NORTH);
        izquierda.add(crearPanelRecorridos(), BorderLayout.CENTER);

        panel.add(izquierda, BorderLayout.WEST);
        panel.add(crearPanelTabla(), BorderLayout.CENTER);

        return panel;
    }

   private JPanel crearPanelFormulario() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(Color.WHITE);
    panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 225, 230)),
            new EmptyBorder(20, 20, 20, 20)
    ));

    JLabel titulo = new JLabel("Formulario de Estudiante");
    titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
    titulo.setForeground(new Color(18, 41, 77));

    JPanel campos = new JPanel(new GridLayout(7, 2, 10, 10));
    campos.setOpaque(false);
    campos.setBorder(new EmptyBorder(20, 0, 20, 0));

    txtId = new JTextField();
    txtNombre = new JTextField();
    txtEdad = new JTextField();
    txtPromedio = new JTextField();
    txtTelefono = new JTextField();
    txtCorreo = new JTextField();

    cmbCarrera = new JComboBox<>(new String[]{
            "1 - Sistemas",
            "2 - Administración",
            "3 - Contaduría",
            "4 - Derecho"
    });

    aplicarEstiloCampo(txtId);
    aplicarEstiloCampo(txtNombre);
    aplicarEstiloCampo(txtEdad);
    aplicarEstiloCampo(txtPromedio);
    aplicarEstiloCampo(txtTelefono);
    aplicarEstiloCampo(txtCorreo);

    txtId.setEditable(false);
    txtId.setBackground(new Color(245, 247, 250)); // gris suave tipo moderno
txtId.setForeground(new Color(100, 100, 100)); // texto gris, no negro

    cmbCarrera.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    cmbCarrera.setPreferredSize(new Dimension(100, 34));

    campos.add(crearLabel("ID Estudiante:"));
    campos.add(txtId);
    campos.add(crearLabel("Nombre:"));
    campos.add(txtNombre);
    campos.add(crearLabel("Edad:"));
    campos.add(txtEdad);
    campos.add(crearLabel("Promedio:"));
    campos.add(txtPromedio);
    campos.add(crearLabel("ID Carrera:"));
    campos.add(cmbCarrera);
    campos.add(crearLabel("Teléfono:"));
    campos.add(txtTelefono);
    campos.add(crearLabel("Correo:"));
    campos.add(txtCorreo);

    JPanel botones = new JPanel(new GridLayout(2, 3, 10, 10));
    botones.setOpaque(false);

    JButton btnNuevo = new BotonEstilizado("Nuevo", BotonEstilizado.Tipo.SECUNDARIO);
    JButton btnGuardar = new BotonEstilizado("Guardar", BotonEstilizado.Tipo.EXITO);
    JButton btnBuscar = new BotonEstilizado("Buscar", BotonEstilizado.Tipo.PRIMARIO);
    JButton btnModificar = new BotonEstilizado("Modificar", BotonEstilizado.Tipo.ADVERTENCIA);
    JButton btnEliminar = new BotonEstilizado("Eliminar", BotonEstilizado.Tipo.PELIGRO);
    JButton btnLimpiar = new BotonEstilizado("Limpiar", BotonEstilizado.Tipo.INFO);

    btnNuevo.addActionListener(e -> limpiarCampos());
    btnGuardar.addActionListener(e -> guardarEstudiante());
    btnBuscar.addActionListener(e -> buscarEstudiante());
    btnModificar.addActionListener(e -> modificarEstudiante());
    btnEliminar.addActionListener(e -> eliminarEstudiante());
    btnLimpiar.addActionListener(e -> limpiarCampos());

    botones.add(btnNuevo);
    botones.add(btnGuardar);
    botones.add(btnBuscar);
    botones.add(btnModificar);
    botones.add(btnEliminar);
    botones.add(btnLimpiar);

    panel.add(titulo, BorderLayout.NORTH);
    panel.add(campos, BorderLayout.CENTER);
    panel.add(botones, BorderLayout.SOUTH);

    return panel;
}

    private JPanel crearPanelRecorridos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel titulo = new JLabel("Recorridos del Árbol AVL");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(18, 41, 77));

        txtRecorridos = new JTextArea();
        txtRecorridos.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtRecorridos.setEditable(false);
        txtRecorridos.setLineWrap(true);
        txtRecorridos.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(txtRecorridos);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 225, 230)));
        scroll.setPreferredSize(new Dimension(100, 220));

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel titulo = new JLabel("Listado de Estudiantes");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(18, 41, 77));

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Edad", "Promedio", "Carrera", "Teléfono", "Correo"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(28);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(new Color(18, 41, 77));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setSelectionBackground(new Color(184, 207, 229));
        tabla.setSelectionForeground(Color.BLACK);

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() != -1) {
                cargarCamposDesdeTabla();
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(60, 60, 60));
        return label;
    }

    private void aplicarEstiloCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setPreferredSize(new Dimension(100, 34));
    }

    private void cargarDatosDesdeBD() {
        try {
            arbol.limpiar();
            List<Estudiante> lista = dao.obtenerTodos();

            for (Estudiante e : lista) {
                arbol.insertar(e);
            }

            cargarTabla(arbol.obtenerTodosLosEstudiantes());
            actualizarResumen("Datos cargados desde BD");
            txtRecorridos.setText(arbol.obtenerInOrdenTexto());

            if (panelArbolAVL != null) {
                panelArbolAVL.refrescar();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + ex.getMessage());
        }
    }

    private void cargarTabla(List<Estudiante> lista) {
        modeloTabla.setRowCount(0);

        for (Estudiante e : lista) {
            modeloTabla.addRow(new Object[]{
                    e.getId(),
                    e.getNombre(),
                    e.getEdad(),
                    e.getPromedio(),
                    e.getIdCarrera(),
                    e.getTelefono(),
                    e.getCorreo()
            });
        }
    }

    private void actualizarResumen(String estado) {
        cardTotalNodos.setValor(String.valueOf(arbol.contarNodos()));
        cardAltura.setValor(String.valueOf(arbol.alturaArbol()));
        cardEstado.setValor(estado);

        String estadoLower = estado.toLowerCase();

        if (estadoLower.contains("encontrado")) {
            cardEstado.setColorPrincipal(new Color(255, 140, 0));
        } else if (estadoLower.contains("guardado")
                || estadoLower.contains("modificado")
                || estadoLower.contains("sincronizado")) {
            cardEstado.setColorPrincipal(new Color(0, 153, 102));
        } else if (estadoLower.contains("eliminado")) {
            cardEstado.setColorPrincipal(new Color(220, 53, 69));
        } else if (estadoLower.contains("cargados")) {
            cardEstado.setColorPrincipal(new Color(0, 102, 204));
        } else {
            cardEstado.setColorPrincipal(new Color(255, 140, 0));
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        txtPromedio.setText("");
        cmbCarrera.setSelectedIndex(0);
        txtTelefono.setText("");
        txtCorreo.setText("");
        tabla.clearSelection();
        txtId.requestFocus();
        generarIdAutomatico(); 
        
    }

    private int obtenerIdCarreraSeleccionada() {
        String seleccionado = cmbCarrera.getSelectedItem().toString();
        return Integer.parseInt(seleccionado.split(" - ")[0]);
    }

    private Estudiante leerFormulario() {
        int id = Integer.parseInt(txtId.getText().trim());
        String nombre = txtNombre.getText().trim();
        int edad = Integer.parseInt(txtEdad.getText().trim());
        double promedio = Double.parseDouble(txtPromedio.getText().trim());
        int carrera = obtenerIdCarreraSeleccionada();
        String telefono = txtTelefono.getText().trim();
        String correo = txtCorreo.getText().trim();

        return new Estudiante(id, nombre, edad, promedio, carrera, telefono, correo);
    }

    private boolean validarCampos() {
        if (txtId.getText().trim().isEmpty()
                || txtNombre.getText().trim().isEmpty()
                || txtEdad.getText().trim().isEmpty()
                || txtPromedio.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Completa los campos obligatorios.");
            return false;
        }

        try {
            Integer.parseInt(txtId.getText().trim());
            Integer.parseInt(txtEdad.getText().trim());
            Double.parseDouble(txtPromedio.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID, edad y promedio deben ser numéricos.");
            return false;
        }

        return true;
    }

    private void guardarEstudiante() {
        if (!validarCampos()) {
            return;
        }

        try {
            Estudiante e = leerFormulario();

            if (arbol.existe(e.getId())) {
                JOptionPane.showMessageDialog(this, "Ya existe un estudiante con ese ID.");
                return;
            }

            boolean insertadoArbol = arbol.insertar(e);
            boolean insertadoBD = dao.insertar(e);

            if (insertadoArbol && insertadoBD) {
                cargarTabla(arbol.obtenerTodosLosEstudiantes());
                actualizarResumen("Estudiante guardado");
                txtRecorridos.setText(arbol.obtenerInOrdenTexto());
                limpiarCampos();

                if (panelArbolAVL != null) {
                    panelArbolAVL.refrescar();
                }

                JOptionPane.showMessageDialog(this, "Estudiante guardado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar el estudiante.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
        }
        generarIdAutomatico();
    }

    private void buscarEstudiante() {
        if (txtId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa el ID a buscar.");
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText().trim());
            Estudiante e = arbol.buscar(id);

            if (e != null) {
                txtNombre.setText(e.getNombre());
                txtEdad.setText(String.valueOf(e.getEdad()));
                txtPromedio.setText(String.valueOf(e.getPromedio()));
                cmbCarrera.setSelectedItem(obtenerTextoCarrera(e.getIdCarrera()));
                txtTelefono.setText(e.getTelefono());
                txtCorreo.setText(e.getCorreo());
                actualizarResumen("Estudiante encontrado");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el estudiante.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage());
        }
    }

    private void modificarEstudiante() {
        if (!validarCampos()) {
            return;
        }

        try {
            Estudiante e = leerFormulario();

            boolean actualizadoArbol = arbol.actualizar(e);
            boolean actualizadoBD = dao.actualizar(e);

            if (actualizadoArbol && actualizadoBD) {
                cargarTabla(arbol.obtenerTodosLosEstudiantes());
                actualizarResumen("Estudiante modificado");
                txtRecorridos.setText(arbol.obtenerInOrdenTexto());
                limpiarCampos();

                if (panelArbolAVL != null) {
                    panelArbolAVL.refrescar();
                }

                JOptionPane.showMessageDialog(this, "Estudiante modificado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo modificar. Verifica el ID.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + ex.getMessage());
        }
    }

    private void eliminarEstudiante() {
        if (txtId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa el ID del estudiante a eliminar.");
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText().trim());

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Deseas eliminar este estudiante?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            boolean eliminadoArbol = arbol.eliminar(id);
            boolean eliminadoBD = dao.eliminar(id);

            if (eliminadoArbol && eliminadoBD) {
                cargarTabla(arbol.obtenerTodosLosEstudiantes());
                actualizarResumen("Estudiante eliminado");
                txtRecorridos.setText(arbol.obtenerInOrdenTexto());
                limpiarCampos();

                if (panelArbolAVL != null) {
                    panelArbolAVL.refrescar();
                }

                JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar. Verifica el ID.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void sincronizarBD() {
        try {
            boolean ok = dao.sincronizarDesdeArbol(arbol.obtenerTodosLosEstudiantes());

            if (ok) {
                actualizarResumen("Sincronizado con BD");

                if (panelArbolAVL != null) {
                    panelArbolAVL.refrescar();
                }

                JOptionPane.showMessageDialog(this, "Base de datos sincronizada correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo sincronizar la base de datos.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al sincronizar: " + ex.getMessage());
        }
    }

    private void cargarCamposDesdeTabla() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            return;
        }

        txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
        txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
        txtEdad.setText(modeloTabla.getValueAt(fila, 2).toString());
        txtPromedio.setText(modeloTabla.getValueAt(fila, 3).toString());
        cmbCarrera.setSelectedItem(obtenerTextoCarrera(Integer.parseInt(modeloTabla.getValueAt(fila, 4).toString())));
        txtTelefono.setText(modeloTabla.getValueAt(fila, 5).toString());
        txtCorreo.setText(modeloTabla.getValueAt(fila, 6).toString());
    }

    private String obtenerTextoCarrera(int idCarrera) {
        switch (idCarrera) {
            case 1:
                return "1 - Sistemas";
            case 2:
                return "2 - Administración";
            case 3:
                return "3 - Contaduría";
            case 4:
                return "4 - Derecho";
            default:
                return "1 - Sistemas";
        }
    }
}