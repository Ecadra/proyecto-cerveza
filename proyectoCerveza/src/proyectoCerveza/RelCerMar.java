/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoCerveza;
import javax.persistence.*;
/**
 *
 * @author edwin-993
 */
public class RelCerMar {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$object/db/cerveaz.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Cerveza cer1 = new Cerveza(1,"Cerveza prueba",30.4f);
        Marca mar1 = new Marca(1,"Marca prueba");
        System.out.println("Cerveza: " + cer1);
        System.out.println("Marca: " + mar1);

        cer1.formCer_mar(mar1);
        mar1.formMar_cer(cer1);
        em.getTransaction().commit();
    }
}
