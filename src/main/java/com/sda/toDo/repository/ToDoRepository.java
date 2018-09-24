package com.sda.toDo.repository;

import com.sda.toDo.model.ToDo;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository {
void save (ToDo toDo);
Optional<ToDo> findById (String id);
List<ToDo> findAll();

}
