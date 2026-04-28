package vista;

import Modelo.ArbolAVL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PanelConfiguracion extends JPanel {

    private final ArbolAVL arbol;
    private final JFrame parentFrame;

    private JTextField txtNombreSistema;
    private JTextField txtInstitucion;
    private JTextField txtRuta;

    private JCheckBox chkAltura;
    private JCheckBox chkID;
    private JCheckBox chkAutoRefresh;

    private JComboBox<String> cmbTema;

    private TarjetaResumen cardTotal;
    private TarjetaResumen cardAltura;
    private TarjetaResumen cardVersion;

    public PanelConfiguracion(ArbolAVL arbol, JFrame parentFrame) {
        this.arbol = arbol;
        this.parentFrame = parentFrame;

        setLayout(new BorderLayout());
        setBackground(new Color(240, 242, 245));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        initComponents();
        actualizarResumen();
    }

    private void initComponents() {
        add(crearPanelResumen(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
    }

    private JPanel crearPanelResumen() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 15, 15));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(0, 0, 20, 0));

        cardTotal = new TarjetaResumen("Total de Estudiantes", "0", new Color(0, 102, 204));
        cardAltura = new TarjetaResumen("Altura del Árbol", "0", new Color(0, 153, 102));
        cardVersion = new TarjetaResumen("Versión del Sistema", "1.0", new Color(255, 140, 0));

        panel.add(cardTotal);
        panel.add(cardAltura);
        panel.add(cardVersion);

        return panel;
    }

    private JPanel crearPanelCentral() {
        JPanel principal = new JPanel(new GridLayout(1, 3, 20, 20));
        principal.setOpaque(false);

        principal.add(crearCardSistema());
        principal.add(crearCardAVL());
        principal.add(crearCardInfo());

        return principal;
    }

private JPanel crearCardSistema() {
    JPanel panel = crearCardBase();

    JLabel titulo = crearTituloCard("Configuración del Sistema");

    // 🔹 CREAR COMPONENTES PRIMERO
    txtNombreSistema = new JTextField("Sistema AVL");
    txtInstitucion = new JTextField("Instituto IPEF");
    txtRuta = new JTextField("reportes_generados");

    cmbTema = new JComboBox<>(new String[]{"Claro", "Oscuro", "Azul Noche"});

    // 🔹 APLICAR ESTILO DESPUÉS DE CREARLOS
    Font fuenteCampos = new Font("Segoe UI", Font.PLAIN, 16);

    txtNombreSistema.setFont(fuenteCampos);
    txtInstitucion.setFont(fuenteCampos);
    txtRuta.setFont(fuenteCampos);

    txtNombreSistema.setPreferredSize(new Dimension(100, 40));
    txtInstitucion.setPreferredSize(new Dimension(100, 40));
    txtRuta.setPreferredSize(new Dimension(100, 40));

    txtNombreSistema.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 220)));
    txtInstitucion.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 220)));
    txtRuta.setBorder(BorderFactory.createLineBorder(new Color(180, 200, 220)));

    cmbTema.setFont(fuenteCampos);
    cmbTema.setPreferredSize(new Dimension(100, 42));

    if (GestorTema.getTema() != null) {
        cmbTema.setSelectedItem(GestorTema.getTema().nombreTema());
    }

    JButton btnGuardar = new BotonEstilizado("Guardar", BotonEstilizado.Tipo.EXITO);
    JButton btnAplicarTema = new BotonEstilizado("Aplicar Tema", BotonEstilizado.Tipo.PRIMARIO);

    btnGuardar.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Configuración guardada.")
    );

    btnAplicarTema.addActionListener(e -> aplicarTemaSeleccionado());

    // 🔹 ARMAR PANEL
    panel.add(titulo);
    panel.add(Box.createVerticalStrut(10));

    panel.add(crearLabel("Nombre del Sistema:"));
    panel.add(txtNombreSistema);

    panel.add(Box.createVerticalStrut(10));
    panel.add(crearLabel("Institución:"));
    panel.add(txtInstitucion);

    panel.add(Box.createVerticalStrut(10));
    panel.add(crearLabel("Ruta Reportes:"));
    panel.add(txtRuta);

    panel.add(Box.createVerticalStrut(10));
    panel.add(crearLabel("Tema visual:"));
    panel.add(cmbTema);

    panel.add(Box.createVerticalStrut(16));
    panel.add(btnGuardar);

    panel.add(Box.createVerticalStrut(10));
    panel.add(btnAplicarTema);

    return panel;
}

    private JPanel crearCardAVL() {
        JPanel panel = crearCardBase();

        JLabel titulo = crearTituloCard("Configuración AVL");

        chkAltura = new JCheckBox("Mostrar altura en nodos", true);
        chkID = new JCheckBox("Mostrar ID en nodos", true);
        chkAutoRefresh = new JCheckBox("Auto refrescar árbol", true);

        estilizarCheck(chkAltura);
        estilizarCheck(chkID);
        estilizarCheck(chkAutoRefresh);

        JButton btnAplicar = new BotonEstilizado("Aplicar", BotonEstilizado.Tipo.PRIMARIO);
        JButton btnRestablecer = new BotonEstilizado("Restablecer", BotonEstilizado.Tipo.SECUNDARIO);

        btnAplicar.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Configuración AVL aplicada.")
        );

        btnRestablecer.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Configuración restablecida.")
        );

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(18));
        panel.add(chkAltura);
        panel.add(Box.createVerticalStrut(12));
        panel.add(chkID);
        panel.add(Box.createVerticalStrut(12));
        panel.add(chkAutoRefresh);
        panel.add(Box.createVerticalStrut(24));
        panel.add(btnAplicar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnRestablecer);

        return panel;
    }

    private JPanel crearCardInfo() {
        JPanel panel = crearCardBase();

        JLabel titulo = crearTituloCard("Información del Sistema");

        JLabel lblInfo = new JLabel();
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblInfo.setForeground(new Color(60, 60, 60));

        lblInfo.setText(
                "<html>" +
                        "Fecha actual: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "<br><br>" +
                        "Estado general: Activo<br>" +
                        "Módulo visual: Configuración<br>" +
                        "Árbol en memoria: Sí" +
                        "</html>"
        );

        JButton btnActualizar = new BotonEstilizado("Actualizar", BotonEstilizado.Tipo.INFO);
        btnActualizar.addActionListener(e -> actualizarResumen());

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(18));
        panel.add(lblInfo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnActualizar);

        return panel;
    }

    private JPanel crearCardBase() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230)),
                new EmptyBorder(20, 20, 20, 20)
        ));
        return panel;
    }

    private JLabel crearTituloCard(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbl.setForeground(new Color(18, 41, 77));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl.setForeground(new Color(60, 60, 60));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private void estilizarCheck(JCheckBox chk) {
        chk.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        chk.setOpaque(false);
        chk.setForeground(new Color(40, 40, 40));
        chk.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void aplicarTemaSeleccionado() {
        String seleccionado = cmbTema.getSelectedItem().toString();
        GestorTema.setTemaPorNombre(seleccionado);

        if (parentFrame instanceof FrmPrincipal) {
            ((FrmPrincipal) parentFrame).refrescarTema();
        }

        JOptionPane.showMessageDialog(parentFrame, "Tema aplicado correctamente.");
    }

    private void actualizarResumen() {
        cardTotal.setValor(String.valueOf(arbol.contarNodos()));
        cardAltura.setValor(String.valueOf(arbol.alturaArbol()));
        cardVersion.setValor("1.0");
    }
}