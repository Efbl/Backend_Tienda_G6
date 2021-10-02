package com.tienda.tienda;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.DAO.ProductoDAO;
import com.tienda.DTO.ProductoDTO;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
public class ProductoController {
    @RequestMapping(value = "/registrarProducto", method = RequestMethod.POST)
    public boolean registrarProducto(@RequestBody ProductoDTO Producto) {
        ProductoDAO dao = new ProductoDAO();
        return dao.registrarProducto(Producto);
    }

    @RequestMapping("/consultarProducto")
    public ArrayList<ProductoDTO> consultarProducto(int code) {
        ProductoDAO dao = new ProductoDAO();
        return dao.consultarProducto(code);
    }

    @RequestMapping("/listarProductos")
    public ArrayList<ProductoDTO> listaDeProductos() {
        ProductoDAO dao = new ProductoDAO();
        return dao.listarProductos();
    }

    @RequestMapping(value = "/actualizarProducto", method = RequestMethod.POST)
    public boolean actualizarProducto(@RequestBody ProductoDTO producto) {
        ProductoDAO dao = new ProductoDAO();
        return dao.editarProducto(producto);
    }

    @RequestMapping(value = "/eliminarProducto", method = RequestMethod.DELETE)
    public void eliminarProducto(int code) {
        ProductoDAO dao = new ProductoDAO();
        dao.eliminarProducto(code);
    }
}
