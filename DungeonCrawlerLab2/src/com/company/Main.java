package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Hero p1 = new Hero(100, 5, 5);
        Enemy e1 = new Enemy(100, 1, 1);

        System.out.println(e1.getMaxHealth());
        p1.attack(e1);
        System.out.println(e1.getMaxHealth());
        p1.map.moveRight();
    }
}
