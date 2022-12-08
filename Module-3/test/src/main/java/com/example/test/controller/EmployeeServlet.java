package com.example.test.controller;

import com.example.test.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EmployeeServlet", value = "/employee")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        controller(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        controller(request, response);
    }

    private void controller(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "add":
                    add(request, response);
                    break;
                case "edit":
                    edit(request, response);
                    break;
                case "delete":
                    delete(request, response);
                    break;
                case "search":
                    search(request, response);
                    break;
                default:
                    renderAll(request, response);
            }
        } catch (SQLException | ClassNotFoundException | ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void renderAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        EmployeeService.getInstance().render(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
        dispatcher.forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException, ClassNotFoundException {
        EmployeeService.getInstance().create(request);
        HttpSession session = request.getSession(true);
        session.setAttribute("mess", "Create successfully");
        response.sendRedirect("/employee");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        EmployeeService.getInstance().update(request);
        HttpSession session = request.getSession(true);
        session.setAttribute("mess", "Edit successfully");
        response.sendRedirect("/employee");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        EmployeeService.getInstance().delete(request);
        HttpSession session = request.getSession(true);
        session.setAttribute("mess", "Delete successfully");
        response.sendRedirect("/employee");
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException, ServletException {
        EmployeeService.getInstance().renderSearch(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
        dispatcher.forward(request, response);
    }
}
