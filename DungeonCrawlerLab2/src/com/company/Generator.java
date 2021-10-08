package com.company;

import java.util.ArrayList;

public class Generator {
    private ArrayList<Tile> wall;
    private ArrayList<Tile> potion;
    private ArrayList<Enemy> enemy;

    public Generator(){
        wall = new ArrayList<>();
        enemy = new ArrayList<>();
    }

    // Walls -- Start ------------------------------------------------------------------------
    public Tile getWallTile(int index) {
        return wall.get(index);
    }

    public ArrayList<Tile> getWall() {
        return wall;
    }

    public void generateWalls(){
        int[] xCor = {4,5,9,13,
                    1,2,4,5,9,13,
                    1,4,5,6,7,9,13,
                    4,9,10,12,13,14,
                    0,1,2,4,7,9,6,7,9,12,13,14,
                    1,4,4,6,8,9,10,12,13,14,15,
                    1,4,
                    1,3,4,5,6,8,10,11,12,14,15,
                    1,3,6,8,15,
                    0,1,6,8,10,11,12,13,15,
                    0,1,3,4,5,6,8,10,13,15,
                    1,8,10,11,13,
                    1,3,4,5,6,7,8,11,15,
                    7,8,9,13,14,15};
        int[] yCor = {0,0,0,0,
                    1,1,1,1,1,1,
                    2,2,2,2,2,2,2,
                    3,3,3,3,3,3,
                    4,4,4,4,4,4,
                    5,5,5,5,5,5,
                    6,6,
                    7,7,7,7,7,7,7,7,7,
                    8,8,
                    9,9,9,9,9,9,9,9,9,9,9,
                    10,10,10,10,10,
                    11,11,11,11,11,11,11,11,11,
                    12,12,12,12,12,12,12,12,12,12,
                    13,13,13,13,13,
                    14,14,14,14,14,14,14,14,14,
                    15,15,15,15,15,15};

        int count = 0;
        for(int y = 0; y < 16; y++){
            for(int x = 0; x < 16; x++){
                if(x == xCor[count] && y == yCor[count]){
                    wall.add(new Tile(x, y));
                    count++;
                }
            }
        }
    }
    // Walls -- Finish ------------------------------------------------------------------------

    // Enemies -- Start ------------------------------------------------------------------------
    public Tile getEnemyTile(int index){
        return enemy.get(index).getEnemyTile();
    }

    public Tile getEnemyStartingTile(int index){ return enemy.get(index).getStartingTile(); }

    public Tile getEnemyNewTile(int index){ return enemy.get(index).getNewTile(); }

    public void setEnemyNewTile(int index, int x, int y){ enemy.get(index).setNewTile(x,y); }

    public void setEnemyTile(int index, int x, int y){
        enemy.get(index).setEnemyTile(x,y);
    }

    public ArrayList<Enemy> getEnemy(){
        return enemy;
    }

    public void generateEnemies(){
        int[] xCor = {3,11,15,
                    1,
                    4,
                    10,
                    5,
                    2,11,
                    4,
                    5,12,
                    1,5, -1};
        int[] yCor = {2,2,2,
                    3,
                    5,
                    6,
                    7,
                    10,10,
                    11,
                    13,13,
                    15,15, -1};

        int count = 0;
        for(int y = 0; y < 16; y++){
            for(int x = 0; x < 16; x++){
                if(x == xCor[count] && y == yCor[count]){
                    enemy.add(new Enemy(100, 5, 20, new Tile(x,y)));
                    count++;
                }
            }
        }

    }

    public void removeEnemy(Enemy enemy){
        this.enemy.remove(enemy);
    }

    public void removeEnemy(int index){
        this.enemy.remove(index);
    }
    // Enemies -- Finish ------------------------------------------------------------------------

}
