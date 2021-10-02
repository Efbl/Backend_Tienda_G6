package com.tienda.DAO;

import java.sql.*;
import java.util.ArrayList;
// import javax.swing.JOptionPane;

import com.tienda.DTO.ProductoDTO;

public class ProductoDAO {
    public ArrayList<ProductoDTO> listarProductos() {
        ArrayList<ProductoDTO> productos = new ArrayList<ProductoDTO>();
        Conexion conex = new Conexion();
        try {
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM productos");
            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {
                ProductoDTO producto = new ProductoDTO();
                producto.setCodigoProducto(Integer.parseInt(rs.getString("codigo_producto")));
                producto.setIvaCompra(Double.parseDouble(rs.getString("iva_compra")));
                producto.setNitProveedor(Integer.parseInt(rs.getString("nit_proveedor")));
                producto.setNombreProducto(rs.getString("nombre_producto"));
                producto.setPrecioCompra(Double.parseDouble(rs.getString("precio_compra")));
                producto.setPrecioVenta(Double.parseDouble(rs.getString("precio_venta")));
                productos.add(producto);
            }
            rs.close();
            consulta.close();
            conex.desconectar();
        } catch (Exception e) {
            String message = "No se puede consultar " + e;
            System.out.println(message);
        }
        return productos;
    }

    public boolean registrarProducto(ProductoDTO producto) {
        Conexion conex = new Conexion();
        try {
            PreparedStatement ps = conex.getConnection()
                    .prepareStatement("INSERT INTO productos VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, producto.getCodigoProducto());
            ps.setDouble(2, producto.getIvaCompra());
            ps.setInt(3, producto.getNitProveedor());
            ps.setString(4, producto.getNombreProducto());
            ps.setDouble(5, producto.getPrecioCompra());
            ps.setDouble(6, producto.getPrecioVenta());
            ps.executeUpdate();
            ps.close();
            conex.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
            // JOptionPane.showMessageDialog(null, "No se pudo registrar el producto");
        }
    }

    public ArrayList<ProductoDTO> consultarProducto(int code) {
        ArrayList<ProductoDTO> miProducto = new ArrayList<ProductoDTO>();
        Conexion conex = new Conexion();
        try {
            PreparedStatement consulta = conex.getConnection()
                    .prepareStatement("SELECT * FROM productos WHERE codigo_producto = ?");
            consulta.setInt(1, code);
            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {
                ProductoDTO producto = new ProductoDTO();
                producto.setCodigoProducto(Integer.parseInt(rs.getString("codigo_producto")));
                producto.setIvaCompra(Double.parseDouble(rs.getString("iva_compra")));
                producto.setNitProveedor(Integer.parseInt(rs.getString("nit_proveedor")));
                producto.setNombreProducto(rs.getString("nombre_producto"));
                producto.setPrecioCompra(Double.parseDouble(rs.getString("precio_compra")));
                producto.setPrecioVenta(Double.parseDouble(rs.getString("precio_venta")));
                miProducto.add(producto);
            }
            rs.close();
            consulta.close();
            conex.desconectar();
        } catch (Exception e) {
            String message = "No se pudo consultar al proveedor";
            System.out.println(message);
        }
        return miProducto;
    }

    public boolean editarProducto(ProductoDTO producto) {
        Conexion conex = new Conexion();
        try {
            String query = "UPDATE productos set iva_compra = '" + producto.getIvaCompra() + "', nit_proveedor = '"
                    + producto.getNitProveedor() + "', nombre_producto = '" + producto.getNombreProducto()
                    + "', precio_compra = '" + producto.getPrecioCompra() + "', precio_venta = '"
                    + producto.getPrecioVenta() + "' WHERE codigo_producto = '" + producto.getCodigoProducto() + "'";
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

    public void eliminarProducto(int code) {
        Conexion conex = new Conexion();
        PreparedStatement preparedStatement;
        try {
            String query = "DELETE FROM productos WHERE codigo_producto = ?";
            preparedStatement = conex.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, code);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conex.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
