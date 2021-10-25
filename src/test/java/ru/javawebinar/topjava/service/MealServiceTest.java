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
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

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
        Meal meal = service.get(FIRST_MEAL_ID, CURRENT_USER_ID);
        assertMatch(meal, meals.get(2));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, CURRENT_USER_ID));
        assertThrows(NotFoundException.class, () -> service.get(FIRST_MEAL_ID, CURRENT_USER_ID + 1));
    }

    @Test
    public void delete() {
        service.delete(FIRST_MEAL_ID, CURRENT_USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(FIRST_MEAL_ID, CURRENT_USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, CURRENT_USER_ID));
        assertThrows(NotFoundException.class, () -> service.delete(FIRST_MEAL_ID, CURRENT_USER_ID + 1));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> all = service.getBetweenInclusive(START_DATE, END_DATE, CURRENT_USER_ID);
        assertMatch(all, meals.subList(4, 7));
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(CURRENT_USER_ID);
        assertMatch(all, meals);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, CURRENT_USER_ID);
        assertMatch(service.get(FIRST_MEAL_ID, CURRENT_USER_ID), getUpdated());
    }

    @Test
    public void updateNotFound() {
        assertThrows(NotFoundException.class, () -> service.update(getUpdated(), CURRENT_USER_ID + 1));
    }

    @Test
    public void create() {
        Meal created = service.create(getNew(), CURRENT_USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, CURRENT_USER_ID), newMeal);
    }

    @Test
    public void duplicateCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(
                        new Meal(LocalDateTime.of(2021, Month.OCTOBER, 25, 13, 0), "Обед", 111),
                        CURRENT_USER_ID));
    }
}