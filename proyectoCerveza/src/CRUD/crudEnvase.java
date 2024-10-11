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
import proyectoCerveza.Envase;

/**
 *
 * @author ulseg
 */
public class crudEnvase {
    void opCreateEnvase(Envase env) {
        Envase en;
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

        // Asigna el objeto Envase y lo persiste en la base de datos
        en = env;
        em.persist(en);

        // Confirma la transacción
        em.getTransaction().commit();

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Mensaje de confirmación
        System.out.println("Envase registrado");
    }

    void opUpdateEnvase(Envase env) {
        Envase en;
        String aux;

        // Muestra el envase que está entrando a la función
        System.out.println("Envase entra" + env);

        // Abre una conexión a la base de datos (diferentes rutas comentadas):
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");        

        // Crea un EntityManager
        EntityManager em = emf.createEntityManager();

        // Obtiene el tipo de envase que se usará para buscar en la base de datos
        aux = env.getTipo_envase();

        // Inicia la transacción
        em.getTransaction().begin();

        // Busca el envase en la base de datos usando el identificador (tipo_envase)
        en = em.find(Envase.class, aux);

        // Muestra el envase encontrado
        System.out.println("Envase encontrado con id " + aux);
        System.out.println(en);

        // Actualiza la capacidad del envase
        //en.setTipo_envase(aux);
        en.setCapacidad_ml(env.getEnvase_capacidad());

        // Muestra el envase modificado
        System.out.println("Envase modificado \n" + en);

        // Confirma la transacción
        em.getTransaction().commit();

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Mensaje de confirmación
        System.out.println("Envase actualizado");
    }

    public List opReadEnvase(String ent, String field, String crit) {

        // Abre una conexión a la base de datos
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb");           
        EntityManager em = emf.createEntityManager();

        if (ent.equals("Envase")) {
            // Consulta tipada para obtener objetos de la base de datos
            TypedQuery<Envase> query = null;
            List<Envase> results = new ArrayList<Envase>();

            // Si no hay criterio de búsqueda, devuelve todos los envases
            if (crit.equals("")) {
                query = em.createQuery("select e from Envase e", Envase.class);
            } 
            // Si hay criterio de búsqueda, filtra por el campo especificado
            else {
                query = em.createQuery("select e from Envase e where e." + field.toLowerCase() + " like '%" + crit + "%'", Envase.class);
            }

            // Obtiene los resultados de la consulta
            results = query.getResultList();
            System.out.println("Envases encontrados: " + results.size());

            // Devuelve la lista de envases encontrados
            return results;
        }

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        return null;
    }

    public TableModel listtoTMEnvase(List rs, String entit) {
        Vector columnNames = new Vector();
        Vector rows = new Vector();

        // Nombres de campos según la entidad Envase
        if (entit.equals("Envase")) {
            Envase e;
            // Añade nombres de columnas
            columnNames.addElement("Tipo de envase");
            columnNames.addElement("Envase capacidad");

            // Itera sobre los resultados para llenar las filas de la tabla
            Iterator it = rs.iterator();
            while (it.hasNext()) {
                e = (Envase) it.next();
                Vector newRow = new Vector();

                // Añade valores a la nueva fila
                newRow.addElement(e.getTipo_envase());
                newRow.addElement(e.getEnvase_capacidad());
                rows.addElement(newRow);
            }
        }

        // Devuelve el modelo de tabla con filas y columnas
        return new DefaultTableModel(rows, columnNames);
    }

    public TableModel opBuscarEnvase(String ent, String field, String crit) {
        TableModel tm = null;

        // Busca en la entidad Envase según los criterios especificados
        if (ent.equals("Envase")) {
            List<Envase> res = opReadEnvase(ent, field, crit);
            tm = listtoTMEnvase(res, ent);
        }

        // Devuelve el modelo de tabla con los resultados
        return tm;
    }

    public Object opBuscarEnvase1(String ent, String crit) {
        Object e = null;

        // Abre una conexión a la base de datos
        // Cesar    
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/edwin-993/cervezaodb/cervezadb.odb");
        // Sebas   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("C:\\Users\\ulseg\\Downloads\\NetBeansProjects\\objectdb-2.9.0\\db\\cervezadb.odb");
        // Xim     
        // EntityManagerFactory emf= Persistence.createEntityManagerFactory("C:\\\\objectdb-2.9.0\\\\db\\\\cervezadb.odb"); 
        EntityManager em = emf.createEntityManager();

        // Busca un envase específico usando su identificador (tipo_envase)
        if (ent.equals("Envase")) {
            e = em.find(Envase.class, crit);
        }

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Devuelve el objeto encontrado
        return e;
    }

    public void opDeleteEnvase(String ent, String crit) {
        Object e = null;

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

        // Si la entidad es Envase, busca el envase por su ID y lo elimina
        if (ent.equals("Envase")) {
            e = em.find(Envase.class, crit);
            em.remove(e);
        }

        // Confirma la transacción
        em.getTransaction().commit();

        // Cierra la conexión a la base de datos
        em.close();
        emf.close();

        // Mensaje de confirmación de eliminación
        System.out.println("Envase eliminado");
    }
  
}
