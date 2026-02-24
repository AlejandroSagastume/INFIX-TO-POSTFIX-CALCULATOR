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

    // class to hold expression results
    public static class ExpresionResultado {
        private String expresion;
        private String resultado;
        private boolean esError;
        
        // Constructor
        public ExpresionResultado(String expresion, String resultado, boolean esError) {
            this.expresion = expresion;
            this.resultado = resultado;
            this.esError = esError;
        }
        
        // Getters
        public String getExpresion() { return expresion; }
        public String getResultado() { return resultado; }
        public boolean isError() { return esError; }
    }
    
/**
* Generates a PDF report of postfix expression results.
* @param    nombreArchivo The name of the output PDF file.
* @param    resultados List of expression results to include in the report.
* @return   void
* @throws   FileNotFoundException If the file cannot be created or written to
* @throws   Exception For other errors during PDF generation
*/
    public void generarReporte(String nombreArchivo, List<ExpresionResultado> resultados)
            throws FileNotFoundException {
        
        try {
            // Create the PDF writer and document
            PdfWriter writer = new PdfWriter(nombreArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            
            // Add the main title
            Paragraph titulo = new Paragraph("REPORTE DE CALCULADORA POSTFIX")
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(10);
            document.add(titulo);
            
            // Add generation date and time
            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            Paragraph fecha = new Paragraph("Fecha de generación: " + ahora.format(formatter))
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
            document.add(fecha);
            
            
            // Add course and university information
            document.add(new Paragraph("Universidad del Valle de Guatemala")
                .setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("CC2003 - Sección 20 - Algoritmos y Estructura de Datos")
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20));
            
            // Create table with 3 columns (No., Expression, Result)
            float[] columnWidths = {50f, 250f, 150f};
            Table tabla = new Table(columnWidths);
            tabla.setWidth(UnitValue.createPercentValue(100));
            
            // Add table headers
            tabla.addHeaderCell(new Cell().add(new Paragraph("No.").setBold())
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setTextAlignment(TextAlignment.CENTER));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Expresión").setBold())
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setTextAlignment(TextAlignment.CENTER));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Resultado").setBold())
                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setTextAlignment(TextAlignment.CENTER));
            
            // Populate the table with expression results
            int contador = 1;
            int exitosos = 0;
            int errores = 0;
            
            for (ExpresionResultado resultado : resultados) {
                // Column 1: Line number
                tabla.addCell(new Cell().add(new Paragraph(String.valueOf(contador))).setTextAlignment(TextAlignment.CENTER));
                
                // Column 2: Postfix Expression
                tabla.addCell(new Cell().add(new Paragraph(resultado.getExpresion()))
                    .setFontSize(10));
                
                // Column 3: Result or error message
                Cell celdaResultado = new Cell().add(new Paragraph(resultado.getResultado()))
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER);
                
                // If error, color the cell pink
                if (resultado.isError()) {
                    celdaResultado.setBackgroundColor(ColorConstants.PINK);
                    errores++;
                } else {
                    exitosos++;
                }
                
                tabla.addCell(celdaResultado);
                contador++;
            }
            
            // Add the table to the document
            document.add(tabla);
            
            // Add statistical summary
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("RESUMEN")
                .setBold()
                .setFontSize(14)
                .setMarginTop(20));
            
            document.add(new Paragraph("Total de expresiones evaluadas: " + resultados.size())
                .setFontSize(11));
            document.add(new Paragraph("Expresiones exitosas: " + exitosos)
                .setFontSize(11)
                .setFontColor(ColorConstants.GREEN));
            document.add(new Paragraph("Expresiones con error: " + errores)
                .setFontSize(11)
                .setFontColor(ColorConstants.RED));
            
            // Add author information
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("Autores: Alejandro Sagastume - Jimena Vásquez")
                .setFontSize(9)
                .setTextAlignment(TextAlignment.CENTER)
                .setItalic());
            
            // close the document
            document.close();
            
            System.out.println("✓ PDF generado exitosamente: " + nombreArchivo);
            
        } catch (Exception e) {
            System.err.println("Error al generar PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
}