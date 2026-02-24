package org.postfix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== SELECCIONAR IMPLEMENTACIÓN DE PILA =====");
        System.out.println("1. ArrayList");
        System.out.println("2. Vector");
        System.out.println("3. Lista (Singly / Doubly)");
        System.out.print("Opción: ");

        int option = scanner.nextInt();
        StackFactory.StackType stackType;
        ListFactory.ListType listType = null;

        switch (option) {
            case 1:
                stackType = StackFactory.StackType.ARRAYLIST;
                break;
            case 2:
                stackType = StackFactory.StackType.VECTOR;
                break;
            case 3:
                stackType = StackFactory.StackType.LIST;
                System.out.println("\nSeleccione tipo de lista:");
                System.out.println("1. Simplemente enlazada");
                System.out.println("2. Doblemente enlazada");
                System.out.print("Opción: ");
                int listOption = scanner.nextInt();
                if (listOption == 1) {
                    listType = ListFactory.ListType.SINGLY;
                } else if (listOption == 2) {
                    listType = ListFactory.ListType.DOUBLY;
                } else {
                    System.out.println("Opción inválida.");
                    scanner.close();
                    return;
                }
                break;
            default:
                System.out.println("Opción inválida.");
                scanner.close();
                return;
        }
        scanner.close();

        InfixToPostfix converter = new InfixToPostfix();
        PostfixCalculator calculator = PostfixCalculator.getInstance();
        PDFGenerator pdfGenerator = new PDFGenerator();
        List<PDFGenerator.ExpresionResultado> resultados = new ArrayList<>();
        String fileName = "src/main/resources/datos.txt";
        System.out.println("\n===== PROCESANDO EXPRESIONES =====\n");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                System.out.println("Expresión " + lineNumber + ": " + line);

                try {
                    String postfix = converter.convert(line);
                    System.out.println("Postfix: " + postfix);
                    double result = calculator.calculate(postfix);
                    System.out.println("Resultado: " + result);
                    resultados.add(
                            new PDFGenerator.ExpresionResultado(
                                    line,
                                    String.valueOf(result),
                                    false
                            )
                    );
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    resultados.add(
                            new PDFGenerator.ExpresionResultado(
                                    line,
                                    "ERROR: " + e.getMessage(),
                                    true
                            )
                    );
                }
                System.out.println();
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo.");
            System.out.println(e.getMessage());
            return;
        }
        try {
            pdfGenerator.generarReporte("reporte_postfix.pdf", resultados);
        } catch (Exception e) {
            System.out.println("Error al generar el PDF.");
            System.out.println(e.getMessage());
        }
        System.out.println("\n===== FIN DEL PROGRAMA =====");
    }
}