package com.isi.tptodo.servlet;

import com.isi.tptodo.ejb.TodoListService;
import com.isi.tptodo.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/tdl")
public class TodoListServlet extends HttpServlet {
    private final TodoListService todoListService = new TodoListService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //User user = (User) session.getAttribute("user");
        User user2=new User();
        user2.setId(2);
        user2.setUsername("Djibril");
        user2.setPassword("passer");
        if (user2 != null) {
            //String title = request.getParameter("title");
            String title = "test";
            todoListService.createTodoList(user2, title);
            System.out.println("Created");
            // response.sendRedirect("todoLists.jsp");
        } else {
            System.out.println("non Created");
            //response.sendRedirect("login.jsp");
        }
    }
}
