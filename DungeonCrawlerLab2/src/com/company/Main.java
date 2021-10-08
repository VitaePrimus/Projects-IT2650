package com.company;

import java.util.Scanner;

public class Main {
    static Map map = new Map();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Hero p1 = new Hero(100, 5, 50);

        Tile currentPosition = p1.getCharTile();
        Tile newPosition = p1.getCharTile();

        String choice;

        // Couple strings for the interface:
        String startFight = "***********%n" +
                "You are facing a wandering goblin.%n";
        String fightingChoices = "Attack - A Drink potion - P Escape - E";

        String enemyFight = "***********%n" +
                "An enemy has found you.";

        // Main game loop ------------------------------------------------------------------------------
        while(true){
            currentPosition = p1.getCharTile();     // Remembers position before moving

            // Encounter with enemy on their move
            if(map.enemyDetected(p1)){
                System.out.printf(enemyFight);
                System.out.printf(fightingChoices);

                choice = scanner.nextLine();
                while(!p1.startFight(map.getFightOpponent(map.enemyIndex(p1)), choice, map.enemyIndex(p1))){
                    choice = scanner.nextLine();
                }
                System.out.printf("You won!%n");
                map.removeEnemy(map.enemyIndex(p1));
            }

            System.out.println();
            System.out.println("Where to go?");
            System.out.print("North(W), South(S), West(A), East(D): ");
            choice = scanner.nextLine();
            p1.move(choice);                        // Move character
            newPosition = p1.getCharTile();         // Position after moving

            // Encounter with enemy on player's move
            if(map.enemyDetected(p1)){
                System.out.printf(startFight);
                System.out.printf(fightingChoices);

                choice = scanner.nextLine();
                while(!p1.startFight(map.getFightOpponent(map.enemyIndex(p1)), choice, map.enemyIndex(p1))){
                    choice = scanner.nextLine();
                }
                System.out.printf("You won!%n");
                map.removeEnemy(map.enemyIndex(p1));
            }

            map.moveEnemy(p1);

            eventManager(p1, currentPosition, newPosition);     // Check for event

            System.out.printf("Your location is: %s.%n", p1.getCharTile());
            currentPosition = newPosition;

            System.out.println("Enemies left: " + map.generator.getEnemy().size());
        } // End of main game loop ----------------------------------------------------------------------------

    }

    // Check for events -----------------------------------------------------------------------------
    static void eventManager(Hero hero, Tile currentTile, Tile newTile){
        System.out.println();
        if(map.IS_EDGE(hero, currentTile)){
            System.out.printf("%nYou are at the edge of the dungeon.%n");
        }
        if(map.IS_WALL(hero, currentTile)){
            System.out.printf("%nYou are facing a wall.%n");
        }
        if(map.enemyDetected(hero)){
            System.out.printf("%nYou have encountered an enemy.%n");
        }

    }
}
