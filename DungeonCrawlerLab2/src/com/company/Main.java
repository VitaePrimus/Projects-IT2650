/*Created by Vitaliy Vuychych*/
package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Map map = new Map();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Hero p1 = new Hero(100, 5, 10);

        Tile currentPosition = p1.getCharTile();
        Tile newPosition = p1.getCharTile();

        String choice;
        int turn = 0;

        Random rng = new Random();

        // Couple strings for the interface:
        String startFight = "***********%n" +
                "You are facing a wandering goblin.%n";
        String enemyFight = "***********%n" +
                "An enemy has found you.%n";
        String fightingChoices = "Attack - A Drink potion - P Escape - E: ";

        System.out.println("________________________________________________");
        System.out.println("You are lost in a dungeon. Try to find an exit.");
        System.out.println("________________________________________________");

        // Main game loop ------------------------------------------------------------------------------
        while(true){
            currentPosition = p1.getCharTile();     // Remembers position before moving

            if(map.enemyDetected(p1)){
                Enemy enemy = map.getFightOpponent(map.enemyIndex(p1));
                System.out.printf(enemyFight);
                System.out.printf(fightingChoices);

                while(enemy.getCurrentHealth() > 0) {
                    choice = scanner.nextLine();
                    if (choice.equalsIgnoreCase("a")) {
                        enemy.attack(p1);    // Enemy attacking
                        p1.attack(enemy);    // Hero attacking
                    }
                    if(choice.equalsIgnoreCase("e")){
                        System.out.println("You can't escape, you were ambushed.");
                    }
                    if(choice.equalsIgnoreCase("p")){
                        System.out.println(p1.drinkPotion());
                    }

                    if(p1.getCurrentHealth() <= 0){
                        break;
                    }
                    if(enemy.getCurrentHealth() <= 0){                                  // Exits loop after enemy is defeated
                        System.out.println("Your health: " + p1.getCurrentHealth());
                        map.removeEnemy(map.enemyIndex(p1));
                        break;
                    }

                    System.out.println("Your health: " + p1.getCurrentHealth());
                    System.out.println("Enemy health: " + enemy.getCurrentHealth());


                    System.out.printf(fightingChoices);
                }
                if(p1.getCurrentHealth() <= 0){
                    System.out.println("You lost");
                    break;
                }

                int random = rng.nextInt(5);
                if(random == 0){
                    p1.setPotions(p1.getPotions() + 1);
                    System.out.println("You received a potion.");
                }

                System.out.printf("You won!%n");
            }
            if(p1.getCurrentHealth() <= 0){
                System.out.println("You lost");
                break;
            }

            System.out.println();
            System.out.println("Where to go?   |   type 'rest' to view your health, heal and skip turn.");
            System.out.print("North(W), South(S), West(A), East(D): ");
            choice = scanner.nextLine();

            // Resting -- Start ---------------------------------------------------------------------------------------------------
            if(choice.equalsIgnoreCase("rest")){
                if(p1.getCurrentHealth() >= p1.getMaxHealth()){
                    System.out.println("You have maximum health: " + p1.getCurrentHealth() + ".");
                }
                else if(p1.getCurrentHealth() >= p1.getMaxHealth() - 5){
                    p1.setCurrentHealth(p1.getMaxHealth());
                    System.out.println("You healed up to the maximum health: " + p1.getCurrentHealth() + ".");
                }
                else{
                    p1.setCurrentHealth(p1.getCurrentHealth() + 5);
                    System.out.println("You healed up by 5. Your health is: " + p1.getCurrentHealth() + ".");

                }
                System.out.println("You have " + p1.getPotions() + " potions.");
                System.out.println("It is turn " + turn + ".");
            }
            // Resting -- Finish ---------------------------------------------------------------------------------------------------

            p1.move(choice);                        // Move character
            newPosition = p1.getCharTile();         // Position after moving

            map.drinkPotion(p1);    // Increases attack or defence if player is on an appropriate tile

            // Winning the game! *****************************************************************************
            if(map.win(p1.getCharTile())){
                System.out.println("--- ♪♪♪ Congratulations, You Won the Game!!! ♪♪♪ ---");
                break;
            }
            // ************************************************************************************************

            if(map.enemyDetected(p1)){
                Enemy enemy = map.getFightOpponent(map.enemyIndex(p1));
                System.out.printf(startFight);
                System.out.printf(fightingChoices);

                while(enemy.getCurrentHealth() > 0) {
                    choice = scanner.nextLine();
                    if (choice.equalsIgnoreCase("a")) {

                        p1.attack(enemy);    // Hero attacking
                        enemy.attack(p1);    // Enemy attacking
                    }
                    if(choice.equalsIgnoreCase("e")){
                        p1.escape(enemy, currentPosition);
                        break;  // Exit loop if escape
                    }
                    if(choice.equalsIgnoreCase("p")){
                        System.out.println(p1.drinkPotion());
                    }

                    if(p1.getCurrentHealth() <= 0){
                        break;
                    }
                    if(enemy.getCurrentHealth() <= 0){                                  // Exits loop after enemy is defeated
                        System.out.println("Your health: " + p1.getCurrentHealth());
                        map.removeEnemy(map.enemyIndex(p1));
                        break;
                    }

                    System.out.println("Your health: " + p1.getCurrentHealth());
                    System.out.println("Enemy health: " + enemy.getCurrentHealth());

                    System.out.printf(fightingChoices);
                }
                if(p1.getCurrentHealth() <= 0){
                    System.out.println("You lost");
                    break;
                }

                int random = rng.nextInt(5);
                if(random == 0){
                    p1.setPotions(p1.getPotions() + 1);
                    System.out.println("You received a potion.");
                }
                System.out.printf("You won!%n");
            }
            if(p1.getCurrentHealth() <= 0){
                System.out.println("You lost");
                break;
            }

            map.moveEnemy(p1);

            // Increase the health of all enemies by 20 every 20 turns
            if(turn >= 20 && turn % 20 == 0){
                map.lvlUpEnemies();
                System.out.println("Enemies have become stronger.");
            }

            eventManager(p1, currentPosition, newPosition);     // Check for event

            System.out.printf("Your location is: %s.%n", p1.getCharTile());

            System.out.println("Enemies left: " + map.generator.getEnemy().size());

            turn++;
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
