/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Venta;
import proyectoCerveza.Inventario;


public class RelVenInv {
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
        Venta ve1 = new Venta(7,2000,"17/10/2024",500034);
        Inventario iv1 = em.find(Inventario.class,"1");  
        if (iv1 != null) {
        System.out.println("Inventario encontrado: " + iv1.toString());
        } else {
        System.out.println("No se encontró el inventario");
        }
        ve1.formVe_inv(iv1);
        iv1.formInv_ven(ve1);
        
        em.persist(ve1);
        em.persist(iv1);
        em.getTransaction().commit();
    }
    
}
