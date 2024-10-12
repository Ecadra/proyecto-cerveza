/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import proyectoCerveza.Fabricante;

/**
 *
 * @author xim
 */
public class InterfazFabricante extends javax.swing.JFrame {
   
    /**
     * Creates new form InterfazFabricante
     */
    public InterfazFabricante() {
        initComponents();
        actualizarTabla();
        
    }
    
     void opCreateFabricante(Fabricante fab) {
        Fabricante fa;
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");       
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        fa = fab;
        em.persist(fa);
        em.getTransaction().commit();
        // Close the database connection:
        em.close();
        emf.close();
        System.out.println("Fabricante registrado");
    }
    
    void opUpdateFabricante(Fabricante fab) {
        Fabricante fa;
        String aux;
        System.out.println("Fabricante entra" + fab);

        // Open a database connection
        // (create a new database if it doesn't exist yet):
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");        
        EntityManager em = emf.createEntityManager();
        aux = fab.getFab_nombre();
        em.getTransaction().begin();
        fa = em.find(Fabricante.class, aux);
        System.out.println("Fabricante encontrado:  " + aux);
        System.out.println(fab);
        fa.setFab_contacto(fab.getFab_contacto());
        System.out.println("Fabricante modificado \n" + fa);

        em.getTransaction().commit();
    // Close the database connection:
        em.close();
        emf.close();
        System.out.println("Fabricante actualizado");
    }
    
    public List opReadFabricante(String ent, String field, String crit) {

        // Open a database connection
        // (create a new database if it doesn't exist yet):
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");           
        EntityManager em = emf.createEntityManager();
        if (ent.equals("Fabricante")) {
            // Retrieve Proveedor objects from the database:
            TypedQuery<Fabricante> query = null;
            List<Fabricante> results = new ArrayList<Fabricante>();
            if (crit.equals("")) {
                query = em.createQuery("select f from Fabricante f", Fabricante.class);
            } else {
                query = em.createQuery("select f from Fabricante f where f." + field.toLowerCase() + " like '%" + crit + "%'", Fabricante.class);
            }
            results = query.getResultList();
            System.out.println("Fabricante(s) encontrados: " + results.size());
            return results;
        }
    // Close the database connection:
        em.close();
        emf.close();
        return null;
    }
    
    public TableModel listtoTMFabricante(List rs, String entit) {
        Vector columnNames = new Vector();
        Vector rows = new Vector();

        // Nombres de campos
        if (entit.equals("Fabricante")) {
            Fabricante f;
            columnNames.addElement("Nombre de fabricante");
            columnNames.addElement("Número de contacto");

            Iterator it = rs.iterator();
            while (it.hasNext()) {
                f = (Fabricante) it.next();
                Vector newRow = new Vector();
                newRow.addElement(f.getFab_nombre());
                newRow.addElement(f.getFab_contacto());
                rows.addElement(newRow);
            }
        }return new DefaultTableModel(rows, columnNames);
    }
    
    public TableModel opBuscarFabricante(String ent, String field, String crit) {
        TableModel tm = null;
        if (ent.equals("Fabricante")) {
            List<Fabricante> res = opReadFabricante(ent, field, crit);
            tm = listtoTMFabricante(res, ent);
        }
        return tm;
    }
    
    public void activarFabricante(boolean activado){
        btnRegistrar.setEnabled(activado);
    }
    
    public Object opBuscarFabricante1(String ent, String crit) {
        Object c = null;
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb"); 
        EntityManager em = emf.createEntityManager();

        if (ent.equals("Fabricante")) {
            c = em.find(Fabricante.class, crit);
        }
        // Close the database connection:
        em.close();
        emf.close();
        return c;
    }
    
    public void opDeleteFabricante(String ent, String crit) {
        Object e = null;
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb"); 
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if (ent.equals("Fabricante")) {
            e = em.find(Fabricante.class, crit);
            em.remove(e);
        }
        em.getTransaction().commit();
        // Close the database connection:
        em.close();
        emf.close();
        System.out.println("Fabricante eliminado");
    }
    
    private void actualizarTabla() {
    List<Fabricante> listaFabricantes = obtenerListaFabricantes();  // Método que obtiene la lista actualizada de la DB
    TableModel model = listtoTMFabricante(listaFabricantes, "Fabricante");
    tableFabricante.setModel(model);

    // Revalidar y repintar la tabla para que los cambios sean visibles
    tableFabricante.revalidate();
    tableFabricante.repaint();
    }   

// Método para obtener los fabricantes de la base de datos
    public List<Fabricante> obtenerListaFabricantes() {
    //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim   
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\objectdb-2.9.0\\db\\cervezadb.odb");
    EntityManager em = emf.createEntityManager();
    
    // Consulta para obtener todos los fabricantes
    List<Fabricante> listaFabricantes = em.createQuery("SELECT f FROM Fabricante f", Fabricante.class).getResultList();
    
    em.close();
    emf.close();
    return listaFabricantes;
}
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNomFabricante = new javax.swing.JTextField();
        txtContacto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        PanelRegistros = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnBusqueda = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbAtributo = new javax.swing.JComboBox<>();
        ScrollFabricante = new javax.swing.JScrollPane();
        tableFabricante = new javax.swing.JTable();
        btnNewFabricante = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Nombre:");

