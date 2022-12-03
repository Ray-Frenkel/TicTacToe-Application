package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TicTacToeBoard extends View {
    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int LineColor;
    private int cell = getWidth()/3;
    private final Paint paint = new Paint();
    private final GameRules game;
    private boolean winning = false;

    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        game = new GameRules();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TicTacToeBoard,0,0);
        try{
            boardColor = a.getInteger(R.styleable.TicTacToeBoard_boardColor,0);
            XColor = a.getInteger(R.styleable.TicTacToeBoard_XColor,0);
            OColor = a.getInteger(R.styleable.TicTacToeBoard_OColor,0);
            LineColor = a.getInteger(R.styleable.TicTacToeBoard_LineColor,0);
        }
        finally {
            a.recycle();
        }
    }
    @Override
    protected void onMeasure(int width, int height)
    {
        super.onMeasure(width,height);
        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cell = dimension / 3;
        setMeasuredDimension(dimension, dimension);
    }
    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        
        drawBoard(canvas);
        drawMarker(canvas);

    }

    private void drawMarker(Canvas canvas) {
        for(int i = 0; i < 3; i++)
        {
            for(int j=0; j < 3; j++){
               if(game.getGameBoard()[i][j] != 0)
               {
                   if(game.getGameBoard()[i][j] == 1)
                       drawX(canvas, i,j);
                   else
                       drawO(canvas, i, j);
               }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
            int touchEvent = event.getAction();
            if (touchEvent == MotionEvent.ACTION_DOWN) {
                int row = (int) Math.ceil(y / cell);
                int col = (int) Math.ceil(x / cell);
                if(!winning) {
                    if (game.updateGameBoard(row, col)) {
                        invalidate();
                        if(game.GameWinner())
                        {
                            winning = true;
                            invalidate();
                        }
                        //update the player turn
                        if (game.getShape() % 2 == 0)
                            game.setShape(game.getShape() - 1);
                        else
                            game.setShape(game.getShape() + 1);
                    }
                }
                invalidate();
                return true;
            }

        return false;

    }
    private void drawBoard(Canvas canvas) {
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
        for(int i = 1; i < 3; i++)
        {
            canvas.drawLine(cell*i,0,cell*i,canvas.getWidth(),paint);
        }
        for(int j = 1; j < 3; j++)
        {
            canvas.drawLine(0,cell*j,canvas.getWidth(),cell*j,paint);
        }
    }
    private void drawO(Canvas canvas, int row, int col){
        paint.setColor(OColor);
        canvas.drawOval((float) (col*cell+cell*0.2),(float)(row*cell+cell*0.2),(float)((col*cell+cell)-cell*0.2),(float) ((row*cell + cell)-cell*0.2),paint);

    }
    private void drawX(Canvas canvas, int row, int col){
        paint.setColor(XColor);
        canvas.drawLine((float)((col+1)*cell-cell*0.2),(float) (row*cell+cell*0.2),(float) (col*cell+cell*0.2),(float)((row+1)*cell-cell*0.2),paint);
        canvas.drawLine((float)((col)*cell+cell*0.2),(float)(row*cell+cell*0.2),(float)((col+1)*cell-cell*0.2),(float) ((row+1)*cell-cell*0.2),paint);
    }
    public void ClearGame()
    {
        game.ClearGame();
        winning = false;
    }
    public void setGame(Button playAgain, TextView playerDisplay){
        game.setPlayAgainBtn(playAgain);
        game.setPlayTurn(playerDisplay);
    }
}
