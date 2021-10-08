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


    // Enemy encounter -- Start ----------------------------------------------------------------------------------------------- //
    public boolean enemyDetected(Hero hero){
        boolean enemy = false;
        for(int i = 0; i < generator.getEnemy().size(); i++) {
            if (hero.getCharTile().getLocation().equals(generator.getEnemyTile(i).getLocation())) {      // Check if player is on the same tile as enemy
                enemy = true;
                break;
            }
        }
        return enemy;
    }

    public int enemyIndex(Hero hero){
        int enemy = -1;
        for(int i = 0; i < generator.getEnemy().size(); i++) {
            if (hero.getCharTile().getLocation().equals(generator.getEnemyTile(i).getLocation())) {
                enemy = i;
                break;
            }
        }
        return enemy;
    }

    public Enemy getFightOpponent(int index){
        return generator.getEnemy().get(index);
    }

    public void removeEnemy(int index){ generator.getEnemy().remove(index); }
    // Enemy encounter -- Finish ----------------------------------------------------------------------------------------------- //


    public boolean win(Tile tile){
        return tile.getLocation().equals(winningTile.getLocation());
    }


    // This was ... something
    public void moveEnemy(Hero hero){
        for(int i = 0; i < generator.getEnemy().size(); i++) {
            Random rng = new Random();
            int random = rng.nextInt(4);

            String newEnemyTile;

            int constX = generator.getEnemyStartingTile(i).getX();
            int constY = generator.getEnemyStartingTile(i).getY();

            boolean flag1 = false;   // Checking if not on the wall
            boolean flag2 = false;   // Checking if not on the other enemies position
            boolean flag3 = false;   // Checking for map boundaries
            boolean flag4 = false;   // Checking so that the distance is not mora than 2 from original spot


            while(!flag1 && !flag2 && !flag3 && !flag4) {

                int newX = generator.getEnemy().get(i).getEnemyTile().getX();
                int newY = generator.getEnemy().get(i).getEnemyTile().getY();

                switch (random) {
                    case 0 -> newX = newX + 1;
                    case 1 -> newX = newX - 1;
                    case 2 -> newY = newY + 1;
                    case 3 -> newY = newY - 1;
                }

                generator.setEnemyNewTile(i, newX, newY);
                newEnemyTile = generator.getEnemyNewTile(i).getLocation();

                if(newEnemyTile.equals(hero.getCharTile().getLocation())){
                    System.out.println("Enemy found you.");
                    break;
                }

                for(int x = 0; x < generator.getWall().size(); x++) {
                    flag1 = newEnemyTile.equals(generator.getWall().get(x).getLocation());
                    if(flag1) {break;}
                }
                for(int v = 0; v < generator.getEnemy().size(); v++) {
                    flag2 = newEnemyTile.equals(generator.getEnemyTile(v).getLocation());
                    if(flag2) {break;}
                }

                flag3 = newX < 0 || newX >= 16 && newY < 0 || newY >= 16;
                flag4 = newX > constX + 2 || newY > constY + 2;

                if(!flag1 && !flag2 && !flag3 && !flag4){
                    //System.out.println("exit loop");
                    break; }  // Exit loop when enemy moved

                if(random >= 3) {
                    random = 0;
                }
                else {
                    random = random + 1;
                }

                generator.setEnemyNewTile(
                        i,
                        generator.getEnemy().get(i).getEnemyTile().getX(),
                        generator.getEnemy().get(i).getEnemyTile().getY());

            }

            // Set the enemy tile to the one the enemy moved to
            generator.setEnemyTile(
                    i,
                    generator.getEnemy().get(i).getNewTile().getX(),
                    generator.getEnemy().get(i).getNewTile().getY());
        }
    }


}
