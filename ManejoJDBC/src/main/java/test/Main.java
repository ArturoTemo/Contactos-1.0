package test;

import datos.*;
import domain.PersonaDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        int option = 0;
        Scanner sc = new Scanner(System.in);
        ListarContactos listarContactos = new ListarContactos();
        Connection conexion = null;
        UsuarioDao usuarioDao = new UsuarioDaoJDBC(conexion);
        PersonaDao personaDao = new PersonaDaoJDBC(conexion);
        System.out.println("***Bienvenido a Mi agenda***");
        System.out.println("***Ingresa la Opcion deseada***");
        System.out.println("1.-Ingresar Usuario");

        System.out.println("2.-Registrar Usuario");
        option= sc.nextInt();


        if (option==1) {
            System.out.println("Selecciona la opcion deseada");
            System.out.println(" 1.-Lista de Contactos Registrados");

            option= sc.nextInt();

            switch (option) {


                case 1:

                    System.out.println("Lista de Contactos");
                    List<PersonaDTO> personas = personaDao.select();

                    for (PersonaDTO persona : personas) {
                        System.out.println( persona);
                    }

                case 2:






                    try {
                        conexion = Conexion.getConnection();
                        if (conexion.getAutoCommit()) {
                            conexion.setAutoCommit(false);
                        }







                        conexion.commit();
                        System.out.println("Se ha hecho commit de la transaccion");
                    }


                    catch (SQLException ex) {
                        ex.printStackTrace(System.out);
                        System.out.println("Entramos al rollback");
                        try {
                            conexion.rollback();
                        } catch (SQLException ex1) {
                            ex1.printStackTrace(System.out);
                        }

                    }

            }


        }
    }
}
