/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import estructuras.Direccion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import proyectoCerveza.Cerveza;
import proyectoCerveza.Expendio;
import proyectoCerveza.Fabricante;
import proyectoCerveza.Inventario;
import proyectoCerveza.Marca;
import proyectoCerveza.Venta;
import proyectoCerveza.Sede;

/**
 *
 * @author ximen
 */
public class crudGeneralSI {
    
     //Cesar
    //public String ruta = "D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb";
    
    //Sebas
    //public String ruta = "C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb";
   
    //Edwin
    //public String ruta = "/home/edwin-993/cervezaodb/cervezadb.odb";
    
    //Xim
    public String ruta = "C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb";
    
    public void opPersistObjeto(String entidad, Object objeto) {    
        EntityManagerFactory emf= Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();

        switch (entidad) {//Switch para crear los 2 objetos (Sede y Venta)
            case "Sede": //Creacion del objeto Sede (Incluyendo la relacion bidireccional con fabricante)
                Sede sedeNuevo = (Sede) objeto;

                //Se busca el fabricante con la que se relaciono el objeto en la interfaz
               Fabricante fab = em.find(Fabricante.class,sedeNuevo.getSe_fab().getId_fab());
                //NO ES NECESARIO VERIFICAR LA EXISTENCIA DE LA MARCA PORQUE LA COMBO SE RELLENA CON OBJETOS EXISTENTES
                //Relacionar la nueva sede con un fabricante ya existente
                sedeNuevo.formSe_fab(fab); // Relacion Sede - Fabricante
                fab.formFab_se(sedeNuevo); //Relación Fabricante - Sedde

                em.getTransaction().begin();//Inicia la transaccion
                //Persistencia de los objetos
                em.persist(sedeNuevo);
                em.persist(fab);
                em.getTransaction().commit();//Se compromete la transaccion con la base de datos

                //Se cierra la conexion
                em.close();
                emf.close();

                JOptionPane.showMessageDialog(null, "Se ha guardado la Sede" + sedeNuevo); //Se manda mensaje de confirmacion
                break;
                
            case "Venta":
                Venta ventaNueva = (Venta) objeto;
                //Buscar los 2 objetos con relacion de venta inventario y expendio
                Inventario inv = em.find(Inventario.class, ventaNueva.getVen_inv().getInv_cod());
                Expendio exp = em.find(Expendio.class,ventaNueva.getVen_exp().getId_expendio());
       
                
                ventaNueva.formVe_inv(inv);
                inv.formInv_ven(ventaNueva);
                ventaNueva.formVe_exp(exp);
                exp.formExp_ven(ventaNueva);

                em.getTransaction().begin();
                em.persist(inv);
                em.persist(exp);
                em.persist(ventaNueva);
                em.getTransaction().commit();
                em.close();
                emf.close();
                break;
            
            case "Fabricante":
                Fabricante fabricanteNuevo = (Fabricante) objeto;     
                em.getTransaction().begin();
                em.persist(fabricanteNuevo);
                em.getTransaction().commit();
                em.close();
                emf.close();
                System.out.println("Fabricante registrado");
                JOptionPane.showMessageDialog(null, "Fabricante registrado correctamente."+fabricanteNuevo);
                break;
            
        }
    }
    
    
    
