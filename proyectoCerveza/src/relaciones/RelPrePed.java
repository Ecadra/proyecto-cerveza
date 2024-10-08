
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Pedido;
import proyectoCerveza.Presentacion;


public class RelPrePed {
     public static void main(String[] args){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Presentacion p6=new Presentacion(6);
        Pedido ped=em.find(Pedido.class,1);
        System.out.print(ped);
        ped.formPed_pre(p6);
        p6.formPre_ped(ped);
        
        em.persist(ped);
        em.persist(p6);
        
        em.getTransaction().commit();
        
     }
    
}
