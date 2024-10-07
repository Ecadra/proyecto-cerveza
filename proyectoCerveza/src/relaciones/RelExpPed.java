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
import proyectoCerveza.Pedido;

/**
 *
 * @author edwin-993
 */
//PASO LA PRUEBA
public class RelExpPed {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Direccion dir1 = new Direccion("Avenida Siempre Viva2", 743, 4, "Springfield2", 12345, "Illinois2");
    // Crear un expendio con el siguiente ID disponible
    Expendio expendio = new Expendio(4, "Malaquitas2", "RFCexpendio2", true, dir1, "7721231231");
    Pedido pedido = em.find(Pedido.class, 1);  // Usar el ID existente de Pedido

    // Verificar si el pedido existe
    if (pedido == null) {
        System.out.println("El pedido no se encontró en la base de datos.");
        em.getTransaction().rollback();
        em.close();
        return;
    }

    // Relacionar Expendio con Pedido
    expendio.formExp_ped(pedido);
    pedido.formPed_exp(expendio);

    em.persist(expendio);
    em.persist(pedido);
    em.getTransaction().commit();
    em.close();
}

}
