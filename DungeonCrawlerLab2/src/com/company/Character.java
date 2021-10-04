package com.company;

abstract class Character {
    private int maxHealth;
    private int defence;
    private int attack;
    //Map map = new Map();

    public Character(int maxHealth , int defence, int attack){
        this.maxHealth = maxHealth;
        this.defence = defence;
        this.attack = attack;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
