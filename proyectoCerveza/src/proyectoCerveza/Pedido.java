package proyectoCerveza;
import java.io.Serializable;
import javax.persistence.*;
import java.util.*;
import proyectoCerveza.Expendio;
import proyectoCerveza.Presentacion;
/**
 *
 * @author ulseg
 */
public class Pedido implements Serializable{

    @Id
    private int ped_codigo;
    private short ped_cantidad;
    @Temporal(TemporalType.DATE)
    private Date ped_forden;
    @Temporal(TemporalType.DATE)
    private Date ped_fdespacho;
    private float ped_total;
    private float ped_subtotal;
    private float ped_iva;
    
    
    @ManyToOne
    @JoinColumn(name = "exp_ped", nullable = false)
    private Expendio ped_exp;
    
    @ManyToOne
    @JoinColumn(name = "pre_ped", nullable = false)
    private Presentacion ped_pre;

    
    public Pedido() {
        this.ped_codigo = 0;               // int -> valor inicial 0
        this.ped_cantidad = 0;             // short -> valor inicial 0
        this.ped_forden = null;            // Date -> valor inicial null
        this.ped_fdespacho = null;         // Date -> valor inicial null
        this.ped_total = 0.0f;             // float -> valor inicial 0.0
        this.ped_subtotal = 0.0f;          // float -> valor inicial 0.0
        this.ped_iva = 0.0f;               // float -> valor inicial 0.0
    }

    
    public Pedido(int ped_codigo, short ped_cantidad, Date ped_forden, Date ped_fdespacho, float ped_total,
            float ped_subtotal, float ped_iva) {
        this.ped_codigo = ped_codigo;
        this.ped_cantidad = ped_cantidad;
        this.ped_forden = ped_forden;
        this.ped_fdespacho = ped_fdespacho;
        this.ped_total = ped_total;
        this.ped_subtotal = ped_subtotal;
        this.ped_iva = ped_iva;
        this.ped_exp = ped_exp;
        this.ped_pre = ped_pre;
    }
    
    
    @Override
    public String toString() {
        return String.format(
            "\n-----\nCódigo del pedido: %d "
            + "\nCantidad: %d "
            + "\nFecha de orden: %s "
            + "\nFecha de despacho: %s "
            + "\nTotal: %.2f "
            + "\nSubtotal: %.2f "
            + "\nIVA: %.2f \n",
            this.ped_codigo,this.ped_cantidad,this.ped_forden,this.ped_fdespacho,
            this.ped_total,this.ped_subtotal,this.ped_iva);
    }
    
    public void formPed_exp(Expendio e1) {
        ped_exp = e1;
    }
    
    public void formPed_pre(Presentacion p1) {
        ped_pre = p1;
    }

    public int getPed_codigo() {
        return ped_codigo;
    }

    public void setPed_codigo(int ped_codigo) {
        this.ped_codigo = ped_codigo;
    }

    public short getPed_cantidad() {
        return ped_cantidad;
    }

    public void setPed_cantidad(short ped_cantidad) {
        this.ped_cantidad = ped_cantidad;
    }

    public Date getPed_forden() {
        return ped_forden;
    }

    public void setPed_forden(Date ped_forden) {
        this.ped_forden = ped_forden;
    }

    public Date getPed_fdespacho() {
        return ped_fdespacho;
    }

    public void setPed_fdespacho(Date ped_fdespacho) {
        this.ped_fdespacho = ped_fdespacho;
    }

    public float getPed_total() {
        return ped_total;
    }

    public void setPed_total(float ped_total) {
        this.ped_total = ped_total;
    }

    public float getPed_subtotal() {
        return ped_subtotal;
    }

    public void setPed_subtotal(float ped_subtotal) {
        this.ped_subtotal = ped_subtotal;
    }

    public float getPed_iva() {
        return ped_iva;
    }

    public void setPed_iva(float ped_iva) {
        this.ped_iva = ped_iva;
    }

    public Expendio getPed_exp() {
        return ped_exp;
    }

    public void setPed_exp(Expendio ped_exp) {
        this.ped_exp = ped_exp;
    }

    public Presentacion getPed_pre() {
        return ped_pre;
    }

    public void setPed_pre(Presentacion ped_pre) {
        this.ped_pre = ped_pre;
    }
   
}
