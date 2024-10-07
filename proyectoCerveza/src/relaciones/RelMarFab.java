/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Fabricante;
import proyectoCerveza.Marca;

/**
 *
 * @author edwin-993
 */
public class RelMarFab {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    // Crear una marca con el siguiente ID disponible
    Marca marca = new Marca(5, "Marca5");  // Usar el ID existente de Marca
    Fabricante fabricante = em.find(Fabricante.class, "Modelo");  // Usar el ID existente de Fabricante

    // Verificar si el fabricante existe
    if (fabricante == null) {
        System.out.println("El fabricante no se encontró en la base de datos.");
        em.getTransaction().rollback();
        em.close();
        return;
    }

    // Relacionar Marca con Fabricante
    marca.formMar_fab(fabricante);
    fabricante.formFab_mar(marca);

    em.persist(marca);
    em.persist(fabricante);
    em.getTransaction().commit();
    em.close();
}

}
