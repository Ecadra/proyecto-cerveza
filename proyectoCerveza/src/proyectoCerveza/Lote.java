
package proyectoCerveza;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Lote implements Serializable {
    private static final long serialVersionUID=1L;
    
    @Id
    private int lote_cod;
    private int cantidad;
    private Date lote_fechaProduccion;
    private Date lote_fechaCaducidad;
    
    @ManyToOne
    @JoinColumn(name="cer_lote", nullable=false)
    private Cerveza lote_cer;

    public Lote(int lote_cod, int cantidad, Date lote_fechaProduccion, Date lote_fechaCaducidad) {
        this.lote_cod = lote_cod;
        this.cantidad = cantidad;
        this.lote_fechaProduccion = lote_fechaProduccion;
        this.lote_fechaCaducidad = lote_fechaCaducidad;
    }
    
    public Lote()
    {
        this.lote_cod=0;
        this.cantidad=0;
        this.lote_fechaCaducidad=null;
        this.lote_fechaProduccion=null;
    }
    public String toString(){
        return String.format("\n-----\nCodigo del lote: %d "
                + "\nCantidad: %d "
                + "\nFecha Caducidad: %s"
                + "\nFecha Produccion\n",this.lote_cod,this.cantidad,this.lote_fechaCaducidad,this.lote_fechaProduccion);
    }
    
    public void formLote_cer(Cerveza cer1)
    {
        this.lote_cer=cer1;
    }
    public void dropLote_cer(Cerveza cer1)
    {
        this.lote_cer=cer1;
    }

    public void setLote_cod(int lote_cod) {
        this.lote_cod = lote_cod;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setLote_fechaProduccion(Date lote_fechaProduccion) {
        this.lote_fechaProduccion = lote_fechaProduccion;
    }

    public void setLote_fechaCaducidad(Date lote_fechaCaducidad) {
        this.lote_fechaCaducidad = lote_fechaCaducidad;
    }

    

    public int getLote_cod() {
        return lote_cod;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Date getLote_fechaProduccion() {
        return lote_fechaProduccion;
    }

    public Date getLote_fechaCaducidad() {
        return lote_fechaCaducidad;
    }
    
    
}
