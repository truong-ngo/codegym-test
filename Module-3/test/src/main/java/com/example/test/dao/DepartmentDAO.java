package com.example.test.dao;

import com.example.test.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements InterfaceDAO<Department, Long> {
    private final String SELECT_BY_ID = "select * from department where id = ?;";
    private final String SELECT_ALL = "select * from department;";
    DBConnection dbConn = DBConnection.getInstance();

    private static DepartmentDAO instance;

    private DepartmentDAO() {
    }

    public static DepartmentDAO getInstance() {
        if (instance == null) {
            instance = new DepartmentDAO();
        }
        return instance;
    }
    @Override
    public Department findById(Long id) throws SQLException, ClassNotFoundException {
        try (Connection connection = dbConn.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            return getList(result).get(0);
        }
    }

    @Override
    public List<Department> findAll() throws SQLException, ClassNotFoundException {
        try (Connection connection = dbConn.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet result = statement.executeQuery();
            return getList(result);
        }
    }

    @Override
    public void insert(Department department) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void update(Long id, Department department) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void delete(Long id) throws SQLException, ClassNotFoundException {

    }

    public List<Department> getList(ResultSet result) throws SQLException {
        List<Department> departments = new ArrayList<>();
        while (result.next()) {
            Long id = result.getLong("id");
            String name = result.getString("name");
            departments.add(new Department(id, name));
        }
        return departments;
    }
}
