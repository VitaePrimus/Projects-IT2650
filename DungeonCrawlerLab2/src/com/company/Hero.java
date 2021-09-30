package com.company;

public class Hero extends Character {
    private int potions;
    private Tile charTile;


    public Hero(int maxHealth, int defence, int attack) {
        super(maxHealth, defence, attack);
        potions = 1;
        charTile = new Tile(0,0);
    }

    public void attack(Enemy enemy){
        enemy.setMaxHealth(enemy.getMaxHealth() - this.getAttack());
    }


    public String drinkPotion(){
        String message;

        if(potions > 0 && getMaxHealth() < 100){
            setMaxHealth(getMaxHealth() + 20);
            if(getMaxHealth() > 100){
                setMaxHealth(100);
            }
            potions = potions - 1;
            message = "You were healed by a potion";
        }
        else{
            message = "You can't drink a potion now";
        }

        return message;
    }


    public void move(String direction){
        if(direction.equalsIgnoreCase("n")){
            charTile = new Tile(charTile.getX(), charTile.getY() - 1);
        }
        else if(direction.equalsIgnoreCase("s")){
            charTile = new Tile(charTile.getX(), charTile.getY() + 1);
        }
        else if(direction.equalsIgnoreCase("w")){
            charTile = new Tile(charTile.getX() - 1, charTile.getY());
        }
        else if(direction.equalsIgnoreCase("e")){
            charTile = new Tile(charTile.getX() + 1, charTile.getY());
        }
    }

    public String getCharLoc(){
        return charTile.getLocation();
    }

    public Tile getCharTile() { return charTile; }

    public void setCharTile(Tile charTile) { this.charTile = charTile; }

    public void setPotions(int num){
        potions = potions + num;
    }
}
