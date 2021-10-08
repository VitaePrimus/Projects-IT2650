package com.company;

public class Hero extends Character {
    private int potions;
    private Tile charTile;
    private int currentHealth;


    public Hero(int maxHealth, int defence, int attack) {
        super(maxHealth, defence, attack);
        potions = 1;
        charTile = new Tile(0,0);
        currentHealth = maxHealth;
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


    // Starting a fight
    public boolean startFight(Enemy enemy, String choice, int index){
        Generator generator = new Generator();
        boolean win = false;

        while(this.currentHealth > 0 && enemy.getCurrentHealth() > 0){
            break;
        }
        enemy.setCurrentHealth(0);

        if(enemy.getCurrentHealth() <= 0){
            win = true;
        }

        return win;
    }

    public String getCharLoc(){
        return charTile.getLocation();
    }

    public Tile getCharTile() { return charTile; }

    public void setCharTile(Tile charTile) { this.charTile = charTile; }

    public void setPotions(int num){
        potions = potions + num;
    }

    public void setCurrentHealth(int currentHealth) { this.currentHealth = currentHealth; }

    public int getCurrentHealth() { return currentHealth; }

}
