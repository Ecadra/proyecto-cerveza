
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Envase;
import proyectoCerveza.Presentacion;

public class RelPreEnv {
    public static void main (String[] args){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Presentacion p4 = new Presentacion(4);
        Envase env= em.find(Envase.class,"Lata");
        System.out.print(env);
        env.formEnv_pre(p4);
        p4.formPre_env(env);
        
        em.persist(p4);
        em.persist(env);
        
        em.getTransaction().commit();
    }
}
