package com.company;

public class Enemy extends Character {
    private Tile enemyTile;


    public Enemy(int maxHealth, int defence, int attack) {
        super(maxHealth, defence, attack);
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

}
