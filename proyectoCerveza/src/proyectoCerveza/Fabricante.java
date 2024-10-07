/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoCerveza;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

/**
 *
 * @author ximen
 */
    @Entity
    public class Fabricante implements Serializable {
    private static final long serialVersionUID = 1L;
 
    @Id   
    private String fab_nombre;
    private String fab_contacto;
    
    @OneToMany
    @JoinColumn(name="se_fab",nullable=false)
    private List<Sede> fab_se = new ArrayList<Sede>();
    
 
    public Fabricante(){
     this.fab_nombre = "null";
     this.fab_contacto = "null";
    }
         
    public Fabricante(String nom, String con)
    {  
     fab_nombre=nom;
     fab_contacto=con;
     
    }
 
  @Override
    public String toString() {
        return String.format("\n-----\nNombre: %s "
            + "\nContacto: %s "
            + "\nNúmero de sedes: %d ",
        this.fab_nombre, this.fab_contacto,
        this.getFab_se().size()); // Cambiado a .size()
    }
 
    public void printSedes(){
        System.out.println("Sedes: "+getFab_se().size());
        
        for (int i=0;i<getFab_se().size();i++) {
            System.out.println(getFab_se().get(i));
    }
    }
    
    public void formFab_se(Sede se)
    {
        getFab_se().add(se);
    }
    
    public void dropFab_se(Sede se)
    {
        getFab_se().remove(se);
    }
 
    public String getFab_nombre() {
        return fab_nombre;
    }

    public void setFab_nombre(String fab_nombre) {
        this.fab_nombre = fab_nombre;
    }

    public String getFab_contacto() {
        return fab_contacto;
    }

    public void setFab_contacto(String fab_contacto) {
        this.fab_contacto = fab_contacto;
    }

    public List<Sede> getFab_se() {
        return fab_se;
    }
 

}

