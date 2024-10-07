package proyectoCerveza;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Presentacion implements Serializable {
    private static final long serialVersionUID=1L;
    
    @Id
    private String pre_cod;
    
    @ManyToOne
    @JoinColumn(name="env_pre",nullable=false)
    private Envase pre_env;
    @ManyToOne
    @JoinColumn(name="cer_pre",nullable=false)
    private Cerveza pre_cer;
    @OneToMany
    @JoinColumn(name="ped_pre",nullable=false)
    private List<Pedido> pre_ped;
    @JoinColumn(name="inv_pre",nullable=false)
    private List<Inventario> pre_inv;

    public Presentacion(String pre_cod) {
        this.pre_cod = pre_cod;
    }
    
    public Presentacion(){
        this.pre_cod=null;
    }

    public void setPre_cod(String pre_cod) {
        this.pre_cod = pre_cod;
    }

    public String getPre_cod() {
        return pre_cod;
    }

    public Envase getPre_env() {
        return pre_env;
    }

    public Cerveza getPre_cer() {
        return pre_cer;
    }

    public List<Pedido> getPre_ped() {
        return pre_ped;
    }

    public List<Inventario> getPre_inv() {
        return pre_inv;
    }

}
