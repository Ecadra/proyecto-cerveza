package relaciones;
import javax.persistence.*;
import proyectoCerveza.Pedido;
import proyectoCerveza.Presentacion;

/**
 *
 * @author ulseg
 */
public class RelPedPre {
    public static void main(String args[]) {
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim     
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\Users\\ximen\\Documents\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Pedido ped4 = new Pedido(4, (short)1500, "01/10/2024", "25/09/2024", 1550.0f, 8000.0f, 400.0f);
        Presentacion p1 = em.find(Presentacion.class, 1);   
        
        System.out.println(p1);
        ped4.formPed_pre(p1);
        p1.formPre_ped(ped4);        
        em.persist(p1);
        em.persist(ped4);
        em.getTransaction().commit();
    }
}
