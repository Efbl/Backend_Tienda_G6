package com.tienda.DTO;

public class LoginDTO {
    private String password;
    private String usuario;
    public String getPassword() {
        return password;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
