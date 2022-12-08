package com.example.test.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IService<E> {
    void create(HttpServletRequest request) throws ServletException, IOException, SQLException, ClassNotFoundException;
    void render(HttpServletRequest request) throws SQLException, ClassNotFoundException;
    void update(HttpServletRequest request) throws SQLException, ClassNotFoundException;
    void delete(HttpServletRequest request) throws SQLException, ClassNotFoundException;
}
