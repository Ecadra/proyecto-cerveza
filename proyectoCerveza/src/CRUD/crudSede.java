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
import proyectoCerveza.Sede;


/**
 *
 * @author Xim
 */
public class crudSede {
    
    void opCreateSede(Sede sed) {
        Sede se;
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
        se = sed;
        em.persist(se);
        em.getTransaction().commit();
        // Close the database connection:
        em.close();
        emf.close();
        System.out.println("Sede registrada");
    }
    
    void opUpdateSede(Sede sed) {
        Sede se;
        String aux;
        System.out.println("Sede entra" + sed);

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
        aux = sed.getSe_nombre();
        em.getTransaction().begin();
        se = em.find(Sede.class, aux);
        System.out.println("Sede encontrada:  " + aux);
        System.out.println(sed);
        se.setSe_direccion(se.getSe_direccion());
        System.out.println("Sede modificado \n" + se);

        em.getTransaction().commit();
    // Close the database connection:
        em.close();
        emf.close();
        System.out.println("Sede actualizada");
    }
    
    public List opReadSede(String ent, String field, String crit) {

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
        if (ent.equals("Sede")) {
            // Retrieve Proveedor objects from the database:
            TypedQuery<Sede> query = null;
            List<Sede> results = new ArrayList<Sede>();
            if (crit.equals("")) {
                query = em.createQuery("select s from Sede s", Sede.class);
            } else {
                query = em.createQuery("select s from Sede s where s." + field.toLowerCase() + " like '%" + crit + "%'", Sede.class);
            }
            results = query.getResultList();
            System.out.println("Sede(s) encontrados: " + results.size());
            return results;
        }
    // Close the database connection:
        em.close();
        emf.close();
        return null;
    }
    
    public TableModel listtoTMSede(List rs, String entit) {
        Vector columnNames = new Vector();
        Vector rows = new Vector();

        // Nombres de campos
        if (entit.equals("Sede")) {
            Sede s;
            columnNames.addElement("Nombre de sede");
            columnNames.addElement("Direccion");

            Iterator it = rs.iterator();
            while (it.hasNext()) {
                s = (Sede) it.next();
                Vector newRow = new Vector();
                newRow.addElement(s.getSe_nombre());
                newRow.addElement(s.getSe_direccion());
                rows.addElement(newRow);
            }
        }return new DefaultTableModel(rows, columnNames);
    }
    
    public TableModel opBuscarSede(String ent, String field, String crit) {
        TableModel tm = null;
        if (ent.equals("Sede")) {
            List<Sede> res = opReadSede(ent, field, crit);
            tm = listtoTMSede(res, ent);
        }
        return tm;
    }
    
    public Object opBuscarSede1(String ent, String crit) {
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

        if (ent.equals("Sede")) {
            c = em.find(Sede.class, crit);
        }
        // Close the database connection:
        em.close();
        emf.close();
        return c;
    }
    
    public void opDeleteSede(String ent, String crit) {
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
        if (ent.equals("Sede")) {
            e = em.find(Sede.class, crit);
            em.remove(e);
        }
        em.getTransaction().commit();
        // Close the database connection:
        em.close();
        emf.close();
        System.out.println("Sede eliminada");
    }
    
    
    
}