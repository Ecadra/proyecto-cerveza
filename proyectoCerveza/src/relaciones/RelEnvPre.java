package relaciones;
import javax.persistence.*;
import proyectoCerveza.Envase;
import proyectoCerveza.Presentacion;

/**
 *
 * @author ulseg
 */
public class RelEnvPre {
    public static void main(String args[]) {
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\Users\\ximen\\Documents\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Envase env4 = new Envase("Botella vidrio", (short)1100);
        Presentacion p1 = em.find(Presentacion.class, 1);   
        
        System.out.println(p1);
        env4.formEnv_pre(p1);
        p1.formPre_env(env4);        
        em.persist(p1);
        em.persist(env4);
        em.getTransaction().commit();
    }
}
