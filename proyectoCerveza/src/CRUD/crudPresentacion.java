
package CRUD;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import proyectoCerveza.Presentacion;


public class crudPresentacion {
    void opCreateLote(Presentacion press){
        Presentacion pres;
        
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        
        pres=press;
        em.persist(pres);
        em.getTransaction().commit();
        em.close();
        emf.close();
        System.out.print("Presentacion registrada");
    }
    
    
    void opUpdateLote(Presentacion press){
        Presentacion pre;
        int aux=-1;
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        aux=press.getPre_cod();
        em.getTransaction().begin();
        
        pre=em.find(Presentacion.class, aux);
        System.out.print("Lote encontrado con el ID: " + aux);
        System.out.print(pre);
        pre.setPre_cod(aux);
       
        
        System.out.print("Lote modificado \n"+pre);
        
        em.getTransaction().commit();
        em.close();
        emf.close();
        System.out.print("Presentacion actualizado");
    }
    
    public List opRead(String ent, String field, String crit){
        
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        
        if(ent.equals("Presentacion")){
            TypedQuery<Presentacion>query=null;
            List<Presentacion>results=new ArrayList<Presentacion>();
            
            if(crit.equals(""))
            {
                query=em.createQuery("Select c From Presentacion c",Presentacion.class);
            }else{
                 query=em.createQuery("Select c FROM Presentacion c WHERE c."+field.toLowerCase() + "LIKE '%"+crit+"%'", Presentacion.class);
            }
            results=query.getResultList();
            System.out.print("Objetos encontrados "+ results.size());
            return results;
        }
        em.close();
        emf.close();
        return null;
    }
    
    public TableModel listToTM(List rs, String entit){
        Vector columnNames=new Vector();
        Vector rows = new Vector();
        Presentacion pre;
        
        columnNames.addElement("Codigo de presentación");
        
        Iterator it= rs.iterator();
        
        while(it.hasNext()){
            
            pre=(Presentacion)it.next();
            Vector newRow=new Vector();           
            newRow.addElement(pre.getPre_cod());
        }
         return new DefaultTableModel(rows,columnNames);
    }
    
      public Object opBuscar(String ent, String field, String crit){
        TableModel tm=null;
        
        List<Presentacion> res=opRead(ent,field,crit);
        tm=listToTM(res,ent);
        return tm;
    }
      
    public void opDelete(String ent, String crit){
        Object c= null;
        
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        c=em.find(Presentacion.class, crit);
        em.remove(c);
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
        
        System.out.print("Objeto Eliminado");
    }
}
