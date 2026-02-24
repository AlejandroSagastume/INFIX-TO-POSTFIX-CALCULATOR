package org.postfix;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

public class PDFGenerator {

    public static class ExpresionResultado {

        private final String expresion;
        private final String resultado;
        private final boolean esError;

        public ExpresionResultado(String expresion, String resultado, boolean esError) {
            this.expresion = expresion;
            this.resultado = resultado;
            this.esError = esError;
        }

        public String getExpresion() {
            return expresion;
        }

        public String getResultado() {
            return resultado;
        }

        public boolean isError() {
            return esError;
        }
    }

    public void generarReporte(String nombreArchivo,List<ExpresionResultado> resultados) throws FileNotFoundException {

        PdfWriter writer = new PdfWriter(nombreArchivo);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Paragraph titulo = new Paragraph("REPORTE DE CALCULADORA INFIX → POSTFIX")
                .setFontSize(18)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10);

        document.add(titulo);

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        document.add(new Paragraph("Fecha: " + ahora.format(formatter))
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(10)
                .setMarginBottom(20));

        float[] columnWidths = {50f, 300f, 150f};
        Table table = new Table(columnWidths);
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell(headerCell("No."));
        table.addHeaderCell(headerCell("Expresión"));
        table.addHeaderCell(headerCell("Resultado"));

        int contador = 1;
        int exitosos = 0;
        int errores = 0;

        for (ExpresionResultado r : resultados) {
            table.addCell(new Cell()
                    .add(new Paragraph(String.valueOf(contador)))
                    .setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell()
                    .add(new Paragraph(r.getExpresion()))
                    .setFontSize(10));
            Cell resultadoCell = new Cell()
                    .add(new Paragraph(r.getResultado()))
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER);
            if (r.isError()) {
                resultadoCell.setBackgroundColor(ColorConstants.PINK);
                errores++;
            } else {
                exitosos++;
            }
            table.addCell(resultadoCell);
            contador++;
        }
        document.add(table);
        document.add(new Paragraph("\nRESUMEN")
                .setBold()
                .setFontSize(14)
                .setMarginTop(20));
        document.add(new Paragraph("Total expresiones: " + resultados.size()));
        document.add(new Paragraph("Exitosas: " + exitosos)
                .setFontColor(ColorConstants.GREEN));
        document.add(new Paragraph("Errores: " + errores)
                .setFontColor(ColorConstants.RED));

        document.close();

        System.out.println("PDF generado correctamente: " + nombreArchivo);
    }


    private Cell headerCell(String text) {
        return new Cell()
                .add(new Paragraph(text).setBold())
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setTextAlignment(TextAlignment.CENTER);
    }
}