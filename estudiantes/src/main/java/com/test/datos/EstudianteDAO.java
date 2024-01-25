package com.test.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static com.test.conexion.Conexion.getConexion;


import com.test.dominio.Estudiante;

public class EstudianteDAO {
    public List<Estudiante> listarEstudiantes(){
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante ORDER by id_estudiante";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        return estudiantes;
    }

    public static void main(String[] args) {
        var estudianteDAO = new EstudianteDAO();
        System.out.println("Listado Estudiantes: ");
        List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
        estudiantes.forEach(System.out::println);
    }
}
