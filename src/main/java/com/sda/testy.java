package com.sda;

import java.util.Scanner;

public class testy {

    public static void main(String[] args) {

        Scanner scanner= new Scanner("1 2 l s g g jj");
        scanner.nextInt();
        scanner.nextInt();
        scanner.nextLine();
        scanner=new Scanner ("dsafas");
        if(scanner.hasNextInt()){
            System.out.println("sa jeszcze cyfry");


        }else if(scanner.hasNext()){
            System.out.println("sa jeszcze litery");

        }else{
            System.out.println("koniec");




        }





    }


}
