package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {
    // null if updated meal do not belong to userId
    Meal saveByUser(Meal meal, int userId);

    // false if meal do not belong to userId
    boolean deleteByUser(int id, int userId);

    // null if meal do not belong to userId
    Meal getByUser(int id, int userId);

    // ORDERED dateTime desc
    List<Meal> getAllByUser(int userId);
}
