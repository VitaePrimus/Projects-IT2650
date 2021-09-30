package com.company;

import java.util.ArrayList;

public class Map {
    ArrayList<Tile> enemyTile;
    ArrayList<Tile> potionTile;
    Generator generator;

    public Map(){
        generator = new Generator();
        generator.generateWalls();
    }

    // Return enemie's location
//    public String getEnemyLoc(int index) { return enemyTile[index].getLocation(); }
//
//    public String getPotionLoc(int index) { return potionTile[index].getLocation(); }
//
//    public String getWallLoc(int index) { return wall.wall.get(index).getLocation(); }


    // Check for map boundaries
    public boolean IS_EDGE(Hero hero){
        boolean IS_EDGE =   hero.getCharTile().getX() >= 16 ||
                            hero.getCharTile().getY() >= 16 ||
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
        for(int i = 0; i < enemyTile.size(); i++) {
            if (hero.getCharTile().getLocation().equals(generator.getEnemyTile(i).getLocation())) {      // Check if player is on the same tile as enemy
                enemyDetected = true;
            }
        }
        return enemyDetected;
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
            event = "You are at the edge of the dungeon.%n%n";
            hero.setCharTile(currentTile);
        }

        if(IS_WALL(hero)){
            event = "You are facing a wall.%n%n";
            hero.setCharTile(currentTile);
        }

//        if(enemyDetected(hero)){
//
//        }
        return event;
    }

}
