package proyecto_arbol;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

import Modelo.ArbolAVL;

public class ServidorAVL {

    private static ArbolAVL arbol;

    public static void iniciar(ArbolAVL a) {
        arbol = a;

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

            // =========================
            // API
            // =========================

          server.createContext("/nodos", exchange -> {
    try {
        String response = "{\"nodos\":" + arbol.contarNodos() + "}";
        responder(exchange, response);
    } catch (Exception e) {
        e.printStackTrace();
    }
});

          server.createContext("/altura", exchange -> {
    try {
        String response = "{\"altura\":" + arbol.alturaArbol() + "}";
        responder(exchange, response);
    } catch (Exception e) {
        e.printStackTrace();
    }
});
            server.createContext("/dashboard", exchange -> {
    try {
        String response = "{"
                + "\"nodos\":" + arbol.contarNodos() + ","
                + "\"altura\":" + arbol.alturaArbol()
                + "}";
        responder(exchange, response);
    } catch (Exception e) {
        e.printStackTrace();
    }
});

            server.createContext("/buscar", exchange -> {
                try {
                    String query = exchange.getRequestURI().getQuery();
                    String response;

                    if (query != null && query.startsWith("id=")) {
                        String id = query.replace("id=", "");

                        try {
                            int idInt = Integer.parseInt(id);
                            Object est = arbol.buscar(idInt);

                            if (est != null) {
                                response = "{\"resultado\":\"Encontrado\"}";
                            } else {
                                response = "{\"resultado\":\"No encontrado\"}";
                            }

                        } catch (NumberFormatException ex) {
                            response = "{\"error\":\"ID inválido\"}";
                        }

                    } else {
                        response = "{\"error\":\"ID no proporcionado\"}";
                    }

                    responder(exchange, response);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // =========================
            // SERVIR FRONTEND (IA)
            // =========================

            server.createContext("/asistente", exchange -> {
                try {
                    String html = new String(
                            Files.readAllBytes(Paths.get("frontend/index.html"))
                    );

                    exchange.getResponseHeaders().add("Content-Type", "text/html");
                    exchange.sendResponseHeaders(200, html.getBytes().length);

                    OutputStream os = exchange.getResponseBody();
                    os.write(html.getBytes());
                    os.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // =========================
            // SERVIR CSS
            // =========================
            server.createContext("/style.css", exchange -> {
                try {
                    byte[] css = Files.readAllBytes(Paths.get("frontend/style.css"));

                    exchange.getResponseHeaders().add("Content-Type", "text/css");
                    exchange.sendResponseHeaders(200, css.length);

                    OutputStream os = exchange.getResponseBody();
                    os.write(css);
                    os.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            // =========================
            // SERVIR JS
            // =========================
            server.createContext("/script.js", exchange -> {
                try {
                    byte[] js = Files.readAllBytes(Paths.get("frontend/script.js"));

                    exchange.getResponseHeaders().add("Content-Type", "application/javascript");
                    exchange.sendResponseHeaders(200, js.length);

                    OutputStream os = exchange.getResponseBody();
                    os.write(js);
                    os.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            server.start();
            System.out.println("Servidor AVL en http://localhost:8080");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void responder(HttpExchange exchange, String response) throws Exception {
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        exchange.sendResponseHeaders(200, response.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}