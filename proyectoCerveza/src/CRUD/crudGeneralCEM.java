/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

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
import proyectoCerveza.Pedido;
import proyectoCerveza.Grano;
import proyectoCerveza.Envase;
import proyectoCerveza.Receta;
import proyectoCerveza.Presentacion;

/**
 *
 * @author edwin-993
 */
public class crudGeneralCEM {

    public void opPersistObjeto(String entidad, Object objeto) {
        //Se crea la conexion a la base de datos (Si no existe, se crea)
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
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
                Fabricante fabricante = em.find(Fabricante.class, marcaNueva.getMar_fab().getFab_nombre());

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
                Inventario inventario = em.find(Inventario.class, expendioNuevo.getExp_inv().getInv_cod());

                expendioNuevo.formExp_inv(inventario);
                inventario.formInv_exp(expendioNuevo);

                em.getTransaction().begin();
                em.persist(expendioNuevo);
                em.persist(inventario);
                em.getTransaction().commit();

                em.close();
                emf.close();
                break;
            case "Pedido":
                Pedido pedidoNuevo = (Pedido) objeto;
                
                //Se crea si existe un expendio que cumpla con lo requerido
                Expendio expendio = em.find(Expendio.class, pedidoNuevo.getPed_exp().getId_expendio());
                
                pedidoNuevo.formPed_exp(expendio);
                expendio.formExp_ped(pedidoNuevo);

                em.getTransaction().begin();
                em.persist(pedidoNuevo);
                em.persist(expendio);
                em.getTransaction().commit();

                em.close();
                emf.close();
                break;
            case "Receta":
                Receta recetaNuevo = (Receta) objeto;
                
                //Se crea si existe un grano
                Grano grano = em.find(Grano.class, recetaNuevo.getRec_gra().getGra_nombre());
                
                recetaNuevo.formRec_gra(grano);
                grano.formGra_rec(recetaNuevo);

                em.getTransaction().begin();
                em.persist(recetaNuevo);
                em.persist(grano);
                em.getTransaction().commit();

                em.close();
                emf.close();
                break;
            case "Grano":
                Grano granoNuevo = (Grano) objeto;
                //Devuelve solo el primer elemento de la lista
                Receta receta = em.find(Receta.class, granoNuevo.getGra_rec().get(0).getId_receta());

                granoNuevo.formGra_rec(receta);
                receta.formRec_gra(granoNuevo);

                em.getTransaction().begin();
                em.persist(receta);
                em.persist(granoNuevo);
                em.getTransaction().commit();
                
                em.close();
                emf.close();
                break;
            case "Envase":
                Envase envaseNuevo = (Envase) objeto;
                //Devuelve solo el primer elemento de la lista
                Presentacion presentacion = em.find(Presentacion.class, envaseNuevo.getEnv_pre().get(0).getPre_cod());

                envaseNuevo.formEnv_pre(presentacion);
                presentacion.formPre_env(envaseNuevo);

                em.getTransaction().begin();
                em.persist(presentacion);
                em.persist(envaseNuevo);
                em.getTransaction().commit();
                
                em.close();
                emf.close();
                break;

        }
        //JOptionPane.showMessageDialog(null, "Se ha vuelto persistente la cerveza:\n" + objeto.toString());
    }

    public void opUpdateObjeto(String entidad, Object objeto) {
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        switch (entidad) {
            case "Cerveza":
                Cerveza nuevosDatosCer= (Cerveza) objeto;
                Cerveza cerActualizar;
                
                //Inicia la transaccion con la base de datos
                em.getTransaction().begin();
                
                //Se busca la nuevosDatosCer a actualizar mediante el ID
                cerActualizar = em.find(Cerveza.class, nuevosDatosCer.getId_cerveza());
                
                //En caso de que se encuentre la cerveza se notifica por consola y se actualizan los datos de la nuevosDatosCer (Excluendo las relaciones del objeto)
                System.out.println("Se ha encontrado la cerveza a actualizar.\n"
                        + "Datos nuevosx:  \n" + nuevosDatosCer);
                cerActualizar.setCer_graduacion(nuevosDatosCer.getCer_graduacion());
                cerActualizar.setCer_nombre(nuevosDatosCer.getCer_nombre());
                //Se compromete la transaccion
                em.getTransaction().commit();
                //Se cierran las conexiones a la base de datos
                em.close();
                emf.close();
                break;
            case "Marca":
                Marca nuevosDatosMar = (Marca) objeto;
                Marca marActualizar;
                em.getTransaction().begin();
                
                marActualizar = em.find(Marca.class, nuevosDatosMar.getId_marca());
                
                System.out.println("Se ha encontrado la marca a actualizar. \n"
                        + "Datos anteriores: \n" + nuevosDatosMar);
                
                marActualizar.setMar_nombre(nuevosDatosMar.getMar_nombre());
                
                em.getTransaction().commit();
                em.close();
                emf.close();
                break;
            case"Expendio":
                Expendio nuevosDatosExp = (Expendio) objeto;
                Expendio expActualizar;
                em.getTransaction().begin();

                expActualizar = em.find(Expendio.class, nuevosDatosExp.getExp_nombre());

                System.out.println("Se ha encontrado la marca a actualizar. \n"
                        + "Datos nuevos: \n" + nuevosDatosExp);

                expActualizar.setExp_nombre(nuevosDatosExp.getExp_nombre());

                em.getTransaction().commit();
                em.close();
                emf.close();
            break;
            case "Pedido":
                Pedido nuevosDatosPed = (Pedido) objeto;
                Pedido pedActualizar;

                em.getTransaction().begin();
                // Busca el pedido en la base de datos utilizando el código del pedido
                pedActualizar = em.find(Pedido.class, nuevosDatosPed.getPed_codigo());

                // Verifica si el pedido fue encontrado
                if (pedActualizar != null) {
                    // Muestra los datos del pedido antes de la actualización
                    System.out.println("Se ha encontrado el pedido a actualizar."
                            + " \nDatos anteriores: \n" + pedActualizar);

                    // Actualiza los campos del pedido con los nuevos valores
                    pedActualizar.setPed_cantidad(nuevosDatosPed.getPed_cantidad());
                    pedActualizar.setPed_forden(nuevosDatosPed.getPed_forden());
                    pedActualizar.setPed_fdespacho(nuevosDatosPed.getPed_fdespacho());
                    pedActualizar.setPed_total(nuevosDatosPed.getPed_total());
                    pedActualizar.setPed_subtotal(nuevosDatosPed.getPed_subtotal());
                    pedActualizar.setPed_iva(nuevosDatosPed.getPed_iva());

                    // Muestra el pedido modificado
                    System.out.println("Pedido modificado: \n" + pedActualizar);

                    // Confirma la transacción
                    em.getTransaction().commit();

                    // Mensaje de confirmación
                    System.out.println("El pedido ha sido actualizado.");
                } else {
                    // Si no se encuentra el pedido, muestra un mensaje de error
                    System.out.println("No se encontró ningún pedido con el código: " + nuevosDatosPed.getPed_codigo());
                    em.getTransaction().rollback();  // Reversión de la transacción si no se encuentra
                }

                // Cierra la conexión a la base de datos
                em.close();
                emf.close();
                break;
            case "Receta":
                Receta nuevosDatosRec = (Receta) objeto;
                Receta recActualizar;

                // Inicia la transacción
                em.getTransaction().begin();

                // Busca la receta en la base de datos utilizando el ID de la receta
                recActualizar = em.find(Receta.class, nuevosDatosRec.getId_receta());

                // Verifica si la receta fue encontrada
                if (recActualizar != null) {
                    // Muestra los datos de la receta antes de la actualización
                    System.out.println("Se ha encontrado la receta a actualizar. \nDatos anteriores: \n" + recActualizar);

                    // Actualiza los campos de la receta con los nuevos valores
                    recActualizar.setRec_cantidad(nuevosDatosRec.getRec_cantidad());

                    // Muestra la receta modificada
                    System.out.println("Receta modificada: \n" + recActualizar);

                    em.getTransaction().commit();
                    System.out.println("La receta ha sido actualizada.");
                } else {
                    // Si no se encuentra la receta, muestra un mensaje de error
                    System.out.println("No se encontró ninguna receta con el ID: " + nuevosDatosRec.getId_receta());
                    em.getTransaction().rollback();  // Reversión de la transacción si no se encuentra
                }

                // Cierra la conexión a la base de datos
                em.close();
                emf.close();
                break;
            case "Grano":
                Grano nuevosDatosGra = (Grano) objeto;
                Grano graActualizar;

                em.getTransaction().begin();

                // Busca el grano en la base de datos utilizando el nombre del grano como identificador
                graActualizar = em.find(Grano.class, nuevosDatosGra.getGra_nombre());

                if (graActualizar != null) {
                    // Actualiza los campos del grano con los nuevos valores
                    graActualizar.setGra_nombre(nuevosDatosGra.getGra_nombre());
                    graActualizar.setGra_procedencia(nuevosDatosGra.getGra_procedencia());
                } else {
                    em.getTransaction().rollback();
                }

                em.getTransaction().commit();
                em.close();
                emf.close();
                break;
            case "Envase":
                Envase nuevosDatosEnv = (Envase) objeto;
                Envase envActualizar;

                em.getTransaction().begin();

                // Busca el grano en la base de datos utilizando el nombre del grano como identificador
                envActualizar = em.find(Envase.class, nuevosDatosEnv.getTipo_envase());

                if (envActualizar != null) {
                    // Actualiza los campos del grano con los nuevos valores
                    envActualizar.setTipo_envase(nuevosDatosEnv.getTipo_envase());
                    envActualizar.setCapacidad_ml(nuevosDatosEnv.getEnvase_capacidad());
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
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
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
            case "Pedido":
                // Se declara una consulta tipada de la clase Pedido, inicialmente vacía
                TypedQuery<Pedido> consultaPedido = null;
                // Se declara una lista para almacenar los resultados de la consulta
                List<Pedido> resultadosPedido = new ArrayList<Pedido>();

                // Si no se ha especificado ningún criterio de búsqueda, se seleccionan todos los pedidos
                consultaPedido = criterio.equals("")
                    ? em.createQuery("SELECT p FROM Pedido p", Pedido.class)  // Consulta para obtener todos los pedidos
                    : em.createQuery("SELECT p FROM Pedido p WHERE p." + field.toLowerCase() + " LIKE '%" + criterio + "%'", Pedido.class);  
                    // Si se especifica un criterio, filtra los resultados según el campo y el criterio proporcionados

                // Se obtienen los resultados de la consulta y se almacenan en la lista
                resultadosPedido = consultaPedido.getResultList();

                // Se imprime en consola el número de pedidos recuperados
                System.out.println("Se han recuperado satisfactoriamente " + resultadosPedido.size() + " Pedidos");

                // Se cierra la conexión con la base de datos
                em.close();
                emf.close();

                // Se retorna la lista de pedidos encontrados
                return resultadosPedido;

            case "Receta":
                //Se recuperan los objetos Inventario desde la base de datos
                TypedQuery<Receta> consultaReceta = null;
                List<Receta> resultadosReceta = new ArrayList<Receta>();
                consultaReceta = criterio.equals("")
                        ? em.createQuery("SELECT r FROM Receta r", Receta.class)
                        : em.createQuery("SELECT r FROM Receta r WHERE r." + field.toLowerCase() + " LIKE '%" + criterio + "%'", Receta.class);
                resultadosReceta = consultaReceta.getResultList();
                System.out.println("Se han recuperado satisfactoriamente " + resultadosReceta.size() + " Recetas");
                em.close();
                emf.close();
                return resultadosReceta;
            case "Grano":
                //Se recuperan los objetos Inventario desde la base de datos
                TypedQuery<Grano> consultaGrano = null;
                List<Grano> resultadosGrano = new ArrayList<Grano>();
                consultaGrano = criterio.equals("")
                        ? em.createQuery("SELECT g FROM Grano g", Grano.class)
                        : em.createQuery("SELECT g FROM Grano g WHERE g." + field.toLowerCase() + " LIKE '%" + criterio + "%'", Grano.class);
                resultadosGrano = consultaGrano.getResultList();
                System.out.println("Se han recuperado satisfactoriamente " + resultadosGrano.size() + " Granos");
                em.close();
                emf.close();
                return resultadosGrano;
            case "Envase":
                //Se recuperan los objetos Inventario desde la base de datos
                TypedQuery<Envase> consultaEnvase = null;
                List<Envase> resultadosEnvase = new ArrayList<Envase>();
                consultaEnvase = criterio.equals("")
                        ? em.createQuery("SELECT e FROM Envase e", Envase.class)
                        : em.createQuery("SELECT e FROM Envase e WHERE e." + field.toLowerCase() + " LIKE '%" + criterio + "%'", Envase.class);
                resultadosEnvase = consultaEnvase.getResultList();
                System.out.println("Se han recuperado satisfactoriamente " + resultadosEnvase.size() + " Envases");
                em.close();
                emf.close();
                return resultadosEnvase;            
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
                columnNames.addElement("Nombre");
                columnNames.addElement("Graduacion");
                columnNames.addElement("Marca");
                Iterator it = resultados.iterator();
                while (it.hasNext()) {
                    cerveza = (Cerveza) it.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(cerveza.getCer_nombre());
                    nuevaFila.addElement(cerveza.getCer_graduacion());
                    nuevaFila.addElement(cerveza.getCer_mar().getMar_nombre());
                    rows.addElement(nuevaFila);
                }
                break;
            case "Expendio":
                Expendio Expendio;
                columnNames.addElement("Nombre");
                columnNames.addElement("RFC");
                columnNames.addElement("Estado de operacion");
                columnNames.addElement("Direccion");
                columnNames.addElement("Telefono:");
                Iterator itExp = resultados.iterator();
                while (itExp.hasNext()) {
                    Expendio = (Expendio) itExp.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(Expendio.getExp_nombre());
                    nuevaFila.addElement(Expendio.getExp_rfc());
                    nuevaFila.addElement(Expendio.isExp_estado());
                    nuevaFila.addElement(Expendio.getExp_direccion().toString());
                    nuevaFila.addElement(Expendio.getExp_telefono());

                    rows.addElement(nuevaFila);
                }
                break;
            case "Marca":
                Marca Marca;
                columnNames.addElement("Nombre de la marca");
                Iterator itMar = resultados.iterator();
                while (itMar.hasNext()) {
                    Marca = (Marca) itMar.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(Marca.getMar_nombre());
                    rows.addElement(nuevaFila);
                }
                break;
            case "Pedido":
                Pedido pedido;
                columnNames.addElement("Código del pedido");
                columnNames.addElement("Expendio");
                columnNames.addElement("Presentacion");
                columnNames.addElement("Cantidad del pedido");
                columnNames.addElement("Fecha de orden");
                columnNames.addElement("Fecha de despacho");
                columnNames.addElement("Total");
                columnNames.addElement("Subtotal");
                columnNames.addElement("IVA");
                
                Iterator itPed = resultados.iterator();
                while (itPed.hasNext()) {
                    pedido = (Pedido) itPed.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(pedido.getPed_codigo());
                    nuevaFila.addElement(pedido.getPed_exp().getExp_nombre());
                    nuevaFila.addElement(pedido.getPed_pre().getPre_env().getTipo_envase());
                    nuevaFila.addElement(pedido.getPed_cantidad());
                    nuevaFila.addElement(pedido.getPed_forden());
                    nuevaFila.addElement(pedido.getPed_fdespacho());
                    nuevaFila.addElement(pedido.getPed_total());
                    nuevaFila.addElement(pedido.getPed_subtotal());
                    nuevaFila.addElement(pedido.getPed_iva());
                    
                    rows.addElement(nuevaFila);
                }
                break;
            case "Receta":
                Receta receta;
                columnNames.addElement("Grano");
                columnNames.addElement("Cantidad de la receta");
                
                Iterator itRec = resultados.iterator();
                while (itRec.hasNext()) {
                    receta = (Receta) itRec.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(receta.getRec_gra().getGra_nombre());
                    nuevaFila.addElement(receta.getRec_cantidad());
                    
                    rows.addElement(nuevaFila);
                }
                break;
            case "Grano":
                Grano grano;
                columnNames.addElement("Grano");
                columnNames.addElement("Procedencia");
                
                Iterator itGra = resultados.iterator();
                while (itGra.hasNext()) {
                    grano = (Grano) itGra.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(grano.getGra_nombre());
                    nuevaFila.addElement(grano.getGra_procedencia());
                    
                    rows.addElement(nuevaFila);
                }
                break;
            case "Envase":
                Envase envase;
                columnNames.addElement("Envase");
                columnNames.addElement("Capacidad en ml");
                
                Iterator itEnv = resultados.iterator();
                while (itEnv.hasNext()) {
                    envase = (Envase) itEnv.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(envase.getTipo_envase());
                    nuevaFila.addElement(envase.getEnvase_capacidad());
                    
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
        if (ent.equals("Pedido")) {
            List<Pedido> resultadosPedido;
            switch(field){
                case "Codigo":
                    resultadosPedido = opReadObjetos(ent, "ped_codigo", criterio);
                    tm = listToTM(resultadosPedido, ent);
                    break;
                case "Cantidad":
                    resultadosPedido = opReadObjetos(ent, "ped_cantidad", criterio);
                    tm = listToTM(resultadosPedido, ent);
                    break;
                case "Fecha de orden":
                    resultadosPedido = opReadObjetos(ent, "ped_forden", criterio);
                    tm = listToTM(resultadosPedido, ent);
                    break;
                case "Fecha de despacho":
                    resultadosPedido = opReadObjetos(ent, "ped_fdespacho", criterio);
                    tm = listToTM(resultadosPedido, ent);
                    break;
                case "Total":
                    resultadosPedido = opReadObjetos(ent, "ped_total", criterio);
                    tm = listToTM(resultadosPedido, ent);
                    break;
                case "Subtotal":
                    resultadosPedido = opReadObjetos(ent, "ped_subtotal", criterio);
                    tm = listToTM(resultadosPedido, ent);
                    break;
                case "IVA":
                    resultadosPedido = opReadObjetos(ent, "ped_iva", criterio);
                    tm = listToTM(resultadosPedido, ent);
                    break;
                default:
                    resultadosPedido = opReadObjetos(ent, field, criterio);
                    tm = listToTM(resultadosPedido, ent);
                    break;
            }
        }
        if (ent.equals("Receta")) {
            List<Receta> resultados = opReadObjetos(ent, field, criterio);
            tm = listToTM(resultados, ent);
        }
        if(ent.equals("Grano")){
            List<Grano> resultadosGrano;
            switch(field){
                case "Nombre grano":
                    resultadosGrano = opReadObjetos(ent, "gra_nombre", criterio);
                    tm = listToTM(resultadosGrano, ent);
                    break;
                case "Procedencia":
                    resultadosGrano = opReadObjetos(ent, "gra_procedencia", criterio);
                    tm = listToTM(resultadosGrano, ent);
                    break;
                default:
                    resultadosGrano = opReadObjetos(ent, field, criterio);
                    tm = listToTM(resultadosGrano, ent);
                    break;
            }
        }
        if(ent.equals("Envase")){
            List<Envase> resultadosEnvase;
            switch(field){
                case "Tipo de envase":
                    resultadosEnvase = opReadObjetos(ent, "tipo_envase", criterio);
                    tm = listToTM(resultadosEnvase, ent);
                    break;
                case "Capacidad":
                    resultadosEnvase = opReadObjetos(ent, "capacidad_ml", criterio);
                    tm = listToTM(resultadosEnvase, ent);
                    break;
                default:
                    resultadosEnvase = opReadObjetos(ent, field, criterio);
                    tm = listToTM(resultadosEnvase, ent);
                    break;
            }
        }
        
        return tm;
    }

    public Object opBuscarObjeto(String entidad, String criterio) { //Funcion que busca un objeto específico de la entidad deseada
        Object objeto = null;
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        int id;
        switch (entidad) {
            case "Cerveza":
                objeto = em.find(Cerveza.class, Integer.parseInt(criterio));
                em.close();
                emf.close();
                return objeto;
            case "Expendio":
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
                objeto = em.find(Fabricante.class, criterio); //Marca se busca mediante el nombreí
                em.close();
                emf.close();
                return objeto;
            case"Inventario":
                id = Integer.parseInt(criterio);
                objeto = em.find(Inventario.class,id); //Marca se busca mediante el nombreí
                em.close();
                emf.close();
                return objeto;
            case"Pedido":
                id = Integer.parseInt(criterio);
                objeto = em.find(Pedido.class,id); //Pedido se busca mediante el codigo
                em.close();
                emf.close();
                return objeto;
            case"Receta":
                objeto = em.find(Receta.class,criterio); //Receta se busca mediante la cantidad
                em.close();
                emf.close();
                return objeto;
            case"Grano":
                objeto = em.find(Inventario.class,criterio); //Grano se busca mediante el nombre
                em.close();
                emf.close();
                return objeto;
            case"Envase":
                objeto = em.find(Inventario.class,criterio); //Envase se busca mediante el tipo
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

    public void opDeleteObjeto(String entidad, String criterio) {//Funcion de CRUD para borrar una Cerveza
        Object objeto = null;
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
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
            case "Pedido":
                objeto = em.find(Pedido.class, criterio);
                em.remove(objeto);
                break;
            case "Receta":
                objeto = em.find(Receta.class, criterio);
                em.remove(objeto);
                break;
            case "Grano":
                objeto = em.find(Grano.class, criterio);
                em.remove(objeto);
                break;
            case "Envase":
                objeto = em.find(Envase.class, criterio);
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
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
        //Cesar    
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
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
        }
        return -1;
    }
}
