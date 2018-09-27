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

    public static void displayAssignment(Optional<ToDo> todo, ToDoUser currentUser) {
        System.out.println(todo.map(x -> "Przypisano " + currentUser.getName() + " do zadania \"" + x.getName() + "\"")
                .orElse("zadanie nie istnieje"));
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
        //scanner.nextLine()


        return option;
    }

    public Integer getToDoID() {
        String possibleID = scanner.nextLine();
        int iD;
        if (possibleID.length() == 0) {
            System.out.println("Podaj numer zadania");
            iD = scanner.nextInt();
            scanner.nextLine();
        } else {
            iD = Integer.valueOf(possibleID.substring(1));
        }


        return iD;

    }

    public void showToWithDetails(Optional<ToDo> todo) {
        String message = todo.map(x -> {
            ToDoUser creator = x.getCreator();
            Optional<ToDoUser> owner = Optional.ofNullable(x.getOwner());
            return x.getName() + " (" + x.getToDoStatus().toString() + ") (\n" +
                    x.getDescription() + "\n" +
                    " Tworca: " + creator.getName() + "\n" +
                    " Przyspisane: " + owner.orElse(ToDoUser.unassigned()).getName();
        }).orElse("Zadanie nie istnieje");
        System.out.println(message);
    }

    public Integer getToDoIDToRemove() {
        String possibleID = scanner.nextLine();
        Integer ID = possibleID.length() > 0 ?
                getIdFromMessage(possibleID) :
                askForIDToRemove();

        return ID;

    }

    private Integer getIdFromMessage(String message) {
        return Integer.valueOf(message.substring(1));
    }

    private Integer askForIDToRemove() {
        System.out.println("Podaj ID zadania");
        int toDoId = scanner.nextInt();
        scanner.nextLine();
        return toDoId;
    }

//    public String getPossibleID() {
//        String possibleID = scanner.nextLine();
////        if(possibleID.length()>0){
////            return possibleID.substring(1);
////        }else{
////            return  possibleID;
////        }
//        return possibleID.length()>0 ?
//                possibleID.substring(1):
//                possibleID;
//
//    }

    public Integer getTodoIdToRemove() {
        System.out.println("Podaj numer zadania");
        int id=scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public void displayToDoToRemove(Optional<ToDo> removeTodo) {
        System.out.println(removeTodo.map(x -> "Usunieto zadanie " + x.getName())
                .orElse("Zadanie nie istnieje"));
    }

    public String getPossibleID() {
        String possibleId=scanner.nextLine();
        return possibleId.length()>0 ?
                possibleId.substring(1):
                    possibleId;
    }
}
