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
        String description = req.getParameter("description");
        int todolistId = Integer.parseInt(req.getParameter("todolistId"));

        // Récupérer la Todolist à partir de l'ID
        Todolist todolist = em.find(Todolist.class, todolistId);
        if (todolist != null) {
            taskService.addTask(todolist, description,0);
            req.setAttribute("todolistId", todolistId);  // Passer l'ID de la Todolist
            req.getRequestDispatcher("tasks.jsp").forward(req, resp); // Recharger la même page
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Todolist non trouvée");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("tasks.jsp");
    }
}
