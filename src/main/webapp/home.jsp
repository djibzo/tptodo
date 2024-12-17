<%@ page import="com.isi.tptodo.entities.Todolist" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Todolists</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        button {
            padding: 5px 10px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<%
    if (session.getAttribute("loggedUser") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<body>
<h1>Liste des Todolists</h1>
<a href="logout">
    <button style="margin-top: 2px;color: red" >Deconnexion</button>
</a>
<table>
    <thead>
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
            <!-- Bouton pour ajouter des tâches -->
            <form action="tasks.jsp" method="GET" style="display: inline;">
                <input type="hidden" name="todolistId" value="<%= todolist.getId() %>">
                <button type="submit">Ajouter des Tasks</button>
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="3">Aucune Todolist disponible.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<!-- Lien pour créer une nouvelle Todolist -->
<a href="todoList.jsp">
    <button style="margin-top: 2px" >Creer une nouvelle Todolist</button>
</a>
</body>
</html>
