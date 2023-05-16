package com.contactsapp.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.contactsapp.datos.Conexion;
import com.contactsapp.datos.PersonaDao;
import com.contactsapp.domain.PersonaDTO;



public class PersonasController {

    PersonaDao personaDao;
    Scanner sc= new Scanner(System.in);
    private Connection conexionTransaccional;


    public PersonasController(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }

    public void listPersonas() throws NotPersonasRegisteredException, SQLException {
        System.out.println("===========Contact list...===========");
        List<PersonaDTO> personasList = this.personaDao.select();
        if (personasList.isEmpty()) {
            throw new NotPersonasRegisteredException();
        }
        for(PersonaDTO persona: personasList) {
            System.out.println(persona);
        }
        System.out.println("===========Contact list. Finished.===========");
    }

    public void createPersonaDTO(PersonaDTO personaDTO) throws SQLException {
        this.personaDao.insert(personaDTO);
    }

    public void updateUserDTO(PersonaDTO personaDTO) throws SQLException {
        System.out.println("===========Update the Contact===========");
        System.out.println("Enter the id you want to update");
        personaDTO.setId_persona(sc.nextInt());
        System.out.println("Name:");
        sc.nextLine();
        personaDTO.setNombre(sc.nextLine());
        System.out.println("Last name:");
        personaDTO.setApellido(sc.nextLine());
        System.out.println("Email:");
        personaDTO.setEmail(sc.nextLine());
        System.out.println("Phone:");
        personaDTO.setTelefono(sc.nextLine());
        this.personaDao.update(personaDTO);
        System.out.println("===========The data has been updated===========");
    }


    public void deleteUserDTO(PersonaDTO personaDTO) throws SQLException {
        System.out.println("===========Delete Contact===========");
        System.out.println("Enter the ID you want to delete");
        personaDTO.setId_persona(sc.nextInt());
        this.personaDao.delete(personaDTO);
        System.out.println("===========Contact Deleted ===========");
    }



}
