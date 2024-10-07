
package relaciones;
import javax.persistence.*;
import proyectoCerveza.Grano;
import proyectoCerveza.Receta;

/**
 *
 * @author ulseg
 */
public class RelGraRec {
    public static void main(String args[]) {
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\Users\\ximen\\Documents\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
  
        Grano g4 = new Grano("Arroz","Chino");
        Receta rec = em.find(Receta.class, 1);   
        System.out.println(rec);
        rec.formRec_gra(g4);
        g4.formGra_rec(rec);

        em.persist(g4);
        em.persist(rec);
        em.getTransaction().commit();
    }
}

