/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoCerveza;

import java.io.Serializable;
import javax.persistence.*;
import estructuras.Direccion;
/**
 *
 * @author ximen
 */
@Entity
public class Sede implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id_sede;
    private String se_nombre;
    
    @Embedded // Asumiendo que Direccion es una clase embebible
    private Direccion se_direccion;
    
    @ManyToOne
    @JoinColumn(name = "fab_se", nullable = false)
    private Fabricante se_fab;
    
    // Constructor por defecto
    public Sede() {
        this.id_sede = 0;
        this.se_nombre = "null";
        this.se_direccion = null; // null en lugar de un string
    }
    
    // Constructor con parámetros
    public Sede(int id, String se_nom, Direccion dic) {
        this.id_sede = id;
        this.se_nombre = se_nom;
        this.se_direccion = dic;
    }
    
    @Override
    public String toString() {
        return String.format("\n-----\nNombre sede: %s \nDirección: %s",
                this.se_nombre, this.se_direccion);
    }
    
    public void formSe_fab(Fabricante fab) {
        this.se_fab = fab;
    }

    // Método para eliminar la relación con el fabricante
    public void dropSe_fab(Fabricante fab) {
        this.se_fab = fab; // Eliminar la relación
    }
    
    // Getters y Setters
    public String getSe_nombre() {
        return se_nombre;
    }

    public void setSe_nombre(String se_nombre) {
        this.se_nombre = se_nombre;
    }
    
    public int getId_sede(){
        return id_sede;
    }
    
    public void setId_sede(int id_sede){
        this.id_sede = id_sede;
    }

    public Direccion getSe_direccion() {
        return se_direccion;
    }

    public void setSe_direccion(Direccion se_direccion) {
        this.se_direccion = se_direccion;
    }

    public Fabricante getSe_fab() {
        return se_fab;
    }

    public void setSe_fab(Fabricante se_fab) {
        this.se_fab = se_fab;
    }
}

