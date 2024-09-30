
package proyectoCerveza;

import javax.persistence.*;
import java.util.AbstractCollection.*;

public class Test {
    
    public static void main(String [] args){
        //Open a database connection
        //create a new database if it doesn´t exist yet:
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("$objectdb/db/cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        Presentacion p1 =new Presentacion("1","Lata de alumnio", "Lata","355ml");
        Presentacion p2 =new Presentacion("2","Botella de cristal", "Botella","210ml");
        Presentacion p3 =new Presentacion("3","Botella de cristal", "Botella","355ml");
        
        Inventario a1 = new Inventario("1",false);
        Inventario a2 = new Inventario("2",true);
        Inventario a3 = new Inventario("3",true);
        
        a1.formPre_inv(p1);
        a2.formPre_inv(p2);
        a3.formPre_inv(p3);
        
        em.getTransaction().commit();
        em.close();;
        emf.close();
        
        
     }
    
    
    
}
