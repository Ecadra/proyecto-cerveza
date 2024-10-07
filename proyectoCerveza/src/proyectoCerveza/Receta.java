
package proyectoCerveza;
import java.io.Serializable;
import javax.persistence.*;
import java.util.*;


/**
 *
 * @author ulseg
 */
@Entity
public class Receta implements Serializable{

    @Id
    private int id_receta;
    private String rec_cantidad;
   
    @ManyToOne
    @JoinColumn(name="gra_rec",nullable=false)
    private Grano rec_gra;
    
    @ManyToOne
    @JoinColumn(name="cer_rec",nullable=false)
    private Cerveza rec_cer;
    
    public Receta(){
        this.id_receta = -1;
        this.rec_cantidad = "";
    }
    
    public Receta(int id, String nom){
        this.id_receta = id;
        this.rec_cantidad = nom;
    }
    
    
    @Override
    public String toString() {
        return String.format("\n-----\nId: %d"
                + "\nCantidad: %s ",
                this.id_receta, this.rec_cantidad);
    }
    
    public void formRec_gra(Grano g1)
    {
        rec_gra=g1;
    }
    
    public void formRec_cer(Cerveza c1)
    {
        rec_cer=c1;
    }    

    public int getId_receta() {
        return id_receta;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
    }
    
    public String getRec_cantidad() {
        return rec_cantidad;
    }
    
    public void setRec_cantidad(String rec_cantidad) {
        this.rec_cantidad = rec_cantidad;
    }
    
    public Grano getRec_gra() {
        return rec_gra;
    }

    public void setRec_gra(Grano g1) {
        this.rec_gra = rec_gra;
    }

    public Cerveza getRec_cer() {
        return rec_cer;
    }

    public void setRec_cer(Cerveza c1) {
        this.rec_cer = rec_cer;
    }
    
}
