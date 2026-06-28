// =============================
// Paradigma Orientado a Objetos
// =============================
// La clase Butaca representa una entidad del mundo real
// con atributos (estado) y comportamientos (reservar, liberar, etc.),
// aplicando encapsulamiento mediante atributos privados y métodos públicos.

package modelo;

public class Butaca {
    private int fila;
    private int columna;
    private String estado; // "Libre", "Reservado" o "Ocupado"

    public Butaca(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.estado = "Libre";
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Los métodos siguientes modifican el estado del objeto
    // mediante instrucciones secuenciales y condicionales.

    public void reservar() {
        if (this.estado.equals("Libre")) {
            this.estado = "Reservado";
        }
    }

    public void ocupar() {
        if (this.estado.equals("Libre")) {
            this.estado = "Ocupado";
        }
    }

    public void liberar() {
        this.estado = "Libre";
    }

    public String getEstado() {
        return this.estado;
    }

    public String mostrarEstado() {
        return "Butaca [" + this.fila + "," + this.columna + "]: " + this.estado;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
