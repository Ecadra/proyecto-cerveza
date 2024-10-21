/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import estructuras.Direccion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.persistence.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import proyectoCerveza.Cerveza;
import proyectoCerveza.Expendio;
import proyectoCerveza.Fabricante;
import proyectoCerveza.Inventario;
import proyectoCerveza.Marca;

/**
 *
 * @author edwin-993
 */
public class crudGeneralCEM {
    
    //Cesar
    //public String ruta = "D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb";
    //Sebas
    public String ruta = "C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb";    //Xim
    //public String ruta = "C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb";
    //Edwin
    //public String ruta = "/home/edwin-993/cervezaodb/cervezadb.odb";


    public void opPersistObjeto(String entidad, Object objeto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();

        switch (entidad) {//Switch para crear los 3 objetos (Cerveza, Marca y Expendio)
            case "Cerveza": //Creacion del objeto cerveza (Incluyendo la relacion bidireccional con marca)
                Cerveza cervezaNueva = (Cerveza) objeto;

                //Se busca la marca con la que se relaciono el objeto en la interfaz
                Marca mar = em.find(Marca.class, cervezaNueva.getCer_mar().getId_marca());

                //NO ES NECESARIO VERIFICAR LA EXISTENCIA DE LA MARCA PORQUE LA COMBO SE RELLENA CON OBJETOS EXISTENTES
                //Relacionar la nueva cerveza con una marca ya existente
                cervezaNueva.formCer_mar(mar);  // Se establece la relacion de Cerveza a Marca
                mar.formMar_cer(cervezaNueva);  // Se establece la relacion Marca a Cerveza

                em.getTransaction().begin();//Inicia la transaccion
                //Persistencia de los objetos
                em.persist(cervezaNueva);
                em.persist(mar);
                em.getTransaction().commit();//Se compromete la transaccion con la base de datos

                //Se cierra la conexion
                em.close();
                emf.close();

                JOptionPane.showMessageDialog(null, "Se ha guardado la cerveza" + cervezaNueva); //Se manda mensaje de confirmacion
                break;
            case "Marca":
                Marca marcaNueva = (Marca) objeto;
                Fabricante fabricante = em.find(Fabricante.class, marcaNueva.getMar_fab().getId_fab());

                marcaNueva.formMar_fab(fabricante);
                fabricante.formFab_mar(marcaNueva);

                em.getTransaction().begin();
                em.persist(fabricante);
                em.persist(marcaNueva);
                em.getTransaction().commit();
                em.close();
                emf.close();
                break;
            case "Expendio":
                Expendio expendioNuevo = (Expendio) objeto;

                em.getTransaction().begin();
                em.persist(expendioNuevo);
                em.getTransaction().commit();

                em.close();
                emf.close();
                break;
        }
        //JOptionPane.showMessageDialog(null, "Se ha vuelto persistente la cerveza:\n" + objeto.toString());
    }

