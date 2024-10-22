
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
    
    //Cesar
    public String ruta= "D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb";
    //Sebastian
    //public String ruta= "C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezaodb.odb";
    //Ximena
    //public String ruta="C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb";
    //Edwin
    //public String ruta="/home/edwin-993/cervezaodb/cervezadb.odb";
    
    
    public void opCreate(String entidad, Object obj){
        //Se crea la conexion a la base de datos (Si no existe, se crea)

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        
        EntityManager em = emf.createEntityManager();
        
        
        //Switch para saber que tipo de objeto es (Presentación, Inventario y Lote) los objetos ya viene contruidos de la interfaz, aquí solo es
        //hacerlos persistente
        
        switch(entidad){
            case "Presentacion":
                Presentacion pre = (Presentacion)obj;
                
                Envase env=em.find(Envase.class, pre.getPre_env().getId_envase());//Ejemplo de herencia
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
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
      
        EntityManager em = emf.createEntityManager();
       
       switch(ent){
           case "Presentacion":
               
               TypedQuery<Presentacion>queryPresentacion=null;
               List<Presentacion>resultPre=null;
               if(crit.equals("")){
                   queryPresentacion=em.createQuery("SELECT c FROM Presentacion c",Presentacion.class);
               }else{
                   queryPresentacion=em.createQuery("SELECT c FROM Presentacion c WHERE c."+field+"="+crit+"", Presentacion.class);
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
                   queryLote=em.createQuery("SELECT c FROM Lote  c WHERE c."+field+"="+crit+"  ", Lote.class);
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
                   queryInv=em.createQuery("SELECT c FROM Inventario c WHERE c."+field+"="+crit+"", Inventario.class);
               }
               resultInv=queryInv.getResultList();
               System.out.print("\nSe han recuperado "+resultInv.size()+ " Inventarios");
               em.close();
               emf.close();
               return resultInv;
               
           case "Envase":
               TypedQuery<Envase>queryEnv=null;
               List<Envase>resultEnv=null;
               
               if(crit.equals("")){//Si no se busca por un criterio entonces se busca toda la tabla
                   queryEnv=em.createQuery("SELECT c FROM Envase c", Envase.class);
               }else{
                        if(field.equals("tipo_envase")){

                            queryEnv=em.createQuery("SELECT c FROM Envase c WHERE c."+field+" LIKE '"+crit+"%'", Envase.class);

                        }
                        if(field.equals("capacidad_ml")){

                            queryEnv=em.createQuery("SELECT c FROM Envase c WHERE c."+field+" = "+crit, Envase.class);  
                        }
               }
               resultEnv=queryEnv.getResultList();
               System.out.print("\nSe han recuperado "+resultEnv.size()+ " Envases");
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
               columnNames.addElement("Código del Envase");
               columnNames.addElement("Código de Cerveza");
               Iterator itPre=resultX.iterator();
               while(itPre.hasNext()){
                   presentacion=(Presentacion)itPre.next();
                   Vector nuevaFila=new Vector();
                   nuevaFila.addElement(presentacion.getPre_cod());
                   nuevaFila.addElement(presentacion.getPre_env().getId_envase());
                   nuevaFila.addElement(presentacion.getPre_cer().getId_cerveza());
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
                
                columnNames.addElement("Código del envase");
                columnNames.addElement("Tipo de envase");
                columnNames.addElement("Capacidad en ml");
                
                Iterator itEnv= resultX.iterator();
                while(itEnv.hasNext()){
                    envase = (Envase)itEnv.next();
                    Vector nuevaFila = new Vector();
                    
                    nuevaFila.addElement(envase.getId_envase());
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
       System.out.println("\nLa entidad a buscar en opBuscar es: "+ent+"\nEL criterio es: "+crit);
       if(ent.equals("Inventario")){//Inicio de if inventario
           List<Inventario>results;
           switch(field){//Inicio del switch
               case "Codigo de Inventario":
                   results=opRead(ent,"inv_cod",crit);
                   tm=listToTM(results,ent);
               break;
               case "Precio Unitario":
                   results=opRead(ent,"precio_unitario",crit);
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
               case "Codigo de Lote":
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
           
           List<Presentacion>results;
           switch(field){
               case  "Codigo de la presentacion":
                   results=opRead(ent,"pre_cod",crit);
                   tm=listToTM(results,ent);  
                break;
               default:
                   results=opRead(ent,field,crit);
                   tm=listToTM(results,ent);
               break;
           }
           
  
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
           
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
       
        EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       
       switch(ent){
           
           case "Inventario":
               obj=em.find(Inventario.class, Integer.parseInt(crit));
               em.remove(obj);
           break;
           case "Lote":
               obj=em.find(Lote.class, Integer.parseInt(crit));
               em.remove(obj);
           break;
           case "Presentacion":
               obj=em.find(Presentacion.class, Integer.parseInt(crit));
               em.remove(obj);
           break;
           default:
               JOptionPane.showMessageDialog(null, "\nNo se ha encontrado nada que borrar","Error->opDelete",JOptionPane.ERROR_MESSAGE);
           break;
       }
        em.getTransaction().commit();
        em.close();
        emf.close();
   }
   
   public Object opBuscarObjeto(String ent, String crit){
       Object obj;
       System.out.print("\nLa entidad a buscar dentro de opBuscar es: "+ent);
       System.out.print("\nEl criterio de busqueda dentro de opBuscar es: "+crit);
        //Se crea la conexion a la base de datos (Si no existe, se crea)
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
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
               obj=em.find(Expendio.class, Integer.parseInt(crit));
               em.close();
               emf.close();
               return obj;
               
           case "Cerveza":
               
               obj=em.find(Cerveza.class, nameToID("Cerveza",crit));
               em.close();
               emf.close();
               return obj;
               
           case "Envase":
               
               obj=em.find(Envase.class, Integer.parseInt(crit));
               em.close();
               emf.close();
               return obj;
           case "Inventario":
               obj=em.find(Inventario.class, Integer.parseInt(crit));
               em.close();
               emf.close();
               return obj;
       }
       return null;
   }
   
   public int nameToID(String entidad, String nombreEntidad){
       //Se crea la conexion a la base de datos (Si no existe, se crea)
         
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
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
   
   
   public void opUpdate(String entidad, Object obj){
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
        EntityManager em = emf.createEntityManager();
        System.out.print("\n La entidad a actualizar es: "+ entidad);
        
        switch(entidad){//Inicio del switch update
            case "Inventario":
               
                Inventario invNuevo=(Inventario)obj;
                Inventario invViejo;
                int aux = invNuevo.getInv_cod();
                
                em.getTransaction().begin();
                invViejo=em.find(Inventario.class,aux);
                System.out.print("\nSe ha encontrado un inventario con el ID: "+aux);
                System.out.print("\nEl inventario nuevo es: \n"+ invNuevo);
                System.out.print("\nEl inventario viejo es: \n"+ invViejo);
                
                
                
               if(invViejo.getInv_exp().getId_expendio()==invNuevo.getInv_exp().getId_expendio()){
                   
                 invViejo.setInv_cod(invNuevo.getInv_cod());
                 invViejo.setCantidad(invNuevo.getCantidad());
                 invViejo.setExistencia(invNuevo.isExistencia());
                 invViejo.setPrecio_unitario(invNuevo.getPrecio_unitario());
                }else {
                   
                    Expendio exp=em.find(Expendio.class, invNuevo.getInv_exp().getId_expendio());
                    invViejo.dropInv_exp();
                    invViejo.formInv_exp(exp);
                    invViejo.setInv_cod(invNuevo.getInv_cod());
                    invViejo.setCantidad(invNuevo.getCantidad());
                    invViejo.setExistencia(invNuevo.isExistencia());
                    invViejo.setPrecio_unitario(invNuevo.getPrecio_unitario());
                    
                    
               }
                    
                if(invViejo.getInv_pre().getPre_cod()==invNuevo.getInv_pre().getPre_cod()){
                    invViejo.setInv_cod(invNuevo.getInv_cod());
                    invViejo.setCantidad(invNuevo.getCantidad());
                    invViejo.setExistencia(invNuevo.isExistencia());
                    invViejo.setPrecio_unitario(invNuevo.getPrecio_unitario());
              
                }else{
                    
                   Presentacion pre=em.find(Presentacion.class, invNuevo.getInv_pre().getPre_cod());
                   invViejo.dropInv_pre();
                   invViejo.formInv_pre(pre);
                   invViejo.setInv_cod(invNuevo.getInv_cod());
                   invViejo.setCantidad(invNuevo.getCantidad());
                   invViejo.setExistencia(invNuevo.isExistencia());
                   invViejo.setPrecio_unitario(invNuevo.getPrecio_unitario());
                   
               }
                
                em.getTransaction().commit();
                em.close();
                emf.close(); 
            break;    
            case "Presentacion":
                Presentacion nuevaPres=(Presentacion)obj;
                Presentacion viejaPres;
                int auxPres=nuevaPres.getPre_cod();
                
                em.getTransaction().begin();
                viejaPres=em.find(Presentacion.class, auxPres);
                System.out.print("\nSe ha encontrado una presentacion con el ID: "+auxPres);
                System.out.print("\nLa presentacion nueva es: \n"+ nuevaPres);
                System.out.print("\nLa presentacion viejo es: \n"+ viejaPres);
                
                if(viejaPres.getPre_cer().getId_cerveza()==nuevaPres.getPre_cer().getId_cerveza()){
                    System.out.print("\nNo hay que cambiar la relación de presentacion con cerveza\n");
                }else{
                    Cerveza cer=em.find(Cerveza.class, nuevaPres.getPre_cer().getId_cerveza());
                    viejaPres.dropPre_cer();
                    viejaPres.formPre_cer(cer);    
                }
                
                
                 if(viejaPres.getPre_env().getId_envase()==nuevaPres.getPre_env().getId_envase()){
                    System.out.print("\nNo hay que cambiar la relación de presentacion con envase\n");
                }else{
                    Envase env=em.find(Envase.class, nuevaPres.getPre_env().getId_envase());
                    viejaPres.dropPre_env();
                    viejaPres.formPre_env(env);
                }
                em.getTransaction().commit();
                em.close();
                emf.close(); 
            break;
            
            case "Lote":
                Lote nuevoLote=(Lote)obj;
                Lote viejoLote;
                int auxLote=nuevoLote.getLote_cod();
                
                em.getTransaction().begin();
                viejoLote=em.find(Lote.class, auxLote);
                
                if(viejoLote.getLote_cer().getId_cerveza()==nuevoLote.getLote_cer().getId_cerveza()){
                    System.out.print("\nNo hay que cambiar la relación de lote con cerveza\n");
                    viejoLote.setCantidad(nuevoLote.getCantidad());
                    viejoLote.setLote_fechaCaducidad(nuevoLote.getLote_fechaCaducidad());
                    viejoLote.setLote_fechaProduccion(nuevoLote.getLote_fechaProduccion());
                }else{
                    Cerveza cer=em.find(Cerveza.class, nuevoLote.getLote_cer().getId_cerveza());
                    viejoLote.dropLote_cer();
                    viejoLote.formLote_cer(cer);
                    viejoLote.setCantidad(nuevoLote.getCantidad());
                    viejoLote.setLote_fechaCaducidad(nuevoLote.getLote_fechaCaducidad());
                    viejoLote.setLote_fechaProduccion(nuevoLote.getLote_fechaProduccion());
                }
                em.getTransaction().commit();
                em.close();
                emf.close(); 
                
            break;
                
        }//Fin del switch update
       
   }
   
   public int opIDMax(String entidad){
      EntityManagerFactory emf = Persistence.createEntityManagerFactory(ruta);
      EntityManager em = emf.createEntityManager();
      
      switch(entidad){
          
          case "Inventario":
              TypedQuery<Integer>queryInv=null;
              List<Integer>resultsInv=new ArrayList();
              
              queryInv=em.createQuery("SELECT MAX(c.inv_cod)FROM Inventario c",Integer.class);
              resultsInv=queryInv.getResultList();
              
              em.close();
              emf.close();
              return resultsInv.get(0);
              
          case "Presentacion":
              TypedQuery<Integer>queryPres=null;
              List<Integer>resultsPres=new ArrayList();
              
              queryPres=em.createQuery("SELECT MAX(c.pre_cod)FROM Presentacion c",Integer.class);
              resultsPres=queryPres.getResultList();
              
              em.close();
              emf.close();
              return resultsPres.get(0);
          case "Lote":
              TypedQuery<Integer>queryLote=null;
              List<Integer>resultsLote=null;
              
              queryLote=em.createQuery("SELECT MAX(c.lote_cod)FROM Lote c", Integer.class);
              resultsLote=queryLote.getResultList();
              
              em.close();
              emf.close();
              return resultsLote.get(0);
      }
        return -1;
   }
   
  
}