    //ACTUALIZAR SEDE Y VENTA
    public void opUpdateObjeto(String entidad, Object objeto) {
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        switch (entidad) {
            case "Sede":
                Sede nuevosDatosSede = (Sede) objeto;
                Sede sedActualizar;
                em.getTransaction().begin();
                
                sedActualizar = em.find(Sede.class, nuevosDatosSede.getId_sede());
                
                System.out.println("\nSe ha encontrado la sede a actualizar. \n"
                        + "Datos anteriores: \n" + nuevosDatosSede);
                Fabricante fabViejo = em.find(Fabricante.class, sedActualizar.getSe_fab().getId_fab());
                System.out.println("\n\nFabricante anteriormente relacionado: " + fabViejo.toString());
                Fabricante fabNuevo = em.find(Fabricante.class, nuevosDatosSede.getSe_fab().getId_fab());
                
                System.out.println("\n\nFabricante seleccionado en la interfaz: " + fabNuevo.toString());
                
                if(fabViejo.getId_fab() == fabNuevo.getId_fab()){
                    System.out.println("\n\nNo se ha actualizado la relacion del objeto Sede - Fabricante... Actualizando solo los datos del objeto");
                    //Se actualiza el nombre
                    sedActualizar.setSe_nombre(nuevosDatosSede.getSe_nombre());
                }else{
                    System.out.println("\n\nSe han detectado cambios en la relacion del objeto Sede - Fabricante... Actualizando relacion y datos del objeto");
                    //Se actualiza el nombre
                    sedActualizar.setSe_nombre(nuevosDatosSede.getSe_nombre());
                    
                    //Se elimina el anterior fabricante de manera bidireccional
                    sedActualizar.dropSe_fab(fabViejo);
                    fabViejo.dropFab_se(sedActualizar);
                    
                    //Se hace una nueva relacion de manera bidireccional
                    fabNuevo.formFab_se(sedActualizar);
                    sedActualizar.formSe_fab(fabNuevo);
                    
                }
                em.getTransaction().commit();
                System.out.println("Se ha actualizado la sede: " + sedActualizar.toString());
                em.close();
                emf.close();
                break;
                
            case "Venta":
                Venta nuevosDatosVen = (Venta) objeto;
                Venta venActualizar;
                em.getTransaction().begin();
                
                venActualizar = em.find(Venta.class, nuevosDatosVen.getId_venta());
                
                System.out.println("Se ha encontrado la venta a actualizar. \n"
                        + "Datos anteriores: \n" + nuevosDatosVen);
                venActualizar.setVen_cantidad(nuevosDatosVen.getVen_cantidad());
                venActualizar.setVen_fecha(nuevosDatosVen.getVen_fecha());
                venActualizar.setVen_total(nuevosDatosVen.getVen_total());
                em.getTransaction().commit();
                em.close();
                emf.close();
                break;
            case "Fabricante":
                Fabricante nuevosDatosFabricante = (Fabricante) objeto;
                Fabricante fabActualizar;

                em.getTransaction().begin();
                fabActualizar = em.find(Fabricante.class, nuevosDatosFabricante.getId_fab());

                if (fabActualizar != null) {
                    // Actualiza los campos del grano con los nuevos valores
                    fabActualizar.setFab_nombre(nuevosDatosFabricante.getFab_nombre());
                    fabActualizar.setFab_contacto(nuevosDatosFabricante.getFab_contacto());
                } else {
                    em.getTransaction().rollback();
                }

                em.getTransaction().commit();
                em.close();
                emf.close();
                break;
                
            
           
        }

    }
    
