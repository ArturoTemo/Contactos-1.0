package com.contactsapp.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.contactsapp.datos.Conexion;
import com.contactsapp.datos.PersonaDao;
import com.contactsapp.datos.PersonaDaoJDBC;
import com.contactsapp.domain.PersonaDTO;

public class ListarContactos  {
    private Connection conexionTransaccional;
    private static final String SQL_SELECT="SELECT id_persona,nombre, apellido, email, telefono FROM persona";
    public List<PersonaDTO> select() throws SQLException {

        return null;
    }


}

