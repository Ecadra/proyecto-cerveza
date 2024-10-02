package proyectoCerveza;

import estructuras.Direccion;
import javax.persistence.*;

public class Test {

    public static void main(String[] args) {
        //Open a database connection
        //create a new database if it doesn´t exist yet:
        //Cesar    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\OneDrive\\Documentos\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Xim     
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\Users\\ximen\\Documents\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");

        //Edwin
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Presentacion p1 = new Presentacion("1", "Lata de alumnio", "Lata", "355ml");
        Presentacion p2 = new Presentacion("2", "Botella de cristal", "Botella", "210ml");
        Presentacion p3 = new Presentacion("3", "Botella de cristal", "Botella", "355ml");

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        Inventario a1 = new Inventario("1", false);
        Inventario a2 = new Inventario("2", true);
        Inventario a3 = new Inventario("3", true);

        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);

        Cerveza c1 = new Cerveza(1, "Clara", 15.5f);
        Cerveza c2 = new Cerveza(2, "Oscura", 10.5f);
        Cerveza c3 = new Cerveza(3, "Malta", 11.5f);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);

        Marca m1 = new Marca(1, "Modelo");
        Marca m2 = new Marca(2, "Victoria");
        Marca m3 = new Marca(3, "Corona");

        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m3);

        Direccion dir1 = new Direccion("Avenida Siempre Viva", 742, 3, "Springfield", 12345, "Illinois");
        Direccion dir2 = new Direccion("Calle Falsa", 123, 0, "Shelbyville", 54321, "Kentucky");
        Direccion dir3 = new Direccion("Boulevard de los Sueños", 456, 10, "Ciudad Fantasía", 67890, "California");
        
        Expendio e1 = new Expendio(1, "Malaquitas", "RFCexpendio1", true,dir1,"7721231231");
        Expendio e2 = new Expendio(2, "Cobrig", "RFCexpendio2", true,dir2,"1231234569");
        Expendio e3 = new Expendio(3, "Alexandria", "RFCexpendio3", true,dir3,"3216549871");
        
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        
        Lote l1= new Lote(),l2= new Lote(),l3 = new Lote();
        
        Receta r1= new Receta(),r2= new Receta(),r3 = new Receta();
        //Relacion de Cerveza con Marca
        c1.formCer_mar(m1);
        c2.formCer_mar(m2);
        c3.formCer_mar(m3);
        
        //Relacion de Cerveza con Lote
        c1.formCer_lot(l1);
        c2.formCer_lot(l2);
        c3.formCer_lot(l3);
        //Relacion de Cerveza con Presentacion
        c1.formCer_pre(p1);
        c2.formCer_pre(p2);
        c3.formCer_pre(p3);
        //Relacion de Cerveza con Receta
        c1.formCer_rec(r1);
        c2.formCer_rec(r2);
        c3.formCer_rec(r3);
        
        //Relacion de Marca con Cerveza
        m1.formMar_cer(c1);
        m2.formMar_cer(c2);
        m3.formMar_cer(c3);
        
        //Relacion de Expendio con Ventas
        e1.formExp_ven(v1);
        e2.formExp_ven(v2);
        e3.formExp_ven(v3);
        //Relacion de Expendio con Pedido
        e1.formExp_ped(pe1);
        e2.formExp_ped(pe2);
        e3.formExp_ped(pe3);
        //Relacion de Expendio con Inventario
        e1.formExp_inv(i1);
        e2.formExp_inv(i2);
        e3.formExp_inv(i3);
        
        a1.formPre_inv(p1);
        a2.formPre_inv(p2);
        a3.formPre_inv(p3);

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        
        //Persistencia de Cerveza, Marca y Expendio
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(m1);
        em.persist(m2);
        em.persist(m3);
        em.persist(e1);
        em.persist(e2);
        em.persist(e3);

        em.getTransaction().commit();
        em.close();;
        emf.close();

    }

}
