// =============================
// Paradigma Orientado a Objetos
// =============================
// SalaController es la clase controladora del patrón MVC.
// Actúa como intermediaria entre el modelo (SalaCine, Butaca)
// y la vista (FrmSalaCine), manejando las acciones del usuario
// y actualizando la interfaz según los resultados.

package controlador;

import modelo.SalaCine;
import vista.FrmSalaCine;
import javax.swing.JOptionPane;

public class SalaController {
    private SalaCine sala;
    private FrmSalaCine vista;

    public SalaController(SalaCine sala) {
        this.sala = sala;
        this.vista = null;
    }

    public void setVista(FrmSalaCine vista) {
        this.vista = vista;
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Cada método ejecuta una secuencia de instrucciones:
    // validar, ejecutar operación, mostrar resultado, refrescar GUI.

    public void reservarButaca(int fila, int columna) {
        if (!sala.validarPosicion(fila, columna)) {
            JOptionPane.showMessageDialog(vista,
                "Posición inválida. Fila o columna fuera de rango.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = sala.reservarButaca(fila, columna);
        if (exito) {
            JOptionPane.showMessageDialog(vista,
                "Reserva realizada con éxito.",
                "Reserva", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String estado = sala.getButacas()[fila][columna].getEstado();
            if (estado.equals("Ocupado")) {
                JOptionPane.showMessageDialog(vista,
                    "La butaca ya está ocupada. No se puede reservar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            } else if (estado.equals("Reservado")) {
                JOptionPane.showMessageDialog(vista,
                    "La butaca ya está reservada.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        vista.actualizarSala();
    }

    public void cancelarReserva(int fila, int columna) {
        if (!sala.validarPosicion(fila, columna)) {
            JOptionPane.showMessageDialog(vista,
                "Posición inválida. Fila o columna fuera de rango.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = sala.cancelarReserva(fila, columna);
        if (exito) {
            JOptionPane.showMessageDialog(vista,
                "Reserva cancelada con éxito.",
                "Cancelar Reserva", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String estado = sala.getButacas()[fila][columna].getEstado();
            if (estado.equals("Libre")) {
                JOptionPane.showMessageDialog(vista,
                    "La butaca no está reservada. No se puede cancelar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            } else if (estado.equals("Ocupado")) {
                JOptionPane.showMessageDialog(vista,
                    "La butaca está ocupada. No se puede cancelar una reserva inexistente.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        vista.actualizarSala();
    }

    public void contarButacasLibres() {
        int libres = sala.contarButacasLibres();
        int total = sala.getFilas() * sala.getColumnas();
        JOptionPane.showMessageDialog(vista,
            "Butacas libres: " + libres + " de " + total,
            "Conteo de Butacas", JOptionPane.INFORMATION_MESSAGE);
    }

    public void reiniciarSala() {
        sala.reiniciarSala();
        JOptionPane.showMessageDialog(vista,
            "La sala se ha reiniciado. Todas las butacas están libres.",
            "Reiniciar Sala", JOptionPane.INFORMATION_MESSAGE);
        vista.actualizarSala();
    }

    public void mostrarSala() {
        String representacion = sala.mostrarSala();
        JOptionPane.showMessageDialog(vista,
            representacion,
            "Estado de la Sala", JOptionPane.INFORMATION_MESSAGE);
    }

    public void salir() {
        int confirmacion = JOptionPane.showConfirmDialog(vista,
            "¿Está seguro de que desea salir?",
            "Salir", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
