package com.company;

public class Enemy extends Character {
    private Tile enemyTile;
    private int currentHealth;


    public Enemy(int maxHealth, int defence, int attack, Tile enemyTile) {
        super(maxHealth, defence, attack);
        this.enemyTile = enemyTile;
        currentHealth = maxHealth;
    }

    public void attack(Hero hero){
        hero.setMaxHealth(hero.getMaxHealth() - this.getAttack());
    }

    public String getEnemyLoc(){
        return enemyTile.getLocation();
    }

    public Tile getEnemyTile(){
        return enemyTile;
    }

    public void setCurrentHealth(int currentHealth) { this.currentHealth = currentHealth; }

    public int getCurrentHealth() { return currentHealth; }
}
