package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.DAOMeal;

import java.util.List;

public class MealService {

    private final DAOMeal dao = DAOMeal.getINSTANCE();

    public MealService() {
    }

    public List<Meal> getAllMeals() {
        return dao.getAll();
    }

    public void addMeal(Meal meal) {
        dao.add(meal);
    }

    public Meal getMeal(long id) {
        return dao.get(id);
    }

    public void editMeal(Meal meal) {
        dao.update(meal);
    }

    public void deleteMeal(long id) {
        dao.delete(id);
    }
}
