
package relaciones;
import javax.persistence.*;
import proyectoCerveza.Cerveza;
import proyectoCerveza.Receta;
/**
 *
 * @author ulseg
 */
public class RelRecCer {
    public static void main(String args[]) {
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\Users\\ximen\\Documents\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Receta rec4 = new Receta(4,"1 kilo");
        Cerveza c1 = em.find(Cerveza.class, "1");   
        
        System.out.println(c1);
        rec4.formRec_cer(c1);
        c1.formCer_rec(rec4);

        
        em.persist(c1);
        em.persist(rec4);
        em.getTransaction().commit();
    }
}




