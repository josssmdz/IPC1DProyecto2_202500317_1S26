
---
# Manual de Usuario
# GameZone Pro

## 1. Introducción

GameZone Pro es una aplicación de escritorio desarrollada en Java Swing que permite al usuario interactuar con diferentes módulos relacionados con una plataforma gamer. El sistema incluye una tienda de videojuegos, un álbum de cartas coleccionables, torneos, gamificación y reportes.

Este manual explica el funcionamiento general de la aplicación y la forma correcta de utilizar cada uno de sus apartados.

## 2. Requisitos de uso

- Tener Java instalado.
- Ejecutar el archivo `.jar` o el proyecto desde NetBeans.
- Contar con los archivos de texto necesarios para la persistencia del sistema.

## 3. Pantalla principal

Al iniciar la aplicación se muestra el menú principal, desde donde el usuario puede navegar a cada módulo del sistema.

<p align="center">
  <img src="img/menuprincipal.png" alt="Menú principal" width="700">
</p>

## 4. Menú principal

Desde esta pantalla se puede acceder a:

- Tienda de Videojuegos
- Álbum de Cartas Coleccionables
- Eventos Especiales
- Programa de Recompensas
- Reportes
- Datos del Estudiante
- Salir


## 5. Módulo Tienda de Videojuegos

Este módulo permite gestionar la compra de videojuegos.

### Funciones principales

- Visualizar el catálogo de juegos.
- Buscar juegos por nombre o código.
- Filtrar por género o plataforma.
- Agregar juegos al carrito.
- Eliminar juegos del carrito.
- Confirmar compras.
- Ver historial de compras.

### Proceso de uso

1. Ingresar al módulo Tienda.
2. Explorar el catálogo de juegos.
3. Usar la búsqueda o filtros si se desea encontrar un juego específico.
4. Presionar el botón para agregar un juego al carrito.
5. Revisar el carrito en el panel derecho.
6. Confirmar la compra.
7. Verificar que el historial se actualice.

<p align="center">
  <img src="img/modulotienda.png" alt="Módulo tienda" width="700">
</p>

## 6. Módulo Álbum de Cartas

Este módulo permite administrar cartas coleccionables dentro de una cuadrícula.

### Funciones principales

- Agregar cartas manualmente.
- Seleccionar cartas para ver sus detalles.
- Intercambiar cartas entre posiciones.
- Buscar cartas por nombre, tipo o rareza.

### Qué debe ingresar el usuario al agregar carta

Los datos se escriben manualmente en el formulario:

- Código
- Nombre
- Tipo
- Rareza
- Ataque
- Defensa
- PS

### Ejemplo de carta válida

- Código: `CARTA-001`
- Nombre: `Pikachu`
- Tipo: `Eléctrico`
- Rareza: `Rara`
- Ataque: `85`
- Defensa: `60`
- PS: `100`

### Proceso de uso

1. Entrar al módulo Álbum.
2. Presionar `Agregar Carta`.
3. Escribir los datos solicitados.
4. Confirmar el ingreso.
5. Seleccionar una carta para ver sus detalles.
6. Usar la función `Intercambiar` si se desea cambiar posiciones.
7. Para intercambiar, ingresar la posición con formato `fila,columna` usando numeración desde 1.
   Ejemplo: `1,3`

<p align="center">
  <img src="img/moduloalbum.png" alt="Módulo álbum" width="700">
</p>

## 7. Módulo Eventos Especiales

Este módulo permite la inscripción de participantes y la simulación de venta de tickets para torneos.

### Funciones principales

- Visualizar torneos disponibles.
- Inscribir participantes.
- Encolar participantes.
- Iniciar proceso de venta.
- Ver estado de taquillas.
- Visualizar log de tickets vendidos.

### Proceso de uso

1. Ingresar al módulo Torneos.
2. Seleccionar un torneo de la tabla.
3. Presionar `Inscribirse`.
4. Ingresar el nombre del participante.
5. Repetir el proceso si se desean más personas en la cola.
6. Presionar `Iniciar Venta`.
7. Observar la ejecución concurrente de las taquillas.

<p align="center">
  <img src="img/modulotorneos.png" alt="Módulo torneos" width="700">
</p>

## 8. Módulo Gamificación

Este módulo muestra el progreso del usuario principal dentro del sistema.

### Información mostrada

- Nombre del usuario principal
- XP acumulado
- Nivel actual
- Rango
- Barra de progreso
- Logros
- Tabla de posiciones

### Aclaración importante

El sistema trabaja con un usuario principal fijo. Los nombres ingresados en torneos no se convierten en usuarios completos del sistema, sino en participantes de la cola de tickets.

<p align="center">
  <img width="700" src="https://github.com/user-attachments/assets/89a22039-4cfa-4336-807e-7d38669cb40b" />
</p>


## 9. Módulo Reportes

Permite generar reportes HTML del sistema.

### Tipos de reporte

- Inventario de tienda
- Ventas
- Álbum
- Torneos

### Proceso de uso

1. Ingresar al módulo Reportes.
2. Seleccionar el reporte deseado.
3. Esperar a que se genere el archivo.
4. Verificar que el navegador abra el HTML automáticamente.

<p align="center">
  <img width="700" src="https://github.com/user-attachments/assets/3912fafc-81d5-4104-a59d-c9f76e6fbd49" />
</p>


## 10. Panel del estudiante

Este apartado muestra exclusivamente la información del estudiante desarrollador del proyecto.

### Información mostrada

- Nombre
- Carné
- Correo
- Sección
- Semestre
- Descripción breve del proyecto

<p align="center">
  <img width="700"src="https://github.com/user-attachments/assets/e4b2f31d-d86e-428c-a775-edca532b5cfb" />
</p>

## 11. Salida del sistema

Al cerrar la aplicación, el sistema solicita confirmación y posteriormente guarda la información correspondiente en archivos de texto.

## 12. Recomendaciones

- No cerrar la aplicación de forma abrupta.
- Realizar pruebas de cada módulo antes de generar reportes.
- Verificar que los archivos `.txt` se mantengan accesibles.
