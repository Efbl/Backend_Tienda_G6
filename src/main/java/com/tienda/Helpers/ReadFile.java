package com.tienda.Helpers;

import java.io.IOException;
import java.util.ArrayList;

import com.tienda.DAO.ProductoDAO;
import com.tienda.DAO.ProveedorDAO;
import com.tienda.DTO.ProductoDTO;
import com.tienda.DTO.ProveedorDTO;

import org.springframework.web.multipart.MultipartFile;

public class ReadFile {
    public boolean read(MultipartFile file) {
        ProductoDTO nuevoProducto = new ProductoDTO();
        ProductoDAO productoDAO = new ProductoDAO();
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        int j = 0;
        ArrayList<ProveedorDTO> res = new ArrayList<ProveedorDTO>();
        try {
            byte[] bytes = file.getBytes();
            String completeData = new String(bytes);
            String rows[] = completeData.split("\n");
            for (int i = 1; i < rows.length; i++) {
                String columns[] = rows[i].split(",");
                res = proveedorDAO.consultarProveedor(Integer.parseInt(columns[2]));
                j = 0;
                if (!res.isEmpty()) {
                    nuevoProducto.setCodigoProducto(Integer.parseInt(columns[j]));
                    j++;
                    nuevoProducto.setNombreProducto(columns[j]);
                    j++;
                    nuevoProducto.setNitProveedor(Integer.parseInt(columns[2]));
                    j++;
                    nuevoProducto.setPrecioCompra(Double.parseDouble(columns[j]));
                    j++;
                    nuevoProducto.setIvaCompra(Double.parseDouble(columns[j]));
                    j++;
                    nuevoProducto.setPrecioVenta(Double.parseDouble(columns[j]));
                    j++;
                    productoDAO.registrarProducto(nuevoProducto);
                } else {
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
