package com.example.semestral.Model;

public class Register {
    private String username;
    private String correo;
    private String password;
    private String cedula;
    private String pri_nom;
    private String pri_ape;
    private int Sede;
    public String getUsername() {
        return username;
    }
    public void setUsuario(String usuario) {
        this.username = usuario;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getPri_nom() {
        return pri_nom;
    }
    public void setPri_nom(String pri_nom) {
        this.pri_nom = pri_nom;
    }
    public String getPri_ape() {
        return pri_ape;
    }
    public void setPri_ape(String pri_ape) {
        this.pri_ape = pri_ape;
    }
    public int getSede() {
        return Sede;
    }
    public void setSede(int sede) {
        Sede = sede;
    }

}