    public void opUpdateObjeto(String entidad, Object objeto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();
        switch (entidad) {
            case "Cerveza":
                Cerveza nuevosDatosCer = (Cerveza) objeto;
                Cerveza cerActualizar;
                
                //Inicia la transaccion con la base de datos
                em.getTransaction().begin();
                
                //Se busca la cerveza a actualizar en la base de datos mediante el id
                cerActualizar = em.find(Cerveza.class, nuevosDatosCer.getId_cerveza());
                //En caso de que se encuentre la cerveza se notifica por consola y se actualizan los datos de la nuevosDatosCer (Excluendo las relaciones del objeto)
                System.out.println("Se ha encontrado la cerveza a actualizar.\n"
                        + "Datos anteriores:  \n" + cerActualizar.toString()+
                        "Nuevos datos: \n" + nuevosDatosCer.toString());
                
                //Actualizacion de las relaciones si se ha cambiado la marca
                //Se recupera la (antigua)marca desde la base de datos
                Marca marcaBD = em.find(Marca.class, cerActualizar.getCer_mar().getId_marca()); 
                System.out.println("Marca anteriormente relacionada: " + marcaBD.toString());
                //Se recupera la (nueva)marca desde la base de datos
                Marca marcaSeleccionada = em.find(Marca.class, nuevosDatosCer.getCer_mar().getId_marca());
                System.out.println("Marca seleccionada en la interfaz: " + marcaSeleccionada.toString());
                
                if(marcaBD.getId_marca() != marcaSeleccionada.getId_marca()){ //Si se realizaron cambios en la relacion de la cerveza
                    System.out.println("Se han detectado cambios en la relacion... Actualizando en la base de datos");
                    //Se borra la relacion bidireccionalmente (tomando en cuenta la cerveza antigua)
                    marcaBD.dropMar_cer(cerActualizar);
                    cerActualizar.dropCer_mar();
                    
                    //Se actualiza la relacion con los nuevos datos de la interfaz
                    cerActualizar.formCer_mar(marcaSeleccionada);
                    marcaSeleccionada.formMar_cer(cerActualizar);
                    
                    //Se actualizan los datos de la cerveza de la base de datos, con los datos recuperados de la interfaz
                    cerActualizar.setCer_graduacion(nuevosDatosCer.getCer_graduacion());
                    cerActualizar.setCer_nombre(nuevosDatosCer.getCer_nombre());
                    
                    //Se guardan los cambios de todos los objetos modificados
                    em.persist(cerActualizar);
                    em.persist(marcaBD);
                    em.persist(marcaSeleccionada);
                }else{//En caso de que no se hayan realizado cambios en la relacion de Cerveza con Marca
                    System.out.println("No se han detectado cambios en la relacion... Actualizando solo los datos de Cerveza");
                    //Se actualizan los datos de la cerveza de la base de datos, con los datos recuperados de la interfaz
                    cerActualizar.setCer_graduacion(nuevosDatosCer.getCer_graduacion());
                    cerActualizar.setCer_nombre(nuevosDatosCer.getCer_nombre());
                    em.persist(cerActualizar);
                }
               
                //Se compromete la transaccion
                em.getTransaction().commit();
                //Se cierran las conexiones a la base de datos
                
                JOptionPane.showMessageDialog(null, "Se ha actualizado la cerveza " + nuevosDatosCer.getCer_nombre());
                em.close();
                emf.close();
                break;
            case "Marca":
                Marca nuevosDatosMar = (Marca) objeto;
                Marca marActualizar;
                em.getTransaction().begin();
                
                marActualizar = em.find(Marca.class, nuevosDatosMar.getId_marca());
                
                System.out.println("\nSe ha encontrado la marca a actualizar. \n"
                        + "Datos anteriores: \n" + nuevosDatosMar);
                Fabricante fabViejo = em.find(Fabricante.class, marActualizar.getMar_fab().getId_fab());
                System.out.println("\n\nFabricante anteriormente relacionado: " + fabViejo.toString());
                Fabricante fabNuevo = em.find(Fabricante.class, nuevosDatosMar.getMar_fab().getId_fab());
                
                System.out.println("\n\nFabricante seleccionado en la interfaz: " + fabNuevo.toString());
                
                if(fabViejo.getId_fab() == fabNuevo.getId_fab()){
                    System.out.println("\n\nNo se ha actualizado la relacion del objeto Marca - Fabricante... Actualizando solo los datos del objeto");
                    //Se actualiza el nombre
                    marActualizar.setMar_nombre(nuevosDatosMar.getMar_nombre());
                }else{
                    System.out.println("\n\nSe han detectado cambios en la relacion del objeto Marca - Fabricante... Actualizando relacion y datos del objeto");
                    //Se actualiza el nombre
                    marActualizar.setMar_nombre(nuevosDatosMar.getMar_nombre());
                    
                    //Se elimina el anterior fabricante de manera bidireccional
                    marActualizar.dropMar_fab(fabViejo);
                    fabViejo.dropFab_se(marActualizar);
                    
                    //Se hace una nueva relacion de manera bidireccional
                    fabNuevo.formFab_mar(marActualizar);
                    marActualizar.formMar_fab(fabNuevo);
                    
                }
                em.getTransaction().commit();
                System.out.println("Se ha actualizado la marca: " + marActualizar.toString());
                em.close();
                emf.close();
                break;
            case"Expendio":
                Expendio nuevosDatosExp = (Expendio) objeto;
                Expendio datosViejosExp;
                
                //Inicia la transaccion con la base de datos
                em.getTransaction().begin();
                
                //Se busca el expendio a actualizar en la base de datos mediante el id
                datosViejosExp = em.find(Expendio.class, nuevosDatosExp.getId_expendio());
                //En caso de que se encuentre la cerveza se notifica por consola y se actualizan los datos de la nuevosDatosCer (Excluendo las relaciones del objeto)
                System.out.println("\nSe ha encontrado el expendio a actualizar.\n"
                        + "Datos anteriores:  \n" + nuevosDatosExp.toString()+
                        "Nuevos datos: \n" + nuevosDatosExp.toString());
                
                datosViejosExp.setExp_direccion(nuevosDatosExp.getExp_direccion());
                datosViejosExp.setExp_nombre(nuevosDatosExp.getExp_nombre());
                datosViejosExp.setExp_rfc(nuevosDatosExp.getExp_rfc());
                datosViejosExp.setExp_telefono(nuevosDatosExp.getExp_telefono());
               
                //Se compromete la transaccion
                em.getTransaction().commit();
                //Se cierran las conexiones a la base de datos
                
                JOptionPane.showMessageDialog(null, "\n\nSe ha actualizado el expendio " + nuevosDatosExp.getExp_nombre());
                em.close();
                emf.close();
            break;
        }

    }

