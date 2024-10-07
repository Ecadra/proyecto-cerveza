/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Fabricante;
import proyectoCerveza.Marca;

/**
 *
 * @author ximen
 */
public class RelFabMar {
    public static void main(String[] args) {
         // Cambiar ruta por la de ustedes
    //Xim
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\objectdb-2.9.0\\db\\cervezadb.odb");
    //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
    //Sebas
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\OneDrive\\Documentos\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
    //Cesar
        ////EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb\\db\\cervezadb.odb");
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Fabricante fab1 = new Fabricante("Sol", "5556789212");
        Marca m1 = em.find(Marca.class,1);  
        if (m1 != null) {
        System.out.println("Marca encontrada: " + m1.toString());
        } else {
        System.out.println("No se encontró la marca.");
        }
        m1.formMar_fab(fab1);
        fab1.formFab_mar(m1);
    
        em.persist(fab1);
        em.persist(m1);
        em.getTransaction().commit();
    }
    
    
}
