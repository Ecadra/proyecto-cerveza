package cervezaodb;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author edwin-993
 */
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id_marca;
    private String mar_nombre;

    @OneToMany
    @JoinColumn(name = "cer_mar", nullable = false)
    private List<Cerveza> mar_cer = new ArrayList<Cerveza>();

    public Marca(int id_marca, String mar_nombre) {
        this.id_marca = id_marca;
        this.mar_nombre = mar_nombre;
    }

    public Marca() {
        this.id_marca = 0;
        this.mar_nombre = "";
    }

    public String toString() {
        return String.format("\n-----\nMarca: %d"
                + "\nNombre: %s"
                + "\nCervezas: %b",
                this.id_marca,
                this.mar_nombre,
                this.getMar_cer());
    }

    public void printCervezas() {
        System.out.println("Cervezas: " + getMar_cer().size());
        for (int i = 0; i > getMar_cer().size(); i++) {
            System.out.println(getMar_cer().get(i));
        }
    }

    public void formMar_cer(Cerveza cer) {
        this.mar_cer.add(cer);
    }

    public void dropMar_cer(Cerveza cer) {
        this.mar_cer.remove(cer);
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getMar_nombre() {
        return mar_nombre;
    }

    public void setMar_nombre(String mar_nombre) {
        this.mar_nombre = mar_nombre;
    }

    public List<Cerveza> getMar_cer() {
        return mar_cer;
    }

    public void setMar_cer(List<Cerveza> mar_cer) {
        this.mar_cer = mar_cer;
    }

}
