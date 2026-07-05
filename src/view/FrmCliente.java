// =============================
// Paradigma Orientado a Objetos
// =============================
// FrmCliente es la ventana principal del módulo Cliente.
// Extiende JFrame y permite visualizar las salas, seleccionar
// asientos y realizar operaciones de reserva, ocupación y
// cancelación. Se actualiza automáticamente para reflejar
// los cambios realizados desde el módulo Administrador.

package view;

import controller.ClienteController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import model.GestorSalas;
import model.Sala;
import view.FrmInicio;

public class FrmCliente extends JFrame {
    private ClienteController controlador;
    private GestorSalas gestor;

    // Colores modernos
    private static final Color COLOR_DISPONIBLE = new Color(76, 175, 80);
    private static final Color COLOR_RESERVADO  = new Color(255, 152, 0);
    private static final Color COLOR_OCUPADO    = new Color(244, 67, 54);
    private static final Color COLOR_SELECCION  = new Color(33, 150, 243);
    private static final Color COLOR_FONDO      = new Color(245, 245, 245);
    private static final Color COLOR_PANTALLA   = new Color(66, 66, 66);
    private static final Color COLOR_CABECERA   = new Color(25, 25, 25);

    private JComboBox<String> cmbSalas;
    private JPanel panelButacas;
    private JButton[][] botonesButacas;
    private JLabel lblAsientoSeleccionado;
    private JLabel lblEstadoAsiento;
    private JButton btnReservar;
    private JButton btnOcupar;
    private JButton btnCancelar;

    private ImageIcon iconoDisponible;
    private ImageIcon iconoReservado;
    private ImageIcon iconoOcupado;

    private String salaActual;
    private String asientoSeleccionado;
    private int filasActuales;
    private int columnasActuales;
    private boolean actualizandoComboBox;

    private static final int MAX_FILAS    = 10;
    private static final int MAX_COLUMNAS = 12;

    public FrmCliente() {
        this.controlador = new ClienteController();
        this.gestor = GestorSalas.getInstance();
        this.botonesButacas = new JButton[MAX_FILAS][MAX_COLUMNAS];

        configurarVentana();
        inicializarComponentes();
        cargarImagenes();

        // =============================
        // Timer de sincronización
        // =============================
        // Refresca automáticamente la interfaz cada 2 segundos
        // para que los cambios del Administrador se vean en
        // tiempo real sin necesidad de interacción del usuario.
        // Se usa refrescarVisual() en lugar de actualizarSala()
        // para no interferir con la selección actual del usuario.
        Timer timer = new Timer(2000, e -> refrescarVisual());
        timer.start();
    }

    // =============================
    // Paradigma Modular
    // =============================
    // Métodos pequeños con responsabilidad única.

