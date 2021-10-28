package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(getByLastDateTime().getId(), USER_ID);
        assertMatch(meal, getByLastDateTime());
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
        assertThrows(NotFoundException.class, () -> service.get(FIRST_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(FIRST_MEAL_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(FIRST_MEAL_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
        assertThrows(NotFoundException.class, () -> service.delete(FIRST_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> actual = service.getBetweenInclusive(DATE_24_10_2021, DATE_24_10_2021, USER_ID);
        assertMatch(actual, getAllOn_24_10_2021());

        actual = service.getBetweenInclusive(DATE_25_10_2021, DATE_25_10_2021, USER_ID);
        assertMatch(actual, getAllOn_25_10_2021());
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MealTestData.getAll());
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(dinner_25_10_2021.getId(), USER_ID), getUpdated());
    }

    @Test
    public void updateNotFound() {
        assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void duplicateCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(lunch_25_10_2021.getDateTime(), "Обед", 111), USER_ID));
    }
}