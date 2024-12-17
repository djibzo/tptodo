<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter des Tasks</title>
</head>
<body>
<h1>Ajouter des Tasks à votre Todolist</h1>

<form action="task" method="POST">
    <input type="hidden" name="todolistId" value="<%= request.getParameter("todolistId") %>">
    <label for="description">Description :</label>
    <input type="text" id="description" name="description" required>
    <br><br>
    <button type="submit">Ajouter Task</button>
</form>

<h2>Tasks existantes :</h2>
<ul>
    <!-- Afficher les tasks existantes pour cette Todolist -->
    <%
        int todolistId = Integer.parseInt(request.getParameter("todolistId"));
        com.isi.tptodo.ejb.TodoListService todolistService = new com.isi.tptodo.ejb.TodoListService();
        com.isi.tptodo.entities.Todolist todolist = todolistService.findTodolistById(todolistId);

        for (com.isi.tptodo.entities.Task task : todolist.getTasksById()) {
    %>
    <li><%= task.getDescription() %></li>
    <% } %>
</ul>
</body>
</html>
