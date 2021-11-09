package sample;

public class Helicopter {
    private static Location location;
    private double maxFuel;
    private double currentFuel;
    private double mpg;



    public Helicopter(Location location, double maxFuel, double mpg){
        this.location = location;
        this.maxFuel = maxFuel;
        this.currentFuel = maxFuel;
        this.mpg = mpg;

    }

    public String Fly(Location newLocation){
        double distanceX;
        double distanceY;
        double consumption;
        double distance;
        String result;

        distanceX = newLocation.getLocX() - location.getLocX();
        distanceY = newLocation.getLocY() - location.getLocY();

        // Calculate distance
        distance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));

        // Fuel consumption
        consumption = distance / mpg;
        currentFuel = currentFuel - consumption;

        // Set new location if possible
        if(newLocation == location){
            result = "This is your current location, chose a different option.";
        }
        else if(currentFuel >= 0){
            location = newLocation;
            result = "New location: " + Print() + "%nNew Fuel: " + (Math.round(currentFuel * 100.0) / 100.0) +
                    "%nDistance flown: " + (Math.round(distance * 100.0) / 100.0);
        }
        else{
            result = "You can't fly to " + newLocation.getName() + ", not enough fuel. Refuel if you can.%n" ;
            currentFuel = currentFuel + consumption;
            consumption = 0;
        }

        return result;
    }

    // Refuel the helicopter
    public void Refuel(){
        if(location.getFuel()) {
            currentFuel = this.maxFuel;
            System.out.println("Refueled.");
            System.out.println(currentFuel);
        }
        else{
            System.out.println("No Fuel here.");
        }
    }

    // Some getters
    public String Print(){
        return location.getName() + " X cor: " + location.getLocX() + "Y cor: " + location.getLocY();
    }

    public Double getFuel(){
        return currentFuel;
    }

    public String getLocation() { return location.getName(); }
}






