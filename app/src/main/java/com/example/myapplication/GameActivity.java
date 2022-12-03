package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
private TicTacToeBoard board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        board = findViewById(R.id.ticTacToeBoard2);
        Button playAgain = findViewById(R.id.playAgainBtn);
        TextView playersTv = findViewById(R.id.playerTv);
        board.setGame(playAgain,playersTv);
        playAgain.setEnabled(false);
        //playAgain.setVisibility(View.GONE);
    }
    public void playAgainButton(View view)
    {
        board.ClearGame();
        board.invalidate();
    }
}