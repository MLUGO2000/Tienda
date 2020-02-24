package com.lugo.manueln.tienda.modelo;

public class producto {

    private int idP;
    private String nombreP,rutaImagenP,descripcionP,categoriaP;
    private double precioP;
    private boolean disponibleP;

    public String getRutaImagenP() {
        return rutaImagenP;
    }

    public void setRutaImagenP(String rutaImagenP) {
        this.rutaImagenP = rutaImagenP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public double getPrecioP() {
        return precioP;
    }

    public void setPrecioP(double precioP) {
        this.precioP = precioP;
    }

    public boolean isDisponibleP() {
        return disponibleP;
    }

    public void setDisponibleP(boolean disponibleP) {
        this.disponibleP = disponibleP;
    }

    public String getCategoriaP() {
        return categoriaP;
    }

    public void setCategoriaP(String categoriaP) {
        this.categoriaP = categoriaP;
    }
}
