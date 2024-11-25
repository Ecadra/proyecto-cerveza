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
        
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
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
                JOptionPane.showMessageDialog(null, "Sede registrada correctamente."+sedActualizar);
                em.close();
                emf.close();
                break;
                
            case "Venta":
                Venta nuevosDatosVenta = (Venta) objeto;
                Venta venActualizar;
                em.getTransaction().begin();
                
                venActualizar = em.find(Venta.class, nuevosDatosVenta.getId_venta());
                
                System.out.println("\nSe ha encontrado la venta a actualizar. \n"
                        + "Datos anteriores: \n" + nuevosDatosVenta);
                
                Expendio expViejo = em.find(Expendio.class, venActualizar.getVen_exp().getId_expendio());
                System.out.println("\n\nExpendio anteriormente relacionado: " + expViejo.toString());
                Expendio expNuevo = em.find(Expendio.class, nuevosDatosVenta.getVen_exp().getId_expendio());
                System.out.println("\n\nExpendio seleccionado en la interfaz: " + expNuevo.toString());
                Inventario invViejo = em.find(Inventario.class, venActualizar.getVen_inv().getInv_cod());
                System.out.println("\n\nInventario anteriormente relacionado: " + invViejo.toString());
                Inventario invNuevo = em.find(Inventario.class, nuevosDatosVenta.getVen_inv().getInv_cod());
                System.out.println("\n\nInventario seleccionado en la interfaz: " + invNuevo.toString());
                
                
                if(expViejo.getId_expendio() == expNuevo.getId_expendio()){
                    System.out.println("\n\nNo se ha actualizado la relacion del objeto Expendio - Venta... Actualizando solo los datos del objeto");
                    //Se actualiza el nombre
                    venActualizar.setVen_cantidad(nuevosDatosVenta.getVen_cantidad());
                    venActualizar.setVen_fecha(nuevosDatosVenta.getVen_fecha());
                    venActualizar.setVen_total(nuevosDatosVenta.getVen_total());
                }else{
                    System.out.println("\n\nSe han detectado cambios en la relacion del objeto Sede - Fabricante... Actualizando relacion y datos del objeto");
                    //Se actualiza el nombre
                    venActualizar.setVen_cantidad(nuevosDatosVenta.getVen_cantidad());
                    venActualizar.setVen_fecha(nuevosDatosVenta.getVen_fecha());
                    venActualizar.setVen_total(nuevosDatosVenta.getVen_total());
                    
                    //Se elimina el anterior fabricante de manera bidireccional
                    venActualizar.dropVe_exp(expViejo);
                    expViejo.dropExp_ven(venActualizar);
                    
                    venActualizar.dropVe_inv(invViejo);
                    invViejo.dropInv_ven(venActualizar);
                    //Se hace una nueva relacion de manera bidireccional                    
                }
                em.getTransaction().commit();
                System.out.println("Se ha actualizado la sede: " + venActualizar.toString());
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
            
            case "Venta": // Si es Venta
    // Se recuperan los objetos Venta desde la base de datos
            TypedQuery<Venta> consultaVenta = null; // Objeto para la consulta
            List<Venta> resultadosVenta = new ArrayList<>(); // Lista para los resultados

            if (criterio.equals("")) {
                // Si no hay criterio, selecciona todos
                consultaVenta = em.createQuery("SELECT v FROM Venta v", Venta.class);
            } else {
                // Si hay criterio, valida el tipo del campo
                switch (field) {
                  case "Venta Total": // Campo numérico
    try {
        float valor = Float.parseFloat(criterio); // Convierte el criterio a float
        consultaVenta = em.createQuery(
            "SELECT v FROM Venta v WHERE v.ven_total = :valor", 
            Venta.class
        );
        consultaVenta.setParameter("valor", valor); // Asigna el valor exacto
    } catch (NumberFormatException e) {
        System.out.println("El criterio debe ser un número válido para 'ven_total'.");
        return resultadosVenta; // Devuelve una lista vacía si el criterio no es válido
    }
    break;

                    case "Fecha": // Campo de tipo Date
                    try {
                        // Convierte el criterio a Date (ajusta el formato según sea necesario)
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato de fecha
                        Date fecha = sdf.parse(criterio); // Convierte el criterio a Date
                        consultaVenta = em.createQuery(
                            "SELECT v FROM Venta v WHERE v.ven_fecha = :fecha", 
                            Venta.class
                        );
                        consultaVenta.setParameter("fecha", fecha); // Establece el parámetro de la fecha
                    } catch (ParseException e) {
                        System.out.println("El criterio debe estar en formato 'yyyy-MM-dd'.");
                        return resultadosVenta; // Devuelve una lista vacía si el formato es incorrecto
                }
                    break;


                    default: // Campo de tipo String
                        consultaVenta = em.createQuery(
                            "SELECT v FROM Venta v WHERE v." + field + " LIKE :criterio", 
                            Venta.class
                        );
                        consultaVenta.setParameter("criterio", criterio + "%");
                        break;
                }
            }

            // Ejecuta la consulta y guarda los resultados en la lista
            resultadosVenta = consultaVenta.getResultList();
            System.out.println("Se han recuperado satisfactoriamente " + resultadosVenta.size() + " venta(s)"); // Notifica en consola
            em.close();
            emf.close();
            return resultadosVenta;

            /*case "Venta":
                // Se recuperan los objetos Venta desde la base de datos
                TypedQuery<Venta> consultaVenta = null; // Objeto para la consulta
                List<Venta> resultadosVenta = new ArrayList<Venta>(); // Lista para los resultados

                // Si no hay criterio, seleccionamos todas las ventas
                if (criterio.equals("")) {
                consultaVenta = em.createQuery("SELECT v FROM Venta v", Venta.class);
                } else {
                 switch (field.toLowerCase()) {
                     case "id_venta": // Caso para buscar por id_venta
                try {
                    // Convertir el criterio a un número entero
                    int idBuscado = Integer.parseInt(criterio);
                    consultaVenta = em.createQuery("SELECT v FROM Venta v WHERE v.id_venta = :id", Venta.class);
                    consultaVenta.setParameter("id", idBuscado);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("Criterio de id_venta no es un número válido.");
                }
                break;
                     
                     
                     case "ven_fecha":
                try {
                    // Convertir el criterio de búsqueda a formato de fecha
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechaBuscada = sdf.parse(criterio);
                    // Hacer la consulta con la fecha formateada
                    consultaVenta = em.createQuery("SELECT v FROM Venta v WHERE v.ven_fecha = :fecha", Venta.class);
                    consultaVenta.setParameter("fecha", fechaBuscada);
                    } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("Formato de fecha incorrecto. Use dd/MM/yyyy.");
                    }
                   break;

                    case "ven_cantidad":
                // Si se busca por cantidad, convertir el criterio a número entero
                    try {
                    int cantidadBuscada = Integer.parseInt(criterio);
                    consultaVenta = em.createQuery("SELECT v FROM Venta v WHERE v.ven_cantidad = :cantidad", Venta.class);
                    consultaVenta.setParameter("cantidad", cantidadBuscada);
                     } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("Criterio de cantidad no es un número válido.");
                         }
                    break;

                    case "ven_total":
                    // Si se busca por total de venta, convertir el criterio a número decimal
                try {
                    double totalBuscado = Double.parseDouble(criterio);
                    consultaVenta = em.createQuery("SELECT v FROM Venta v WHERE v.ven_total = :total", Venta.class);
                    consultaVenta.setParameter("total", totalBuscado);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("Criterio de total de venta no es un número válido.");
                }
                break;

            default:
                // Para otros campos, utilizamos LIKE para buscar coincidencias parciales
                consultaVenta = em.createQuery("SELECT v FROM Venta v WHERE LOWER(v." + field + ") LIKE :criterio", Venta.class);
                consultaVenta.setParameter("criterio", "%" + criterio.toLowerCase() + "%");
                break;
                }
                }

            // Ejecutar la consulta y obtener los resultados
            if (consultaVenta != null) {
                resultadosVenta = consultaVenta.getResultList();
            System.out.println("Se han recuperado satisfactoriamente " + resultadosVenta.size() + " ventas");
            }

            em.close();
            emf.close();
            return resultadosVenta;*/

                
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
                columnNames.addElement("Fabricante");
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
                    nuevaFila.addElement(Sede.getSe_fab().getFab_nombre());
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
            columnNames.addElement("No de Venta");  // Corregido: Se añade la columna "No de Venta"
            columnNames.addElement("Cantidad vendida");
            columnNames.addElement("Fecha");
            columnNames.addElement("Total vendido");
            columnNames.addElement("Expendio");
            columnNames.addElement("Inventario");
            Iterator itVenta = resultados.iterator();
            while (itVenta.hasNext()) {
                venta = (Venta) itVenta.next();
                Vector nuevaFila = new Vector();
                nuevaFila.addElement(venta.getId_venta());  
                nuevaFila.addElement(venta.getVen_cantidad());  // Añadir "Cantidad vendida"
                nuevaFila.addElement(venta.getVen_fecha());  // Añadir "Fecha"
                nuevaFila.addElement(venta.getVen_total());  // Añadir "Total vendido"
                nuevaFila.addElement(venta.getVen_exp().getExp_nombre());
                nuevaFila.addElement(venta.getVen_inv().getInv_cod());
                rows.addElement(nuevaFila);
            }
            break;
    }
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
                
                case "No de Venta":
                 resultadosVenta = opReadObjetos(ent, "id_venta", criterio);
                 tm = listToTM(resultadosVenta, ent);
                 break;
                case "Cantidad vendida":
                 resultadosVenta = opReadObjetos(ent, "ven_cantidad", criterio);
                 tm = listToTM(resultadosVenta, ent);
                 break;
             case "Fecha":
                 resultadosVenta = opReadObjetos(ent, "ven_fecha", criterio);
                 tm = listToTM(resultadosVenta, ent);
                 break;
             case "Total vendido":
                 resultadosVenta = opReadObjetos(ent,"ven_total",criterio);
                 tm = listToTM(resultadosVenta, ent);
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
                objeto = em.find(Expendio.class, nameToID("Expendio",criterio));
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
 
        public void opDeleteObjeto(String entidad, int criterio) {
        Object objeto = null; 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();

    try {
        em.getTransaction().begin();
        
        switch (entidad) {
            case "Sede":
                objeto = em.find(Sede.class, criterio);
                break;
            case "Venta":
                objeto = em.find(Venta.class, criterio);
                break;
            default:
                JOptionPane.showMessageDialog(null, "El objeto que se desea eliminar no se encuentra registrado en este método",
                        "Error en crudCerveza -> opDelete", JOptionPane.ERROR_MESSAGE);
                return; 
        }
        
        if (objeto == null) {
            JOptionPane.showMessageDialog(null, "El objeto con el criterio proporcionado no existe.",
                    "Error en CRUD", JOptionPane.ERROR_MESSAGE);
            em.getTransaction().rollback();
            return;
        }

            em.remove(objeto);
            em.getTransaction().commit();
            } catch (Exception e) {
            em.getTransaction().rollback(); // Revierte si ocurre un error
                JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }   finally {
        em.close(); 
        emf.close();
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
