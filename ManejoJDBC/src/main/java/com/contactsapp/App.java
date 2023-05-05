package com.contactsapp;

import java.sql.Connection;
import java.util.Scanner;

import com.contactsapp.controllers.NotPersonasRegisteredException;
import com.contactsapp.controllers.PersonasController;
import com.contactsapp.datos.Conexion;
import com.contactsapp.datos.PersonaDao;
import com.contactsapp.datos.PersonaDaoJDBC;
import com.contactsapp.domain.PersonaDTO;

public class App {

    static Connection conexion;
    public static void main(String[] args) {
        try {
            conexion = Conexion.getConnection();
        } catch (Exception e) {
            System.err.println("Ha sucedido un error al conectarse a la base de datos. Intenta mas tarde");
        }
        PersonaDao personaDao = new PersonaDaoJDBC(conexion);
        PersonasController personasController = new PersonasController(personaDao);

        int option = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("***Bienvenido a Mi agenda***");
        System.out.println("***Ingresa la Opcion deseada***");
        System.out.println("1.-Ingresar Usuario");
        System.out.println("2.-Registrar Usuario");
        option= sc.nextInt();

        switch (option) {
            case 1:
                App.usersMenu(option, sc, personasController);
                break;

            default:
                break;
        }
    }


    public static String inputData(String expectedData, Scanner sc) {
        System.out.printf("Ingresa %s: ", expectedData);
        return sc.nextLine();
    }

    public static void usersMenu(int option, Scanner sc, PersonasController personasController) {
        System.out.println("Selecciona la opcion deseada");
        System.out.println(" 1.-Lista de Contactos Registrados");
        System.out.println(" 2.-Crear Contacto");
        option= sc.nextInt();
        switch (option) {
            case 1:
                try {
                    personasController.listPersonas();
                } catch(NotPersonasRegisteredException e) {
                    System.err.println("No hay usuarios para mostrar. Intenta creando uno nuevo he intenta de nuevo");
                } catch (Exception e) {
                    System.err.println("Ha sucedido un error. Intenta mas tarde");
                }
                break;
            case 2:
                try {
                    System.out.println("Crear Nuevo Contacto");
                    personasController.createPersonaDTO(new PersonaDTO(
                        App.inputData("nombew", sc),
                        App.inputData("apellido", sc),
                        App.inputData("email", sc),
                        App.inputData("telefono", sc)
                    ));
                } catch (Exception e) {
                    System.err.println("Ha sucedido un error. Intenta mas tarde");
                }
                break;
        }
    }
}
