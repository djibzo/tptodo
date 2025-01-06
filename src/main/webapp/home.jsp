<%@ page import="com.isi.tptodo.entities.Todolist" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Todolists</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<body>
<div class="container mt-4">
    <h1 class="mb-4">Liste des Todolists</h1>
    <a href="logout" class="btn btn-danger mb-3">Deconnexion</a>

    <table class="table table-bordered">
        <thead class="table-light">
        <tr>
            <th>ID</th>
            <th>Titre</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Todolist> todolists = (List<com.isi.tptodo.entities.Todolist>) request.getAttribute("todolists");
            if (todolists != null && !todolists.isEmpty()) {
                for (com.isi.tptodo.entities.Todolist todolist : todolists) {
        %>
        <tr>
            <td><%= todolist.getId() %></td>
            <td><%= todolist.getTitle() %></td>
            <td>
                <form action="tasks.jsp" method="GET" class="d-inline">
                    <input type="hidden" name="todolistId" value="<%= todolist.getId() %>">
                    <button type="submit" class="btn btn-primary btn-sm">Ajouter des Tasks</button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3" class="text-center">Aucune Todolist disponible.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <a href="todoList.jsp" class="btn btn-success">Creer une nouvelle Todolist</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
