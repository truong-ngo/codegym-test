<%--
  Created by IntelliJ IDEA.
  User: X1 Carbon
  Date: 27/11/2022
  Time: 12:11 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/dist/css/bootstrap.css">
</head>
<%
    Object messObj = session.getAttribute("mess");
    if (messObj != null) {
%>
<body onload="return alert('<%= messObj.toString()%>')">
<% } else { %>
<body>
<% } %>
    <div class="container">
        <div class="header my-3">
            <div>
                <button type="button" class="btn btn-outline-dark" data-bs-toggle="modal" data-bs-target="#create-modal">Add</button>
                <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/employee">Back</a>
            </div>
            <form class="search" action="${pageContext.request.contextPath}/employee?&action=search" method="post">
                <div class="search-box input-group input-group-sm">
                    <label class="search-icon" for="search-input">
                        <button class="search-btn"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                        </svg></button>
                    </label>
                    <input id="search-input" type="text" name="search" class="form-control" placeholder="Search">
                </div>
            </form>
        </div>
        <div class="body">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Address</th>
                        <th scope="col">Phone Number</th>
                        <th scope="col">Salary</th>
                        <th scope="col">Department</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${employees}" var="e">
                    <tr>
                        <th scope="row"><c:out value="${e.getId()}"/></th>
                        <td>
                            <c:out value="${e.getName()}"/>
                        </td>
                        <td><c:out value="${e.getEmail()}"/></td>
                        <td><c:out value="${e.getAddress()}"/></td>
                        <td><c:out value="${e.getPhoneNumber()}"/></td>
                        <td><c:out value="${e.getSalary()}"/>$</td>
                        <td><c:out value="${e.getDepartment().getName()}"/></td>
                        <td><button type="button" onclick="openEditModal('${e.getId()}', '${e.getName()}', '${e.getEmail()}', '${e.getAddress()}', '${e.getPhoneNumber()}', '${e.getSalary()}', '${e.getDepartment().getId()}')"
                                    class="edit btn btn-outline-dark" data-bs-toggle="modal" data-bs-target="#edit-modal">Edit
                        </button></td>
                        <td><button type="button" onclick="openDeleteModal(${e.getId()})"
                                    class="delete btn btn-outline-dark" data-bs-toggle="modal" data-bs-target="#delete-modal">Delete
                        </button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%--Create modal--%>
    <div class="modal fade" id="create-modal" tabindex="-1" aria-labelledby="create-header" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="create-header">Add new employee</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/employee?&action=add" method="post">
                        <input id="c-name" type="text" name="name" class="form-control mb-2" placeholder="Enter name:">
                        <input id="c-email" type="text" name="email" class="form-control mb-2" placeholder="Enter email:">
                        <input id="c-address" type="text" name="address" class="form-control mb-2" placeholder="Enter address:">
                        <input id="c-phone_number" type="text" name="phone_number" class="form-control mb-2" placeholder="Enter phone number:">
                        <input id="c-salary" type="text" name="salary" class="form-control mb-2" placeholder="Enter salary:">
                        <select id="c-department" class="form-select" name="department_id" aria-label="Default select example">
                            <option value="">-- Choose department --</option>
                            <c:forEach items="${departments}" var="d">
                                <option value="${d.getId()}"><c:out value="${d.getName()}"/></option>
                            </c:forEach>
                        </select>
                        <div class="submit mt-3">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-success">Create</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%--Edit modal--%>
    <div class="modal fade" id="edit-modal" tabindex="-1" aria-labelledby="edit-header" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="edit-header">Edit employee #<span id="e-id-container"></span></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/employee?&action=edit" method="post">
                        <input id="e-id" type="text" name="id" class="form-control">
                        <input id="e-name" type="text" name="name" class="form-control mb-2" placeholder="Enter name:">
                        <input id="e-email" type="text" name="email" class="form-control mb-2" placeholder="Enter email:">
                        <input id="e-address" type="text" name="address" class="form-control mb-2" placeholder="Enter address:">
                        <input id="e-phone_number" type="text" name="phone_number" class="form-control mb-2" placeholder="Enter phone number:">
                        <input id="e-salary" type="text" name="salary" class="form-control mb-2" placeholder="Enter salary:">
                        <select id="e-department" class="form-select" name="department_id" aria-label="Default select example">
                            <option value="">-- Choose department --</option>
                            <c:forEach items="${departments}" var="d">
                                <option id="focus-${d.getId()}" class="focus" value="${d.getId()}"><c:out value="${d.getName()}"/></option>
                            </c:forEach>
                        </select>
                        <div class="submit mt-3">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-success">Save change</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <%--Delete modal--%>
    <div class="modal fade" id="delete-modal" tabindex="-1" aria-labelledby="delete-header" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="delete-header">Delete employee #<span id="d-id-container"></span></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body d-b-modal">
                    <div>Do you want to delete employee #<span id="d-id-body"></span>?</div>
                    <form action="${pageContext.request.contextPath}/employee?&action=delete" method="post">
                        <input id="d-id" type="text" name="id" class="form-control">
                        <div class="submit mt-3">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-primary">Delete</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/main.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/dist/js/bootstrap.js"></script>
<%
session.invalidate();
%>
</body>
</html>
