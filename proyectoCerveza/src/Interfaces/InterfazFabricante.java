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
import CRUD.crudGeneralSI;

/**
 *
 * @author xim
 */
public class InterfazFabricante extends javax.swing.JFrame {
   
    /**
     * Creates new form InterfazFabricante
     */
    //Cesar
    //public String ruta = "D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb";
    
    //Sebas
    //public String ruta = "C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb";
   
    //Edwin
    //public String ruta = "/home/edwin-993/cervezaodb/cervezadb.odb";
    
    //Xim
    public String ruta = "C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb";
    private crudGeneralSI operacionesCRUD = new crudGeneralSI();
    public InterfazFabricante() {
        initComponents();
        this.setLocationRelativeTo(null);
        actualizarTabla();
        txtCodigo.setText((operacionesCRUD.opMaxID("Fabricante")+1)+"");
        
    }
    
     void opCreateFabricante(Fabricante fab) {
        Fabricante fa;
        EntityManagerFactory emf= Persistence.createEntityManagerFactory(ruta);       
        EntityManager em = emf.createEntityManager();
        
        try {
        em.getTransaction().begin();
        // Buscar si ya existe un fabricante con ese nombre
        Fabricante existente = em.find(Fabricante.class, fab.getId_fab());

        if (existente != null) {
            throw new Exception("El fabricante con el ID '" + fab.getId_fab() + "' ya está registrado.");
        }
        else{
        fa = fab;
        em.persist(fa);
        em.getTransaction().commit();
        System.out.println("Fabricante registrado");
        JOptionPane.showMessageDialog(this, "Fabricante registrado correctamente.");
        }
        
    }catch(Exception e) {
        em.getTransaction().rollback(); // Si hay un error, deshacer la transacción
        System.out.println("Error al registrar fabricante: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

    } finally {
        em.close();
        emf.close();
        }
    }
    
    void opUpdateFabricante(Fabricante fab) {
        Fabricante fa;
        int aux;
        System.out.println("Fabricante entra" + fab);
        EntityManagerFactory emf= Persistence.createEntityManagerFactory(ruta);        
        EntityManager em = emf.createEntityManager();
        aux = fab.getId_fab();
        em.getTransaction().begin();
        fa = em.find(Fabricante.class, aux);
        System.out.println("Fabricante encontrado:  " + aux);
        System.out.println(fab);
        fa.setFab_nombre(fab.getFab_nombre());
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
        EntityManagerFactory emf= Persistence.createEntityManagerFactory(ruta);           
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
    
    public void buscarTabla() {
    TableModel modelo = opBuscar("Fabricante", (String) cmbAtributo.getSelectedItem(), txtCriterio.getText());
    if (modelo != null) {
        tableFabricante.setModel(modelo);
    } else {
        // Maneja el caso en que no haya modelo (opcional)
        System.out.println("No se encontró ningún resultado.");
    }
}

    
    public TableModel listtoTMFabricante(List rs, String entit) {
        Vector columnNames = new Vector();
        Vector rows = new Vector();

        // Nombres de campos
        if (entit.equals("Fabricante")) {
            Fabricante f;
            columnNames.addElement("Código");
            columnNames.addElement("Nombre de fabricante");
            columnNames.addElement("Número de contacto");

            Iterator it = rs.iterator();
            while (it.hasNext()) {
                f = (Fabricante) it.next();
                Vector newRow = new Vector();
                newRow.addElement(f.getId_fab());
                newRow.addElement(f.getFab_nombre());
                newRow.addElement(f.getFab_contacto());
                rows.addElement(newRow);
            }
        }return new DefaultTableModel(rows, columnNames);
    }
    
    public TableModel opBuscar(String ent, String field, String criterio) {
        TableModel tm = null;
    if (ent.equals("Fabricante")) {
        List<Fabricante> resultados;
        switch (field) {
            case "Nombre":
                resultados = opReadFabricante(ent, "fab_nombre", criterio);
                break;
            case "Contacto":
                resultados = opReadFabricante(ent, "fab_contacto", criterio);
                break;
            default:
                resultados = opReadFabricante(ent, field, criterio);
                break;
        }
        // Asegúrate de que siempre se asigna un TableModel
        if (resultados != null && !resultados.isEmpty()) {
            tm = listtoTMFabricante(resultados, ent);
        } else {
            // Retorna un modelo vacío en caso de que no haya resultados
            tm = listtoTMFabricante(new ArrayList<Fabricante>(), ent);
        }
        }
     return tm; // Devuelve el modelo (puede ser nulo si 'ent' no es "Fabricante")
        }
    
    
    
    public Object opBuscarFabricante1(String ent, String crit) {
        Object c = null;     
        EntityManagerFactory emf= Persistence.createEntityManagerFactory(ruta); 
        EntityManager em = emf.createEntityManager();

        if (ent.equals("Fabricante")) {
            c = em.find(Fabricante.class, crit);
        }
        // Close the database connection:
        em.close();
        emf.close();
        return c;
    }
    
    public void opDeleteFabricante(String ent, int crit) {
        Object e = null;
        EntityManagerFactory emf= Persistence.createEntityManagerFactory(ruta); 
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
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
    EntityManager em = emf.createEntityManager();
    
    // Consulta para obtener todos los fabricantes
    List<Fabricante> listaFabricantes = em.createQuery("SELECT f FROM Fabricante f", Fabricante.class).getResultList();
    
    em.close();
    emf.close();
    return listaFabricantes;
}
    
    public void Limpiar(){
        txtNomFabricante.setText("");
        txtContacto.setText("");
        txtNomFabricante1.setText("");
        txtContacto1.setText("");
        txtNomFabricante2.setText("");
        txtContacto2.setText("");
       
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegresar = new javax.swing.JButton();
        PanelRegistros = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtCriterio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbAtributo = new javax.swing.JComboBox<>();
        ScrollFabricante = new javax.swing.JScrollPane();
        tableFabricante = new javax.swing.JTable();
        tbdFabricante = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNomFabricante = new javax.swing.JTextField();
        txtContacto = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        w = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNomFabricante1 = new javax.swing.JTextField();
        txtContacto1 = new javax.swing.JTextField();
        btnLimpiar1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtCodigo1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNomFabricante2 = new javax.swing.JTextField();
        txtContacto2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCodigo2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnRegresar.setText("Regresar al menu");

        PanelRegistros.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Registros"));

        jLabel4.setText("Busqueda: ");

        txtCriterio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCriterioKeyReleased(evt);
            }
        });

