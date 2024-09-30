/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoCerveza;
import java.util.*;
import javax.persistence.*;
 //Xime

public class QFabricantes {
    
    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf= Persistence.
                createEntityManagerFactory("$objectdb/db/cervezadb.odb");
        EntityManager em = emf.createEntityManager();

        List<Fabricante> results = new ArrayList<Fabricante>();

        // Retrieve all the Cine objects from the database:
        TypedQuery<Fabricante> query= em.createQuery("SELECT a FROM Fabricante a", Fabricante.class);
        results = query.getResultList();
        for (Fabricante a : results) {
            System.out.println(a);
            a.printSedes();
        }

        // Close the database connection:
        em.close();
        emf.close();
    }
}
