/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectoCerveza.Pedido;
import CRUD.crudPREG;
import CRUD.crudIPL;
import java.text.SimpleDateFormat;
import proyectoCerveza.Expendio;
import proyectoCerveza.Presentacion;
import proyectoCerveza.Inventario;


import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ADM
 */
public class InterfazPedido extends javax.swing.JFrame {
    
    private crudPREG operacionesCRUD = new crudPREG();
    
    
    public InterfazPedido() throws Exception {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarExpendios();
        cargarPresentaciones();
        txtCodigo.setText((operacionesCRUD.opMaxID("Pedido")+1)+"");
        Date fechaOrden = dateFechaOrden.getDate();
        Date fechaDespacho = dateFechaDespacho.getDate();
        tblPedido.setModel(operacionesCRUD.opBuscar("Pedido", "", ""));
    }
    
    private void cargarExpendios(){
        List<Expendio> listaExpendios = operacionesCRUD.opReadObjetos("Expendio", "", "");
        for(Expendio expendio : listaExpendios){
            cmb_Expendio.addItem(expendio.getExp_nombre());
        }
    }
    
    private void cargarPresentaciones(){
        List<Presentacion> listaPresentaciones = operacionesCRUD.opReadObjetos("Presentacion", "", "");
        for(Presentacion presentacion : listaPresentaciones){
//            cmb_Presentacion.addItem(presentacion.getPre_cer().getCer_nombre()
//                    +  " " + presentacion.getPre_env().getTipo_envase() + " - " + 
//                    presentacion.getPre_env().getEnvase_capacidad() + " ml");
              cmb_Presentacion.addItem(presentacion.getPre_cod()+"");
        }
    }

    private void actualizarTabla(){
        tblPedido.setModel(operacionesCRUD.opBuscar("Pedido", (String)cmbAtributoPedido.getSelectedItem(), txtBusquedaPedido.getText()));
    }
    
