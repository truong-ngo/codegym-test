package com.example.test.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAO<E, K> {
    E findById(K id) throws SQLException, ClassNotFoundException;
    List<E> findAll() throws SQLException, ClassNotFoundException;
    void insert(E e) throws SQLException, ClassNotFoundException;
    void update(K id, E e)throws SQLException, ClassNotFoundException;
    void delete(K id) throws SQLException, ClassNotFoundException;
}
