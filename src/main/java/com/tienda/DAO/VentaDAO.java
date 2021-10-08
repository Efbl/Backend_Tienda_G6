package com.tienda.DAO;

import java.sql.*;
import java.util.ArrayList;

import com.tienda.DTO.ClienteDTO;
import com.tienda.DTO.VentaClienteDTO;
import com.tienda.DTO.VentaDTO;

public class VentaDAO {
    public int registrarVenta(VentaDTO venta) {
        Integer idGenerado = 0;
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
            if (generatedKeys.next()) {
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

    public ArrayList<VentaClienteDTO> listarVentasCliente() {
        Double totalCliente = 0.0;
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<VentaClienteDTO> ventaClientes = new ArrayList<VentaClienteDTO>();
        ArrayList<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
        Conexion conex = new Conexion();
        try {
            clientes = clienteDAO.listarClientes();
            for (int i = 0; i < clientes.size(); i++) {
                VentaClienteDTO ventaCliente = new VentaClienteDTO();
                ventaCliente.setCedulaCliente(clientes.get(i).getCedulaCliente());
                ventaCliente.setNombreCliente(clientes.get(i).getNombreCliente());
                totalCliente = 0.0;
                try{
                    PreparedStatement consulta = conex.getConnection().prepareStatement(
                            "SELECT * FROM ventas WHERE cedula_cliente =" + clientes.get(i).getCedulaCliente());
                    ResultSet rs = consulta.executeQuery();
                    while (rs.next()) {
                        totalCliente = totalCliente + Double.parseDouble(rs.getString("valor_venta"));
                    }
                    rs.close();
                    consulta.close();
                } catch (Exception e) {
                    String message = "No se puede consultar " + e;
                    System.out.println(message);
                }
                ventaCliente.setTotalVentasCliente(totalCliente);
                ventaClientes.add(ventaCliente);
            }
        } catch (Exception e) {
            String message = "No se puede consultar " + e;
            System.out.println(message);
        }
        conex.desconectar();
        return ventaClientes;
    }
}
