package vista;

import Modelo.ArbolAVL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import javax.swing.border.Border;

public class PanelAsistente extends JPanel {

    private final ArbolAVL arbol;

    private JLabel imagenModulo;
    private JLabel avatar;
    private JLabel estado;
    private JLabel tituloModulo;
    private JTextArea textoModulo;
    private JTextPane chat;
    private JTextField input;

    private boolean bienvenidaDada = false;
    private boolean dashboardIniciado = false;
    private boolean explicandoTodo = false;

    private String chatHtml = "";
    private String ultimoTema = "";

    private final Random random = new Random();

    private final Color FONDO = new Color(245, 247, 251);
    private final Color AZUL = new Color(18, 41, 77);
    private final Color CYAN = new Color(6, 182, 212);
    private final Color VERDE = new Color(22, 163, 74);
    private final Color GRIS = new Color(108, 117, 125);
    private final Color CHAT = new Color(2, 6, 23);
    private final Color BLANCO_CARD = Color.WHITE;

    public PanelAsistente(ArbolAVL arbol) {
        this.arbol = arbol;

        setLayout(new BorderLayout());
        setBackground(FONDO);
        setBorder(new EmptyBorder(20, 35, 20, 35));

        add(crearHeader(), BorderLayout.NORTH);
        add(crearCentro(), BorderLayout.CENTER);
    }

    public void activarAsistente() {
        if (!bienvenidaDada) {
            bienvenidaDada = true;

            String msg = "Hola, bienvenido al asistente inteligente del sistema AVL. Puedes seleccionar un módulo, usar el botón explicar todo, escribir una pregunta o hablarme con el micrófono.";

            escribirConEfecto("IA", msg);
            hablar(msg);
            animarAvatar();
            iniciarDashboard();
        }
    }

    private JPanel crearHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(0, 0, 20, 0));

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Asistente Inteligente del Sistema AVL");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(AZUL);

        JLabel subtitulo = new JLabel("Guía interactiva con voz, imágenes, preguntas y datos en tiempo real.");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitulo.setForeground(AZUL);

        textos.add(titulo);
        textos.add(Box.createVerticalStrut(8));
        textos.add(subtitulo);

        estado = new JLabel("Listo", SwingConstants.CENTER);
        estado.setOpaque(true);
        estado.setBackground(VERDE);
        estado.setForeground(Color.WHITE);
        estado.setFont(new Font("Segoe UI", Font.BOLD, 14));
        estado.setPreferredSize(new Dimension(120, 45));
        estado.setBorder(new EmptyBorder(8, 12, 8, 12));

        header.add(textos, BorderLayout.WEST);
        header.add(estado, BorderLayout.EAST);

        return header;
    }

    private JPanel crearCentro() {
        JPanel centro = new JPanel(new GridLayout(1, 2, 30, 0));
        centro.setOpaque(false);

        centro.add(crearPanelIzquierdo());
        centro.add(crearPanelDerecho());

        return centro;
    }

    private JPanel crearPanelIzquierdo() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setOpaque(false);

        avatar = new JLabel("🤖", SwingConstants.CENTER);
        avatar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 56));
        avatar.setForeground(AZUL);

        JLabel instruccion = new JLabel("Selecciona un módulo o haz una pregunta.", SwingConstants.CENTER);
        instruccion.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        instruccion.setForeground(AZUL);

        JPanel avatarPanel = new JPanel(new BorderLayout());
        avatarPanel.setOpaque(false);
        avatarPanel.add(avatar, BorderLayout.CENTER);
        avatarPanel.add(instruccion, BorderLayout.SOUTH);

       imagenModulo = new JLabel();
imagenModulo.setHorizontalAlignment(SwingConstants.CENTER);
imagenModulo.setVerticalAlignment(SwingConstants.CENTER);

imagenModulo.setPreferredSize(new Dimension(520, 220));
imagenModulo.setMaximumSize(new Dimension(650, 240));


imagenModulo.setOpaque(true);
imagenModulo.setBackground(Color.WHITE);

imagenModulo.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(220, 225, 230), 1, true), 
        new EmptyBorder(12, 12, 12, 12) 
));


imagenModulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        cargarImagen("inicio.png");

        JPanel textoCard = new JPanel(new BorderLayout(0, 8));
        textoCard.setBackground(BLANCO_CARD);
        textoCard.setPreferredSize(new Dimension(520, 130));
        textoCard.setMaximumSize(new Dimension(650, 140));
        textoCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 5, 0, 0, CYAN),
                new EmptyBorder(16, 20, 16, 20)
        ));

        tituloModulo = new JLabel("Sistema AVL");
        tituloModulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        tituloModulo.setForeground(AZUL);

        textoModulo = new JTextArea("Bienvenido. Este asistente te ayudará a explicar el funcionamiento completo del sistema de gestión de estudiantes con Árbol AVL.");
        textoModulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textoModulo.setForeground(AZUL);
        textoModulo.setLineWrap(true);
        textoModulo.setWrapStyleWord(true);
        textoModulo.setEditable(false);
        textoModulo.setOpaque(false);

        textoCard.add(tituloModulo, BorderLayout.NORTH);
        textoCard.add(textoModulo, BorderLayout.CENTER);

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);

        imagenModulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        textoCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        contenido.add(imagenModulo);
        contenido.add(Box.createVerticalStrut(15));
        contenido.add(textoCard);
        contenido.add(Box.createVerticalStrut(12));
        contenido.add(crearBotonesModulos());

        panel.add(avatarPanel, BorderLayout.NORTH);
        panel.add(contenido, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearBotonesModulos() {
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setOpaque(false);
        contenedor.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel fila1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        fila1.setOpaque(false);

        fila1.add(boton("Inicio", AZUL, e -> explicarModulo("inicio")));
        fila1.add(boton("Estudiantes", AZUL, e -> explicarModulo("estudiantes")));
        fila1.add(boton("Árbol AVL", AZUL, e -> explicarModulo("arbol")));
        fila1.add(boton("Recorridos", AZUL, e -> explicarModulo("recorridos")));
        fila1.add(boton("Reportes", AZUL, e -> explicarModulo("reportes")));

        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        fila2.setOpaque(false);

        fila2.add(boton("Configuración", AZUL, e -> explicarModulo("configuracion")));
        fila2.add(boton("🎬 Explicar todo", VERDE, e -> explicarTodo()));

        contenedor.add(fila1);
        contenedor.add(fila2);

        return contenedor;
    }

    private JPanel crearPanelDerecho() {
        JPanel panel = new JPanel(new BorderLayout(0, 15));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(70, 0, 25, 0));

        JLabel titulo = new JLabel("Pregúntale al asistente", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 25));
        titulo.setForeground(AZUL);

        JPanel entrada = new JPanel(new BorderLayout(8, 0));
        entrada.setOpaque(false);

        input = new JTextField();
        input.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        input.setBorder(new EmptyBorder(10, 12, 10, 12));

        JButton btnPreguntar = boton("Preguntar", GRIS, this::procesarPregunta);
        JButton btnHablar = boton("Hablar", GRIS, e -> escucharUsuario());

        entrada.add(input, BorderLayout.CENTER);
        entrada.add(btnPreguntar, BorderLayout.EAST);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        acciones.setOpaque(false);
        acciones.add(btnHablar);

        JPanel sugerencias = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        sugerencias.setOpaque(false);
        sugerencias.add(boton("Total estudiantes", GRIS, e -> preguntaRapida("cuantos estudiantes hay")));
        sugerencias.add(boton("Altura", GRIS, e -> preguntaRapida("cual es la altura")));
        sugerencias.add(boton("Qué es AVL", GRIS, e -> preguntaRapida("que es avl")));
        sugerencias.add(boton("Buscar ID 1", GRIS, e -> preguntaRapida("buscar 1")));

        chat = new JTextPane();
        chat.setContentType("text/html");
        chat.setEditable(false);
        chat.setBackground(CHAT);
        chat.setBorder(new EmptyBorder(10, 10, 10, 10));
        limpiarChat();

        JScrollPane scroll = new JScrollPane(chat);
        scroll.setPreferredSize(new Dimension(520, 350));
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 41, 59), 2),
                new EmptyBorder(0, 0, 0, 0)
        ));
        scroll.getViewport().setBackground(CHAT);

        JButton limpiar = boton("Limpiar chat", GRIS, e -> limpiarChat());

        JPanel arriba = new JPanel();
        arriba.setOpaque(false);
        arriba.setLayout(new BoxLayout(arriba, BoxLayout.Y_AXIS));
        arriba.add(titulo);
        arriba.add(Box.createVerticalStrut(15));
        arriba.add(entrada);
        arriba.add(Box.createVerticalStrut(10));
        arriba.add(acciones);
        arriba.add(Box.createVerticalStrut(10));
        arriba.add(sugerencias);

        panel.add(arriba, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(limpiar, BorderLayout.SOUTH);

        input.addActionListener(this::procesarPregunta);

        return panel;
    }

