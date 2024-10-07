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
import proyectoCerveza.Inventario;

/**
 *
 * @author edwin-993
 */
//PASO LA PRUEBA
public class RelExpInv {
    public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    Direccion dir1 = new Direccion("Avenida Siempre Viva2", 743, 4, "Springfield2", 12345, "Illinois2");
    // Crear un expendio con el siguiente ID disponible
    Expendio expendio = new Expendio(6, "Malaquitas4", "RFCexpendio4", true, dir1, "7721231231");
    Inventario inventario = em.find(Inventario.class, "1");  // Usar el ID existente de Inventario

    // Verificar si el inventario existe
    if (inventario == null) {
        System.out.println("El inventario no se encontró en la base de datos.");
        em.getTransaction().rollback();
        em.close();
        return;
    }

    // Relacionar Expendio con Inventario
    expendio.formExp_inv(inventario);
    inventario.formInv_exp(expendio);

    em.persist(expendio);
    em.persist(inventario);
    em.getTransaction().commit();
    em.close();
}

}
