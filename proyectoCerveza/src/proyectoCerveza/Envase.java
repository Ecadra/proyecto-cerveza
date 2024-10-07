package proyectoCerveza;
import java.io.Serializable;
import javax.persistence.*;
import java.util.*;
/**
 *
 * @author ulseg
 */
@Entity
public class Envase implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private String tipo_envase;
    private short capacidad_ml;
    @OneToMany
    @JoinColumn(name = "pre_env", nullable = false)
    private List<Presentacion> env_pre = new ArrayList<Presentacion>();

    public Envase(){
        this.tipo_envase = "";
        this.capacidad_ml = 0;
    }

    public Envase(String envase, short capacidad) {
        this.tipo_envase = envase;
        this.capacidad_ml = capacidad;
    }
      
    @Override
    public String toString() {
        return String.format("\n-----\nEnvase: %s "
                + "\nCapacidad en ml: %d ",  
                this.tipo_envase, this.capacidad_ml);
    }
    
    public void formEnv_pre(Presentacion p1)
    {
        getEnv_pre().add(p1);
    }
   
    public String getTipo_envase() {
        return tipo_envase;
    }
    
    public short getEnvase_capacidad(){
        return capacidad_ml;
    }

    public void setTipo_envase(String tipo_envase) {
        this.tipo_envase = tipo_envase;
    }

    public void setCapacidad_ml(short capacidad_ml) {
        this.capacidad_ml = capacidad_ml;
    }
     
    public List<Presentacion> getEnv_pre() {
        return env_pre;
    }
    
}
