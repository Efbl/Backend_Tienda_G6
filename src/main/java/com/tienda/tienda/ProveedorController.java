package com.tienda.tienda;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.DAO.ProveedorDAO;
import com.tienda.DTO.ProveedorDTO;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProveedorController {
    @RequestMapping(value = "/registrarProveedor", method = RequestMethod.POST)
    public boolean registrarProveedor(@RequestBody ProveedorDTO Proveedor) {
        ProveedorDAO dao = new ProveedorDAO();
        return dao.registrarProveedor(Proveedor);
    }

    @RequestMapping("/consultarProveedor")
    public ArrayList<ProveedorDTO> consultarProveedor(int nit) {
        ProveedorDAO dao = new ProveedorDAO();
        return dao.consultarproveedor(nit);
    }

    @RequestMapping("/listarProveedores")
    public ArrayList<ProveedorDTO> listaDeProveedores() {
        ProveedorDAO dao = new ProveedorDAO();
        return dao.listarProveedores();
    }

    @RequestMapping(value = "/actualizarProveedor", method = RequestMethod.POST)
    public boolean actualizarProveedor(@RequestBody ProveedorDTO proveedor) {
        ProveedorDAO dao = new ProveedorDAO();
        return dao.editarProveedor(proveedor);
    }

    @RequestMapping(value = "/eliminarProveedor", method = RequestMethod.DELETE)
    public void eliminarProveedor(int nit) {
        ProveedorDAO dao = new ProveedorDAO();
        dao.eliminarProveedor(nit);
    }
}
