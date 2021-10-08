package com.tienda.tienda;

import com.tienda.DAO.DetalleVentasDAO;
import com.tienda.DTO.DetalleVentaDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5000")
@RestController
public class DetalleVentaController {
    @RequestMapping(value = "/registrarDetalleVenta", method = RequestMethod.POST)
    public ResponseEntity<?> registrarVenta(@RequestBody DetalleVentaDTO detalleVenta) {
        DetalleVentasDAO dao = new DetalleVentasDAO();
        if(dao.registrarDetalleVenta(detalleVenta)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}
