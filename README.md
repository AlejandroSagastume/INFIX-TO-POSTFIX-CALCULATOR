# INFIX-TO-POSTFIX-CALCULATOR

Calculadora de expresiones en notación Infix que convierte a Postfix y luego evalúa el resultado.  
Desarrollada en Java utilizando ADT, patrones de diseño y pruebas unitarias.

---

## Descripción

Este proyecto implementa:

- - Conversión de expresiones **Infix → Postfix**
- - Evaluación de expresiones **Postfix**
- - **ADT Stack (Pila genérica)** con tres implementaciones:
    - `StackArrayList` - Basada en ArrayList
    - `StackVector` - Basada en Vector
    - `StackList` - Basada en Lista enlazada
- - **ADT List (Lista genérica)** con dos implementaciones:
    - `SinglyLinkedList` - Lista simplemente enlazada
    - `DoublyLinkedList` - Lista doblemente enlazada
- - Patrón de diseño **Factory**
    - `StackFactory`
    - `ListFactory`
- - Patrón de diseño **Singleton**
    - `PostfixCalculator`
- - Generador de reportes PDF con iText7
- - Pruebas unitarias con JUnit 5

### Operadores soportados:

`+` `-` `*` `/`

### Manejo de errores:

- División entre cero  
- Operandos insuficientes  
- Expresión inválida  
- Paréntesis desbalanceados  
- Caracteres inválidos  

---

## Estructura del Proyecto

```
INFIX-TO-POSTFIX-CALCULATOR/
├── README.md
├── .gitignore
└── demo/                           ← TRABAJAR SIEMPRE DESDE AQUÍ
    ├── pom.xml
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── org/postfix/
    │   │   │       ├── Calc.java
    │   │   │       ├── Stack.java
    │   │   │       ├── AbstractStack.java
    │   │   │       ├── StackArrayList.java
    │   │   │       ├── StackVector.java
    │   │   │       ├── StackList.java
    │   │   │       ├── ListADT.java
    │   │   │       ├── AbstractListADT.java
    │   │   │       ├── SinglyLinkedList.java
    │   │   │       ├── DoublyLinkedList.java
    │   │   │       ├── StackFactory.java
    │   │   │       ├── ListFactory.java
    │   │   │       ├── InfixToPostfix.java
    │   │   │       ├── PostfixCalculator.java
    │   │   │       ├── PDFGenerator.java
    │   │   │       └── Main.java
    │   │   └── resources/
    │   │       └── datos.txt
    │   └── test/
    │       └── java/
    │           └── org/postfix/
    │               ├── CalculatorTest.java
    │               ├── InfixToPostfixTest.java
    │               ├── StackImplementationsTest.java
    │               ├── StackListTest.java
    │               └── StackOperation.java
    └── target/
```

---

## Requisitos

- **Java 17 o superior**
- **Maven 3.6+** (recomendado)

### Versión utilizada en este proyecto:

```
openjdk version "25.0.2" 2026-01-20 LTS
OpenJDK Runtime Environment Temurin-25.0.2+10 (build 25.0.2+10-LTS)
OpenJDK 64-Bit Server VM Temurin-25.0.2+10 (build 25.0.2+10-LTS, mixed mode, sharing)
```

---

## Instalación

### 1. Clonar el repositorio

```
git clone https://github.com/AlejandroSagastume/INFIX-TO-POSTFIX-CALCULATOR/edit/main/README.md
cd INFIX-TO-POSTFIX-CALCULATOR
```

### 2. Compilar con Maven

```
cd demo
mvn clean install
```

Debe mostrar:

```
BUILD SUCCESS
```

---

## Compilación y Ejecución

### Opción 1

```
cd demo
mvn clean install
cd ..
java -cp demo/target/classes org.postfix.Main
```

El programa:

1. Solicita implementación de pila  
2. Si se elige LIST, solicita tipo de lista (Singly o Doubly)  
3. Lee expresiones desde `datos.txt`  
4. Convierte Infix → Postfix  
5. Evalúa  
6. Genera reporte PDF automáticamente  

---

### Opción 2

```
cd demo
javac -d out src/main/java/org/postfix/*.java
```

⚠ Nota: Sin Maven no se generará el PDF porque requiere iText7.

---

## Archivo de Datos

Ubicación:

```
src/main/resources/datos.txt
```

Ejemplo:

```
5 + 8 * (3 + 2)
8 + 75 + 95 * 32
96 - 25 - (63 / 3)
69 * + 6
(10+20)*9
```

---

## Reporte PDF

El programa genera automáticamente:

```
reporte_postfix.pdf
```

Incluye:

- Tabla con todas las expresiones evaluadas  
- Errores resaltados  
- Resumen estadístico  
- Fecha y hora de generación  
- Información del curso y autores  

---

## Pruebas JUnit

Ejecutar todas las pruebas:

```
cd demo
mvn test
```

Pruebas incluidas:

- `CalculatorTest.java`
- `StackImplementationsTest.java`
- `StackListTest.java`
- `InfixToPostfixTest.java`
- `StackOperation.java`
---

## Tecnologías Utilizadas

- Java 25 LTS 
- Maven
- JUnit 5
- iText7
- Git/GitHub

---

## Autores

- **Jimena Vásquez**
- **Alejandro Sagastume**

Curso:  
**CC2003 - Algoritmos y Estructura de Datos**  
Universidad del Valle de Guatemala  
Hoja de Trabajo No. 4
