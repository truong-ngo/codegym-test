package com.example.test.service;

import com.example.test.dao.DepartmentDAO;
import com.example.test.dao.EmployeeDAO;
import com.example.test.model.Department;
import com.example.test.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService implements IService<Employee> {
    private static EmployeeService instance;

    private EmployeeService() {
    }

    public static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    @Override
    public void create(HttpServletRequest request) throws ServletException, IOException, SQLException, ClassNotFoundException {
        Employee employee = getEmployee(request);
        EmployeeDAO.getInstance().insert(employee);
    }


    @Override
    public void render(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        List<Employee> employees = EmployeeDAO.getInstance().findAll();
        List<Department> departments = DepartmentDAO.getInstance().findAll();
        request.setAttribute("employees", employees);
        request.setAttribute("departments", departments);
    }

    @Override
    public void update(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        Long id = Long.parseLong(request.getParameter("id"));
        Employee employee = getEmployee(request);
        EmployeeDAO.getInstance().update(id, employee);
    }

    @Override
    public void delete(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        Long id = Long.parseLong(request.getParameter("id"));
        EmployeeDAO.getInstance().delete(id);
    }

    public Employee getEmployee(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone_number");
        double salary = Double.parseDouble(request.getParameter("salary"));
        Long departmentId = Long.parseLong(request.getParameter("department_id"));
        Department department = DepartmentDAO.getInstance().findById(departmentId);
        return new Employee(name, email, address, phoneNumber, salary, department);
    }

    public void renderSearch(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String search = request.getParameter("search");
        List<Employee> employees = EmployeeDAO.getInstance().findByName(search);
        List<Department> departments = DepartmentDAO.getInstance().findAll();
        request.setAttribute("employees", employees);
        request.setAttribute("departments", departments);
    }
}
