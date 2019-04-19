package com.conan.servlet;

import com.conan.service.ListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String command = req.getParameter("command");
        String description = req.getParameter("description");

        ListService listService = new ListService();
        req.setAttribute("messageList", listService.queryMessageList(command, description));
        req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
