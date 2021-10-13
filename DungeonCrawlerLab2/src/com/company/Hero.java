package com.company;

import java.util.Random;

public class Hero extends Character {
    private int potions;
    private Tile charTile;
    private int currentHealth;


    public Hero(int maxHealth, int defence, int attack) {
        super(maxHealth, defence, attack);
        potions = 3;
        charTile = new Tile(0,0);
        currentHealth = maxHealth;
    }


    public String drinkPotion(){
        String message;

        if(potions > 0 && getCurrentHealth() < 100){
            setCurrentHealth(getCurrentHealth() + 20);
            if(getCurrentHealth() > 100){
                setCurrentHealth(100);
            }
            potions = potions - 1;
            message = "You were healed by a potion";
        }
        else{
            message = "You can't drink a potion now";
        }

        return message;
    }


    // Hero movement. Destroys old tile, creates new hero tile with new coordinates.
    public void move(String direction){
        if(direction.equalsIgnoreCase("w")){
            charTile = new Tile(charTile.getX(), charTile.getY() - 1);
        }
        else if(direction.equalsIgnoreCase("s")){
            charTile = new Tile(charTile.getX(), charTile.getY() + 1);
        }
        else if(direction.equalsIgnoreCase("a")){
            charTile = new Tile(charTile.getX() - 1, charTile.getY());
        }
        else if(direction.equalsIgnoreCase("d")){
            charTile = new Tile(charTile.getX() + 1, charTile.getY());
        }
    }

    public void attack(Character enemy){
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

        enemy.setCurrentHealth(enemy.getCurrentHealth() - ((getAttack() * multiplier) / enemy.getDefence()));
    }

    public void escape(Enemy enemy, Tile oldTile){
            setCurrentHealth(getCurrentHealth() - ((enemy.getAttack() * 5) / this.getDefence()));
            setCharTile(oldTile);
    }

    // Starting a fight
//    public boolean startFight(Enemy enemy, String choice, Tile oldTile){
//        boolean win = false;
//
//        if(choice.equalsIgnoreCase("a")){
//            enemy.setCurrentHealth(enemy.getCurrentHealth() - ((getAttack() * 10) / enemy.getDefence()));
//            setCurrentHealth(getCurrentHealth() - ((enemy.getAttack() * 10) / this.getDefence()));
//        }
//        if(choice.equalsIgnoreCase("p")){
//
//        }
//        if(choice.equalsIgnoreCase("e")){
//            setCurrentHealth(getCurrentHealth() - ((enemy.getAttack() * 5) / this.getDefence()));
//            setCharTile(oldTile);
//        }
//
//        if(enemy.getCurrentHealth() <= 0){
//            win = true;
//        }
//
//        return win;
//    }

    public String getCharLoc(){
        return charTile.getLocation();
    }

    public Tile getCharTile() { return charTile; }

    public void setCharTile(Tile charTile) { this.charTile = charTile; }

    public void setPotions(int num){ potions = num; }

    public int getPotions() { return potions; }

    public void setCurrentHealth(int currentHealth) { this.currentHealth = currentHealth; }

    public int getCurrentHealth() { return currentHealth; }

}
