package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public List<Meal> getAllByUser(int userId) {
        return repository.getAllByUser(userId);
    }

    public List<Meal> getAllByUserBetweenDates(int userId,
                                               LocalDate fromDate,
                                               LocalDate toDate) {
        return repository.getAllByUserBetweenDates(userId, fromDate, toDate);
    }

    public Meal getByUser(int id, int userId) {
        return checkNotFoundWithId(repository.getByUser(id, userId), id);
    }

    public Meal createByUser(Meal user, int userId) {
        return repository.saveByUser(user, userId);
    }

    public void updateByUser(Meal meal, int userId) {
        checkNotFoundWithId(repository.saveByUser(meal, userId), meal.getId());
    }

    public void deleteByUser(int id, int userId) {
        checkNotFoundWithId(repository.deleteByUser(id, userId), id);
    }
}