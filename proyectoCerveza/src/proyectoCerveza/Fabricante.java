/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoCerveza;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author ximen
 */
    @Entity
    public class Fabricante implements Serializable {
    private static final long serialVersionUID = 1L;
 
    @Id  
    private int id_fab;
    private String fab_nombre;
    private String fab_contacto;
    
    @OneToMany
    @JoinColumn(name="se_fab",nullable=false)
    private List<Sede> fab_se = new ArrayList<Sede>();
    
    @OneToMany
    @JoinColumn(name = "mar_fab",nullable=false)
    private List<Marca> fab_mar = new ArrayList<Marca>();
    
 
    public Fabricante(){
     this.id_fab = 0;
     this.fab_nombre = "null";
     this.fab_contacto = "null";
    }
         
    public Fabricante(int id,String nom, String con)
    {  
     id_fab = id;
     fab_nombre=nom;
     fab_contacto=con;
     
    }

  @Override
   public String toString() {
    return String.format("\n-----\nNombre: %s "
        + "\nContacto: %s ",
    this.fab_nombre, this.fab_contacto);
    }

 
    public void printSedes(){
        System.out.println("Sedes: "+getFab_se().size());
        
        for (int i=0;i<getFab_se().size();i++) {
            System.out.println(getFab_se().get(i));
    }
    }
    
    public void printMarcas(){
        System.out.println("Marca: "+getFab_mar().size());
        
        for (int i=0;i<getFab_mar().size();i++) {
            System.out.println(getFab_mar().get(i));
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
    
    public void formFab_mar(Marca mar)
    {
        getFab_mar().add(mar);
    }
    
    public void dropFab_se(Marca mar)
    {
        getFab_mar().remove(mar);
    }
    
    public int getId_fab(){
        return id_fab;
    }
    
    public void setId_fab(int id_fab){
        this.id_fab = id_fab;
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
    
     public List<Marca> getFab_mar() {
        return fab_mar;
    }
 

}

