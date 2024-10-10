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
import proyectoCerveza.Pedido;

/**
 *
 * @author ulseg
 */
public class crudPedido {
    void opCreatePedido(Pedido ped) {
        Pedido pd;
        // Abre una conexión a la base de datos (crea una nueva si no existe)
        // Diferentes configuraciones de rutas para distintos usuarios:
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");       

        // Crea un EntityManager para gestionar la transacción
        EntityManager em = emf.createEntityManager();

        // Inicia la transacción
        em.getTransaction().begin();

        // Asigna el objeto Pedido y lo persiste en la base de datos
        pd = ped;
        em.persist(pd);

        // Confirma la transacción
        em.getTransaction().commit();

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Mensaje de confirmación
        System.out.println("Pedido registrado");
    }

    void opUpdatePedido(Pedido ped) {
        Pedido pd;
        int aux;

        // Muestra el pedido que está entrando a la función
        System.out.println("Pedido entra" + ped);

        // Abre una conexión a la base de datos (diferentes rutas comentadas):
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");        

        // Crea un EntityManager
        EntityManager em = emf.createEntityManager();

        // Obtiene el tipo de pedido que se usará para buscar en la base de datos
        aux = ped.getPed_codigo();

        // Inicia la transacción
        em.getTransaction().begin();

        // Busca el pedido en la base de datos usando el identificador (ped_codigo)
        pd = em.find(Pedido.class, aux);

        // Muestra el pedido encontrado
        System.out.println("Pedido encontrado con id " + aux);
        System.out.println(pd);

        // Actualiza el pedido
        //pd.setPed_codigo(aux);
        pd.setPed_cantidad(ped.getPed_cantidad());
        pd.setPed_forden(ped.getPed_forden());
        pd.setPed_fdespacho(ped.getPed_fdespacho());
        pd.setPed_total(ped.getPed_total());
        pd.setPed_subtotal(ped.getPed_subtotal());
        pd.setPed_iva(ped.getPed_iva());

        // Muestra el pedido modificado
        System.out.println("Pedido modificado \n" + pd);

        // Confirma la transacción
        em.getTransaction().commit();

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Mensaje de confirmación
        System.out.println("Pedido actualizado");
    }

    public List opReadPedido(String ent, String field, String crit) {

        // Abre una conexión a la base de datos
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");           
        EntityManager em = emf.createEntityManager();

        if (ent.equals("Pedido")) {
            // Consulta tipada para obtener objetos de la base de datos
            TypedQuery<Pedido> query = null;
            List<Pedido> results = new ArrayList<Pedido>();

            // Si no hay criterio de búsqueda, devuelve todos los pedidos
            if (crit.equals("")) {
                query = em.createQuery("select p from Pedido p", Pedido.class);
            } 
            // Si hay criterio de búsqueda, filtra por el campo especificado
            else {
                query = em.createQuery("select p from Pedido p where p." + field.toLowerCase() + " like '%" + crit + "%'", Pedido.class);
            }

            // Obtiene los resultados de la consulta
            results = query.getResultList();
            System.out.println("Pedidos encontrados: " + results.size());

            // Devuelve la lista de pedidos encontrados
            return results;
        }

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        return null;
    }

    public TableModel listtoTMPedido(List rs, String entit) {
        Vector columnNames = new Vector();
        Vector rows = new Vector();

        // Nombres de campos según la entidad pedido
        if (entit.equals("Pedido")) {
            Pedido p;
            // Añade nombres de columnas
            columnNames.addElement("Código de pedido");
            columnNames.addElement("Cantidad de cerveza");
            columnNames.addElement("Fecha de orden");
            columnNames.addElement("Fecha de despacho");
            columnNames.addElement("Total");
            columnNames.addElement("Subtotal");
            columnNames.addElement("IVA");

            // Itera sobre los resultados para llenar las filas de la tabla
            Iterator it = rs.iterator();
            while (it.hasNext()) {
                p = (Pedido) it.next();
                Vector newRow = new Vector();

                // Añade valores a la nueva fila
                newRow.addElement(p.getPed_codigo());
                newRow.addElement(p.getPed_cantidad());
                newRow.addElement(p.getPed_forden());
                newRow.addElement(p.getPed_fdespacho());
                newRow.addElement(p.getPed_total());
                newRow.addElement(p.getPed_subtotal());
                newRow.addElement(p.getPed_iva());
                rows.addElement(newRow);
            }
        }

        // Devuelve el modelo de tabla con filas y columnas
        return new DefaultTableModel(rows, columnNames);
    }

    public TableModel opBuscarPedido(String ent, String field, String crit) {
        TableModel tm = null;

        // Busca en la entidad PEdido según los criterios especificados
        if (ent.equals("Pedido")) {
            List<Pedido> res = opReadPedido(ent, field, crit);
            tm = listtoTMPedido(res, ent);
        }

        // Devuelve el modelo de tabla con los resultados
        return tm;
    }

    public Object opBuscarPedido1(String ent, String crit) {
        Object p = null;

        // Abre una conexión a la base de datos
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb"); 
        EntityManager em = emf.createEntityManager();

        // Busca un pedido específico usando su identificador
        if (ent.equals("Pedido")) {
            p = em.find(Pedido.class, crit);
        }

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Devuelve el objeto encontrado
        return p;
    }

    public void opDeletePedido(String ent, String crit) {
        Object p = null;

        // Abre una conexión a la base de datos
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb"); 
        EntityManager em = emf.createEntityManager();

        // Inicia la transacción
        em.getTransaction().begin();

        // Si la entidad es Pedido, busca el Pedido por su ID y lo elimina
        if (ent.equals("Pedido")) {
            p = em.find(Pedido.class, crit);
            em.remove(p);
        }

        // Confirma la transacción
        em.getTransaction().commit();

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Mensaje de confirmación de eliminación
        System.out.println("Pedido eliminado");
    }
  
}
