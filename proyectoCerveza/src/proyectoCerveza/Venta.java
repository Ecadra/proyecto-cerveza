/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoCerveza;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Entity
public class Venta implements Serializable {
private static final long serialVersionUID = 1L;

    @Id
    private String ven_numero;
    private String ven_cantidad;
    private Date ven_fecha;
    
    @ManyToOne
    @JoinColumn(name = "exp_ve", nullable = false)
    private Expendio ven_exp;
    
    @ManyToOne
    @JoinColumn(name = "pre_ve", nullable = false)
    private Presentacion ven_pre;
    
    public Venta() {
        this.ven_numero = "null";
        this.ven_cantidad = "null"; // null en lugar de un string
        this.ven_fecha = null;
    }
    
     @Override
    public String toString() {
         return String.format("\n-----\nNúmero de venta: %s "
                + "\nCantidad de ventas: %s "
                + "\nFecha de venta: %d \n",
                this.ven_numero, this.ven_cantidad, this.ven_fecha);
    }
  
    public void formVe_exp(Expendio e1) {
        this.ven_exp = e1;
    }
    
    public void formVe_pre(Presentacion p1) {
        this.ven_pre = p1;
    }

    // Método para eliminar la relación con el fabricante
    public void dropVe_exp() {
        this.ven_exp = null; // Eliminar la relación
    }
    
     public void dropVe_pre() {
        this.ven_pre = null; // Eliminar la relación
    }
     
    // Getters y Setters
    public String getVen_numero() {
        return ven_numero;
    }

    public void setVen_numero(String ven_numero) {
        this.ven_numero = ven_numero;
    }

    public String getVen_cantidad() {
        return ven_cantidad;
    }

    public void setVen_cantidad(String ven_cantidad) {
        this.ven_cantidad = ven_cantidad;
    }
    
    public Date getVen_fecha() {
        return ven_fecha;
    }

    public void setVen_fecha(Date ven_fecha) {
        this.ven_fecha = ven_fecha;
    }

    public Expendio getVen_exp() {
        return ven_exp;
    }
    
    public Presentacion getVen_pre() {
        return ven_pre;
    }

    public void setVen_exp(Expendio ven_exp) {
        this.ven_exp = ven_exp;
    }
    
    public void setVen_pre(Presentacion ven_pre) {
        this.ven_pre = ven_pre;
    }


}

