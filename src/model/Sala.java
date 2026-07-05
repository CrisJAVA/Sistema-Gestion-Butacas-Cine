// =============================
// Paradigma Orientado a Objetos
// =============================
// Sala representa una sala de cine independiente.
// Contiene un arreglo bidimensional de butacas y expone
// métodos para administrar sus asientos.

package model;

public class Sala {
    private String nombre;
    private Butaca[][] butacas; // Arreglo bidimensional obligatorio
    private int filas;
    private int columnas;

    private static final String[] LETRAS = {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
    };

    public Sala(String nombre, int filas, int columnas) {
        this.nombre = nombre;
        this.filas = filas;
        this.columnas = columnas;
        this.butacas = new Butaca[filas][columnas];
        inicializar();
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Bucles anidados para recorrer el arreglo bidimensional
    // y asignar un código secuencial con letra + número.

    private void inicializar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String codigo = LETRAS[i] + (j + 1);
                butacas[i][j] = new Butaca(i, j, codigo);
            }
        }
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Busca una butaca por su código recorriendo el arreglo.

    public Butaca buscarButaca(String codigo) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (butacas[i][j].getCodigo().equalsIgnoreCase(codigo)) {
                    return butacas[i][j];
                }
            }
        }
        return null;
    }

    public boolean reservarButaca(String codigo, String usuario) {
        Butaca b = buscarButaca(codigo);
        if (b != null && b.puedeReservar()) {
            b.reservar(usuario);
            return true;
        }
        return false;
    }

    public boolean ocuparButaca(String codigo) {
        Butaca b = buscarButaca(codigo);
        if (b != null && b.puedeOcupar()) {
            b.ocupar();
            return true;
        }
        return false;
    }

    public boolean cancelarReserva(String codigo, String usuario) {
        Butaca b = buscarButaca(codigo);
        if (b != null && b.puedeCancelar(usuario)) {
            b.liberar();
            return true;
        }
        return false;
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Recorre el arreglo bidimensional para contar estados.

    public void reiniciar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                butacas[i][j].liberar();
            }
        }
    }

    public int contarDisponibles() {
        int count = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (butacas[i][j].getEstado().equals("Disponible")) {
                    count++;
                }
            }
        }
        return count;
    }

    public int contarReservados() {
        int count = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (butacas[i][j].getEstado().equals("Reservado")) {
                    count++;
                }
            }
        }
        return count;
    }

    public int contarOcupados() {
        int count = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (butacas[i][j].getEstado().equals("Ocupado")) {
                    count++;
                }
            }
        }
        return count;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public Butaca[][] getButacas() {
        return butacas;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
