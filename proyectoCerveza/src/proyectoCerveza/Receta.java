
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
    private String rec_cantidad;
   
    @OneToMany
    @JoinColumn(name="gra_rec",nullable=false)
    private List<Grano> rec_gra=new ArrayList<Grano>();
    
    @OneToMany
    @JoinColumn(name="cer_rec",nullable=false)
    private List<Cerveza> rec_cer=new ArrayList<Cerveza>();
    
    public Receta(){
        this.rec_cantidad = "";
    }
    
    public Receta(String nom){
        this.rec_cantidad = nom;
    }
    
    
    @Override
    public String toString() {
        return String.format("\n-----\nCantidad: %s "
                + "\nGrano: %s "
                + "\nCerveza: %s \n",
                this.rec_cantidad, this.getRec_gra(), this.getRec_cer());
    }
    
    public void printGranos(){
        System.out.println("Salas: "+getRec_gra().size());
        
        for (int i=0;i<getRec_gra().size();i++) {
            System.out.println(getRec_gra().get(i));
        }
    }
    
    public void printCervezas(){
        System.out.println("Salas: "+getRec_cer().size());
        
        for (int i=0;i<getRec_cer().size();i++) {
            System.out.println(getRec_cer().get(i));
        }
    }
    
    public void formRec_gra(Grano g1)
    {
        getRec_gra().add(g1);
    }
    
    public void formRec_cer(Cerveza c1)
    {
        getRec_cer().add(c1);
    }    
    
    public String getRec_cantidad() {
        return rec_cantidad;
    }
    
    public void setRec_cantidad(String rec_cantidad) {
        this.rec_cantidad = rec_cantidad;
    }
    
    public List<Grano> getRec_gra() {
        return rec_gra;
    }
    
    public List<Cerveza> getRec_cer() {
        return rec_cer;
    }
}
