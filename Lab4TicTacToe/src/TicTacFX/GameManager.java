package TicTacFX;

//This is a game manager class.
//Something like this should be used to keep track of things like who's turn it is, the state of the board
//if there is a winner, etc...

import javax.swing.text.html.ImageView;

public class GameManager {

    public boolean stop;
    private boolean turn;
    private final int[][] board = new int[3][3];

    GameManager()
    {
        turn = false;
        stop = false;
        for(int x = 0; x < 3; x ++)
        {
            for(int y = 0; y < 3; y ++)
            {
                board[x][y] = 0;
            }
        }
    }

    public boolean takeTurn()
    {
        turn = !turn;
        return turn;
    }

    public boolean getTurn(){
        return turn;
    }

    public boolean checkSpaceEmpty(int x, int y)
    {
        if(board[x][y] == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void markSpot(int x, int y)
    {
        if(turn)
        {
            board[x][y] = 1;
        }
        else
        {
            board[x][y] = -1;
        }
    }

    public void stop(){ stop = true; }

    public boolean getStop() { return stop; }

    public void resetBoard(TTTbutton button, int x, int y)
    {
        //Fill me in
        button.setGraphic(null);
        board[x][y] = 0;
        turn = false;
        stop = false;
    }

    public boolean checkWin()
    {
        boolean win = false;
        //Fill me in
        //You may want to change the return type, though it can be done with this one.

        //Brute Force at it's finest
        if(board[0][0] == 1 && board[0][1] == 1 && board[0][2] == 1 || board[0][0] == -1 && board[0][1] == -1 && board[0][2] == -1){
            win = true;
        }
        if(board[1][0] == 1 && board[1][1] == 1 && board[1][2] == 1 || board[1][0] == -1 && board[1][1] == -1 && board[1][2] == -1){
            win = true;
        }
        if(board[2][0] == 1 && board[2][1] == 1 && board[2][2] == 1 || board[2][0] == -1 && board[2][1] == -1 && board[2][2] == -1){
            win = true;
        }
        if(board[0][0] == 1 && board[1][0] == 1 && board[2][0] == 1 || board[0][0] == -1 && board[1][0] == -1 && board[2][0] == -1){
            win = true;
        }
        if(board[0][1] == 1 && board[1][1] == 1 && board[2][1] == 1 || board[0][1] == -1 && board[1][1] == -1 && board[2][1] == -1){
            win = true;
        }
        if(board[0][2] == 1 && board[1][2] == 1 && board[2][2] == 1 || board[0][2] == -1 && board[1][2] == -1 && board[2][2] == -1){
            win = true;
        }
        if(board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1 || board[0][0] == -1 && board[1][1] == -1 && board[2][2] == -1){
            win = true;
        }
        if(board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1 || board[0][2] == -1 && board[1][1] == -1 && board[2][0] == -1){
            win = true;
        }

        return win;
    }
}
