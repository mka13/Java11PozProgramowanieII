package com.sda.toDo.repository;

import com.sda.toDo.model.ToDoUser;

public interface ToDoUserRepository {
    boolean save(ToDoUser user);
    ToDoUser findByName (String name);
    boolean exists (String name);
}
