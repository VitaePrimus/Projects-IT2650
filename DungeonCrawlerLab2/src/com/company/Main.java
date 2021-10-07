package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Hero p1 = new Hero(100, 5, 50);
        Map map = new Map();

        Tile currentPosition;
        Tile newPosition;

        String choice;

        while(true){
            System.out.println("Where to go?");
            System.out.print("(N)orth, (S)outh, (W)est, (E)ast: ");
            choice = scanner.nextLine();

            currentPosition = p1.getCharTile();     // Remembers position before moving
            p1.move(choice);                        // Move character
            newPosition = p1.getCharTile();         // Position after moving

            for(int i = 0; i < map.generator.getEnemy().size(); i++){
                map.moveEnemy(p1);
            }

            System.out.printf(map.getEvent(p1, currentPosition, newPosition));          // Gets an event and prints a message identifying it


            System.out.printf("Your location is: %s.%n", p1.getCharTile());
            currentPosition = newPosition;
        }

    }
}
