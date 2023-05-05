package test.java.com.contactsapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.contactsapp.datos.*;
import com.contactsapp.domain.PersonaDTO;
import com.contactsapp.domain.UsuarioDTO;
import com.contactsapp.services.Contactos;
import com.contactsapp.services.ListarContactos;

public class Main {

    public static void main(String[] args) throws SQLException {
        int option = 0;
        Scanner sc = new Scanner(System.in);
        ListarContactos listarContactos = new ListarContactos();
        Contactos crearContacto= new Contactos();
        Connection conexion = null; // MANI: Este no lo dejes aquí null. Puede causar problemas. Veo que lo inicializas abajo, pero pues ya aquí en corto, pa
        // Connection conection = Conexion.getConnection(); // Y listón
        UsuarioDao usuarioDao = new UsuarioDaoJDBC(conexion);
        PersonaDao personaDao = new PersonaDaoJDBC(conexion);

        System.out.println("***Bienvenido a Mi agenda***");
        System.out.println("***Ingresa la Opcion deseada***");
        System.out.println("1.-Ingresar Usuario");

        System.out.println("2.-Registrar Usuario");
        option= sc.nextInt();

        if (option==1) { // Este if lo puedes pasar a un switch. Cada comportamiento de la aplicación la puedes encerrar en una clase. Te voy a dejar un ejemplo
            System.out.println("Selecciona la opcion deseada");
            System.out.println(" 1.-Lista de Contactos Registrados");
            System.out.println(" 2.-Crear Contacto");
            option= sc.nextInt();

            switch (option) {

                case 1:

                    System.out.println("Lista de Contactos");
                    List<PersonaDTO> personas = personaDao.select();

                    for (PersonaDTO persona : personas) {
                        System.out.println( persona);
                    }
                    break;

                case 2:
                    System.out.println("Crear Nuevo Contacto");
                    PersonaDaoJDBC CrearContacto= new PersonaDaoJDBC(); // nombres de variables en camel case (crearContacto)
                    // Y procura usar inglés: contactsCreator. Algo así quedaria :D
                    CrearContacto.insert(); // No le estas pasando el Dao que va a guardar :O
            }
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

