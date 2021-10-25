package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int CURRENT_USER_ID = START_SEQ;
    public static final LocalDate START_DATE = LocalDate.of(2021, Month.OCTOBER, 24);
    public static final LocalDate END_DATE = LocalDate.of(2021, Month.OCTOBER, 24);
    public static final int FIRST_MEAL_ID = 100002;
    public static int nextId = FIRST_MEAL_ID;
    public static final int NOT_FOUND = 100;
    public static final List<Meal> meals = Arrays.asList(
            new Meal(FIRST_MEAL_ID, LocalDateTime.of(2021, Month.OCTOBER, 25, 10, 0), "Завтрак", 500),
            new Meal(++nextId, LocalDateTime.of(2021, Month.OCTOBER, 25, 13, 0), "Обед", 1000),
            new Meal(++nextId, LocalDateTime.of(2021, Month.OCTOBER, 25, 20, 0), "Ужин", 500),
            new Meal(++nextId, LocalDateTime.of(2021, Month.OCTOBER, 25, 0, 0), "Еда на граничное значение", 1000),
            new Meal(++nextId, LocalDateTime.of(2021, Month.OCTOBER, 24, 10, 0), "Завтрак", 1000),
            new Meal(++nextId, LocalDateTime.of(2021, Month.OCTOBER, 24, 13, 0), "Обед", 500),
            new Meal(++nextId, LocalDateTime.of(2021, Month.OCTOBER, 24, 20, 0), "Ужин", 410)
    );

    static {
        meals.sort(Comparator.comparing(Meal::getDateTime).reversed());
    }

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2021, Month.OCTOBER, 26, 10, 0), "Завтрак", 400);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meals.get(2));
        updated.setDateTime(LocalDateTime.of(2021, Month.OCTOBER, 26, 15, 0));
        updated.setDescription("Перекус в Макдаке");
        updated.setCalories(5000);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
