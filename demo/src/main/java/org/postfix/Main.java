/**
*
* Main - Entry point for the Postfix Calculator application
* 
* @author Jimena Vásquez, Alejandro Sagastume
* @version 1.0
*/

package org.postfix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Stacks.setDefaultType(Stacks.StackType.VECTOR); // Set default stack implementation here, can be VECTOR or ARRAYLIST

        System.out.println("=== INICIANDO CALCULADORA POSTFIX ===\n");
        System.out.println("Implementación seleccionada: " + Stacks.getDefaultType() + "\n");

        // Create an instance of the PostfixCalculator
        Calc calculadora = new PostfixCalculator();
        
        // Create the PDF generator
        PDFGenerator pdfGenerator = new PDFGenerator();
        
        // List to store all results (successes and errors)
        List<PDFGenerator.ExpresionResultado> resultados = new ArrayList<>();
        
        // Name of the input file with the expressions
        String nombreArchivo = "src/main/resources/datos.txt";
        
        try {
            // Open the file for reading
            FileReader archivo = new FileReader(nombreArchivo);
            BufferedReader lector = new BufferedReader(archivo);
            
            System.out.println("=== CALCULADORA POSTFIX ===\n");
            
            String linea;
            int numeroLinea = 1;
            
            // Read each line from the file
            while ((linea = lector.readLine()) != null) {
                
                // Ignore empty lines
                if (linea.trim().isEmpty()) {
                    continue;
                }
                
                // Display the expression in console
                System.out.println("Expresion " + numeroLinea + ": " + linea);
                
                try {
                    // Calculate the result of the expression
                    double resultado = calculadora.calculate(linea);
                    System.out.println("Resultado: " + resultado);
                    
                    // Save the successful result for the PDF
                    resultados.add(new PDFGenerator.ExpresionResultado(
                        linea,
                        String.valueOf(resultado),
                        false
                    ));
                    
                } catch (Exception e) {
                    // If there is an error in calculation, display it in console
                    System.out.println("Error: " + e.getMessage());
                    
                    // Save the error for the PDF
                    resultados.add(new PDFGenerator.ExpresionResultado(
                        linea,
                        "ERROR: " + e.getMessage(),
                        true
                    ));
                }
                
                System.out.println();
                numeroLinea++;
            }
            
            // Close the file
            lector.close();
            archivo.close();
            
            // GENERATE THE PDF REPORT with all the results
            System.out.println("=== GENERANDO REPORTE PDF ===\n");
            pdfGenerator.generarReporte("reporte_postfix.pdf", resultados);
            
        } catch (IOException e) {
            // If the file cannot be read
            System.err.println("No se pudo leer el archivo: " + nombreArchivo);
            System.err.println("Error: " + e.getMessage());
        }
    }
}