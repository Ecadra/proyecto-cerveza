package proyectoCerveza;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Presentacion implements Serializable {
    private static final long serialVersionUID=1L;
    
    @Id
    private String pre_cod;
    private String descripcion;
    private String tipoenvase;
    private String capacidad;
    @ManyToOne
    @JoinColumn(name="cer_pre", nullable=false)
    private Cerveza pre_cer;
 
    

    public Presentacion(String pre_cod, String descripcion, String tipoenvase, String capacidad) {
        this.pre_cod = pre_cod;
        this.descripcion = descripcion;
        this.tipoenvase = tipoenvase;
        this.capacidad = capacidad;
    }
    
    public Presentacion(){
        this.pre_cod = null;
        this.descripcion = null;
        this.tipoenvase = null;
        this.capacidad = null;
    }
    
    public String toString(){
        return String.format("\n-----\nCodigo Presetacion: %S "
                + "\nDescripcion: %S"
                + "\n Tipo envase: %S"
                + "\nCapacidad:%S",this.pre_cod,this.descripcion,this.tipoenvase,this.capacidad);
    }
    
    public void formPre_cer(Cerveza cer1)
    {
        pre_cer=cer1;
    }
    public void dropPre_cer(Cerveza cer1)
    {
         pre_cer=cer1;
    }
 

    public void setPre_cod(String pre_cod) {
        this.pre_cod = pre_cod;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipoenvase(String tipoenvase) {
        this.tipoenvase = tipoenvase;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipoenvase() {
        return tipoenvase;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public String getPre_cod() {
        return pre_cod;
    }

   
    
}
