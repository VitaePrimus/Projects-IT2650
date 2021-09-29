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
            choice = scanner.nextLine();

            if ("r".equalsIgnoreCase(choice)) {
                p1.map.moveRight();
            }
            else if ("l".equalsIgnoreCase(choice)) {
                p1.map.moveLeft();
            }
            else if ("u".equalsIgnoreCase(choice)) {
                p1.map.moveUp();
            }
            else if ("d".equalsIgnoreCase(choice)) {
                p1.map.moveDown();
            }

            while(p1.map.enemyDetected() && e1.getMaxHealth() > 0){
                System.out.println("Chose your option: attack - a, heal - h");
                choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("a")){
                    p1.attack(e1);
                    e1.attack(p1);
                    System.out.println(p1.getMaxHealth());
                    System.out.println(e1.getMaxHealth());
                }
                if (choice.equals("h")){
                    p1.setMaxHealth(100);
                }
            }

            System.out.println(p1.map.getCharLoc());

        }

    }
}
