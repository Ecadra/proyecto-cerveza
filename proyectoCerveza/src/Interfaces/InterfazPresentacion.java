/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import CRUD.crudGeneralCEM;
import CRUD.crudIPL;
import javax.swing.JOptionPane;
import proyectoCerveza.Cerveza;
import proyectoCerveza.Envase;
import proyectoCerveza.Presentacion;

/**
 *
 * @author admin
 */
public class InterfazPresentacion extends javax.swing.JFrame {

    crudIPL opCRUD =new crudIPL();
    crudGeneralCEM operacionesCRUD = new crudGeneralCEM();
    boolean actualizarFlag=false;
    public InterfazPresentacion() {
        initComponents();
        actualizaTablaPre();
        actualizaTablaCer();
        actualizaTablaEnv();
        idMax();
        txtPres.setEnabled(false);
        txtPresAct.setEnabled(false);
        txtPresDel.setEnabled(false);
        txtCervezaDel.setEnabled(false);
        txtEnvaseDel.setEnabled(false);
        
    }
    
    private void actualizaTablaPre(){
        tblPresentacion.setModel(opCRUD.opBuscar("Presentacion", cmbBuscarPres.getSelectedItem().toString(), txtBuscarPres.getText()));
    }
     private void actualizaTablaCer(){
        tblCerveza.setModel(operacionesCRUD.opBuscar("Cerveza",cmbBuscarCerveza.getSelectedItem().toString(),txtBuscarCerveza.getText()));
    }
     
     private void actualizaTablaEnv(){
         System.out.print("\nEl campo a buscar en el combo de envase: "+cmbBuscarEnvase.getSelectedItem().toString());
         tblEnvase.setModel(opCRUD.opBuscar("Envase",cmbBuscarEnvase.getSelectedItem().toString(),txtBuscarEnvase.getText()));
     }
     private void clearFields(){
        
         txtCerveza.setText("");
         txtEnvase.setText("");
         txtPresAct.setText("");
         txtCervezaAct.setText("");
         txtEnvaseAct.setText("");
         tblPresentacion.clearSelection();
         tblEnvase.clearSelection();
         tblCerveza.clearSelection();
         txtPresDel.setText("");
         txtCervezaDel.setText("");
         txtEnvaseDel.setText("");
         
     }
     
