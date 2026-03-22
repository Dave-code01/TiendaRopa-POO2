# Tienda de Ropa — Sistema E-commerce

Proyecto desarrollado para la materia Programación Orientada a Objetos II
en la IU Digital de Antioquia.

## Integrantes
- David Castro Mora
- Dilbani Enríquez Botina

**Profesor:** Ramiro A. Giraldo Escobar

---

## ¿De qué trata?

Creamos un sistema de ventas para una tienda de ropa usando Java.
El programa permite gestionar productos, clientes y pedidos,
aplicar descuentos y validar datos de entrada.

---

## ¿Qué aplicamos?

- Herencia entre clases (Usuario → Cliente)
- Relaciones de composición y asociación
- Patrón Singleton para el gestor principal
- Patrón Strategy para los tipos de descuento
- Streams y lambdas para consultas sobre colecciones
- Manejo de errores con try-catch-finally

---

## Estructura
```
src/tienda/
├── Main.java
├── modelo/
├── patrones/
├── funcional/
└── errores/
```

---

## Cómo correrlo
```bash
javac -d out src/tienda/modelo/*.java src/tienda/patrones/*.java src/tienda/funcional/*.java src/tienda/errores/*.java src/tienda/Main.java
java -cp out tienda.Main
```

---

## Enlaces
- Video de sustentación: (pendiente)
