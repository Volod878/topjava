package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealInMemoryDao;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    private final MealInMemoryDao mealInMemoryDao = new MealInMemoryDao();

    private final static int CALORIES_PER_DAY = 2000;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            String id = request.getParameter("id");

            switch (action) {
                case "insert":
                    log.debug(String.format("redirect to editMeal to %s meal", action));

                    request.setAttribute("action", "Add");
                    request.getRequestDispatcher("editMeal.jsp").forward(request, response);
                    return;
                case "update":
                    log.debug(String.format("redirect to editMeal to %s meal", action));

                    request.setAttribute("action", "Edit");
                    request.setAttribute("meal", mealInMemoryDao.get(Long.parseLong(id)));
                    request.getRequestDispatcher("editMeal.jsp").forward(request, response);
                    return;
                case "delete":
                    log.debug("delete meal with id = " + id);

                    mealInMemoryDao.delete(Long.parseLong(id));
                    response.sendRedirect("meals");
                    return;
                default:
                    log.debug("unknown action - '" + action + "'");
                    break;
            }
        }

        log.debug("redirect to meals");

        List<Meal> meals = new ArrayList<>(mealInMemoryDao.getAll());
        request.setAttribute("mealsTo",
                MealsUtil.withExcess(meals, CALORIES_PER_DAY));
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");

        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("datetime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (request.getParameter("id").length() == 0) {
            log.debug("adding a new meal");

            mealInMemoryDao.add(meal);
        } else {
            log.debug("updating meal data");

            meal.setId(Long.parseLong(request.getParameter("id")));
            mealInMemoryDao.update(meal);
        }

        response.sendRedirect("meals");
    }
}
