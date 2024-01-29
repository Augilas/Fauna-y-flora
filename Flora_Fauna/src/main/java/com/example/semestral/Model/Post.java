package com.example.semestral.Model;

public class Post {
    private String tipo;
    private String nombre;
    private String lugar;
    private byte[] base64String;
    private String Usuario;

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setbase64String(byte[] base64String) {
        this.base64String = base64String;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public byte[] getbase64String() {
        return base64String;
    }

    public String getLugar() {
        return lugar;
    }

    public String getTipo() {
        return tipo;
    }
}
