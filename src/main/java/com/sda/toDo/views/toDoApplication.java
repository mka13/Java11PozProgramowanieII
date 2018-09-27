package com.sda.toDo.views;

import com.sda.toDo.ToDoConsoleView;
import com.sda.toDo.model.ToDo;
import com.sda.toDo.model.ToDoUser;
import com.sda.toDo.model.exception.InvalidPasswordException;
import com.sda.toDo.model.exception.ToDoUserDoesNotExists;
import com.sda.toDo.repository.InMemomryToDoUserRepository;
import com.sda.toDo.repository.ToDoMemoryRepository;
import com.sda.toDo.repository.ToDoRepository;
import com.sda.toDo.repository.ToDoUserRepository;
import com.sda.toDo.service.ToDoService;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
public class toDoApplication {
    private ToDoService toDoService;
    private ToDoConsoleView toDoConsoleView;
    private ToDoUser currentUser;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoRepository toDoRepository = new ToDoMemoryRepository();
        ToDoUserRepository toDoUserRepository = new InMemomryToDoUserRepository();
        ToDoService toDoService = new ToDoService(toDoRepository, toDoUserRepository);
        ToDoConsoleView toDoConsoleView = new ToDoConsoleView(scanner);
        toDoApplication TodoApplication = new toDoApplication(toDoService, toDoConsoleView, null);
        TodoApplication.start();



    }

    public void start() {
        Boolean flag = true;
        do {
            Integer menutoption = toDoConsoleView.menu();
            switch (menutoption) {
                case 1:
                    login();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    addNewToDo();
                    break;
                case 4:
                   showToDoList();
                    break;
                case 0:

                default:
                    toDoConsoleView.exit();
                    flag = false;
                    break;


            }
        } while (flag);
    }

    private void showToDoList() {
        Integer option = toDoConsoleView.showToDolist(toDoService.findAllToDos());
        String possibleId=toDoConsoleView.getPossibleID();
        System.out.println("Wybrano opjce: "+ option);

        switch(option) {
            case 1:
                showtToDo(possibleId);
                break;
            case 2:
                removeToDo(possibleId);
                break;
            case 3:
                assign(possibleId,currentUser);
                break;

        }


    }

    private void assign(String possibleId, ToDoUser currentUser) {
        Integer toDoId = extractToDoiD(possibleId);
        Optional<ToDo> todo = toDoService.findToDoById(toDoId);
        if(todo.isPresent()){
            ToDo toDoToChangeAssaignemnt = todo.get();
            toDoToChangeAssaignemnt.setOwner(currentUser);
                    }
    ToDoConsoleView.displayAssignment(todo, currentUser);
    }

    private void removeToDo(String possibleId) {
        Integer todoID = extractToDoiD(possibleId);
   Optional<ToDo> removeTodo=toDoService.removeToDO(todoID);
        toDoConsoleView.displayToDoToRemove(removeTodo);
    }

    private Integer extractToDoiD(String possibleId) {
        Integer todoID; //---> definicja przed warunkiem zeby miec go w else i
        if(possibleId.length()==0){
            todoID= toDoConsoleView.getToDoID()-1;
        }else{
            todoID=Integer.valueOf(possibleId)-1;
        }
        return todoID;
    }

    private void showtToDo(String possibleId) {

        Integer toDoID=extractToDoiD(possibleId);



       Optional <ToDo> todo=toDoService.findToDoById(toDoID);
        toDoConsoleView.showToWithDetails(todo);




    }

    private void registerUser() {
        String name = toDoConsoleView.registerName();
        String password = toDoConsoleView.registerPaswword();
        ToDoUser user = toDoService.register(name, password);
        if (user == null) {
            toDoConsoleView.displayError("Nie można zarejestrować użytkownika. \n" + "uzytkownik o podanej nazwie juz istnieje");
        }
        toDoConsoleView.displaySuccess("Udalo sie zarejestrowac użytkownika" + name);
    }






    private void login() {
        this.currentUser = null;
        String name = toDoConsoleView.logInName();

        String password = toDoConsoleView.loginPassword();
        try {
            this.currentUser = toDoService.login(name, password);
        } catch (ToDoUserDoesNotExists | InvalidPasswordException e) {  //obsluga dwoch wyjatkow na raz
            toDoConsoleView.displayError(e.getMessage());
        }
        if (this.currentUser != null) {
            toDoConsoleView.displaySuccess("Uzytkownik o nicku \"" + name + "\" został zalogowany");
        }

    }


    private void addNewToDo() {
        if (currentUser == null) {
            login();

        }
        if(currentUser!=null){


        String todoName = toDoConsoleView.creatNewToDoName();
        String todoDescription = toDoConsoleView.creatNewToDoDescritpion();
        ToDo todo = new ToDo(todoName, this.currentUser);
        todo.setDescription(todoDescription);

        toDoService.save(todo);
        }
    }

}
