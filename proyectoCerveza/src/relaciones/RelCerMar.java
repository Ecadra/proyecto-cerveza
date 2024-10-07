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
public class RelCerMar {
    public static void main(String[] args){
         EntityManagerFactory emf= Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
  
        // Crear la dirección
        Cerveza cerveza1 = new Cerveza(7, "Cerveza7", 24.5f);
        Marca mar = em.find(Marca.class, 1);
        //Fabricante fab = em.find(Fabricante.class, "fab_nombre");

        //Verificar si el fabricante existe
        if (mar == null) {
            System.out.println("La marca no se encontró en la base de datos.");
            em.getTransaction().rollback(); // Deshacer la transacción si no se encontró el fabricante
            em.close();
            return; // Salir del método
        }

        // Relacionar Sede con Fabricante
        cerveza1.formCer_mar(mar);  // Relacionar fabricante con la sede
        mar.formMar_cer(cerveza1);  // Relacionar sede con el fabricante
        System.out.println("Cerveza: " + cerveza1);
        System.out.println("Marca: " + mar);
        em.persist(cerveza1);
        em.persist(mar);
        em.getTransaction().commit();
    }
}
