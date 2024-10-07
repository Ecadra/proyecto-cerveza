/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Cerveza;
import proyectoCerveza.Receta;

/**
 *
 * @author edwin-993
 */
//PASO LA PRUEBA
public class RelCerRec {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    // Crear una cerveza con el siguiente ID disponible
    Cerveza cerveza = new Cerveza(6, "Blonde Ale", 13.0f);
    Receta receta = em.find(Receta.class, 1);  // Usar el ID existente de Receta

    // Verificar si la receta existe
    if (receta == null) {
        System.out.println("La receta no se encontró en la base de datos.");
        em.getTransaction().rollback();
        em.close();
        return;
    }

    // Relacionar Cerveza con Receta
    cerveza.formCer_rec(receta);
    receta.formRec_cer(cerveza);

    em.persist(cerveza);
    em.persist(receta);
    em.getTransaction().commit();
    em.close();
}

}
