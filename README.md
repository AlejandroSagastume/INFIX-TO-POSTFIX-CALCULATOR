# POSTFIX-CALCULATOR

Calculadora de expresiones en notación Postfix desarrollada en Java.

## Descripción

Este proyecto implementa:
- **ADT Stack** (Pila genérica) con dos implementaciones:
  - `StackArrayList` - Basada en ArrayList
  - `StackVector` - Basada en Vector
- **Clase utilitaria Stacks** - Permite cambiar fácilmente entre implementaciones
- **Calculadora Postfix** que evalúa expresiones aritméticas
- **Generador de reportes PDF** con iText7
- Operadores soportados: `+`, `-`, `*`, `/`
- Manejo de errores: división por cero, operandos insuficientes, caracteres inválidos

## Estructura del Proyecto
```
POSTFIX-CALCULATOR/
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
    │   │   │       ├── StackArrayList.java
    │   │   │       ├── StackVector.java
    │   │   │       ├── Stacks.java
    │   │   │       ├── PostfixCalculator.java
    │   │   │       ├── PDFGenerator.java
    │   │   │       └── Main.java
    │   │   └── resources/
    │   │       └── datos.txt
    │   └── test/
    │       └── java/
    │           └── org/postfix/
    │               ├── CalculatorTest.java
    │               └── StackOperation.java
    └── target/
        └── classes/
```
## Requisitos

- **Java 17 o superior**
- **Maven 3.6+** (recomendado)

## Instalación

### 1. Clonar el repositorio
```
git clone https://github.com/mjimevm/POSTFIX-CALCULATOR
cd POSTFIX-CALCULATOR
```
### 2. Verificar instalación de Java

java -version

Debe mostrar Java 17 o superior.

### 3. Instalación de Maven
```
cd demo
mvn clean install
```
## Compilación y Ejecución

### Opción 1: Con Maven (Recomendado)

1. Ve a la carpeta demo

cd demo

2. Compila el proyecto
```
mvn clean install
```
3. Ejecuta desde la raíz del proyecto
```
cd ..
java -cp demo/target/classes org.postfix.Main
```
### Opción 2: Sin Maven

1. Ve a la carpeta demo

2. Compila el código:
```
javac -d out src/main/java/org/postfix/*.java
```
**Nota:** La opción sin Maven **NO generará el PDF** porque requiere las librerías de iText7.

## Cambiar Implementación de Stack

El programa permite cambiar entre dos implementaciones de Stack:

### Método 1: En Main.java

Agrega al inicio del método main:
```
// Para usar Vector
Stacks.setDefaultType(Stacks.StackType.VECTOR);

// Para usar ArrayList
Stacks.setDefaultType(Stacks.StackType.ARRAYLIST);
```
### Método 2: En Stacks.java (línea 19)
```
// Cambiar el valor predeterminado
private static StackType defaultType = StackType.VECTOR;    // o ARRAYLIST
```
Después de cambiar, recompila:
```
cd demo
mvn clean install
cd ..
java -cp demo/target/classes org.postfix.Main
```
## Archivo de Datos

El programa lee expresiones desde `src/main/resources/datos.txt`

**Formato:**
- Operandos y operadores separados por espacios
- Una expresión por línea
- Operadores: `+` `-` `*` `/`

**Ejemplo:**
```
1 2 + 4 * 3 +
10 1 2 + +
6 2 3 + *
3 4 - 5 +
3 4 5 * -
10 12 12 * +
9 10 15 1 + + +
```
## Reporte PDF

El programa genera automáticamente un archivo `reporte_postfix.pdf` en la raíz del proyecto con:

- **Tabla de resultados** con todas las expresiones evaluadas
- **Errores resaltados** en color rosa
- **Resumen estadístico** (total, exitosos, errores)
- **Fecha y hora** de generación
- **Información del curso** y autores

**Ubicación del PDF generado:**

POSTFIX-CALCULATOR/
└── reporte_postfix.pdf  ← Se genera aquí

## Pruebas JUnit

Ejecutar todas las pruebas:

cd demo
mvn test

**Pruebas incluidas:**
- `StackOperation.java` - Pruebas del ADT Stack (Vector)
- `CalculatorTest.java` - Pruebas de la calculadora Postfix

## Dependencias

El proyecto utiliza las siguientes librerías (gestionadas automáticamente por Maven):

- **JUnit 5.10.1** - Framework de pruebas
- **iText7 7.2.5** - Generación de documentos PDF
  - `kernel` - Núcleo de iText
  - `layout` - Diseño y formato
  - `io` - Entrada/salida
- **SLF4J 1.7.36** - Sistema de logging (opcional, elimina warnings)

## Solución de Problemas

### Error: "cannot find symbol" al compilar

**Solución:** Asegúrate de compilar con Maven para descargar las dependencias de iText:

cd demo
mvn clean install

### El PDF no se genera

1. Verifica que compilaste con Maven: `mvn clean install`
2. Verifica que las dependencias se descargaron: `mvn dependency:tree`
3. Ejecuta desde la raíz del proyecto, no desde `demo/`

## Tecnologías Utilizadas

- **Java 17** - Lenguaje de programación
- **Maven** - Gestión de dependencias y construcción
- **JUnit 5** - Pruebas unitarias
- **iText7** - Generación de reportes PDF
- **Git/GitHub** - Control de versiones

## Implementaciones de Stack

### StackArrayList
- Usa `ArrayList<T>` internamente

### StackVector
- Usa `Vector<T>` internamente

Ambas implementan la misma interfaz `Stack<T>`, permitiendo intercambiarlas sin modificar el código de la calculadora mediante la clase utilitaria `Stacks`.

## Autores

- **Jimena Vásquez** - vas25092@uvg.edu.gt
- **Alejandro Sagastume** - sag25257@uvg.edu.gt

## Curso

**CC2003 - Sección 20 - Algoritmos y Estructura de Datos**  
Universidad del Valle de Guatemala  
Hoja de Trabajo No. 2
