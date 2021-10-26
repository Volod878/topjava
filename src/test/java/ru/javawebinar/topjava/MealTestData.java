package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

public class MealTestData {
    public static final int CURRENT_USER_ID = USER_ID;
    public static final int ANOTHER_USER_ID = ADMIN_ID;
    public static final LocalDate DATE_24_10_2021 = LocalDate.of(2021, Month.OCTOBER, 24);
    public static final LocalDate DATE_25_10_2021 = LocalDate.of(2021, Month.OCTOBER, 25);
    public static final int FIRST_MEAL_ID = 100002;
    public static int nextId = FIRST_MEAL_ID;
    public static final int NOT_FOUND = 100;
    public static final Meal breakfast_25_10_2021 = new Meal(FIRST_MEAL_ID, LocalDateTime.of(2021, Month.OCTOBER, 25, 10, 0), "Завтрак", 500);
    public static final Meal lunch_25_10_2021 = new Meal(++nextId, LocalDateTime.of(2021, Month.OCTOBER, 25, 13, 0), "Обед", 1000);
    public static final Meal dinner_25_10_2021 = new Meal(++nextId, LocalDateTime.of(2021, Month.OCTOBER, 25, 20, 0), "Ужин", 500);
    public static final Meal borderlineMeal_25_10_2021 = new Meal(++nextId, LocalDateTime.of(2021, Month.OCTOBER, 25, 0, 0), "Еда на граничное значение", 1000);
    public static final Meal breakfast_24_10_2021 = new Meal(++nextId, LocalDateTime.of(2021, Month.OCTOBER, 24, 10, 0), "Завтрак", 1000);
    public static final Meal lunch_24_10_2021 = new Meal(++nextId, LocalDateTime.of(2021, Month.OCTOBER, 24, 13, 0), "Обед", 500);
    public static final Meal dinner_24_10_2021 = new Meal(++nextId, LocalDateTime.of(2021, Month.OCTOBER, 24, 20, 0), "Ужин", 410);

    public static Meal getByLastDateTime() {
        return dinner_25_10_2021;
    }

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2021, Month.OCTOBER, 26, 10, 0), "Завтрак", 400);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(dinner_25_10_2021);
        updated.setDateTime(LocalDateTime.of(2021, Month.OCTOBER, 26, 15, 0));
        updated.setDescription("Перекус в Макдаке");
        updated.setCalories(5000);
        return updated;
    }

    public static List<Meal> getAllOn_24_10_2021() {
        return Arrays.asList(dinner_24_10_2021, lunch_24_10_2021, breakfast_24_10_2021);
    }

    public static List<Meal> getAllOn_25_10_2021() {
        return Arrays.asList(dinner_25_10_2021, lunch_25_10_2021, breakfast_25_10_2021, borderlineMeal_25_10_2021);
    }

    public static List<Meal> getAll() {
        return Arrays.asList(dinner_25_10_2021, lunch_25_10_2021, breakfast_25_10_2021, borderlineMeal_25_10_2021,
                dinner_24_10_2021, lunch_24_10_2021, breakfast_24_10_2021);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
