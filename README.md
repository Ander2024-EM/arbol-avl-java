#  AVL Student Management System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Estado](https://img.shields.io/badge/Estado-Activo-success?style=for-the-badge)
![Versión](https://img.shields.io/badge/Versión-v1.2-blue?style=for-the-badge)

Sistema desarrollado en Java que implementa un Árbol AVL para la gestión eficiente de estudiantes, incorporando una interfaz gráfica moderna, manejo de datos y generación de reportes.

---

##  Descripción

Este proyecto aplica estructuras de datos avanzadas en un entorno práctico. A través de un Árbol AVL, se optimiza la organización y búsqueda de información, integrándolo en un sistema funcional con interfaz gráfica.

---

## Contexto del problema

En muchas instituciones educativas, la gestión de estudiantes se realiza directamente sobre bases de datos relacionales. Sin embargo, este enfoque no permite visualizar ni comprender estructuras jerárquicas o procesos de balanceo de datos.

Este sistema resuelve ese problema combinando:

- Persistencia en base de datos (MariaDB)
- Manipulación en memoria mediante un Árbol AVL

Permitiendo así:

- Búsquedas eficientes  
- Organización automática de datos  
- Visualización de estructura jerárquica  
- Análisis del comportamiento del árbol  

---

##  Características

- Implementación de Árbol AVL (auto-balanceado)
- Gestión de estudiantes (crear, editar, eliminar, buscar)
- Recorridos del árbol:
  - Inorden
  - Preorden
  - Postorden
- Interfaz gráfica desarrollada en Java Swing
- Generación de reportes en PDF y CSV
- Sistema de temas visuales (claro, oscuro, azul noche)
- Integración de módulo asistente
- Integración con base de datos MariaDB

---

##  Funcionamiento del Árbol AVL

El sistema utiliza un Árbol AVL para almacenar los estudiantes en memoria, garantizando que el árbol siempre se mantenga balanceado.

### Operaciones implementadas:

- Inserción de nodos  
- Búsqueda de nodos  
- Eliminación de nodos  
- Actualización de información  
- Recorridos (Inorden, Preorden, Postorden)

### Balanceo automático:

- Rotación simple izquierda  
- Rotación simple derecha  
- Rotación doble izquierda-derecha  
- Rotación doble derecha-izquierda  

Esto asegura eficiencia en todas las operaciones.

---

##  Integración con Base de Datos

El sistema trabaja con un enfoque híbrido:

1. Al iniciar la aplicación:
   - Se conecta a la base de datos  
   - Se cargan los registros  
   - Se construye el Árbol AVL en memoria  

2. Durante el uso:
   - Cada operación se ejecuta en:
     - El árbol (memoria)
     - La base de datos

3. Sincronización:
   - Se pueden guardar los cambios completos del árbol hacia la base de datos

Esto permite mantener consistencia entre la estructura en memoria y la persistencia.

---

##  Arquitectura del sistema
```bash
Modelo/
├── Estudiante.java
├── Nodo.java
└── ArbolAVL.java

dao/
├── Conexion.java
└── EstudianteDAO.java

vista/
├── FrmPrincipal.java
├── PanelEstudiantes.java
└── Paneles auxiliares

proyecto_arbol/
└── Main
```

### Separación de responsabilidades:

- **Modelo:** lógica del árbol AVL  
- **DAO:** acceso a base de datos  
- **Vista:** interfaz gráfica (Swing)  
- **Main:** inicialización del sistema  

---

##  Tecnologías utilizadas

- Java  
- Swing (Interfaz gráfica)  
- Programación Orientada a Objetos (POO)  
- iText (Generación de PDF)  
- MariaDB  

---

##  Instalación

1. Descargar el instalador desde la sección de **Releases**
2. Ejecutar `SistemaAVL_Setup.exe`
3. Seguir los pasos del asistente de instalación

**Requisitos:**

- Java JDK 8 o superior

---

## Descarga

[![Descargar aplicación](https://img.shields.io/badge/Descargar-Sistema%20AVL-blue?style=for-the-badge&logo=github)](https://github.com/Ander2024-EM/arbol-avl-java/releases/download/v1.2/SistemaAVL_Setup.exe)

---

##  Estructura del proyecto

```bash
src/
 ├── Modelo/
 ├── dao/
 ├── vista/
 └── proyecto_arbol/
```

---

##  Objetivo

Demostrar la aplicación de estructuras de datos como los Árboles AVL dentro de un sistema funcional, integrando interfaz gráfica y persistencia de datos.

---

##  Decisiones de diseño

- Uso de Árbol AVL para garantizar eficiencia en búsquedas  
- Separación en capas (Modelo, DAO, Vista)  
- Integración con base de datos para persistencia  
- Implementación manual del árbol (sin librerías externas)  

---

##  Autor

**Albert Elias**

---

##  Nota

Proyecto académico orientado al desarrollo práctico de estructuras de datos, diseño de interfaces y gestión de información.
