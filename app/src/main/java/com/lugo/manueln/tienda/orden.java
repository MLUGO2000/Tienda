package com.lugo.manueln.tienda;

public class orden {


    private producto productoOrden;
    private int cantidadOrden,idProductoOrden;
    private String nombreOrden;
    private String rutaImagenOrden;
    private double precioProductoOrden;
    private double precioTotalOrden;
    private boolean ordenExistente;

    public String getNombreOrden() {
        return nombreOrden;
    }

    public void setNombreOrden(String nombreOrden) {
        this.nombreOrden = nombreOrden;
    }

    public String getRutaImagenOrden() {
        return rutaImagenOrden;
    }

    public void setRutaImagenOrden(String rutaImagenOrden) {
        this.rutaImagenOrden = rutaImagenOrden;
    }

    public double getPrecioProductoOrden() {
        return precioProductoOrden;
    }

    public void setPrecioProductoOrden(double precioProductoOrden) {
        this.precioProductoOrden = precioProductoOrden;
    }

    public double getPrecioTotalOrden() {
        return precioTotalOrden;
    }

    public void setPrecioTotalOrden(double precioTotalOrden) {
        this.precioTotalOrden = precioTotalOrden;
    }

    public producto getProductoOrden() {
        return productoOrden;
    }

    public void setProductoOrden(producto productoOrden) {
        this.productoOrden = productoOrden;
    }

    public int getCantidadOrden() {
        return cantidadOrden;
    }

    public void setCantidadOrden(int cantidadOrden) {
        this.cantidadOrden = cantidadOrden;
    }

    public int getIdProductoOrden() {
        return idProductoOrden;
    }

    public void setIdProductoOrden(int idProductoOrden) {
        this.idProductoOrden = idProductoOrden;
    }

    public boolean isOrdenExistente() {
        return ordenExistente;
    }

    public void setOrdenExistente(boolean ordenExistente) {
        this.ordenExistente = ordenExistente;
    }
}
