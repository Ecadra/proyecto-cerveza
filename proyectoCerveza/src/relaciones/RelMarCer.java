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
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    // Crear una cerveza con el siguiente ID disponible
    Marca marca = new Marca(4, "Marca4");  // Usar el ID existente de Marca
    Cerveza cerveza = em.find(Cerveza.class, 1);
    

    // Verificar si la marca existe
    if (cerveza == null) {
        System.out.println("La cerveza no se encontró en la base de datos.");
        em.getTransaction().rollback();
        em.close();
        return;
    }

    // Relacionar Marca con Cerveza
    cerveza.formCer_mar(marca);
    marca.formMar_cer(cerveza);

    em.persist(cerveza);
    em.persist(marca);
    em.getTransaction().commit();
    em.close();
}


}