private JButton boton(String texto, final Color color, java.awt.event.ActionListener evento) {

    JButton b = new JButton(texto) {

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      
            g2.setColor(new Color(0, 0, 0, 40));
            g2.fillRoundRect(3, 3, getWidth() - 6, getHeight() - 4, 20, 20);

            
            if (getModel().isRollover()) {
                g2.setColor(color.brighter());
            } else {
                g2.setColor(color);
            }

            g2.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, 20, 20);

            g2.dispose();
            super.paintComponent(g);
        }
    };


    b.setFocusPainted(false);
    b.setBorderPainted(false);
    b.setContentAreaFilled(false);
    b.setOpaque(false);
    b.setCursor(new Cursor(Cursor.HAND_CURSOR));


    b.setForeground(Color.WHITE);
    b.setFont(new Font("Segoe UI", Font.BOLD, 13));

 
    b.setBorder(new EmptyBorder(12, 22, 12, 22));


    b.addActionListener(evento);

    return b;
}

    private void explicarModulo(String modulo) {
        InfoModulo info = obtenerInfoModulo(modulo);

        ultimoTema = modulo;

        tituloModulo.setText(info.titulo);
        textoModulo.setText(info.texto);
        cargarImagen(info.imagen);

        agregarMensaje("IA", info.texto);
        hablar(info.texto);
        animarAvatar();
    }

    private void explicarTodo() {
        if (explicandoTodo) {
            escribirConEfecto("IA", frase("Ya estoy haciendo el recorrido completo. Espera un momento y termino la explicación.", "Dame un momento, ya estoy explicando el sistema completo.", "Estoy en pleno recorrido. Cuando termine puedes pedirme otra explicación."));
            return;
        }

        explicandoTodo = true;

        new Thread(() -> {
            String[] modulos = {
                    "inicio", "estudiantes", "arbol",
                    "recorridos", "reportes", "configuracion"
            };

            SwingUtilities.invokeLater(() -> {
                String intro = "Perfecto, voy a explicarte todo el sistema paso a paso, empezando por el inicio y terminando con la configuración.";
                agregarMensaje("IA", intro);
                animarAvatar();
            });

            hablarBloqueante("Perfecto, voy a explicarte todo el sistema paso a paso.");

            for (String m : modulos) {
                InfoModulo info = obtenerInfoModulo(m);

                SwingUtilities.invokeLater(() -> {
                    ultimoTema = m;
                    tituloModulo.setText(info.titulo);
                    textoModulo.setText(info.texto);
                    cargarImagen(info.imagen);
                    agregarMensaje("IA", info.texto);
                    animarAvatar();
                });

                hablarBloqueante(info.texto);

                try {
                    Thread.sleep(900);
                } catch (InterruptedException ignored) {}
            }

            SwingUtilities.invokeLater(() -> {
                explicandoTodo = false;
                estado.setText("Listo");
                estado.setBackground(VERDE);
                String cierre = "Recorrido completo finalizado. Ya conoces los módulos principales del sistema AVL.";
                escribirConEfecto("IA", cierre);
            });

            hablar("Recorrido completo finalizado. Ya conoces los módulos principales del sistema AVL.");

        }).start();
    }

    private void procesarPregunta(ActionEvent e) {
        String pregunta = input.getText().trim();
        if (pregunta.isEmpty()) return;

        agregarMensaje("Tú", pregunta);
        input.setText("");

        String respuesta = responder(pregunta.toLowerCase());
        escribirConEfecto("IA", respuesta);
        hablar(respuesta);
        animarAvatar();
    }

    private void preguntaRapida(String pregunta) {
        input.setText(pregunta);
        procesarPregunta(null);
    }

    private String responder(String pregunta) {

        if (pregunta.contains("hola") || pregunta.contains("buenas") || pregunta.contains("qué tal") || pregunta.contains("que tal")) {
            return frase(
                    "¡Hola! Me alegra ayudarte. Puedes preguntarme sobre el árbol AVL, estudiantes, reportes, recorridos o usar el botón explicar todo.",
                    "Hola, claro que sí. Estoy listo para ayudarte con el sistema AVL.",
                    "¡Buenas! Pregúntame lo que necesites del sistema y te lo explico paso a paso."
            );
        }

        if (pregunta.contains("gracias") || pregunta.contains("muchas gracias")) {
            return frase(
                    "Con gusto. Me alegra que te esté sirviendo.",
                    "De nada, para eso estoy. Cuando quieras seguimos revisando el sistema.",
                    "Con mucho gusto. Puedes preguntarme otra cosa si quieres."
            );
        }

        if (pregunta.contains("no entendi") || pregunta.contains("no entendí") || pregunta.contains("explica mejor") || pregunta.contains("explicame mejor") || pregunta.contains("explícame mejor")) {
            return explicarMas();
        }

        if (pregunta.contains("explica mas") || pregunta.contains("explica más") || pregunta.contains("amplia") || pregunta.contains("amplía")) {
            return explicarMas();
        }

        if (pregunta.contains("ejemplo") || pregunta.contains("dame un ejemplo")) {
            return ejemploDelTema();
        }

        if (pregunta.contains("explica todo") || pregunta.contains("explicar todo") || pregunta.contains("todo el sistema")) {
            SwingUtilities.invokeLater(this::explicarTodo);
            return "Claro, iniciaré el recorrido completo explicando todos los módulos del sistema.";
        }

        if (pregunta.contains("buscar") || pregunta.contains("busca") || pregunta.contains("existe")) {
            ultimoTema = "buscar";

            String numero = pregunta.replaceAll("[^0-9]", "");

            if (numero.isEmpty()) {
                return "Claro, puedo buscar estudiantes. Indícame el ID. Por ejemplo: buscar estudiante 5.";
            }

            try {
                int id = Integer.parseInt(numero);
                Object est = arbol.buscar(id);

                if (est != null) {
                    return frase(
                            "Sí, el estudiante con ID " + id + " fue encontrado dentro del árbol AVL.",
                            "Encontré el estudiante con ID " + id + ". Eso significa que sí está cargado en la estructura AVL.",
                            "Correcto, el ID " + id + " existe dentro del árbol."
                    );
                } else {
                    return frase(
                            "No encontré ningún estudiante con ID " + id + " dentro del árbol AVL.",
                            "Revisé el árbol y no aparece un estudiante con ID " + id + ".",
                            "El ID " + id + " no está registrado actualmente en la estructura."
                    );
                }

            } catch (Exception ex) {
                return "No pude interpretar correctamente el ID ingresado. Prueba escribiendo algo como: buscar estudiante 1.";
            }
        }

        if (pregunta.contains("altura") || pregunta.contains("niveles") || pregunta.contains("profundidad")) {
            ultimoTema = "altura";
            return frase(
                    "La altura actual del árbol AVL es " + arbol.alturaArbol() + ". Esto representa los niveles que tiene la estructura del árbol.",
                    "En este momento, el árbol tiene una altura de " + arbol.alturaArbol() + ". Mientras menor sea la altura, más eficiente suele ser la búsqueda.",
                    "La altura del AVL es " + arbol.alturaArbol() + ". Ese dato ayuda a entender qué tan balanceada está la estructura."
            );
        }

        if (pregunta.contains("cuantos") || pregunta.contains("cuántos") || pregunta.contains("total") || pregunta.contains("cantidad") || pregunta.contains("estudiantes") || pregunta.contains("nodos")) {
            ultimoTema = "estudiantes";
            return frase(
                    "Actualmente hay " + arbol.contarNodos() + " estudiantes registrados en el árbol AVL.",
                    "En este momento el sistema tiene " + arbol.contarNodos() + " estudiantes cargados.",
                    "El árbol contiene " + arbol.contarNodos() + " nodos, y cada nodo representa un estudiante."
            );
        }

        if (pregunta.contains("inorden")) {
            ultimoTema = "inorden";
            return "El recorrido inorden visita primero el subárbol izquierdo, luego la raíz y finalmente el subárbol derecho. En un árbol de búsqueda muestra los datos ordenados.";
        }

        if (pregunta.contains("preorden")) {
            ultimoTema = "preorden";
            return "El recorrido preorden visita primero la raíz, luego el subárbol izquierdo y después el subárbol derecho. Sirve para mostrar la estructura comenzando desde el nodo principal.";
        }

        if (pregunta.contains("postorden")) {
            ultimoTema = "postorden";
            return "El recorrido postorden visita primero los nodos hijos y al final la raíz. Es útil cuando se necesita procesar primero las partes internas antes del nodo principal.";
        }

        if (pregunta.contains("recorrido") || pregunta.contains("recorridos")) {
            ultimoTema = "recorridos";
            return "El sistema permite ver tres recorridos principales: inorden, preorden y postorden. Cada uno muestra los nodos del árbol en un orden diferente.";
        }

        if (pregunta.contains("avl") || pregunta.contains("arbol") || pregunta.contains("árbol")) {
            ultimoTema = "avl";
            return frase(
                    "Un árbol AVL es una estructura de datos balanceada. Mantiene el equilibrio automáticamente para permitir búsquedas, inserciones y eliminaciones eficientes.",
                    "El árbol AVL organiza los datos de forma balanceada. Eso ayuda a que la búsqueda de estudiantes sea más rápida.",
                    "AVL significa que el árbol se ajusta automáticamente para no quedar demasiado cargado hacia un lado."
            );
        }

        if (pregunta.contains("reporte") || pregunta.contains("pdf") || pregunta.contains("csv") || pregunta.contains("txt")) {
            ultimoTema = "reportes";
            return "El módulo de reportes permite generar archivos en PDF, CSV y TXT para respaldar, exportar y presentar información del sistema.";
        }

        if (pregunta.contains("configuracion") || pregunta.contains("configuración") || pregunta.contains("tema")) {
            ultimoTema = "configuracion";
            return "El módulo de configuración permite cambiar el nombre del sistema, la institución, la ruta de reportes, el tema visual y opciones del árbol AVL.";
        }

        if (pregunta.contains("ayuda")) {
            return "Puedes usar los botones de módulos, el botón explicar todo, escribir preguntas o presionar hablar para dictar una pregunta.";
        }

        if (pregunta.contains("ok") || pregunta.contains("va") || pregunta.contains("bueno")) {
            return "Perfecto. Seguimos cuando quieras. También puedes pedirme un ejemplo o decirme: explícame más.";
        }

        return frase(
                "Puedo ayudarte con preguntas sobre estudiantes, altura del árbol, recorridos, reportes, configuración o el funcionamiento general del AVL.",
                "No entendí del todo la pregunta, pero puedo explicarte módulos, buscar estudiantes, mostrar la altura o darte ejemplos.",
                "Intenta preguntarme algo como: cuántos estudiantes hay, buscar estudiante 1, qué es AVL, dame un ejemplo o explícame más."
        );
    }

    private InfoModulo obtenerInfoModulo(String modulo) {
        switch (modulo) {
            case "inicio":
                return new InfoModulo(
                        "Módulo de Inicio",
                        "inicio.png",
                        "Este es el panel principal del sistema. Desde aquí puedes observar un resumen general, la cantidad de estudiantes registrados, la altura actual del árbol AVL y accesos rápidos hacia los módulos principales."
                );

            case "estudiantes":
                return new InfoModulo(
                        "Módulo de Estudiantes",
                        "estudiantes.png",
                        "En este módulo se administra la información de los estudiantes. Permite registrar, buscar, modificar, eliminar y limpiar datos. Cada estudiante se integra al árbol AVL para mantener una organización eficiente."
                );

            case "arbol":
                return new InfoModulo(
                        "Visualización del Árbol AVL",
                        "arbol.png",
                        "Aquí se visualiza gráficamente la estructura del árbol AVL. Cada nodo representa un estudiante y el árbol se balancea automáticamente para mejorar las búsquedas."
                );

            case "recorridos":
                return new InfoModulo(
                        "Módulo de Recorridos",
                        "recorridos.png",
                        "Este módulo permite visualizar los recorridos del árbol AVL: inorden, preorden y postorden. Cada recorrido muestra los nodos en un orden diferente."
                );

            case "reportes":
                return new InfoModulo(
                        "Módulo de Reportes",
                        "reportes.png",
                        "Aquí puedes generar reportes en PDF, CSV y TXT. Sirve para respaldar, exportar y presentar la información registrada en el sistema."
                );

            case "configuracion":
                return new InfoModulo(
                        "Módulo de Configuración",
                        "configuracion.png",
                        "En configuración puedes modificar datos generales del sistema, institución, ruta de reportes, tema visual y opciones de visualización del árbol AVL."
                );

            default:
                return new InfoModulo(
                        "Sistema AVL",
                        "inicio.png",
                        "Asistente del sistema AVL."
                );
        }
    }

    private String explicarMas() {
        switch (ultimoTema) {
            case "altura":
                return "Claro. La altura del árbol indica cuántos niveles tiene desde la raíz hasta el nodo más profundo. En un AVL se busca que esa altura no crezca demasiado, porque eso permite encontrar estudiantes de manera más rápida.";

            case "estudiantes":
                return "En este sistema cada estudiante se guarda como un nodo dentro del árbol. Al usar un AVL, los registros no solo se almacenan, sino que quedan organizados para que la búsqueda sea más eficiente.";

            case "avl":
                return "Te lo explico más simple: imagina una lista de estudiantes, pero organizada como ramas. El AVL evita que todas las ramas se carguen hacia un solo lado, manteniendo el árbol equilibrado.";

            case "recorridos":
                return "Los recorridos son formas distintas de leer el árbol. Inorden muestra los datos ordenados, preorden empieza por la raíz y postorden deja la raíz al final.";

            case "reportes":
                return "Los reportes sirven para exportar información del sistema. Por ejemplo, puedes generar un PDF para presentar datos o un CSV para abrirlo en Excel.";

            case "configuracion":
                return "La configuración permite ajustar datos generales del sistema, como nombre, institución, rutas y opciones visuales. Es útil para personalizar el proyecto sin tocar el código principal.";

            case "buscar":
                return "Cuando buscas un estudiante por ID, el árbol AVL compara el valor con los nodos y decide si ir a la izquierda o derecha. Como está balanceado, la búsqueda suele ser rápida.";

            default:
                return "Claro. Este sistema combina una interfaz gráfica con un árbol AVL para administrar estudiantes de forma ordenada, visual y eficiente.";
        }
    }

    private String ejemploDelTema() {
        switch (ultimoTema) {
            case "altura":
                return "Ejemplo: si el árbol tiene altura 3, significa que desde la raíz hasta el nodo más profundo hay varios niveles. Mientras más balanceado esté, más rápida será la búsqueda.";

            case "estudiantes":
                return "Ejemplo: si registras a un estudiante con ID 10, ese registro se convierte en un nodo del árbol. Luego puedes buscarlo usando ese ID.";

            case "avl":
                return "Ejemplo: si insertas los ID 10, 20 y 30, un árbol normal podría inclinarse hacia la derecha. Pero un AVL se rota automáticamente para quedar balanceado.";

            case "recorridos":
                return "Ejemplo: en inorden, si tienes los ID 5, 10 y 15, el recorrido los mostraría ordenados de menor a mayor.";

            case "reportes":
                return "Ejemplo: puedes generar un reporte PDF con los estudiantes registrados para entregarlo o imprimirlo.";

            case "configuracion":
                return "Ejemplo: puedes cambiar el nombre del sistema, la institución o la carpeta donde se guardan los reportes.";

            default:
                return "Ejemplo general: registras estudiantes, el sistema los guarda en el árbol AVL y luego puedes buscarlos, visualizarlos o generar reportes.";
        }
    }

    private String frase(String... opciones) {
        if (opciones == null || opciones.length == 0) return "";
        return opciones[random.nextInt(opciones.length)];
    }

    private void cargarImagen(String nombre) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/vista/img/" + nombre));

            int ancho = imagenModulo.getWidth() > 0 ? imagenModulo.getWidth() - 18 : 520;
            int alto = imagenModulo.getHeight() > 0 ? imagenModulo.getHeight() - 18 : 245;

            Image img = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            imagenModulo.setIcon(new ImageIcon(img));
            imagenModulo.setText("");

        } catch (Exception e) {
            imagenModulo.setIcon(null);
            imagenModulo.setText("No se encontró la imagen: " + nombre);
            imagenModulo.setForeground(AZUL);
        }
    }

    private void agregarMensaje(String autor, String mensaje) {
        String fondo = autor.equals("Tú") ? "#06b6d4" : "#1e293b";
        String borde = autor.equals("Tú") ? "#0891b2" : "#22d3ee";

  chatHtml += "<div style='margin:14px;padding:14px;border-radius:18px;"
        + "background:" + fondo + ";color:white;"
        + "box-shadow:0 4px 12px rgba(0,0,0,0.15);"
        + "border-left:6px solid " + borde + ";"
        + "line-height:1.6;'>"
        + "<b>" + escaparHtml(autor) + ":</b> "
        + escaparHtml(mensaje)
        + "</div>";
        refrescarChat();
    }

    private void escribirConEfecto(String autor, String mensaje) {
        new Thread(() -> {
            StringBuilder parcial = new StringBuilder();

            for (char c : mensaje.toCharArray()) {
                parcial.append(c);

                SwingUtilities.invokeLater(() -> {
                    String fondo = autor.equals("Tú") ? "#06b6d4" : "#1e293b";
                    String borde = autor.equals("Tú") ? "#0891b2" : "#22d3ee";

                    String temporal = chatHtml
                            + "<div style='margin:12px;padding:12px;border-radius:12px;background:" + fondo + ";color:white;border-left:5px solid " + borde + ";line-height:1.5;'>"
                            + "<b>" + escaparHtml(autor) + ":</b> " + escaparHtml(parcial.toString())
                            + "</div>";

                    setChatBody(temporal);
                });

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
            }

            SwingUtilities.invokeLater(() -> agregarMensaje(autor, mensaje));
        }).start();
    }

    private void refrescarChat() {
        setChatBody(chatHtml);
    }

    private void setChatBody(String contenido) {
        chat.setText("<html><body style='background:#020617;color:white;font-family:Segoe UI;font-size:14px;padding:10px;line-height:1.5;'>"
                + contenido
                + "</body></html>");

        chat.setCaretPosition(chat.getDocument().getLength());
        chat.revalidate();
        chat.repaint();
    }

    private void limpiarChat() {
        chatHtml = "";
        setChatBody("");
    }

    private String escaparHtml(String texto) {
        if (texto == null) return "";
        return texto.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "");
    }

    private void animarAvatar() {
        if (avatar == null) return;

        avatar.setText("🟢");
        estado.setText("Hablando");
        estado.setBackground(CYAN);

        Timer timer = new Timer(900, e -> {
            avatar.setText("🤖");
            estado.setText("Listo");
            estado.setBackground(VERDE);
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void iniciarDashboard() {
        if (dashboardIniciado) return;

        dashboardIniciado = true;

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException ignored) {
                }

                int total = arbol.contarNodos();

                SwingUtilities.invokeLater(() -> {
                    if (!"Hablando".equals(estado.getText()) && !"Escuchando".equals(estado.getText())) {
                        estado.setText("AVL OK (" + total + ")");
                        estado.setBackground(VERDE);
                    }
                });
            }
        }).start();
    }

    private void hablar(String texto) {
        final String limpio = texto
                .replace("'", "")
                .replace("\"", "")
                .replace("\n", " ");

        new Thread(() -> ejecutarVoz(limpio, false)).start();
    }

    private void hablarBloqueante(String texto) {
        final String limpio = texto
                .replace("'", "")
                .replace("\"", "")
                .replace("\n", " ");

        ejecutarVoz(limpio, true);
    }

    private void ejecutarVoz(String texto, boolean esperar) {
        try {
            String comando =
                    "Add-Type -AssemblyName System.Speech; "
                            + "$voz = New-Object System.Speech.Synthesis.SpeechSynthesizer; "
                            + "$voz.Rate = -1; "
                            + "$voz.Volume = 100; "
                            + "$voz.Speak('" + texto + "');";

            Process p = new ProcessBuilder("powershell", "-Command", comando).start();

            if (esperar) {
                p.waitFor();
            }

        } catch (Exception e) {
            System.out.println("Error voz: " + e.getMessage());
        }
    }

    private void escucharUsuario() {
        escribirConEfecto("IA", "Escuchando... habla ahora.");
        estado.setText("Escuchando");
        estado.setBackground(GRIS);
        avatar.setText("🎤");

        new Thread(() -> {
            String resultado = "";

            try {
                String comando =
                        "$ErrorActionPreference = 'SilentlyContinue'; "
                                + "Add-Type -AssemblyName System.Speech; "
                                + "$culture = [System.Globalization.CultureInfo]::GetCultureInfo('es-ES'); "
                                + "try { $rec = New-Object System.Speech.Recognition.SpeechRecognitionEngine($culture) } "
                                + "catch { $rec = New-Object System.Speech.Recognition.SpeechRecognitionEngine }; "
                                + "$rec.SetInputToDefaultAudioDevice(); "
                                + "$grammar = New-Object System.Speech.Recognition.DictationGrammar; "
                                + "$rec.LoadGrammar($grammar); "
                                + "$r = $rec.Recognize([TimeSpan]::FromSeconds(7)); "
                                + "if ($r -ne $null) { Write-Output $r.Text }";

                ProcessBuilder pb = new ProcessBuilder("powershell", "-Command", comando);
                pb.redirectErrorStream(true);

                Process proceso = pb.start();

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(proceso.getInputStream(), StandardCharsets.UTF_8)
                );

                String linea;
                StringBuilder salida = new StringBuilder();

                while ((linea = br.readLine()) != null) {
                    salida.append(linea).append(" ");
                }

                resultado = salida.toString().trim();

            } catch (Exception e) {
                resultado = "";
            }

            final String textoReconocido = resultado;

            SwingUtilities.invokeLater(() -> {
                avatar.setText("🤖");
                estado.setText("Listo");
                estado.setBackground(VERDE);

                if (textoReconocido.isEmpty()) {
                    String msg = "No logré escuchar correctamente. Puedes intentar de nuevo o escribir la pregunta.";
                    escribirConEfecto("IA", msg);
                    hablar(msg);
                } else {
                    input.setText(textoReconocido);
                    procesarPregunta(null);
                }
            });

        }).start();
    }

    private static class InfoModulo {
        String titulo;
        String imagen;
        String texto;

        InfoModulo(String titulo, String imagen, String texto) {
            this.titulo = titulo;
            this.imagen = imagen;
            this.texto = texto;
        }
    }
    // =========================
// ESTILO MODERNO
// =========================

private Border cardModerna() {
    return BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 225, 230), 1),
            new EmptyBorder(18, 18, 18, 18)
    );
}

private JButton botonModerno(String texto, Color color, java.awt.event.ActionListener evento) {
    JButton b = new JButton(texto);
    b.setFocusPainted(false);
    b.setBorderPainted(false);
    b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    b.setForeground(Color.WHITE);
    b.setFont(new Font("Segoe UI", Font.BOLD, 13));
    b.setBackground(color);
    b.setBorder(new EmptyBorder(12, 20, 12, 20));
    b.addActionListener(evento);
    return b;
}
}