//Crud de pedido, envase, receta y grano
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
import proyectoCerveza.Envase;
import proyectoCerveza.Expendio;
import proyectoCerveza.Grano;
import proyectoCerveza.Pedido;
import proyectoCerveza.Presentacion;
import proyectoCerveza.Receta;
/**
 *
 * @author ulseg
 */
public class crudPREG {
    //Cesar
    //public String ruta = "D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb";
    //Sebas
    public String ruta = "C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb";
    //Xim
    //public String ruta = "C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb";
    //Edwin
    //public String ruta = "/home/edwin-993/cervezaodb/cervezadb.odb";
    
    public void opPersistObjeto(String entidad, Object objeto) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();

        switch (entidad) {//Switch para crear los 3 objetos (Cerveza, Marca y Expendio)
            case"Pedido":
                Pedido pedidoNueva = (Pedido) objeto;
                
                Expendio exp = em.find(Expendio.class, pedidoNueva.getPed_exp().getId_expendio());
                Presentacion pre = em.find(Presentacion.class, pedidoNueva.getPed_pre().getPre_cod());        

                //NO ES NECESARIO VERIFICAR LA EXISTENCIA DE LA MARCA PORQUE LA COMBO SE RELLENA CON OBJETOS EXISTENTES
                //Relacionar la nueva pedido con una marca ya existente
                
                pedidoNueva.formPed_exp(exp);
                exp.formExp_ped(pedidoNueva);
                pedidoNueva.formPed_pre(pre);
                pre.formPre_ped(pedidoNueva);
                
                em.getTransaction().begin();//Inicia la transaccion
                //Persistencia de los objetos
                em.persist(pedidoNueva);
                em.persist(exp);
                em.persist(pre);
                em.getTransaction().commit();//Se compromete la transaccion con la base de datos

                //Se cierra la conexion
                em.close();
                emf.close();

                JOptionPane.showMessageDialog(null, "Se ha guardado el pedido" + pedidoNueva); //Se manda mensaje de confirmacion
                break;
            case "Receta":
                Receta recetaNuevo = (Receta) objeto;
                
                //Se crea si existe un grano
                Grano grano = em.find(Grano.class, recetaNuevo.getRec_gra().getId_grano());
                Cerveza cerveza = em.find(Cerveza.class, recetaNuevo.getRec_cer().getId_cerveza());

                recetaNuevo.formRec_gra(grano);
                grano.formGra_rec(recetaNuevo);
                
                recetaNuevo.formRec_cer(cerveza);
                cerveza.formCer_rec(recetaNuevo);
                
                em.getTransaction().begin();
                em.persist(recetaNuevo);
                em.persist(grano);
                em.persist(cerveza);
                em.getTransaction().commit();

                em.close();
                emf.close();
                break;
            case"Grano":
                Grano granoNuevo = (Grano) objeto;
                //Devuelve solo el primer elemento de la lista
                //Receta receta = em.find(Receta.class, granoNuevo.getGra_rec().get(0).getId_receta());

                //granoNuevo.formGra_rec(receta);
                //receta.formRec_gra(granoNuevo);

                em.getTransaction().begin();
                //em.persist(receta);
                em.persist(granoNuevo);
                em.getTransaction().commit();
                
                em.close();
                emf.close();
                break;
            case"Envase":
                Envase envaseNuevo = (Envase) objeto;
                //Devuelve solo el primer elemento de la lista
                //Presentacion presentacion = em.find(Presentacion.class, envaseNuevo.getEnv_pre().get(0).getPre_cod());

                //envaseNuevo.formEnv_pre(presentacion);
                //presentacion.formPre_env(envaseNuevo);

                em.getTransaction().begin();
                em.persist(envaseNuevo);
                em.getTransaction().commit();
                
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
            case "Pedido": //Si es Pedido
                //Se recuperan los objetos Pedido desde la base de datos
                TypedQuery<Pedido> consultaPedido = null;//Objeto para la consulta
                List<Pedido> resultadosPedido = new ArrayList<Pedido>();//Lista para los resultados
                consultaPedido = criterio.equals("")
                        ? //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Pedido c", Pedido.class)
                        : //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Pedido c WHERE c." + field + " LIKE '" + criterio + "%'", Pedido.class);//Operador ternario, si hay criterio, se busca en el campo
                resultadosPedido = consultaPedido.getResultList();//Se guardan los resultados en la lista creada anteriormente
                System.out.println("Se han recuperado satisfactoriamente " + resultadosPedido.size() + " pedidos");//Se notifica mediante consola
                em.close();
                emf.close();
                return resultadosPedido;//Se regresa la lista de resultados
            case "Receta": //Si es Receta
                //Se recuperan los objetos Receta desde la base de datos
                TypedQuery<Receta> consultaReceta = null;//Objeto para la consulta
                List<Receta> resultadosReceta = new ArrayList<Receta>();//Lista para los resultados
                consultaReceta = criterio.equals("")
                        ? //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Receta c", Receta.class)
                        : //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Receta c WHERE c." + field + " LIKE '" + criterio + "%'", Receta.class);//Operador ternario, si hay criterio, se busca en el campo
                resultadosReceta = consultaReceta.getResultList();//Se guardan los resultados en la lista creada anteriormente
                System.out.println("Se han recuperado satisfactoriamente " + resultadosReceta.size() + " recetas");//Se notifica mediante consola
                em.close();
                emf.close();
                return resultadosReceta;//Se regresa la lista de resultados
            case "Grano": //Si es Grano
                //Se recuperan los objetos Grano desde la base de datos
                TypedQuery<Grano> consultaGrano = null;//Objeto para la consulta
                List<Grano> resultadosGrano = new ArrayList<Grano>();//Lista para los resultados
                consultaGrano = criterio.equals("")
                        ? //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Grano c", Grano.class)
                        : //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Grano c WHERE c." + field + " LIKE '" + criterio + "%'", Grano.class);//Operador ternario, si hay criterio, se busca en el campo
                resultadosGrano = consultaGrano.getResultList();//Se guardan los resultados en la lista creada anteriormente
                System.out.println("Se han recuperado satisfactoriamente " + resultadosGrano.size() + " granos");//Se notifica mediante consola
                em.close();
                emf.close();
                return resultadosGrano;//Se regresa la lista de resultados
            case "Presentacion": //Si es Presentacion
                //Se recuperan los objetos Presentacion desde la base de datos
                TypedQuery<Presentacion> consultaPresentacion = null;//Objeto para la consulta
                List<Presentacion> resultadosPresentacion = new ArrayList<Presentacion>();//Lista para los resultados
                consultaPresentacion = criterio.equals("")
                        ? //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Presentacion c", Presentacion.class)
                        : //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Presentacion c WHERE c." + field + " LIKE '" + criterio + "%'", Presentacion.class);//Operador ternario, si hay criterio, se busca en el campo
                resultadosPresentacion = consultaPresentacion.getResultList();//Se guardan los resultados en la lista creada anteriormente
                System.out.println("Se han recuperado satisfactoriamente " + resultadosPresentacion.size() + " presentaciones");//Se notifica mediante consola
                em.close();
                emf.close();
                return resultadosPresentacion;//Se regresa la lista de resultados
            case "Envase": //Si es Presentacion
                //Se recuperan los objetos Presentacion desde la base de datos
                TypedQuery<Envase> consultaEnvase = null;//Objeto para la consulta
                List<Envase> resultadosEnvase = new ArrayList<Envase>();//Lista para los resultados
                consultaEnvase = criterio.equals("")
                        ? //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Envase c", Envase.class)
                        : //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Envase c WHERE c." + field + " LIKE '" + criterio + "%'", Envase.class);//Operador ternario, si hay criterio, se busca en el campo
                resultadosEnvase = consultaEnvase.getResultList();//Se guardan los resultados en la lista creada anteriormente
                System.out.println("Se han recuperado satisfactoriamente " + resultadosEnvase.size() + " envases");//Se notifica mediante consola
                em.close();
                emf.close();
                return resultadosEnvase;//Se regresa la lista de resultados          
            default:
                JOptionPane.showMessageDialog(null,
                        "La clase que se intenta buscar no se encuentra registrada en este metodo",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                break;
        }
        em.close();
        emf.close();
        return null; //En caso de que no exista la clase a buscar, se regresa null.
    }

    
    public void opUpdateObjeto(String entidad, Object objeto) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();
        switch (entidad) {
            case"Expendio":
                Expendio nuevosDatosExp = (Expendio) objeto;
                Expendio expActualizar;
                em.getTransaction().begin();

                expActualizar = em.find(Expendio.class, nuevosDatosExp.getId_expendio());

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
                graActualizar = em.find(Grano.class, nuevosDatosGra.getId_grano());

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
                envActualizar = em.find(Envase.class, nuevosDatosEnv.getId_envase());

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
    
    public TableModel listToTM(List resultados, String entidad) { //Clase para convertir de una lista de resultados a un TableModel
        Vector columnNames = new Vector();
        Vector rows = new Vector();
        switch (entidad) {
            case "Grano":
                Grano grano;
                //Se crean las columnas que se desea aparezcan en el TableModel
                columnNames.addElement("Nombre");
                columnNames.addElement("Procedencia");
                
                Iterator itGran = resultados.iterator();
                while (itGran.hasNext()) {
                    grano = (Grano) itGran.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(grano.getGra_nombre());
                    nuevaFila.addElement(grano.getGra_procedencia());
                    rows.addElement(nuevaFila);
                }
                break;
            case "Pedido":
                Pedido pedido;
                columnNames.addElement("Código del pedido");
                columnNames.addElement("Expendio");
                columnNames.addElement("Presentacion");
                columnNames.addElement("Capacidad");
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
                    nuevaFila.addElement(pedido.getPed_pre().getPre_env().getEnvase_capacidad());
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
                //Se crean las columnas que se desea aparezcan en el TableModel
                columnNames.addElement("Cantidad");
                columnNames.addElement("Grano");
                columnNames.addElement("Cerveza");
                
                Iterator itRec = resultados.iterator();
                while (itRec.hasNext()) {
                    receta = (Receta) itRec.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(receta.getRec_cantidad());
                    nuevaFila.addElement(receta.getRec_gra().getGra_nombre());
                    nuevaFila.addElement(receta.getRec_cer().getCer_nombre());
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
        if (ent.equals("Pedido")) {
            List<Pedido> resPed;
            switch (field) {            
                case "Codigo":
                    resPed = opReadObjetos(ent, "ped_codigo", criterio);
                    tm = listToTM(resPed, ent);
                    break;
                case "Cantidad":
                    resPed = opReadObjetos(ent, "ped_cantidad", criterio);
                    tm = listToTM(resPed, ent);
                    break;
                case "Total de la orden":
                    resPed = opReadObjetos(ent, "ped_total", criterio);
                    tm = listToTM(resPed, ent);
                    break;
                case "Expendio":
                    resPed = opReadObjetos(ent, "ped_exp", criterio);
                    tm = listToTM(resPed, ent);
                    break;
                case "Presentacion":
                    resPed = opReadObjetos(ent, "ped_pre", criterio);
                    tm = listToTM(resPed, ent);
                    break;
                default:
                    resPed = opReadObjetos(ent, field, criterio);
                    tm = listToTM(resPed, ent);
                    break;
            }
        }
        if (ent.equals("Receta")) {
            List<Receta> resRec;
            switch (field) {
                case "Cantidad":
                    resRec = opReadObjetos(ent, "rec_cantidad", criterio);
                    tm = listToTM(resRec, ent);
                    break;
                case "Grano":
                    resRec = opReadObjetos(ent, "rec_gra", criterio);
                    tm = listToTM(resRec, ent);
                    break;
                case "Cerveza":
                    resRec = opReadObjetos(ent, "rec_cer", criterio);
                    tm = listToTM(resRec, ent);
                    break;
                default:
                    resRec = opReadObjetos(ent, field, criterio);
                    tm = listToTM(resRec, ent);
                    break;
            }
        }
        if (ent.equals("Grano")) {
            List<Grano> resGran;
            switch (field) {
                case "Grano":
                    resGran = opReadObjetos(ent, "gra_nombre", criterio);
                    tm = listToTM(resGran, ent);
                    break;
                case "Procedencia":
                    resGran = opReadObjetos(ent, "gra_procedencia", criterio);
                    tm = listToTM(resGran, ent);
                    break;
                default:
                    resGran = opReadObjetos(ent, field, criterio);
                    tm = listToTM(resGran, ent);
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
    
    public Object opBuscarObjeto(String entidad, int criterio) { //Funcion que busca un objeto específico de la entidad deseada
        Object objeto = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();

        switch (entidad) {
            case "Cerveza":
                objeto = em.find(Cerveza.class, criterio);
                em.close();
                emf.close();
                return objeto;
            case "Pedido":
                objeto = em.find(Pedido.class, criterio);
                em.close();
                emf.close();
                return objeto;
            case "Receta":
                objeto = em.find(Receta.class, criterio);
                em.close();
                emf.close();
                return objeto;
            case "Expendio":
                objeto = em.find(Expendio.class, criterio);
                em.close();
                emf.close();
                return objeto;
            case "Grano":
                objeto = em.find(Grano.class, criterio); //Grano se busca mediante el nombre
                em.close();
                emf.close();
                return objeto;
            case "Presentacion":
                objeto = em.find(Presentacion.class, criterio); //Presentacion se busca mediante el nombreí
                em.close();
                emf.close();
                return objeto;
            default:
                JOptionPane.showMessageDialog(null,
                        "El objeto que se desea buscar no se encuentra dado de alta en este método",
                        "Error --> opBuscarObjeto", JOptionPane.ERROR_MESSAGE);
                break;
        }
        if (objeto == null) {
            JOptionPane.showMessageDialog(null, "El objeto no se ha recuperado");
        }
        return objeto;

    }
    
    public void opDeleteObjeto(String entidad, int criterio) {//Funcion de CRUD para borrar una Cerveza
        Object objeto = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        switch (entidad) {
            case "Pedido":
                objeto = em.find(Pedido.class, criterio);
                em.remove(objeto);
                break;
            case "Grano":
                objeto = em.find(Grano.class, criterio);
                em.remove(objeto);
                break;
            case "Receta":
                objeto = em.find(Receta.class, criterio);
                em.remove(objeto);
                break;
            default:
                JOptionPane.showMessageDialog(null, "El objeto que se desea eliminar no se encuentra registrado en este método",
                        "Error -> opDelete", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
    
    public int opMaxID(String entidad) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();
        switch (entidad) {
            case "Receta":
                //Se recuperan los objetos Expendio desde la base de datos
                TypedQuery<Integer> consultaReceta = null; //Objeto para la consulta
                List<Integer> resultadosReceta = new ArrayList<Integer>();//Lista para los resultados

                consultaReceta = em.createQuery("SELECT MAX(c.id_receta) FROM Receta c", Integer.class);
                resultadosReceta = consultaReceta.getResultList();

                System.out.println("Se han recuperado satisfactoriamente " + resultadosReceta.size() + " Recetas");

                em.close();
                emf.close();
                return resultadosReceta.get(0);
            case "Pedido":
                //Se recuperan los objetos Pedido desde la base de datos
                TypedQuery<Integer> consultaPedido = null; //Objeto para la consulta
                List<Integer> resultadosPedido = new ArrayList<Integer>();//Lista para los resultados

                consultaPedido = em.createQuery("SELECT MAX(c.ped_codigo) FROM Pedido c", Integer.class);
                resultadosPedido = consultaPedido.getResultList();

                System.out.println("Se han recuperado satisfactoriamente " + resultadosPedido.size() + " Pedidos");

                em.close();
                emf.close();
                return resultadosPedido.get(0);
            case "Envase":
                //Se recuperan los objetos envase desde la base de datos
                TypedQuery<Integer> consultaEnvase = null; //Objeto para la consulta
                List<Integer> resultadosEnvase = new ArrayList<Integer>();//Lista para los resultados

                consultaEnvase = em.createQuery("SELECT MAX(c.id_envase) FROM Envase c", Integer.class);
                resultadosEnvase = consultaEnvase.getResultList();

                System.out.println("Se han recuperado satisfactoriamente " + resultadosEnvase.size() + " envases");

                em.close();
                emf.close();
                return resultadosEnvase.get(0);
            case "Grano":
                //Se recuperan los objetos grano desde la base de datos
                TypedQuery<Integer> consultaGrano = null; //Objeto para la consulta
                List<Integer> resultadosGrano = new ArrayList<Integer>();//Lista para los resultados

                consultaGrano = em.createQuery("SELECT MAX(c.id_grano) FROM Grano c", Integer.class);
                resultadosGrano = consultaGrano.getResultList();

                System.out.println("Se han recuperado satisfactoriamente " + resultadosGrano.size() + " granos");

                em.close();
                emf.close();
                return resultadosGrano.get(0);
            default:
                JOptionPane.showMessageDialog(null, "El objeto a recuperar no se encuentra definido dentro de este método\n",
                        "Error en crudGeneralCEM -> opMaxID", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }
    
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
            case "Grano":
                //Se recuperan los objetos Grano desde la base de datos
                TypedQuery<Integer> consultaGrano = null; //Objeto para la consulta
                List<Integer> resultadosGrano = new ArrayList<Integer>();//Lista para los resultados

                consultaGrano = em.createQuery("SELECT c.id_grano FROM Grano c WHERE c.gra_nombre = '" + nombreEntidad + "'", Integer.class);
                resultadosGrano = consultaGrano.getResultList();

                System.out.println("Se ha recuperado satisfactoriamente el ID del Grano " + nombreEntidad);

                em.close();
                emf.close();
                return resultadosGrano.get(0);
            case "Envase":
                //Se recuperan los objetos Envase desde la base de datos
                TypedQuery<Integer> consultaEnvase = null; //Objeto para la consulta
                List<Integer> resultadosEnvase = new ArrayList<Integer>();//Lista para los resultados

                consultaEnvase = em.createQuery("SELECT c.id_envase FROM Envase c WHERE c.tipo_envase = '" + nombreEntidad + "'", Integer.class);
                resultadosEnvase = consultaEnvase.getResultList();

                System.out.println("Se ha recuperado satisfactoriamente el ID del Envase " + nombreEntidad);

                em.close();
                emf.close();
                return resultadosEnvase.get(0);
        }
        return -1;
    }    
}
