// =============================
// Paradigma Orientado a Objetos
// =============================
// SalaCine es una clase del modelo que contiene la lógica de negocio.
// Gestiona un arreglo bidimensional de objetos Butaca y expone
// métodos para manipular el estado de la sala.

package modelo;

public class SalaCine {
    private Butaca[][] butacas; // Arreglo bidimensional obligatorio
    private int filas;
    private int columnas;

    public SalaCine(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.butacas = new Butaca[filas][columnas];
        inicializarSala();
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Se utilizan bucles anidados (for) para recorrer el arreglo
    // bidimensional e inicializar cada posición con una nueva Butaca.

    private void inicializarSala() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                butacas[i][j] = new Butaca(i, j);
            }
        }

        // Estado inicial de demostración: algunas butacas
        // se predefinen como Reservado u Ocupado para visualizar
        // los diferentes colores en la interfaz.
        butacas[0][2].reservar();
        butacas[0][3].ocupar();
        butacas[1][5].reservar();
        butacas[2][1].ocupar();
        butacas[2][3].reservar();
        butacas[3][4].ocupar();
        butacas[4][2].reservar();
        butacas[4][7].ocupar();
        butacas[5][0].reservar();
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Construye una representación textual de la sala recorriendo
    // el arreglo bidimensional con bucles anidados.

    public String mostrarSala() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- SALA DE CINE ---\n");
        sb.append("  ");
        for (int j = 0; j < columnas; j++) {
            sb.append(" C" + (j + 1));
        }
        sb.append("\n");
        for (int i = 0; i < filas; i++) {
            sb.append("F" + (i + 1) + " ");
            for (int j = 0; j < columnas; j++) {
                String est = butacas[i][j].getEstado();
                if (est.equals("Libre")) {
                    sb.append("[L] ");
                } else if (est.equals("Reservado")) {
                    sb.append("[R] ");
                } else {
                    sb.append("[O] ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public boolean reservarButaca(int fila, int columna) {
        if (!validarPosicion(fila, columna)) {
            return false;
        }
        Butaca b = butacas[fila][columna];
        if (b.getEstado().equals("Libre")) {
            b.reservar();
            return true;
        }
        return false;
    }

    public boolean cancelarReserva(int fila, int columna) {
        if (!validarPosicion(fila, columna)) {
            return false;
        }
        Butaca b = butacas[fila][columna];
        if (b.getEstado().equals("Reservado")) {
            b.liberar();
            return true;
        }
        return false;
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Recorre el arreglo bidimensional con un contador para
    // determinar cuántas butacas están libres.

    public int contarButacasLibres() {
        int count = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (butacas[i][j].getEstado().equals("Libre")) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean validarPosicion(int fila, int columna) {
        return fila >= 0 && fila < this.filas && columna >= 0 && columna < this.columnas;
    }

    public void reiniciarSala() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                butacas[i][j].liberar();
            }
        }
    }

    // Getters
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
