package vista;

import Modelo.ArbolAVL;
import dao.EstudianteDAO;
import vista.PanelInicio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import proyecto_arbol.ServidorAVL;

public class FrmPrincipal extends JFrame {

    private JPanel panelContenido;
    private CardLayout cardLayout;

    private final ArbolAVL arbol;
    private final EstudianteDAO dao;

    private PanelArbolAVL panelArbolAVL;
    private PanelRecorridos panelRecorridos;
   private PanelInicio panelInicio;

    private JPanel panelLateral;
    private JPanel panelSuperior;
    private PanelAsistente panelAsistente;
    
    public FrmPrincipal() {
        arbol = new ArbolAVL();
        dao = new EstudianteDAO();

        configurarVentana();
        inicializarComponentes();
        refrescarTema();
        ServidorAVL.iniciar(arbol);
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión de Estudiantes - Árbol AVL");
        setSize(1450, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(1200, 700));
    }

    private void inicializarComponentes() {
        panelLateral = crearPanelLateral();
        panelSuperior = crearPanelSuperior();
        panelContenido = crearPanelContenido();

        add(panelLateral, BorderLayout.WEST);
        add(panelSuperior, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);
    }

    private JPanel crearPanelLateral() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(220, 0));

        JLabel logo = new JLabel("<html><center>SISTEMA AVL<br>ESTUDIANTES</center></html>", SwingConstants.CENTER);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        logo.setBorder(new EmptyBorder(30, 10, 30, 10));

        JPanel menu = new JPanel(new GridLayout(8, 1, 0, 10));
        menu.setOpaque(false);
        menu.setBorder(new EmptyBorder(10, 20, 10, 20));

    JButton btnInicio = crearBotonMenu("Inicio", "/vista/icons/home.png");
JButton btnEstudiantes = crearBotonMenu("Estudiantes", "/vista/icons/student.png");
JButton btnArbol = crearBotonMenu("Árbol AVL", "/vista/icons/tree.png");
JButton btnRecorridos = crearBotonMenu("Recorridos", "/vista/icons/recorrdio.png");
JButton btnReportes = crearBotonMenu("Reportes", "/vista/icons/report.png");
JButton btnConfiguracion = crearBotonMenu("Configuración", "/vista/icons/settings.png");
JButton btnAyuda = crearBotonMenu("Ayuda", "/vista/icons/help.png");
JButton btnSalir = crearBotonMenu("Salir", "/vista/icons/exit.png");

        btnInicio.addActionListener(e -> mostrarPanel("inicio"));
        btnEstudiantes.addActionListener(e -> mostrarPanel("estudiantes"));
        btnArbol.addActionListener(e -> mostrarPanel("arbol"));
        btnRecorridos.addActionListener(e -> mostrarPanel("recorridos"));
        btnReportes.addActionListener(e -> mostrarPanel("reportes"));
        btnConfiguracion.addActionListener(e -> mostrarPanel("configuracion"));
btnAyuda.addActionListener(e -> {
    mostrarPanel("ayuda");
    panelAsistente.activarAsistente();
});
        btnSalir.addActionListener(e -> System.exit(0));

        menu.add(btnInicio);
        menu.add(btnEstudiantes);
        menu.add(btnArbol);
        menu.add(btnRecorridos);
        menu.add(btnReportes);
        menu.add(btnConfiguracion);
        menu.add(btnAyuda);
        menu.add(btnSalir);

        JLabel footer = new JLabel("<html><center>Software desarrollado<br>en Java Swing</center></html>", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footer.setBorder(new EmptyBorder(20, 10, 20, 10));

        panel.add(logo, BorderLayout.NORTH);
        panel.add(menu, BorderLayout.CENTER);
        panel.add(footer, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(0, 70));
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel titulo = new JLabel("MÓDULO DE GESTIÓN DE ESTUDIANTES");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));

        JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        derecha.setOpaque(false);

        BotonEstilizado btnHogar = new BotonEstilizado("Hogar", BotonEstilizado.Tipo.INFO);
        BotonEstilizado btnSalir = new BotonEstilizado("Salir", BotonEstilizado.Tipo.PELIGRO);

        btnHogar.addActionListener(e -> mostrarPanel("inicio"));
        btnSalir.addActionListener(e -> System.exit(0));

        derecha.add(btnHogar);
        derecha.add(btnSalir);

        panel.add(titulo, BorderLayout.WEST);
        panel.add(derecha, BorderLayout.EAST);

        return panel;
    }

