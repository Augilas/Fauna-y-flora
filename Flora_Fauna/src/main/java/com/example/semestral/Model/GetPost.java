package com.example.semestral.Model;

public class GetPost {
    private String nombre;
    private String comentarios;
    private byte[] base64String;
    private String cod;
    public String getCod() {
        return cod;
    }
    public void setCod(String cod) {
        this.cod = cod;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
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
