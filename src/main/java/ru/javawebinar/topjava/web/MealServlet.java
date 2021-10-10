package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    private static final MealService mealService = new MealService();

    private final static int caloriesPerDay = 2000;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.debug("redirect to meals");

        String action = request.getParameter("action");
        if ("insert".equals(action)) {
            request.getRequestDispatcher("edit_meal.jsp").forward(request, response);

        } else if ("update".equals(action)) {
            String id = request.getParameter("id");
            request.setAttribute("meal", mealService.getMeal(Long.parseLong(id)));
            request.getRequestDispatcher("edit_meal.jsp").forward(request, response);

        } else if ("delete".equals(action)) {
            doDelete(request, response);

        } else {
            request.setAttribute("mealsTo",
                    MealsUtil.withExcess(mealService.getAllMeals(), caloriesPerDay));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String datetime = request.getParameter("datetime");
        String description = request.getParameter("description");
        String calories = request.getParameter("calories");

        if (id == null || id.length() == 0) {
            mealService.addMeal(new Meal(LocalDateTime.parse(datetime), description, Integer.parseInt(calories)));

        } else {
            Meal meal = mealService.getMeal(Long.parseLong(id));
            meal.setDateTime(LocalDateTime.parse(datetime));
            meal.setDescription(description);
            meal.setCalories(Integer.parseInt(calories));
            mealService.editMeal(meal);
        }

        response.sendRedirect("meals");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String id = request.getParameter("id");
        mealService.deleteMeal(Long.parseLong(id));

        response.sendRedirect("meals");
    }
}
