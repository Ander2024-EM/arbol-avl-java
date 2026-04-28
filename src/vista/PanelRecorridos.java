package vista;

import Modelo.ArbolAVL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PanelRecorridos extends JPanel {

    private final ArbolAVL arbol;

    private TarjetaResumen cardTotalNodos;
    private TarjetaResumen cardAltura;
    private TarjetaResumen cardEstado;

    private JTextArea txtResultado;

    public PanelRecorridos(ArbolAVL arbol) {
        this.arbol = arbol;

        setLayout(new BorderLayout());
        setBackground(new Color(240, 242, 245));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        initComponents();
        actualizarResumen("Datos actualizados");
    }

    private void initComponents() {
        add(crearPanelResumen(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
    }

    private JPanel crearPanelResumen() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 15, 15));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(0, 0, 20, 0));

        cardTotalNodos = new TarjetaResumen("Total de Nodos", "0", new Color(0, 102, 204));
        cardAltura = new TarjetaResumen("Altura del Árbol", "0", new Color(0, 153, 102));
        cardEstado = new TarjetaResumen("Estado", "Sin datos", new Color(255, 140, 0));

        panel.add(cardTotalNodos);
        panel.add(cardAltura);
        panel.add(cardEstado);

        return panel;
    }

    private JPanel crearPanelCentral() {
        JPanel principal = new JPanel(new BorderLayout(15, 15));
        principal.setOpaque(false);

        JPanel panelBotones = new JPanel(new GridLayout(1, 4, 12, 12));
        panelBotones.setOpaque(false);

        JButton btnInorden = new BotonEstilizado("Mostrar Inorden", BotonEstilizado.Tipo.PRIMARIO);
        JButton btnPreorden = new BotonEstilizado("Mostrar Preorden", BotonEstilizado.Tipo.PRIMARIO);
        JButton btnPostorden = new BotonEstilizado("Mostrar Postorden", BotonEstilizado.Tipo.EXITO);
        JButton btnRefrescar = new BotonEstilizado("Refrescar", BotonEstilizado.Tipo.INFO);

        btnInorden.addActionListener(e -> mostrarInorden());
        btnPreorden.addActionListener(e -> mostrarPreorden());
        btnPostorden.addActionListener(e -> mostrarPostorden());
        btnRefrescar.addActionListener(e -> refrescar());

        panelBotones.add(btnInorden);
        panelBotones.add(btnPreorden);
        panelBotones.add(btnPostorden);
        panelBotones.add(btnRefrescar);

        JPanel panelResultado = new JPanel(new BorderLayout());
        panelResultado.setBackground(Color.WHITE);
        panelResultado.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel titulo = new JLabel("Resultado de Recorridos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(18, 41, 77));

        txtResultado = new JTextArea();
        txtResultado.setFont(new Font("Consolas", Font.PLAIN, 16));
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setMargin(new Insets(12, 12, 12, 12));

        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 225, 230)));

        panelResultado.add(titulo, BorderLayout.NORTH);
        panelResultado.add(scroll, BorderLayout.CENTER);

        principal.add(panelBotones, BorderLayout.NORTH);
        principal.add(panelResultado, BorderLayout.CENTER);

        return principal;
    }

    private void mostrarInorden() {
        txtResultado.setText(arbol.obtenerInOrdenTexto());
        actualizarResumen("Recorrido Inorden");
    }

    private void mostrarPreorden() {
        txtResultado.setText(arbol.obtenerPreOrdenTexto());
        actualizarResumen("Recorrido Preorden");
    }

    private void mostrarPostorden() {
        txtResultado.setText(arbol.obtenerPostOrdenTexto());
        actualizarResumen("Recorrido Postorden");
    }

    public void refrescar() {
        txtResultado.setText("");
        actualizarResumen("Datos actualizados");
    }

    private void actualizarResumen(String estado) {
        cardTotalNodos.setValor(String.valueOf(arbol.contarNodos()));
        cardAltura.setValor(String.valueOf(arbol.alturaArbol()));
        cardEstado.setValor(estado);

        String e = estado.toLowerCase();

        if (e.contains("inorden") || e.contains("preorden")) {
            cardEstado.setColorPrincipal(new Color(0, 102, 204));
        } else if (e.contains("postorden")) {
            cardEstado.setColorPrincipal(new Color(0, 153, 102));
        } else {
            cardEstado.setColorPrincipal(new Color(255, 140, 0));
        }
    }
}