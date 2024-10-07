
package relaciones;
import javax.persistence.*;
import proyectoCerveza.Grano;
import proyectoCerveza.Receta;
/**
 *
 * @author ulseg
 */
public class RelRecGra {
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
        Grano g1 = em.find(Grano.class, "Arroz Yamadanishiki");   
        
        System.out.println(g1);
        rec4.formRec_gra(g1);
        g1.formGra_rec(rec4);        
        em.persist(g1);
        em.persist(rec4);
        em.getTransaction().commit();
    }
}




