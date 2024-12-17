package com.isi.tptodo.servlet;

import com.isi.tptodo.ejb.TodoListService;
import com.isi.tptodo.entities.Todolist;
import com.isi.tptodo.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "home", value = "/home")
public class HomeServlet extends HttpServlet {
    private TodoListService todolistService;

    @Override
    public void init() throws ServletException {
        todolistService = new TodoListService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupérer la liste des Todolists
        User user = (User) req.getSession().getAttribute("loggedUser");
        if (user == null) {
            resp.sendRedirect("login");
            return;
        }
        List<Todolist> todolists = todolistService.getTodoListsByUser(user.getId());

        // Passer les Todolists à la page JSP
        req.setAttribute("todolists", todolists);

        // Rediriger vers la page home.jsp
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}