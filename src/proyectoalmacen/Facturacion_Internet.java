package proyectoalmacen;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.management.StringValueExp;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Facturacion_Internet extends javax.swing.JFrame {
    
     DefaultTableModel TablaModelo = new DefaultTableModel();

    public Facturacion_Internet() {
        initComponents();
        //String[] Apartado = new  String[]{"Nombre","Documento","Pago","cant.Ropa","P.V.T.Ropa","cant.Tenis","P.V.T.Tenis","cant.Suministro","P.V.T.Suministro","Total"};
        String[] Apartado = new  String[]{"Nombre","Documento","Pago"};
        TablaModelo.setColumnIdentifiers(Apartado);
        jTable1.setModel(TablaModelo);
    }
    void Agregar(){
        try {
            String pago = "";
            if(RBoton_CreditoTienda.isSelected()) pago = "Credito";
            else if(RBoton_TarjetaCredito.isSelected()) pago = "Tarjeta de Credito";
            else if(RBoton_TarjetaDebito.isSelected()) pago = "Tarjeta de Debito";
            else{
                throw new Exception("Debe seleccionar al menos un metodo de pago.");
            }
            FacturarCliente(pago, Comprobar_Cliente());
            Boton_CargarCompra.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error"+e);
        }
    }
    
    void FacturarCliente(String pago, boolean cliente){
        try {
            int cropa = Integer.valueOf(Cantidad_Ropa.getText());
            int ctenis = Integer.valueOf(Cantidad_Tenis.getText());
            int csuministro = Integer.valueOf(Cantidad_Suministros.getText());
            int pvtropa = Integer.valueOf(Precio_Ropa.getText());
            int pvttenis = Integer.valueOf(Precio_Tenis.getText());
            int pvtsumin = Integer.valueOf(Precio_Suministros.getText());
            
            
            ArrayList<String> list = new ArrayList<>();
            list.add(Caja_Nombre.getText());
            list.add(Caja_Documento.getText());
            list.add(pago);
            
            if(cropa>0){
                TablaModelo.addColumn("Cant.Ropa");
                TablaModelo.addColumn("PVT.Ropa");
                list.add(String.valueOf(cropa));
                list.add(String.valueOf(pvtropa));
            }
            if(ctenis>0){
                TablaModelo.addColumn("Cant.Tenis");
                TablaModelo.addColumn("PVT.Tenis");
                list.add(String.valueOf(ctenis));
                list.add(String.valueOf(pvttenis));
            }
            if(csuministro>0){
                TablaModelo.addColumn("Cant.Suministro");
                TablaModelo.addColumn("PVT.Suministro");
                list.add(String.valueOf(csuministro));
                list.add(String.valueOf(pvtsumin));
            }
            
            int total = cropa*pvtropa + ctenis*pvttenis + csuministro*pvtsumin;
            if(pago.equals("Credito")){
                total += total*0.12;
                TablaModelo.addColumn("Cuotas");
                list.add(String.valueOf(Cuotas.getSelectedItem()));
            }
            else{
                if(cliente && !pago.equals("Credito")){
                    System.out.println(pago);
                    double descuento;
                    if(pago.equals("Efectivo")) descuento=total+total*0.05;
                    else descuento=total*0.03;
                    total -= descuento;
                    TablaModelo.addColumn("Descuento");
                    list.add(String.valueOf(descuento));
                }
            }
            list.add(String.valueOf(total));
            TablaModelo.addColumn("Total");
            TablaModelo.addRow(list.toArray());
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error:\n" + e);
        }
    }
    
    boolean Comprobar_Cliente(){
        boolean cliente = false;
        try {
            String docuser = Caja_Documento.getText();
            String linea = "",documento;
            FileReader Archivo = new FileReader ("Stores\\Prueba_Cliente.txt");
            BufferedReader Datos = new BufferedReader(Archivo);
            Datos.readLine();
            while (linea != null) {
                documento = Datos.readLine();
                Datos.readLine();
                Datos.readLine();
                linea = Datos.readLine();
                if(documento.equals(docuser)){
                    cliente = true; 
                    break;
                }
           }
            Archivo.close();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error:\n" + e);
        }
        return cliente;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        Boton_Salir = new javax.swing.JButton();
        Boton_VolverMenuFacturacion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Cantidad_Ropa = new javax.swing.JTextField();
        Cantidad_Tenis = new javax.swing.JTextField();
        Cantidad_Suministros = new javax.swing.JTextField();
        Boton_CargarCompra = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Caja_Nombre = new javax.swing.JTextField();
        Caja_Documento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Precio_Ropa = new javax.swing.JTextField();
        Precio_Tenis = new javax.swing.JTextField();
        Precio_Suministros = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Boton_EliminarCompra = new javax.swing.JButton();
        RBoton_TarjetaCredito = new javax.swing.JRadioButton();
        RBoton_TarjetaDebito = new javax.swing.JRadioButton();
        RBoton_CreditoTienda = new javax.swing.JRadioButton();
        Cuotas = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Boton_Salir.setText("SALIR");
        Boton_Salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Boton_SalirMouseClicked(evt);
            }
        });
        jPanel1.add(Boton_Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));

        Boton_VolverMenuFacturacion.setText("VOLVER AL MENU DE FACTURACION");
        Boton_VolverMenuFacturacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Boton_VolverMenuFacturacionMouseClicked(evt);
            }
        });
        jPanel1.add(Boton_VolverMenuFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jTable1.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 910, 160));

        Cantidad_Ropa.setText("0");
        jPanel1.add(Cantidad_Ropa, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 60, 30));

        Cantidad_Tenis.setText("0");
        jPanel1.add(Cantidad_Tenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 60, 30));

        Cantidad_Suministros.setText("0");
        jPanel1.add(Cantidad_Suministros, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, 60, 30));

        Boton_CargarCompra.setText("Cargar Compra");
        Boton_CargarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_CargarCompraActionPerformed(evt);
            }
        });
        jPanel1.add(Boton_CargarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, 170, 40));

        jLabel1.setText("ROPA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 80, 30));

        jLabel2.setText("TENIS");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 80, 30));

        jLabel3.setText("SUMINISTROS");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, -1, 30));
        jPanel1.add(Caja_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 130, 40));

        Caja_Documento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Caja_DocumentoActionPerformed(evt);
            }
        });
        jPanel1.add(Caja_Documento, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 130, 40));

        jLabel4.setText("CANTIDAD");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 60, -1));

        jLabel5.setText("PRECIO");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 100, -1));

        Precio_Ropa.setText("0");
        jPanel1.add(Precio_Ropa, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, 100, 30));

        Precio_Tenis.setText("0");
        jPanel1.add(Precio_Tenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 100, 30));

        Precio_Suministros.setText("0");
        jPanel1.add(Precio_Suministros, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 140, 100, 30));

        jLabel6.setText("NOMBRE");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 130, 30));

        jLabel7.setText("DOCUMENTO");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 130, 30));

        Boton_EliminarCompra.setText("Eliminar Compra");
        jPanel1.add(Boton_EliminarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 170, 40));

        buttonGroup1.add(RBoton_TarjetaCredito);
        RBoton_TarjetaCredito.setText("Tarjeta Credito");
        RBoton_TarjetaCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBoton_TarjetaCreditoActionPerformed(evt);
            }
        });
        jPanel1.add(RBoton_TarjetaCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, -1));

        buttonGroup1.add(RBoton_TarjetaDebito);
        RBoton_TarjetaDebito.setText("Tarjeta Debito");
        RBoton_TarjetaDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBoton_TarjetaDebitoActionPerformed(evt);
            }
        });
        jPanel1.add(RBoton_TarjetaDebito, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, -1));

        buttonGroup1.add(RBoton_CreditoTienda);
        RBoton_CreditoTienda.setText("Credito Tienda");
        RBoton_CreditoTienda.setToolTipText("Credito Tienda");
        RBoton_CreditoTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBoton_CreditoTiendaActionPerformed(evt);
            }
        });
        jPanel1.add(RBoton_CreditoTienda, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, -1));

        Cuotas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "6", "12" }));
        Cuotas.setEnabled(false);
        jPanel1.add(Cuotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, -1, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Boton_SalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_SalirMouseClicked
        try {
            System.exit(0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error:\n" + ex);
        }
    }//GEN-LAST:event_Boton_SalirMouseClicked

    private void Boton_VolverMenuFacturacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_VolverMenuFacturacionMouseClicked
        try {
            Menu_Facturacion Menu_Facturacion = new Menu_Facturacion();
            Menu_Facturacion.setVisible(true);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error:\n" + ex);
        }
    }//GEN-LAST:event_Boton_VolverMenuFacturacionMouseClicked

    private void Boton_CargarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_CargarCompraActionPerformed
        Agregar();
    }//GEN-LAST:event_Boton_CargarCompraActionPerformed

    private void RBoton_CreditoTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBoton_CreditoTiendaActionPerformed
        Cuotas();
    }//GEN-LAST:event_RBoton_CreditoTiendaActionPerformed

    private void RBoton_TarjetaCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBoton_TarjetaCreditoActionPerformed
        Cuotas();
    }//GEN-LAST:event_RBoton_TarjetaCreditoActionPerformed

    private void RBoton_TarjetaDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBoton_TarjetaDebitoActionPerformed
        Cuotas();
    }//GEN-LAST:event_RBoton_TarjetaDebitoActionPerformed

    private void Caja_DocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Caja_DocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Caja_DocumentoActionPerformed

    private void Cuotas(){
        if(RBoton_CreditoTienda.isSelected()){
            Cuotas.setEnabled(true);
        }
        else{
            Cuotas.setEnabled(false);
        }
        Cuotas.setSelectedIndex(0);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Facturacion_Internet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facturacion_Internet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facturacion_Internet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facturacion_Internet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facturacion_Internet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_CargarCompra;
    private javax.swing.JButton Boton_EliminarCompra;
    private javax.swing.JButton Boton_Salir;
    private javax.swing.JButton Boton_VolverMenuFacturacion;
    private javax.swing.JTextField Caja_Documento;
    private javax.swing.JTextField Caja_Nombre;
    private javax.swing.JTextField Cantidad_Ropa;
    private javax.swing.JTextField Cantidad_Suministros;
    private javax.swing.JTextField Cantidad_Tenis;
    private javax.swing.JComboBox<String> Cuotas;
    private javax.swing.JTextField Precio_Ropa;
    private javax.swing.JTextField Precio_Suministros;
    private javax.swing.JTextField Precio_Tenis;
    private javax.swing.JRadioButton RBoton_CreditoTienda;
    private javax.swing.JRadioButton RBoton_TarjetaCredito;
    private javax.swing.JRadioButton RBoton_TarjetaDebito;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