     public List opReadObjetos(String ent, String field, String criterio) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory(ruta);
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        //Dependiendo de la entidad
        switch (ent) {
            case "Sede": //Si es Cerveza
                //Se recuperan los objetos Cerveza desde la base de datos
                TypedQuery<Sede> consultaSede = null;//Objeto para la consulta
                List<Sede> resultadosSede = new ArrayList<Sede>();//Lista para los resultados
                consultaSede = criterio.equals("")
                        ? //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT s FROM Sede s", Sede.class)
                        : //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT s FROM Sede s WHERE s." + field + " LIKE '" + criterio + "%'", Sede.class);//Operador ternario, si hay criterio, se busca en el campo
                resultadosSede = consultaSede.getResultList();//Se guardan los resultados en la lista creada anteriormente
                System.out.println("Se han recuperado satisfactoriamente " + resultadosSede.size() + " sede(s)");//Se notifica mediante consola
                em.close();
                emf.close();
                return resultadosSede;//Se regresa la lista de resultados
            
            case "Venta":
            // Se recuperan los objetos Venta desde la base de datos
            TypedQuery<Venta> consultaVenta = null; // Objeto para la consulta
            List<Venta> resultadosVenta = new ArrayList<Venta>(); // Lista para los resultados

            // Si no hay criterio, seleccionamos todas las ventas
            if (criterio.equals("")) {
                consultaVenta = em.createQuery("SELECT v FROM Venta v", Venta.class);
            } else {
                // Si el campo es ven_fecha, tratamos de convertir el criterio a Date
                if (field.equals("ven_fecha")) {
                    try {
                        // Convertir el criterio de búsqueda a formato de fecha
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaBuscada = sdf.parse(criterio);

                        // Hacer la consulta con la fecha formateada
                        consultaVenta = em.createQuery("SELECT v FROM Venta v WHERE v.ven_fecha = :fecha", Venta.class);
                        consultaVenta.setParameter("fecha", fechaBuscada);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Para otros campos, utilizamos LIKE
                    consultaVenta = em.createQuery("SELECT v FROM Venta v WHERE v." + field.toLowerCase() + " LIKE '%" + criterio + "%'", Venta.class);
                }
            }

            // Ejecutar la consulta y obtener los resultados
            resultadosVenta = consultaVenta.getResultList();
            System.out.println("Se han recuperado satisfactoriamente " + resultadosVenta.size() + " ventas");
            em.close();
            emf.close();
            return resultadosVenta;
                
            case "Fabricante":
                //Se recuperan los objetos Fabricante desde la base de datos
                TypedQuery<Fabricante> consultaFabricante = null;
                List<Fabricante> resultadosFabricante = new ArrayList<Fabricante>();
                consultaFabricante = criterio.equals("")
                        ? em.createQuery("SELECT c FROM Fabricante c", Fabricante.class)
                        : em.createQuery("SELECT c FROM Fabricante c WHERE c." + field.toLowerCase() + " LIKE '%" + criterio + "%'", Fabricante.class);
                resultadosFabricante = consultaFabricante.getResultList();
                System.out.println("Se han recuperado satisfactoriamente " + resultadosFabricante.size() + " Fabricantes");
                em.close();
                emf.close();
                return resultadosFabricante;
            case "Inventario":
                //Se recuperan los objetos Inventario desde la base de datos
                TypedQuery<Inventario> consultaInventario = null;
                List<Inventario> resultadosInventario = new ArrayList<Inventario>();
                consultaInventario = criterio.equals("")
                        ? em.createQuery("SELECT c FROM Inventario c", Inventario.class)
                        : em.createQuery("SELECT c FROM Inventario c WHERE c." + field.toLowerCase() + " LIKE '%" + criterio + "%'", Inventario.class);
                resultadosInventario = consultaInventario.getResultList();
                System.out.println("Se han recuperado satisfactoriamente " + resultadosInventario.size() + " Inventarios");
                em.close();
                emf.close();
                return resultadosInventario;
                
             case "Expendio":
                //Se recuperan los objetos Expendio desde la base de datos
                TypedQuery<Expendio> consultaExpendio = null; //Objeto para la consulta
                List<Expendio> resultadosExpendio = new ArrayList<Expendio>();//Lista para los resultados
                consultaExpendio = criterio.equals("")
                        ? em.createQuery("SELECT c FROM Expendio c", Expendio.class)
                        : em.createQuery("SELECT c FROM Expendio c WHERE c." + field.toLowerCase() + " LIKE '%" + criterio + "%'", Expendio.class);
                resultadosExpendio = consultaExpendio.getResultList();
                System.out.println("Se han recuperado satisfactoriamente " + resultadosExpendio.size() + " Expendios");
                em.close();
                emf.close();
                return resultadosExpendio;
            default:
                JOptionPane.showMessageDialog(null,
                        "La clase que se intenta buscar no se encuentra registrada en este metodo",
                        "Error en crudSI -> opReadObjetos()",
                        JOptionPane.ERROR_MESSAGE);
                break;

        }
        em.close();
        emf.close();
        return null; //En caso de que no exista la clase a buscar, se regresa null.
    }
     
     public TableModel listToTM(List resultados, String entidad) { //Clase para convertir de una lista de resultados a un TableModel
        Vector columnNames = new Vector();
        Vector rows = new Vector();
        switch (entidad) {
            case "Sede":
                Sede Sede;
                columnNames.addElement("Código");
                columnNames.addElement("Nombre");
                columnNames.addElement("Calle");
                columnNames.addElement("Numero ext.");
                columnNames.addElement("Numero int.");
                columnNames.addElement("Col.");
                columnNames.addElement("C.P");
                columnNames.addElement("Estado");
                Iterator itExp = resultados.iterator();
                while (itExp.hasNext()) {
                    Sede = (Sede) itExp.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(Sede.getId_sede());
                    nuevaFila.addElement(Sede.getSe_nombre());
                    Direccion direccion = Sede.getSe_direccion();
                    nuevaFila.addElement(direccion.getCalle());
                    nuevaFila.addElement(direccion.getNumeroExt());
                    nuevaFila.addElement(direccion.getNumeroInt());
                    nuevaFila.addElement(direccion.getColonia());
                    nuevaFila.addElement(direccion.getCodigoPostal());
                    nuevaFila.addElement(direccion.getEstado());
                    rows.addElement(nuevaFila);
                }
                break;
            case "Venta":
                Venta venta;
                columnNames.addElement("No de Venta");
                columnNames.addElement("Cantidad vendida");
                columnNames.addElement("Fecha");
                columnNames.addElement("Total vendido");
                Iterator it = resultados.iterator();
                while (it.hasNext()) {
                    venta = (Venta) it.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(venta.getId_venta());
                    nuevaFila.addElement(venta.getVen_cantidad());
                    nuevaFila.addElement(venta.getVen_fecha());
                    nuevaFila.addElement(venta.getVen_total());
                    rows.addElement(nuevaFila);
                }
                break;
        }//Termina switch de entidad
        return new DefaultTableModel(rows, columnNames);
    }
     
