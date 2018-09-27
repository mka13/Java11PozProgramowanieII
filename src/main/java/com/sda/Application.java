package com.sda;

import com.sda.toDo.model.ToDo;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        List<String> texts = Arrays.asList("Ala", "ma", null, "kota", null, "abc");
        for (String text:texts
             ) {
            Optional<String> optional=Optional.ofNullable(text);
            String s = optional.filter(x -> x.length() > 2).orElse("STOP");
            System.out.println(s);


            // uzywajac zmiennej optional wyswietl element tylko wtedy gdy ma wiecej niz 2 litery,
            // w przeciwnym wypadku wypisac STOP-->filter(warunek czy ma wiecej niz 2 lietery)
        }



    }}
