package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Hero p1 = new Hero(100, 5, 50);
        Enemy e1 = new Enemy(100, 1, 1);

        String choice;

        while(true){
            System.out.println("Where to go?");
            System.out.print("(N)orth, (S)outh, (W)est, (E)ast: ");
            choice = scanner.nextLine();

            if ("e".equalsIgnoreCase(choice)) {
                p1.map.moveRight();
            }
            else if ("w".equalsIgnoreCase(choice)) {
                p1.map.moveLeft();
            }
            else if ("n".equalsIgnoreCase(choice)) {
                p1.map.moveUp();
            }
            else if ("s".equalsIgnoreCase(choice)) {
                p1.map.moveDown();
            }

            System.out.println(p1.map.getEvent(p1));          // Gets an event and prints a message identifying it

            while(p1.map.enemyDetected() && e1.getMaxHealth() > 0){
                System.out.println("Chose your option: attack - A, Drink potion - P, escape - R");
                choice = scanner.nextLine();
                if(choice.equalsIgnoreCase("a")){
                    p1.attack(e1);
                    e1.attack(p1);
                    System.out.println(p1.getMaxHealth());
                    System.out.println(e1.getMaxHealth());
                }
                if(choice.equalsIgnoreCase("h")){
                    p1.drinkPotion();
                }
//                if(choice.equalsIgnoreCase("r")){
//                    e1.attack(p1);
//                    p1.map.
//                }
            }

            System.out.println(p1.map.getCharLoc());

        }

    }
}
