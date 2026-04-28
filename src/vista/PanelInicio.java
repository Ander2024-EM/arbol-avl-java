package vista;

import Modelo.ArbolAVL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PanelInicio extends JPanel {

    private final ArbolAVL arbol;
    private final FrmPrincipal parentFrame;

    private TarjetaResumen cardTotal;
    private TarjetaResumen cardAltura;
    private TarjetaResumen cardTema;

    private JLabel lblTituloPrincipal;
    private JLabel lblTextoPrincipal;
    private JLabel lblInstitucion;
    private JLabel lblProyecto;
    private JLabel lblTemaActivo;
    private JLabel lblFecha;
    private JLabel lblEstado;

    public PanelInicio(ArbolAVL arbol, FrmPrincipal parentFrame) {
        this.arbol = arbol;
        this.parentFrame = parentFrame;

        setLayout(new BorderLayout(18, 18));
        setBorder(new EmptyBorder(22, 22, 22, 22));
        setOpaque(true);

        initComponents();
        refrescarVista();
    }

    private void initComponents() {
        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelCentro(), BorderLayout.CENTER);
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout(16, 16));
        panel.setOpaque(false);

        panel.add(crearTarjetaBienvenida(), BorderLayout.CENTER);
        panel.add(crearTarjetaInstitucional(), BorderLayout.EAST);

        return panel;
    }

    private JPanel crearPanelCentro() {
        JPanel panel = new JPanel(new BorderLayout(16, 16));
        panel.setOpaque(false);

        JPanel resumen = new JPanel(new GridLayout(1, 3, 14, 14));
        resumen.setOpaque(false);

        cardTotal = new TarjetaResumen("Estudiantes Cargados", "0", new Color(0, 102, 204));
        cardAltura = new TarjetaResumen("Altura del Árbol", "0", new Color(0, 153, 102));
        cardTema = new TarjetaResumen("Tema Activo", "-", new Color(255, 166, 0));

        resumen.add(cardTotal);
        resumen.add(cardAltura);
        resumen.add(cardTema);

        JPanel accesos = new JPanel(new GridLayout(1, 3, 18, 18));
        accesos.setOpaque(false);

        accesos.add(crearTarjetaModulo(
                "Gestión de Estudiantes",
                "Registra, busca, modifica y elimina estudiantes de forma rápida y ordenada.",
                "Abrir módulo",
                new Color(0, 102, 204),
                BotonEstilizado.Tipo.PRIMARIO,
                () -> parentFrame.irAPanel("estudiantes")
        ));

        accesos.add(crearTarjetaModulo(
                "Árbol AVL",
                "Consulta la estructura, la altura y el comportamiento del árbol balanceado.",
                "Ver árbol",
                new Color(0, 153, 102),
                BotonEstilizado.Tipo.EXITO,
                () -> parentFrame.irAPanel("arbol")
        ));

        accesos.add(crearTarjetaModulo(
                "Reportes del Sistema",
                "Genera reportes en PDF, CSV y TXT para respaldar y exportar la información.",
                "Ir a reportes",
                new Color(34, 166, 179),
                BotonEstilizado.Tipo.INFO,
                () -> parentFrame.irAPanel("reportes")
        ));

        panel.add(resumen, BorderLayout.NORTH);
        panel.add(accesos, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearTarjetaBienvenida() {
        JPanel tarjeta = crearPanelRedondeado(26);
        tarjeta.setLayout(new BorderLayout());
        tarjeta.setBorder(new EmptyBorder(28, 34, 28, 28));

        JPanel contenido = new JPanel();
        contenido.setOpaque(false);
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        lblTituloPrincipal = new JLabel("Bienvenido al Sistema de Gestión de Estudiantes");
        lblTituloPrincipal.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTituloPrincipal.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblTituloPrincipal.setHorizontalAlignment(SwingConstants.LEFT);

        lblTextoPrincipal = new JLabel(
                "<html><div style='width:660px;'>Administra estudiantes, visualiza el árbol AVL, consulta recorridos y genera reportes desde una interfaz moderna, clara y profesional.</div></html>"
        );
        lblTextoPrincipal.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblTextoPrincipal.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblTextoPrincipal.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        botones.setOpaque(false);
        botones.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnEstudiantes = new BotonEstilizado("Ir a Estudiantes", BotonEstilizado.Tipo.PRIMARIO);
        JButton btnArbol = new BotonEstilizado("Visualizar Árbol", BotonEstilizado.Tipo.EXITO);
        JButton btnReportes = new BotonEstilizado("Abrir Reportes", BotonEstilizado.Tipo.INFO);

        btnEstudiantes.setPreferredSize(new Dimension(180, 42));
        btnArbol.setPreferredSize(new Dimension(180, 42));
        btnReportes.setPreferredSize(new Dimension(180, 42));

        btnEstudiantes.addActionListener(e -> parentFrame.irAPanel("estudiantes"));
        btnArbol.addActionListener(e -> parentFrame.irAPanel("arbol"));
        btnReportes.addActionListener(e -> parentFrame.irAPanel("reportes"));

        botones.add(btnEstudiantes);
        botones.add(btnArbol);
        botones.add(btnReportes);

        contenido.add(lblTituloPrincipal);
        contenido.add(Box.createVerticalStrut(16));
        contenido.add(lblTextoPrincipal);
        contenido.add(Box.createVerticalStrut(24));
        contenido.add(botones);

        tarjeta.add(contenido, BorderLayout.WEST);
        return tarjeta;
    }

    private JPanel crearTarjetaInstitucional() {
        JPanel tarjeta = crearPanelRedondeado(26);
        tarjeta.setPreferredSize(new Dimension(280, 0));
        tarjeta.setLayout(new BorderLayout(10, 10));
        tarjeta.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblLogo = new JLabel();
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon logo = cargarLogo("/vista/logo_umg.png", 130, 130);
        if (logo == null) {
            logo = cargarLogo("/vista/img/logo_umg.png", 130, 130);
        }

        if (logo != null) {
            lblLogo.setIcon(logo);
        } else {
            lblLogo.setText("UMG");
            lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        }

        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

        lblInstitucion = new JLabel("<html><center>Universidad Mariano Gálvez<br>de Guatemala</center></html>");
        lblInstitucion.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblInstitucion.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblProyecto = new JLabel("Proyecto AVL - Gestión Académica");
        lblProyecto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblProyecto.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblTemaActivo = new JLabel("Tema visual: -");
        lblTemaActivo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTemaActivo.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblFecha = new JLabel("Fecha: -");
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblFecha.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblEstado = new JLabel("Estado general: Activo");
        lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);

        info.add(lblInstitucion);
        info.add(Box.createVerticalStrut(6));
        info.add(lblProyecto);
        info.add(Box.createVerticalStrut(10));
        info.add(lblTemaActivo);
        info.add(Box.createVerticalStrut(4));
        info.add(lblFecha);
        info.add(Box.createVerticalStrut(4));
        info.add(lblEstado);

        tarjeta.add(lblLogo, BorderLayout.NORTH);
        tarjeta.add(info, BorderLayout.CENTER);

        return tarjeta;
    }

private JPanel crearTarjetaModulo(String titulo, String descripcion, String textoBoton,
                                  Color colorAcento, BotonEstilizado.Tipo tipoBoton,
                                  Runnable accion) {

    JPanel tarjeta = crearPanelRedondeado(24);
    tarjeta.setLayout(new BorderLayout());
    tarjeta.setBorder(new EmptyBorder(0, 0, 0, 0));

    JPanel lineaSuperior = new JPanel();
    lineaSuperior.setBackground(colorAcento);
    lineaSuperior.setPreferredSize(new Dimension(0, 8));

    JPanel contenido = new JPanel();
    contenido.setOpaque(false);
    contenido.setLayout(new BorderLayout());
    contenido.setBorder(new EmptyBorder(18, 22, 18, 22));

    JPanel cuerpo = new JPanel();
    cuerpo.setOpaque(false);
    cuerpo.setLayout(new BoxLayout(cuerpo, BoxLayout.Y_AXIS));

    JLabel lblTitulo = new JLabel(titulo);
    lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
    lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

    JTextArea txtDescripcion = new JTextArea(descripcion);
    txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
    txtDescripcion.setLineWrap(true);
    txtDescripcion.setWrapStyleWord(true);
    txtDescripcion.setEditable(false);
    txtDescripcion.setOpaque(false);
    txtDescripcion.setBorder(null);
    txtDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);
   txtDescripcion.setRows(5);
txtDescripcion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

    cuerpo.add(lblTitulo);
    cuerpo.add(Box.createVerticalStrut(12));
    cuerpo.add(txtDescripcion);

    JButton btn = new BotonEstilizado(textoBoton, tipoBoton);
    btn.setPreferredSize(new Dimension(155, 40));
    btn.addActionListener(e -> accion.run());

    JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panelBoton.setOpaque(false);
    panelBoton.setBorder(new EmptyBorder(16, 0, 0, 0));
    panelBoton.add(btn);

    contenido.add(cuerpo, BorderLayout.NORTH);
    contenido.add(panelBoton, BorderLayout.SOUTH);

    tarjeta.add(lineaSuperior, BorderLayout.NORTH);
    tarjeta.add(contenido, BorderLayout.CENTER);

    aplicarColoresTarjetaModulo(tarjeta, lblTitulo, txtDescripcion);

    return tarjeta;
}

    private void aplicarColoresTarjetaModulo(JPanel tarjeta, JLabel titulo, JTextArea descripcion) {
        TemaVisual tema = GestorTema.getTema();
        tarjeta.setBackground(tema.fondoCard());
        titulo.setForeground(tema.textoPrincipal());
        descripcion.setForeground(tema.textoSecundario());
    }

    private JPanel crearPanelRedondeado(int radio) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                TemaVisual tema = GestorTema.getTema();
                Color fondo = getBackground() != null ? getBackground() : tema.fondoCard();

                g2.setColor(new Color(0, 0, 0, 16));
                g2.fillRoundRect(4, 6, getWidth() - 8, getHeight() - 8, radio, radio);

                g2.setColor(fondo);
                g2.fillRoundRect(0, 0, getWidth() - 8, getHeight() - 8, radio, radio);

                g2.setColor(tema.borde());
                g2.drawRoundRect(0, 0, getWidth() - 9, getHeight() - 9, radio, radio);

                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };
    }

    private ImageIcon cargarLogo(String ruta, int ancho, int alto) {
        try {
            java.net.URL url = getClass().getResource(ruta);
            if (url == null) {
                return null;
            }
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            return null;
        }
    }

    public void refrescarVista() {
        TemaVisual tema = GestorTema.getTema();

        setBackground(tema.fondoGeneral());

        if (cardTotal != null) {
            cardTotal.setValor(String.valueOf(arbol.contarNodos()));
            cardTotal.setColorPrincipal(tema.botonPrimario());
        }

        if (cardAltura != null) {
            cardAltura.setValor(String.valueOf(arbol.alturaArbol()));
            cardAltura.setColorPrincipal(tema.botonExito());
        }

        if (cardTema != null) {
            cardTema.setValor(tema.nombreTema());
            cardTema.setColorPrincipal(tema.botonAdvertencia());
        }

        if (lblTituloPrincipal != null) {
            lblTituloPrincipal.setForeground(tema.textoPrincipal());
        }

        if (lblTextoPrincipal != null) {
            lblTextoPrincipal.setForeground(tema.textoSecundario());
        }

        if (lblInstitucion != null) {
            lblInstitucion.setForeground(tema.textoPrincipal());
        }

        if (lblProyecto != null) {
            lblProyecto.setForeground(tema.textoSecundario());
        }

        if (lblTemaActivo != null) {
            lblTemaActivo.setForeground(tema.textoPrincipal());
            lblTemaActivo.setText("Tema visual: " + tema.nombreTema());
        }

        if (lblFecha != null) {
            lblFecha.setForeground(tema.textoSecundario());
            lblFecha.setText("Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        }

        if (lblEstado != null) {
            lblEstado.setForeground(tema.textoSecundario());
        }

        revalidate();
        repaint();
    }
}