package com.sda.toDo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class ToDoUser {
    public static ToDoUser unassigned() {
        return new ToDoUser(null,"Nie przypisano");
    }

    private String password;
private String name;


}
