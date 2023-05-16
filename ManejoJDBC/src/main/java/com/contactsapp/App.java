package com.contactsapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.contactsapp.controllers.NotPersonasRegisteredException;
import com.contactsapp.controllers.PersonasController;
import com.contactsapp.datos.Conexion;
import com.contactsapp.datos.PersonaDao;
import com.contactsapp.datos.PersonaDaoJDBC;
import com.contactsapp.domain.PersonaDTO;
import com.contactsapp.domain.UsuarioDTO;

public class App {
    static Connection conexion;

    public static void main(String[] args) throws SQLException {

        try {

            conexion = Conexion.getConnection();
        } catch (Exception e) {
            System.err.println("An error occurred while connecting to the database. try later");
        }
        PersonaDao personaDao = new PersonaDaoJDBC(conexion);
        PersonasController personasController = new PersonasController(personaDao);
        int option = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Press #1 to access Contacts");
        option = sc.nextInt();


        switch (option) {
            case 1:
                App.usersMenu(option, sc, personasController);
        }
    }

    public static String inputData(String expectedData, Scanner sc) {
        System.out.printf("Enter %s: ", expectedData);
        return sc.nextLine();
    }



    public static void usersMenu(int option, Scanner sc, PersonasController personasController) throws SQLException {
        while (option !=5) {
            System.out.println("My Contacts");
            System.out.println(" 1.-Create Contacts List");
            System.out.println(" 2.-Create Contact");
            System.out.println(" 3.-Update Contact");
            System.out.println(" 4.-Delete Contact");
            System.out.println(" 5.-Exit");
            System.out.println("Enter an option: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    try {
                        personasController.listPersonas();
                    } catch (NotPersonasRegisteredException e) {
                        System.err.println("There are no users to display. Try creating a new one and try again");
                    } catch (Exception e) {
                        System.err.println("An error has occurred. try later");
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Create new Contact");
                        personasController.createPersonaDTO(new PersonaDTO(

                                App.inputData("name", sc),
                                App.inputData("last name", sc),
                                App.inputData("email", sc),
                                App.inputData("phone", sc)
                        ));
                    } catch (Exception e) {
                        System.err.println("Ha sucedido un error. Intenta mas tarde");
                    }
                    break;
                case 3:

                    personasController.updateUserDTO(new PersonaDTO());
                    PersonaDTO personaDTO= new PersonaDTO();

                    break;


                case 4:
                    System.out.println("Delete Contact");
                    personasController.deleteUserDTO(new PersonaDTO());
                    break;


            }
        }
    }
}