package com.tienda.tienda;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.DAO.ClienteDAO;
import com.tienda.DTO.ClienteDTO;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ClienteController {
    @RequestMapping(value = "/registrarCliente", method = RequestMethod.POST)
    public boolean registrarCliente(@RequestBody ClienteDTO cliente) {
        ClienteDAO dao = new ClienteDAO();
        return dao.registrarCliente(cliente);
    }

    @RequestMapping("/consultarCliente")
    public ArrayList<ClienteDTO> consultarCliente(int cedula) {
        ClienteDAO dao = new ClienteDAO();
        return dao.consultarCliente(cedula);
    }

    @RequestMapping("/listarClientes")
    public ArrayList<ClienteDTO> listaDeClientes() {
        ClienteDAO dao = new ClienteDAO();
        return dao.listarClientes();
    }

    @RequestMapping(value = "/actualizarCliente", method = RequestMethod.POST)
    public boolean actualizarCliente(@RequestBody ClienteDTO cliente) {
        ClienteDAO dao = new ClienteDAO();
        return dao.editarCliente(cliente);
    }

    @RequestMapping(value = "/eliminarCliente", method = RequestMethod.DELETE)
    public void eliminarCliente(int cedula) {
        ClienteDAO dao = new ClienteDAO();
        dao.eliminarCliente(cedula);
    }

}
