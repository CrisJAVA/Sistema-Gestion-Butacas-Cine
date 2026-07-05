# 🎬 Sistema de Gestión de Butacas de Cine

<p align="center">
   <img src="src/IMG/banner.png" width="900">
</p>

---

## 📖 Descripción

**Sistema de Gestión de Butacas de Cine** es una aplicación de escritorio que permite administrar la venta y reserva de asientos en un cine. El programa cuenta con dos perfiles de usuario —**Cliente** y **Administrador**— y ofrece una interfaz visual moderna e intuitiva para gestionar múltiples salas, sus butacas y los estados de cada una.

---

## ✨ Características principales

- Administrar varias salas de cine de forma independiente.
- Crear nuevas salas con la cantidad de filas y columnas deseada.
- Reservar butacas disponibles.
- Ocupar butacas (solo si la reserva fue realizada por el mismo usuario).
- Cancelar reservas propias.
- Consultar el estado de cada asiento en tiempo real.
- Sincronización automática entre la vista de Cliente y Administrador.
- Estadísticas en vivo de disponibilidad, reservas y ocupación.

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Propósito |
|------------|-----------|
| Java | Lenguaje de programación principal |
| Java Swing (JFrame) | Interfaz gráfica de usuario |
| IntelliJ IDEA | Entorno de desarrollo |
| Patrón MVC | Arquitectura del proyecto |
| POO | Programación Orientada a Objetos |

---

## 🚀 Cómo ejecutar el proyecto

1. Abrir **IntelliJ IDEA**.
2. Ir a *File → Open* y seleccionar la carpeta del proyecto.
3. Esperar a que IntelliJ cargue e indexe los archivos.
4. En el panel de proyecto, ubicar `src/Main.java`.
5. Hacer clic derecho sobre `Main.java` y seleccionar **Run 'Main.main()'**.
6. Se abrirá la ventana de inicio del sistema.
7. Seleccionar el tipo de usuario y comenzar a usar el programa.

---

## 📘 Manual de uso

### Pantalla de inicio

Al ejecutar el programa aparece una pantalla de bienvenida con dos opciones de ingreso:

- **Ingresar como Cliente**
- **Ingresar como Administrador**

---

### 👤 Cliente

El cliente puede realizar las siguientes acciones:

- Seleccionar una sala del listado disponible.
- Visualizar la distribución de butacas con su estado actual.
- **Reservar** un asiento que esté disponible.
- **Ocupar** un asiento previamente reservado por él mismo.
- **Cancelar** una reserva que haya realizado.
- Consultar el estado de cualquier asiento seleccionado.

---

### 🔧 Administrador

El administrador tiene acceso a todas las funciones del cliente, más las siguientes:

- **Crear** nuevas salas (nombre, filas y columnas).
- **Eliminar** salas existentes.
- **Reiniciar** una sala (todas las butacas vuelven a disponible).
- Ver **estadísticas** en tiempo real (disponibles, reservados, ocupados).
- Gestionar todas las salas desde una misma interfaz.

---

### 🎫 Estados de las butacas

| Estado | Significado |
|--------|-------------|
| 🟢 Disponible | El asiento está libre y puede ser reservado u ocupado |
| 🟡 Reservado | El asiento fue reservado por un usuario |
| 🔴 Ocupado | El asiento ya fue ocupado y no está disponible |

Cada butaca muestra su código (ej. A1, B3, C5) sobre la imagen que representa su estado actual.

---

## 📁 Estructura del proyecto

```
src/
│
├── model/        # Clases del modelo (Butaca, Sala, GestorSalas)
├── view/         # Ventanas de la interfaz gráfica (FrmInicio, FrmCliente, FrmAdministrador, FrmComprobante)
├── controller/   # Controladores (SalaController, ClienteController, AdminController)
├── IMG/          # Imágenes de butacas, logos y recursos visuales
└── Main.java     # Punto de entrada de la aplicación
```

---

## 📸 Capturas de pantalla

### Menú Principal

(Agregar imagen aquí)

### Vista Cliente

(Agregar imagen aquí)

### Vista Administrador

(Agregar imagen aquí)

---

## 👤 Autor

Proyecto desarrollado para el curso de **Lenguajes de Programación**.

**Autor:** _________________________

---
