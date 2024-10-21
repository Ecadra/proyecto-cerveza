/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import CRUD.crudGeneralCEM;
import CRUD.crudIPL;
import javax.swing.JOptionPane;
import proyectoCerveza.Expendio;
import proyectoCerveza.Inventario;
import proyectoCerveza.Presentacion;

/**
 *
 * @author admin
 */
public class InterfazInventario extends javax.swing.JFrame {

    crudIPL opCRUD=new crudIPL();
    boolean actualizarFlag=false;
    crudGeneralCEM opCRUDCEM=new crudGeneralCEM();
    public InterfazInventario() {
        initComponents();
        actualizarTablaInv();
        actualizarTablaExp();
        actualizarTablaPre();
        idMax();
        txtExp.setEditable(false);
        txtPres.setEditable(false);
        txtID.setEnabled(false);
        txtIDAct.setEnabled(false);
        txtIDDel.setEnabled(false);
        txtPrecioDel.setEnabled(false);
        txtCantidadDel.setEnabled(false);
        txtExpDel.setEnabled(false);
        txtPresDel.setEnabled(false);
        
    }

    private void actualizarTablaInv(){
        tblInv.setModel(opCRUD.opBuscar("Inventario", cmbFiltroInv.getSelectedItem().toString(), txtCritInv.getText()));
    }
    private void actualizarTablaExp(){
        
        tblExp.setModel(opCRUDCEM.opBuscar("Expendio",cmbFiltroExp.getSelectedItem().toString(), txtCritExp.getText()));
        System.out.print("\nEl campo seleccionado dentro de la combo es: "+cmbFiltroExp.getSelectedItem().toString());
    }
    private void actualizarTablaPre(){
        tblPres.setModel(opCRUD.opBuscar("Presentacion", cmbFiltroPres.getSelectedItem().toString(), txtCritPres.getText()));
    }
    private void idMax(){
        txtID.setText((opCRUD.opIDMax("Inventario")+1)+"");
       
        
    }
    
