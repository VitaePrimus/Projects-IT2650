package TicTacFX;

//This is a game manager class.
//Something like this should be used to keep track of things like who's turn it is, the state of the board
//if there is a winner, etc...

public class GameManager {

    private boolean turn;
    private final int[][] board = new int[3][3];

    GameManager()
    {
        turn = false;
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

    public void resetBoard()
    {
        //Fill me in
    }

    public boolean checkWin()
    {
        //Fill me in
        //You may want to change the return type, though it can be done with this one.
        return false;
    }

}