    private void configurarVentana() {
        setTitle("Cliente - Sistema de Gestión de Butacas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new BorderLayout(0, 0));
    }

    private void inicializarComponentes() {
        add(crearPanelCabecera(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
        add(crearPanelInferior(), BorderLayout.SOUTH);
    }

    private JPanel crearPanelCabecera() {
        JPanel panel = new JPanel();
        panel.setBackground(COLOR_CABECERA);
        panel.setBorder(new EmptyBorder(12, 15, 12, 15));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("SISTEMA DE GESTI\u00d3N DE BUTACAS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);

        panel.add(Box.createVerticalStrut(4));

        JLabel lblSubtitulo = new JLabel("M\u00f3dulo Cliente");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitulo.setForeground(new Color(180, 180, 180));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblSubtitulo);

        return panel;
    }

    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(new EmptyBorder(10, 15, 5, 15));
        panel.setBackground(COLOR_FONDO);

        // Selector de sala
        JPanel panelSelector = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        panelSelector.setBackground(COLOR_FONDO);

        JLabel lblSala = new JLabel("Sala:");
        lblSala.setFont(new Font("Segoe UI", Font.BOLD, 14));

        cmbSalas = new JComboBox<>();
        cmbSalas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbSalas.setPreferredSize(new Dimension(200, 28));
        actualizarListaSalas();

        cmbSalas.addActionListener(e -> {
            if (actualizandoComboBox) return;
            String nuevaSala = (String) cmbSalas.getSelectedItem();
            if (nuevaSala != null && !nuevaSala.equals(salaActual)) {
                salaActual = nuevaSala;
                Sala sala = gestor.getSala(salaActual);
                if (sala != null) {
                    filasActuales = sala.getFilas();
                    columnasActuales = sala.getColumnas();
                }
                asientoSeleccionado = null;
                actualizarSala();
            }
        });

        panelSelector.add(lblSala);
        panelSelector.add(cmbSalas);

        // Área de pantalla + butacas
        JPanel panelSala = new JPanel(new BorderLayout(0, 5));
        panelSala.setBackground(COLOR_FONDO);

        // Simulación de pantalla de cine
        JPanel pantalla = new JPanel();
        pantalla.setBackground(COLOR_PANTALLA);
        pantalla.setPreferredSize(new Dimension(700, 45));
        pantalla.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
        JLabel lblPantalla = new JLabel("P A N T A L L A");
        lblPantalla.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblPantalla.setForeground(new Color(200, 200, 200));
        pantalla.add(lblPantalla);
        panelSala.add(pantalla, BorderLayout.NORTH);

        // Cuadrícula de butacas
        panelButacas = new JPanel(new GridLayout(MAX_FILAS, MAX_COLUMNAS, 5, 5));
        panelButacas.setBackground(COLOR_FONDO);
        panelButacas.setBorder(BorderFactory.createTitledBorder("Butacas"));

        for (int i = 0; i < MAX_FILAS; i++) {
            for (int j = 0; j < MAX_COLUMNAS; j++) {
                JButton btn = new JButton();
                btn.setFont(new Font("Segoe UI", Font.BOLD, 11));
                btn.setForeground(Color.WHITE);
                btn.setFocusPainted(false);
                btn.setContentAreaFilled(false);
                btn.setBorderPainted(false);
                btn.setOpaque(false);
                btn.setHorizontalTextPosition(SwingConstants.CENTER);
                btn.setVerticalTextPosition(SwingConstants.CENTER);
                btn.setPreferredSize(new Dimension(60, 50));
                btn.setVisible(false);

                final int fila = i;
                final int columna = j;
                btn.addActionListener(e -> {
                    Sala sala = gestor.getSala(salaActual);
                    if (sala != null && fila < sala.getFilas() && columna < sala.getColumnas()) {
                        asientoSeleccionado = sala.getButacas()[fila][columna].getCodigo();
                        actualizarInfoAsiento();
                    }
                });

                botonesButacas[i][j] = btn;
                panelButacas.add(btn);
            }
        }

        panelSala.add(panelButacas, BorderLayout.CENTER);

        panel.add(panelSelector, BorderLayout.NORTH);
        panel.add(panelSala, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(new EmptyBorder(5, 15, 12, 15));

        // Panel de información del asiento seleccionado
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelInfo.setBackground(COLOR_FONDO);

        lblAsientoSeleccionado = new JLabel("Asiento: ---");
        lblAsientoSeleccionado.setFont(new Font("Segoe UI", Font.BOLD, 15));

        lblEstadoAsiento = new JLabel("Estado: ---");
        lblEstadoAsiento.setFont(new Font("Segoe UI", Font.BOLD, 15));

        panelInfo.add(lblAsientoSeleccionado);
        panelInfo.add(lblEstadoAsiento);

        // Panel de botones de acción
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelBotones.setBackground(COLOR_FONDO);

        btnReservar = new JButton("Reservar");
        btnOcupar   = new JButton("Ocupar");
        btnCancelar = new JButton("Cancelar Reserva");

        estilizarBoton(btnReservar, COLOR_DISPONIBLE, Color.WHITE);
        estilizarBoton(btnOcupar, COLOR_OCUPADO, Color.WHITE);
        estilizarBoton(btnCancelar, COLOR_RESERVADO, Color.BLACK);

        btnReservar.setEnabled(false);
        btnOcupar.setEnabled(false);
        btnCancelar.setEnabled(false);

        // =============================
        // Eventos (ActionListener)
        // =============================

        btnReservar.addActionListener(e -> ejecutarReservar());
        btnOcupar.addActionListener(e -> ejecutarOcupar());
        btnCancelar.addActionListener(e -> ejecutarCancelar());

        panelBotones.add(btnReservar);
        panelBotones.add(btnOcupar);
        panelBotones.add(btnCancelar);

        // Panel de leyenda
        JPanel panelLeyenda = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        panelLeyenda.setBackground(COLOR_FONDO);

        panelLeyenda.add(crearEtiquetaLeyenda("Disponible", COLOR_DISPONIBLE, Color.WHITE));
        panelLeyenda.add(crearEtiquetaLeyenda("Reservado", COLOR_RESERVADO, Color.BLACK));
        panelLeyenda.add(crearEtiquetaLeyenda("Ocupado", COLOR_OCUPADO, Color.WHITE));

        panel.add(panelInfo);
        panel.add(panelBotones);
        panel.add(new JSeparator());
        panel.add(panelLeyenda);

        // Botón para volver al menú principal
        JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        panelVolver.setBackground(COLOR_FONDO);
        JButton btnVolver = new JButton("\u2190 Volver al Men\u00fa Principal");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnVolver.setBackground(new Color(80, 80, 80));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(new LineBorder(new Color(80, 80, 80).darker(), 1, true));
        btnVolver.setPreferredSize(new Dimension(200, 30));
        btnVolver.addActionListener(e -> volverAlMenu());
        panelVolver.add(btnVolver);
        panel.add(panelVolver);

        return panel;
    }

    private void volverAlMenu() {
        FrmInicio inicio = new FrmInicio();
        inicio.setVisible(true);
        dispose();
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Recorre los botones de la cuadrícula y actualiza su
    // color, texto y visibilidad según el estado actual de
    // las butacas en el modelo.

    // =============================
    // Paradigma Imperativo
    // =============================
    // Actualiza la lista de salas y luego refresca la
    // visualización completa de la cuadrícula.

    public void actualizarSala() {
        String seleccionAnterior = (String) cmbSalas.getSelectedItem();
        actualizarListaSalas();

        if (seleccionAnterior != null && gestor.getSala(seleccionAnterior) != null) {
            cmbSalas.setSelectedItem(seleccionAnterior);
        }

        salaActual = (String) cmbSalas.getSelectedItem();
        if (salaActual == null) return;

        Sala sala = gestor.getSala(salaActual);
        if (sala == null) return;

        filasActuales = sala.getFilas();
        columnasActuales = sala.getColumnas();

        refrescarVisual();
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Refresca únicamente la apariencia de los botones de
    // butacas sin modificar la lista de salas ni la selección
    // del usuario. Esto evita que el Timer borre la selección
    // actual mientras el usuario decide qué acción realizar.

    private void refrescarVisual() {
        if (salaActual == null) return;
        Sala sala = gestor.getSala(salaActual);
        if (sala == null) return;

        filasActuales = sala.getFilas();
        columnasActuales = sala.getColumnas();

        for (int i = 0; i < MAX_FILAS; i++) {
            for (int j = 0; j < MAX_COLUMNAS; j++) {
                JButton btn = botonesButacas[i][j];
                if (i < filasActuales && j < columnasActuales) {
                    btn.setVisible(true);
                    String estado = sala.getButacas()[i][j].getEstado();
                    String codigo = sala.getButacas()[i][j].getCodigo();
                    btn.setText(codigo);

                    switch (estado) {
                        case "Disponible":
                            btn.setIcon(iconoDisponible);
                            break;
                        case "Reservado":
                            btn.setIcon(iconoReservado);
                            break;
                        case "Ocupado":
                            btn.setIcon(iconoOcupado);
                            break;
                    }

                    if (asientoSeleccionado != null && codigo.equals(asientoSeleccionado)) {
                        btn.setBorder(new LineBorder(COLOR_SELECCION, 3));
                    } else {
                        btn.setBorder(null);
                    }
                } else {
                    btn.setVisible(false);
                }
            }
        }

        actualizarInfoAsiento();
    }

    private void cargarImagenes() {
        int ancho = 58;
        int alto  = 48;
        iconoDisponible = escalarImagen("src/IMG/asiento_disponible.png", ancho, alto);
        iconoReservado  = escalarImagen("src/IMG/asiento_reservado.png", ancho, alto);
        iconoOcupado    = escalarImagen("src/IMG/asiento_ocupado.png", ancho, alto);
    }

    private ImageIcon escalarImagen(String ruta, int ancho, int alto) {
        try {
            ImageIcon icono = new ImageIcon(ruta);
            Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            return null;
        }
    }

    private void actualizarInfoAsiento() {
        if (asientoSeleccionado == null || salaActual == null) {
            lblAsientoSeleccionado.setText("Asiento: ---");
            lblEstadoAsiento.setText("Estado: ---");
            btnReservar.setEnabled(false);
            btnOcupar.setEnabled(false);
            btnCancelar.setEnabled(false);
            return;
        }

        String estado = controlador.getEstadoButaca(salaActual, asientoSeleccionado);
        lblAsientoSeleccionado.setText("Asiento: " + asientoSeleccionado);

        if (estado != null) {
            lblEstadoAsiento.setText("Estado: " + estado);
            btnReservar.setEnabled(estado.equals("Disponible"));
            btnOcupar.setEnabled(!estado.equals("Ocupado"));
            btnCancelar.setEnabled(estado.equals("Reservado"));
        } else {
            lblEstadoAsiento.setText("Estado: ---");
            btnReservar.setEnabled(false);
            btnOcupar.setEnabled(false);
            btnCancelar.setEnabled(false);
        }
    }

    private void ejecutarReservar() {
        if (asientoSeleccionado == null || salaActual == null) return;

        boolean exito = controlador.reservarButaca(salaActual, asientoSeleccionado, "Cliente");
        if (exito) {
            JOptionPane.showMessageDialog(this,
                "Reserva realizada con \u00e9xito.",
                "Reserva", JOptionPane.INFORMATION_MESSAGE);
            mostrarComprobante();
        } else {
            JOptionPane.showMessageDialog(this,
                "No se pudo reservar el asiento. Verifique que est\u00e9 disponible.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        actualizarSala();
    }

    private void ejecutarOcupar() {
        if (asientoSeleccionado == null || salaActual == null) return;

        boolean exito = controlador.ocuparButaca(salaActual, asientoSeleccionado, "Cliente");
        if (exito) {
            JOptionPane.showMessageDialog(this,
                "Asiento ocupado con \u00e9xito.",
                "Ocupar", JOptionPane.INFORMATION_MESSAGE);
            mostrarComprobante();
        } else {
            JOptionPane.showMessageDialog(this,
                "Este asiento fue reservado por otro usuario y no puede ser ocupado desde esta cuenta.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        actualizarSala();
    }

    private void ejecutarCancelar() {
        if (asientoSeleccionado == null || salaActual == null) return;

        boolean exito = controlador.cancelarReserva(salaActual, asientoSeleccionado, "Cliente");
        if (exito) {
            JOptionPane.showMessageDialog(this,
                "Reserva cancelada con \u00e9xito.",
                "Cancelar Reserva", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "No se puede cancelar. Este asiento no fue reservado por usted.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        actualizarSala();
    }

    // =============================
    // Paradigma Imperativo
    // =============================
    // Consulta el estado actual de la butaca en el modelo
    // después de realizar la operación, garantizando que el
    // comprobante refleje siempre el valor real.

    private void mostrarComprobante() {
        String estado = controlador.getEstadoButaca(salaActual, asientoSeleccionado);
        if (estado == null) estado = "Desconocido";
        FrmComprobante comp = new FrmComprobante(
            this, salaActual, asientoSeleccionado, estado);
        comp.setVisible(true);
    }

    private void actualizarListaSalas() {
        actualizandoComboBox = true;
        try {
            String seleccionado = (cmbSalas.getSelectedItem() != null)
                ? (String) cmbSalas.getSelectedItem() : null;

            cmbSalas.removeAllItems();
            for (String nombre : gestor.getNombresSalas()) {
                cmbSalas.addItem(nombre);
            }

            if (seleccionado != null && gestor.getSala(seleccionado) != null) {
                cmbSalas.setSelectedItem(seleccionado);
            } else if (cmbSalas.getItemCount() > 0) {
                cmbSalas.setSelectedIndex(0);
            }

            salaActual = (String) cmbSalas.getSelectedItem();
            if (salaActual != null) {
                Sala sala = gestor.getSala(salaActual);
                if (sala != null) {
                    filasActuales = sala.getFilas();
                    columnasActuales = sala.getColumnas();
                }
            }
        } finally {
            actualizandoComboBox = false;
        }
    }

    private void estilizarBoton(JButton btn, Color fondo, Color texto) {
        btn.setBackground(fondo);
        btn.setForeground(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(new LineBorder(fondo.darker(), 1, true));
        btn.setPreferredSize(new Dimension(150, 35));
    }

    private JLabel crearEtiquetaLeyenda(String texto, Color fondo, Color textoColor) {
        JLabel lbl = new JLabel("  " + texto + "  ");
        lbl.setOpaque(true);
        lbl.setBackground(fondo);
        lbl.setForeground(textoColor);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setBorder(new LineBorder(fondo.darker(), 1, true));
        return lbl;
    }
}
