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
import proyectoCerveza.Receta;

/**
 *
 * @author ulseg
 */
public class crudReceta {
    void opCreateReceta(Receta rec) {
        Receta rc;
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
        rc = rec;
        em.persist(rc);

        // Confirma la transacción
        em.getTransaction().commit();

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Mensaje de confirmación
        System.out.println("Receta registrada");
    }

    void opUpdateReceta(Receta rec) {
        Receta rc;
        int aux;

        // Muestra la receta que está entrando a la función
        System.out.println("Receta entra" + rec);

        // Abre una conexión a la base de datos (diferentes rutas comentadas):
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");        

        // Crea un EntityManager
        EntityManager em = emf.createEntityManager();

        // Obtiene el tipo de receta que se usará para buscar en la base de datos
        aux = rec.getId_receta();

        // Inicia la transacción
        em.getTransaction().begin();

        // Busca la receta en la base de datos usando el identificador 
        rc = em.find(Receta.class, aux);

        // Muestra la receta encontrado
        System.out.println("Receta encontrada con id " + aux);
        System.out.println(rc);

        // Actualiza la procedencia del grano
        //gr.setGra_nombre(aux);
        rc.setRec_cantidad(rec.getRec_cantidad());

        // Muestra la receta modificado
        System.out.println("Receta modificada \n" + rc);

        // Confirma la transacción
        em.getTransaction().commit();

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Mensaje de confirmación
        System.out.println("Receta actualizada");
    }

    public List opReadReceta(String ent, String field, String crit) {

        // Abre una conexión a la base de datos
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");           
        EntityManager em = emf.createEntityManager();

        if (ent.equals("Receta")) {
            // Consulta tipada para obtener objetos de la base de datos
            TypedQuery<Receta> query = null;
            List<Receta> results = new ArrayList<Receta>();

            // Si no hay criterio de búsqueda, devuelve todos las recetas
            if (crit.equals("")) {
                query = em.createQuery("select r from Receta r", Receta.class);
            } 
            // Si hay criterio de búsqueda, filtra por el campo especificado
            else {
                query = em.createQuery("select r from Receta r where r." + field.toLowerCase() 
                        + " like '%" + crit + "%'", Receta.class);
            }

            // Obtiene los resultados de la consulta
            results = query.getResultList();
            System.out.println("Recetas encontradas: " + results.size());

            // Devuelve la lista de recetas encontrados
            return results;
        }

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        return null;
    }

    public TableModel listtoTMReceta(List rs, String entit) {
        Vector columnNames = new Vector();
        Vector rows = new Vector();

        // Nombres de campos según la entidad Receta
        if (entit.equals("Receta")) {
            Receta r;

            // Añade nombres de columnas
            columnNames.addElement("Id de la receta");
            columnNames.addElement("Cantidad");

            // Itera sobre los resultados para llenar las filas de la tabla
            Iterator it = rs.iterator();
            while (it.hasNext()) {
                r = (Receta) it.next();
                Vector newRow = new Vector();

                // Añade valores a la nueva fila
                newRow.addElement(r.getId_receta());
                newRow.addElement(r.getRec_cantidad());
                rows.addElement(newRow);
            }
        }

        // Devuelve el modelo de tabla con filas y columnas
        return new DefaultTableModel(rows, columnNames);
    }

    public TableModel opBuscarReceta(String ent, String field, String crit) {
        TableModel tm = null;

        // Busca en la entidad receta según los criterios especificados
        if (ent.equals("Receta")) {
            List<Receta> res = opReadReceta(ent, field, crit);
            tm = listtoTMReceta(res, ent);
        }

        // Devuelve el modelo de tabla con los resultados
        return tm;
    }

    public Object opBuscarReceta1(String ent, String crit) {
        Object r = null;

        // Abre una conexión a la base de datos
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb"); 
        EntityManager em = emf.createEntityManager();

        // Busca una receta específica usando su identificador 
        if (ent.equals("Receta")) {
            r = em.find(Receta.class, crit);
        }

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Devuelve el objeto encontrado
        return r;
    }

    public void opDeleteReceta(String ent, String crit) {
        Object r = null;

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

        // Si la entidad es receta, busca la receta por su ID y lo elimina
        if (ent.equals("Receta")) {
            r = em.find(Receta.class, crit);
            em.remove(r);
        }

        // Confirma la transacción
        em.getTransaction().commit();

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Mensaje de confirmación de eliminación
        System.out.println("Receta eliminada");
    }
  
}
