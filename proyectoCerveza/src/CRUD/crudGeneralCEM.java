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
import proyectoCerveza.Marca;

/**
 *
 * @author edwin-993
 */
public class crudGeneralCEM {
    public static void opCreateObjeto(Object objeto){
        //Se crea la conexion a la base de datos (Si no existe, se crea)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        em.close();
        emf.close();
        JOptionPane.showMessageDialog(null, "Se ha vuelto persistente la cerveza:\n" + objeto.toString());
    }
    public static void opUpdateObjeto(Cerveza cerveza){
        Cerveza cerActualizar;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        EntityManager em = emf.createEntityManager();
        
        //Inicia la transaccion con la base de datos
        em.getTransaction().begin();
        //Se busca la cerveza a actualizar mediante el ID
        cerActualizar = em.find(Cerveza.class, cerveza.getId_cerveza());
        //En caso de que no se encuentre la cerveza
        if (cerActualizar == null) {
            //Se cancela la operacion y se notifica al usuario
            JOptionPane.showMessageDialog(null,
                    "No se ha encontrado la cerveza a actualizar en la base de datos: \n" + cerveza.toString(),
                    "Error en crudCerveza -> opUpdateCerveza",
                    JOptionPane.ERROR_MESSAGE);
            em.getTransaction().rollback();
            em.close();
            emf.close();
            return;
        }else{
            //En caso de que se encuentre la cerveza se notifica por consola y se actualizan los datos de la cerveza (Excluendo las relaciones del objeto)
            System.out.println("Se ha encontrado la cerveza \n" + cerveza.toString());
            cerActualizar.setCer_graduacion(cerveza.getCer_graduacion());
            cerActualizar.setCer_nombre(cerveza.getCer_nombre());
            //Se compromete la relacion
            em.getTransaction().commit(); 
            //Se cierran las conexiones a la base de datos
            em.close();
            emf.close();
            return;
        }
    }
    public static List opReadObjetos(String ent,String field, String criterio){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        EntityManager em = emf.createEntityManager();
        //Dependiendo de la entidad
        switch(ent){
            case "Cerveza" : //Si es Cerveza
                //Se recuperan los objetos Cerveza desde la base de datos
                TypedQuery<Cerveza> consultaCerveza = null;//Objeto para la consulta
                List<Cerveza> resultadosCerveza = new ArrayList<Cerveza>();//Lista para los resultados
                consultaCerveza =  criterio.equals("") ? //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Cerveza c",Cerveza.class) : //Operador ternario, si no hay criterio, se seleccionan todos
                        em.createQuery("SELECT c FROM Cerveza c WHERE c." + field.toLowerCase() + " LIKE '%" + criterio + "%'",Cerveza.class);//Operador ternario, si hay criterio, se busca en el campo
                resultadosCerveza = consultaCerveza.getResultList();//Se guardan los resultados en la lista creada anteriormente
                System.out.println("Se han recuperado satisfactoriamente " + resultadosCerveza.size() + " cervezas");//Se notifica mediante consola
                em.close();
                emf.close();
                return resultadosCerveza;//Se regresa la lista de resultados
            case "Expendio":
                //Se recuperan los objetos Expendio desde la base de datos
                TypedQuery<Expendio> consultaExpendio = null; //Objeto para la consulta
                List<Expendio> resultadosExpendio = new ArrayList<Expendio>();//Lista para los resultados
                consultaExpendio =  criterio.equals("") ?
                        em.createQuery("SELECT c FROM Expendio c",Expendio.class) : 
                        em.createQuery("SELECT c FROM Expendio c WHERE c." + field.toLowerCase() + " LIKE '%" + criterio + "%'",Expendio.class);
                resultadosExpendio = consultaExpendio.getResultList();
                System.out.println("Se han recuperado satisfactoriamente " + resultadosExpendio.size() + " Expendios");
                em.close();
                emf.close();
                return resultadosExpendio;
            case "Marca":
                //Se recuperan los objetos Marca desde la base de datos
                TypedQuery<Marca> consultaMarca = null;
                List<Marca> resultadosMarca = new ArrayList<Marca>();
                consultaMarca =  criterio.equals("") ? 
                        em.createQuery("SELECT c FROM Marca c",Marca.class) : 
                        em.createQuery("SELECT c FROM Marca c WHERE c." + field.toLowerCase() + " LIKE '%" + criterio + "%'",Marca.class);
                resultadosMarca = consultaMarca.getResultList();
                System.out.println("Se han recuperado satisfactoriamente " + resultadosMarca.size() + " Marcas");
                em.close();
                emf.close();
                return resultadosMarca;
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
    @SuppressWarnings("unchecked")
    public static TableModel listToTM(List resultados, String entidad){
        Vector columnNames = new Vector();
        Vector rows = new Vector();
        switch(entidad){
            case"Cerveza":
                Cerveza cerveza;
                columnNames.addElement("Nombre");
                columnNames.addElement("Graduacion");
                Iterator it = resultados.iterator();
                while(it.hasNext()){
                    cerveza = (Cerveza) it.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(cerveza.getCer_nombre());
                    nuevaFila.addElement(cerveza.getCer_graduacion());
                    rows.addElement(nuevaFila);
                }
                break;
            case"Expendio":
                Expendio Expendio;
                columnNames.addElement("Nombre");
                columnNames.addElement("RFC");
                columnNames.addElement("Estado de operacion");
                columnNames.addElement("Direccion");
                columnNames.addElement("Telefono:");
                Iterator itExp = resultados.iterator();
                while(itExp.hasNext()){
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
            case"Marca":
                    Marca Marca;
                columnNames.addElement("Nombre");
                Iterator itMar = resultados.iterator();
                while(itMar.hasNext()){
                    Marca = (Marca) itMar.next();
                    Vector nuevaFila = new Vector();
                    nuevaFila.addElement(Marca.getMar_nombre());
                    rows.addElement(nuevaFila);
                }
                break;
        }//Termina switch de entidad
        return new DefaultTableModel(rows,columnNames);
    }
    public static TableModel opBuscar(String ent, String field, String criterio){
        TableModel tm = null;
        if(ent.equals("Cerveza")){
            List<Cerveza> resultados = opReadObjetos(ent,field,criterio);
            tm = listToTM(resultados,ent);   
        }
        if(ent.equals("Expendio")){
            List<Expendio> resultados = opReadObjetos(ent,field,criterio);
            tm = listToTM(resultados,ent);
        }
        if(ent.equals("Marca")){
            List<Marca> resultados = opReadObjetos(ent,field,criterio);
            tm =listToTM(resultados, ent);
        }
        return tm;
    }
    public Object opBuscarObjeto(String entidad, String criterio){
        Object objeto =null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        EntityManager em = emf.createEntityManager();
        
        switch(entidad){
            case"Cerveza":
                objeto = em.find(Cerveza.class,criterio);
                break;
            case"Expendio":
                objeto = em.find(Expendio.class, criterio);
                break;
            case"Marca":
                objeto = em.find(Marca.class, criterio);
                break;
            default:
                JOptionPane.showMessageDialog(null,
                        "El objeto que se desea buscar no se encuentra dado de alta en este método",
                        "Error en crudCerveza -> opBuscarObjeto",JOptionPane.ERROR_MESSAGE);
                break;
        }
        em.close();
        emf.close();
        return objeto;
 
    }
    public void opDeleteObjeto(String entidad, String criterio){
        Object objeto =null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        switch(entidad){
            case"Cerveza":
                objeto = em.find(Cerveza.class,criterio);
                em.remove(objeto);
                break;
            case"Expendio":
                objeto = em.find(Expendio.class, criterio);
                em.remove(objeto);
                break;
            case"Marca":
                objeto = em.find(Marca.class, criterio);
                em.remove(objeto);
                break;
            default:
                JOptionPane.showMessageDialog(null, "El objeto que se desea eliminar no se encuentra registrado en este método",
                        "Error en crudCerveza -> opDelete",JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
  
}

  
