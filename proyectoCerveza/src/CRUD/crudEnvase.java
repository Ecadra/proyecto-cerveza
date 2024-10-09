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
import proyectoCerveza.Envase;

/**
 *
 * @author ulseg
 */
public class crudEnvase {
    void opCreateEnvase(Envase env) {
        Envase en;
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");       
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        en = env;
        em.persist(en);
        em.getTransaction().commit();
        // Close the database connection:
        em.close();
        emf.close();
        System.out.println("Envase registrado");
    }
    
    void opUpdateEnvase(Envase env) {
        Envase en;
        String aux;
        System.out.println("Envase entra" + env);

        // Open a database connection
        // (create a new database if it doesn't exist yet):
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");        
        EntityManager em = emf.createEntityManager();
        aux = env.getTipo_envase();
        em.getTransaction().begin();
        en = em.find(Envase.class, aux);
        System.out.println("Envase encontrado con id " + aux);
        System.out.println(en);
        en.setCapacidad_ml(env.getEnvase_capacidad());
        
        System.out.println("Envase modificado \n" + en);

        em.getTransaction().commit();
    // Close the database connection:
        em.close();
        emf.close();
        System.out.println("Envase actualizado");
    }
    
    public List opReadEnvase(String ent, String field, String crit) {

        // Open a database connection
        // (create a new database if it doesn't exist yet):
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");           
        EntityManager em = emf.createEntityManager();
        if (ent.equals("Envase")) {
            // Retrieve Proveedor objects from the database:
            TypedQuery<Envase> query = null;
            List<Envase> results = new ArrayList<Envase>();
            if (crit.equals("")) {
                query = em.createQuery("select e from Envase e", Envase.class);
            } else {
                query = em.createQuery("select e from Envase e where e." + field.toLowerCase() + " like '%" + crit + "%'", Envase.class);
            }
            results = query.getResultList();
            System.out.println("Envases encontrados: " + results.size());
            return results;
        }
    // Close the database connection:
        em.close();
        emf.close();
        return null;
    }
    
    public TableModel listtoTMEnvase(List rs, String entit) {
        Vector columnNames = new Vector();
        Vector rows = new Vector();

        // Nombres de campos
        if (entit.equals("Envase")) {
            Envase e;
            columnNames.addElement("Tipo de envase");
            columnNames.addElement("Envase capacidad");

            Iterator it = rs.iterator();
            while (it.hasNext()) {
                e = (Envase) it.next();
                Vector newRow = new Vector();
                newRow.addElement(e.getTipo_envase());
                newRow.addElement(e.getEnvase_capacidad());
                rows.addElement(newRow);
            }
        }return new DefaultTableModel(rows, columnNames);
    }
    
    public TableModel opBuscarEnvase(String ent, String field, String crit) {
        TableModel tm = null;
        if (ent.equals("Envase")) {
            List<Envase> res = opReadEnvase(ent, field, crit);
            tm = listtoTMEnvase(res, ent);
        }
        return tm;
    }
    
    public Object opBuscarEnvase1(String ent, String crit) {
        Object c = null;
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb"); 
        EntityManager em = emf.createEntityManager();

        if (ent.equals("Envase")) {
            c = em.find(Envase.class, crit);
        }
        // Close the database connection:
        em.close();
        emf.close();
        return c;
    }
    
    public void opDeleteEnvase(String ent, String crit) {
        Object e = null;
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb"); 
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if (ent.equals("Envase")) {
            e = em.find(Envase.class, crit);
            em.remove(e);
        }
        em.getTransaction().commit();
        // Close the database connection:
        em.close();
        emf.close();
        System.out.println("Envase eliminado");
    }
    
    
    
}
