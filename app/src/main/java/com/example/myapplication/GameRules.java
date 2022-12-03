package com.example.myapplication;

import android.widget.Button;
import android.widget.TextView;

public class GameRules {
    private int[][] gameBoard;
    private int shape = 1;



    private Button playAgainBtn;
    private TextView playTurn;
    GameRules() {
        gameBoard = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = 0;
            }
        }
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public boolean updateGameBoard(int row, int col) {
        if (gameBoard[row - 1][col - 1] == 0) {
            gameBoard[row - 1][col - 1] = shape;
            if(shape == 1)
            {
                playTurn.setText("O Turn");
            }
            else
            {
                playTurn.setText("X Turn");
            }
            return true;
        } else {
            return false;
        }
    }
    public boolean GameWinner()
    {
        boolean winner = false;
        for(int i = 0; i < 3; i++)//check if there is a winner in the rows
        {
            if(gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2] && gameBoard[i][0] != 0)
               winner = true;

        }
        for(int j = 0; j < 3; j++)//check if there is a winner in the column
        {
            if(gameBoard[0][j] == gameBoard[1][j] && gameBoard[0][j] == gameBoard[2][j] && gameBoard[0][j] != 0)
                winner = true;
        }
        if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] != 0)
            winner = true;
        if(gameBoard[2][0] == gameBoard[1][1] && gameBoard[2][0] == gameBoard[0][2] && gameBoard[2][0] != 0)
            winner = true;

        int fullBoard = 0;
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(gameBoard[i][j] != 0)
                    fullBoard++;
            }
        }
        if(winner == true)
        {
            playAgainBtn.setEnabled(true);
            if(shape == 1)
               playTurn.setText("The Winner is X!!!");
            else
                playTurn.setText("The Winner is O!!!");
            return true;

        }
        else if(fullBoard == 9)
        {
            playAgainBtn.setEnabled(true);
            playTurn.setText("There is no Winner!!!");
            return true;
        }
        else
            return false;

    }
    public void ClearGame()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = 0;
            }
        }
        playTurn.setText("X Turn");
        shape = 1;
        playAgainBtn.setEnabled(false);
    }
    public int getShape() {
        return shape;
    }
    public void setShape(int shape) {
        this.shape = shape;
    }
    public void setPlayAgainBtn(Button playAgainBtn) {
        this.playAgainBtn = playAgainBtn;
    }

    public void setPlayTurn(TextView playTurn) {
        this.playTurn = playTurn;
    }
}
