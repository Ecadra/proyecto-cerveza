
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Expendio;
import proyectoCerveza.Inventario;


public class RelInvExp {
     public static void main (String[] args){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Inventario i4=new Inventario("4",60.0f,false);
        Expendio exp=em.find(Expendio.class,3);
        System.out.print(exp);
        i4.formInv_exp(exp);
        exp.formExp_inv(i4);
        
        em.persist(i4);
        em.persist(exp);
        
        em.getTransaction().commit();
     }
}
