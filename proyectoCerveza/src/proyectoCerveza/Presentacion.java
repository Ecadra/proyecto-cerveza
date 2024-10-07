package proyectoCerveza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Presentacion implements Serializable {
    private static final long serialVersionUID=1L;
    
    @Id
    private int pre_cod;
    
    @ManyToOne
    @JoinColumn(name="env_pre",nullable=false)
    private Envase pre_env;
    @ManyToOne
    @JoinColumn(name="cer_pre",nullable=false)
    private Cerveza pre_cer;
    @OneToMany
    @JoinColumn(name="ped_pre",nullable=false)
    private List<Pedido> pre_ped=new ArrayList<Pedido>();
    @OneToMany
    @JoinColumn(name="inv_pre",nullable=false)
    private List<Inventario> pre_inv =new ArrayList<Inventario>();
    
    @Override
    public String toString(){
        return String.format("\n-----\nCódigo de la presentación: %d", this.pre_cod);
    }
    
    public Presentacion(int pre_cod) {
        this.pre_cod = pre_cod;
    }
    
    public Presentacion(){
        this.pre_cod=0;
    }
    
    public void formPre_env(Envase env1){
        this.pre_env=env1;
    }
    
    public void dropPre_env(Envase env1){
        this.pre_env=env1;
    }
    
    public void formPre_cer(Cerveza cer1){
        this.pre_cer=cer1;
    }
    
    public void dropPre_cer(Cerveza cer1){
        this.pre_cer=cer1;
    }
    /////////////////////////////////////////////////////
     public void formPre_ped(Pedido ped1){
        getPre_ped().add(ped1);
    }
     public void dropPre_ped(Pedido ped1){
        getPre_ped().remove(ped1);
    }
     
      public void formPre_inv(Inventario inv1){
        getPre_inv().add(inv1);
    }
     public void dropPre_inv(Inventario inv1){
        getPre_inv().remove(inv1);
    }
     
     
    

    public void setPre_cod(int pre_cod) {
        this.pre_cod = pre_cod;
    }

    public int getPre_cod() {
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
