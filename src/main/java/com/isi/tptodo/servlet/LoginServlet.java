package com.isi.tptodo.servlet;

import com.isi.tptodo.ejb.UserService;
import com.isi.tptodo.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "login",value = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        // Initialisez le UserService (ou injectez-le si votre projet supporte l'injection)
        userService = new UserService();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // Vérifiez les informations d'identification
        User user = userService.authenticate(username, password);

        if (user != null) {
            // Stockez l'utilisateur dans la session
            req.getSession().setAttribute("loggedUser", user);

            // Redirigez vers la page d'accueil ou tableau de bord
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            // Si l'authentification échoue, retournez à la page de connexion avec un message d'erreur
            req.setAttribute("errorMessage", "Nom d'utilisateur ou mot de passe incorrect.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Si un utilisateur est déjà connecté, redirigez vers le tableau de bord
        if (req.getSession().getAttribute("loggedUser") != null) {
            resp.sendRedirect("home");
        } else {
            // Sinon, affichez la page de connexion
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
    }