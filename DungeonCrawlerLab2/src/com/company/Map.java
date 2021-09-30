package com.company;

import java.util.ArrayList;

public class Map {
    ArrayList<Tile> enemyTile;
    ArrayList<Tile> potionTile;
    ArrayList<Wall> wall;

    public Map(){

    }

    // Return enemie's location
//    public String getEnemyLoc(int index) { return enemyTile[index].getLocation(); }
//
//    public String getPotionLoc(int index) { return potionTile[index].getLocation(); }
//
//    public String getWallLoc(int index) { return wall.wall.get(index).getLocation(); }


    // Check for map boundaries
    public boolean IS_EDGE(Hero hero){
        boolean IS_EDGE =   hero.getCharTile().getX() >= 4 ||
                            hero.getCharTile().getY() >= 4 ||
                            hero.getCharTile().getX() < 0 ||
                            hero.getCharTile().getY() < 0;
        return IS_EDGE;
    }


    // Enemy encounter
//    public boolean enemyDetected(Hero hero){
//        boolean enemyDetected = false;
//        for(int i = 0; i < enemyTile.size(); i++) {
//            if (hero.getCharTile().equals(getEnemyLoc(i))) {      // Check if player is on the same tile as enemy, index goes to getEnemyLoc()
//                enemyDetected = true;
//            }
//        }
//        return enemyDetected;
//    }
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
//        if(enemyDetected()){
//            event = "You have encountered an enemy.";
//        }
        if(IS_EDGE(hero)){
            event = "You have hit a wall (edge).%n%n";
            hero.setCharTile(currentTile);
        }
//        if(potionFound()){
//            hero.setPotions(1);
//            event = "You have found a potion.";
//        }
        return event;
    }

}