private JPanel crearPanelContenido() {
    cardLayout = new CardLayout();
    JPanel panel = new JPanel(cardLayout);

    panelArbolAVL = new PanelArbolAVL(arbol);
    panelRecorridos = new PanelRecorridos(arbol);
    panelInicio = new PanelInicio(arbol, this);
    panel.add(panelInicio, "inicio");
    panel.add(new PanelEstudiantes(arbol, dao, panelArbolAVL), "estudiantes");
    panel.add(crearPanelVisualArbol(), "arbol");
    panel.add(panelRecorridos, "recorridos");
    panel.add(new PanelReportes(arbol, dao), "reportes");
    panel.add(new PanelConfiguracion(arbol, this), "configuracion");
panelAsistente = new PanelAsistente(arbol);
panel.add(panelAsistente, "ayuda");

    cardLayout.show(panel, "inicio");
    return panel;
}

    // =========================
    // PANEL INICIO ANTIGUO (RESPALDO)
    // =========================

    private JPanel crearPanelInicioRespaldo() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(new Color(245, 247, 251));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel superior = new JPanel(new BorderLayout(20, 20));
        superior.setOpaque(false);

        JPanel bienvenida = new JPanel();
        bienvenida.setLayout(new BoxLayout(bienvenida, BoxLayout.Y_AXIS));
        bienvenida.setBackground(Color.WHITE);
        bienvenida.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 238)),
                new EmptyBorder(28, 28, 28, 28)
        ));

        JLabel lblTitulo = new JLabel("Bienvenido al Sistema de Gestión de Estudiantes");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(18, 41, 77));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTexto = new JLabel(
                "<html><div style='width:680px;'>Administra estudiantes, visualiza el árbol AVL, realiza recorridos y genera reportes desde un solo lugar.</div></html>"
        );
        lblTexto.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblTexto.setForeground(new Color(80, 80, 90));
        lblTexto.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnEntrar = new BotonEstilizado("Ir a Estudiantes", BotonEstilizado.Tipo.PRIMARIO);
        btnEntrar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEntrar.setMaximumSize(new Dimension(220, 45));
        btnEntrar.addActionListener(e -> mostrarPanel("estudiantes"));

        bienvenida.add(lblTitulo);
        bienvenida.add(Box.createVerticalStrut(14));
        bienvenida.add(lblTexto);
        bienvenida.add(Box.createVerticalStrut(20));
        bienvenida.add(btnEntrar);

        JPanel logoCard = new JPanel(new BorderLayout());
        logoCard.setPreferredSize(new Dimension(260, 0));
        logoCard.setBackground(Color.WHITE);
        logoCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 238)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblLogo = new JLabel();
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon logo = cargarLogo("/vista/img/logo_umg.png", 120, 120);
        if (logo != null) {
            lblLogo.setIcon(logo);
        } else {
            lblLogo.setText("UMG");
            lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 30));
            lblLogo.setForeground(new Color(18, 41, 77));
            lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JLabel lblUni = new JLabel(
                "<html><center>Universidad Mariano Gálvez<br>de Guatemala</center></html>",
                SwingConstants.CENTER
        );
        lblUni.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblUni.setForeground(new Color(18, 41, 77));

        logoCard.add(lblLogo, BorderLayout.CENTER);
        logoCard.add(lblUni, BorderLayout.SOUTH);

        superior.add(bienvenida, BorderLayout.CENTER);
        superior.add(logoCard, BorderLayout.EAST);

        JPanel inferior = new JPanel(new GridLayout(1, 3, 20, 20));
        inferior.setOpaque(false);

        inferior.add(crearCardInicio(
                "Gestión de Estudiantes",
                "Registra, busca, modifica y elimina estudiantes de forma rápida y ordenada.",
                new Color(0, 102, 204)
        ));

        inferior.add(crearCardInicio(
                "Árbol AVL",
                "Consulta estructura, altura y funcionamiento del árbol balanceado.",
                new Color(0, 153, 102)
        ));

        inferior.add(crearCardInicio(
                "Reportes y Base de Datos",
                "Genera reportes y mantén sincronizada la información del sistema.",
                new Color(255, 140, 0)
        ));

        panel.add(superior, BorderLayout.NORTH);
        panel.add(inferior, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearSidebarInicioRespaldo() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(220, 0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(250, 250, 252));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(230, 235, 242)),
                new EmptyBorder(20, 18, 20, 18)
        ));

        JLabel lblMarca = new JLabel("Sistema AVL");
        lblMarca.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblMarca.setForeground(new Color(54, 79, 199));
        lblMarca.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSub = new JLabel("Gestión Académica");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(new Color(110, 110, 120));
        lblSub.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(lblMarca);
        panel.add(Box.createVerticalStrut(4));
        panel.add(lblSub);
        panel.add(Box.createVerticalStrut(30));

        JLabel lblLogo = new JLabel();
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon logo = cargarLogo("/vista/img/logo_umg.png", 95, 95);
        if (logo != null) {
            lblLogo.setIcon(logo);
        } else {
            lblLogo.setText("UMG");
            lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 28));
            lblLogo.setForeground(new Color(18, 41, 77));
        }

        JLabel lblNombre = new JLabel("Universidad Mariano Gálvez");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNombre.setForeground(new Color(18, 41, 77));
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblCorreo = new JLabel("Proyecto de Programación");
        lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblCorreo.setForeground(new Color(120, 120, 130));
        lblCorreo.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(lblLogo);
        panel.add(Box.createVerticalStrut(15));
        panel.add(lblNombre);
        panel.add(Box.createVerticalStrut(5));
        panel.add(lblCorreo);
        panel.add(Box.createVerticalStrut(30));

        panel.add(crearItemSidebarInicio("🏠  Dashboard"));
        panel.add(Box.createVerticalStrut(12));
        panel.add(crearItemSidebarInicio("👨‍🎓  Estudiantes"));
        panel.add(Box.createVerticalStrut(12));
        panel.add(crearItemSidebarInicio("🌳  Árbol AVL"));
        panel.add(Box.createVerticalStrut(12));
        panel.add(crearItemSidebarInicio("📄  Reportes"));
        panel.add(Box.createVerticalStrut(12));
        panel.add(crearItemSidebarInicio("⚙  Configuración"));

        panel.add(Box.createVerticalGlue());

        JLabel lblFooter = new JLabel("Interfaz principal");
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblFooter.setForeground(new Color(150, 150, 160));
        lblFooter.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(lblFooter);

        return panel;
    }

    private JPanel crearCentroInicioRespaldo() {
        JPanel panel = new JPanel(new BorderLayout(18, 18));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(5, 10, 5, 10));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("Hola, bienvenido");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 34));
        lblTitulo.setForeground(new Color(35, 35, 60));

        JLabel lblSubtitulo = new JLabel("Panel principal del sistema de gestión de estudiantes");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblSubtitulo.setForeground(new Color(120, 120, 130));

        textos.add(lblTitulo);
        textos.add(Box.createVerticalStrut(8));
        textos.add(lblSubtitulo);

        JButton btnAccion = new BotonEstilizado("Abrir módulo estudiantes", BotonEstilizado.Tipo.PRIMARIO);
        btnAccion.setPreferredSize(new Dimension(220, 44));
        btnAccion.addActionListener(e -> mostrarPanel("estudiantes"));

        header.add(textos, BorderLayout.WEST);
        header.add(btnAccion, BorderLayout.EAST);

        JPanel modulos = new JPanel(new GridLayout(1, 3, 16, 16));
        modulos.setOpaque(false);

        modulos.add(crearCardDashboard("Gestión de Estudiantes",
                "Registrar, buscar, modificar y eliminar estudiantes.",
                new Color(123, 63, 152)));

        modulos.add(crearCardDashboard("Árbol AVL",
                "Visualiza recorridos, altura y estructura balanceada.",
                new Color(110, 196, 207)));

        modulos.add(crearCardDashboard("Base de Datos",
                "Sincroniza y conserva la información del sistema.",
                new Color(255, 132, 84)));

        JPanel inferior = new JPanel(new GridLayout(1, 2, 16, 16));
        inferior.setOpaque(false);

        inferior.add(crearBloqueTareasInicio());
        inferior.add(crearBloqueEstadisticasInicio());

        panel.add(header, BorderLayout.NORTH);
        panel.add(modulos, BorderLayout.CENTER);
        panel.add(inferior, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelDerechoInicioRespaldo() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(250, 0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(250, 246, 244));
        panel.setBorder(new EmptyBorder(22, 18, 22, 18));

        JLabel lblTitulo = new JLabel("Resumen");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(50, 30, 70));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblFecha = new JLabel("Hoy");
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblFecha.setForeground(new Color(120, 120, 130));
        lblFecha.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(8));
        panel.add(lblFecha);
        panel.add(Box.createVerticalStrut(30));

        panel.add(crearEventoInicio("10:00", "Revisión de estudiantes", new Color(110, 196, 207)));
        panel.add(Box.createVerticalStrut(15));
        panel.add(crearEventoInicio("13:20", "Análisis AVL", new Color(255, 132, 84)));
        panel.add(Box.createVerticalStrut(15));
        panel.add(crearEventoInicio("15:00", "Generación de reportes", new Color(123, 63, 152)));
        panel.add(Box.createVerticalStrut(25));

        TarjetaResumen cardA = new TarjetaResumen("Nodos cargados", String.valueOf(arbol.contarNodos()), new Color(54, 79, 199));
        TarjetaResumen cardB = new TarjetaResumen("Altura actual", String.valueOf(arbol.alturaArbol()), new Color(0, 153, 102));

        cardA.setAlignmentX(Component.LEFT_ALIGNMENT);
        cardB.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(cardA);
        panel.add(Box.createVerticalStrut(14));
        panel.add(cardB);

        return panel;
    }

    // =========================
    // MÉTODOS AUXILIARES DE RESPALDO
    // =========================

    private JPanel crearCardDashboard(String titulo, String descripcion, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(new EmptyBorder(18, 18, 18, 18));
        card.setPreferredSize(new Dimension(0, 150));

        JLabel lblTitulo = new JLabel("<html>" + titulo.replace(" ", "<br>") + "</html>");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblDesc = new JLabel("<html><div style='color:white; font-size:11px;'>" + descripcion + "</div></html>");
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDesc.setForeground(new Color(255, 255, 255));

        JPanel barra = new JPanel();
        barra.setOpaque(false);
        barra.setLayout(new BorderLayout());

        JProgressBar progreso = new JProgressBar();
        progreso.setValue(75);
        progreso.setBorderPainted(false);
        progreso.setForeground(Color.WHITE);
        progreso.setBackground(new Color(255, 255, 255, 70));

        barra.add(progreso, BorderLayout.SOUTH);

        card.add(lblTitulo, BorderLayout.CENTER);
        card.add(lblDesc, BorderLayout.SOUTH);
        card.add(barra, BorderLayout.NORTH);

        return card;
    }

    private JPanel crearBloqueTareasInicio() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 235, 242), 1),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel titulo = new JLabel("Tareas de hoy");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(50, 30, 70));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(18));
        panel.add(crearTareaInicio("Revisar estudiantes", "Verificar registros recientes", new Color(255, 132, 84)));
        panel.add(Box.createVerticalStrut(12));
        panel.add(crearTareaInicio("Analizar AVL", "Comprobar altura y nodos", new Color(123, 63, 152)));
        panel.add(Box.createVerticalStrut(12));
        panel.add(crearTareaInicio("Preparar reportes", "Exportaciones pendientes", new Color(110, 196, 207)));

        return panel;
    }

    private JPanel crearBloqueEstadisticasInicio() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 235, 242), 1),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel titulo = new JLabel("Estadísticas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(50, 30, 70));

        JPanel grid = new JPanel(new GridLayout(2, 2, 12, 12));
        grid.setOpaque(false);

        grid.add(crearMiniStat("Nodos", String.valueOf(arbol.contarNodos())));
        grid.add(crearMiniStat("Altura", String.valueOf(arbol.alturaArbol())));
        grid.add(crearMiniStat("Tema", GestorTema.getTema().nombreTema()));
        grid.add(crearMiniStat("Estado", "Activo"));

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(grid, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearMiniStat(String titulo, String valor) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBackground(new Color(248, 248, 251));
        box.setBorder(new EmptyBorder(14, 14, 14, 14));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblTitulo.setForeground(new Color(120, 120, 130));

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblValor.setForeground(new Color(50, 30, 70));

        box.add(lblTitulo);
        box.add(Box.createVerticalStrut(8));
        box.add(lblValor);

        return box;
    }

    private JPanel crearTareaInicio(String titulo, String descripcion, Color color) {
        JPanel item = new JPanel(new BorderLayout(12, 0));
        item.setBackground(new Color(250, 250, 252));
        item.setBorder(new EmptyBorder(12, 12, 12, 12));
        item.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel linea = new JPanel();
        linea.setBackground(color);
        linea.setPreferredSize(new Dimension(6, 0));

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(35, 35, 60));

        JLabel lblDesc = new JLabel(descripcion);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(new Color(120, 120, 130));

        textos.add(lblTitulo);
        textos.add(Box.createVerticalStrut(4));
        textos.add(lblDesc);

        item.add(linea, BorderLayout.WEST);
        item.add(textos, BorderLayout.CENTER);

        return item;
    }

    private JPanel crearEventoInicio(String hora, String texto, Color color) {
        JPanel item = new JPanel(new BorderLayout(10, 0));
        item.setOpaque(false);
        item.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblHora = new JLabel(hora);
        lblHora.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblHora.setForeground(new Color(50, 30, 70));

        JPanel linea = new JPanel();
        linea.setBackground(color);
        linea.setPreferredSize(new Dimension(4, 40));

        JLabel lblTexto = new JLabel("<html>" + texto + "</html>");
        lblTexto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTexto.setForeground(new Color(90, 90, 100));

        JPanel derecha = new JPanel(new BorderLayout());
        derecha.setOpaque(false);
        derecha.add(lblTexto, BorderLayout.CENTER);

        item.add(lblHora, BorderLayout.WEST);
        item.add(linea, BorderLayout.CENTER);
        item.add(derecha, BorderLayout.EAST);

        return item;
    }

    private JLabel crearItemSidebarInicio(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lbl.setForeground(new Color(90, 90, 100));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
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

    private JPanel crearCardInicioSuave(String titulo, String descripcion, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 238), 1),
                new EmptyBorder(0, 0, 0, 0)
        ));

        JPanel linea = new JPanel();
        linea.setBackground(color);
        linea.setPreferredSize(new Dimension(0, 8));

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);
        contenido.setBorder(new EmptyBorder(22, 22, 22, 22));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(18, 41, 77));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea txt = new JTextArea(descripcion);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txt.setForeground(new Color(85, 85, 85));
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        txt.setEditable(false);
        txt.setOpaque(false);
        txt.setAlignmentX(Component.LEFT_ALIGNMENT);

        contenido.add(lblTitulo);
        contenido.add(Box.createVerticalStrut(12));
        contenido.add(txt);

        card.add(linea, BorderLayout.NORTH);
        card.add(contenido, BorderLayout.CENTER);

        return card;
    }

    private JPanel crearCardInicio(String titulo, String descripcion, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 230, 238)),
                new EmptyBorder(0, 0, 0, 0)
        ));

        JPanel barra = new JPanel();
        barra.setBackground(color);
        barra.setPreferredSize(new Dimension(0, 10));

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);
        contenido.setBorder(new EmptyBorder(22, 22, 22, 22));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(18, 41, 77));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea txt = new JTextArea(descripcion);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        txt.setForeground(new Color(85, 85, 85));
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        txt.setEditable(false);
        txt.setOpaque(false);
        txt.setAlignmentX(Component.LEFT_ALIGNMENT);

        contenido.add(lblTitulo);
        contenido.add(Box.createVerticalStrut(12));
        contenido.add(txt);

        card.add(barra, BorderLayout.NORTH);
        card.add(contenido, BorderLayout.CENTER);

        return card;
    }

    private JLabel crearChip(String texto, Color color) {
        JLabel lbl = new JLabel("  " + texto + "  ");
        lbl.setOpaque(true);
        lbl.setBackground(color);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setBorder(new EmptyBorder(8, 12, 8, 12));
        return lbl;
    }

    private JPanel crearTarjeta(String titulo, String descripcion) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JTextArea txt = new JTextArea(descripcion);
        txt.setWrapStyleWord(true);
        txt.setLineWrap(true);
        txt.setEditable(false);
        txt.setOpaque(false);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        card.add(lblTitulo, BorderLayout.NORTH);
        card.add(txt, BorderLayout.CENTER);

        return card;
    }

    // =========================
    // MÉTODOS ACTIVOS
    // =========================

    private JPanel crearPanelPlaceholder(String titulo, String descripcion) {
        JPanel panel = new JPanel(new GridBagLayout());

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDesc = new JLabel(descripcion);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(15));
        card.add(lblDesc);

        panel.add(card);
        return panel;
    }

    private JPanel crearPanelVisualArbol() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Visualización del Árbol AVL");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JButton btnRefrescar = new BotonEstilizado("Refrescar", BotonEstilizado.Tipo.SECUNDARIO);
        btnRefrescar.addActionListener(e -> panelArbolAVL.refrescar());

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(titulo, BorderLayout.WEST);
        top.add(btnRefrescar, BorderLayout.EAST);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        contenedor.add(panelArbolAVL, BorderLayout.CENTER);

        panel.add(top, BorderLayout.NORTH);
        panel.add(contenedor, BorderLayout.CENTER);

        return panel;
    }

  private JButton crearBotonMenu(String texto, String icono) {
    JButton boton = new JButton(texto);

    boton.setFont(new Font("Segoe UI", Font.BOLD, 15));
    boton.setFocusPainted(false);
    boton.setBorderPainted(false);
    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

    boton.setHorizontalAlignment(SwingConstants.LEFT);
    boton.setIconTextGap(12);

    // cargar icono
    try {
        ImageIcon icon = new ImageIcon(getClass().getResource(icono));
        Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(img));
    } catch (Exception e) {
        System.out.println("Icono no encontrado: " + icono);
    }

    return boton;
}

    private void mostrarPanel(String nombre) {
        if ("inicio".equals(nombre) && panelInicio != null) {
            panelInicio.refrescarVista();
        }

        if ("arbol".equals(nombre) && panelArbolAVL != null) {
            panelArbolAVL.refrescar();
        }

        if ("recorridos".equals(nombre) && panelRecorridos != null) {
            panelRecorridos.refrescar();
        }

        cardLayout.show(panelContenido, nombre);
    }

    public void irAPanel(String nombre) {
        mostrarPanel(nombre);
    }

    public void refrescarTema() {
        EstilosTema.aplicarTema(this);

        TemaVisual tema = GestorTema.getTema();

        if (panelLateral != null) {
            panelLateral.setBackground(tema.fondoMenu());
        }

        if (panelSuperior != null) {
            panelSuperior.setBackground(tema.fondoSuperior());
        }

        if (panelInicio != null) {
        panelInicio.refrescarVista();
}

        repaint();
        revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestorTema.setTema(new TemaClaro());
            new FrmPrincipal().setVisible(true);
        });
    }
}