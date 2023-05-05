package com.contactsapp.datos;

import java.sql.SQLException;
import java.util.List;

import com.contactsapp.domain.UsuarioDTO;

public interface UsuarioDao {

    public List<UsuarioDTO> select() throws SQLException;

    public int insert(UsuarioDTO usuario) throws SQLException;

    public int update(UsuarioDTO usuario) throws SQLException;

    public int delete(UsuarioDTO usuario) throws SQLException;
}
