/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoCerveza;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Venta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id_venta;
    private int ven_cantidad;
    @Temporal(TemporalType.DATE)
    private Date ven_fecha;
    private float ven_total;

    @ManyToOne
    @JoinColumn(name = "exp_ven", nullable = false)
    private Expendio ven_exp;

    @ManyToOne
    @JoinColumn(name = "inv_ven", nullable = false)
    private Inventario ven_inv;

    public Venta() {
        this.id_venta = 0;
        this.ven_total = 0.0f;
        this.ven_cantidad = 0;
        this.ven_fecha = new Date(); // Establecer la fecha actual como predeterminada
    }

    public Venta(int id_ven, int cant, String vfecha, float vtotal) {
        this.id_venta = id_ven;
        this.ven_cantidad = cant;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.ven_fecha = sdf.parse(vfecha);
        } catch (ParseException e) {
            e.printStackTrace();
            this.ven_fecha = new Date(); // Establecer la fecha actual si hay un error
        }

        this.ven_total = vtotal;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("\n-----\nId Venta: %d "
                + "\nCantidad: %d "
                + "\nFecha: %s "
                + "\nVenta total: %.2f \n",
                this.id_venta, this.ven_cantidad, sdf.format(this.ven_fecha), this.ven_total);
    }

    public void formVe_exp(Expendio exp) {
        this.ven_exp = exp;
    }

    public void formVe_inv(Inventario inv) {
        this.ven_inv = inv;
    }

    public void dropVe_exp(Expendio exp) {
        this.ven_exp = exp; // Eliminar la relación
    }

    public void dropVe_inv(Inventario inv) {
        this.ven_inv = inv; // Eliminar la relación
    }

    // Getters y Setters
    public float getVen_total() {
        return ven_total;
    }

    public void setVen_total(float ven_total) {
        this.ven_total = ven_total;
    }

    public int getVen_cantidad() {
        return ven_cantidad;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public void setVen_cantidad(int ven_cantidad) {
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

    public Inventario getVen_inv() {
        return ven_inv;
    }

    public void setVen_exp(Expendio ven_exp) {
        this.ven_exp = ven_exp;
    }

    public void setVen_inv(Inventario ven_inv) {
        this.ven_inv = ven_inv;
    }
}
