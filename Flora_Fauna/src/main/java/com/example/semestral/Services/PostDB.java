package com.example.semestral.Services;

import com.example.semestral.Model.Comentario;
import com.example.semestral.Model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostDB {
    private Connection _cn;

    public PostDB() {
        _cn = new Conexion().openDb();
    }

    public int GuardarPost(Post post, String user) {
        int Cod_lugar = 0;
        String cedula = "", Cod_flora = "";

        try {
            if ("flora".equals(post.getTipo())) {
                //Verifica si ya existe con ese nombre
                String VerifQuery = "SELECT COUNT(*) AS count FROM Flora WHERE Nombre_comun_flora = ?";
                PreparedStatement selectVerifPstmt = _cn.prepareStatement(VerifQuery);
                selectVerifPstmt.setString(1, post.getNombre());
                ResultSet rwws = selectVerifPstmt.executeQuery();
                
                boolean plantaExistente = false;
                
                if (rwws.next()) {
                    int count = rwws.getInt("count");
                    plantaExistente = count > 0;
                }
                
                rwws.close();
                selectVerifPstmt.close();                

                if (!plantaExistente) {
                    String insertQuery = "INSERT INTO Flora (Nombre_comun_flora, Imagen_flora) VALUES (?, ?)";
                    PreparedStatement insertPstmt = _cn.prepareStatement(insertQuery);
                    insertPstmt.setString(1, post.getNombre());
                    insertPstmt.setBytes(2, post.getbase64String());
                    insertPstmt.executeUpdate();
    
                    // Segunda consulta: seleccionar Cedula_estudiante de la tabla Usuario y
                    String selectQuery = "SELECT u.Cedula_estudiante " +
                            "FROM Usuario u " +
                            "JOIN Estudiante e ON u.Cedula_estudiante = e.Cedula_estudiante " +
                            "WHERE u.Usuario = ?";
                    PreparedStatement selectPstmt = _cn.prepareStatement(selectQuery);
                    selectPstmt.setString(1, user);
                    ResultSet rs = selectPstmt.executeQuery();
                    while (rs.next()) {
                        cedula = rs.getString("Cedula_estudiante");
                    }
                    rs.close();
                    selectPstmt.close();
    
                    // Tercera consulta: seleccionar Cod_lugar de la tabla Lugar
                    String selectLugarQuery = "SELECT Cod_lugar FROM Lugar WHERE Nombre_lugar = ?";
                    PreparedStatement selectLugarPstmt = _cn.prepareStatement(selectLugarQuery);
                    selectLugarPstmt.setString(1, post.getLugar());
                    ResultSet lugarRs = selectLugarPstmt.executeQuery();
                    while (lugarRs.next()) {
                        Cod_lugar = lugarRs.getInt("Cod_lugar");
                    }
                    lugarRs.close();
                    selectLugarPstmt.close();
    
                    // Cuarta consulta: seleccionar Cod_Flora de la tabla Flora
                    String selectCodFloraQuery = "select Cod_flora from Flora where Nombre_comun_flora = ?";
                    PreparedStatement selectFloraPstmt = _cn.prepareStatement(selectCodFloraQuery);
                    selectFloraPstmt.setString(1, post.getNombre());
                    ResultSet rws = selectFloraPstmt.executeQuery();
                    while (rws.next()) {
                        Cod_flora = rws.getString("Cod_flora");
                    }
                    rws.close();
                    selectFloraPstmt.close();
    
                    // Quinta consulta: Ternaria
                    String insertQuery2 = "insert into Lugar_flora_estudiante (Cod_lugar_flora, Cod_flora_Lugar_flora, Cedula_estudiante_lugar_flora) values (?, ?, ?)";
                    PreparedStatement insertPstmt2 = _cn.prepareStatement(insertQuery2);
                    insertPstmt2.setInt(1, Cod_lugar);
                    insertPstmt2.setString(2, Cod_flora);
                    insertPstmt2.setString(3, cedula);
                    insertPstmt2.executeUpdate();
    
                    return 1;
                } else {
                    return 0;
                }
            } else {
                //Verifica si ya existe con ese nombre
                String VerifQuery = "SELECT COUNT(*) AS count FROM Fauna WHERE Nombre_comun_animal = ?";
                PreparedStatement selectVerifPstmt = _cn.prepareStatement(VerifQuery);
                selectVerifPstmt.setString(1, post.getNombre());
                ResultSet rwws = selectVerifPstmt.executeQuery();
                
                boolean animalExistente = false;
                
                if (rwws.next()) {
                    int count = rwws.getInt("count");
                    animalExistente = count > 0;
                }
                
                rwws.close();
                selectVerifPstmt.close();                

                if (!animalExistente) {
                    String insertQuery = "INSERT INTO Fauna (Nombre_comun_animal, Imagen_animal) VALUES (?, ?)";
                    PreparedStatement insertPstmt = _cn.prepareStatement(insertQuery);
                    insertPstmt.setString(1, post.getNombre());
                    insertPstmt.setBytes(2, post.getbase64String());
                    insertPstmt.executeUpdate();
    
                    // Segunda consulta: seleccionar Cedula_estudiante de la tabla Usuario y
                    String selectQuery = "SELECT u.Cedula_estudiante " +
                            "FROM Usuario u " +
                            "JOIN Estudiante e ON u.Cedula_estudiante = e.Cedula_estudiante " +
                            "WHERE u.Usuario = ?";
                    PreparedStatement selectPstmt = _cn.prepareStatement(selectQuery);
                    selectPstmt.setString(1, user);
                    ResultSet rs = selectPstmt.executeQuery();
                    while (rs.next()) {
                        cedula = rs.getString("Cedula_estudiante");
                    }
                    rs.close();
                    selectPstmt.close();
    
                    // Tercera consulta: seleccionar Cod_lugar de la tabla Lugar
                    String selectLugarQuery = "SELECT Cod_lugar FROM Lugar WHERE Nombre_lugar = ?";
                    PreparedStatement selectLugarPstmt = _cn.prepareStatement(selectLugarQuery);
                    selectLugarPstmt.setString(1, post.getLugar());
                    ResultSet lugarRs = selectLugarPstmt.executeQuery();
                    while (lugarRs.next()) {
                        Cod_lugar = lugarRs.getInt("Cod_lugar");
                    }
                    lugarRs.close();
                    selectLugarPstmt.close();
    
                    // Cuarta consulta: seleccionar Cod_Flora de la tabla Flora
                    String selectCodFaunaQuery = "select Cod_animal from Fauna where Nombre_comun_animal = ?";
                    PreparedStatement selectFaunaPstmt = _cn.prepareStatement(selectCodFaunaQuery);
                    selectFaunaPstmt.setString(1, post.getNombre());
                    ResultSet rws = selectFaunaPstmt.executeQuery();
                    while (rws.next()) {
                        Cod_flora = rws.getString("Cod_animal");
                    }
                    rws.close();
                    selectFaunaPstmt.close();
    
                    // Quinta consulta: Ternaria
                    String insertQuery2 = "insert into Lugar_fauna_estudiante (Cod_lugar_fauna, Cod_animal_lugar_fauna, Cedula_estudiante_lugar_fauna) values (?, ?, ?)";
                    PreparedStatement insertPstmt2 = _cn.prepareStatement(insertQuery2);
                    insertPstmt2.setInt(1, Cod_lugar);
                    insertPstmt2.setString(2, Cod_flora);
                    insertPstmt2.setString(3, cedula);
                    insertPstmt2.executeUpdate();
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    //arreglar
    public int GuardarComentario(Comentario coment, String user) {
        try {
            String Cod_flora = "";
            String selectCodFaunaQuery = "select Cod_animal from Fauna where Nombre_comun_animal = ?";
            PreparedStatement selectFaunaPstmt = _cn.prepareStatement(selectCodFaunaQuery);
            selectFaunaPstmt.setInt(1, coment.getCod_animal());
            ResultSet rws = selectFaunaPstmt.executeQuery();
            while (rws.next()) {
                Cod_flora = rws.getString("Cod_animal");
            }
            rws.close();
            selectFaunaPstmt.close();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
