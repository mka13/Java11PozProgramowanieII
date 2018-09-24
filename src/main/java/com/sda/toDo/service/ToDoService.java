package com.sda.toDo.service;

import com.sda.toDo.model.ToDo;
import com.sda.toDo.model.ToDoUser;
import com.sda.toDo.model.exception.InvalidPasswordException;
import com.sda.toDo.model.exception.ToDoUserAlreadyExistsException;
import com.sda.toDo.repository.ToDoRepository;
import com.sda.toDo.repository.ToDoUserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ToDoService {
    private ToDoRepository toDoRepository;
    private ToDoUserRepository toDoUserRepository;
    public void save(ToDo toDo){
        toDoRepository.save(toDo);
    }
public ToDoUser login (String name, String password){
        if(!toDoUserRepository.exists(name)){
            throw new ToDoUserAlreadyExistsException("User with name" + name + " already exists");
        }
    ToDoUser user = toDoUserRepository.findByName(name);
            if(!user.getPassword().equals(password)){
            throw  new InvalidPasswordException("Invalid password");
    }
    return user;
    }

public ToDoUser register (String name, String password){
    if(toDoUserRepository.exists(name)){
        return null;
    }
    ToDoUser user = new ToDoUser(password, name);
    toDoUserRepository.save(user);
    return user;
}


}
