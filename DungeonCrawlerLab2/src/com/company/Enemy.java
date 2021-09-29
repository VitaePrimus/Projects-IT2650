package com.company;

public class Enemy extends Character {
    public Enemy(int maxHealth, int defence, int attack) {
        super(maxHealth, defence, attack);
    }

    public void attack(Hero hero){
        hero.setMaxHealth(hero.getMaxHealth() - this.getAttack());
    }

}
