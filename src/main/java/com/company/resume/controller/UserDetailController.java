package com.company.resume.controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "UserDetailController", urlPatterns ="/userdetail")
public class UserDetailController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDaoInter userDaoInter = Context.instanceUserDao();
        String processName = req.getParameter("action");
        System.out.println(processName);
        if (processName.equals("update")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            User user = userDaoInter.getById(id);
            user.setName(name);
            user.setSurname(surname);
            userDaoInter.updateUser(user);
            resp.sendRedirect("users");
        } else if (processName.equals("delete")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            userDaoInter.removeUser(id);

        } else if (processName.equals("search")) {
            try {
                String name = req.getParameter("name");
                String surname = req.getParameter("surname");
                if (name == null || name.trim().isEmpty()){

                    throw new IllegalArgumentException("id is not specified");
                }
                User u = new User(name, surname);
                req.setAttribute("user", u);
                req.getRequestDispatcher("user.jsp").forward(req, resp);
                //resp.sendRedirect("users");
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendRedirect("error");
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String usrId = request.getParameter("id");
            if (usrId == null || usrId.trim().isEmpty()) {
                throw new IllegalArgumentException("id is not specified");
            }

            Integer userid = Integer.parseInt(usrId);
            UserDaoInter user = Context.instanceUserDao();
            User u = user.getById(userid);
            if (u == null) {
                throw new IllegalArgumentException("there is no user with this id");
            }
            request.setAttribute("user", u);
            request.getRequestDispatcher("userdetail.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }
}
