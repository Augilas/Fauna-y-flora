package com.example.semestral.Services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.security.crypto.bcrypt.BCrypt;


import com.example.semestral.Model.Login;
import com.example.semestral.Model.Register;



public class UserDB {
    private final String hashsalt = "$2a$10$asdsamlsacpwpewieqwmlqwñ";
    private Connection _cn;

    public UserDB() {
        _cn = new Conexion().openDb();
    }

    public int GuardarUsuario(Register user) {
        int resultado = 0;
        try {
            CallableStatement cstmt = _cn.prepareCall("{call InsertarUsuario(?, ?, ?, ?, ?, ?)}");
            cstmt.setString(1, user.getPri_nom());
            cstmt.setString(2, user.getPri_ape());
            cstmt.setString(3, user.getCedula());
            cstmt.setString(4, user.getCorreo());
            cstmt.setString(5, user.getUsername());
            cstmt.setString(6, BCrypt.hashpw(user.getPassword(), hashsalt));
            resultado = cstmt.executeUpdate();
            cstmt.close();
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public Boolean BuscarUsuario(Login log) {
        try {
            String query = "SELECT * FROM usuario WHERE Usuario = ?";
            PreparedStatement pstmt = _cn.prepareStatement(query);
            pstmt.setString(1, log.getUsername());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String hashedPasswordFromDB = rs.getString("Contrasena");
                if (BCrypt.checkpw(log.getPassword(), hashedPasswordFromDB)) {
                    return true; 
                } else {
                    return false; 
                }
            }
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int BuscarTipo(Login log) {
        try {
            String query = "SELECT * FROM usuario WHERE Usuario = ?";
            PreparedStatement pstmt = _cn.prepareStatement(query);
            pstmt.setString(1, log.getUsername());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("cod_tipo");
            }
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Boolean BuscarCorreo(String mail) {
        try {
            CallableStatement cstmt = _cn.prepareCall("{call BuscarUsuario(?, ?)}");
            cstmt.setString(1, mail);
            cstmt.registerOutParameter(2, Types.BIT); 
            cstmt.execute(); 
    
            Boolean encontrado = cstmt.getBoolean(2); 
    
            cstmt.close();
            return encontrado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean BuscarToken(String token) {
        try {
            String query = "SELECT * FROM Token WHERE Token = ?";
            PreparedStatement pstmt = _cn.prepareStatement(query);
            pstmt.setString(1, token);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                pstmt.close();
                return true;
            } else {
                pstmt.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }

    public Boolean BuscarTokenCorreo(String correo) {
        try {
            String query = "SELECT * FROM Token WHERE correo = ?";
            PreparedStatement pstmt = _cn.prepareStatement(query);
            pstmt.setString(1, correo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                pstmt.close();
                return true;
            } else {
                pstmt.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
    

    public void Guardartoken(String token, String mail) {
        try {
            String query = "Insert into Token values(?, ?)";
            PreparedStatement pstmt = _cn.prepareStatement(query);
            pstmt.setString(1, mail);
            pstmt.setString(2, token);
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CambioContra(String token, String password) {
        System.out.println(password);
        try {
            CallableStatement cstmt = _cn.prepareCall("{call CambioContrasena(?, ?)}");
            cstmt.setString(1, token);
            cstmt.setString(2, BCrypt.hashpw(password, hashsalt)); 
            cstmt.execute(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
