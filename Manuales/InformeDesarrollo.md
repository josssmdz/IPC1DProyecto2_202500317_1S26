# Informe de Desarrollo
# GameZone Pro

## 1. Introducción

El presente informe describe el proceso de desarrollo del Proyecto 2: GameZone Pro, elaborado para el curso Introducción a la Programación y Computación 1. El proyecto consistió en implementar una aplicación de escritorio que integrara varios módulos funcionales utilizando Java Swing y estructuras de datos desarrolladas desde cero.

## 2. Objetivo del proyecto

Desarrollar una aplicación gráfica que permitiera aplicar conocimientos de:

- Programación orientada a objetos
- Estructuras de datos dinámicas
- Persistencia en archivos
- Programación concurrente
- Generación de reportes
- Diseño de interfaces en Swing

## 3. Plan de desarrollo

El proyecto se desarrolló por etapas:

- Definición de estructuras de datos
- Creación de modelos del sistema
- Diseño de la ventana principal
- Implementación de cada módulo
- Integración de persistencia
- Implementación de reportes HTML
- Ajustes finales y pruebas

## 4. Estructuras implementadas

### Lista enlazada simple

Fue utilizada para representar distintas colecciones internas del sistema como el catálogo, el carrito, el historial, los logros y los tickets vendidos.

### Cola

Se utilizó en el módulo de torneos para modelar la fila de espera de participantes.

### Malla ortogonal

Se utilizó en el módulo álbum para representar la estructura matricial de cartas mediante enlaces entre nodos.

## 5. Desarrollo de módulos

### 5.1 Tienda de videojuegos

Se desarrolló un panel para mostrar juegos cargados desde archivo y permitir la compra de videojuegos. Este módulo incluye catálogo, filtros, búsqueda, carrito e historial de compras.

<p align="center">
  <img src="img/modulotienda.png" alt="Desarrollo de tienda" width="700">
</p>

### 5.2 Álbum de cartas

Se implementó una malla ortogonal para almacenar cartas coleccionables. El usuario puede agregar cartas manualmente, buscar coincidencias y reorganizar posiciones mediante intercambio.

<p align="center">
  <img src="img/moduloalbum.png" alt="Desarrollo de álbum" width="700">
</p>

### 5.3 Torneos

Se implementó un sistema de torneos con participantes en cola y dos taquillas concurrentes usando hilos. Esto permitió simular el procesamiento simultáneo de compra de tickets.

<p align="center">
  <img src="img/modulotorneos.png" alt="Desarrollo de torneos" width="700">
</p>

### 5.4 Gamificación

Se agregó un sistema de experiencia, niveles, logros y tabla de posiciones. Las acciones realizadas en tienda, álbum y torneos se reflejan en el progreso del usuario principal.

<p align="center">
  <img width="700" src="https://github.com/user-attachments/assets/2649fb62-e97a-49b6-adf0-8b38182c4185" />
</p>

### 5.5 Reportes

Se implementó la generación de archivos HTML para representar información importante del sistema como inventario, ventas, álbum y torneos.

<p align="center">
  <img width="700" src="https://github.com/user-attachments/assets/d8243579-6db2-4af3-a5b1-d3eec9a778dd" />
</p>

## 6. Problemas encontrados

Durante el desarrollo se presentaron varios retos:

- Comunicación entre módulos de la interfaz.
- Integración entre las estructuras personalizadas.
- Manejo manual de persistencia.
- Coordinación de hilos y actualización de la interfaz.
- Corrección de referencias, métodos faltantes y detalles de navegación.

## 7. Soluciones aplicadas

Para resolver los problemas encontrados se aplicaron las siguientes medidas:

- Separación del sistema en paquetes por responsabilidad.
- Uso de `CardLayout` para la navegación.
- Uso de getters para compartir información entre módulos.
- Implementación de `synchronized` en la cola.
- Uso de `SwingUtilities.invokeLater()` para actualizar componentes Swing desde hilos.
- Validaciones para entradas del usuario.
- Correcciones en el comportamiento del álbum y sus posiciones.

## 8. Resultados obtenidos

Se obtuvo una aplicación funcional que integra módulos independientes dentro de una interfaz gráfica unificada. El sistema permite simular compras, colección de cartas, torneos y recompensas, además de generar reportes HTML automáticos.

## 9. Aprendizajes obtenidos

Este proyecto permitió fortalecer conocimientos en:

- Programación orientada a objetos
- Swing
- Estructuras de datos desde cero
- Manejo de archivos
- Hilos y concurrencia
- Organización modular del código

## 10. Evidencia visual

### Menú principal

<p align="center">
  <img src="img/menuprincipal.png" alt="Menú principal" width="700">
</p>

### Tienda

<p align="center">
  <img src="img/modulotienda.png" alt="Tienda" width="700">
</p>

### Álbum

<p align="center">
  <img src="img/moduloalbum.png" alt="Álbum" width="700">
</p>

### Torneos

<p align="center">
  <img src="img/modulotorneos.png" alt="Torneos" width="700">
</p>

### Gamificación

<p align="center">
  <img width="700" src="https://github.com/user-attachments/assets/2649fb62-e97a-49b6-adf0-8b38182c4185" />
</p>

### Reportes

<p align="center">
  <img width="700" src="https://github.com/user-attachments/assets/d8243579-6db2-4af3-a5b1-d3eec9a778dd" />
</p>

## 11. Conclusiones

El desarrollo de GameZone Pro permitió integrar en un solo sistema varias competencias del curso. La implementación manual de estructuras de datos, la construcción de la interfaz y la incorporación de concurrencia hicieron posible desarrollar una solución amplia y funcional.

## 12. Recomendaciones

- Mantener una estructura modular del proyecto.
- Probar cada módulo de forma individual antes de integrarlo.
- Fortalecer documentación desde etapas tempranas.
- Verificar persistencia y validaciones antes de la entrega final.
