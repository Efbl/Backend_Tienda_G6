package com.tienda.DAO;

import java.sql.*;
import java.util.ArrayList;
// import javax.swing.JOptionPane;

import com.tienda.DTO.UsuarioDTO;

public class UsuarioDAO {
    public ArrayList<UsuarioDTO> listarUsuarios() {
        ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
        Conexion conex = new Conexion();
        try {
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setCedulaUsuario(Integer.parseInt(rs.getString("cedula_usuario")));
                usuario.setEmailUsuario(rs.getString("email_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setUsuario(rs.getString("usuario"));
                usuarios.add(usuario);
            }
            rs.close();
            consulta.close();
            conex.desconectar();
        } catch (Exception e) {
            String message = "No se puede consultar " + e;
            System.out.println(message);
        }
        return usuarios;
    }

    public boolean registrarUsuario(UsuarioDTO usuario) {
        Conexion conex = new Conexion();
        try {
            PreparedStatement ps = conex.getConnection()
                    .prepareStatement("INSERT INTO usuarios VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, usuario.getCedulaUsuario());
            ps.setString(2, usuario.getEmailUsuario());
            ps.setString(3, usuario.getNombreUsuario());
            ps.setString(4, usuario.getPassword());
            ps.setString(5, usuario.getUsuario());
            ps.executeUpdate();
            ps.close();
            conex.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
            // JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario");
        }
    }

    public ArrayList<UsuarioDTO> consultarUsuario(int cedula) {
        ArrayList<UsuarioDTO> miUsuario = new ArrayList<UsuarioDTO>();
        Conexion conex = new Conexion();
        try {
            PreparedStatement consulta = conex.getConnection()
                    .prepareStatement("SELECT * FROM usuarios WHERE cedula_usuario = ?");
            consulta.setInt(1, cedula);
            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setCedulaUsuario(Integer.parseInt(rs.getString("cedula_usuario")));
                usuario.setEmailUsuario(rs.getString("email_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setUsuario(rs.getString("usuario"));
                miUsuario.add(usuario);
            }
            rs.close();
            consulta.close();
            conex.desconectar();
        } catch (Exception e) {
            String message = "No se pudo consultar al usuario";
            System.out.println(message);
        }
        return miUsuario;
    }

    public boolean editarUsuario(UsuarioDTO usuario) {
        Conexion conex = new Conexion();
        try {
            String query = "UPDATE usuarios set email_usuario = '" + usuario.getEmailUsuario() + "', nombre_usuario = '"
                    + usuario.getNombreUsuario() + "', password = '" + usuario.getPassword() + "', usuario = '"
                    + usuario.getUsuario() + "' WHERE cedula_usuario = '" + usuario.getCedulaUsuario() + "'";
            PreparedStatement preparedStatement = conex.getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            conex.desconectar();
            return true;
        } catch (Exception e) {
            String message = "No se pudo actualizar al usuario";
            System.out.println(message);
            return false;
        }
    }

    public void eliminarUsuario(int cedula) {
        Conexion conex = new Conexion();
        PreparedStatement preparedStatement;
        try {
            String query = "DELETE FROM usuarios WHERE cedula_usuario = ?";
            preparedStatement = conex.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, cedula);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conex.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean loginUsuario(String usuario, String password) {
        Conexion conex = new Conexion();
        PreparedStatement preparedStatement;
        boolean res = false;
        try {
            String query = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
            preparedStatement = conex.getConnection().prepareStatement(query);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                res = true;
            }
            preparedStatement.close();
            rs.close();
            conex.desconectar();
            return res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
