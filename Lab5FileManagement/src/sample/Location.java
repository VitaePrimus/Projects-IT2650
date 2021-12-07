package sample;

public class Location {
    private String name;
    private int locX;
    private int locY;
    private boolean fuel;

    public Location(String name, int locX, int locY, boolean fuel){
        this.name = name;
        this.locX = locX;
        this.locY = locY;
        this.fuel = fuel;
    }

    // Getters needed for this class
    public String getName() { return name; }

    public boolean getFuel(){
        return fuel;
    }

    public int getLocX(){
        return locX;
    }

    public int getLocY(){
        return locY;
    }

    public String getFileLine(){
        return getName() + "," +getLocX()+ "," + getLocY() + "," + getFuel() + "\n";
    }

    @Override
    public String toString(){
        return name + " | Fuel: " + getFuel();
    }

}
