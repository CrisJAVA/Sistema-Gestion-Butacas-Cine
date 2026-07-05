// =============================
// Paradigma Orientado a Objetos
// =============================
// FrmInicio es la ventana de bienvenida del sistema.
// Permite al usuario seleccionar el rol con el que desea
// ingresar: Cliente o Administrador. Una vez seleccionado,
// la ventana se cierra y se abre la correspondiente.

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class FrmInicio extends JFrame {
    private static final Color COLOR_FONDO   = new Color(13, 13, 30);
    private static final Color COLOR_TITULO  = new Color(255, 215, 0);
    private static final Color COLOR_CLIENTE = new Color(25, 118, 210);
    private static final Color COLOR_ADMIN   = new Color(123, 31, 162);
    private static final Color COLOR_SALIR   = new Color(66, 66, 66);

    public FrmInicio() {
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Sistema de Gesti\u00f3n de Butacas de Cine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 480);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(COLOR_FONDO);
    }

    private void inicializarComponentes() {
        // Panel contenedor centrado con GridBagLayout
        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setBackground(COLOR_FONDO);

        // Panel de contenido con BoxLayout vertical
        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setBackground(COLOR_FONDO);
        contenido.setBorder(new EmptyBorder(20, 40, 20, 40));

        // Ícono decorativo
        JLabel lblIcono = new JLabel("\uD83C\uDFAC");
        lblIcono.setFont(new Font("Segoe UI", Font.PLAIN, 56));
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(lblIcono);

        contenido.add(Box.createVerticalStrut(8));

        // Título principal
        JLabel lblTitulo1 = new JLabel("SISTEMA DE GESTI\u00d3N");
        lblTitulo1.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo1.setForeground(COLOR_TITULO);
        lblTitulo1.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo1.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(lblTitulo1);

        JLabel lblTitulo2 = new JLabel("DE BUTACAS DE CINE");
        lblTitulo2.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo2.setForeground(COLOR_TITULO);
        lblTitulo2.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo2.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(lblTitulo2);

        contenido.add(Box.createVerticalStrut(20));

        // Mensaje de bienvenida
        JLabel lblBienvenida = new JLabel("Seleccione el modo de ingreso");
        lblBienvenida.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblBienvenida.setForeground(Color.LIGHT_GRAY);
        lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenido.add(lblBienvenida);

        contenido.add(Box.createVerticalStrut(25));

        // Logo del sistema
        ImageIcon iconoLogoScaled = escalarImagen("src/IMG/admin-logo.png", 70, 70);
        if (iconoLogoScaled != null) {
            JLabel lblLogo = new JLabel(iconoLogoScaled);
            lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
            contenido.add(lblLogo);
            contenido.add(Box.createVerticalStrut(5));
        }

        // Botón Cliente con imagen
        ImageIcon iconoUsrScaled = escalarImagen("src/IMG/usr-logo.png", 28, 28);
        JButton btnCliente = new JButton("Ingresar como Cliente");
        if (iconoUsrScaled != null) btnCliente.setIcon(iconoUsrScaled);
        estilizarBoton(btnCliente, COLOR_CLIENTE, 350, 50, 16);
        btnCliente.addActionListener(e -> abrirCliente());
        contenido.add(btnCliente);

        contenido.add(Box.createVerticalStrut(12));

        // Botón Administrador con imagen
        ImageIcon iconoAdminScaled = escalarImagen("src/IMG/admin-logo.png", 28, 28);
        JButton btnAdmin = new JButton("Ingresar como Administrador");
        if (iconoAdminScaled != null) btnAdmin.setIcon(iconoAdminScaled);
        estilizarBoton(btnAdmin, COLOR_ADMIN, 350, 50, 16);
        btnAdmin.addActionListener(e -> abrirAdministrador());
        contenido.add(btnAdmin);

        contenido.add(Box.createVerticalStrut(30));

        // Botón Salir (sin imagen disponible)
        JButton btnSalir = new JButton("Salir");
        estilizarBoton(btnSalir, COLOR_SALIR, 200, 40, 14);
        btnSalir.addActionListener(e -> System.exit(0));
        contenido.add(btnSalir);

        contenedor.add(contenido);
        add(contenedor);
    }

    private void estilizarBoton(JButton btn, Color fondo,
                                 int ancho, int alto, int tamFuente) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, tamFuente));
        btn.setBackground(fondo);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(ancho, alto));
        btn.setPreferredSize(new Dimension(ancho, alto));
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn.setVerticalTextPosition(SwingConstants.CENTER);
        btn.setIconTextGap(10);
        btn.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(fondo.darker(), 2, true),
            new EmptyBorder(8, 18, 8, 18)
        ));
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

    private void abrirCliente() {
        FrmCliente cliente = new FrmCliente();
        cliente.setVisible(true);
        dispose();
    }

    private void abrirAdministrador() {
        FrmAdministrador admin = new FrmAdministrador();
        admin.setVisible(true);
        dispose();
    }
}
