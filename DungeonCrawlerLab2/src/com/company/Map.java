package com.company;

public class Map {
    int x = 0;
    int y = 0;
    Tile charTile = new Tile(x, y);

    public void moveUp(){
        if(IS_EDGE()){
            System.out.println("You have hit a wall");
            return;  // To exit if when the coordinates are out of bounds
        }
        y = y - 1;
        charTile = new Tile(x, y);
    }

    public void moveDown(){
        if(IS_EDGE()){
            System.out.println("You have hit a wall");
            return;
        }
        y = y + 1;
        charTile = new Tile(x, y);
    }

    public void moveRight(){
        if(IS_EDGE()){
            System.out.println("You have hit a wall");
            return;
        }
        x = x + 1;
        charTile = new Tile(x, y);
    }

    public void moveLeft(){
        if(IS_EDGE()){
            System.out.println("You have hit a wall");
            return;
        }
        x = x - 1;
        charTile = new Tile(x, y);
    }

    public String getCharLoc(){
        return charTile.getLocation();
    }

    public boolean IS_EDGE(){
        boolean IS_EDGE = x >= 4 || y >= 4;

        return IS_EDGE;
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
