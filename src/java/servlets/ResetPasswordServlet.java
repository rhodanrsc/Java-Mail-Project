package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String uuid = request.getParameter("uuid");
        
        if (uuid != null) {
            session.setAttribute("uuid", uuid);
            request.setAttribute("reset_password_uuid", uuid);
            getServletContext().getRequestDispatcher("/WEB-INF/resetNewPassword.jsp").forward(request, response);
            return;
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        
        String email = request.getParameter("email");
        if (email == null) {
            email = (String) session.getAttribute("email");
        }
        String action = request.getParameter("action");
        
        if (action != null && action.equals("Submit") && email != null) {
            String url = request.getRequestURL().toString();
            String path = getServletContext().getRealPath("/WEB-INF");
            session.setAttribute("email", email);
            as.resetPassword(email, path, url);
            response.sendRedirect("login");
        } else if (action != null && action.equals("resetPass") && request.getParameter("newPass") != null) {
            String uuid = (String) session.getAttribute("uuid");
            String password = request.getParameter("newPass");
            System.out.println(password);
            as.changePassword(uuid, password);
            request.setAttribute("message", "Password reset successfully.");
            response.sendRedirect("login");
        }
    }
}
