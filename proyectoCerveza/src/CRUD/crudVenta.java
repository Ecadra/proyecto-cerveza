package CRUD;
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
import proyectoCerveza.Venta;


/**
 *
 * @author Xim
 */
public class crudVenta {
    
    void opCreateVenta(Venta ven) {
        Venta ve;
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
        ve = ven;
        em.persist(ve);
        em.getTransaction().commit();
        // Close the database connection:
        em.close();
        emf.close();
        System.out.println("Venta registrada");
    }
    
    void opUpdateVenta(Venta ven) {
        Venta ve;
        int aux;
        System.out.println("Venta entra" + ven);

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
        aux = ven.getId_venta();
        em.getTransaction().begin();
        ve = em.find(Venta.class, aux);
        System.out.println("Venta encontrada:  " + aux);
        System.out.println(ven);
        ve.setVen_cantidad(ve.getVen_cantidad());
        ve.setVen_fecha(ve.getVen_fecha());
        ve.setVen_total(ve.getVen_total());
        System.out.println("Venta modificada \n" + ve);

        em.getTransaction().commit();
    // Close the database connection:
        em.close();
        emf.close();
        System.out.println("Venta actualizada");
    }
    
    public List opReadVenta(String ent, String field, String crit) {

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
        if (ent.equals("Venta")) {
            // Retrieve Proveedor objects from the database:
            TypedQuery<Venta> query = null;
            List<Venta> results = new ArrayList<Venta>();
            if (crit.equals("")) {
                query = em.createQuery("select v from Venta v", Venta.class);
            } else {
                query = em.createQuery("select v from Venta v where v." + field.toLowerCase() + " like '%" + crit + "%'", Venta.class);
            }
            results = query.getResultList();
            System.out.println("Venta(s) encontrados: " + results.size());
            return results;
        }
    // Close the database connection:
        em.close();
        emf.close();
        return null;
    }
    
    public TableModel listtoTMVenta(List rs, String entit) {
        Vector columnNames = new Vector();
        Vector rows = new Vector();

        // Nombres de campos
        if (entit.equals("Venta")) {
            Venta v;
            columnNames.addElement("Id Venta");
            columnNames.addElement("Cantidad venta");
            columnNames.addElement("Fecha de venta");
            columnNames.addElement("Cantidad total venta");

            Iterator it = rs.iterator();
            while (it.hasNext()) {
                v = (Venta) it.next();
                Vector newRow = new Vector();
                newRow.addElement(v.getId_venta());
                newRow.addElement(v.getVen_cantidad());
                newRow.addElement(v.getVen_fecha());
                newRow.addElement(v.getVen_total());
                rows.addElement(newRow);
            }
        }return new DefaultTableModel(rows, columnNames);
    }
    
    public TableModel opBuscarVenta(String ent, String field, String crit) {
        TableModel tm = null;
        if (ent.equals("Venta")) {
            List<Venta> res = opReadVenta(ent, field, crit);
            tm = listtoTMVenta(res, ent);
        }
        return tm;
    }
    
    public Object opBuscarVenta1(String ent, String crit) {
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

        if (ent.equals("Venta")) {
            c = em.find(Venta.class, crit);
        }
        // Close the database connection:
        em.close();
        emf.close();
        return c;
    }
    
    public void opDeleteVenta(String ent, String crit) {
        Object e = null;
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
        if (ent.equals("Venta")) {
            e = em.find(Venta.class, crit);
            em.remove(e);
        }
        em.getTransaction().commit();
        // Close the database connection:
        em.close();
        emf.close();
        System.out.println("Venta eliminada");
    }
    
    
    
}