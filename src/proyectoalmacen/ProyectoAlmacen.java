package proyectoalmacen;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ProyectoAlmacen {

    public static void main(String[] args) {
        try {
            Login log = new Login();
            log.setLocationRelativeTo(null);
            log.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error:\n" + ex);
        }
    }
}
