
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Inventario;
import proyectoCerveza.Venta;


public class RelInvVen {
     public static void main (String[] args){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Inventario i6 = new Inventario("6",20.1f,false);
        Venta ven =em.find(Venta.class, 1);
        System.out.print(ven);
        i6.formInv_ven(ven);
        ven.formVe_inv(i6);
        
        em.persist(i6);
        em.persist(ven);
        
        em.getTransaction().commit();
     }
}
