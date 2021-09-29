package com.company;

public class Hero extends Character {

    public Hero(int maxHealth, int defence, int attack) {
        super(maxHealth, defence, attack);
    }

    public void attack(Enemy enemy){
        enemy.setMaxHealth(enemy.getMaxHealth() - this.getAttack());
    }


}
