
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Inventario;
import proyectoCerveza.Presentacion;


public class RelInvPre {
     public static void main (String[] args){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Inventario i5=new Inventario(5,20.1f,false);
        Presentacion pre=em.find(Presentacion.class, "3");
        System.out.print(pre);
        i5.formInv_pre(pre);
        pre.formPre_inv(i5);
        
        em.persist(i5);
        em.persist(pre);
        
        em.getTransaction().commit();
     }
}