        jLabel2.setText("Contacto:");

        jLabel3.setText("Datos del fabricante:");

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNomFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(btnRegistrar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnActualizar))
                            .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNomFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRegistrar)
                        .addComponent(btnEliminar)
                        .addComponent(btnActualizar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        btnRegresar.setText("Regresar al menu");

        PanelRegistros.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Registros"));

        jLabel4.setText("Busqueda: ");

        jLabel5.setText("Atributo:");

        cmbAtributo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre Fabricante", "Contacto" }));

        tableFabricante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Fabricante", "Contacto"
            }
        ));
        tableFabricante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableFabricanteMouseClicked(evt);
            }
        });
        ScrollFabricante.setViewportView(tableFabricante);

        javax.swing.GroupLayout PanelRegistrosLayout = new javax.swing.GroupLayout(PanelRegistros);
        PanelRegistros.setLayout(PanelRegistrosLayout);
        PanelRegistrosLayout.setHorizontalGroup(
            PanelRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRegistrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ScrollFabricante)
                    .addGroup(PanelRegistrosLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelRegistrosLayout.setVerticalGroup(
            PanelRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRegistrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btnBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmbAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollFabricante, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        btnNewFabricante.setText("Nuevo Fabricante");
        btnNewFabricante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewFabricanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegresar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(PanelRegistros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNewFabricante)
                .addGap(90, 90, 90))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNewFabricante)
                .addGap(7, 7, 7)
                .addComponent(PanelRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // Capturar datos del formulario
    String nombre = txtNomFabricante.getText();
    String contacto = txtContacto.getText();

    // Verificar que no estén vacíos
    if (!nombre.isEmpty() && !contacto.isEmpty()) {
        // Crear un nuevo objeto Fabricante
        Fabricante nuevoFabricante = new Fabricante();
        nuevoFabricante.setFab_nombre(nombre);
        nuevoFabricante.setFab_contacto(contacto);

        // Llamar al método para registrar el fabricante
        opCreateFabricante(nuevoFabricante);

        // Mostrar mensaje de confirmación
        JOptionPane.showMessageDialog(this, "Fabricante registrado correctamente.");

        // Limpiar los campos de texto
        txtNomFabricante.setText("");
        txtContacto.setText("");
        
        // Actualizar la tabla
        actualizarTabla();
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese todos los datos.");
    }
            
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    String nombre = txtNomFabricante.getText();

    if (!nombre.isEmpty()) {
        // Llamar al método para eliminar el fabricante
        opDeleteFabricante("Fabricante", nombre);
        
        // Mostrar mensaje de confirmación
        JOptionPane.showMessageDialog(this, "Fabricante eliminado correctamente.");
        
        // Limpiar los campos de texto
        txtNomFabricante.setText("");
        txtContacto.setText("");
        
        // Actualizar la tabla
        actualizarTabla();
    } else {
        // Mostrar mensaje de error si el nombre está vacío
        JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre del fabricante a eliminar.");
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
      String nombre = txtNomFabricante.getText();
    String contacto = txtContacto.getText();

    if (!nombre.isEmpty() && !contacto.isEmpty()) {
        Fabricante fabricanteActualizado = new Fabricante();
        fabricanteActualizado.setFab_nombre(nombre);
        fabricanteActualizado.setFab_contacto(contacto);

        // Llamar al método para actualizar el fabricante
        opUpdateFabricante(fabricanteActualizado);

        JOptionPane.showMessageDialog(this, "Fabricante actualizado correctamente.");
        
        // Limpiar los campos de texto
        txtNomFabricante.setText("");
        txtContacto.setText("");
        
        // Actualizar la tabla
        actualizarTabla();
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese todos los datos.");
    }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnNewFabricanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewFabricanteActionPerformed
    btnRegistrar.setEnabled(true);
    txtNomFabricante.setText("");
    txtContacto.setText("");
    }//GEN-LAST:event_btnNewFabricanteActionPerformed

    private void tableFabricanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFabricanteMouseClicked
    if(!tableFabricante.isEnabled()){

        }else{
            ///Para poder seleccionar algun alumno de la lista y modificarlo o eliminarlo
            activarFabricante(true);
            //Se activan solo los botones para actualizar y eliminar
            btnRegistrar.setEnabled(false);
            btnActualizar.setEnabled(true);
            btnEliminar.setEnabled(true);
            txtNomFabricante.setText("");
            txtContacto.setText("");
            btnNewFabricante.setEnabled(false);

           
            txtNomFabricante.setText(tableFabricante.getValueAt(tableFabricante.getSelectedRow(),0).toString());
            txtContacto.setText(tableFabricante.getValueAt(tableFabricante.getSelectedRow(),1).toString());
            //jdate.setDate(new Date(((Timestamp) tblAdministracion.getValueAt(tblAdministracion.getSelectedRow(), 6)).getTime()));
 
        }
        
    }//GEN-LAST:event_tableFabricanteMouseClicked

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
            java.util.logging.Logger.getLogger(InterfazFabricante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazFabricante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazFabricante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazFabricante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazFabricante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelRegistros;
    private javax.swing.JScrollPane ScrollFabricante;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JTextField btnBusqueda;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNewFabricante;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbAtributo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable tableFabricante;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JTextField txtNomFabricante;
    // End of variables declaration//GEN-END:variables
}
