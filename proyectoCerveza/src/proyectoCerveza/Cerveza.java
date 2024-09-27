package proyectoCerveza;

/**
 *
 * @author edwin-993
 */
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class Cerveza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int id_cerveza;
    private String cer_nombre;
    private float cer_graduacion;

    //Relaciondes de la clase con cualquier otra
    @OneToMany
    @JoinColumn(name = "lot_cer", nullable = false)
    private List<Lote> cer_lot = new ArrayList<Lote>();
    @OneToMany
    @JoinColumn(name = "pre_cer", nullable = false)
    private List<Presentacion> cer_pre = new ArrayList<Presentacion>();
    @ManyToOne
    @JoinColumn(name = "mar_cer", nullable = false)
    private Marca cer_mar;
    @OneToMany
    @JoinColumn(name = "rec_cer", nullable = false)
    private List<Receta> cer_rec = new ArrayList<Receta>();

    public Cerveza() {
        this.id_cerveza = 0;
        this.cer_nombre = "";
        this.cer_graduacion = 0.0f;
    }

    public Cerveza(int id_cerveza, String cer_nombre, float cer_graduacion, Marca cer_mar) {
        this.id_cerveza = id_cerveza;
        this.cer_nombre = cer_nombre;
        this.cer_graduacion = cer_graduacion;
    }

    @Override
    public String toString() {
        return String.format("\n-----\nCerveza: %s"
                + "\nNombre: %s"
                + "\nGraduacion : %f"
                + "\nLotes: %b"
                + "\nPresentaciones: %b"
                + "\nMarca: %b"
                + "\nRecetas: %b",
                this.cer_nombre,
                this.cer_graduacion,
                this.getCer_lot(),
                this.getCer_pre(),
                this.getCer_mar(),
                this.getCer_rec());
    }

    public void printLotes() {
        System.out.println("Lotes: " + getCer_lot().size());
        for (int i = 0; i < getCer_lot().size(); i++) {
            System.out.println(getCer_lot().get(i));
        }
    }

    public int getId_cerveza() {
        return id_cerveza;
    }

    public void setId_cerveza(int id_cerveza) {
        this.id_cerveza = id_cerveza;
    }

    public String getCer_nombre() {
        return cer_nombre;
    }

    public void setCer_nombre(String cer_nombre) {
        this.cer_nombre = cer_nombre;
    }

    public float getCer_graduacion() {
        return cer_graduacion;
    }

    public void setCer_graduacion(float cer_graduacion) {
        this.cer_graduacion = cer_graduacion;
    }

    public List<Lote> getCer_lot() {
        return cer_lot;
    }

    public void setCer_lot(List<Lote> cer_lot) {
        this.cer_lot = cer_lot;
    }

    public List<Presentacion> getCer_pre() {
        return cer_pre;
    }

    public void setCer_pre(List<Presentacion> cer_pre) {
        this.cer_pre = cer_pre;
    }

    public Marca getCer_mar() {
        return cer_mar;
    }

    public void formCer_mar(Marca cer_mar) {
        this.cer_mar = cer_mar;
    }

    public List<Receta> getCer_rec() {
        return cer_rec;
    }

    public void setCer_rec(List<Receta> cer_rec) {
        this.cer_rec = cer_rec;
    }

    public void formCer_lot(Lote lot) {
        this.cer_lot.add(lot);
    }

    public void dropCer_lot(Lote lot) {
        this.cer_lot.remove(lot);
    }

    public void formCer_pre(Presentacion pre) {
        this.cer_pre.add(pre);
    }

    public void dropCer_pre(Presentacion pre) {
        this.cer_pre.remove(pre);
    }

    ///CERVEZA - MARCA YA SE HIZO ANTERIORMENTE
    public void formCer_rec(Receta rec) {
        this.cer_rec.add(rec);
    }

    public void dropCer_rec(Receta rec) {
        this.cer_rec.remove(rec);
    }

}
