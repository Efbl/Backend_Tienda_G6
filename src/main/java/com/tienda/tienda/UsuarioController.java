package com.tienda.tienda;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.DAO.UsuarioDAO;
import com.tienda.DTO.LoginDTO;
import com.tienda.DTO.UsuarioDTO;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
public class UsuarioController {
    @RequestMapping(value = "/registrarUsuario", method = RequestMethod.POST)
    public boolean registrarUsuario(@RequestBody UsuarioDTO usuario) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.registrarUsuario(usuario);
    }

    @RequestMapping("/consultarUsuario")
    public ArrayList<UsuarioDTO> consultarUsuario(int cedula) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.consultarUsuario(cedula);
    }

    @RequestMapping("/consultarUsuarioUsername")
    public ArrayList<UsuarioDTO> consultarUsuarioUsername(String username) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.consultarUsuarioUsername(username);
    }

    @RequestMapping("/listarUsuarios")
    public ArrayList<UsuarioDTO> listaDeUsuarios() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.listarUsuarios();
    }

    @RequestMapping(value = "/actualizarUsuario", method = RequestMethod.POST)
    public boolean actualizarUsuario(@RequestBody UsuarioDTO usuario) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.editarUsuario(usuario);
    }

    @RequestMapping(value = "/eliminarUsuario", method = RequestMethod.DELETE)
    public void eliminarUsuario(int cedula) {
        UsuarioDAO dao = new UsuarioDAO();
        dao.eliminarUsuario(cedula);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean loginUsuario(@RequestBody LoginDTO req) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.loginUsuario(req.getUsuario(), req.getPassword());
    }
}
