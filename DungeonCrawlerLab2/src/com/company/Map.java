package com.company;

public class Map {
    int x = 0;
    int y = 0;
    Tile charTile = new Tile(x, y);
    Tile[] enemyTile = {new Tile(2, 3), new Tile(3, 3)} ;
    Tile[] potionTile = {new Tile(1,1)};


    public void moveUp(){
        y = y - 1;
        if(IS_EDGE()){
            y = y + 1;
            return;  // To exit if when the coordinates are out of bounds
        }
        charTile = new Tile(x, y);
    }


    public void moveDown(){
        y = y + 1;
        if(IS_EDGE()){
            y = y - 1;
            return;
        }
        charTile = new Tile(x, y);
    }


    public void moveRight(){
        x = x + 1;
        if(IS_EDGE()){
            x = x - 1;
            return;
        }
        charTile = new Tile(x, y);
    }


    public void moveLeft(){
        x = x - 1;
        if(IS_EDGE()){
            x = x + 1;
            return;
        }
        charTile = new Tile(x, y);
    }


    // Return player's location
    public String getCharLoc(){
        return charTile.getLocation();
    }


    // Return enemie's location
    public String getEnemyLoc(int index) { return enemyTile[index].getLocation(); }

    public String getPotionLoc(int index) { return potionTile[index].getLocation(); }


    // Check for map boundaries
    public boolean IS_EDGE(){
        boolean IS_EDGE = x >= 4 || y >= 4 || x < 0 || y < 0;
        return IS_EDGE;
    }


    // Enemy encounter
    public boolean enemyDetected(){
        boolean enemyDetected = false;
        for(int i = 0; i < enemyTile.length; i++) {
            if (getCharLoc().equals(getEnemyLoc(i))) {      // Check if player is on the same tile as enemy, index goes to getEnemyLoc()
                enemyDetected = true;
            }
        }
        return enemyDetected;
    }

    public boolean potionFound(){
        boolean potionFound = false;
        for(int i = 0; i < enemyTile.length; i++) {
            if (getCharLoc().equals(getPotionLoc(i))) {      // Check if player is on the same tile as potion, index goes to getPotionLoc()
                potionFound = true;
            }
        }
        return potionFound;

    }


    public String getEvent(Hero hero){
        String event = "";
        if(enemyDetected()){
            event = "You have encountered an enemy.";
        }
        if(IS_EDGE()){
            event = "You have hit a wall.";
        }
        if(potionFound()){
            hero.setPotions(1);
        }
        return event;
    }


//    public Map(){
//        int count = 0;
//
//        for(int x = 0; x < 16; x++){
//            for(int y = 0; y < 16; y++){
//                tile[count] = new Tile(x, y);
//                count++;
//            }
//        }
//    }
//
//    public void getLocation(){
//        for(int i = 0; i < tile.length; i++){
//            System.out.println(tile[i].getLocation());
//        }
//    }

}
