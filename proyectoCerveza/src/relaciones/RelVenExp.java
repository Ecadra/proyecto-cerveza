/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Expendio;
import proyectoCerveza.Venta;

/**
 *
 * @author ximen
 */
public class RelVenExp {

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
        Venta ve1 = new Venta(6,207,"07/10/2024",206.70f);
        Expendio exp1 = em.find(Expendio.class,2);  
        if (exp1 != null) {
        System.out.println("Expendio encontrado: " + exp1.toString()+
                "\n----");
        exp1.printVentas();
        } else {
        System.out.println("No se encontró el Expendio");
        }
        ve1.formVe_exp(exp1);
        exp1.formExp_ven(ve1);
    
        em.persist(exp1);
        em.persist(ve1);
        em.getTransaction().commit();
    }
    
    
    
}
