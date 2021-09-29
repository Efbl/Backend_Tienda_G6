package com.tienda.DAO;

import java.sql.*;
import java.util.ArrayList;
// import javax.swing.JOptionPane;

import com.tienda.DTO.ProveedorDTO;

public class ProveedorDAO {
    public ArrayList<ProveedorDTO> listarProveedores() {
        ArrayList<ProveedorDTO> proveedores = new ArrayList<ProveedorDTO>();
        Conexion conex = new Conexion();
        try {
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM proveedores");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                ProveedorDTO proveedor = new ProveedorDTO();
                proveedor.setNitProveedor(Integer.parseInt(rs.getString("nit_proveedor")));
                proveedor.setCiudadProveedor(rs.getString("ciudad_proveedor"));
                proveedor.setDireccionProveedor(rs.getString("direccion_proveedor"));
                proveedor.setNombreProveedor(rs.getString("nombre_proveedor"));
                proveedor.setTelefonoProveedor(rs.getString("telefono_proveedor"));
                proveedores.add(proveedor);
            }
            rs.close();
            consulta.close();
            conex.desconectar();
        } catch (Exception e) {
            String message = "No se puede consultar " + e;
            System.out.println(message);
        }
        return proveedores;
    }

    public boolean registrarProveedor(ProveedorDTO proveedor) {
        Conexion conex = new Conexion();
        try {
            PreparedStatement ps = conex.getConnection()
                    .prepareStatement("INSERT INTO proveedores VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, proveedor.getNitProveedor());
            ps.setString(2, proveedor.getCiudadProveedor());
            ps.setString(3, proveedor.getDireccionProveedor());
            ps.setString(4, proveedor.getNombreProveedor());
            ps.setString(5, proveedor.getTelefonoProveedor());
            ps.executeUpdate();
            ps.close();
            conex.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
            // JOptionPane.showMessageDialog(null, "No se pudo registrar el proveedor");
        }
    }

    public ArrayList<ProveedorDTO> consultarproveedor(int nit) {
        ArrayList<ProveedorDTO> miProveedor = new ArrayList<ProveedorDTO>();
        Conexion conex = new Conexion();
        try {
            PreparedStatement consulta = conex.getConnection()
                    .prepareStatement("SELECT * FROM proveedores WHERE nit_proveedor = ?");
            consulta.setInt(1, nit);
            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {
                ProveedorDTO proveedor = new ProveedorDTO();
                proveedor.setNitProveedor(Integer.parseInt(rs.getString("nit_proveedor")));
                proveedor.setCiudadProveedor(rs.getString("ciudad_proveedor"));
                proveedor.setDireccionProveedor(rs.getString("direccion_proveedor"));
                proveedor.setNombreProveedor(rs.getString("nombre_proveedor"));
                proveedor.setTelefonoProveedor(rs.getString("telefono_proveedor"));
                miProveedor.add(proveedor);
            }
            rs.close();
            consulta.close();
            conex.desconectar();
        } catch (Exception e) {
            String message = "No se pudo consultar al proveedor";
            System.out.println(message);
        }
        return miProveedor;
    }

    public boolean editarProveedor(ProveedorDTO proveedor) {
        Conexion conex = new Conexion();
        try {
            String query = "UPDATE proveedores set ciudad_proveedor = '" + proveedor.getCiudadProveedor()
                    + "', direccion_proveedor = '" + proveedor.getDireccionProveedor() + "', nombre_proveedor = '"
                    + proveedor.getNombreProveedor() + "', telefono_proveedor = '" + proveedor.getTelefonoProveedor()
                    + "' WHERE nit_proveedor = '" + proveedor.getNitProveedor() + "'";
            PreparedStatement preparedStatement = conex.getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            conex.desconectar();
            return true;
        } catch (Exception e) {
            String message = "No se pudo actualizar al proveedor";
            System.out.println(message);
            return false;
        }
    }

    public void eliminarProveedor(int nit) {
        Conexion conex = new Conexion();
        PreparedStatement preparedStatement;
        try {
            String query = "DELETE FROM proveedores WHERE nit_proveedor = ?";
            preparedStatement = conex.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, nit);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conex.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
