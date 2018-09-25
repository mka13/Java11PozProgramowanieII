package com.sda.toDo;

import java.util.Scanner;

public class ToDoConsoleView {
    private Scanner scanner;
    public ToDoConsoleView(Scanner scanner){
        this.scanner=scanner;
    }
public Integer menu (){
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

public String logInName (){
    System.out.println("Podaj nick");
    return scanner.nextLine();
}

public String loginPassword(){
    System.out.println("Podaj haslo");
    return scanner.nextLine();
}

public String registerName(){
        return logInName();
}

public String registerPaswword (){
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

public void displayError(String message){
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
}
