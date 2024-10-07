
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Cerveza;
import proyectoCerveza.Presentacion;


public class RelPreCer {
      public static void main(String[] args){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Presentacion p5 = new Presentacion(5);
        Cerveza cer=em.find(Cerveza.class,1);
        System.out.print(cer);
        cer.formCer_pre(p5);
        p5.formPre_cer(cer);
        
        em.persist(cer);
        em.persist(p5);
        
        em.getTransaction().commit();
      }
}
