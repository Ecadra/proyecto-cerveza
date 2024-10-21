package proyectoCerveza;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import proyectoCerveza.Expendio;
import proyectoCerveza.Presentacion;
import proyectoCerveza.Venta;


@Entity
public class Inventario implements Serializable {
    private static final long serialVersionUID=1L;
    
    @Id
    private int inv_cod;
    private float precio_unitario;
    private int cantidad;
    private boolean existencia;
    @ManyToOne
    @JoinColumn(name="exp_inv",nullable=false)
    private Expendio inv_exp;
    @ManyToOne
    @JoinColumn(name="pre_inv",nullable=false)
    private Presentacion inv_pre;
    @OneToMany
    @JoinColumn(name="ven_inv", nullable=false)
    private List<Venta> inv_ven =new ArrayList<Venta>();
    
    @Override
    public String toString(){
        return String.format("\n-----\nCódigo de inventario %d "
                + "\nPrecio Unitario: %f"
                + "\nCantidad: %d"
                + "\nExistencia: %b", this.inv_cod, this.precio_unitario, this.cantidad, this.existencia);
    }

    public Inventario(){
        this.inv_cod=0;
        this.precio_unitario=0.0f;
        this.cantidad=0;
        this.existencia=false;
    }

    public Inventario(int inv_cod, float precio_unitario, int cantidad, boolean existencia) {
        this.inv_cod = inv_cod;
        this.precio_unitario = precio_unitario;
        this.cantidad=cantidad;
        this.existencia = existencia;
    }
    
    public void formInv_ven(Venta ven1){
        this.inv_ven.add(ven1);
    }
    public void dropInv_ven(Venta ven1){
        getInv_ven().remove(ven1);
    }
    public void formInv_exp(Expendio e1){
        this.inv_exp=e1;
    }
    public void dropInv_exp(){
        this.inv_exp=null;
    }
    public void formInv_pre(Presentacion pre1){
        this.inv_pre=pre1;
    }
    public void dropInv_pre(){
        this.inv_pre=null;
    }

    public void setInv_cod(int inv_cod) {
        this.inv_cod = inv_cod;
    }

    public void setPrecio_unitario(float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    

    public int getInv_cod() {
        return inv_cod;
    }

    public int getCantidad() {
        return cantidad;
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

    public List<Venta> getInv_ven() {
        return inv_ven;
    }
   
    
    
    
   
}
