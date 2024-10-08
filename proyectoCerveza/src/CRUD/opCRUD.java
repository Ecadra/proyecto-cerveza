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
import proyectoCerveza.Cerveza;
import proyectoCerveza.Expendio;
import proyectoCerveza.Fabricante;
import proyectoCerveza.Grano;
import proyectoCerveza.Inventario;
import proyectoCerveza.Lote;
import proyectoCerveza.Marca;
import proyectoCerveza.Pedido;
import proyectoCerveza.Presentacion;
import proyectoCerveza.Receta;
import proyectoCerveza.Sede;
import proyectoCerveza.Venta;
import proyectoCerveza.Envase;

/**
 *
 * @author ulseg
 */
public class opCRUD {
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
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");        EntityManager em = emf.createEntityManager();
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
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");        EntityManager em = emf.createEntityManager();
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
}
