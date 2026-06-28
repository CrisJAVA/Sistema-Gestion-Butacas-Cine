// =============================
// Paradigma Orientado a Objetos
// =============================
// FrmSalaCine es la clase Vista del patrón MVC.
// Extiende JFrame y contiene todos los componentes gráficos.
// Se encarga de la presentación visual y de capturar las
// interacciones del usuario, delegando la lógica al controlador.

package vista;

import controlador.SalaController;
import modelo.SalaCine;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FrmSalaCine extends JFrame {
    private SalaController controlador;
    private SalaCine modelo;

    // Colores para los estados de las butacas
    private static final Color COLOR_LIBRE     = new Color(76, 175, 80);
    private static final Color COLOR_RESERVADO = new Color(255, 193, 7);
    private static final Color COLOR_OCUPADO   = new Color(244, 67, 54);
    private static final Color COLOR_FONDO     = new Color(240, 240, 240);

    // Dimensiones de la sala
    private static final int FILAS    = 6;
    private static final int COLUMNAS = 8;

    // Componentes de la interfaz
    private JButton[][] botonesButacas; // Misma dimensión que la sala
    private JSpinner spinnerFila;
    private JSpinner spinnerColumna;

    public FrmSalaCine(SalaController controlador, SalaCine modelo) {
        this.controlador = controlador;
        this.modelo = modelo;
        this.botonesButacas = new JButton[FILAS][COLUMNAS];

        configurarVentana();
        inicializarComponentes();
        actualizarSala();
    }

    // =============================
    // Paradigma Modular
    // =============================
    // Cada responsabilidad se encapsula en un método separado
    // (configurarVentana, inicializarComponentes, etc.)
    // para facilitar la lectura, el mantenimiento y la reutilización.

    private void configurarVentana() {
        setTitle("Sistema de Gestión de Butacas de Cine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        // Panel superior con el título
        JPanel panelTitulo = crearPanelTitulo();
        add(panelTitulo, BorderLayout.NORTH);

        // Panel central con la cuadrícula de butacas
        JPanel panelSala = crearPanelSala();
        add(panelSala, BorderLayout.CENTER);

        // Panel inferior con los controles
        JPanel panelControles = crearPanelControles();
        add(panelControles, BorderLayout.SOUTH);
    }

    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(33, 33, 33));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN DE BUTACAS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Sala de Cine - " + FILAS + " filas x " + COLUMNAS + " columnas");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitulo.setForeground(Color.LIGHT_GRAY);
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblSubtitulo);

        return panel;
    }

    private JPanel crearPanelSala() {
        JPanel panel = new JPanel(new GridLayout(FILAS, COLUMNAS, 4, 4));
        panel.setBorder(BorderFactory.createTitledBorder("Butacas"));
        panel.setBackground(COLOR_FONDO);

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                JButton btn = new JButton("F" + (i + 1) + "C" + (j + 1));
                btn.setFont(new Font("Segoe UI", Font.BOLD, 10));

                // =============================
                // Paradigma Imperativo
                // =============================
                // Almacenamos la posición como propiedades del botón
                // para recuperarla en el evento de clic.
                final int fila = i;
                final int columna = j;

                // Cada botón tiene un ActionListener individual
                btn.addActionListener(e -> {
                    spinnerFila.setValue(fila + 1);
                    spinnerColumna.setValue(columna + 1);
                });

                botonesButacas[i][j] = btn;
                panel.add(btn);
            }
        }

        return panel;
    }

    private JPanel crearPanelControles() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(5, 5));
        panelPrincipal.setBorder(BorderFactory.createTitledBorder("Controles"));
        panelPrincipal.setBackground(COLOR_FONDO);

        // Panel de selección de posición (fila y columna)
        JPanel panelSeleccion = new JPanel();
        panelSeleccion.setBackground(COLOR_FONDO);

        JLabel lblFila = new JLabel("Fila:");
        lblFila.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        spinnerFila = new JSpinner(new SpinnerNumberModel(1, 1, FILAS, 1));
        spinnerFila.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblColumna = new JLabel("Columna:");
        lblColumna.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        spinnerColumna = new JSpinner(new SpinnerNumberModel(1, 1, COLUMNAS, 1));
        spinnerColumna.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelSeleccion.add(lblFila);
        panelSeleccion.add(spinnerFila);
        panelSeleccion.add(lblColumna);
        panelSeleccion.add(spinnerColumna);

        // Panel de botones de acción
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(COLOR_FONDO);

        JButton btnReservar       = new JButton("Reservar");
        JButton btnCancelar       = new JButton("Cancelar Reserva");
        JButton btnContarLibres   = new JButton("Contar Libres");
        JButton btnMostrarSala    = new JButton("Mostrar Sala");
        JButton btnReiniciar      = new JButton("Reiniciar Sala");
        JButton btnSalir          = new JButton("Salir");

        // =============================
        // Eventos (ActionListener)
        // =============================
        // Todos los botones utilizan ActionListener para manejar
        // las interacciones del usuario.

        btnReservar.addActionListener(e -> {
            int fila    = (int) spinnerFila.getValue() - 1;
            int columna = (int) spinnerColumna.getValue() - 1;
            controlador.reservarButaca(fila, columna);
        });

        btnCancelar.addActionListener(e -> {
            int fila    = (int) spinnerFila.getValue() - 1;
            int columna = (int) spinnerColumna.getValue() - 1;
            controlador.cancelarReserva(fila, columna);
        });

        btnContarLibres.addActionListener(e -> {
            controlador.contarButacasLibres();
        });

        btnMostrarSala.addActionListener(e -> {
            controlador.mostrarSala();
        });

        btnReiniciar.addActionListener(e -> {
            controlador.reiniciarSala();
        });

        btnSalir.addActionListener(e -> {
            controlador.salir();
        });

        panelBotones.add(btnReservar);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnContarLibres);
        panelBotones.add(btnMostrarSala);
        panelBotones.add(btnReiniciar);
        panelBotones.add(btnSalir);

        // Panel de leyenda de colores
        JPanel panelLeyenda = new JPanel();
        panelLeyenda.setBackground(COLOR_FONDO);

        JLabel lblLibre = new JLabel("  Libre  ");
        lblLibre.setOpaque(true);
        lblLibre.setBackground(COLOR_LIBRE);
        lblLibre.setForeground(Color.WHITE);
        lblLibre.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JLabel lblReservado = new JLabel("  Reservado  ");
        lblReservado.setOpaque(true);
        lblReservado.setBackground(COLOR_RESERVADO);
        lblReservado.setForeground(Color.BLACK);
        lblReservado.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JLabel lblOcupado = new JLabel("  Ocupado  ");
        lblOcupado.setOpaque(true);
        lblOcupado.setBackground(COLOR_OCUPADO);
        lblOcupado.setForeground(Color.WHITE);
        lblOcupado.setFont(new Font("Segoe UI", Font.BOLD, 12));

        panelLeyenda.add(new JLabel("Leyenda: "));
        panelLeyenda.add(lblLibre);
        panelLeyenda.add(lblReservado);
        panelLeyenda.add(lblOcupado);

        panelPrincipal.add(panelSeleccion, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(panelLeyenda, BorderLayout.SOUTH);

        return panelPrincipal;
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Recorre el arreglo bidimensional de botones y actualiza
    // el color y texto de cada uno según el estado de la butaca
    // correspondiente en el modelo.

    public void actualizarSala() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                JButton btn = botonesButacas[i][j];
                String estado = modelo.getButacas()[i][j].getEstado();

                switch (estado) {
                    case "Libre":
                        btn.setBackground(COLOR_LIBRE);
                        btn.setForeground(Color.WHITE);
                        btn.setText("F" + (i + 1) + "C" + (j + 1));
                        break;
                    case "Reservado":
                        btn.setBackground(COLOR_RESERVADO);
                        btn.setForeground(Color.BLACK);
                        btn.setText("R");
                        break;
                    case "Ocupado":
                        btn.setBackground(COLOR_OCUPADO);
                        btn.setForeground(Color.WHITE);
                        btn.setText("O");
                        break;
                }
            }
        }
    }
}
