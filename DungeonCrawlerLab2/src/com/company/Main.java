package com.company;

import java.util.Scanner;

public class Main {
    static Map map = new Map();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Hero p1 = new Hero(100, 5, 50);

        Tile currentPosition;
        Tile newPosition;

        String choice;

        while(true){
            System.out.println("Where to go?");
            System.out.print("North(W), South(S), West(A), East(D): ");
            choice = scanner.nextLine();

            currentPosition = p1.getCharTile();     // Remembers position before moving
            p1.move(choice);                        // Move character
            newPosition = p1.getCharTile();         // Position after moving

            for(int i = 0; i < map.generator.getEnemy().size(); i++){
                map.moveEnemy(p1);
            }

            eventManager(p1, currentPosition, newPosition);

            System.out.printf("Your location is: %s.%n", p1.getCharTile());
            currentPosition = newPosition;
        }

    }

    static void eventManager(Hero hero, Tile currentTile, Tile newTile){
        System.out.println();
        if(map.IS_EDGE(hero, currentTile)){
            System.out.printf("%nYou are at the edge of the dungeon.%n");
        };
        if(map.IS_WALL(hero, currentTile)){
            System.out.printf("%nYou are facing a wall.%n");
        };
        if(map.enemyDetected(hero, currentTile)){
            System.out.printf("%nYou have encountered an enemy.%n");
//            hero.startFight();
        };

    }
}
