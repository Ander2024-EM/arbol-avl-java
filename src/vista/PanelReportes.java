package vista;

import Modelo.ArbolAVL;
import Modelo.Estudiante;
import dao.EstudianteDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PanelReportes extends JPanel {

    private final ArbolAVL arbol;
    private final EstudianteDAO dao;
    private final ReporteService reporteService;

    private TarjetaResumen cardTotal;
    private TarjetaResumen cardAltura;
    private TarjetaResumen cardFecha;
    private JTextArea txtEstado;

    public PanelReportes(ArbolAVL arbol, EstudianteDAO dao) {
        this.arbol = arbol;
        this.dao = dao;
        this.reporteService = new ReporteService();

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
        cardFecha = new TarjetaResumen("Última actualización", "-", new Color(255, 140, 0));

        panel.add(cardTotal);
        panel.add(cardAltura);
        panel.add(cardFecha);

        return panel;
    }

    private JPanel crearPanelCentral() {
        JPanel principal = new JPanel(new BorderLayout(15, 15));
        principal.setOpaque(false);

        JPanel panelBotones = new JPanel(new GridLayout(2, 4, 12, 12));
        panelBotones.setOpaque(false);

        JButton btnPdfGeneral = new BotonEstilizado("PDF General", BotonEstilizado.Tipo.PELIGRO);
        JButton btnCsvGeneral = new BotonEstilizado("CSV General", BotonEstilizado.Tipo.EXITO);
        JButton btnTxtInorden = new BotonEstilizado("TXT Inorden", BotonEstilizado.Tipo.PRIMARIO);
        JButton btnTxtPreorden = new BotonEstilizado("TXT Preorden", BotonEstilizado.Tipo.PRIMARIO);
        JButton btnTxtPostorden = new BotonEstilizado("TXT Postorden", BotonEstilizado.Tipo.INFO);
        JButton btnPdfResumen = new BotonEstilizado("PDF Resumen AVL", BotonEstilizado.Tipo.ADVERTENCIA);
        JButton btnPdfIndividual = new BotonEstilizado("PDF Individual", BotonEstilizado.Tipo.SECUNDARIO);
        JButton btnActualizar = new BotonEstilizado("Actualizar", BotonEstilizado.Tipo.INFO);

        btnPdfGeneral.addActionListener(e -> generarPdfGeneral());
        btnCsvGeneral.addActionListener(e -> generarCsvGeneral());
        btnTxtInorden.addActionListener(e -> generarTxtRecorrido("inorden"));
        btnTxtPreorden.addActionListener(e -> generarTxtRecorrido("preorden"));
        btnTxtPostorden.addActionListener(e -> generarTxtRecorrido("postorden"));
        btnPdfResumen.addActionListener(e -> generarPdfResumenAVL());
        btnPdfIndividual.addActionListener(e -> generarPdfIndividual());
        btnActualizar.addActionListener(e -> actualizarResumen());

        panelBotones.add(btnPdfGeneral);
        panelBotones.add(btnCsvGeneral);
        panelBotones.add(btnTxtInorden);
        panelBotones.add(btnTxtPreorden);
        panelBotones.add(btnTxtPostorden);
        panelBotones.add(btnPdfResumen);
        panelBotones.add(btnPdfIndividual);
        panelBotones.add(btnActualizar);

        JPanel panelEstado = new JPanel(new BorderLayout());
        panelEstado.setBackground(Color.WHITE);
        panelEstado.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel titulo = new JLabel("Centro de Reportes");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(18, 41, 77));

        txtEstado = new JTextArea();
        txtEstado.setFont(new Font("Consolas", Font.PLAIN, 14));
        txtEstado.setEditable(false);
        txtEstado.setLineWrap(true);
        txtEstado.setWrapStyleWord(true);
        txtEstado.setText(
                "Aquí podrás generar reportes del sistema.\n\n" +
                "Opciones disponibles:\n" +
                "- PDF general de estudiantes\n" +
                "- CSV general\n" +
                "- TXT de recorridos AVL\n" +
                "- PDF resumen AVL\n" +
                "- PDF individual por estudiante\n"
        );

        JScrollPane scroll = new JScrollPane(txtEstado);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 225, 230)));

        panelEstado.add(titulo, BorderLayout.NORTH);
        panelEstado.add(scroll, BorderLayout.CENTER);

        principal.add(panelBotones, BorderLayout.NORTH);
        principal.add(panelEstado, BorderLayout.CENTER);

        return principal;
    }

    private void actualizarResumen() {
        cardTotal.setValor(String.valueOf(arbol.contarNodos()));
        cardAltura.setValor(String.valueOf(arbol.alturaArbol()));
        cardFecha.setValor(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

        if (txtEstado != null) {
            txtEstado.append("\nResumen actualizado correctamente.\n");
        }
    }

    private void generarPdfGeneral() {
        try {
            List<Estudiante> lista = arbol.obtenerTodosLosEstudiantes();
            String ruta = reporteService.generarPdfGeneralEstudiantes(lista);
            txtEstado.append("PDF general generado en: " + ruta + "\n");
            JOptionPane.showMessageDialog(this, "PDF general generado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar PDF general: " + ex.getMessage());
        }
    }

    private void generarCsvGeneral() {
        try {
            List<Estudiante> lista = arbol.obtenerTodosLosEstudiantes();
            String ruta = reporteService.generarCsvEstudiantes(lista);
            txtEstado.append("CSV generado en: " + ruta + "\n");
            JOptionPane.showMessageDialog(this, "CSV generado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar CSV: " + ex.getMessage());
        }
    }

    private void generarTxtRecorrido(String tipo) {
        try {
            String contenido;
            switch (tipo.toLowerCase()) {
                case "preorden":
                    contenido = arbol.obtenerPreOrdenTexto();
                    break;
                case "postorden":
                    contenido = arbol.obtenerPostOrdenTexto();
                    break;
                default:
                    contenido = arbol.obtenerInOrdenTexto();
                    break;
            }

            String ruta = reporteService.generarTxtRecorrido(tipo, contenido);
            txtEstado.append("TXT " + tipo + " generado en: " + ruta + "\n");
            JOptionPane.showMessageDialog(this, "TXT de recorrido generado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar TXT: " + ex.getMessage());
        }
    }

    private void generarPdfResumenAVL() {
        try {
            String ruta = reporteService.generarPdfResumenAVL(
                    arbol.contarNodos(),
                    arbol.alturaArbol(),
                    arbol.obtenerInOrdenTexto(),
                    arbol.obtenerPreOrdenTexto(),
                    arbol.obtenerPostOrdenTexto()
            );
            txtEstado.append("PDF resumen AVL generado en: " + ruta + "\n");
            JOptionPane.showMessageDialog(this, "PDF resumen AVL generado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar PDF resumen: " + ex.getMessage());
        }
    }

    private void generarPdfIndividual() {
        try {
            String input = JOptionPane.showInputDialog(this, "Ingresa el ID del estudiante:");
            if (input == null || input.trim().isEmpty()) {
                return;
            }

            int id = Integer.parseInt(input.trim());
            Estudiante e = arbol.buscar(id);

            if (e == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el estudiante.");
                return;
            }

            String ruta = reporteService.generarPdfEstudianteIndividual(e);
            txtEstado.append("PDF individual generado en: " + ruta + "\n");
            JOptionPane.showMessageDialog(this, "PDF individual generado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar PDF individual: " + ex.getMessage());
        }
    }
}