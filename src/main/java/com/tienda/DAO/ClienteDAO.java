package com.tienda.DAO;

import java.sql.*;
import java.util.ArrayList;
// import javax.swing.JOptionPane;

import com.tienda.DTO.ClienteDTO;

public class ClienteDAO {
    public ArrayList<ClienteDTO> listarClientes() {
        ArrayList<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
        Conexion conex = new Conexion();
        try {
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM clientes");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();
                cliente.setCedulaCliente(Integer.parseInt(rs.getString("cedula_cliente")));
                cliente.setDireccionCliente(rs.getString("direccion_cliente"));
                cliente.setEmailCliente(rs.getString("email_cliente"));
                cliente.setNombreCliente(rs.getString("nombre_cliente"));
                cliente.setTelefonoCliente(rs.getString("telefono_cliente"));
                clientes.add(cliente);
            }
            rs.close();
            consulta.close();
            conex.desconectar();
        } catch (Exception e) {
            String message = "No se puede consultar " + e;
            System.out.println(message);
        }
        return clientes;
    }

    public boolean registrarCliente(ClienteDTO cliente) {
        Conexion conex = new Conexion();
        try {
            PreparedStatement ps = conex.getConnection()
                    .prepareStatement("INSERT INTO clientes VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, cliente.getCedulaCliente());
            ps.setString(2, cliente.getDireccionCliente());
            ps.setString(3, cliente.getEmailCliente());
            ps.setString(4, cliente.getNombreCliente());
            ps.setString(5, cliente.getTelefonoCliente());
            ps.executeUpdate();
            ps.close();
            conex.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
            // JOptionPane.showMessageDialog(null, "No se pudo registrar el cliente");
        }
    }

    public ArrayList<ClienteDTO> consultarCliente(int cedula) {
        ArrayList<ClienteDTO> miCliente = new ArrayList<ClienteDTO>();
        Conexion conex = new Conexion();
        try {
            PreparedStatement consulta = conex.getConnection()
                    .prepareStatement("SELECT * FROM clientes WHERE cedula_cliente = ?");
            consulta.setInt(1, cedula);
            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();
                cliente.setCedulaCliente(Integer.parseInt(rs.getString("cedula_cliente")));
                cliente.setDireccionCliente(rs.getString("direccion_cliente"));
                cliente.setEmailCliente(rs.getString("email_cliente"));
                cliente.setNombreCliente(rs.getString("nombre_cliente"));
                cliente.setTelefonoCliente(rs.getString("telefono_cliente"));
                miCliente.add(cliente);
            }
            rs.close();
            consulta.close();
            conex.desconectar();
        } catch (Exception e) {
            String message = "No se pudo consultar al cliente";
            System.out.println(message);
        }
        return miCliente;
    }

    public boolean editarCliente(ClienteDTO cliente) {
        Conexion conex = new Conexion();
        try {
            String query = "UPDATE clientes set direccion_cliente = '" + cliente.getDireccionCliente()
                    + "', email_cliente = '" + cliente.getEmailCliente() + "', nombre_cliente = '"
                    + cliente.getNombreCliente() + "', telefono_cliente = '" + cliente.getTelefonoCliente()
                    + "' WHERE cedula_cliente = '" + cliente.getCedulaCliente() + "'";
            PreparedStatement preparedStatement = conex.getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            conex.desconectar();
            return true;
        } catch (Exception e) {
            String message = "No se pudo actualizar al cliente";
            System.out.println(message);
            return false;
        }
    }

    public void eliminarCliente(int cedula) {
        Conexion conex = new Conexion();
        PreparedStatement preparedStatement;
        try {
            String query = "DELETE FROM clientes WHERE cedula_cliente = ?";
            preparedStatement = conex.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, cedula);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conex.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
