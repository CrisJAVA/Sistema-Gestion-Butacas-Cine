// =============================
// Paradigma Orientado a Objetos
// =============================
// FrmComprobante es un JDialog que muestra un comprobante
// electrónico cada vez que un cliente reserva u ocupa un
// asiento. Presenta la información con formato elegante
// utilizando HTML dentro de un JLabel.

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FrmComprobante extends JDialog {

    public FrmComprobante(JFrame parent, String nombreSala,
                          String codigoAsiento, String estado) {
        super(parent, "Comprobante de Compra", true);

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fmtHora  = DateTimeFormatter.ofPattern("HH:mm:ss");

        // =============================
        // Paradigma Imperativo
        // =============================
        // Construcción del HTML del comprobante mediante
        // concatenación y formato de cadenas.

        String html = "<html>"
            + "<div style='text-align:center; font-family:monospace; padding:15px;'>"
            + "<h1 style='color:#1a237e; margin-bottom:0;'>CINE</h1>"
            + "<h2 style='color:#333; margin-top:0;'>Comprobante</h2>"
            + "<hr style='border:1px solid #1a237e; width:80%;'>"
            + "<br>"
            + "<p style='font-size:14px; margin:5px 0;'>"
            + "<b>Sala:</b> " + nombreSala + "</p>"
            + "<p style='font-size:14px; margin:5px 0;'>"
            + "<b>N\u00famero de asiento:</b></p>"
            + "<p style='font-size:28px; font-weight:bold; "
            + "color:#d32f2f; margin:5px 0;'>"
            + codigoAsiento + "</p>"
            + "<p style='font-size:14px; margin:5px 0;'>"
            + "<b>Estado:</b> " + estado + "</p>"
            + "<p style='font-size:14px; margin:5px 0;'>"
            + "<b>Fecha:</b> " + ahora.format(fmtFecha) + "</p>"
            + "<p style='font-size:14px; margin:5px 0;'>"
            + "<b>Hora:</b> " + ahora.format(fmtHora) + "</p>"
            + "<br>"
            + "<hr style='border:1px solid #1a237e; width:80%;'>"
            + "<p style='font-style:italic; color:#555; font-size:13px;'>"
            + "Gracias por su compra.</p>"
            + "</div></html>";

        JLabel lblContenido = new JLabel(html);
        lblContenido.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.add(lblContenido, BorderLayout.CENTER);

        setContentPane(panel);
        setSize(420, 380);
        setMinimumSize(new Dimension(380, 340));
        setLocationRelativeTo(parent);
        setResizable(false);
    }
}
