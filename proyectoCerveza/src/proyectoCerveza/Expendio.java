package proyectoCerveza;

import estructuras.Direccion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author edwin-993
 */
/* 
 * class Expendio{
	(extent expendios
	key id_expendio)
}

 */
@Entity
public class Expendio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id_expendio;
    private String exp_nombre;
    private String exp_rfc;
    private boolean exp_estado;
    private Direccion exp_direccion;
    private String exp_telefono;

    /* //ATRIBUTOS DE LA CLASE
	attribute int id_expendio;
	attribute string exp_descripcion;
	attribute string exp_tipoEnvase;
	attribute Direccion exp_direccion;
	attribute bigint exp_telefono; 
     */
    @OneToMany
    @JoinColumn(name = "ped_exp", nullable = false)
    private List<Pedido> exp_ped = new ArrayList<Pedido>();
    @OneToOne
    @JoinColumn(name = "inv_exp", nullable = false)
    private Inventario exp_inv;
    @OneToMany
    @JoinColumn(name = "ven_exp", nullable = false)
    private List<Venta> exp_ven = new ArrayList<Venta>();

    /*
// RELACIONES
	relationship Pedido exp_ped
	inverse Pedido::ped_exp;
	relationship set<Inventario> exp_inv
	inverse Inventario::inv_exp;
	relationship set<Venta> exp_ven
	inverse VEnta::ven_exp;
     */
    public void formExp_ped(Pedido ped) {
        this.exp_ped.add(ped);
    }

    public void dropExp_ped(Pedido ped) {
        this.exp_ped.remove(ped);
    }

    public void formExp_inv(Inventario inv) {
        this.exp_inv = inv;
    }

    public void dropExp_inv() {
        this.exp_inv = null;
    }

    public void formExp_ven(Venta ven) {
        this.exp_ven.add(ven);
    }

    public void dropExp_ven() {
        this.exp_ven = null;
    }

    /*
//OPERACIONES
    void formExp_Ped(Pedido ped);
    void dropExp_Ped(Pedido ped);

    void formExp_Inv(Inventario inv);
    void dropExp_Inv(Inventario inv);

    void formExp_Ven(Venta ven);
    void dropExp_Ven(Venta ven);

     */
    public String getExp_nombre() {
        return exp_nombre;
    }

    public void setExp_nombre(String exp_nombre) {
        this.exp_nombre = exp_nombre;
    }

    public String getExp_telefono() {
        return exp_telefono;
    }

    public void setExp_telefono(String telefono) {
        this.exp_telefono = telefono;
    }

    public int getId_expendio() {
        return id_expendio;
    }

    public void setId_expendio(int id_expendio) {
        this.id_expendio = id_expendio;
    }

    public String getExp_rfc() {
        return exp_rfc;
    }

    public void setExp_rfc(String exp_rfc) {
        this.exp_rfc = exp_rfc;
    }

    public boolean isExp_estado() {
        return exp_estado;
    }

    public void setExp_estado(boolean exp_estado) {
        this.exp_estado = exp_estado;
    }

    public Direccion getExp_direccion() {
        return exp_direccion;
    }

    public void setExp_direccion(Direccion exp_direccion) {
        this.exp_direccion = exp_direccion;
    }

    public Inventario getExp_inv() {
        return exp_inv;
    }

    public void setExp_inv(Inventario exp_inv) {
        this.exp_inv = exp_inv;
    }

    public List<Venta> getExp_ven() {
        return exp_ven;
    }

    public void setExp_ped(List<Pedido> exp_ped) {
        this.exp_ped = exp_ped;
    }
    public List<Pedido> getExp_ped(){
        return this.exp_ped;
    }

    public void printVentas() {
        System.out.println("Ventas: " + getExp_ven().size());
        for (int i = 0; i < getExp_ven().size(); i++) {
            System.out.println(getExp_ven().get(i));
        }
    }
    public void printPedidos(){
        System.out.println("Pedidos: " + getExp_ped().size());
        for (int i = 0; i < getExp_ped().size(); i++) {
            System.out.println(getExp_ped().get(i));
        }
    }

    @Override
    public String toString() {
        String estado = this.exp_estado == true ? "En operacion" : "Inhabilitado";
        return String.format("\n-----\nNúmero de expendio: %d"
                + "\nNombre: %s"
                + "\n Estado de operacion: %s"
                + "\n Direccion: %s"
                + "\n Telefono: %s",
                this.id_expendio,
                this.exp_nombre, estado,
                this.exp_direccion,
                this.exp_telefono);
    }

    public Expendio() {
    }

    public Expendio(int id_expendio, String exp_nombre, String exp_rfc, boolean exp_estado, Direccion exp_direccion, String exp_telefono) {
        this.id_expendio = id_expendio;
        this.exp_nombre = exp_nombre;
        this.exp_rfc = exp_rfc;
        this.exp_estado = exp_estado;
        this.exp_direccion = exp_direccion;
        this.exp_telefono = exp_telefono;
    }
    

}
