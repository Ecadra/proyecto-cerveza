
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Inventario;
import proyectoCerveza.Presentacion;


public class RelPreInv {
     public static void main(String[] args){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Presentacion p7=new Presentacion(7);
        Inventario inv = em.find(Inventario.class, 3);
        System.out.print(inv);
        p7.dropPre_inv(inv);
        inv.formInv_pre(p7);
        
        em.persist(p7);
        em.persist(inv);
        em.getTransaction().commit();
     }
}
