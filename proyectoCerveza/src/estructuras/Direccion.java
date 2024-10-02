/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author edwin-993
 */
public class Direccion{
    private String Calle;
    private int numeroExt;
    private int numeroInt;
    private String Colonia;
    private int codigoPostal;
    private String estado;

    public Direccion(String Calle, int numeroExt, int numeroInt, String Colonia, int codigoPostal, String estado) {
        this.Calle = Calle;
        this.numeroExt = numeroExt;
        this.numeroInt = numeroInt;
        this.Colonia = Colonia;
        this.codigoPostal = codigoPostal;
        this.estado = estado;
    }

    public Direccion() {
    }

    
    
    public String getCalle() {
        return Calle;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }

    public int getNumeroExt() {
        return numeroExt;
    }

    public void setNumeroExt(int numeroExt) {
        this.numeroExt = numeroExt;
    }

    public int getNumeroInt() {
        return numeroInt;
    }

    public void setNumeroInt(int numeroInt) {
        this.numeroInt = numeroInt;
    }

    public String getColonia() {
        return Colonia;
    }

    public void setColonia(String Colonia) {
        this.Colonia = Colonia;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Direccion{" + "Calle=" + Calle + ", numeroExt=" + numeroExt + ", numeroInt=" + numeroInt + ", Colonia=" + Colonia + ", codigoPostal=" + codigoPostal + ", estado=" + estado + '}';
    }

    
    
    
}
