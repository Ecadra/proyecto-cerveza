
package relaciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Cerveza;
import proyectoCerveza.Lote;


public class RelLoteCer {
    public static void main (String[] args) throws ParseException{
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Lote l4=new Lote("4",250,fecha.parse("2024-9-5"),fecha.parse("2025-3-6"));
        Cerveza cer=em.find(Cerveza.class, 1);
        System.out.print(cer);
        
        l4.formLote_cer(cer);
        cer.formCer_lot(l4);
        
        em.persist(l4);
        em.persist(cer);
        
        em.getTransaction().commit();
        
    }
}
