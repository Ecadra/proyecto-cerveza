/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Fabricante;
import proyectoCerveza.Sede;

/**
 *
 * @author ximen
 */
public class RelFabSede {

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
        
        Fabricante fab1 = new Fabricante("Modelo", "5556789212");
        Sede se1=em.find(Sede.class, "Cerveceria Artesanal");  
        if (se1 != null) {
        System.out.println("Sede encontrada: " + se1.toString());
        } else {
        System.out.println("No se encontró la Sede.");
        }
        se1.formSe_fab(fab1);
        fab1.formFab_se(se1);
    
        em.persist(fab1);
        em.persist(se1);
        em.getTransaction().commit();
    }
    
    
}
