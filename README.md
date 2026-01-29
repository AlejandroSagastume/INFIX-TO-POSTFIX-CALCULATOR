# POSTFIX-CALCULATOR

Calculadora de expresiones en notación Postfix (polaca inversa) desarrollada en Java.

## Descripción

Este proyecto implementa:
- **ADT Stack** (Pila genérica) usando ArrayList
- **Calculadora Postfix** que evalúa expresiones aritméticas
- Operadores soportados: `+`, `-`, `*`, `/`
- Manejo de errores: división por cero, operandos insuficientes, caracteres inválidos

## Estructura del Proyecto
```
POSTFIX-CALCULATOR/
├── README.md
└── demo/                           ← TRABAJAR SIEMPRE DESDE AQUÍ
    ├── pom.xml
    ├── src/
    │   └── main/
    │       ├── java/
    │       │   └── org/postfix/
    │       │       ├── Calc.java
    │       │       ├── Stack.java
    │       │       ├── StackVector.java
    │       │       ├── PostfixCalculator.java
    │       │       └── Main.java
    │       └── resources/
    │           └── datos.txt
    └── target/
        └── classes/
```
## Requisitos

- Java 17 o superior
- Maven 3.6+ (opcional pero recomendado)

## Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/mjimevm/POSTFIX-CALCULATOR
cd POSTFIX-CALCULATOR/DEMO
```

## 2. Instalación de Maven

```bash
mvn clean install
```

## Compilación y Ejecución

### Opción 1

1. Ve a la carpeta demo
cd demo

2. Compila el proyecto
```
mvn clean install
```

3. Ejecuta desde la raíz del proyecto
cd ..
```
java -cp demo/target/classes org.postfix.Main
```
### Opción 2

1. Ve a la carpeta demo.

2. Compila el código:
```
javac -d out src/main/java/org/postfix/*.java
```

4. Ejecuta el programa desde la raíz del proyecto:
```
java -cp out org.postfix.Main
```
## Archivo de Datos

El programa lee expresiones desde src/main/resources/datos.txt

Formato:
- Operandos y operadores separados por espacios
- Una expresión por línea
- Operadores: + - * /

Ejemplo:
1 2 + 4 * 3 +
6 2 3 + *
4 2 + 3 5 1 - * +
15 7 1 1 + - / 3 * 2 1 1 + + -
5 1 2 + 4 * + 3 -

(Aquí puede colocar los datos necesarios para la prueba)

## Ejemplo de Salida

=== CALCULADORA POSTFIX ===

Expresion 1: 1 2 + 4 * 3 +
Resultado: 15.0

Expresion 2: 6 2 3 + *
Resultado: 30.0

Expresion 3: 4 2 + 3 5 1 - * +
Resultado: 18.0

Expresion 4: 15 7 1 1 + - / 3 * 2 1 1 + + -
Resultado: 5.0

Expresion 5: 5 1 2 + 4 * + 3 -
Resultado: 14.0

## Pruebas JUnit

Desde la carpeta demo:
cd demo
mvn test

## Autores

- **Jimena Vásquez** (vas25092@uvg.edu.gt)
- **Alejandro Sagastume** (sag25257@uvg.edu.gt)

## Curso

CC2003 - Sección 20 - Algoritmos y Estructura de Datos
Universidad del Valle de Guatemala
Hoja de Trabajo No. 2
