package com.sda.toDo.model.exception;

public class ToDoException extends RuntimeException {

    //uber wyjątek

    public ToDoException(String message) {

        super(message);
    }
}
