
package relaciones;
import javax.persistence.*;
import proyectoCerveza.Pedido;
import proyectoCerveza.Expendio;

/**
 *
 * @author ulseg
 */
public class RelPedExp {
    public static void main(String args[]) {
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\Users\\ximen\\Documents\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Pedido ped5 = new Pedido(5, (short)20000, "10/11/2024", "1/11/2024", 1600.0f, 1000.0f, 800.0f);
        Expendio e3 = em.find(Expendio.class, 3);   
        
        System.out.println(e3);
        ped5.formPed_exp(e3);
        e3.formExp_ped(ped5);        
        em.persist(e3);
        em.persist(ped5);
        em.getTransaction().commit();
    }
}
