package com.tienda.DTO;

public class VentaClienteDTO {
    private Integer cedulaCliente;
    private String nombreCliente;
    private Double totalVentasCliente;

    public Integer getCedulaCliente() {
        return cedulaCliente;
    }

    public Double getTotalVentasCliente() {
        return totalVentasCliente;
    }

    public void setTotalVentasCliente(Double totalVentasCliente) {
        this.totalVentasCliente = totalVentasCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setCedulaCliente(Integer cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }
}