    public TableModel opBuscar(String ent, String field, String criterio) { //Funcion que regresa un TableModel especificando la entidad deseada, el campo por el que se filtra y el criterio
        TableModel tm = null;
        if (ent.equals("Sede")) {
            List<Sede> resultados;
            switch (field) {
                case "Nombre":
                    resultados = opReadObjetos(ent, "se_nombre", criterio);
                    tm = listToTM(resultados, ent);
                    break;
                default:
                    resultados = opReadObjetos(ent, field, criterio);
                    tm = listToTM(resultados, ent);
                    break;
            }
        }
         if (ent.equals("Venta")) {
            List<Venta> resultadosVenta;
            switch(field){
                case "Cantidad Venta":
                 resultadosVenta = opReadObjetos(ent, "ve_cantidad", criterio);
                 tm = listToTM(resultadosVenta, ent);
                 break;
             case "Fecha":
                 resultadosVenta = opReadObjetos(ent, "ven_fecha", criterio);
                 tm = listToTM(resultadosVenta, ent);
                 break;
             case "Total de venta":
                 resultadosVenta = opReadObjetos(ent,"ven_total",criterio);
             default:
                 resultadosVenta = opReadObjetos(ent, field, criterio);
                 tm = listToTM(resultadosVenta, ent);
                 break;

            }
        
        }
        return tm;
    }
    
    public Object opBuscarObjeto(String entidad, String criterio) { //Funcion que busca un objeto específico de la entidad deseada
        Object objeto = null;
        EntityManagerFactory emf= Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();

        switch (entidad) {
            case "Sede":
                objeto = em.find(Sede.class,Integer.parseInt(criterio));
                em.close();
                emf.close();
                return objeto;
            case "Expendio":
                //objeto = em.find(Expendio.class,nameToID("Expendio", criterio));
                objeto = em.find(Expendio.class, Integer.parseInt(criterio));
                em.close();
                emf.close();
                return objeto;
            case "Venta":
                objeto = em.find(Venta.class, criterio); 
                em.close();
                emf.close();
                return objeto;
            case"Fabricante":
                objeto = em.find(Fabricante.class, nameToID("Fabricante",criterio)); 
                em.close();
                emf.close();
                return objeto;
            case"Inventario":
                int id = Integer.parseInt(criterio);
                objeto = em.find(Inventario.class,id); //Marca se busca mediante el nombreí
                em.close();
                emf.close();
                return objeto;
            default:
                JOptionPane.showMessageDialog(null,
                        "El objeto que se desea buscar no se encuentra dado de alta en este método",
                        "Error en crud -> opBuscarObjeto", JOptionPane.ERROR_MESSAGE);
                break;
        }
        if (objeto == null) {
            JOptionPane.showMessageDialog(null, "El objeto no se ha encontrado en ningun caso");
        }
        return objeto;

    }
 
