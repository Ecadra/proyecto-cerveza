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
import proyectoCerveza.Grano;

/**
 *
 * @author ulseg
 */
public class crudGrano {
    void opCreateGrano(Grano gra) {
        Grano gr;
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

        // Asigna el objeto Grano y lo persiste en la base de datos
        gr = gra;
        em.persist(gr);

        // Confirma la transacción
        em.getTransaction().commit();

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Mensaje de confirmación
        System.out.println("Grano registrado");
    }

    void opUpdateGrano(Grano gra) {
        Grano gr;
        String aux;

        // Muestra el grano que está entrando a la función
        System.out.println("Grano entra" + gra);

        // Abre una conexión a la base de datos (diferentes rutas comentadas):
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");        

        // Crea un EntityManager
        EntityManager em = emf.createEntityManager();

        // Obtiene el tipo de grano que se usará para buscar en la base de datos
        aux = gra.getGra_nombre();

        // Inicia la transacción
        em.getTransaction().begin();

        // Busca el grano en la base de datos usando el identificador 
        gr = em.find(Grano.class, aux);

        // Muestra el grano encontrado
        System.out.println("Grano encontrado con id " + aux);
        System.out.println(gr);

        // Actualiza la procedencia del grano
        //gr.setGra_nombre(aux);
        gr.setGra_procedencia(gra.getGra_procedencia());

        // Muestra el Grano modificado
        System.out.println("Grano modificado \n" + gr);

        // Confirma la transacción
        em.getTransaction().commit();

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Mensaje de confirmación
        System.out.println("Grano actualizado");
    }

    public List opReadGrano(String ent, String field, String crit) {

        // Abre una conexión a la base de datos
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");           
        EntityManager em = emf.createEntityManager();

        if (ent.equals("Grano")) {
            // Consulta tipada para obtener objetos de la base de datos
            TypedQuery<Grano> query = null;
            List<Grano> results = new ArrayList<Grano>();

            // Si no hay criterio de búsqueda, devuelve todos los granos
            if (crit.equals("")) {
                query = em.createQuery("select g from Grano g", Grano.class);
            } 
            // Si hay criterio de búsqueda, filtra por el campo especificado
            else {
                query = em.createQuery("select g from Grano g where g." + field.toLowerCase() + " like '%" + crit + "%'", Grano.class);
            }

            // Obtiene los resultados de la consulta
            results = query.getResultList();
            System.out.println("Granos encontrados: " + results.size());

            // Devuelve la lista de granos encontrados
            return results;
        }

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        return null;
    }

    public TableModel listtoTMGrano(List rs, String entit) {
        Vector columnNames = new Vector();
        Vector rows = new Vector();

        // Nombres de campos según la entidad Grano
        if (entit.equals("Grano")) {
            Grano g;

            // Añade nombres de columnas
            columnNames.addElement("Nombre del grano");
            columnNames.addElement("Procedencia");

            // Itera sobre los resultados para llenar las filas de la tabla
            Iterator it = rs.iterator();
            while (it.hasNext()) {
                g = (Grano) it.next();
                Vector newRow = new Vector();

                // Añade valores a la nueva fila
                newRow.addElement(g.getGra_nombre());
                newRow.addElement(g.getGra_procedencia());
                rows.addElement(newRow);
            }
        }

        // Devuelve el modelo de tabla con filas y columnas
        return new DefaultTableModel(rows, columnNames);
    }

    public TableModel opBuscarGrano(String ent, String field, String crit) {
        TableModel tm = null;

        // Busca en la entidad grano según los criterios especificados
        if (ent.equals("Grano")) {
            List<Grano> res = opReadGrano(ent, field, crit);
            tm = listtoTMGrano(res, ent);
        }

        // Devuelve el modelo de tabla con los resultados
        return tm;
    }

    public Object opBuscarGrano1(String ent, String crit) {
        Object e = null;

        // Abre una conexión a la base de datos
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb"); 
        EntityManager em = emf.createEntityManager();

        // Busca un grano específico usando su identificador 
        if (ent.equals("Grano")) {
            e = em.find(Grano.class, crit);
        }

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Devuelve el objeto encontrado
        return e;
    }

    public void opDeleteGrano(String ent, String crit) {
        Object g = null;

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

        // Si la entidad es grano, busca el grano por su ID y lo elimina
        if (ent.equals("Grano")) {
            g = em.find(Grano.class, crit);
            em.remove(g);
        }

        // Confirma la transacción
        em.getTransaction().commit();

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Mensaje de confirmación de eliminación
        System.out.println("Grano eliminado");
    }
  
}
