package com.example.todayfortune.controller;

import com.example.todayfortune.service.FortuneService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// controller
@WebServlet("/fortune")
public class FortuneController extends HttpServlet {
    private FortuneService fortuneService;

    @Override
    public void init() throws ServletException {
        fortuneService = FortuneService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/fortune.jsp").forward(req, resp);
    }
}