     private void idMax(){
         txtPres.setText((opCRUD.opIDMax("Presentacion")+1)+"");
     }
     
     
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPresentacion = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEnvase = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCerveza = new javax.swing.JTable();
        PanelDePresentacion = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtPres = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCerveza = new javax.swing.JTextField();
        txtEnvase = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        CancelarCreate = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtPresAct = new javax.swing.JTextField();
        txtCervezaAct = new javax.swing.JTextField();
        txtEnvaseAct = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        CancelarAct = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtPresDel = new javax.swing.JTextField();
        txtCervezaDel = new javax.swing.JTextField();
        txtEnvaseDel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtBuscarCerveza = new javax.swing.JTextField();
        cmbBuscarCerveza = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        txtBuscarEnvase = new javax.swing.JTextField();
        cmbBuscarEnvase = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        cmbBuscarPres = new javax.swing.JComboBox<>();
        txtBuscarPres = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblPresentacion.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPresentacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPresentacionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPresentacion);

        tblEnvase.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEnvase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEnvaseMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEnvase);

        tblCerveza.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCerveza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCervezaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCerveza);

        PanelDePresentacion.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Presentación de la Cerveza"));
        PanelDePresentacion.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                PanelDePresentacionAncestorMoved(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        PanelDePresentacion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PanelDePresentacionStateChanged(evt);
            }
        });

        jLabel1.setText("# de la Presentación");

        txtEnvase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnvaseActionPerformed(evt);
            }
        });

        jLabel2.setText("Cerveza");

        jLabel3.setText("Envase");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        CancelarCreate.setText("Cancelar");
        CancelarCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarCreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(283, Short.MAX_VALUE)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCerveza, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEnvase, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPres, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addComponent(CancelarCreate)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCerveza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEnvase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(CancelarCreate))
                .addContainerGap())
        );

        PanelDePresentacion.addTab("Create", jPanel1);

        txtCervezaAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCervezaActActionPerformed(evt);
            }
        });

        txtEnvaseAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnvaseActActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        CancelarAct.setText("Cancelar");
        CancelarAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActActionPerformed(evt);
            }
        });

        jLabel4.setText("# de la Presentación");

        jLabel5.setText("Cerveza");

        jLabel6.setText("Envase");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnvaseAct, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCervezaAct, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPresAct, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(216, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizar)
                .addGap(28, 28, 28)
                .addComponent(CancelarAct)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPresAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCervezaAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEnvaseAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(0, 72, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelarAct)
                    .addComponent(btnActualizar))
                .addContainerGap())
        );

        PanelDePresentacion.addTab("Update", jPanel2);

        txtEnvaseDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnvaseDelActionPerformed(evt);
            }
        });

        jLabel7.setText("# de la Presentación");

        jLabel8.setText("Cerveza");

        jLabel9.setText("Envase");

        jButton3.setText("Borrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPresDel)
                    .addComponent(txtCervezaDel)
                    .addComponent(txtEnvaseDel, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(264, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPresDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCervezaDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEnvaseDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        PanelDePresentacion.addTab("Delete", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "BuscarCerveza"));

        txtBuscarCerveza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarCervezaActionPerformed(evt);
            }
        });
        txtBuscarCerveza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarCervezaKeyReleased(evt);
            }
        });

        cmbBuscarCerveza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Marca" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(cmbBuscarCerveza, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscarCerveza, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarCerveza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBuscarCerveza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "BuscarEnvase"));

        txtBuscarEnvase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarEnvaseActionPerformed(evt);
            }
        });
        txtBuscarEnvase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarEnvaseKeyReleased(evt);
            }
        });

        cmbBuscarEnvase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo de envase", "Capacidad" }));
        cmbBuscarEnvase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBuscarEnvaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbBuscarEnvase, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarEnvase, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarEnvase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBuscarEnvase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "BuscarPresentacion"));

        cmbBuscarPres.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo de la presentacion" }));
        cmbBuscarPres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBuscarPresActionPerformed(evt);
            }
        });
        cmbBuscarPres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbBuscarPresKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbBuscarPresKeyReleased(evt);
            }
        });

        txtBuscarPres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarPresKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(cmbBuscarPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarPres, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBuscarPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(PanelDePresentacion)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(PanelDePresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEnvaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnvaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnvaseActionPerformed

    private void CancelarCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarCreateActionPerformed
        clearFields();
    }//GEN-LAST:event_CancelarCreateActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        
        boolean bandera=false;
        int nuevoCod=Integer.parseInt(txtPres.getText());
        
      
        
        if(txtCerveza.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe de seleccionar una Cerveza","Error",JOptionPane.ERROR_MESSAGE);
           bandera=true;
        }
        if(txtEnvase.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe de seleccionar un Envase","Error",JOptionPane.ERROR_MESSAGE);
           bandera=true;
        }
        
        if(bandera==false){
            Presentacion nuevaPre=new Presentacion(nuevoCod);
            
            Cerveza cervezanuevaPre=(Cerveza)opCRUD.opBuscarObjeto("Cerveza",txtCerveza.getText());
            Envase envasenuevaPre=(Envase)opCRUD.opBuscarObjeto("Envase", txtEnvase.getText());
            
            nuevaPre.formPre_cer(cervezanuevaPre);
            nuevaPre.formPre_env(envasenuevaPre);
            
            opCRUD.opCreate("Presentacion", nuevaPre);
             actualizaTablaPre();
             clearFields();
             idMax();
        }
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tblCervezaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCervezaMouseClicked
        txtCerveza.setText(tblCerveza.getValueAt(tblCerveza.getSelectedRow(), 0).toString());
        
        if(actualizarFlag==true){
            txtCervezaAct.setText(tblCerveza.getValueAt(tblCerveza.getSelectedRow(), 0).toString());
        }
    }//GEN-LAST:event_tblCervezaMouseClicked

    private void tblEnvaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEnvaseMouseClicked
        txtEnvase.setText(tblEnvase.getValueAt(tblEnvase.getSelectedRow(), 0).toString());
        
         if(actualizarFlag==true){
            txtEnvaseAct.setText(tblEnvase.getValueAt(tblEnvase.getSelectedRow(), 0).toString());
        }
    }//GEN-LAST:event_tblEnvaseMouseClicked

    private void txtCervezaActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCervezaActActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCervezaActActionPerformed

    private void txtEnvaseActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnvaseActActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnvaseActActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtEnvaseDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnvaseDelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnvaseDelActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        boolean bandera=false;
        int nuevoCod=0;
        
        try{
            nuevoCod=Integer.parseInt(txtPresAct.getText());
        }catch(NumberFormatException err){
           JOptionPane.showMessageDialog(null,"Debe de seleccionar un registro a editar","Error",JOptionPane.ERROR_MESSAGE);
           bandera=true;
        }
      
        
        if(txtCervezaAct.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe de seleccionar una Cerveza","Error",JOptionPane.ERROR_MESSAGE);
           bandera=true;
        }
        if(txtEnvaseAct.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe de seleccionar un Envase","Error",JOptionPane.ERROR_MESSAGE);
           bandera=true;
        }
        
        if(bandera==false){
            Presentacion nuevaPre=new Presentacion(nuevoCod);
            
            Cerveza cervezanuevaPre=(Cerveza)opCRUD.opBuscarObjeto("Cerveza",txtCervezaAct.getText());
            Envase envasenuevaPre=(Envase)opCRUD.opBuscarObjeto("Envase", txtEnvaseAct.getText());
            
            nuevaPre.formPre_cer(cervezanuevaPre);
            nuevaPre.formPre_env(envasenuevaPre);
            
             opCRUD.opUpdate("Presentacion", nuevaPre);
             actualizaTablaPre();
             clearFields();
             idMax();
             actualizarFlag=false;
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tblPresentacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPresentacionMouseClicked
        actualizarFlag=true;
        txtPresAct.setText(tblPresentacion.getValueAt(tblPresentacion.getSelectedRow(),0).toString());
        txtCervezaAct.setText(tblPresentacion.getValueAt(tblPresentacion.getSelectedRow(),1).toString());
        txtEnvaseAct.setText(tblPresentacion.getValueAt(tblPresentacion.getSelectedRow(),2).toString());
       
        
        
        txtPresDel.setText(tblPresentacion.getValueAt(tblPresentacion.getSelectedRow(),0).toString());
        txtCervezaDel.setText(tblPresentacion.getValueAt(tblPresentacion.getSelectedRow(),1).toString());
        txtEnvaseDel.setText(tblPresentacion.getValueAt(tblPresentacion.getSelectedRow(),2).toString());
    }//GEN-LAST:event_tblPresentacionMouseClicked

    private void CancelarActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActActionPerformed
        clearFields();
    }//GEN-LAST:event_CancelarActActionPerformed

    private void PanelDePresentacionAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_PanelDePresentacionAncestorMoved
        
    }//GEN-LAST:event_PanelDePresentacionAncestorMoved

    private void PanelDePresentacionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PanelDePresentacionStateChanged
        clearFields();
    }//GEN-LAST:event_PanelDePresentacionStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        if(txtPresDel.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Debe de seleccionar un registro para eliminarlo","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            Presentacion auxiliar=(Presentacion)opCRUD.opBuscarObjeto("Presentacion", txtPresDel.getText());
             if(JOptionPane.showConfirmDialog(null,"Usted esta seguro de elminar el registro?\n"+ auxiliar,
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
            {
            opCRUD.opDelte("Presentacion", txtPresDel.getText());
             actualizaTablaPre();
            clearFields();
            idMax();
            actualizarFlag=false;
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtBuscarCervezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCervezaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCervezaActionPerformed

    private void txtBuscarPresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPresKeyReleased
        actualizaTablaPre();
    }//GEN-LAST:event_txtBuscarPresKeyReleased

    private void cmbBuscarPresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbBuscarPresKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBuscarPresKeyPressed

    private void cmbBuscarPresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbBuscarPresKeyReleased
      
    }//GEN-LAST:event_cmbBuscarPresKeyReleased

    private void cmbBuscarPresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBuscarPresActionPerformed
         actualizaTablaPre();
    }//GEN-LAST:event_cmbBuscarPresActionPerformed

    private void cmbBuscarEnvaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBuscarEnvaseActionPerformed
      
    }//GEN-LAST:event_cmbBuscarEnvaseActionPerformed

    private void txtBuscarEnvaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarEnvaseActionPerformed
        
    }//GEN-LAST:event_txtBuscarEnvaseActionPerformed

    private void txtBuscarEnvaseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEnvaseKeyReleased
        actualizaTablaEnv();
    }//GEN-LAST:event_txtBuscarEnvaseKeyReleased

    private void txtBuscarCervezaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCervezaKeyReleased
        actualizaTablaCer();
    }//GEN-LAST:event_txtBuscarCervezaKeyReleased

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
            java.util.logging.Logger.getLogger(InterfazPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazPresentacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelarAct;
    private javax.swing.JButton CancelarCreate;
    private javax.swing.JTabbedPane PanelDePresentacion;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JComboBox<String> cmbBuscarCerveza;
    private javax.swing.JComboBox<String> cmbBuscarEnvase;
    private javax.swing.JComboBox<String> cmbBuscarPres;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblCerveza;
    private javax.swing.JTable tblEnvase;
    private javax.swing.JTable tblPresentacion;
    private javax.swing.JTextField txtBuscarCerveza;
    private javax.swing.JTextField txtBuscarEnvase;
    private javax.swing.JTextField txtBuscarPres;
    private javax.swing.JTextField txtCerveza;
    private javax.swing.JTextField txtCervezaAct;
    private javax.swing.JTextField txtCervezaDel;
    private javax.swing.JTextField txtEnvase;
    private javax.swing.JTextField txtEnvaseAct;
    private javax.swing.JTextField txtEnvaseDel;
    private javax.swing.JTextField txtPres;
    private javax.swing.JTextField txtPresAct;
    private javax.swing.JTextField txtPresDel;
    // End of variables declaration//GEN-END:variables
}
