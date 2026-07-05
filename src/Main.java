// =============================
// Paradigma Orientado a Objetos
// =============================
// Punto de entrada de la aplicación. Muestra la ventana
// de inicio (FrmInicio) para que el usuario seleccione
// el rol con el que desea ingresar al sistema.

// =============================
// Paradigma Modular
// =============================
// Main solo se encarga de arrancar la aplicación,
// manteniendo esta responsabilidad separada del resto.

import view.FrmInicio;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // =============================
        // Paradigma Imperativo
        // =============================
        // Secuencia de instrucciones:
        // 1. Crear la ventana de inicio
        // 2. Mostrarla en pantalla

        SwingUtilities.invokeLater(() -> {
            new FrmInicio().setVisible(true);
        });
    }
}
