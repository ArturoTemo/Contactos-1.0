package com.contactsapp.controllers;

import java.sql.SQLException;
import java.util.List;

import com.contactsapp.datos.PersonaDao;
import com.contactsapp.domain.PersonaDTO;

public class PersonasController {

    PersonaDao personaDao;

    public PersonasController(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }

    public void listPersonas() throws NotPersonasRegisteredException, SQLException {
        System.out.println("===========Listando contactos...===========");
        List<PersonaDTO> personasList = this.personaDao.select();
        if (personasList.isEmpty()) {
            throw new NotPersonasRegisteredException();
        }
        for(PersonaDTO persona: personasList) {
            System.out.println(persona);
        }
        System.out.println("===========Lista de Contactos. Terminado.===========");
    }

    public void createPersonaDTO(PersonaDTO personaDTO) throws SQLException {
        this.personaDao.insert(personaDTO);
    }

}
