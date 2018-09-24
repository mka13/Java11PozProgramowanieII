package com.sda.toDo.views;

import com.sda.toDo.ToDoConsoleView;
import com.sda.toDo.model.ToDo;
import com.sda.toDo.model.ToDoUser;
import com.sda.toDo.repository.InMemomryToDoUserRepository;
import com.sda.toDo.repository.ToDoMemoryRepository;
import com.sda.toDo.repository.ToDoRepository;
import com.sda.toDo.repository.ToDoUserRepository;
import com.sda.toDo.service.ToDoService;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Scanner;

@AllArgsConstructor
public class toDoApplication {
    private ToDoService toDoService;
    private ToDoConsoleView toDoConsoleView;
    private ToDoUser currentUser;



    public static void main(String[] args){
    Scanner scanner = new Scanner (System.in);
        ToDoRepository toDoRepository =new ToDoMemoryRepository();
    ToDoUserRepository toDoUserRepository = new InMemomryToDoUserRepository(Arrays.asList(new ToDoUser("jfsi","Szymon")));
    ToDoService toDoService = new ToDoService(toDoRepository, toDoUserRepository);
        ToDoConsoleView toDoConsoleView = new ToDoConsoleView(scanner);
        toDoApplication TodoApplication  = new toDoApplication (toDoService, toDoConsoleView,null);
    TodoApplication.start();


        System.out.println("Todo application");
    }
public void start(){

        do{
     Integer menutoption = toDoConsoleView.menu();
     switch (menutoption) {
         case 1:
             break;
         case 2:
        break;
         case 3:
             addNewToDo();
             break;
         case 4:
             break;
             default:
                 break;


     }
      }while(true);
}


private  void addNewToDo(){
        if(currentUser==null){
            String name = toDoConsoleView.logInName();
            String password = toDoConsoleView.loginPassword();
            this.currentUser = toDoService.login(name, password);

        }
        String todoName =toDoConsoleView.creatNewToDoName();
        String todoDescription =toDoConsoleView.creatNewToDoDescritpion();
         ToDo todo= new ToDo(todoName,this.currentUser);
        todo.setDescription(todoDescription);

        toDoService.save(todo);
    }

}