/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Cerveza;
import proyectoCerveza.Presentacion;

/**
 *
 * @author edwin-993
 */
public class RelCerPre {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    // Crear una cerveza con el siguiente ID disponible
    Cerveza cerveza = new Cerveza(5, "Porter", 14.0f);
    Presentacion presentacion = em.find(Presentacion.class, 2);  // Usar el ID existente de Presentación

    // Verificar si la presentación existe
    if (presentacion == null) {
        System.out.println("La presentación no se encontró en la base de datos.");
        em.getTransaction().rollback();
        em.close();
        return;
    }

    // Relacionar Cerveza con Presentación
    cerveza.formCer_pre(presentacion);
    presentacion.formPre_cer(cerveza);

    em.persist(cerveza);
    em.persist(presentacion);
    em.getTransaction().commit();
    em.close();
}

}