        jLabel5.setText("Atributo:");

        cmbAtributo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Contacto" }));
        cmbAtributo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAtributoActionPerformed(evt);
            }
        });

        tableFabricante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Contacto"
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
                        .addComponent(txtCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        PanelRegistrosLayout.setVerticalGroup(
            PanelRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRegistrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmbAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollFabricante, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        tbdFabricante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbdFabricante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdFabricanteMouseClicked(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Nombre:");

        jLabel2.setText("Contacto:");

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

        jLabel9.setText("Código:");

        txtCodigo.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtContacto, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(txtNomFabricante, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(txtCodigo))
                .addGap(56, 56, 56)
                .addComponent(btnRegistrar)
                .addGap(31, 31, 31)
                .addComponent(btnLimpiar)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNomFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrar)
                            .addComponent(btnLimpiar))
                        .addGap(28, 28, 28))))
        );

        tbdFabricante.addTab("Registar", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        w.setText("Nombre:");

        jLabel7.setText("Contacto:");

        btnLimpiar1.setText("Limpiar Campos");
        btnLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Código");

        txtCodigo1.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtContacto1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(w))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNomFabricante1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(txtCodigo1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar1)
                .addGap(73, 73, 73))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnActualizar)
                            .addComponent(btnLimpiar1))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(w)
                            .addComponent(txtNomFabricante1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtContacto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))))
        );

        tbdFabricante.addTab("Actualizar", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar2.setText("Limpiar Campos");
        btnLimpiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiar2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre:");

        jLabel6.setText("Contacto:");

        txtNomFabricante2.setEditable(false);

        txtContacto2.setEditable(false);

        jLabel11.setText("Código:");

        txtCodigo2.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtContacto2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNomFabricante2, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(txtCodigo2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(btnEliminar)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar2)
                .addGap(62, 62, 62))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCodigo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNomFabricante2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtContacto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEliminar)
                        .addComponent(btnLimpiar2)))
                .addGap(22, 22, 22))
        );

        tbdFabricante.addTab("Eliminar", jPanel3);

        jLabel8.setText("Datos del fabricante:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelRegistros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRegresar)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(tbdFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(291, 291, 291)
                                .addComponent(jLabel8)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegresar)
                .addGap(2, 2, 2)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbdFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
    String nombre = txtNomFabricante.getText();
    String contacto = txtContacto.getText();
    String idStr = txtCodigo.getText();

    if (!nombre.isEmpty() && !contacto.isEmpty() && !idStr.isEmpty()) {
         try {
        int id = Integer.parseInt(idStr);  // Convertir el ID a entero
        
        // Validar que el contacto sea numérico y tenga 10 dígitos
            if (contacto.matches("\\d{10}")) {
            Fabricante nuevoFabricante = new Fabricante();
            nuevoFabricante.setId_fab(id);
            nuevoFabricante.setFab_nombre(nombre);
            nuevoFabricante.setFab_contacto(contacto);
            
            try {
                opCreateFabricante(nuevoFabricante);
                txtNomFabricante.setText("");
                txtContacto.setText("");
                actualizarTabla();
            } catch (Exception e) {
                // Manejo de la excepción aquí
                if (e.getMessage().contains("ya está registrado")) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ID Duplicado", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage(), "Error en la BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un contacto válido (10 dígitos numéricos).", "Error en el contacto", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error en el ID", JOptionPane.ERROR_MESSAGE);
    }
    } else {
    JOptionPane.showMessageDialog(this, "Por favor, ingrese todos los datos.");
    }

            
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    String nombre = txtNomFabricante1.getText();
    String contacto = txtContacto1.getText();
    int id = Integer.parseInt(txtCodigo1.getText());

    if (!nombre.isEmpty() && !contacto.isEmpty()) {
        Fabricante fabricanteActualizado = new Fabricante();
        fabricanteActualizado.setId_fab(id);
        fabricanteActualizado.setFab_nombre(nombre);
        fabricanteActualizado.setFab_contacto(contacto);

        // Llamar al método para actualizar el fabricante
        opUpdateFabricante(fabricanteActualizado);

        JOptionPane.showMessageDialog(this, "Fabricante actualizado correctamente.");
        
        // Limpiar los campos de texto
        Limpiar();        
        // Actualizar la tabla
        actualizarTabla();
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese todos los datos.");
    }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tableFabricanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFabricanteMouseClicked
    tbdFabricante.setSelectedIndex(1);
        editar(true);
        insertar(false);
        eliminar(true);
        int fila = tableFabricante.getSelectedRow();
  

        //SE RECUPERAN LOS DATOS EN EDITAR
        txtCodigo1.setText(tableFabricante.getValueAt(fila, 0)+ "");
        txtNomFabricante1.setText(tableFabricante.getValueAt(fila, 1) + "");
        txtContacto1.setText(tableFabricante.getValueAt(fila, 2) + "");

        //SE RECUPERAN LOS DATOS EN ELIMINAR
        //txtId1.setText(tblRegistros.getValueAt(fila, 0) + "");
        txtCodigo2.setText(tableFabricante.getValueAt(fila, 0)+ "");
        txtNomFabricante2.setText(tableFabricante.getValueAt(fila, 1) + "");
         txtContacto2.setText(tableFabricante.getValueAt(fila, 2) + "");
       

    }//GEN-LAST:event_tableFabricanteMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
   if (JOptionPane.showConfirmDialog(rootPane, """
                                                   Al cambiar de funcion se perderan los cambios de la funcion actual
                                                   Esta usted seguro de querer proceder?""", "Confirmación de eliminacion",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Limpiar();
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tbdFabricanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdFabricanteMouseClicked
    boolean tablaSeleccionada = tableFabricante.getSelectedRow() != -1;
        System.out.println(tablaSeleccionada); //Mensaje de bandera
        //tabSeleccionada se obtiene mediante el metodo getSelectedIndex, por lo que puede
        //tener valores de 0 a 2, 0 = insertar, 1 = editar, 2 = eliminar
        int tabSeleccionada = tbdFabricante.getSelectedIndex(); //Mensaje de bandera
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
                tbdFabricante.setSelectedIndex(0);
                insertar(true);
                eliminar(false);
                editar(false);
                btnRegresar.setEnabled(true);
               
                        }
        }

    }//GEN-LAST:event_tbdFabricanteMouseClicked

    private void btnLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar1ActionPerformed
     if (JOptionPane.showConfirmDialog(rootPane, """
                                                   Al cambiar de funcion se perderan los cambios de la funcion actual
                                                   Esta usted seguro de querer proceder?""", "Confirmación de eliminacion",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            tableFabricante.clearSelection();
            tbdFabricante.setSelectedIndex(0);
            Limpiar();
        }
    }//GEN-LAST:event_btnLimpiar1ActionPerformed

    private void btnLimpiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiar2ActionPerformed
     if (JOptionPane.showConfirmDialog(rootPane, """
                                                   Al cambiar de funcion se perderan los cambios de la funcion actual
                                                   Esta usted seguro de querer proceder?""", "Confirmación de eliminacion",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            tableFabricante.clearSelection();
            tbdFabricante.setSelectedIndex(0);
            Limpiar();
        }
    }//GEN-LAST:event_btnLimpiar2ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
      if (!txtCodigo2.getText().isEmpty()) {
        try {
            int id = Integer.parseInt(txtCodigo2.getText());
            
            opDeleteFabricante("Fabricante", id);
            JOptionPane.showMessageDialog(this, "Fabricante eliminado correctamente.");
            
            Limpiar();
            txtNomFabricante2.setText("");
            txtContacto2.setText("");
            txtCodigo2.setText("");
            actualizarTabla();
        } catch (NumberFormatException e) {
            // Mostrar mensaje de error si no se puede convertir el texto a entero
            JOptionPane.showMessageDialog(this, "El código del fabricante debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Mostrar mensaje de error si el campo del código está vacío
        JOptionPane.showMessageDialog(this, "Por favor, ingrese el código del fabricante a eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cmbAtributoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAtributoActionPerformed
    buscarTabla();
    }//GEN-LAST:event_cmbAtributoActionPerformed

    private void txtCriterioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCriterioKeyReleased
    buscarTabla();
    }//GEN-LAST:event_txtCriterioKeyReleased
    
        public void insertar(boolean activacion) {
        txtNomFabricante.setEnabled(activacion);
        txtContacto.setEnabled(activacion);
        btnRegistrar.setEnabled(activacion);
        btnLimpiar.setEnabled(activacion);

    }

    public void eliminar(boolean activacion) {
        btnEliminar.setEnabled(activacion);
        btnLimpiar2.setEnabled(activacion);
        btnRegresar.setEnabled(!activacion);
    }

    public void editar(boolean activacion) {
        //txtId2.setEnabled(activacion);
        txtNomFabricante1.setEnabled(activacion);
        txtContacto1.setEnabled(activacion);
        btnActualizar.setEnabled(activacion);
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
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiar1;
    private javax.swing.JButton btnLimpiar2;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbAtributo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTable tableFabricante;
    private javax.swing.JTabbedPane tbdFabricante;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigo1;
    private javax.swing.JTextField txtCodigo2;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JTextField txtContacto1;
    private javax.swing.JTextField txtContacto2;
    private javax.swing.JTextField txtCriterio;
    private javax.swing.JTextField txtNomFabricante;
    private javax.swing.JTextField txtNomFabricante1;
    private javax.swing.JTextField txtNomFabricante2;
    private javax.swing.JLabel w;
    // End of variables declaration//GEN-END:variables
}
