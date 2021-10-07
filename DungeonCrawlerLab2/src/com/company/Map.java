package com.company;

import java.util.Random;

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
    public boolean IS_EDGE(Hero hero, Tile currentTile){
        boolean IS_EDGE =   hero.getCharTile().getX() >= 16 ||      // Map size, currently 16x16
                            hero.getCharTile().getY() >= 16 ||      // Creates 256 tile map
                            hero.getCharTile().getX() < 0 ||
                            hero.getCharTile().getY() < 0;
        if(IS_EDGE){
            hero.setCharTile(currentTile);
        }
        return IS_EDGE;
    }

    public boolean IS_WALL(Hero hero, Tile currentTile){
        boolean IS_WALL = true;
        for(int i = 0; i < generator.getWall().size(); i++){
            if(hero.getCharTile().getLocation().equals(generator.getWallTile(i).getLocation())){    // Get character tile and get wall tile and compare them.
                IS_WALL = true;
                hero.setCharTile(currentTile);
                break;
            }
            else{
                IS_WALL = false;
            }
        }
        return IS_WALL;
    }


    // Enemy encounter
    public boolean enemyDetected(Hero hero, Tile currentTile){
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
//    public String getEvent(Hero hero, Tile currentTile, Tile newTile){
//        String event = "%n";
//
//        if(IS_EDGE(hero)){
//            event = "%nYou are at the edge of the dungeon.%n";
//            hero.setCharTile(currentTile);
//        }
//
//        if(IS_WALL(hero)){
//            event = "%nYou are facing a wall.%n";
//            hero.setCharTile(currentTile);
//        }
//
//        if(enemyDetected(hero)){
//            event = "%nYou have encountered an enemy.%n";
//            for(int i = 0; i < generator.getEnemy().size(); i++) {
//                if (hero.getCharTile().getLocation().equals(generator.getEnemyTile(i).getLocation())) {
//                    hero.startFight(generator.getEnemy().get(i));
//                }
//            }
//        }
//
//        if(win(newTile)){
//            event = "%nYou won!%n";
//        }
//        return event;
//    }

    // This was ... something
    public void moveEnemy(Hero hero){
        for(int i = 0; i < generator.getEnemy().size(); i++) {
            Random rng = new Random();

            int constX = generator.getEnemyStartingTile(i).getX();
            int constY = generator.getEnemyStartingTile(i).getY();

            boolean flag1 = true;   // Checking if not on the wall
            boolean flag2 = true;   // Checking if not on the other enemies position
            boolean flag3 = true;   // Checking for map boundaries
            boolean flag4 = true;   // Checking so that the distance is not mora than 2 from original spot


            while(flag1 || flag2 || flag3 || flag4) {

                int random = rng.nextInt(4);
                int newX = generator.getEnemy().get(i).getNewTile().getX();
                int newY = generator.getEnemy().get(i).getNewTile().getY();

                switch (random) {
                    case 0 -> newX = newX + 1;
                    case 1 -> newX = newX - 1;
                    case 2 -> newY = newY + 1;
                    case 3 -> newY = newY - 1;
                }

                generator.getEnemy().get(i).setNewTile(newX, newY);
                String newEnemyTile = generator.getEnemyNewTile(i).getLocation();

                if(newEnemyTile.equals(hero.getCharTile().getLocation())){
                    //hero.startFight(generator.getEnemy().get(i));
                }

                for(int x = 0; x < generator.getWall().size(); x++) {
                    if (newEnemyTile.equals(generator.getWall().get(x).getLocation())) {
                        flag1 = true;
                        break;
                    }
                    else{ flag1 = false; }
                }
                for(int x = 0; x < generator.getEnemy().size(); x++) {
                    if (newEnemyTile.equals(generator.getEnemyTile(x).getLocation())) {
                        flag2 = true;
                        break;
                    }
                    else{ flag2 = false; }
                }

                if(newX > 0 || newX <= 16 && newY > 0 || newY <= 16){ flag3 = false; }
                if(newX < constX + 2 && newY < constY + 2){ flag4 = false; }

                if(!flag1 && !flag2 && !flag3 && !flag4){ break; }    // Exit loop when enemy moved

                generator.getEnemy().get(i).setNewTile
                        (generator.getEnemy().get(i).getEnemyTile().getX(),
                         generator.getEnemy().get(i).getEnemyTile().getY());
            }

            // Set the enemy tile to the one the enemy moved
            generator.getEnemy().get(i).setEnemyTile
                    (generator.getEnemy().get(i).getNewTile().getX(),
                     generator.getEnemy().get(i).getNewTile().getY());
        }
    }


}
