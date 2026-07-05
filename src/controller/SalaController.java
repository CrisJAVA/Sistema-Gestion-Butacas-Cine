// =============================
// Paradigma Orientado a Objetos
// =============================
// SalaController es la clase base del controlador en el patrón MVC.
// Contiene las operaciones compartidas entre cliente y administrador:
// reservar, ocupar y cancelar reserva.

package controller;

import model.Butaca;
import model.GestorSalas;
import model.Sala;

public class SalaController {
    protected GestorSalas gestor;

    public SalaController() {
        this.gestor = GestorSalas.getInstance();
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Secuencia de instrucciones: obtener sala, validar, ejecutar.

    public boolean reservarButaca(String nombreSala, String codigoAsiento) {
        Sala sala = gestor.getSala(nombreSala);
        if (sala == null) return false;
        return sala.reservarButaca(codigoAsiento);
    }

    public boolean ocuparButaca(String nombreSala, String codigoAsiento) {
        Sala sala = gestor.getSala(nombreSala);
        if (sala == null) return false;
        return sala.ocuparButaca(codigoAsiento);
    }

    public boolean cancelarReserva(String nombreSala, String codigoAsiento) {
        Sala sala = gestor.getSala(nombreSala);
        if (sala == null) return false;
        return sala.cancelarReserva(codigoAsiento);
    }

    public Butaca getButaca(String nombreSala, String codigoAsiento) {
        Sala sala = gestor.getSala(nombreSala);
        if (sala == null) return null;
        return sala.buscarButaca(codigoAsiento);
    }

    public String getEstadoButaca(String nombreSala, String codigoAsiento) {
        Butaca b = getButaca(nombreSala, codigoAsiento);
        return (b != null) ? b.getEstado() : null;
    }

    public GestorSalas getGestor() {
        return gestor;
    }
}
