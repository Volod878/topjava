package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "jpa")
public class JpaUserServiceTest extends UserServiceTest {
    @Rule
    public final Stopwatch stopwatch = super.stopwatch;

    @AfterClass
    public static void printResult() {
        UserServiceTest.printResult();
    }

    @Before
    public void setup() {
        super.setup();
    }

    @Test
    public void create() {
        super.create();
    }

    @Test
    public void duplicateMailCreate() {
        super.duplicateMailCreate();
    }

    @Test
    public void delete() {
        super.delete();
    }

    @Test
    public void deletedNotFound() {
        super.deletedNotFound();
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
    public void getByEmail() {
        super.getByEmail();
    }

    @Test
    public void update() {
        super.update();
    }

    @Test
    public void getAll() {
        super.getAll();
    }
}