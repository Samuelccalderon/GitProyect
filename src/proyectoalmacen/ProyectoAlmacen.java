package proyectoalmacen;
import javax.swing.JOptionPane;

public class ProyectoAlmacen {
    public static void main(String[] args) {
        try {
            Login log = new Login();
            log.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error:\n" + ex);
        }
    }
}
