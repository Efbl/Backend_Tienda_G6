package com.tienda.DTO;

public class ProductoDTO {
    private Integer codigoProducto;
    private Double ivaCompra;
    private String nombreProducto;
    private Integer nitProveedor;
    private Double precioCompra;
    private Double precioVenta;

    public Integer getCodigoProducto() {
        return codigoProducto;
    }

    public Integer getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(Integer nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getIvaCompra() {
        return ivaCompra;
    }

    public void setIvaCompra(Double ivaCompra) {
        this.ivaCompra = ivaCompra;
    }

    public void setCodigoProducto(Integer codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

}
