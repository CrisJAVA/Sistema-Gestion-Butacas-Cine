// =============================
// Paradigma Orientado a Objetos
// =============================
// Punto de entrada de la aplicación.
// Se crean los objetos del modelo, controlador y vista,
// y se establecen las relaciones entre ellos (MVC).

// =============================
// Paradigma Modular
// =============================
// La clase Main pertenece al paquete "principal" y solo se
// encarga de inicializar y lanzar la aplicación, separando
// esta responsabilidad de las demás clases.

package principal;

import modelo.SalaCine;
import controlador.SalaController;
import vista.FrmSalaCine;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // =============================
        // Paradigma Imperativo
        // =============================
        // Secuencia de instrucciones para inicializar la aplicación:
        // 1. Crear el modelo
        // 2. Crear el controlador
        // 3. Crear la vista y asociarla al controlador
        // 4. Hacer visible la ventana

        SwingUtilities.invokeLater(() -> {
            SalaCine modelo     = new SalaCine(6, 8);
            SalaController controlador = new SalaController(modelo);
            FrmSalaCine vista   = new FrmSalaCine(controlador, modelo);
            controlador.setVista(vista);
            vista.setVisible(true);
        });
    }
}
