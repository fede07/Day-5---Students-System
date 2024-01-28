package com.test.presentacion;

import java.util.List;
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

    private static boolean ejecutarOperacion(Scanner consola, EstudianteDAO estudianteDAO) {
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion) {
            case 1 -> { // LISTAR
                System.out.println("Listado Estudiantes: ");
                List<Estudiante> estudiantes = estudianteDAO.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> { // BUSCAR
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
            case 3 -> { // AGREGAR
                System.out.print("Nombre estudiante: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();
                Estudiante nuevoEstudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDAO.agregarEstudiante(nuevoEstudiante);
                if (agregado) {
                    System.out.println("Estudiante agregado: " + nuevoEstudiante);
                } else {
                    System.out.println("No se agrego el estudiante" + nuevoEstudiante);
                }
            }
            case 4 -> { // MODIFICAR
                System.out.print("ID estudiante a modificar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre estudiante: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Telefono: ");
                var telefono = consola.nextLine();
                System.out.print("Email: ");
                var email = consola.nextLine();
                Estudiante estudianteModificar = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                var modificado = estudianteDAO.modificarEstudiante(estudianteModificar);
                if (modificado) {
                    System.out.println("Estudiante Modificado: " + estudianteModificar);
                } else {
                    System.out.println("Estudiante no modificado: " + estudianteModificar);
                }
            }
            case 5 -> { //ELIMINAR
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
            case 6 -> {
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
