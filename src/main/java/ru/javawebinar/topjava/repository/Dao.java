package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface Dao<E> {

    E add(E e);

    Collection<Meal> getAll();

    E get(long id);

    E update(E e);

    void delete(long id);
}