    public List opReadObjetos(String ent, String field, String criterio) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();
        //Dependiendo de la entidad
        switch (ent) {
            case "Cerveza": //Si es Cerveza
                //Se recuperan los objetos Cerveza desde la base de datos
                TypedQuery<Cerveza> consultaCerveza = null;//Objeto para la consulta
                List<Cerveza> resultadosCerveza = new ArrayList<Cerveza>();//Lista para los resultados
                consultaCerveza = criterio.equals("")
                        ? //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Cerveza c", Cerveza.class)
                        : //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Cerveza c WHERE c." + field + " LIKE '" + criterio + "%'", Cerveza.class);//Operador ternario, si hay criterio, se busca en el campo
                resultadosCerveza = consultaCerveza.getResultList();//Se guardan los resultados en la lista creada anteriormente
                System.out.println("Se han recuperado satisfactoriamente " + resultadosCerveza.size() + " cervezas");//Se notifica mediante consola
                em.close();
                emf.close();
                return resultadosCerveza;//Se regresa la lista de resultados
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
            case "Marca":
                //Se recuperan los objetos Marca desde la base de datos
                TypedQuery<Marca> consultaMarca = null;
                List<Marca> resultadosMarca = new ArrayList<Marca>();
                consultaMarca = criterio.equals("")
                        ? em.createQuery("SELECT c FROM Marca c", Marca.class)
                        : em.createQuery("SELECT c FROM Marca c WHERE c." + field.toLowerCase() + " LIKE '%" + criterio + "%'", Marca.class);
                resultadosMarca = consultaMarca.getResultList();
                System.out.println("Se han recuperado satisfactoriamente " + resultadosMarca.size() + " Marcas");
                em.close();
                emf.close();
                return resultadosMarca;
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
            default:
                JOptionPane.showMessageDialog(null,
                        "La clase que se intenta buscar no se encuentra registrada en este metodo",
                        "Error en crudCerveza -> opReadObjetos()",
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
            case "Cerveza":
                Cerveza cerveza;
                //Se crean las columnas que se desea aparezcan en el TableModel
                columnNames.addElement("Identificador");
                columnNames.addElement("Nombre");
                columnNames.addElement("Graduacion");
                columnNames.addElement("Marca");
                Iterator it = resultados.iterator();
                while (it.hasNext()) {
                    cerveza = (Cerveza) it.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(cerveza.getId_cerveza());
                    nuevaFila.addElement(cerveza.getCer_nombre());
                    nuevaFila.addElement(cerveza.getCer_graduacion());
                    nuevaFila.addElement(cerveza.getCer_mar().getMar_nombre());
                    rows.addElement(nuevaFila);
                }
                break;
            case "Expendio":
                Expendio Expendio;
                columnNames.addElement("Identificador");
                columnNames.addElement("Nombre");
                columnNames.addElement("RFC");
                columnNames.addElement("Estado de operacion");
                columnNames.addElement("Calle");
                columnNames.addElement("Numero exterior");
                columnNames.addElement("Numero interior");
                columnNames.addElement("Colonia");
                columnNames.addElement("Codigo postal");
                columnNames.addElement("Estado");
                columnNames.addElement("Telefono:");
                Iterator itExp = resultados.iterator();
                while (itExp.hasNext()) {
                    Expendio = (Expendio) itExp.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(Expendio.getId_expendio());
                    nuevaFila.addElement(Expendio.getExp_nombre());
                    nuevaFila.addElement(Expendio.getExp_rfc());
                    String activo = Expendio.isExp_estado() ? "Activo" : "Inactivo";
                    nuevaFila.addElement(activo);
                    Direccion direccion = Expendio.getExp_direccion();
                    nuevaFila.addElement(direccion.getCalle());
                    nuevaFila.addElement(direccion.getNumeroExt());
                    nuevaFila.addElement(direccion.getNumeroInt());
                    nuevaFila.addElement(direccion.getColonia());
                    nuevaFila.addElement(direccion.getEstado());
                    nuevaFila.addElement(Expendio.getExp_telefono());

                    rows.addElement(nuevaFila);
                }
                break;
            case "Marca":
                Marca Marca;
                columnNames.addElement("Identificador");
                columnNames.addElement("Nombre de la marca");
                columnNames.addElement("Fabricante");
                Iterator itMar = resultados.iterator();
                while (itMar.hasNext()) {
                    Marca = (Marca) itMar.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(Marca.getId_marca());
                    nuevaFila.addElement(Marca.getMar_nombre());
                    nuevaFila.addElement(Marca.getMar_fab().getFab_nombre());
                    rows.addElement(nuevaFila);
                }
                break;
        }//Termina switch de entidad
        return new DefaultTableModel(rows, columnNames);
    }

