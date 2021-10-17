package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> saveByUser(meal, meal.getUserId()));
    }

    @Override
    public Meal saveByUser(Meal meal, int userId) {
        log.info("saveByUser {} for {}", meal, userId);
        if (meal.getUserId() != userId)
            return null;
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean deleteByUser(int id, int userId) {
        log.info("deleteByUser {} for {}", id, userId);
        return isPresent(id, userId) && repository.remove(id) != null;
    }

    @Override
    public Meal getByUser(int id, int userId) {
        log.info("getByUser {} for {}", id, userId);
        return isPresent(id, userId) ? repository.get(id) : null;
    }

    @Override
    public List<Meal> getAllByUser(int userId) {
        log.info("getByUser {}", userId);
        return repository.values()
                .stream()
                .filter(meal -> meal.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    private boolean isPresent(int id, int userId) {
        return repository.containsKey(id) && repository.get(id).getUserId() == userId;
    }
}

