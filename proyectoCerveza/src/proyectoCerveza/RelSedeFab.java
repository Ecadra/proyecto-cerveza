/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectoCerveza;
import javax.persistence.*;
import estructuras.Direccion;
/**
 *
 * @author ximen
 */
public class RelSedeFab {

    public static void main(String[] args) {
     EntityManagerFactory emf= Persistence.createEntityManagerFactory
        ("$objectdb/db/cerveza.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
  
        // Crear la dirección
        Direccion direccion1 = new Direccion("Av Rio", 18, 17, "Colonia Doctores", 43815, "Hidalgo");

        // Crear la instancia de Sede con la dirección
        Sede s1 = new Sede("Modelo", direccion1);

        // Buscar el fabricante
        Fabricante f1 = em.find(Fabricante.class, "Fabrica1");

        // Relacionar Sede con Fabricante
        f1.formFab_se(s1);  // Relacionar fabricante con la sede
        s1.formSe_fab(f1);  // Relacionar sede con el fabricante

        // Persistir los objetos
        em.persist(s1);
        em.persist(f1);

    
        em.persist(s1);
        em.persist(f1);
        em.getTransaction().commit();
    }
    
}
