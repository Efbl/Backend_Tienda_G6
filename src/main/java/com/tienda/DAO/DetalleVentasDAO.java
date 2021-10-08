package com.tienda.DAO;

import java.sql.*;
import com.tienda.DTO.DetalleVentaDTO;

public class DetalleVentasDAO {
    public boolean registrarDetalleVenta(DetalleVentaDTO detalleVenta) {
        Conexion conex = new Conexion();
        try {
            PreparedStatement ps = conex.getConnection()
                    .prepareStatement("INSERT INTO detalle_ventas (cantidad_producto, codigo_producto, codigo_venta, valor_total, valor_venta, valor_iva) VALUES (?, ?, ?, ?, ?, ?)");
            // ps.setInt(1, venta.getCodigo_venta());
            ps.setInt(1, detalleVenta.getCantidadProducto());
            ps.setInt(2, detalleVenta.getCodigoProducto());
            ps.setDouble(3, detalleVenta.getCodigoVenta());
            ps.setDouble(4, detalleVenta.getValorTotal());
            ps.setDouble(5, detalleVenta.getValorVenta());
            ps.setDouble(6, detalleVenta.getValorIva());

            ps.executeUpdate();
            ps.close();
            conex.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
            // JOptionPane.showMessageDialog(null, "No se pudo registrar el Venta");
        }
    }
}
