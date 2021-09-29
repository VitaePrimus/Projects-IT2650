package com.company;

public class Hero extends Character {
    private int potions = 1;


    public Hero(int maxHealth, int defence, int attack) {
        super(maxHealth, defence, attack);
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

    public void setPotions(int num){
        potions = potions + num;
    }
}
