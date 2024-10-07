package proyectoCerveza;


import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Inventario implements Serializable {
    private static final long serialVersionUID=1L;
    
    @Id
    private String inv_cod;
    private float precio_unitario;
    private boolean existencia;
    @ManyToOne
    @JoinColumn(name="exp_inv",nullable=false)
    private Expendio inv_exp;
    @ManyToOne
    @JoinColumn(name="pre_inv",nullable=false)
    private Presentacion inv_pre;
    
    @Override
    public String toString(){
        return String.format("\n-----\nCódigo de inventario %s "
                + "\nPrecio Unitario: %.2f"
                + "\nExistencia: %b", this.inv_cod, this.precio_unitario, this.existencia);
    }

    public Inventario(){
        this.inv_cod=null;
        this.precio_unitario=0.0f;
        this.existencia=false;
    }

    public Inventario(String inv_cod, float precio_unitario, boolean existencia) {
        this.inv_cod = inv_cod;
        this.precio_unitario = precio_unitario;
        this.existencia = existencia;
    }
    
    public void formInv_exp(Expendio e1){
        this.inv_exp=e1;
    }
    public void dropInv_exp(Expendio e1){
        this.inv_exp=e1;
    }
    public void formInv_pre(Presentacion pre1){
        this.inv_pre=pre1;
    }
    public void dropInv_pre(Presentacion pre1){
        this.inv_pre=pre1;
    }

    public void setInv_cod(String inv_cod) {
        this.inv_cod = inv_cod;
    }

    public void setPrecio_unitario(float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }

    public String getInv_cod() {
        return inv_cod;
    }

    public float getPrecio_unitario() {
        return precio_unitario;
    }

    public boolean isExistencia() {
        return existencia;
    }

    public Expendio getInv_exp() {
        return inv_exp;
    }

    public Presentacion getInv_pre() {
        return inv_pre;
    }
   
    
    
    
   
}
