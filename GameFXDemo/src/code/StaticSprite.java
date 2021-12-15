package code;

import javafx.scene.image.Image;

//This is class to manage static sprite (image) objects.
//These objects can (if desired) move around the scene, but will have an animation cycle
//This particular class does not include the ability to have play controlled objects
public class StaticSprite {

    private Image image;
    private double x, y;

    //This constructor accepts the image file path and starting location.
    public StaticSprite(String imageFilePath, double x, double y)
    {
        image = new Image(imageFilePath);
        this.x = x;
        this.y = y;
    }

    //Basic getters
    //X1 is the left side, X2 the right side
    //Y1 is the top, Y2 is the bottom
    public double getX1() { return x; }
    public double getX2() { return x + image.getWidth(); }
    public double getY1() { return y; }
    public double getY2() { return y + image.getHeight(); }
    public Image getImage() {return image; }

    //Basic location setter
    public void setNewLocation(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    //Instead of setting a location, this one moves the object based on the previous location
    public void move(double xAmount, double yAmount)
    {
        this.x += xAmount;
        this.y += yAmount;
    }

    //Allows for the ability to change the image if desired
    public void changeImage(String imageFilePath)
    {
        image = new Image(imageFilePath);
    }

}
