package com.sda.toDo.repository;

import com.sda.toDo.model.ToDo;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public class ToDoMemoryRepository implements ToDoRepository {

    private List<ToDo>toDos;

    public ToDoMemoryRepository() {
        this.toDos = new ArrayList<>();
    }

    @Override
    public void save(ToDo toDo) {
    toDos.add(toDo);
    }

    @Override
    public Optional<ToDo> findById(String id) {

        return toDos.stream()
                .filter(x->x.getId().equals(id))
                .findFirst();

        // przy Optional nie piszę się już get


    }


    @Override
    public List<ToDo> findAll() {
        // alternatywnie Collections.copy (new ArrayList<>(),todos)
        // kopiowanie listy
        // dzieki powyzszemu sposobowi mozna wkopiowywac wiele list
        //np
        // List<Ludzie> people = new ArrayList<>();
        // List <male> males;
        // List <female> felmales;
        // Collections.copy (people,female)
        //Collections.copy (people, male)
        return new ArrayList<>(toDos);


    }

    @Override
    public Optional<ToDo> findById(Integer id) {
//       if(id>=0 &id <toDos.size() ){
//        return Optional.of(toDos.get(id));}
//        return  Optional.empty();

        // Operator 3 argumentowy


        return (id>=0 &id <toDos.size() ) ?
        Optional.of(toDos.get(id)) :
        Optional.empty();

     //jest to if else
        // można tez nie przy zwracaniu:
//        String message = id % 2==0 ?
//                "even":
//                "add";



    }

    @Override
    public void remove(int toDoIDToRemove) {

        toDos.remove(toDoIDToRemove);
    }

}
