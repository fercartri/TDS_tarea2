# Gestión de Facturas - Tarea 2

> **Asignatura:** Tecnologías para el Desarrollo de Software  
> **Grado:** Ingeniería Informática (Mención en Ingeniería del Software)  
> **Universidad:** Universidad de Valladolid  

Este proyecto consiste en la implementación de una aplicación para la gestión de facturas de una empresa utilizando la metodología **Desarrollo Guiado por Pruebas (TDD)**. El objetivo es desarrollar las funcionalidades especificadas aplicando técnicas como la Caja Negra, la Partición en Clases de Equivalencia y el Análisis de Valores Límite.

---

## Funcionalidades principales

El sistema permitirá gestionar un conjunto de facturas asociadas a un gestor de facturación, proporcionando operaciones como:

- Crear y administrar un gestor de facturación con restricciones sobre fechas y nombre.
- Añadir facturas individuales o en lotes, asegurando que cumplan las restricciones.
- Actualizar datos de facturas existentes en el gestor.
- Consultar listados de facturas ordenados por fecha o por importe.
- Manejo de excepciones para operaciones no permitidas.

El desarrollo sigue un enfoque modular que incluye el uso de **JUnit** para escribir pruebas y documentar el código con **Javadoc**.

---

## Requisitos técnicos

El proyecto está diseñado como un proyecto **Maven** compatible con **JDK 17**. La estructura del código cumple con las especificaciones del arquetipo `maven-archetype-quickstart`.

---

## Metodología

El desarrollo se lleva a cabo aplicando el ciclo de TDD, con commits regulares que documentan cada paso: creación de pruebas, implementación y refactorización.
