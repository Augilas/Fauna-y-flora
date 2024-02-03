package com.example.semestral.Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.semestral.Model.GetPost;

public class PostFloraFauna {
    private Connection _cn;

    public PostFloraFauna() {
        _cn = new Conexion().openDb();
    }

    public List<GetPost> obtenerComentariosPorAnimal() {
        List<GetPost> comentarios = new ArrayList<>();

        try {
            Statement statement = _cn.createStatement();
            String query = "SELECT f.Nombre_comun_flora AS NombreFlora, f.Imagen_flora as Imagen, f.Cod_flora as COD," +
                    "(SELECT e.Nombre_estudiante + ' ' + e.Apellido_estudiante AS Nombre, " +
                    "        cf.Comentario_flora AS Comentario " +
                    " FROM Comentarios_flora cf " +
                    " JOIN Usuario u ON cf.Cod_usuario = u.Cod_usuario " +
                    " JOIN Estudiante e ON e.Cedula_estudiante = u.Cedula_estudiante " +
                    " WHERE cf.Cod_flora_comentarios = f.Cod_flora " +
                    " FOR JSON PATH) AS Comentarios " +
                    "FROM Flora f";

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String nombreAnimal = resultSet.getString("NombreFlora");
                String comentariosJson = resultSet.getString("Comentarios");
                String cod = resultSet.getString("COD");
                byte[] imagen = resultSet.getBytes("Imagen");
                GetPost comentario = new GetPost();
                comentario.setNombre(nombreAnimal);
                comentario.setCod(cod);
                comentario.setBase64String(imagen);
                comentario.setComentarios(comentariosJson);
                comentarios.add(comentario);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comentarios;
    }

    // Arreglar cambiar el query a flores
    public List<GetPost> obtenerComentariosPorPlanta() {
        List<GetPost> comentarios = new ArrayList<>();

        try {
            Statement statement = _cn.createStatement();
            String query = "SELECT f.Nombre_comun_animal AS NombreAnimal, f.Imagen_animal as Imagen, f.Cod_animal as COD," +
            "(SELECT e.Nombre_estudiante + ' ' + e.Apellido_estudiante AS Nombre, cf.Comentario_animal AS Comentario " +
            " FROM Comentarios_fauna cf " +
            " JOIN Usuario u ON cf.Cod_usuario = u.Cod_usuario " +
            " JOIN Estudiante e ON e.Cedula_estudiante = u.Cedula_estudiante " +
            " WHERE cf.Cod_animal_comentarios = f.Cod_animal " +
            " FOR JSON PATH) AS Comentarios FROM Fauna f";

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String nombreAnimal = resultSet.getString("NombreAnimal");
                String comentariosJson = resultSet.getString("Comentarios");
                byte[] imagen = resultSet.getBytes("Imagen");
                String cod = resultSet.getString("COD");
                GetPost comentario = new GetPost();
                comentario.setNombre(nombreAnimal);
                comentario.setCod(cod);
                comentario.setComentarios(comentariosJson);
                comentario.setBase64String(imagen);
                comentarios.add(comentario);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return comentarios;
    }
}
