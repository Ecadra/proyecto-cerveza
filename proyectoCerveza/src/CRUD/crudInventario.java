
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
import proyectoCerveza.Inventario;


public class crudInventario {
    
    void opCreateInventario(Inventario invv){
        
        Inventario inv;
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        inv=invv;
        em.persist(inv);
        
        em.getTransaction().commit();
        em.close();
        emf.close();
        System.out.print("Inventario registrado");
    }
    
    void opUpdateInventario(Inventario invv){
        Inventario inv;
        int aux=-1;
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        invv.getInv_cod();
        em.getTransaction().begin();
        
        inv = em.find(Inventario.class, aux);//buscar la clase 
        System.out.print("Invenrario encontrado con el id "+aux);
        System.out.print(inv);
        inv.setExistencia(invv.isExistencia());
        inv.setCantidad(invv.getCantidad());
        inv.setPrecio_unitario(invv.getPrecio_unitario());
        
        System.out.print("Inventario modificado \n"+inv);
        
        em.getTransaction().commit();
        em.close();
        emf.close();
        System.out.print("Inventario actualizado");

    }
    
    public List opRead(String ent, String field, String crit){
        
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        
        if(ent.equals("Inventario")){
            TypedQuery<Inventario>query=null;//Donde se va a guardar la Query
            List<Inventario>results=new ArrayList<Inventario>();//Resultados
            
            if(crit.equals(""))
            {
                query=em.createQuery("Select c From Inventario c",Inventario.class);
                
            }else{
                query=em.createQuery("Select c FROM Inventario c WHERE c."+field.toLowerCase() + "LIKE '%"+crit+"%'", Inventario.class);
            }
            results=query.getResultList();//Se guardan los resultados
            System.out.print("Obejetos encontrados "+ results.size());
            return results;
            
        }
        em.close();
        emf.close();
        return null;
    }
    
    public TableModel listToTM(List rs, String entit){
        Vector columnNames=new Vector();
        Vector rows = new Vector();
        Inventario inv;
        
        columnNames.addElement("Cantidad");
        columnNames.addElement("Existencia");
        columnNames.addElement("Precio Unitario");
        
        Iterator it= rs.iterator();
        
        while(it.hasNext())
        {
            inv=(Inventario)it.next();
            Vector newRow=new Vector();
            
            newRow.addElement(inv.getCantidad());
            newRow.addElement(inv.isExistencia());
            newRow.addElement(inv.getPrecio_unitario());
            rows.addElement(newRow);
        }
        
        return new DefaultTableModel(rows,columnNames);
    }
    
    public Object opBuscar(String ent, String field, String crit){
        TableModel tm=null;
        
        List<Inventario> res=opRead(ent,field,crit);
        tm=listToTM(res,ent);
        return tm;
    }
    
    public void opDelete(String ent, String crit){
        Object c= null;
        
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("D:\\Documentos HDD\\Proyecto Neatbeans\\Librerias\\objectdb-2.9.0\\db\\cervezadb.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        c=em.find(Inventario.class, crit);
        em.remove(c);
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
        
        System.out.print("Objeto Eliminado");
    }
}
