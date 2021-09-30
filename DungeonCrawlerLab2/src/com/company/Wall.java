package com.company;

import java.util.ArrayList;

public class Wall {
    ArrayList<Tile> wall;

    public Wall(){
        wall = new ArrayList<>();
        this.wall.add(new Tile(2, 2));
    }
}
