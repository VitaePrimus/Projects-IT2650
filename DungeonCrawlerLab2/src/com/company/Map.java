package com.company;

import java.util.ArrayList;

public class Map {
    Generator generator;
    Tile winningTile;


    public Map(){
        generator = new Generator();
        generator.generateWalls();
        generator.generateEnemies();

        winningTile = new Tile(11,12);
    }


    // Check for map boundaries
    public boolean IS_EDGE(Hero hero){
        boolean IS_EDGE =   hero.getCharTile().getX() >= 16 ||      // Map size, currently 16x16
                            hero.getCharTile().getY() >= 16 ||      // Creates 256 tile map
                            hero.getCharTile().getX() < 0 ||
                            hero.getCharTile().getY() < 0;
        return IS_EDGE;
    }

    public boolean IS_WALL(Hero hero){
        boolean IS_WALL = true;
        for(int i = 0; i < generator.getWall().size(); i++){
            if(hero.getCharTile().getLocation().equals(generator.getWallTile(i).getLocation())){    // Get character tile and get wall tile and compare them.
                IS_WALL = true;
                break;
            }
            else{
                IS_WALL = false;
            }
        }
        return IS_WALL;
    }


    // Enemy encounter
    public boolean enemyDetected(Hero hero){
        boolean enemyDetected = false;
        for(int i = 0; i < generator.getEnemy().size(); i++) {
            if (hero.getCharTile().getLocation().equals(generator.getEnemyTile(i).getLocation())) {      // Check if player is on the same tile as enemy
                enemyDetected = true;
            }
        }
        return enemyDetected;
    }

    public boolean win(Tile tile){
        return tile.getLocation().equals(winningTile.getLocation());
    }
//
//    public boolean potionFound(){
//        boolean potionFound = false;
//        for(int i = 0; i < potionTile.size(); i++) {
//            if (getCharLoc().equals(getPotionLoc(i))) {      // Check if player is on the same tile as potion, index goes to getPotionLoc()
//                potionFound = true;
//            }
//        }
//        return potionFound;
//
//    }
//
//
    public String getEvent(Hero hero, Tile currentTile, Tile newTile){
        String event = "%n";

        if(IS_EDGE(hero)){
            event = "%nYou are at the edge of the dungeon.%n";
            hero.setCharTile(currentTile);
        }

        if(IS_WALL(hero)){
            event = "%nYou are facing a wall.%n";
            hero.setCharTile(currentTile);
        }

        if(enemyDetected(hero)){
            event = "%nYou have encountered an enemy.%n";
            for(int i = 0; i < generator.getEnemy().size(); i++) {
                if (hero.getCharTile().getLocation().equals(generator.getEnemyTile(i).getLocation())) {
                    hero.startFight(generator.getEnemy().get(i));
                }
            }
        }

        if(win(newTile)){
            event = "%nYou won!%n";
        }
        return event;
    }

}