    public TableModel opBuscar(String ent, String field, String criterio) { //Funcion que regresa un TableModel especificando la entidad deseada, el campo por el que se filtra y el criterio
        TableModel tm = null;
        if (ent.equals("Cerveza")) {
            List<Cerveza> resultados;
            switch (field) {
                case "Nombre":
                    resultados = opReadObjetos(ent, "cer_nombre", criterio);
                    tm = listToTM(resultados, ent);
                    break;
                case "Graduacion":
                    resultados = opReadObjetos(ent, "cer_graduacion", criterio);
                    tm = listToTM(resultados, ent);
                    break;
                default:
                    resultados = opReadObjetos(ent, field, criterio);
                    tm = listToTM(resultados, ent);
                    break;
            }
        }
        if (ent.equals("Expendio")) {
            List<Expendio> resultadosExpendio;
            switch(field){
                case "Nombre":
                 resultadosExpendio = opReadObjetos(ent, "exp_nombre", criterio);
                 tm = listToTM(resultadosExpendio, ent);
                 break;
             case "RFC":
                 resultadosExpendio = opReadObjetos(ent, "exp_graduacion", criterio);
                 tm = listToTM(resultadosExpendio, ent);
                 break;
             case "Numero de telefono":
                 resultadosExpendio = opReadObjetos(ent,"exp_telefono",criterio);
             default:
                 resultadosExpendio = opReadObjetos(ent, field, criterio);
                 tm = listToTM(resultadosExpendio, ent);
                 break;

            }
        }
        if (ent.equals("Marca")) {
            List<Marca> resultados = opReadObjetos(ent, field, criterio);
            tm = listToTM(resultados, ent);
        }
        
        return tm;
    }

    public Object opBuscarObjeto(String entidad, String criterio) { //Funcion que busca un objeto específico de la entidad deseada
        Object objeto = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();
        int id;
        switch (entidad) {
            case "Cerveza":
                objeto = em.find(Cerveza.class, Integer.parseInt(criterio));
                em.close();
                emf.close();
                return objeto;
            case "Expendio":
                //objeto = em.find(Expendio.class, nameToID("Expendio", criterio));
             
                objeto = em.find(Expendio.class, Integer.parseInt(criterio));
                em.close();
                emf.close();
                return objeto;
            case "Marca":
                objeto = em.find(Marca.class, nameToID("Marca", criterio)); //Marca se busca mediante el nombreí
                em.close();
                emf.close();
                return objeto;
            case"Fabricante":
                objeto = em.find(Fabricante.class, Integer.parseInt(criterio)); //Marca se busca mediante el nombreí
                em.close();
                emf.close();
                return objeto;
            case"Inventario":
                id = Integer.parseInt(criterio);
                objeto = em.find(Inventario.class,id); //Marca se busca mediante el nombreí
                em.close();
                emf.close();
                return objeto;
            default:
                JOptionPane.showMessageDialog(null,
                        "El objeto que se desea buscar no se encuentra dado de alta en este método",
                        "Error en crudCerveza -> opBuscarObjeto", JOptionPane.ERROR_MESSAGE);
                break;
        }
        if (objeto == null) {
            JOptionPane.showMessageDialog(null, "El objeto no se ha encontrado en ningun caso");
        }
        return objeto;

    }

    public void opDeleteObjeto(String entidad, int criterio) {//Funcion de CRUD para borrar una Cerveza
        Object objeto = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        switch (entidad) {
            case "Cerveza":
                objeto = em.find(Cerveza.class, criterio);
                em.remove(objeto);
                break;
            case "Expendio":
                objeto = em.find(Expendio.class, criterio);
                em.remove(objeto);
                break;
            case "Marca":
                objeto = em.find(Marca.class, criterio);
                em.remove(objeto);
                break;
            default:
                JOptionPane.showMessageDialog(null, "El objeto que se desea eliminar no se encuentra registrado en este método",
                        "Error en crudCerveza -> opDelete", JOptionPane.ERROR_MESSAGE);
                break;
        }
        em.getTransaction().commit();
        // Close the database connection: 
        em.close();
        emf.close();
        System.out.println("Objeto eliminado: " + objeto.toString());
    }

