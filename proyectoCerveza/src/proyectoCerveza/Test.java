
package proyectoCerveza;

import javax.persistence.*;
import java.util.AbstractCollection.*;

public class Test {
    
    public static void main(String [] args){
        //Open a database connection
        //create a new database if it doesn´t exist yet:
  //Cesar    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb\\db\\cervezadb.odb");
  //Sebas   
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\OneDrive\\Documentos\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
  //Xim     
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\Users\\ximen\\Documents\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
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
        
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        
        em.getTransaction().commit();
        em.close();;
        emf.close();
        
        
        
     }
    
    
    
}
