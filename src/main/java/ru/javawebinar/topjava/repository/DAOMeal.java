package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DAOMeal implements DAO<Meal> {

    private static final List<Meal> meals = new CopyOnWriteArrayList<>();

    private DAOMeal() {
        List<Meal> meals = Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
        DAOMeal.meals.addAll(meals);
    }

    private static class DAOMealHolder {
        public static final DAOMeal HOLDER_INSTANCE = new DAOMeal();
    }

    public static DAOMeal getINSTANCE() {
        return DAOMealHolder.HOLDER_INSTANCE;
    }

    @Override
    public void add(Meal meal) {
        meals.add(meal);
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    @Override
    public Meal get(long id) {
        return meals.stream()
                .filter(m -> m.getId() == id)
                .findFirst().get();
    }

    @Override
    public void update(Meal meal) {
        meals.stream()
                .filter(m -> m.getId() == meal.getId())
                .forEach(m -> m = meal);
    }

    @Override
    public void delete(long id) {
        meals.removeIf(meal -> meal.getId() == id);
    }
}