    private void cleanFields(){
        
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtExp.setText("");
        txtPres.setText("");
        txtIDAct.setText("");
        txtPrecioAct.setText("");
        txtCantidadAct.setText("");
        txtExpendioAct.setText("");
        txtPresAct.setText("");
        tblInv.clearSelection();
        tblPres.clearSelection();
        tblExp.clearSelection();
        txtIDDel.setText("");
        txtPrecioDel.setText("");
        txtCantidadDel.setText("");
        txtExpDel.setText("");
        txtPresDel.setText("");
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblExp = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPres = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblInv = new javax.swing.JTable();
        PanelInventario = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        txtPrecio = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        cmbExistencia = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtExp = new javax.swing.JTextField();
        txtPres = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtIDAct = new javax.swing.JTextField();
        txtPrecioAct = new javax.swing.JTextField();
        txtCantidadAct = new javax.swing.JTextField();
        cmbExistAct = new javax.swing.JComboBox<>();
        txtExpendioAct = new javax.swing.JTextField();
        txtPresAct = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txtIDDel = new javax.swing.JTextField();
        txtPrecioDel = new javax.swing.JTextField();
        txtCantidadDel = new javax.swing.JTextField();
        cmbExistDel = new javax.swing.JComboBox<>();
        txtExpDel = new javax.swing.JTextField();
        txtPresDel = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtCritInv = new javax.swing.JTextField();
        cmbFiltroInv = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        txtCritPres = new javax.swing.JTextField();
        cmbFiltroPres = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        txtCritExp = new javax.swing.JTextField();
        cmbFiltroExp = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Inventario"));

        tblExp.setModel(new javax.swing.table.DefaultTableModel(
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
        tblExp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblExpMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblExp);

        tblPres.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPresMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblPres);

        tblInv.setModel(new javax.swing.table.DefaultTableModel(
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
        tblInv.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tblInvMouseDragged(evt);
            }
        });
        tblInv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInvMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblInv);

        PanelInventario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PanelInventario.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PanelInventarioStateChanged(evt);
            }
        });

        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        cmbExistencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));

        jLabel1.setText("Precio Unitario");

        jLabel2.setText("Cantidad");

        jLabel3.setText("Existencia");

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel4.setText("# de Inventario");

        btnRegistrar.setText("Crear Registro");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");

        txtExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExpActionPerformed(evt);
            }
        });

        txtPres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPresActionPerformed(evt);
            }
        });

        jLabel5.setText("Presentación");

        jLabel6.setText("Expendio");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButton2)
                .addGap(56, 56, 56))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32))
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio)
                    .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(txtCantidad))
                .addGap(94, 94, 94)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExp)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPres))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(jButton2))
                .addGap(22, 22, 22))
        );

        PanelInventario.addTab("Create", jPanel5);

        txtIDAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActActionPerformed(evt);
            }
        });

        txtCantidadAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActActionPerformed(evt);
            }
        });

        cmbExistAct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));

        jLabel7.setText("# de Inventario");

        jLabel8.setText("Precio Unitario");

        jLabel9.setText("Cantidad");

        jLabel10.setText("Existencia ");

        jLabel11.setText("Expendio");

        jLabel12.setText("Presentación");

        btnUpdate.setText("Actualizar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jButton3.setText("Cancelar");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIDAct))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrecioAct, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbExistAct, 0, 82, Short.MAX_VALUE)
                            .addComponent(txtCantidadAct))))
                .addGap(72, 72, 72)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPresAct)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtExpendioAct))
                .addGap(82, 82, 82))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(btnUpdate)
                .addGap(45, 45, 45)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecioAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExpendioAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCantidadAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPresAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbExistAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        PanelInventario.addTab("Update", jPanel6);

        txtPrecioDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioDelActionPerformed(evt);
            }
        });

        txtCantidadDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadDelActionPerformed(evt);
            }
        });

        cmbExistDel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));

        jLabel13.setText("# de Inventario");

        jLabel14.setText("Precio unitario");

        jLabel15.setText("Cantidad");

        jLabel16.setText("Expendio");

        jLabel17.setText("Presentación");

        jLabel18.setText("Existencia");

        btnEliminar.setText("Delete");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jButton4.setText("Cancel");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtPrecioDel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtIDDel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 26, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addGap(0, 20, Short.MAX_VALUE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbExistDel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidadDel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtPresDel, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                        .addComponent(txtExpDel))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))))
                        .addGap(0, 68, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(29, 29, 29))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(9, 9, 9)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecioDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addGap(7, 7, 7)
                        .addComponent(txtPresDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidadDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbExistDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(jButton4))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        PanelInventario.addTab("Delete", jPanel7);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Buscar un Inventario"));

        txtCritInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCritInvActionPerformed(evt);
            }
        });
        txtCritInv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCritInvKeyReleased(evt);
            }
        });

        cmbFiltroInv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo de Inventario", "Precio Unitario", "Cantidad" }));
        cmbFiltroInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltroInvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbFiltroInv, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCritInv, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCritInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFiltroInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Buscar una Presentación"));

        txtCritPres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCritPresActionPerformed(evt);
            }
        });
        txtCritPres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCritPresKeyReleased(evt);
            }
        });

        cmbFiltroPres.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo de la presentacion" }));
        cmbFiltroPres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltroPresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addComponent(cmbFiltroPres, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCritPres, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCritPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFiltroPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Buscar un Expendio"));

        txtCritExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCritExpActionPerformed(evt);
            }
        });
        txtCritExp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCritExpKeyReleased(evt);
            }
        });

        cmbFiltroExp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Numero de telefono" }));
        cmbFiltroExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltroExpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbFiltroExp, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCritExp, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCritExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFiltroExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(PanelInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PanelInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
       
        boolean existencia, bandera=false;
        int IDnuevo=Integer.parseInt(txtID.getText());;
        float precio=0.0f;
        int cantidad=0;
        String expendio = txtExp.getText();
        String presentacion=txtPres.getText();
        
        
      
        try{
            
            precio=Float.parseFloat(txtPrecio.getText());
            
            if(precio<=0.0f){
                JOptionPane.showMessageDialog(null,"El precio debe de ser mayor a cero","Error",JOptionPane.ERROR_MESSAGE);
                bandera=true;
            }
            
        }catch(NumberFormatException err){
            JOptionPane.showMessageDialog(null,"El precio debe de ser un número","Error",JOptionPane.ERROR_MESSAGE);
            bandera=true;
        }
        
         try{
            
            cantidad=Integer.parseInt(txtCantidad.getText());
             if(cantidad<=0){
                JOptionPane.showMessageDialog(null,"La cantidad debe de ser mayor a cero","Error",JOptionPane.ERROR_MESSAGE);
                bandera=true;
            }
            
        }catch(NumberFormatException err){
            JOptionPane.showMessageDialog(null,"La cantidad debe de ser un número","Error",JOptionPane.ERROR_MESSAGE);
            bandera=true;
        }

        if(cmbExistencia.getSelectedItem().equals("Si")){
            existencia=true;
        }else{
            existencia=false;
        }
        
        
        if(expendio.equals("")){
            bandera=true;
            JOptionPane.showMessageDialog(null,"Debe de seleccionar un expendio","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        if(presentacion.equals("")){
            bandera=true;
            JOptionPane.showMessageDialog(null,"Debe de seleccionar una presentación","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        if(bandera==false){
        Inventario nuevoInventario =new Inventario(IDnuevo,precio,cantidad,existencia);
        Expendio nuevoExpendio=(Expendio)opCRUD.opBuscarObjeto("Expendio",expendio);
        System.out.print("\nSe ha encotrado dentro de la interfaz el expendio\n"+nuevoExpendio);
        Presentacion nuevaPresentacion=(Presentacion)opCRUD.opBuscarObjeto("Presentacion", presentacion);
        System.out.print("\nSe ha encotrado dentro de la interfaz la presentacion\n"+nuevaPresentacion);
        nuevoInventario.formInv_exp(nuevoExpendio);
        nuevoInventario.formInv_pre(nuevaPresentacion);
        
        opCRUD.opCreate("Inventario", nuevoInventario);
        
        actualizarTablaInv();
        cleanFields();
        idMax();
        }
        
         
      
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tblExpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblExpMouseClicked
       
       txtExp.setText(tblExp.getValueAt(tblExp.getSelectedRow(), 0).toString());
       
       if(actualizarFlag==true){
           
            txtExpendioAct.setText(tblExp.getValueAt(tblExp.getSelectedRow(), 0).toString());
           
       }
    }//GEN-LAST:event_tblExpMouseClicked

    private void tblPresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPresMouseClicked
        System.out.print("\n"+tblPres.getValueAt(tblPres.getSelectedRow(), 0).toString());
        txtPres.setText(tblPres.getValueAt(tblPres.getSelectedRow(), 0).toString());
        
       if(actualizarFlag==true){
           
            
            txtPresAct.setText(tblPres.getValueAt(tblPres.getSelectedRow(), 0).toString());
       } 
    }//GEN-LAST:event_tblPresMouseClicked

    private void txtExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExpActionPerformed

    private void txtPresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPresActionPerformed

    private void txtIDActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActActionPerformed

    private void txtCantidadActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        
        boolean existencia, bandera=false;
        int IDnuevo=0;
        float precio=0.0f;
        int cantidad=0;
        String expendio = txtExpendioAct.getText();
        String presentacion=txtPresAct.getText();
        
       
        try{
            IDnuevo=Integer.parseInt(txtIDAct.getText());
        }catch(NumberFormatException err){
            JOptionPane.showMessageDialog(null,"Debe de seleccionar un registro a editar","Error",JOptionPane.ERROR_MESSAGE);
             bandera=true;
        }
        try{
            
            precio=Float.parseFloat(txtPrecioAct.getText());
            if(precio<=0){
                JOptionPane.showMessageDialog(null,"El precio debe de ser mayor a cero","Error",JOptionPane.ERROR_MESSAGE);
                bandera=true;
            }
            
        }catch(NumberFormatException err){
            JOptionPane.showMessageDialog(null,"El precio debe de ser un número","Error",JOptionPane.ERROR_MESSAGE);
            bandera=true;
        }
        
         try{
            
            cantidad=Integer.parseInt(txtCantidadAct.getText());
            if(cantidad<=0){
                JOptionPane.showMessageDialog(null,"La cantidad debe de ser mayor a cero","Error",JOptionPane.ERROR_MESSAGE);
                bandera=true;
            }
            
        }catch(NumberFormatException err){
            JOptionPane.showMessageDialog(null,"La cantidad debe de ser un número","Error",JOptionPane.ERROR_MESSAGE);
            bandera=true;
        }

        if(cmbExistAct.getSelectedItem().equals("Si")){
            existencia=true;
        }else{
            existencia=false;
        }
        
        
        if(expendio.equals("")){
            bandera=true;
            JOptionPane.showMessageDialog(null,"Debe de seleccionar un expendio","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        if(presentacion.equals("")){
            bandera=true;
            JOptionPane.showMessageDialog(null,"Debe de seleccionar una presentación","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        if(bandera==false){
            System.out.print("\nInicio de la acualización\n");
            Inventario invNuevo=new Inventario(IDnuevo,precio,cantidad,existencia);
            Expendio nuevoExpendio=(Expendio)opCRUD.opBuscarObjeto("Expendio",expendio);
            invNuevo.formInv_exp(nuevoExpendio);
            Presentacion nuevaPresentacion=(Presentacion)opCRUD.opBuscarObjeto("Presentacion", presentacion);
            invNuevo.formInv_pre(nuevaPresentacion);
            Inventario invViejoAux=(Inventario)opCRUD.opBuscarObjeto("Inventario",tblInv.getValueAt(tblInv.getSelectedRow(), 0).toString());
            
            Inventario auxiliar=(Inventario)opCRUD.opBuscarObjeto("Inventario", txtIDDel.getText());
            if(JOptionPane.showConfirmDialog(null,"Usted esta seguro de actualizar estos registros registro?\n"+
                    "Registro Actual\n "+invViejoAux+
                    "\nRegsitro nuevo\n"+invNuevo,"Confirmar eliminación", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            {
            opCRUD.opUpdate("Inventario", invNuevo);
            actualizarTablaInv();
            cleanFields();
            idMax();
            actualizarFlag=false;
            }
            
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblInvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInvMouseClicked
        actualizarFlag=true;
        txtIDAct.setText(tblInv.getValueAt(tblInv.getSelectedRow(), 0).toString());
        txtPrecioAct.setText(tblInv.getValueAt(tblInv.getSelectedRow(), 3).toString());
        txtCantidadAct.setText(tblInv.getValueAt(tblInv.getSelectedRow(), 1).toString());
        
        if((boolean)tblInv.getValueAt(tblInv.getSelectedRow(),4)==true){
        cmbExistAct.setSelectedItem("Si");
        }else{
            cmbExistAct.setSelectedItem("No");
        }
        
        txtExpendioAct.setText(tblInv.getValueAt(tblInv.getSelectedRow(), 5).toString());
        txtPresAct.setText(tblInv.getValueAt(tblInv.getSelectedRow(), 2).toString());
        
        
        txtIDDel.setText(tblInv.getValueAt(tblInv.getSelectedRow(), 0).toString());
        txtPrecioDel.setText(tblInv.getValueAt(tblInv.getSelectedRow(), 3).toString());
        txtCantidadDel.setText(tblInv.getValueAt(tblInv.getSelectedRow(), 1).toString());
        
        if((boolean)tblInv.getValueAt(tblInv.getSelectedRow(),4)==true){
        cmbExistDel.setSelectedItem("Si");
        }else{
            cmbExistDel.setSelectedItem("No");
        }
        
        txtExpDel.setText(tblInv.getValueAt(tblInv.getSelectedRow(), 5).toString());
        txtPresDel.setText(tblInv.getValueAt(tblInv.getSelectedRow(), 2).toString());
        
    }//GEN-LAST:event_tblInvMouseClicked

    private void txtCantidadDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadDelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadDelActionPerformed

    private void txtPrecioDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioDelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioDelActionPerformed

    private void txtCritPresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCritPresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCritPresActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(txtIDDel.getText().equals("")){
            
            JOptionPane.showMessageDialog(null,"Debe de seleccionar un registro para eliminarlo","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            Inventario auxiliar=(Inventario)opCRUD.opBuscarObjeto("Inventario", txtIDDel.getText());
            if(JOptionPane.showConfirmDialog(null,"Usted esta seguro de elminar el registro?\n"+ auxiliar,
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            {
            opCRUD.opDelte("Inventario", txtIDDel.getText());
            actualizarTablaInv();
            cleanFields();
            idMax();
            actualizarFlag=false;
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cmbFiltroInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFiltroInvActionPerformed
        actualizarTablaInv();
    }//GEN-LAST:event_cmbFiltroInvActionPerformed

    private void txtCritExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCritExpActionPerformed
        
    }//GEN-LAST:event_txtCritExpActionPerformed

    private void cmbFiltroExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFiltroExpActionPerformed
        actualizarTablaExp();
    }//GEN-LAST:event_cmbFiltroExpActionPerformed

    private void cmbFiltroPresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFiltroPresActionPerformed
        actualizarTablaInv();
    }//GEN-LAST:event_cmbFiltroPresActionPerformed

    private void txtCritInvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCritInvKeyReleased
        actualizarTablaInv();
    }//GEN-LAST:event_txtCritInvKeyReleased

    private void txtCritExpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCritExpKeyReleased
       actualizarTablaExp();
    }//GEN-LAST:event_txtCritExpKeyReleased

    private void txtCritPresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCritPresKeyReleased
       actualizarTablaPre();
    }//GEN-LAST:event_txtCritPresKeyReleased

    private void PanelInventarioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PanelInventarioStateChanged
       cleanFields();
    }//GEN-LAST:event_PanelInventarioStateChanged

    private void tblInvMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInvMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_tblInvMouseDragged

    private void txtCritInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCritInvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCritInvActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazInventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane PanelInventario;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbExistAct;
    private javax.swing.JComboBox<String> cmbExistDel;
    private javax.swing.JComboBox<String> cmbExistencia;
    private javax.swing.JComboBox<String> cmbFiltroExp;
    private javax.swing.JComboBox<String> cmbFiltroInv;
    private javax.swing.JComboBox<String> cmbFiltroPres;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tblExp;
    private javax.swing.JTable tblInv;
    private javax.swing.JTable tblPres;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidadAct;
    private javax.swing.JTextField txtCantidadDel;
    private javax.swing.JTextField txtCritExp;
    private javax.swing.JTextField txtCritInv;
    private javax.swing.JTextField txtCritPres;
    private javax.swing.JTextField txtExp;
    private javax.swing.JTextField txtExpDel;
    private javax.swing.JTextField txtExpendioAct;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDAct;
    private javax.swing.JTextField txtIDDel;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPrecioAct;
    private javax.swing.JTextField txtPrecioDel;
    private javax.swing.JTextField txtPres;
    private javax.swing.JTextField txtPresAct;
    private javax.swing.JTextField txtPresDel;
    // End of variables declaration//GEN-END:variables
}
