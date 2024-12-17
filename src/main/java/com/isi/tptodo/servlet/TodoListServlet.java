package com.isi.tptodo.servlet;

import com.isi.tptodo.ejb.TodoListService;
import com.isi.tptodo.entities.Todolist;
import com.isi.tptodo.entities.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "tdl", value = "/tdl")
public class TodoListServlet extends HttpServlet {
    private TodoListService todoListService ;

    @Override
    public void init(ServletConfig config) throws ServletException {
        todoListService=new TodoListService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("loggedUser");
        String title = req.getParameter("title");
        todoListService.createTodoList(user, title);
        List<Todolist> todolists = todoListService.getTodoListsByUser(user.getId());
        req.setAttribute("todolists", todolists);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("todolist.jsp");

    }
}
