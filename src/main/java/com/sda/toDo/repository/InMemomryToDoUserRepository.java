package com.sda.toDo.repository;

import com.sda.toDo.model.ToDoUser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor

public class InMemomryToDoUserRepository implements  ToDoUserRepository {

   private List<ToDoUser> users;

    public InMemomryToDoUserRepository() {
    this.users = new ArrayList<>();
    }

    @Override
    public boolean save(ToDoUser user) {
       if(exists((user.getName()))){
           return false;
       }

        return users.add(user);// zwraca true
    }

    @Override
    public ToDoUser findByName(String name) {
        return users.stream()
                .filter(e->e.getName().equals(name))
                .findFirst()
                .orElseGet(()->null); // analogia do Optional--> tutaj może nie być tej szukanej wartości i w takiej
        // sytuacji metoda musi dać null --> lub metoda orElse()
    }

    @Override
    public boolean exists(String name) {
        return users.stream()
                .anyMatch(x->x.getName().equals(name));
    }
}
