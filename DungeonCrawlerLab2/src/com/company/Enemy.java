package com.company;

import java.util.Random;

public class Enemy extends Character {
    private Tile startingTile;
    private Tile enemyTile;
    private Tile newTile;
    private int currentHealth;


    public Enemy(int maxHealth, int defence, int attack, Tile enemyTile) {
        super(maxHealth, defence, attack);
        this.enemyTile = enemyTile;
        currentHealth = maxHealth;
        startingTile = enemyTile;
    }


    void attack(Character hero){
        Random rng = new Random();
        int random = rng.nextInt(5);

        int multiplier = 10;

        switch (random) {
            case 0 -> multiplier = 8;
            case 1 -> multiplier = 9;
            case 2 -> multiplier = 10;
            case 3 -> multiplier = 11;
            case 4 -> multiplier = 12;
        }
        hero.setCurrentHealth(hero.getCurrentHealth() - ((getAttack() * multiplier) / hero.getDefence()));
    }


    public String getEnemyLoc(){
        return enemyTile.getLocation();
    }

    public Tile getEnemyTile(){ return enemyTile; }

    public Tile getStartingTile(){
        return startingTile;
    }

    public Tile getNewTile(){
        return newTile;
    }

    public void setEnemyTile(int x, int y){ this.enemyTile = new Tile(x,y); }

    public void setNewTile(int x, int y){ this.newTile = new Tile(x,y); }

    public void setCurrentHealth(int currentHealth) { this.currentHealth = currentHealth; }

    public int getCurrentHealth() { return currentHealth; }
}
