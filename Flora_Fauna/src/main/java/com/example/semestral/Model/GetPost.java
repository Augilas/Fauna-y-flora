package com.example.semestral.Model;

public class GetPost {
    private String nombre;
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    private String comentarios;
    private byte[] base64String;

    public String getComentarios() {
        return comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    public byte[] getBase64String() {
        return base64String;
    }
    public void setBase64String(byte[] base64String) {
        this.base64String = base64String;
    }
}
