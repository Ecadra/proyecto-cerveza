/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package relaciones;
import estructuras.Direccion;
import javax.persistence.*;
import proyectoCerveza.Fabricante;
import proyectoCerveza.Sede;
/**
 *
 * @author ximen
 */
public class RelSedeFab {

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
  
        // Crear la dirección
        Direccion direccion1 = new Direccion("Av Rio", 18, 17, "Colonia Doctores", 43815, "Hidalgo");
        Sede s1 = new Sede("Cerveceria Artesanal", direccion1);
        Fabricante fab1 = em.find(Fabricante.class, "Modelo");
        if (fab1 != null) {
        System.out.println("Fabricante encontrado: " + fab1.toString());
        } else {
        System.out.println("No se encontró el fabricante.");
        }
        
        em.persist(s1);
        em.persist(fab1);
        em.getTransaction().commit();
    }
}
