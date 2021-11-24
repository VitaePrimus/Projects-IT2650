package TicTacFX;

import javafx.scene.control.Button;

//This button is an extension of the javafx button.
//It has everything that the javafx button has, but we added an x and y property.

public class TTTbutton extends Button {

    private final int x;
    private final int y;

    public TTTbutton(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
