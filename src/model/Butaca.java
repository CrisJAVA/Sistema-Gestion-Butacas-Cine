// =============================
// Paradigma Orientado a Objetos
// =============================
// Butaca representa un asiento individual de la sala de cine.
// Encapsula su posición (fila, columna), un código visible para el
// usuario (ej: A1, B3) y su estado actual (Disponible, Reservado,
// Ocupado). Expone métodos de negocio para cambiar de estado.

package model;

public class Butaca {
    private int fila;
    private int columna;
    private String codigo; // Identificador visible: A1, B3, C5, etc.
    private String estado; // "Disponible", "Reservado", "Ocupado"
    private String reservadoPor; // Usuario que reservó

    public Butaca(int fila, int columna, String codigo) {
        this.fila = fila;
        this.columna = columna;
        this.codigo = codigo;
        this.estado = "Disponible";
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Cada método modifica el estado mediante instrucciones
    // condicionales y secuenciales.

    public void reservar() {
        if (this.estado.equals("Disponible")) {
            this.estado = "Reservado";
            this.reservadoPor = "Sistema";
        }
    }

    public void reservar(String usuario) {
        if (this.estado.equals("Disponible")) {
            this.estado = "Reservado";
            this.reservadoPor = usuario;
        }
    }

    public void ocupar() {
        // Puede ocuparse si está Disponible o Reservado
        if (!this.estado.equals("Ocupado")) {
            this.estado = "Ocupado";
        }
    }

    public void liberar() {
        this.estado = "Disponible";
        this.reservadoPor = null;
    }

    public boolean puedeReservar() {
        return this.estado.equals("Disponible");
    }

    public boolean puedeOcupar() {
        return !this.estado.equals("Ocupado");
    }

    public boolean puedeCancelar(String usuario) {
        return this.estado.equals("Reservado")
            && this.reservadoPor != null
            && this.reservadoPor.equals(usuario);
    }

    // Getters
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getEstado() {
        return estado;
    }

    public String getReservadoPor() {
        return reservadoPor;
    }
}
