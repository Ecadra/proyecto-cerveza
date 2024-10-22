/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;
import proyectoCerveza.Venta;
import proyectoCerveza.Expendio;
import proyectoCerveza.Inventario;
import CRUD.crudGeneralSI;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.SimpleDateFormat;
import javax.swing.*;


/**
 *
 * @author ximen
 */
public class InterfazVenta extends javax.swing.JFrame {
    private crudGeneralSI operacionesCRUD = new crudGeneralSI();
    
    
    /**
     * Creates new form InterfazVenta
     */
    public InterfazVenta() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtId.setText((operacionesCRUD.opMaxID("Venta")+1)+"");
        Date dateE = jdate.getDate();
        cargarTabla();
        cargarInventario();
        cargarExpendio();
    }
    
    private boolean validacion(){
        if(txtCantidad.getText().isEmpty() ||
                (jdate.getDate() == null ) ||
                txtTotal.getText().isEmpty() || 
                cmbInventario.getSelectedIndex() == -1 || 
                cmbExpendio.getSelectedIndex() == -1 ||
                txtId.getText().isEmpty()){
            return false;
        }
        return true;
    }
    
    private void cargarInventario(){
        List<Inventario> listaInventarios = operacionesCRUD.opReadObjetos("Inventario", "", "");
        for(Inventario inventario : listaInventarios){
            cmbInventario.addItem(inventario.getInv_cod()+"");
        }
        
        List<Inventario> listaInventarios1 = operacionesCRUD.opReadObjetos("Inventario", "", "");
        for(Inventario inventario : listaInventarios1){
            cmbInventario1.addItem(inventario.getInv_cod()+"");
        }
        
        List<Inventario> listaInventarios2 = operacionesCRUD.opReadObjetos("Inventario", "", "");
        for(Inventario inventario : listaInventarios2){
            cmbInventario2.addItem(inventario.getInv_cod()+"");
        }
    }
     private void cargarExpendio(){
        List<Expendio> listaExpendios = operacionesCRUD.opReadObjetos("Expendio", "", "");
        for(Expendio expendio : listaExpendios){
            cmbExpendio.addItem(expendio.getExp_nombre());
        }
        
        List<Expendio> listaExpendios1 = operacionesCRUD.opReadObjetos("Expendio", "", "");
        for(Expendio expendio : listaExpendios1){
            cmbExpendio1.addItem(expendio.getExp_nombre());
        }
        
         List<Expendio> listaExpendios2 = operacionesCRUD.opReadObjetos("Expendio", "", "");
        for(Expendio expendio : listaExpendios2){
            cmbExpendio2.addItem(expendio.getExp_nombre());
        }
    }
     
     
    private void cargarTabla() {
        
        tblRegistros.setModel(operacionesCRUD.opBuscar("Venta", "", ""));
        // Aplicar el renderer de fechas a la columna 3 (índice 2)
        tblRegistros.getColumnModel().getColumn(2).setCellRenderer(new DateCellRenderer());
    }
    
     class DateCellRenderer extends DefaultTableCellRenderer {
        private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        @Override
        protected void setValue(Object value) {
            if (value instanceof Date) {
                value = formatter.format((Date) value); // Formatear la fecha
            }
            super.setValue(value); // Asignar el valor formateado a la celda
        }
    }
     
     public boolean validarDatos() {
        // Validar el campo ID (txtId)
        if (txtId.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "El campo ID no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
        }
        try {
        Integer.parseInt(txtId.getText()); // Verificar si el ID es un número entero válido
        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El campo ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
        }

        // Validar el campo Cantidad (txtCantidad)
        if (txtCantidad.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "El campo Cantidad no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
         }
        try {
        Integer.parseInt(txtCantidad.getText()); // Verificar si la cantidad es un número entero válido
        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El campo Cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
         }

        // Validar el campo Total (txtTotal)
        if (txtTotal.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "El campo Total no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
        }
        try {
        Float.parseFloat(txtTotal.getText()); // Verificar si el total es un número flotante válido
        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El campo Total debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
        }

        // Validar el campo de la fecha (jdate)
        Date fechaSeleccionada = jdate.getDate();
        if (fechaSeleccionada == null) {
        JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha válida.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
        }

        // Si todos los datos son válidos, retornamos true
        return true;
        }
     
     
          public boolean validarDatos1() {
        // Validar el campo ID (txtId)
        if (txtId1.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "El campo ID no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
        }
        try {
        Integer.parseInt(txtId1.getText()); // Verificar si el ID es un número entero válido
        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El campo ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
        }

        // Validar el campo Cantidad (txtCantidad)
        if (txtCantidad1.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "El campo Cantidad no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
         }
        try {
        Integer.parseInt(txtCantidad1.getText()); // Verificar si la cantidad es un número entero válido
        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El campo Cantidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
         }

        // Validar el campo Total (txtTotal)
        if (txtTotal1.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "El campo Total no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
        }
        try {
        Float.parseFloat(txtTotal1.getText()); // Verificar si el total es un número flotante válido
        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "El campo Total debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
        }

        // Validar el campo de la fecha (jdate)
        Date fechaSeleccionada = jdate1.getDate();
        if (fechaSeleccionada == null) {
        JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha válida.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
        }

        // Si todos los datos son válidos, retornamos true
        return true;
        }
     
    private void limpiarCampos(){
        txtId.setText((operacionesCRUD.opMaxID("Venta")+1)+"");
        txtCantidad.setText("");
        jdate.setDate(null);
        txtTotal.setText("");
        cmbInventario.setSelectedIndex(-1);
        cmbExpendio.setSelectedIndex(-1);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbdVenta = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jdate = new com.toedter.calendar.JDateChooser();
        txtCantidad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbExpendio = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cmbInventario = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmbInventario1 = new javax.swing.JComboBox<>();
        cmbExpendio1 = new javax.swing.JComboBox<>();
        txtTotal1 = new javax.swing.JTextField();
        jdate1 = new com.toedter.calendar.JDateChooser();
        txtCantidad1 = new javax.swing.JTextField();
        txtId1 = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtId2 = new javax.swing.JTextField();
        txtCantidad2 = new javax.swing.JTextField();
        jdate2 = new com.toedter.calendar.JDateChooser();
        txtTotal2 = new javax.swing.JTextField();
        cmbExpendio2 = new javax.swing.JComboBox<>();
        cmbInventario2 = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cmbFiltro = new javax.swing.JComboBox<>();
        txtCriterio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbdVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdVentaMouseClicked(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos de Venta"));

        jLabel4.setText("Total:");

        jLabel3.setText("Fecha:");

        jLabel2.setText("Cantidad:");

        jLabel1.setText("No. Venta:");

        txtId.setEditable(false);

        jLabel5.setText("Expendio:");

        jLabel6.setText("<html>Código <br>Inventario: </html>");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtId)
                        .addComponent(txtCantidad)
                        .addComponent(jdate, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                        .addComponent(txtTotal)
                        .addComponent(cmbExpendio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cmbExpendio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar Campos");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btnRegistrar)
                .addGap(38, 38, 38)
                .addComponent(btnLimpiar)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnLimpiar))
                .addGap(28, 28, 28))
        );

        tbdVenta.addTab("Registrar", jPanel1);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos Venta:"));

        jLabel7.setText("No Venta:");

        jLabel8.setText("Cantidad:");

        jLabel9.setText("Fecha:");

        jLabel10.setText("Total:");

        jLabel11.setText("Expendio:");

        jLabel12.setText("<html>Código <br> Inventario</html>");

        txtId1.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtId1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtCantidad1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdate1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addComponent(txtTotal1)
                            .addComponent(cmbExpendio1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbInventario1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(35, 35, 35)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbExpendio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbInventario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jdate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnLimpiar1.setText("Limpiar");
        btnLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btnActualizar)
                        .addGap(52, 52, 52)
                        .addComponent(btnLimpiar1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnLimpiar1))
                .addGap(28, 28, 28))
        );

        tbdVenta.addTab("Actualizar", jPanel2);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos de venta:"));

        jLabel13.setText("No.Venta:");

        jLabel14.setText("Cantidad:");

        jLabel15.setText("Fecha:");

        jLabel16.setText("Total:");

        jLabel17.setText("Expendio:");

        jLabel18.setText("<htmL>Código <br> Inventario</html>");

        txtId2.setEditable(false);

        txtCantidad2.setEditable(false);
        txtCantidad2.setToolTipText("");

        jdate2.setEnabled(false);

        txtTotal2.setEditable(false);

        cmbExpendio2.setEnabled(false);

        cmbInventario2.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal2)
                    .addComponent(cmbExpendio2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbInventario2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdate2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad2)
                    .addComponent(txtId2))
                .addGap(16, 16, 16))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(txtId2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jdate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cmbExpendio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbInventario2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar2.setText("Limpiar datos");
        btnLimpiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpiar2)
                .addGap(46, 46, 46))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar2))
                .addGap(12, 12, 12))
        );

        tbdVenta.addTab("Eliminar", jPanel3);

        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
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
        tblRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRegistrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblRegistros);

        btnRegresar.setText("Regresar");

        jLabel19.setText("Filtrar por:");

        jLabel20.setText("Criterio:");

        cmbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fecha" }));
        cmbFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltroActionPerformed(evt);
            }
        });

        txtCriterio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCriterioKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tbdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegresar)
                        .addGap(200, 200, 200)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCriterio)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jLabel20)
                        .addComponent(cmbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tbdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

        try {
            // Obtener la fecha del jdate como Date
            Date fechaSeleccionada = jdate.getDate();
            if (fechaSeleccionada == null) {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha válida.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return; // Salir del método si no hay fecha seleccionada
            }
            
            // Formatear la fecha como String si es necesario
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaString = sdf.format(fechaSeleccionada);
            System.out.println("Fecha seleccionada formateada: " + fechaString);
            
            // Crear la nueva venta usando el String de fecha
            Venta nuevaVenta = new Venta(
                Integer.parseInt(txtId.getText()),
                Integer.parseInt(txtCantidad.getText()),
                fechaString, // Pasar el String de fecha
                Float.parseFloat(txtTotal.getText())
            );

            Inventario inventarioVentaNueva = (Inventario) operacionesCRUD.opBuscarObjeto("Inventario", cmbInventario.getSelectedItem().toString());
            System.out.println("Inventario recuperado mediante opBuscarObjeto :\n" + inventarioVentaNueva.toString());
            Expendio expendioVentaNueva = (Expendio) operacionesCRUD.opBuscarObjeto("Expendio", cmbExpendio.getSelectedItem().toString());
            System.out.println("Expendio recuperado mediante opBuscarObjeto :\n" + expendioVentaNueva.toString());

            nuevaVenta.formVe_inv(inventarioVentaNueva);
            nuevaVenta.formVe_exp(expendioVentaNueva);
            operacionesCRUD.opPersistObjeto("Venta", nuevaVenta);
            
        } catch (NumberFormatException err) {
            System.out.println("Error en la conversión de datos:");
            System.out.println("ID: " + txtId.getText());
            System.out.println("Cantidad: " + txtCantidad.getText());
            System.out.println("Total: " + txtTotal.getText());
            JOptionPane.showMessageDialog(null, 
            "Los datos introducidos no son válidos", 
            "" + err.getMessage(),
            JOptionPane.ERROR_MESSAGE);
        }
    
    cargarTabla();
    limpiarCampos();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tbdVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdVentaMouseClicked
     boolean tablaSeleccionada = tblRegistros.getSelectedRow() != -1;
        System.out.println(tablaSeleccionada); //Mensaje de bandera
        //tabSeleccionada se obtiene mediante el metodo getSelectedIndex, por lo que puede
        //tener valores de 0 a 2, 0 = insertar, 1 = editar, 2 = eliminar
        int tabSeleccionada = tbdVenta.getSelectedIndex(); //Mensaje de bandera
        System.out.println(tabSeleccionada);//Mensaje de bandera
        //En caso de que la tab sea 1 o 2 y que se encuentre seleccionado un registro

        if (JOptionPane.showConfirmDialog(rootPane, """
                                                   Al cambiar de función se perderan los cambios de la función actual
                                                   Esta usted seguro de querer proceder?""", "Confirmación de eliminacion",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if ((tabSeleccionada == 1 || tabSeleccionada == 2) && tablaSeleccionada) {
                //Se activa la tab correspondiente segun la seleccion del usuario mediante un switch
                switch (tabSeleccionada) {
                    case 1 -> {
                        editar(true);
                        eliminar(false);
                        insertar(false);
                        btnRegresar.setEnabled(false);
                    }
                    case 2 -> {
                        eliminar(true);
                        editar(false);
                        insertar(false);
                        btnRegresar.setEnabled(false);
                    }
                    default ->
                        System.out.println("Ocurrio un error inesperado, favor de contactar al desarrollador");
                }
            } else {
                //De lo contrario, se regresa el indice a 0, insersion
                tbdVenta.setSelectedIndex(0);
                insertar(true);
                eliminar(false);
                editar(false);
                btnRegresar.setEnabled(true);
               
                        }
        }

        
    }//GEN-LAST:event_tbdVentaMouseClicked

    private void tblRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRegistrosMouseClicked
        tbdVenta.setSelectedIndex(1);
        editar(true);
        insertar(false);
        eliminar(true);
        int fila = tblRegistros.getSelectedRow();
        Object valor = tblRegistros.getValueAt(fila, 5);
        Object valor2 = tblRegistros.getValueAt(fila, 5);

        //SE RECUPERAN LOS DATOS EN EDITAR
        txtId1.setText(tblRegistros.getValueAt(fila, 0)+ "");
        txtCantidad1.setText(tblRegistros.getValueAt(fila, 1) + "");
        jdate1.setDate((Date)tblRegistros.getValueAt(tblRegistros.getSelectedRow(),2));
        txtTotal1.setText(tblRegistros.getValueAt(fila, 3)+ "");
        cmbExpendio1.setSelectedItem(tblRegistros.getValueAt(fila, 4));
        cmbInventario1.setSelectedItem(String.valueOf(valor));
        

        //SE RECUPERAN LOS DATOS EN ELIMINAR
        //txtId1.setText(tblRegistros.getValueAt(fila, 0) + "");
        txtId2.setText(tblRegistros.getValueAt(fila, 0)+ "");
        txtCantidad2.setText(tblRegistros.getValueAt(fila, 1) + "");
        jdate2.setDate((Date)tblRegistros.getValueAt(tblRegistros.getSelectedRow(),2));
        txtTotal2.setText(tblRegistros.getValueAt(fila, 3)+ "");
        cmbExpendio2.setSelectedItem(tblRegistros.getValueAt(fila, 4));
        cmbInventario2.setSelectedItem(String.valueOf(valor2));
        
        
    }//GEN-LAST:event_tblRegistrosMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    
           if (validarDatos1()) {
        try {
            // Se inicializa un objeto Venta con los datos de la interfaz, excluyendo la relación
            Venta venta = new Venta();
            venta.setId_venta(Integer.parseInt(txtId1.getText()));
            venta.setVen_cantidad(Integer.parseInt(txtCantidad1.getText()));
            
            // Verificamos que la fecha no sea null
            if (jdate1.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Por favor seleccione una fecha válida.",
                        "Error de fecha", JOptionPane.ERROR_MESSAGE);
                return;  // Salimos del método si la fecha es null
            } else {
                venta.setVen_fecha(jdate1.getDate());
            }
            
            venta.setVen_total(Float.parseFloat(txtTotal1.getText()));
            
            // Se inicia el objeto Expendio con la relación
            Expendio expendio = new Expendio();
            expendio.setExp_nombre((String) cmbExpendio1.getSelectedItem());
            expendio.setId_expendio(operacionesCRUD.nameToID("Expendio", expendio.getExp_nombre()));
            expendio.setId_expendio(expendio.getId_expendio());
            venta.dropVe_exp(expendio);
            
            // Se inicia el objeto Inventario y se convierte el valor seleccionado
            Inventario inventario = new Inventario();
            String selectedItem = (String) cmbInventario1.getSelectedItem();
            
            try {
                int invCod = Integer.parseInt(selectedItem);
                inventario.setInv_cod(invCod);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error al convertir el código de inventario: " + e.getMessage(),
                        "Error de formato", JOptionPane.ERROR_MESSAGE);
                return;
            }
            inventario.setInv_cod(inventario.getInv_cod());
            venta.dropVe_inv(inventario);
            operacionesCRUD.opUpdateObjeto("Venta", venta);
            cargarTabla();  // Refrescamos la tabla
            limpiarCampos(); // Limpiamos los campos del formulario
            
        } catch (NumberFormatException err) {
            JOptionPane.showMessageDialog(null, "Los datos introducidos no son válidos",
                    "Error en btnActualizarActionPerformed", JOptionPane.ERROR_MESSAGE);
        } catch (ClassCastException err) {
            JOptionPane.showMessageDialog(null, "Existe un error en la lógica de clases: " + err.getMessage(),
                    "Error en btnActualizarActionPerformed", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException err) {
            JOptionPane.showMessageDialog(null, "El índice de la lista de sedes está fuera de los límites\n"
                    + "Probablemente la lista de marcas no está cargando adecuadamente",
                    "Error en btnActualizarActionPerformed", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    if(JOptionPane.showConfirmDialog(null, "¿Está usted seguro que desea eliminar el registro?",
                "Confirmación de eliminacion",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            try{
                operacionesCRUD.opDeleteObjeto("Venta", Integer.parseInt(txtId2.getText()));
                JOptionPane.showMessageDialog(null, "Se ha eliminado el objeto de manera satisfactoria");
               
                tbdVenta.setSelectedIndex(0);
            }catch(NumberFormatException err){
                JOptionPane.showMessageDialog(null, "El tipo de dato del identificador no es un numero",
                        "Error de formato de numero",JOptionPane.ERROR_MESSAGE);
            }
        }
     cargarTabla();
     limpiarCampos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar2ActionPerformed
    limpiarCampos();
    }//GEN-LAST:event_btnLimpiar2ActionPerformed

    private void btnLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar1ActionPerformed
    limpiarCampos();
    }//GEN-LAST:event_btnLimpiar1ActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
    limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtCriterioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCriterioKeyReleased
    tblRegistros.setModel(operacionesCRUD.opBuscar("Venta", (String)cmbFiltro.getSelectedItem(), txtCriterio.getText()));
    }//GEN-LAST:event_txtCriterioKeyReleased

    private void cmbFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFiltroActionPerformed
    tblRegistros.setModel(operacionesCRUD.opBuscar("Venta", (String)cmbFiltro.getSelectedItem(), txtCriterio.getText()));
    }//GEN-LAST:event_cmbFiltroActionPerformed
    
     public void insertar(boolean activacion) {
        txtId.setEnabled(activacion);
        txtCantidad.setEnabled(activacion);
        jdate.setEnabled(activacion);
        txtTotal.setEnabled(activacion);
        btnRegistrar.setEnabled(activacion);
        btnLimpiar.setEnabled(activacion);
        cmbExpendio.setEnabled(activacion);
        cmbInventario.setEnabled(activacion);
        btnRegresar.setEnabled(!activacion);

    }

    public void eliminar(boolean activacion) {
        btnEliminar.setEnabled(activacion);
        btnLimpiar2.setEnabled(activacion);
        btnRegresar.setEnabled(!activacion);
    }

    public void editar(boolean activacion) {
        //txtId2.setEnabled(activacion);
        txtId1.setEnabled(activacion);
        txtCantidad1.setEnabled(activacion);
        btnActualizar.setEnabled(activacion);
        jdate1.setEnabled(activacion);
        txtTotal1.setEnabled(activacion);
        btnLimpiar1.setEnabled(activacion);
        cmbExpendio1.setEnabled(activacion);
        cmbInventario1.setEnabled(activacion);
        btnLimpiar1.setEnabled(activacion);
        btnRegresar.setEnabled(!activacion);
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
            java.util.logging.Logger.getLogger(InterfazVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiar1;
    private javax.swing.JButton btnLimpiar2;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbExpendio;
    private javax.swing.JComboBox<String> cmbExpendio1;
    private javax.swing.JComboBox<String> cmbExpendio2;
    private javax.swing.JComboBox<String> cmbFiltro;
    private javax.swing.JComboBox<String> cmbInventario;
    private javax.swing.JComboBox<String> cmbInventario1;
    private javax.swing.JComboBox<String> cmbInventario2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdate;
    private com.toedter.calendar.JDateChooser jdate1;
    private com.toedter.calendar.JDateChooser jdate2;
    private javax.swing.JTabbedPane tbdVenta;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidad1;
    private javax.swing.JTextField txtCantidad2;
    private javax.swing.JTextField txtCriterio;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId1;
    private javax.swing.JTextField txtId2;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotal1;
    private javax.swing.JTextField txtTotal2;
    // End of variables declaration//GEN-END:variables
}
