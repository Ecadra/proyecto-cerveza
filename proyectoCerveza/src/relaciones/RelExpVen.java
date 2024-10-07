/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relaciones;

import estructuras.Direccion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import proyectoCerveza.Expendio;
import proyectoCerveza.Venta;

/**
 *
 * @author edwin-993
 */
//PASO LA PRUEBA
public class RelExpVen {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    Direccion dir1 = new Direccion("Avenida Siempre Viva3", 744, 5, "Springfield5", 12346, "Illinois6");
    // Crear un expendio con el siguiente ID disponible
    Expendio expendio = new Expendio(5, "Malaquitas3", "RFCexpendio3", true, dir1, "7721231231");
    Venta venta = em.find(Venta.class, 1);  // Usar el ID existente de Venta

    // Verificar si la venta existe
    if (venta == null) {
        System.out.println("La venta no se encontró en la base de datos.");
        em.getTransaction().rollback();
        em.close();
        return;
    }

    // Relacionar Expendio con Venta
    expendio.formExp_ven(venta);
    venta.formVe_exp(expendio);

    em.persist(expendio);
    em.persist(venta);
    em.getTransaction().commit();
    em.close();
}

}