    public int opMaxID(String entidad) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();
        switch (entidad) {
            case "Cerveza":
                //Se recuperan los objetos Expendio desde la base de datos
                TypedQuery<Integer> consultaCerveza = null; //Objeto para la consulta
                List<Integer> resultadosCerveza = new ArrayList<Integer>();//Lista para los resultados

                consultaCerveza = em.createQuery("SELECT MAX(c.id_cerveza) FROM Cerveza c", Integer.class);
                resultadosCerveza = consultaCerveza.getResultList();

                System.out.println("Se han recuperado satisfactoriamente " + resultadosCerveza.size() + " Cervezas");

                em.close();
                emf.close();
                return resultadosCerveza.get(0);
            case "Expendio":
                //Se recuperan los objetos Expendio desde la base de datos
                TypedQuery<Integer> consultaExpendio = null; //Objeto para la consulta
                List<Integer> resultadosExpendio = new ArrayList<Integer>();//Lista para los resultados

                consultaExpendio = em.createQuery("SELECT MAX(c.id_expendio) FROM Expendio c", Integer.class);
                resultadosExpendio = consultaExpendio.getResultList();

                System.out.println("Se han recuperado satisfactoriamente " + resultadosExpendio.size() + " Expendios");

                em.close();
                emf.close();
                return resultadosExpendio.get(0);
            case "Marca":
                //Se recuperan los objetos Expendio desde la base de datos
                TypedQuery<Integer> consultaMarca = null; //Objeto para la consulta
                List<Integer> resultadosMarca = new ArrayList<Integer>();//Lista para los resultados

                consultaMarca = em.createQuery("SELECT MAX(c.id_marca) FROM Marca c", Integer.class);
                resultadosMarca = consultaMarca.getResultList();

                System.out.println("Se han recuperado satisfactoriamente " + resultadosMarca.size() + " Marcas");

                em.close();
                emf.close();
                return resultadosMarca.get(0);
            default:
                JOptionPane.showMessageDialog(null, "El objeto a recuperar no se encuentra definido dentro de este método\n",
                        "Error en crudGeneralCEM -> opMaxID", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }//Funcion que recupera el ultimo ID que hay de la entidad desead

    public int nameToID(String entidad, String nombreEntidad) { //Funcion auxiliar para poder buscar las marcas que se relacionan con Cerveza
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
            EntityManager em = emf.createEntityManager();
        switch (entidad) {
            case "Marca":
                //Se recuperan los objetos Marca desde la base de datos
                TypedQuery<Integer> consultaMarca = null; //Objeto para la consulta
                List<Integer> resultadosMarca = new ArrayList<Integer>();//Lista para los resultados

                consultaMarca = em.createQuery("SELECT c.id_marca FROM Marca c WHERE c.mar_nombre = '" + nombreEntidad + "'", Integer.class);
                resultadosMarca = consultaMarca.getResultList();

                System.out.println("Se ha recuperado satisfactoriamente el ID de la marca " + nombreEntidad);

                em.close();
                emf.close();
                return resultadosMarca.get(0);
            case "Cerveza":
                //Se recuperan los objetos Cerveza desde la base de datos
                TypedQuery<Integer> consultaCerveza = null; //Objeto para la consulta
                List<Integer> resultadosCerveza = new ArrayList<Integer>();//Lista para los resultados

                consultaCerveza = em.createQuery("SELECT c.id_cerveza FROM Cerveza c WHERE c.cer_nombre = '" + nombreEntidad + "'", Integer.class);
                resultadosCerveza = consultaCerveza.getResultList();

                System.out.println("Se ha recuperado satisfactoriamente el ID de la cerveza " + nombreEntidad);

                em.close();
                emf.close();
                return resultadosCerveza.get(0);
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
                //Se recuperan los objetos Fabricante desde la base de datos
                TypedQuery<Integer> consultaFabricante = null; //Objeto para la consulta
                List<Integer> resultadosFabricante = new ArrayList<Integer>();//Lista para los resultados
                
                consultaFabricante = em.createQuery("SELECT c.id_fab FROM Fabricante c WHERE c.fab_nombre = '" + nombreEntidad + "'", Integer.class);
                resultadosFabricante = consultaFabricante.getResultList();

                System.out.println("Se ha recuperado satisfactoriamente el ID del Fabricante " + nombreEntidad);

                em.close();
                emf.close();
                return resultadosFabricante.get(0);
        }
        return -1;
    }
}
