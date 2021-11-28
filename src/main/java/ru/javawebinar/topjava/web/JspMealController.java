package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

@Controller
public class JspMealController {
    @Autowired
    private MealRestController mealController;

    @GetMapping("/meals")
    public String getMeals(Model model) {
        model.addAttribute("meals", mealController.getAll());
        return "meals";
    }

    @GetMapping("/meals/add")
    public String addMeal(Model model) {
        model.addAttribute("action", "create");
        model.addAttribute("meal", new Meal());
        return "mealForm";
    }

    @GetMapping("/meals/{id}")
    public String editMeal(@PathVariable("id") int id, Model model) {
        model.addAttribute("action", "edit");
        model.addAttribute("meal", mealController.get(id));
        return "mealForm";
    }

    @PostMapping("/meals")
    public String createMeal(@RequestBody Meal meal) {
        mealController.create(meal);
        return "redirect:meals";
    }

//    @PutMapping("/meals/{id}")
//    public String updateMeal(@PathVariable("id") int id, @ModelAttribute("meal") Meal meal) {
//        mealController.update(meal, id);
//        return "meals";
//    }

    @DeleteMapping("/meals/{id}")
    public String deleteMeal(@PathVariable("id") int id) {
        mealController.delete(id);
        return "redirect:meals";
    }
}
