package com.sda.toDo;

import com.sda.toDo.model.ToDo;
import com.sda.toDo.model.ToDoStatus;
import com.sda.toDo.model.ToDoUser;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ToDoConsoleView {
    private Scanner scanner;

    public ToDoConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer menu() {
        System.out.println("Todo application");
        System.out.println("1. Zaloguj");
        System.out.println("2. Zarejestruj");
        System.out.println("3. Dodaj zadanie");
        System.out.println("4. Wyświetl zadania");
        System.out.println("0. koniec");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public String logInName() {
        System.out.println("Podaj nick");
        return scanner.nextLine();
    }

    public String loginPassword() {
        System.out.println("Podaj haslo");
        return scanner.nextLine();
    }

    public String registerName() {
        return logInName();
    }

    public String registerPaswword() {
        return loginPassword();
    }

    public String creatNewToDoName() {
        System.out.println("Podaj nazwe zadania");
        return scanner.nextLine();
    }

    public String creatNewToDoDescritpion() {
        System.out.println("Podaj opis zadania");
        return scanner.nextLine();
    }

    public void displayError(String message) {
        System.out.println();
        System.out.println("Error");
        System.out.println(message);
        System.out.println("Error");

    }

    public void displaySuccess(String message) {
        System.out.println();
        System.out.println("Success");
        System.out.println(message);
        System.out.println("Success");

    }

    public void exit() {
        System.out.println("Zapraszamy ponownie");
    }

    public Integer showToDolist(List<ToDo> allToDos) {
        System.out.println("Lista zadan");
        System.out.println("-------------");
        for (int i = 0; i < allToDos.size(); i++) {
            ToDo todo = allToDos.get(i);
            Optional<ToDoUser> owner = Optional.ofNullable(todo.getOwner()); // tutaj chcemy żeby uniknąć sytuacji gyd nie ma jeszcze przypisanej osoby do zadania
            ToDoUser creator = todo.getCreator();
            ToDoStatus toDoStatus = todo.getToDoStatus();

            // todUser ma metode unassined ktora tworzy lipnego użytkownika gdy nie jest podany wlasciciel zadania. Bo optional musi zwrocic jakiegos uztkownika
            System.out.println(i + 1
                    + ". |\" " + todo.getName() + "\" | \" "
                    + creator.getName() + "\" |"
                    + owner.orElse(ToDoUser.unassigned()).getName()
                    + "\" |" + toDoStatus.toString().toUpperCase());
        }
        System.out.println("-------------------------");
        System.out.println("1. Wyświetl");
        System.out.println("2. Usuń");
        System.out.println("3. Przypisz");
        System.out.println("4. Zmień status");
        System.out.println("0. Wyjdź");
        Integer option = scanner.nextInt();
        scanner.nextLine();


        return option;
    }
}
