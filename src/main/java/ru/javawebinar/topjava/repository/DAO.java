package ru.javawebinar.topjava.repository;

import java.util.List;

public interface DAO<E> {

    void add(E e);

    List<E> getAll();

    E get(long id);

    void update(E e);

    void delete(long id);
}