    public void mensajeAdvertencia(String mensaje, String titulo){
        JOptionPane.showMessageDialog(null,mensaje,titulo,0);
    }
    public void mensajeInformativo(String mensaje, String titulo){
        JOptionPane.showInternalMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void limpiarPedido(){
        cmb_Expendio.setSelectedIndex(-1);
        cmb_Presentacion.setSelectedIndex(-1);
        spinnerCantidad.setValue(0);
        txtTotal.setText("");
        txtSubtotal.setText("");
        txtIVA.setText("");
        dateFechaOrden.setDate(null);
        dateFechaDespacho.setDate(null);
    }
    
    public void activarPedido(boolean activado){
        btnRegistrar.setEnabled(activado);
        cmb_Expendio.setEnabled(activado);
        cmb_Presentacion.setEnabled(activado);
        txtCodigo.setEnabled(activado);
        spinnerCantidad.setEnabled(activado);
        dateFechaOrden.setEnabled(activado);
        dateFechaDespacho.setEnabled(activado);
        txtSubtotal.setEnabled(activado);
        txtBusquedaPedido.setEnabled(!activado);
        txtIVA.setEnabled(activado);
        txtTotal.setEnabled(activado);
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
    
    public boolean validarDatos(){
        // Validación del código de la cerveza
        if (txtCodigo.getText().equals("") || 
                txtCodigo.getText().equals("Ingrese el codigo del pedido")) {
            mensajeAdvertencia("El código es inválido.\n"
                    + "Favor de verificar que:\n"
                    + "-> No contenga acentos\n"
                    + "-> No contenga caracteres especiales\n"
                    + "-> Haya registrado correctamente el código del pedido\n", "Código inválido");
            return false;
        }
        // Validación de la cantidad de cervezas dentro del pedido
        if (spinnerCantidad.getValue().equals(0)) {
            mensajeAdvertencia("La cantidad del pedido.\n"
                    + "Favor de verificar que:\n"
                    + "-> Sea un dato numérico\n"
                    + "-> No contenga caracteres especiales\n"
                    + "-> Haya registrado correctamente la cantidad\n", "Cantidad inválida");
            return false;
        }
        
        if (txtSubtotal.getText().equals("") || 
                txtSubtotal.getText().equals("Ingrese el subtotal")) {
            mensajeAdvertencia("El subtotal del pedido.\n"
                    + "Favor de verificar que:\n"
                    + "-> Sea un dato numérico\n"
                    + "-> No contenga caracteres especiales\n"
                    + "-> Haya registrado correctamente la cantidad\n", "Subtotal inválido");
            return false;
        }
        return true;
    }
    
    
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDatosPedido = new javax.swing.JPanel();
        lbl_Codigo = new javax.swing.JLabel();
        lbl_Cantidad = new javax.swing.JLabel();
        lblFechaOrden = new javax.swing.JLabel();
        dateFechaOrden = new com.toedter.calendar.JDateChooser();
        btnActualizar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        lblFechaDespacho = new javax.swing.JLabel();
        dateFechaDespacho = new com.toedter.calendar.JDateChooser();
        cmb_Expendio = new javax.swing.JComboBox<>();
        lbl_Cantidad1 = new javax.swing.JLabel();
        cmb_Presentacion = new javax.swing.JComboBox<>();
        txtCodigo = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        lblSubtotal = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtIVA = new javax.swing.JTextField();
        lblIVA = new javax.swing.JLabel();
        spinnerCantidad = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        btnCancelarPedido = new javax.swing.JButton();
        btnNewPedido = new javax.swing.JButton();
        pnlRegistros = new javax.swing.JPanel();
        lblBusquedaPedido = new javax.swing.JLabel();
        txtBusquedaPedido = new javax.swing.JTextField();
        lblAtributoPedido = new javax.swing.JLabel();
        cmbAtributoPedido = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedido = new javax.swing.JTable();
        btnInicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnlDatosPedido.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos Pedido"));

        lbl_Codigo.setText("Código:");

        lbl_Cantidad.setText("Expendio:");

        lblFechaOrden.setText("Fecha Orden:");

        dateFechaOrden.setEnabled(false);

        btnActualizar.setText("Actualizar");
        btnActualizar.setEnabled(false);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.setEnabled(false);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.setEnabled(false);
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar Campos");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        lblFechaDespacho.setText("Fecha Despacho:");

        dateFechaDespacho.setEnabled(false);

        cmb_Expendio.setEnabled(false);
        cmb_Expendio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_ExpendioActionPerformed(evt);
            }
        });

        lbl_Cantidad1.setText("Presentación:");

        cmb_Presentacion.setEnabled(false);
        cmb_Presentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_PresentacionActionPerformed(evt);
            }
        });

        txtCodigo.setEditable(false);
        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigo.setEnabled(false);

        lblCantidad.setText("Cantidad cerveza:");

        lblTotal.setText("Total:");

        txtTotal.setEditable(false);
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setEnabled(false);

        lblSubtotal.setText("Subtotal:");

        txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSubtotal.setEnabled(false);
        txtSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubtotalActionPerformed(evt);
            }
        });
        txtSubtotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSubtotalKeyReleased(evt);
            }
        });

        txtIVA.setEditable(false);
        txtIVA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIVA.setEnabled(false);

        lblIVA.setText("IVA:");

        spinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100000, 1));
        spinnerCantidad.setEnabled(false);

        jLabel1.setText("Datos de la presentacion");

        javax.swing.GroupLayout pnlDatosPedidoLayout = new javax.swing.GroupLayout(pnlDatosPedido);
        pnlDatosPedido.setLayout(pnlDatosPedidoLayout);
        pnlDatosPedidoLayout.setHorizontalGroup(
            pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosPedidoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaDespacho)
                    .addComponent(lblFechaOrden, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_Codigo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_Cantidad1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_Cantidad, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dateFechaOrden, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                    .addComponent(cmb_Presentacion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmb_Expendio, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateFechaDespacho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDatosPedidoLayout.createSequentialGroup()
                        .addComponent(lblCantidad)
                        .addGap(18, 18, 18)
                        .addComponent(spinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatosPedidoLayout.createSequentialGroup()
                        .addComponent(lblSubtotal)
                        .addGap(18, 18, 18)
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatosPedidoLayout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatosPedidoLayout.createSequentialGroup()
                        .addComponent(lblIVA)
                        .addGap(18, 18, 18)
                        .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(72, 72, 72)
                .addGroup(pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        pnlDatosPedidoLayout.setVerticalGroup(
            pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_Expendio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Cantidad)
                    .addComponent(lblCantidad)
                    .addComponent(spinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosPedidoLayout.createSequentialGroup()
                        .addComponent(btnRegistrar)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Eliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar))
                    .addGroup(pnlDatosPedidoLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_Presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Cantidad1)
                            .addComponent(lblTotal)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDatosPedidoLayout.createSequentialGroup()
                                .addGroup(pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblSubtotal)
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIVA)))
                            .addGroup(pnlDatosPedidoLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(14, 14, 14)
                                .addGroup(pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_Codigo))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateFechaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaOrden))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDatosPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateFechaDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaDespacho))))
                .addGap(18, 44, Short.MAX_VALUE))
        );

        btnCancelarPedido.setText("Cancelar");
        btnCancelarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPedidoActionPerformed(evt);
            }
        });

        btnNewPedido.setText("Nuevo Pedido");
        btnNewPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPedidoActionPerformed(evt);
            }
        });

        pnlRegistros.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Registros Pedido"));

        lblBusquedaPedido.setText("Búsqueda");

        txtBusquedaPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaPedidoKeyReleased(evt);
            }
        });

        lblAtributoPedido.setText("Atributo:");

        cmbAtributoPedido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo", "Cantidad", "Fecha de orden", "Fecha de despacho", "Total", "Subtotal", "IVA", " " }));
        cmbAtributoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAtributoPedidoActionPerformed(evt);
            }
        });

        tblPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        jScrollPane1.setViewportView(tblPedido);

        javax.swing.GroupLayout pnlRegistrosLayout = new javax.swing.GroupLayout(pnlRegistros);
        pnlRegistros.setLayout(pnlRegistrosLayout);
        pnlRegistrosLayout.setHorizontalGroup(
            pnlRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBusquedaPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusquedaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(lblAtributoPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbAtributoPedido, 0, 374, Short.MAX_VALUE)
                .addGap(148, 148, 148))
            .addGroup(pnlRegistrosLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnlRegistrosLayout.setVerticalGroup(
            pnlRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusquedaPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAtributoPedido)
                    .addComponent(cmbAtributoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBusquedaPedido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnInicio.setText("Regresar al Menu");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(722, Short.MAX_VALUE)
                .addComponent(btnCancelarPedido)
                .addGap(27, 27, 27)
                .addComponent(btnNewPedido)
                .addGap(53, 53, 53))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRegistros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDatosPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInicio)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDatosPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelarPedido)
                    .addComponent(btnNewPedido))
                .addGap(18, 18, 18)
                .addComponent(pnlRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if(validarDatos()){//SI LOS DATOS REGISTRADOS SON VALIDOS
            try {
                // Obtener la fecha del jdate como Date
                Date fechaOrden = dateFechaOrden.getDate();
                Date fechaDespacho = dateFechaDespacho.getDate();
                // Formatear la fecha como String
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fechaOrdenS = sdf.format(fechaOrden);
                String fechaDespachoS = sdf.format(fechaDespacho);

                // Crear la nueva venta usando el String de fecha
                Pedido nuevoPedido = new Pedido(
                    Integer.parseInt(txtCodigo.getText()),
                        Integer.parseInt(spinnerCantidad.getValue().toString()),
                        fechaOrdenS,
                        fechaDespachoS,
                        Float.parseFloat(txtTotal.getText()),
                        Float.parseFloat(txtSubtotal.getText()),
                        Float.parseFloat(txtIVA.getText())             
                );

                
                Expendio expendioSeleccionado = (Expendio) operacionesCRUD.opBuscarObjeto("Expendio",
                        operacionesCRUD.nameToID("Expendio", cmb_Expendio.getSelectedItem().toString()));         
                System.out.println("Expendio recuperado mediante opBuscarObjeto :\n" + expendioSeleccionado.toString());
                
                Presentacion presentacionSeleccionada = (Presentacion) operacionesCRUD.opBuscarObjeto("Presentacion",
                        Integer.parseInt(cmb_Presentacion.getSelectedItem().toString()));
                System.out.println("Presentacion recuperada mediante opBuscarObjeto :\n" + presentacionSeleccionada.toString());

                nuevoPedido.formPed_exp(expendioSeleccionado);
                nuevoPedido.formPed_pre(presentacionSeleccionada);
//                nuevoPedido.formPed_pre(presentacionSeleccionada);
                operacionesCRUD.opPersistObjeto("Pedido", nuevoPedido);

            } catch (NumberFormatException err) {
                JOptionPane.showMessageDialog(null, "Los datos introducidos no son válidos",
                        "Error al registrar el pedido",
                        JOptionPane.ERROR_MESSAGE);
            }
        }else{
            mensajeAdvertencia("Registro no realizado","Error de captura de datos");
        }
        limpiarPedido();
        activarPedido(false);
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarPedido();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
    
            
    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    
        
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCancelarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPedidoActionPerformed
        btnNewPedido.setEnabled(true);
        tblPedido.clearSelection(); 
    }//GEN-LAST:event_btnCancelarPedidoActionPerformed

    private void btnNewPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPedidoActionPerformed
        activarPedido(true);
        limpiarPedido();
        txtCodigo.setText((operacionesCRUD.opMaxID("Pedido")+1)+"");
    }//GEN-LAST:event_btnNewPedidoActionPerformed

    private void txtBusquedaPedidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaPedidoKeyReleased
        actualizarTabla();
    }//GEN-LAST:event_txtBusquedaPedidoKeyReleased

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
    try{
           Home inicio = new Home();
            inicio.setVisible(true);
            // Cerrar la ventana
            dispose();  
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ocurrió un error",
                    "Error",0);

        }
    }//GEN-LAST:event_btnInicioActionPerformed

    private void cmb_ExpendioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_ExpendioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_ExpendioActionPerformed

    private void cmb_PresentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_PresentacionActionPerformed
       
    }//GEN-LAST:event_cmb_PresentacionActionPerformed

    private void txtSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubtotalActionPerformed

    private void txtSubtotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSubtotalKeyReleased
   
        try {
            // Obtener el valor ingresado en txtSubtotal y convertirlo a número
            float subtotal = Float.parseFloat(txtSubtotal.getText());

            // Calcular el IVA (16% del subtotal)
            float iva = subtotal * 0.16f;

            // Calcular el total (subtotal + iva)
            float total = subtotal + iva;

            // Establecer los valores calculados en los campos correspondientes
            txtIVA.setText(String.format("%.2f", iva)); // Mostrar IVA con 2 decimales
            txtTotal.setText(String.format("%.2f", total)); // Mostrar Total con 2 decimales
        } catch (NumberFormatException ex) {
            txtSubtotal.setText("");
            txtIVA.setText("");
            txtTotal.setText("");
        }
    }//GEN-LAST:event_txtSubtotalKeyReleased

    private void cmbAtributoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAtributoPedidoActionPerformed
        actualizarTabla();
    }//GEN-LAST:event_cmbAtributoPedidoActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new InterfazPedido().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(InterfazPedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelarPedido;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNewPedido;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JComboBox<String> cmbAtributoPedido;
    private javax.swing.JComboBox<String> cmb_Expendio;
    private javax.swing.JComboBox<String> cmb_Presentacion;
    private com.toedter.calendar.JDateChooser dateFechaDespacho;
    private com.toedter.calendar.JDateChooser dateFechaOrden;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAtributoPedido;
    private javax.swing.JLabel lblBusquedaPedido;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblFechaDespacho;
    private javax.swing.JLabel lblFechaOrden;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lbl_Cantidad;
    private javax.swing.JLabel lbl_Cantidad1;
    private javax.swing.JLabel lbl_Codigo;
    private javax.swing.JPanel pnlDatosPedido;
    private javax.swing.JPanel pnlRegistros;
    private javax.swing.JSpinner spinnerCantidad;
    private javax.swing.JTable tblPedido;
    private javax.swing.JTextField txtBusquedaPedido;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
