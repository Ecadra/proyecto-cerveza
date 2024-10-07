
package proyectoCerveza;
import java.io.Serializable;
import javax.persistence.*;
import java.util.*;


/**
 *
 * @author ulseg
 */
@Entity
public class Grano implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    private String gra_nombre;
    private String gra_procedencia;
            
    @OneToMany
    @JoinColumn(name = "rec_gra", nullable = false)
    private List<Receta>gra_rec = new ArrayList<Receta>();

            
    public Grano(){
        this.gra_nombre = "";
        this.gra_procedencia = "";
    }
    
    public Grano(String nom, String proc){
        this.gra_nombre = nom;
        this.gra_procedencia = proc;
    }
    
    @Override
    public String toString() {
        return String.format("\n-----\nNombre del grano %s "
                + "\nProcedencia del grano: %s \n",
                this.gra_nombre, this.gra_procedencia);
    }
    
    public void printRecetas(){
        System.out.println(this.getGra_rec());
        
        for (int i=0;i<getGra_rec().size();i++) {
            System.out.println(getGra_rec().get(i));
        }
    }
    
    public void formGra_rec(Receta rec1) {
        getGra_rec().add(rec1);
    }

    public String getGra_nombre() {
        return gra_nombre;
    }
    
    public void setGra_nombre(String gra_nombre) {
        this.gra_nombre = gra_nombre;
    }

    public String getGra_procedencia() {
        return gra_procedencia;
    }

    public void setGra_procedencia(String gra_procedencia) {
        this.gra_nombre = gra_procedencia;
    }

    public List<Receta> getGra_rec() {
        return gra_rec;
    }
}
