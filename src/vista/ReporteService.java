package vista;

import Modelo.Estudiante;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReporteService {

    private final String carpetaReportes = "reportes_generados";

    public ReporteService() {
        File carpeta = new File(carpetaReportes);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    private String fechaArchivo() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }

    public String generarPdfGeneralEstudiantes(List<Estudiante> lista) throws Exception {
        String ruta = carpetaReportes + "/estudiantes_general_" + fechaArchivo() + ".pdf";

        Document documento = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(documento, new FileOutputStream(ruta));
        documento.open();

        Font titulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font normal = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

        Paragraph p = new Paragraph("Reporte General de Estudiantes", titulo);
        p.setAlignment(Element.ALIGN_CENTER);
        documento.add(p);
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Fecha de generación: " + LocalDateTime.now(), normal));
        documento.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(7);
        tabla.setWidthPercentage(100);

        String[] encabezados = {"ID", "Nombre", "Edad", "Promedio", "Carrera", "Teléfono", "Correo"};
        for (String encabezado : encabezados) {
            PdfPCell celda = new PdfPCell(new Phrase(encabezado));
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(celda);
        }

        for (Estudiante e : lista) {
            tabla.addCell(String.valueOf(e.getId()));
            tabla.addCell(e.getNombre());
            tabla.addCell(String.valueOf(e.getEdad()));
            tabla.addCell(String.valueOf(e.getPromedio()));
            tabla.addCell(String.valueOf(e.getIdCarrera()));
            tabla.addCell(e.getTelefono());
            tabla.addCell(e.getCorreo());
        }

        documento.add(tabla);
        documento.close();

        return ruta;
    }

    public String generarCsvEstudiantes(List<Estudiante> lista) throws Exception {
        String ruta = carpetaReportes + "/estudiantes_general_" + fechaArchivo() + ".csv";

        try (FileWriter writer = new FileWriter(ruta)) {
            writer.write("ID,Nombre,Edad,Promedio,Carrera,Telefono,Correo\n");

            for (Estudiante e : lista) {
                writer.write(
                        e.getId() + "," +
                        limpiar(e.getNombre()) + "," +
                        e.getEdad() + "," +
                        e.getPromedio() + "," +
                        e.getIdCarrera() + "," +
                        limpiar(e.getTelefono()) + "," +
                        limpiar(e.getCorreo()) + "\n"
                );
            }
        }

        return ruta;
    }

    public String generarTxtRecorrido(String tipo, String contenido) throws Exception {
        String ruta = carpetaReportes + "/recorrido_" + tipo + "_" + fechaArchivo() + ".txt";

        try (FileWriter writer = new FileWriter(ruta)) {
            writer.write("RECORRIDO " + tipo.toUpperCase() + "\n\n");
            writer.write(contenido);
        }

        return ruta;
    }

    public String generarPdfResumenAVL(int total, int altura, String inorden, String preorden, String postorden) throws Exception {
        String ruta = carpetaReportes + "/resumen_avl_" + fechaArchivo() + ".pdf";

        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, new FileOutputStream(ruta));
        documento.open();

        Font titulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font subtitulo = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD);
        Font normal = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL);

        Paragraph p = new Paragraph("Resumen del Árbol AVL", titulo);
        p.setAlignment(Element.ALIGN_CENTER);
        documento.add(p);
        documento.add(new Paragraph(" "));

        documento.add(new Paragraph("Total de nodos: " + total, normal));
        documento.add(new Paragraph("Altura del árbol: " + altura, normal));
        documento.add(new Paragraph("Fecha: " + LocalDateTime.now(), normal));
        documento.add(new Paragraph(" "));

        documento.add(new Paragraph("Recorrido Inorden", subtitulo));
        documento.add(new Paragraph(inorden, normal));
        documento.add(new Paragraph(" "));

        documento.add(new Paragraph("Recorrido Preorden", subtitulo));
        documento.add(new Paragraph(preorden, normal));
        documento.add(new Paragraph(" "));

        documento.add(new Paragraph("Recorrido Postorden", subtitulo));
        documento.add(new Paragraph(postorden, normal));

        documento.close();
        return ruta;
    }

    public String generarPdfEstudianteIndividual(Estudiante e) throws Exception {
        String ruta = carpetaReportes + "/estudiante_" + e.getId() + "_" + fechaArchivo() + ".pdf";

        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, new FileOutputStream(ruta));
        documento.open();

        Font titulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font normal = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

        Paragraph p = new Paragraph("Reporte Individual del Estudiante", titulo);
        p.setAlignment(Element.ALIGN_CENTER);
        documento.add(p);
        documento.add(new Paragraph(" "));

        documento.add(new Paragraph("ID: " + e.getId(), normal));
        documento.add(new Paragraph("Nombre: " + e.getNombre(), normal));
        documento.add(new Paragraph("Edad: " + e.getEdad(), normal));
        documento.add(new Paragraph("Promedio: " + e.getPromedio(), normal));
        documento.add(new Paragraph("Carrera: " + e.getIdCarrera(), normal));
        documento.add(new Paragraph("Teléfono: " + e.getTelefono(), normal));
        documento.add(new Paragraph("Correo: " + e.getCorreo(), normal));

        documento.close();
        return ruta;
    }

    private String limpiar(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.replace(",", " ");
    }
}