   public void opDeleteObjeto(String entidad, String criterio) {//Funcion de CRUD para borrar una Cerveza
        Object objeto = null; 
        EntityManagerFactory emf= Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        switch (entidad) {
            case "Sede":
                objeto = em.find(Sede.class, criterio);
                em.remove(objeto);
                break;
            case "Venta":
                objeto = em.find(Expendio.class, criterio);
                em.remove(objeto);
                break;
                
        
            default:
                JOptionPane.showMessageDialog(null, "El objeto que se desea eliminar no se encuentra registrado en este método",
                        "Error en crudCerveza -> opDelete", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
   
       public int opMaxID(String entidad) {
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        switch (entidad) {
            case "Venta":
                //Se recuperan los objetos Expendio desde la base de datos
                TypedQuery<Integer> consultaExpendio = null; //Objeto para la consulta
                List<Integer> resultadosExpendio = new ArrayList<Integer>();//Lista para los resultados

                consultaExpendio = em.createQuery("SELECT MAX(v.id_venta) FROM Venta v", Integer.class);
                resultadosExpendio = consultaExpendio.getResultList();

                System.out.println("Se han recuperado satisfactoriamente " + resultadosExpendio.size() + " Ventas");

                em.close();
                emf.close();
                return resultadosExpendio.get(0);
            case "Fabricante":
                //Se recuperan los objetos Expendio desde la base de datos
                TypedQuery<Integer> consultaFabricante = null; //Objeto para la consulta
                List<Integer> resultadosFabricante = new ArrayList<Integer>();//Lista para los resultados

                consultaFabricante = em.createQuery("SELECT MAX(f.id_fab) FROM Fabricante f", Integer.class);
                resultadosFabricante = consultaFabricante.getResultList();

                System.out.println("Se han recuperado satisfactoriamente " + resultadosFabricante.size() + " fabricantes");

                em.close();
                emf.close();
                return resultadosFabricante.get(0);
            case "Sede":
                //Se recuperan los objetos Expendio desde la base de datos
                TypedQuery<Integer> consultaSede = null; //Objeto para la consulta
                List<Integer> resultadosSede = new ArrayList<Integer>();//Lista para los resultados

                consultaSede= em.createQuery("SELECT MAX(s.id_sede) FROM Sede s", Integer.class);
                resultadosSede = consultaSede.getResultList();

                System.out.println("Se han recuperado satisfactoriamente " + resultadosSede.size() + " sedes");

                em.close();
                emf.close();
                return resultadosSede.get(0);
            
            default:
                JOptionPane.showMessageDialog(null, "El objeto a recuperar no se encuentra definido dentro de este método\n",
                        "Error en crudGeneralSEV -> opMaxID", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }
       
    /* public String opMax(String entidad) {
    //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        switch (entidad) {
            case "Sede":
                //Se recuperan los objetos Expendio desde la base de datos
                TypedQuery<String> consultaSede = null; //Objeto para la consulta
                List<String> resultadosSede = new ArrayList<String>();//Lista para los resultados

                consultaSede = em.createQuery("SELECT s.se_nombre FROM Sede s ORDER BY s.se_nombre DESC", String.class);
                resultadosSede = consultaSede.getResultList();

                System.out.println("Se han recuperado satisfactoriamente " + resultadosSede.size() + " Sede(s)");

                em.close();
                emf.close();
                return resultadosSede.get(0);
            
            case "Fabricante":
                //Se recuperan los objetos Expendio desde la base de datos
                TypedQuery<String> consultaFabricante = null; //Objeto para la consulta
                List<String> resultadosFabricante = new ArrayList<String>();//Lista para los resultados

                consultaFabricante = em.createQuery("SELECT f.fab_nombre FROM Fabricante f ORDER BY f.fab_nombre DESC", String.class);
                resultadosFabricante = consultaFabricante.getResultList();

                System.out.println("Se han recuperado satisfactoriamente " + resultadosFabricante.size() + " Fabricante(s)");

                em.close();
                emf.close();
                return resultadosFabricante.get(0);   
             
            default:
                JOptionPane.showMessageDialog(null, "El objeto a recuperar no se encuentra definido dentro de este método\n",
                        "Error en crudGeneralCEM -> opMaxID", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }*/
     public int nameToID(String entidad, String nombreEntidad) { //Funcion auxiliar para Venta y Sede  
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        switch (entidad) {
            case "Expendio":
                //Se recuperan los objetos Expendio desde la base de datos
                TypedQuery<Integer> consultaExpendio = null; //Objeto para la consulta
                List<Integer> resultadosExpendio = new ArrayList<Integer>();//Lista para los resultados

                consultaExpendio = em.createQuery("SELECT c.id_expendio FROM Expendio c WHERE c.exp_nombre = '" + nombreEntidad + "'", Integer.class);
                resultadosExpendio = consultaExpendio.getResultList();

                System.out.println("Se ha recuperado satisfactoriamente el ID del Expendio " + nombreEntidad);

                em.close();
                emf.close();
                return resultadosExpendio.get(0);
            
            case "Fabricante":
                //Se recuperan los objetos Expendio desde la base de datos
                TypedQuery<Integer> consultaFabricante = null; //Objeto para la consulta
                List<Integer> resultadosFabricante = new ArrayList<Integer>();//Lista para los resultados

                consultaFabricante = em.createQuery("SELECT f.id_fab FROM Fabricante f WHERE f.fab_nombre = '" + nombreEntidad + "'", Integer.class);
                resultadosFabricante= consultaFabricante.getResultList();

                System.out.println("Se ha recuperado satisfactoriamente el ID de la Fabricante " + nombreEntidad);

                em.close();
                emf.close();
                return resultadosFabricante.get(0);
            
                
        }
        return -1;
    }

    
}
