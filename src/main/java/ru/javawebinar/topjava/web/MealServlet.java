package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private static final MealService mealService = new MealService();

    private final static int caloriesPerDay = 2000;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("redirect to meals");

        request.setAttribute("mealsTo", MealsUtil.withExcess(mealService.getAllMeals(), caloriesPerDay));

        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
