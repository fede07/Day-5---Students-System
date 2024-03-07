package com.test.presentacion;

import java.util.Scanner;

import com.test.datos.EstudianteDAO;
import com.test.dominio.Estudiante;

public class PresentacionEstudiantes {
    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        var salir = false;
        while (!salir) {
            mostrarMenu();
            try {
                salir = ejecutarOperacion(consola, estudianteDAO);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
            System.out.println();
        }
    }

    private static void listar(EstudianteDAO estudianteDAO){
        System.out.println("Listado Estudiantes: ");
        var estudiantes = estudianteDAO.listarEstudiantes();
        estudiantes.forEach(System.out::println);
    }

    private static void buscar(EstudianteDAO estudianteDAO, Scanner consola){
        System.out.println("ID Estudiante a buscar: ");
        var idEstudiante = Integer.parseInt(consola.nextLine());
        Estudiante estudianteBuscar = new Estudiante(idEstudiante);
        var encontrado = estudianteDAO.buscarEstudianteId(estudianteBuscar);
        if (encontrado) {
            System.out.println("Estudiante encontrado: " + estudianteBuscar);
        } else {
            System.out.println("No se encontro estudiante con id " + estudianteBuscar.getIdEstudiante());
        }
    }

    private static Estudiante datosEstudiante(Scanner consola){
        System.out.print("Nombre estudiante: ");
        var nombre = consola.nextLine();
        System.out.print("Apellido: ");
        var apellido = consola.nextLine();
        System.out.print("Telefono: ");
        var telefono = consola.nextLine();
        System.out.print("Email: ");
        var email = consola.nextLine();
        Estudiante estudiante = new Estudiante(nombre, apellido, telefono, email);
        return estudiante;
    }

    private static void agregar(EstudianteDAO estudianteDAO, Scanner consola){
        Estudiante nuevoEstudiante = datosEstudiante(consola);
        var agregado = estudianteDAO.agregarEstudiante(nuevoEstudiante);
        if (agregado) {
            System.out.println("Estudiante agregado: " + nuevoEstudiante);
        } else {
            System.out.println("No se agrego el estudiante" + nuevoEstudiante);
        }
    }

    private static void modificar(EstudianteDAO estudianteDAO, Scanner consola){
        System.out.print("ID estudiante a modificar: ");
        var idEstudiante = Integer.parseInt(consola.nextLine());
        Estudiante estudianteModificar = datosEstudiante(consola);
        estudianteModificar.setIdEstudiante(idEstudiante);
        var modificado = estudianteDAO.modificarEstudiante(estudianteModificar);
        if (modificado) {
            System.out.println("Estudiante Modificado: " + estudianteModificar);
        } else {
            System.out.println("Estudiante no modificado: " + estudianteModificar);
        }
    }

    private static void eliminar(EstudianteDAO estudianteDAO, Scanner consola){
        System.out.print("ID estudiante a eliminar: ");
        var idEstudiante = Integer.parseInt(consola.nextLine());
        var estudianteEliminar = new Estudiante(idEstudiante);
        var eliminado = estudianteDAO.eliminarEstudiante(estudianteEliminar);
        if (eliminado) {
            System.out.println("Estudiante eliminado: " + estudianteEliminar);
        }else{
            System.out.println("No se elimino " + estudianteEliminar);
        }
    }

    private static boolean ejecutarOperacion(Scanner consola, EstudianteDAO estudianteDAO) {
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion) {
            case 1 -> { // LISTAR
                listar(estudianteDAO);
            }
            case 2 -> { // BUSCAR
                buscar(estudianteDAO, consola);
            }
            case 3 -> { // AGREGAR
                agregar(estudianteDAO, consola);
            }
            case 4 -> { // MODIFICAR
                modificar(estudianteDAO, consola);
            }
            case 5 -> { //ELIMINAR
                eliminar(estudianteDAO, consola);
            }
            case 6 -> {
                System.out.println("Goodbye!");
                salir = true;
            }
            default -> {
                System.out.println("Opcion invalida");
            }
        }
        return salir;
    }

    private static void mostrarMenu() {
        System.out.println("""
                *** Sistema de Estudiantes ***
                1. Listar Estudiantes
                2. Buscar Estudiantes
                3. Agregar Estudiantes
                4. Modificar Estudiantes
                5. Eliminar Estudiantes
                6. Salir
                """);
        System.out.print("Opcion elegida: ");
    }
}

//test