package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MealInMemoryDao implements Dao<Meal> {

    private final Map<Long, Meal> meals = new ConcurrentHashMap<>();

    private final AtomicLong lastId = new AtomicLong();

    public MealInMemoryDao() {
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        add(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public Meal add(Meal meal) {
        if (!meals.containsValue(meal)) {
            meal.setId(lastId.incrementAndGet());
            meals.put(meal.getId(), meal);
        }
        return meal;
    }

    @Override
    public Collection<Meal> getAll() {
        return meals.values();
    }

    @Override
    public Meal get(long id) {
        return meals.get(id);
    }

    @Override
    public Meal update(Meal meal) {
        meals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(long id) {
        meals.remove(id);
    }
}
