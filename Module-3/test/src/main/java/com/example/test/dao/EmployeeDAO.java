package com.example.test.dao;

import com.example.test.model.Department;
import com.example.test.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements InterfaceDAO<Employee, Long> {
    private final String SELECT_BY_ID = "select * from employee where id = ?;";
    private final String SELECT_ALL = "select * from employee;";
    private final String INSERT = "insert into employee (name, email, address, phone_number, salary, department_id) " +
                                  "values (?,?,?,?,?,?);";
    private final String UPDATE = "update employee set name = ?, email = ?, address = ?, phone_number = ?" +
                                  ", salary = ?, department_id = ? where id = ?";
    private final String DELETE = "delete from employee where id = ?;";
    private final String SEARCH = "select * from employee where name like ?;";
    DBConnection dbConn = DBConnection.getInstance();
    private static EmployeeDAO instance;

    private EmployeeDAO() {

    }

    public static EmployeeDAO getInstance() {
        if (instance == null) {
            instance = new EmployeeDAO();
        }
        return instance;
    }

    @Override
    public Employee findById(Long id) throws SQLException, ClassNotFoundException {
        try (Connection connection = dbConn.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            return getList(result).get(0);
        }
    }

    public List<Employee> findByName(String name) throws SQLException, ClassNotFoundException {
        try (Connection connection = dbConn.getConnection();
            PreparedStatement statement = connection.prepareStatement(SEARCH)) {
            statement.setString(1, "%" + name + "%");
            ResultSet result = statement.executeQuery();
            return getList(result);
        }
    }

    @Override
    public List<Employee> findAll() throws SQLException, ClassNotFoundException {
        try (Connection connection = dbConn.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet result = statement.executeQuery();
            return getList(result);
        }
    }

    @Override
    public void insert(Employee employee) throws SQLException, ClassNotFoundException {
        try (Connection connection = dbConn.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT)) {
            setUpdateStatement(employee, statement);
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Long id, Employee employee) throws SQLException, ClassNotFoundException {
        try (Connection connection = dbConn.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            setUpdateStatement(employee, statement);
            statement.setLong(7, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException, ClassNotFoundException {
        try (Connection connection = dbConn.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    public List<Employee> getList(ResultSet result) throws SQLException, ClassNotFoundException {
        List<Employee> employees = new ArrayList<>();
        while (result.next()) {
            Long id = result.getLong("id");
            String name = result.getString("name");
            String email = result.getString("email");
            String address = result.getString("address");
            String phoneNumber = result.getString("phone_number");
            double salary = result.getDouble("salary");
            Long departmentId = result.getLong("department_id");
            Department department = DepartmentDAO.getInstance().findById(departmentId);
            employees.add(new Employee(id, name, email, address, phoneNumber, salary, department));
        }
        return employees;
    }

    public void setUpdateStatement(Employee employee, PreparedStatement statement) throws SQLException {
        statement.setString(1, employee.getName());
        statement.setString(2, employee.getEmail());
        statement.setString(3, employee.getAddress());
        statement.setString(4, employee.getPhoneNumber());
        statement.setDouble(5, employee.getSalary());
        statement.setLong(6, employee.getDepartment().getId());
    }
}
