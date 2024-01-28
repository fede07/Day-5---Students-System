package com.test.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public boolean buscarEstudianteId(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if(rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar conexion: " + e.getLocalizedMessage());
            }
        }
        return false;
    }

    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO estudiante (nombre, apellido, telefono, email) VALUES(?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        return false;
    }

    public boolean modificarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=? WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        return false;
    }

    public boolean eliminarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        var estudianteDAO = new EstudianteDAO();

        //Agregar
        // var nuevoEstudiante = new Estudiante("Carlos", "Gomez", "13215462", "carlito_kpo@gmail.com");
        // var agregado = estudianteDAO.agregarEstudiante(nuevoEstudiante);
        // if (agregado) {
        //     System.out.println("Estudiante agregado: " + nuevoEstudiante);
        // }else{
        //     System.out.println("No se agrego el estudiante" + nuevoEstudiante);
        // }
        
        //Modificar
        // var estudianteModificar = new Estudiante(1, "Juan Carlos", "Pedrabella", "45132146", "juan_p@hotmail.com");
        // var modificado = estudianteDAO.modificarEstudiante(estudianteModificar);    
        // if (modificado) {
        //     System.out.println("Estudiante Modificado: " + estudianteModificar);
        // }else{
        //     System.out.println("Estudiante no modificado: "+ estudianteModificar);
        // }

        //Listar
        System.out.println("Listado Estudiantes: ");
        List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
        estudiantes.forEach(System.out::println);

        //Buscar
        var estudiante1 = new Estudiante(1);
        System.out.println("Estudiante antes de la busqueda: " + estudiante1);
        var encontrado = estudianteDAO.buscarEstudianteId(estudiante1);
        if(encontrado){
            System.out.println("Estudiante encontrado: "+ estudiante1);
        }else{
            System.out.println("No se encontro estudiante con id " + estudiante1.getIdEstudiante());
        }

        //Eliminar
        var estudianteEliminar = new Estudiante(3);
        var eliminado = estudianteDAO.eliminarEstudiante(estudianteEliminar);
        if (eliminado) {
            System.out.println("Estudiante eliminado: " + estudianteEliminar);
        }else{
            System.out.println("No se elimino " + estudianteEliminar);
        }
    }
}
