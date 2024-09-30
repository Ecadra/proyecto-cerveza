package proyectoCerveza;


import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Inventario implements Serializable {
    private static final long serialVersionUID=1L;
    
    @Id
    private String inv_cod;
    private boolean existencia;
    @ManyToOne
    @JoinColumn(name="exp_inv", nullable=false)
    private Expendio inv_exp;
    @ManyToOne
    @JoinColumn(name="pre_inv", nullable=false)
    private Presentacion inv_pre;
    
    public Inventario(){
        this.inv_cod=null;
        this.existencia=false;
    }
    public Inventario(String inv_cod, boolean existencia){
        this.inv_cod=inv_cod;
        this.existencia=existencia;
    }
    public String toString(){
        return String.format("\n-----\nNumero sala: %S "
                + "\nCodigo: %S "
                + "\nExistencia: %S\n",
                this.inv_cod,this.existencia);
    }
    
    public void formInv_exp(Expendio exp1)
    {
        inv_exp=exp1;
    }
    public void dropInv_exp(Expendio exp1)
    {
        inv_exp=exp1;
    }
     public void formPre_inv(Presentacion pre1)
    {
        inv_pre=pre1;
    }
    public void dropPre_inv(Presentacion pre1)
    {
        inv_pre=pre1;
    }

    public String getInv_cod() {
        return inv_cod;
    }

    public boolean getExistencia() {
        return existencia;
    }

    public Expendio getInv_exp() {
        return inv_exp;
    }

    public Presentacion getInv_pre() {
        return inv_pre;
    }

    public void setInv_cod(String inv_cod) {
        this.inv_cod = inv_cod;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }

    public void setInv_exp(Expendio inv_exp) {
        this.inv_exp = inv_exp;
    }

    public void setInv_pre(Presentacion inv_pre) {
        this.inv_pre = inv_pre;
    }
    
}
