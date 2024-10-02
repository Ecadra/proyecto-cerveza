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
       //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ximen\\Documents\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
    //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
    //Sebas
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\OneDrive\\Documentos\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
    //Cesar
        ////EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb\\db\\cervezadb.odb");
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
  
        // Crear la dirección
        Direccion direccion1 = new Direccion("Av Rio", 18, 17, "Colonia Doctores", 43815, "Hidalgo");
        Sede s1 = new Sede("Modelo", direccion1);
        Fabricante fab = em.find(Fabricante.class, "Modelo");
        //Fabricante fab = em.find(Fabricante.class, "fab_nombre");

        //Verificar si el fabricante existe
        if (fab == null) {
            System.out.println("El fabricante no se encontró en la base de datos.");
            em.getTransaction().rollback(); // Deshacer la transacción si no se encontró el fabricante
            em.close();
            return; // Salir del método
        }

        // Relacionar Sede con Fabricante
        fab.formFab_se(s1);  // Relacionar fabricante con la sede
        s1.formSe_fab(fab);  // Relacionar sede con el fabricante
        System.out.println("Sede: " + s1);
        System.out.println("Fabricante: " + fab);
        em.persist(s1);
        em.persist(fab);
        em.getTransaction().commit();

        
      
    }
}
