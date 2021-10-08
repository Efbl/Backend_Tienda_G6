package com.tienda.tienda;

import com.tienda.DAO.VentaDAO;
import com.tienda.DTO.VentaDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
public class VentaController {
    @RequestMapping(value = "/registrarVenta", method = RequestMethod.POST)
    public ResponseEntity<?> registrarVenta(@RequestBody VentaDTO Venta) {
        Integer idGenerado;
        VentaDAO dao = new VentaDAO();
        idGenerado = dao.registrarVenta(Venta);
        if(idGenerado != 0) {
            return ResponseEntity.ok(idGenerado);
        }
        return ResponseEntity.ok(0);
    }
}
