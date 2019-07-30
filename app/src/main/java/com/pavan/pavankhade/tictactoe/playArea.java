package com.pavan.pavankhade.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class playArea extends AppCompatActivity implements View.OnClickListener {


    private Button [][] button =new Button[3][3];
    private boolean player1turn=true;
    private int roundCount,player1points,player2points;
    Button btnReset,btnReplay,btnStart;
    public RadioButton StartWithX, StartWithO;
    TextView txtWho;
    RadioGroup rdrgp;
    String j,i,StartWith;
    static TextView txtFirst,txtSecond,txtWinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_area);

        txtFirst=(TextView)findViewById(R.id.txtFirst);
        txtSecond=(TextView)findViewById(R.id.txtSecond);
        txtWinner=(TextView)findViewById(R.id.txtWinner);

        btnReset=(Button)findViewById(R.id.btnReset);
        btnReplay=(Button)findViewById(R.id.btnReplay);
        btnStart=(Button)findViewById(R.id.start);

        btnReset.setEnabled(false);
        btnReplay.setEnabled(false);
        txtWho=(TextView)findViewById(R.id.txtWhoStart);
        rdrgp=(RadioGroup)findViewById(R.id.rdgrp);

        txtWinner.setText("");
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            j =(String) b.get("name");
            txtFirst.setText(j + " (X) : ");
            i =(String) b.get("name1");
            txtSecond.setText(i + " (O) : ");

        }


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                DisableButtons(true);
                txtWho.setVisibility(v.VISIBLE);
                rdrgp.setVisibility(v.VISIBLE);
                btnStart.setEnabled(true);
            }
        });

        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
                DisableButtons(true);
                txtWho.setVisibility(v.VISIBLE);
                rdrgp.setVisibility(v.VISIBLE);
                btnStart.setEnabled(true);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }

        if(StartWith=="X") {
            if (player1turn) {
                ((Button) v).setTextColor(Color.parseColor("#FF4500"));
                ((Button) v).setText("X");
                txtWinner.setText(j +"'s Turn");
            } else {

                ((Button) v).setTextColor(Color.parseColor("#00BFFF"));
                ((Button) v).setText("O");
                txtWinner.setText(i +"'s Turn");
            }
        }else {
            if (player1turn) {
                ((Button) v).setTextColor(Color.parseColor("#FF4500"));
                ((Button) v).setText("O");
                txtWinner.setText(j +"'s Turn");
            } else {

                ((Button) v).setTextColor(Color.parseColor("#00BFFF"));
                ((Button) v).setText("X");
                txtWinner.setText(i +"'s Turn");

            }
        }



        roundCount++;

        if(checkForWin()){
            if(player1turn){
                player1Wins();
                btnReset.setEnabled(true);
                btnReplay.setEnabled(true);

                DisableButtons(false);
            }else{
                player2Wins();
                btnReset.setEnabled(true);
                btnReplay.setEnabled(true);
                DisableButtons(false);
            }
        }
        else if(roundCount==9){
            draw();
            DisableButtons(false);
        }
        else{
            player1turn=!player1turn;
        }
    }


    private boolean checkForWin(){
        String[][] field=new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
               field[i][j]=button[i][j].getText().toString();
            }
        }

        for(int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1])
                    &&field[i][0].equals(field[i][2])
                    &&!field[i][0].equals("")){

                    return true;
            }

        }
        for(int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i])
                    &&field[0][i].equals(field[2][i])
                    &&!field[0][i].equals("")){

                return true;
            }

        }

        if(field[0][0].equals(field[1][1])
                &&field[0][0].equals(field[2][2])
                &&!field[0][0].equals("")){

            return true;
        }

        if(field[0][2].equals(field[1][1])
                &&field[0][2].equals(field[2][0])
                &&!field[0][2].equals("")){

            return true;
        }
        return false;
    }

    private void player1Wins(){

        if(StartWith=="O") {
            txtWinner.setText(i +" Wins the match...");
            player2points++;
            
        }
        else {
            txtWinner.setText(j + " Wins the match...");
            player1points++;

        }
        updatePointText();



    }

    private void DisableButtons(Boolean isEnabled){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                button[i][j].setEnabled(isEnabled);
            }
        }
    }

    private void player2Wins(){

        if(StartWith=="X") {
            txtWinner.setText(j +" Wins the match...");
            player1points++;

        }
        else {
            txtWinner.setText(i + " Wins the match...");
            player2points++;

        }

        updatePointText();

    }

    private void draw(){
        Toast.makeText(getApplicationContext(),"Draw Match",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointText(){
        txtFirst.setText(j + " (X)  : "+ player1points);
        txtSecond.setText(i   +" (O) : "+ player2points);
    }

    private void resetBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                button[i][j].setText("");
            }
        }
        roundCount=0;
        txtWinner.setText("");
        btnReset.setEnabled(true);
        player1turn=true;
    }

    private void resetGame(){
        player1points=0;
        player2points=0;
        updatePointText();
        txtWinner.setText("");
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putInt("roundCount",roundCount);
        outState.putInt("player1points",player1points);
        outState.putInt("player2points",player2points);
        outState.putBoolean("player1turn",player1turn);

    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);

        roundCount=outState.getInt("roundCount");
        player1points=outState.getInt("player1points");
        player2points=outState.getInt("player2points");
        player1turn=outState.getBoolean("player1turn");
    }



    public void StartThegame(View view) {
        StartWithX = (RadioButton)super.findViewById(R.id.StartWithX);
        StartWithO = (RadioButton)super.findViewById(R.id.StartWithO);

        if(StartWithX.isChecked()) {

            StartWith="X";
        } else if(StartWithO.isChecked()) {
            StartWith="O";

        }

        txtWho.setVisibility(view.INVISIBLE);
        rdrgp.setVisibility(view.INVISIBLE);

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String buttonId="button_"+i+j;
                int resId=getResources().getIdentifier(buttonId,"id",getPackageName());
                button[i][j]=findViewById(resId);
                button[i][j].setOnClickListener(this);
                btnStart.setEnabled(false);
            }
        }

    }
}
