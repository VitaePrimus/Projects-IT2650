package com.company;

public class Enemy extends Character {
    private Tile startingTile;
    private Tile enemyTile;
    private int currentHealth;


    public Enemy(int maxHealth, int defence, int attack, Tile enemyTile) {
        super(maxHealth, defence, attack);
        this.enemyTile = enemyTile;
        currentHealth = maxHealth;
        startingTile = enemyTile;
    }

    public void attack(Hero hero){
        hero.setCurrentHealth(hero.getCurrentHealth() - this.getAttack());
    }

    public String getEnemyLoc(){
        return enemyTile.getLocation();
    }

    public Tile getEnemyTile(){
        return enemyTile;
    }

    public Tile getStartingTile(){
        return startingTile;
    }


    public void setEnemyTile(int x, int y){ this.enemyTile = new Tile(x,y); }

    public void setCurrentHealth(int currentHealth) { this.currentHealth = currentHealth; }

    public int getCurrentHealth() { return currentHealth; }
}
