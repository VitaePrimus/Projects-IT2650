package com.company;

public class Tile {
    private int x;
    private int y;


    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }


    public int getX() { return x; }

    public int getY() { return y; }

    public String getLocation(){
        return (x + ", " + y);
    }

    @Override
    public String toString(){
        return (x + ", " + y);
    }
}
