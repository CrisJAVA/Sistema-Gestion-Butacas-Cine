// =============================
// Paradigma Orientado a Objetos
// =============================
// GestorSalas es el modelo central que administra todas las
// salas de cine. Implementa el patrón Singleton para que
// tanto la ventana de Cliente como la de Administrador
// compartan los mismos datos en memoria.

package model;

import java.util.*;

public class GestorSalas {
    private static GestorSalas instancia;
    private Map<String, Sala> salas;

    private GestorSalas() {
        salas = new LinkedHashMap<>();
        inicializarSalasPorDefecto();
    }

    // =============================
    // Singleton
    // =============================
    // Garantiza una única instancia en toda la aplicación,
    // permitiendo que Cliente y Administrador trabajen
    // sobre los mismos datos sin necesidad de base de datos.

    public static GestorSalas getInstance() {
        if (instancia == null) {
            instancia = new GestorSalas();
        }
        return instancia;
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Crea salas por defecto y pre-asigna algunos estados
    // para demostración visual.

    private void inicializarSalasPorDefecto() {
        crearSala("Sala 1", 6, 8);
        crearSala("Sala 2", 5, 7);
        crearSala("Sala 3", 8, 10);
        crearSala("Sala VIP", 4, 6);

        // Estado de demostración: pre-asignar algunas butacas
        Sala s1 = salas.get("Sala 1");
        s1.getButacas()[0][2].reservar();
        s1.getButacas()[0][3].ocupar();
        s1.getButacas()[1][5].reservar();
        s1.getButacas()[2][1].ocupar();
        s1.getButacas()[2][3].reservar();
        s1.getButacas()[3][4].ocupar();
        s1.getButacas()[4][2].reservar();
        s1.getButacas()[4][7].ocupar();
        s1.getButacas()[5][0].reservar();

        Sala s2 = salas.get("Sala 2");
        s2.getButacas()[0][0].ocupar();
        s2.getButacas()[1][2].reservar();
        s2.getButacas()[2][4].ocupar();
        s2.getButacas()[3][1].reservar();

        Sala s3 = salas.get("Sala 3");
        s3.getButacas()[0][1].reservar();
        s3.getButacas()[1][3].ocupar();
        s3.getButacas()[2][5].reservar();

        Sala svip = salas.get("Sala VIP");
        svip.getButacas()[0][0].reservar();
        svip.getButacas()[0][1].ocupar();
        svip.getButacas()[3][4].reservar();
        svip.getButacas()[3][5].ocupar();
    }

    public boolean crearSala(String nombre, int filas, int columnas) {
        if (salas.containsKey(nombre)) {
            return false;
        }
        salas.put(nombre, new Sala(nombre, filas, columnas));
        return true;
    }

    public boolean eliminarSala(String nombre) {
        if (salas.size() <= 1) {
            return false; // No eliminar la última sala
        }
        return salas.remove(nombre) != null;
    }

    public Sala getSala(String nombre) {
        return salas.get(nombre);
    }

    public List<String> getNombresSalas() {
        return new ArrayList<>(salas.keySet());
    }

    public Collection<Sala> getTodasLasSalas() {
        return salas.values();
    }

    public int getCantidadSalas() {
        return salas.size();
    }
}
