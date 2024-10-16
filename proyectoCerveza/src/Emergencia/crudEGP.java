/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Emergencia;

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
import proyectoCerveza.Grano;
import proyectoCerveza.Pedido;
import proyectoCerveza.Presentacion;
import proyectoCerveza.Receta;

/**
 *
 * @author edwin-993
 */
public class crudEGP {

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
            case"Receta":
                Receta recetaNueva = (Receta) objeto;
                
                Grano gran = em.find(Grano.class,recetaNueva.getRec_gra().getGra_nombre());
                Cerveza cer = em.find(Cerveza.class, recetaNueva.getRec_cer().getId_cerveza());

                //NO ES NECESARIO VERIFICAR LA EXISTENCIA DE LA MARCA PORQUE LA COMBO SE RELLENA CON OBJETOS EXISTENTES
                //Relacionar la nueva receta con una marca ya existente
                
                recetaNueva.formRec_gra(gran);
                gran.formGra_rec(recetaNueva);
                recetaNueva.formRec_cer(cer);
                cer.formCer_rec(recetaNueva);

                em.getTransaction().begin();//Inicia la transaccion
                //Persistencia de los objetos
                em.persist(recetaNueva);
                em.persist(gran);
                em.persist(cer);
                em.getTransaction().commit();//Se compromete la transaccion con la base de datos

                //Se cierra la conexion
                em.close();
                emf.close();

                JOptionPane.showMessageDialog(null, "Se ha guardado el pedido" + recetaNueva); //Se manda mensaje de confirmacion
                break;
                
            case"Grano":
                Grano granoNueva = (Grano) objeto;
             
                em.getTransaction().begin();//Inicia la transaccion
                //Persistencia de los objetos
                em.persist(granoNueva);
                em.getTransaction().commit();//Se compromete la transaccion con la base de datos
                //Se cierra la conexion
                em.close();
                emf.close();

                JOptionPane.showMessageDialog(null, "Se ha guardado el pedido" + granoNueva); //Se manda mensaje de confirmacion
                break;
                
        }
        //JOptionPane.showMessageDialog(null, "Se ha vuelto persistente la cerveza:\n" + objeto.toString());
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
                System.out.println("Se han recuperado satisfactoriamente " + resultadosReceta.size() + " pedidos");//Se notifica mediante consola
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
                System.out.println("Se han recuperado satisfactoriamente " + resultadosGrano.size() + " pedidos");//Se notifica mediante consola
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
                System.out.println("Se han recuperado satisfactoriamente " + resultadosPresentacion.size() + " pedidos");//Se notifica mediante consola
                em.close();
                emf.close();
                return resultadosPresentacion;//Se regresa la lista de resultados
            
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
            case "Pedido":
                Pedido pedido;
                //Se crean las columnas que se desea aparezcan en el TableModel
                columnNames.addElement("Codigo");
                columnNames.addElement("Cantidad");
                columnNames.addElement("Fecha de orden");
                columnNames.addElement("Fecha de despacho");
                columnNames.addElement("Total de la orden");
                columnNames.addElement("Subtotal de la orden");
                columnNames.addElement("IVA");
                columnNames.addElement("Expendio");
                columnNames.addElement("Presentacion");
                Iterator it = resultados.iterator();
                while (it.hasNext()) {
                    pedido = (Pedido) it.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(pedido.getPed_codigo());
                    nuevaFila.addElement(pedido.getPed_cantidad());
                    nuevaFila.addElement(pedido.getPed_forden());
                    nuevaFila.addElement(pedido.getPed_fdespacho());
                    nuevaFila.addElement(pedido.getPed_total());
                    nuevaFila.addElement(pedido.getPed_subtotal());
                    nuevaFila.addElement(pedido.getPed_iva());
                    nuevaFila.addElement(pedido.getPed_exp().getExp_nombre());
                    nuevaFila.addElement(pedido.getPed_pre().getPre_cod());
                    
                    rows.addElement(nuevaFila);
                }
                break;
            case "Receta":
                Receta receta;
                //Se crean las columnas que se desea aparezcan en el TableModel
                columnNames.addElement("Identificador");
                columnNames.addElement("Cantidad");
                columnNames.addElement("Grano");
                columnNames.addElement("Cerveza");
                
                Iterator itRec = resultados.iterator();
                while (itRec.hasNext()) {
                    receta = (Receta) itRec.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(receta.getId_receta());
                    nuevaFila.addElement(receta.getRec_cantidad());
                    nuevaFila.addElement(receta.getRec_gra().getGra_nombre());
                    nuevaFila.addElement(receta.getRec_cer().getId_cerveza());
                    rows.addElement(nuevaFila);
                }
                break;
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
                case "Identificador":
                    resRec = opReadObjetos(ent, "id_receta", criterio);
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
                case "Nombre":
                    resGran = opReadObjetos(ent, "id_receta", criterio);
                    tm = listToTM(resGran, ent);
                    break;
                case "Grano":
                    resGran = opReadObjetos(ent, "rec_gra", criterio);
                    tm = listToTM(resGran, ent);
                    break;
                case "Cerveza":
                    resGran = opReadObjetos(ent, "rec_cer", criterio);
                    tm = listToTM(resGran, ent);
                    break;
                default:
                    resGran = opReadObjetos(ent, field, criterio);
                    tm = listToTM(resGran, ent);
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

        switch (entidad) {
            case "Cerveza":
                objeto = em.find(Cerveza.class, Integer.parseInt(criterio));
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
                objeto = em.find(Expendio.class, Integer.parseInt(criterio));
                em.close();
                emf.close();
                return objeto;
            case "Grano":
                objeto = em.find(Grano.class, criterio); //Grano se busca mediante el nombreí
                em.close();
                emf.close();
                return objeto;
            case "Presentacion":
                objeto = em.find(Presentacion.class, Integer.parseInt(criterio)); //Presentacion se busca mediante el nombreí
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
            JOptionPane.showMessageDialog(null, "El objeto no se ha recuperado");
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
