package com.test.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection getConexion(){
        Connection connection = null;
        var baseDatos = "estudiantes_db";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "09787352";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return connection;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if(conexion != null){
            System.out.println("Exito!");
        }else{
            System.out.println("Error.");
        }
        
    }
}
