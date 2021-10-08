package com.tienda.DAO;

import java.sql.*;
import com.tienda.DTO.VentaDTO;

public class VentaDAO {
    Integer idGenerado = 0;
    public int registrarVenta(VentaDTO venta) {
        Conexion conex = new Conexion();
        try {
            PreparedStatement ps = conex.getConnection().prepareStatement(
                    "INSERT INTO ventas (cedula_cliente, cedula_usuario, iva_venta, total_venta, valor_venta) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            // ps.setInt(1, venta.getCodigo_venta());
            ps.setInt(1, venta.getCedula_cliente());
            ps.setInt(2, venta.getCedula_usuario());
            ps.setDouble(3, venta.getIva_venta());
            ps.setDouble(4, venta.getTotal_venta());
            ps.setDouble(5, venta.getValor_venta());
            ps.executeQuery();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1);
            }
            ps.close();
            conex.desconectar();
            return idGenerado;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
            // JOptionPane.showMessageDialog(null, "No se pudo registrar el Venta");
        }
    }
}
