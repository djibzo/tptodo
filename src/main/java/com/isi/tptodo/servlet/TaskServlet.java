package com.isi.tptodo.servlet;

import com.isi.tptodo.ejb.TaskService;
import com.isi.tptodo.ejb.TodoListService;
import com.isi.tptodo.entities.Todolist;
import com.isi.tptodo.entities.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "task",value = "/task")
public class TaskServlet extends HttpServlet {
    @PersistenceContext
    private EntityManager em;

    private TaskService taskService;

    @Override
    public void init() {
        taskService = new TaskService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("complete".equals(action)) {
            // Action pour marquer une tâche comme terminée
            int taskId = Integer.parseInt(req.getParameter("taskId"));
            taskService.markTaskAsCompleted(taskId);
            int todolistId = Integer.parseInt(req.getParameter("todolistId"));
            req.setAttribute("todolistId", todolistId);
            req.getRequestDispatcher("tasks.jsp").forward(req, resp);
        } else {
            // Action par défaut : Ajouter une tâche
            String description = req.getParameter("description");
            int todolistId = Integer.parseInt(req.getParameter("todolistId"));

            Todolist todolist = em.find(Todolist.class, todolistId);
            if (todolist != null) {
                taskService.addTask(todolist, description, 0);
                req.setAttribute("todolistId", todolistId);
                req.getRequestDispatcher("tasks.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Todolist non trouvée");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("tasks.jsp");
    }
}
