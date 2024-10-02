
package test;

import estructuras.Direccion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.*;
import proyectoCerveza.Cerveza;
import proyectoCerveza.Expendio;
import proyectoCerveza.Fabricante;
import proyectoCerveza.Grano;
import proyectoCerveza.Inventario;
import proyectoCerveza.Lote;
import proyectoCerveza.Marca;
import proyectoCerveza.Pedido;
import proyectoCerveza.Presentacion;
import proyectoCerveza.Receta;
import proyectoCerveza.Sede;
import proyectoCerveza.Venta;

public class Test {
    
       public static void main(String[] args) throws ParseException {
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");

        //Open a database connection
        //create a new database if it doesn´t exist yet:
        //Cesar    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\OneDrive\\Documentos\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Xim     
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\Users\\ximen\\Documents\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Presentacion p1 = new Presentacion("1", "Lata de alumnio", "Lata", 355);
        Presentacion p2 = new Presentacion("2", "Botella de cristal", "Botella", 210);
        Presentacion p3 = new Presentacion("3", "Botella de cristal", "Botella", 355);

        Inventario i1 = new Inventario("1", false);
        Inventario i2 = new Inventario("2", true);
        Inventario i3 = new Inventario("3", true);

        Fabricante fab1 = new Fabricante("Modelo", "5556789212");
        Fabricante fab2 = new Fabricante("Corona", "5557809221");
        Fabricante fab3 = new Fabricante("Heineken", "5523409221");

        Direccion dir1 = new Direccion("Avenida Siempre Viva", 742, 3, "Springfield", 12345, "Illinois");
        Direccion dir2 = new Direccion("Calle Falsa", 123, 0, "Shelbyville", 54321, "Kentucky");
        Direccion dir3 = new Direccion("Boulevard de los Sueños", 456, 10, "Ciudad Fantasía", 67890, "California");

        Expendio e1 = new Expendio(1, "Malaquitas", "RFCexpendio1", true, dir1, "7721231231");
        Expendio e2 = new Expendio(2, "Cobrig", "RFCexpendio2", true, dir2, "1231234569");
        Expendio e3 = new Expendio(3, "Alexandria", "RFCexpendio3", true, dir3, "3216549871");

        Cerveza c1 = new Cerveza(1, "Clara", 15.5f);
        Cerveza c2 = new Cerveza(2, "Oscura", 10.5f);
        Cerveza c3 = new Cerveza(3, "Malta", 11.5f);

        Sede se1 = new Sede("Cerveceria Artesanal", dir1);
        Sede se2 = new Sede("Cervecería Hidalgo", dir2);
        Sede se3 = new Sede("Cervecería Hidalgo", dir3);

        Venta ve1 = new Venta("1", "2000", fecha.parse("2024-9-1"));
        Venta ve2 = new Venta("2", "358", fecha.parse("2024-9-21"));
        Venta ve3 = new Venta("3", "2765", fecha.parse("2024-9-11"));

        Marca m1 = new Marca(1, "Modelo");
        Marca m2 = new Marca(2, "Victoria");
        Marca m3 = new Marca(3, "Corona");
        
        Grano g1 = new Grano("Arroz Yamadanishiki", "Japon");
        Grano g2 = new Grano("Avena malteada", "Reino Unido");
        Grano g3 = new Grano("Cebada 2-row","EUA");
        
        Pedido pe1 = new Pedido(1, (short)200, "12/11/2022", "20/11/2022", 1200.0f, 600.0f, 300.0f);
        Pedido pe2 = new Pedido(2, (short)150, "15/10/2023", "25/10/2023", 800.0f, 600.0f, 200.0f);
        Pedido pe3 = new Pedido(3, (short)300, "01/09/2024", "10/09/2024", 1500.0f, 1000.0f, 500.0f);
        
        Receta r1 = new Receta("Maceración, filtración, hervido, fermentación, y embotellado");
        Receta r2 = new Receta("Macerar, hervir lúpulo, enfriar, fermentar y carbonatar");
        Receta r3 = new Receta("Lavar granos, cocción, fermentación secundaria y embotellado");
        
        Lote l1 = new Lote("1",500,fecha.parse("2024-9-5"),fecha.parse("2025-9-5"));
        Lote l2 = new Lote("1",500,fecha.parse("2024-9-5"),fecha.parse("2025-9-5"));
        Lote l3 = new Lote("1",500,fecha.parse("2024-9-5"),fecha.parse("2025-9-5"));

        
        ///
        l1.formLote_cer(c1);
        l2.formLote_cer(c2);
        l3.formLote_cer(c3);
        //Realcion Inventario Expendio
        i1.formInv_exp(e1);
        i2.formInv_exp(e2);
        i3.formInv_exp(e3);
        //Realción inventario presentación
        i1.formInv_pre(p1);
        i2.formInv_pre(p2);
        i3.formInv_pre(p3);
        
        //Relacion presentacion cerveza
        
        p1.formPre_cer(c1);
        p2.formPre_cer(c2);
        p3.formPre_cer(c3);

        //Relacion de Fabricante Sede
        fab1.formFab_se(se1);
        fab2.formFab_se(se2);
        fab3.formFab_se(se3);

        //Relacion Sede Fabricante
        se1.formSe_fab(fab1);
        se2.formSe_fab(fab2);
        se3.formSe_fab(fab3);

        //Relacion Venta - Expendio
        ve1.formVe_exp(e1);
        ve2.formVe_exp(e2);
        ve3.formVe_exp(e3);

        //Relacion Venta - Presentación
        ve1.formVe_pre(p1);
        ve2.formVe_pre(p2);
        ve3.formVe_pre(p3);

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
        e1.formExp_ven(ve1);
        e2.formExp_ven(ve2);
        e3.formExp_ven(ve3);
        
        //Relacion de Expendio con Pedido
        e1.formExp_ped(pe1);
        e2.formExp_ped(pe2);
        e3.formExp_ped(pe3);
        
        //Relacion de Expendio con Inventario
        e1.formExp_inv(i1);
        e2.formExp_inv(i2);
        e3.formExp_inv(i3);
        
        //Relacion de Grano con recetas
        g1.formGra_rec(r1);
        g2.formGra_rec(r2);
        g3.formGra_rec(r3);
        //Relacion de Receta con Grano
        r1.formRec_gra(g1);
        r2.formRec_gra(g2);
        r3.formRec_gra(g3);
        
        //Relacion de receta con cerveza
        r1.formRec_cer(c1);
        r2.formRec_cer(c2);
        r3.formRec_cer(c3);
        //Relacion de pedido con expendio
        pe1.formPed_exp(e1);
        pe2.formPed_exp(e2);
        pe3.formPed_exp(e3);
        //Relacion de pedido con presentacion
        pe1.formPed_pre(p1);
        pe2.formPed_pre(p2);
        pe3.formPed_pre(p3);

        



        em.persist(fab1);
        em.persist(fab2);
        em.persist(fab3);

        em.persist(se1);
        em.persist(se2);
        em.persist(se3);

        em.persist(ve1);
        em.persist(ve2);
        em.persist(ve3);

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);

        em.persist(i1);
        em.persist(i2);
        em.persist(i3);
        
        em.persist(r1);
        em.persist(r2);
        em.persist(r3);
        
        em.persist(pe1);
        em.persist(pe2);
        em.persist(pe3);
        
        em.persist(g1);
        em.persist(g2);
        em.persist(g3);


        em.getTransaction().commit();
        em.close();;
        emf.close();

    }

}
