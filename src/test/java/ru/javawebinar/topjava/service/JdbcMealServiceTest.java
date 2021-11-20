package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "jdbc")
public class JdbcMealServiceTest extends MealServiceTest {
    @Rule
    public final Stopwatch stopwatch = super.stopwatch;

    @AfterClass
    public static void printResult() {
        MealServiceTest.printResult();
    }

    @Test
    public void delete() {
        super.delete();
    }

    @Test
    public void deleteNotFound() {
        super.deleteNotFound();
    }

    @Test
    public void deleteNotOwn() {
        super.deleteNotOwn();
    }

    @Test
    public void create() {
        super.create();
    }

    @Test
    public void duplicateDateTimeCreate() {
        super.duplicateDateTimeCreate();
    }


    @Test
    public void get() {
        super.get();
    }

    @Test
    public void getNotFound() {
        super.getNotFound();
    }

    @Test
    public void getNotOwn() {
        super.getNotOwn();
    }

    @Test
    public void update() {
        super.update();
    }

    @Test
    public void updateNotOwn() {
        super.updateNotOwn();
    }

    @Test
    public void getAll() {
        super.getAll();
    }

    @Test
    public void getBetweenInclusive() {
        super.getBetweenInclusive();
    }

    @Test
    public void getBetweenWithNullDates() {
        super.getBetweenWithNullDates();
    }
}