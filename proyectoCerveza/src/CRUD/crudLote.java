
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
import proyectoCerveza.Lote;

public class crudLote {
    
    void opCreateLote(Lote lott){
        Lote lot;
        
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        
        lot=lott;
        em.persist(lot);
        em.getTransaction().commit();
        em.close();
        emf.close();
        System.out.print("Lote registrado");
    }
    
    void opUpdateLote(Lote lott){
        Lote lot;
        int aux=-1;
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        aux=lott.getLote_cod();
        em.getTransaction().begin();
        
        lot=em.find(Lote.class, aux);
        System.out.print("Lote encontrado con el ID: " + aux);
        System.out.print(lot);
        lot.setCantidad(lott.getCantidad());
        lot.setLote_fechaProduccion(lott.getLote_fechaProduccion());
        lot.setLote_fechaCaducidad(lott.getLote_fechaCaducidad());
       
        
        System.out.print("Lote modificado \n"+lot);
        
        em.getTransaction().commit();
        em.close();
        emf.close();
        System.out.print("Lote actualizado");
    }
    
    public List opRead(String ent, String field, String crit){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        
        if(ent.equals("Lote")){
            TypedQuery<Lote>query=null;
            List<Lote>results=new ArrayList<Lote>();
            
            if(crit.equals(""))
            {
                query=em.createQuery("Select c From Lote c",Lote.class);
            }else{
                 query=em.createQuery("Select c FROM Lote c WHERE c."+field.toLowerCase() + "LIKE '%"+crit+"%'", Lote.class);
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
        Lote lot;
        
        columnNames.addElement("Código de lote");
        columnNames.addElement("Cantidad");
        columnNames.addElement("Fecha de producción");
        columnNames.addElement("Fecha de caducidad");
        
        Iterator it= rs.iterator();
        
        while(it.hasNext())
        {
            lot=(Lote)it.next();
            Vector newRow=new Vector();
            
            newRow.addElement(lot.getLote_cod());
            newRow.addElement(lot.getCantidad());
            newRow.addElement(lot.getLote_fechaProduccion());
            newRow.addElement(lot.getLote_fechaCaducidad());
            rows.addElement(newRow);
        }
        
        return new DefaultTableModel(rows,columnNames);
    }
    
       public Object opBuscar(String ent, String field, String crit){
        TableModel tm=null;
        
        List<Lote> res=opRead(ent,field,crit);
        tm=listToTM(res,ent);
        return tm;
    }
       
    public void opDelete(String ent, String crit){
        Object c= null;
        
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        c=em.find(Lote.class, crit);
        em.remove(c);
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
        
        System.out.print("Objeto Eliminado");
    }
}
