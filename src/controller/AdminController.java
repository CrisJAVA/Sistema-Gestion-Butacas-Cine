// =============================
// Paradigma Orientado a Objetos
// =============================
// AdminController extiende SalaController y agrega las operaciones
// exclusivas del administrador: crear salas, eliminar salas,
// reiniciar salas y obtener estadísticas.

package controller;

import model.Sala;

public class AdminController extends SalaController {

    public boolean crearSala(String nombre, int filas, int columnas) {
        if (nombre == null || nombre.trim().isEmpty()) return false;
        if (filas < 2 || filas > 10) return false;
        if (columnas < 2 || columnas > 12) return false;
        return gestor.crearSala(nombre.trim(), filas, columnas);
    }

    public boolean eliminarSala(String nombre) {
        return gestor.eliminarSala(nombre);
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Secuencia: obtener sala, validar, reiniciar.

    public void reiniciarSala(String nombre) {
        Sala sala = gestor.getSala(nombre);
        if (sala != null) {
            sala.reiniciar();
        }
    }

    public int contarDisponibles(String nombre) {
        Sala sala = gestor.getSala(nombre);
        return (sala != null) ? sala.contarDisponibles() : 0;
    }

    public int contarReservados(String nombre) {
        Sala sala = gestor.getSala(nombre);
        return (sala != null) ? sala.contarReservados() : 0;
    }

    public int contarOcupados(String nombre) {
        Sala sala = gestor.getSala(nombre);
        return (sala != null) ? sala.contarOcupados() : 0;
    }
}
