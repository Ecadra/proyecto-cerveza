
package CRUD;

import java.util.ArrayList;
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
import proyectoCerveza.Envase;
import proyectoCerveza.Expendio;
import proyectoCerveza.Inventario;
import proyectoCerveza.Lote;
import proyectoCerveza.Presentacion;

public class crudIPL {
    
    public void opCreate(String entidad, Object obj){
        //Se crea la conexion a la base de datos (Si no existe, se crea)
        //Cesar    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb")
        EntityManager em = emf.createEntityManager();
        
        
        //Switch para saber que tipo de objeto es (Presentación, Inventario y Lote) los objetos ya viene contruidos de la interfaz, aquí solo es
        //hacerlos persistente
        
        switch(entidad){
            case "Presentacion":
                Presentacion pre = (Presentacion)obj;
                
                Envase env=em.find(Envase.class, pre.getPre_env().getTipo_envase());//Ejemplo de herencia
                Cerveza cer=em.find(Cerveza.class, pre.getPre_cer().getId_cerveza());
                
                 
                pre.formPre_cer(cer);
                cer.formCer_pre(pre);
                pre.formPre_env(env);
                env.formEnv_pre(pre);
                
                em.getTransaction().begin();
                
                em.persist(cer);
                em.persist(env);
                em.persist(pre);
                
                em.getTransaction().commit();
                em.close();
                emf.close();
                
                System.out.print("\nSe ha guardado una presentación\n"+pre);
            break;
            
            case "Lote":
                Lote lote = (Lote)obj;
                
                 Cerveza cer2=em.find(Cerveza.class, lote.getLote_cer().getId_cerveza());//Ejemplo de herencia
                 
                 lote.formLote_cer(cer2);
                 cer2.formCer_lot(lote);
                 
                 em.getTransaction().begin();
                 
                em.persist(lote);
                em.persist(cer2);
                
                em.getTransaction().commit();
                em.close();
                emf.close();
                
                 System.out.print("\nSe ha guardado un lote \n"+lote);
                
            break;
            
            case "Inventario":
                
                Inventario inv =(Inventario)obj;
                
                Expendio exp=em.find(Expendio.class, inv.getInv_exp().getId_expendio());
                System.out.print("\nSe ha encotrado dentro de opCRUD el expendio\n"+exp);
                Presentacion pre2=em.find(Presentacion.class,inv.getInv_pre().getPre_cod());
                System.out.print("\nSe ha encotrado dentro de opCRUD la presentacion\n"+pre2);
                
                inv.formInv_exp(exp);
                exp.formExp_inv(inv);
                inv.formInv_pre(pre2);
                pre2.formPre_inv(inv);
                
                em.getTransaction().begin();
                
                em.persist(inv);
                em.persist(exp);
                em.persist(pre2);
                
                em.getTransaction().commit();
                em.close();
                emf.close();
                 System.out.print("\nSe ha guardado un inventario \n"+inv);
            break;
        }
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
   
    public List opRead(String ent, String field, String crit){
       //Se crea la conexion a la base de datos (Si no existe, se crea)
        //Cesar    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb")
        EntityManager em = emf.createEntityManager();
       
       switch(ent){
           case "Presentacion":
               
               TypedQuery<Presentacion>queryPresentacion=null;
               List<Presentacion>resultPre=null;
               if(crit.equals("")){
                   queryPresentacion=em.createQuery("SELECT c FROM Presentacion c",Presentacion.class);
               }else{
                   queryPresentacion=em.createQuery("SELECT c FROM Presentacion WHERE c."+field+" LIKE "+crit+ "%", Presentacion.class);
               }
               resultPre=queryPresentacion.getResultList();
               System.out.print("\nSe han recuperado "+resultPre.size()+ "presentaciones");
               em.close();
               emf.close();
               return resultPre;//Regresa una lista
               
           case "Lote":
               
               TypedQuery<Lote>queryLote=null;
               List<Lote>resultLote=null;
               
               if(crit.equals("")){
                   queryLote=em.createQuery("SELECT c FROM  Lote c", Lote.class);//(Query, objeto a buscar.clase
               }else{
                   queryLote=em.createQuery("SELECT c FROM Lote WHERE c."+field+" LIKE "+crit+" % ", Lote.class);
               }
               resultLote=queryLote.getResultList();
               System.out.print("\nSe han recuperado "+resultLote.size()+ " Lotes");
               em.close();
               emf.close();
               return resultLote;//Regresa una lista
            
           case "Inventario":
               
               TypedQuery<Inventario>queryInv=null;
               List<Inventario>resultInv=null;
               
               if(crit.equals("")){
                    queryInv=em.createQuery("SELECT c FROM  Inventario c", Inventario.class);//(Query, objeto a buscar.clase
               }else{
                   queryInv=em.createQuery("SELECT c FROM Inventario WHERE c."+field+" LIKE "+crit+" % ", Inventario.class);
               }
               resultInv=queryInv.getResultList();
               System.out.print("\nSe han recuperado "+resultInv.size()+ " Inventarios");
               em.close();
               emf.close();
               return resultInv;
               
           case "Envase":
               TypedQuery<Envase>queryEnv=null;
               List<Envase>resultEnv=null;
               
               if(crit.equals("")){
                   queryEnv=em.createQuery("SELECT c FROM Envase c", Envase.class);
               }else{
                   queryEnv=em.createQuery("SELECT c FROM Envase WHERE c."+field+" LIKE "+crit+" % ", Envase.class);
               }
               resultEnv=queryEnv.getResultList();
               System.out.print("\nSe han recuperado "+resultEnv.size()+ " Inventarios");
               em.close();
               emf.close();
               return resultEnv;
          default:
               JOptionPane.showMessageDialog(null, "Este metodo no abarca la clase que desea buscar");
            break;
       }
       
        em.close();
        emf.close();
        return null;
   }
   public TableModel listToTM(List resultX,String entidad){
       Vector columnNames = new Vector();
       Vector rows = new Vector();
       
       switch(entidad){//Inicia switch
           case "Presentacion":
               Presentacion presentacion;
               
               columnNames.addElement("Código de la presentación");
               columnNames.addElement("Tipo de envase");
               columnNames.addElement("Cerveza");
               Iterator itPre=resultX.iterator();
               while(itPre.hasNext()){
                   presentacion=(Presentacion)itPre.next();
                   Vector nuevaFila=new Vector();
                   nuevaFila.addElement(presentacion.getPre_cod());
                   nuevaFila.addElement(presentacion.getPre_env().getTipo_envase());
                   nuevaFila.addElement(presentacion.getPre_cer().getCer_nombre());
                   rows.addElement(nuevaFila);   
               }
            break;
            
            case "Lote":
               Lote lote;
               
               columnNames.addElement("Código de lote");
               columnNames.addElement("Cantidad");
               columnNames.addElement("Fecha de producción");
               columnNames.addElement("Fecha de caducidad");
               columnNames.addElement("Cerveza producida");
                       
               Iterator itLote=resultX.iterator();
               while(itLote.hasNext()){
                   lote=(Lote)itLote.next();
                   Vector nuevaFila=new Vector();
                   nuevaFila.addElement(lote.getLote_cod());
                   nuevaFila.addElement(lote.getCantidad());
                   nuevaFila.addElement(lote.getLote_fechaProduccion());
                   nuevaFila.addElement(lote.getLote_fechaCaducidad());
                   nuevaFila.addElement(lote.getLote_cer().getCer_nombre());
                   rows.addElement(nuevaFila);   
               }
            break;
            
            case "Inventario":
               Inventario inventario;
               
               columnNames.addElement("Código de inventario");
               columnNames.addElement("Cantidad");
               columnNames.addElement("Presentacion");
               columnNames.addElement("Precio unitario");
               columnNames.addElement("Existencia");
               columnNames.addElement("Expendio");
                       
               Iterator it=resultX.iterator();
               while(it.hasNext()){
                   inventario=(Inventario)it.next();
                   Vector nuevaFila=new Vector();
                   nuevaFila.addElement(inventario.getInv_cod());
                   nuevaFila.addElement(inventario.getCantidad());
                   nuevaFila.addElement(inventario.getInv_pre().getPre_cod());
                   nuevaFila.addElement(inventario.getPrecio_unitario());
                   nuevaFila.addElement(inventario.isExistencia());
                   nuevaFila.addElement(inventario.getInv_exp().getId_expendio());
                   rows.addElement(nuevaFila);   
               }
            break; 
            
            case "Envase":
                Envase envase;
                
                columnNames.addElement("Tipo de envase");
                columnNames.addElement("Capacidad en ml");
                
                Iterator itEnv= resultX.iterator();
                while(itEnv.hasNext()){
                    envase = (Envase)itEnv.next();
                    Vector nuevaFila = new Vector();

                    nuevaFila.addElement(envase.getTipo_envase());
                    nuevaFila.addElement(envase.getEnvase_capacidad());
                    rows.addElement(nuevaFila);
                }
                
            break;
       }//Fin del switch
       return new DefaultTableModel(rows,columnNames);
   }
   public TableModel opBuscar(String ent, String field, String crit){
       TableModel tm=null;
       if(ent.equals("Inventario")){//Inicio de if inventario
           List<Inventario>results;
           switch(field){//Inicio del switch
               case "Código de Inventario":
                   results=opRead(ent,"inv_cod",crit);
                   tm=listToTM(results,ent);
               break;
               case "Precio Unitario":
                   results=opRead(ent,"precion_unitario",crit);
                   tm=listToTM(results,ent);
               break;
               case "Cantidad":
                   results=opRead(ent,"cantidad",crit);
                   tm=listToTM(results,ent);
               break;
               
               case "Existencia":
                   results=opRead(ent,"existencia",crit);
                   tm=listToTM(results,ent);
               break;
               default:
                   results=opRead(ent,field,crit);
                   tm=listToTM(results, ent);
               break;
           }//Fin del switch
       }//Fin de if inventario
       
       if(ent.equals("Lote")){//Inicio de if lote
           List<Lote>results;
           switch(field){//Inicio del switch
               case "Código de Lote":
                   results=opRead(ent,"lote_cod",crit);
                   tm=listToTM(results,ent);
               break;
               case "Cantidad":
                   results=opRead(ent,"cantidad",crit);
                   tm=listToTM(results,ent);
               break;
               default:
                   results=opRead(ent,field,crit);
                   tm=listToTM(results, ent);
               break;
           }//Fin del switch
       }//Fin de if lote
       
       if(ent.equals("Presentacion")){//Inicio de if presentacion
           
           List<Presentacion>results=opRead(ent,field,crit);
           tm=listToTM(results,ent);
  
       }//Fin de if presentacion
       
       if(ent.equals("Envase")){//Envase
           List<Envase>results;
           
           switch(field){//switch
               case "Tipo de envase":
                   results=opRead(ent,"tipo_envase",crit);
                   tm=listToTM(results,ent);
               break;
               case "Capacidad":
                   results=opRead(ent,"capacidad_ml",crit);
                   tm=listToTM(results,ent);
               break;
               default:
                   results=opRead(ent,field,crit);
                   tm=listToTM(results, ent);
               break;
               
           }//switch fin
           
       }//Fin de envase
       
       return tm;
   }
   
   public void opDelte(String ent, String crit){
       Object obj=null;
        //Se crea la conexion a la base de datos (Si no existe, se crea)
        //Cesar    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb")
        EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       
       switch(ent){
           
           case "Inventario":
               obj=em.find(Inventario.class, crit);
               em.remove(obj);
           break;
           case "Lote":
               obj=em.find(Lote.class, crit);
               em.remove(obj);
           break;
           case "Presentacion":
               obj=em.find(Presentacion.class, crit);
               em.remove(obj);
           break;
           default:
               JOptionPane.showMessageDialog(null, "\nNo se ha encontrado nada que borrar","Error->opDelete",JOptionPane.ERROR_MESSAGE);
           break;
           
       }
   }
   
   public Object opBuscarObjeto(String ent, String crit){
       Object obj;
       System.out.print("\nLa entidad a buscar dentro de opBuscar es: "+ent);
       System.out.print("\nEl criterio de busqueda dentro de opBuscar es: "+crit);
        //Se crea la conexion a la base de datos (Si no existe, se crea)
        //Cesar    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb")
        EntityManager em = emf.createEntityManager();
       
       switch(ent){
           case "Lote":
               obj= em.find(Lote.class,Integer.parseInt(crit));
               em.close();
               emf.close();
               return obj;
           
           case "Presentacion":
               obj= em.find(Presentacion.class, Integer.parseInt(crit));
               em.close();
               emf.close();
               return obj;
               
           case "Expendio":
               obj=em.find(Expendio.class, nameToID("Expendio",crit));
               em.close();
               emf.close();
               return obj;
               
           case "Cerveza":
               
               obj=em.find(Cerveza.class, nameToID("Cerveza",crit));
               em.close();
               emf.close();
               return obj;
               
           case "Envase":
               
               obj=em.find(Envase.class, crit);
               em.close();
               emf.close();
               return obj;
       }
       return null;
   }
   
   public int nameToID(String entidad, String nombreEntidad){
       //Se crea la conexion a la base de datos (Si no existe, se crea)
        //Cesar    
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        //Sebas   
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb");
        //Xim    
        //EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");
        //Edwin
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb")
        EntityManager em = emf.createEntityManager();
       
       switch(entidad){
           case "Expendio":
               TypedQuery<Integer>queryExpendio;
               List<Integer>resultadoExpendio;
               
               queryExpendio=em.createQuery("SELECT c.id_expendio FROM Expendio c WHERE c.exp_nombre= '"+nombreEntidad+"'", Integer.class);
               resultadoExpendio=queryExpendio.getResultList();
               
                System.out.println("\nSe ha recuperado satisfactoriamente el ID del expendio " + nombreEntidad);
                
                em.close();
                emf.close();
                return resultadoExpendio.get(0);
                
           case "Cerveza":
               TypedQuery<Integer>queryCerveza;
               List<Integer>resultadoCerveza;
               
               queryCerveza=em.createQuery("SELECT c.id_cerveza FROM Cerveza c WHERE c.cer_nombre= '"+nombreEntidad+"'", Integer.class);
               resultadoCerveza=queryCerveza.getResultList();
               
                System.out.println("\nSe ha recuperado satisfactoriamente el ID de la cerveza " + nombreEntidad);
                
                em.close();
                emf.close();
                return resultadoCerveza.get(0);
                
       }
       return -1;
   }
   
  
}
