/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relaciones;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Cerveza;
import proyectoCerveza.Marca;

/**
 *
 * @author edwin-993
 */
public class RelMarCer {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("/path/to/cervezadb.odb");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    // Crear una marca
    Marca marca = em.find(Marca.class, 7);
    Cerveza cerveza = em.find(Cerveza.class, 8);

    // Verificar si la cerveza existe
    if (cerveza == null) {
        System.out.println("La cerveza no se encontró en la base de datos.");
        em.getTransaction().rollback();
        em.close();
        return;
    }

    // Relacionar Marca con Cerveza
    

    em.persist(marca);
    em.persist(cerveza);
    em.getTransaction().commit();
    em.close();
}

}
