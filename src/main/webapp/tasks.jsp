<%-- tasks.jsp --%>
<%@ page import="com.isi.tptodo.entities.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="com.isi.tptodo.ejb.TaskService" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter des Tasks</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="mb-4">Ajouter des Tasks a votre Todolist</h1>

    <form action="task" method="POST" class="mb-4">
        <input type="hidden" name="todolistId" value="<%= request.getParameter("todolistId") %>">
        <div class="mb-3">
            <label for="description" class="form-label">Description :</label>
            <input type="text" id="description" name="description" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Ajouter Task</button>
    </form>

    <h2>Tasks existantes :</h2>
    <%
        try {
            int todolistId = Integer.parseInt(request.getParameter("todolistId"));
            TaskService taskService = new TaskService();

            // Récupérer les tâches associées à la Todolist
            List<Task> taskList = taskService.getTasksByTodoList(todolistId);

            if (taskList != null && !taskList.isEmpty()) {
    %>
    <ul class="list-group">
        <% for (Task task : taskList) { %>
        <li class="list-group-item"><%= task.getDescription() %></li>
        <% } %>
    </ul>
    <% } else { %>
    <p class="text-muted">Aucune tache trouvee pour cette liste.</p>
    <% } } catch (Exception e) { %>
    <p>Erreur: <%= e.getMessage() %></p>
    <% } %>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
