package test;

import datos.Conexion;
import datos.PersonaDao;
import datos.PersonaDaoJDBC;
import domain.PersonaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListarContactos  {
    private Connection conexionTransaccional;
    private static final String SQL_SELECT="SELECT id_persona,nombre, apellido, email, telefono FROM persona";
    public List<PersonaDTO> select() throws SQLException {

        return null;
    }


}

