/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Cerveza;
import proyectoCerveza.Lote;

/**
 *
 * @author edwin-993
 */
public class RelCerLot {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    // Crear una cerveza con el siguiente ID disponible
    Cerveza cerveza = new Cerveza(4, "Amber", 12.0f);
    Lote lote = em.find(Lote.class, 1);  // Usar el ID existente de Lote

    // Verificar si el lote existe
    if (lote == null) {
        System.out.println("El lote no se encontró en la base de datos.");
        em.getTransaction().rollback();
        em.close();
        return;
    }

    // Relacionar Cerveza con Lote
    cerveza.formCer_lot(lote);
    lote.formLote_cer(cerveza);

    em.persist(cerveza);
    em.persist(lote);
    em.getTransaction().commit();
    em.close();
}

}
