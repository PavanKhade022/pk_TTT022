package com.pavan.pavankhade.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import java.util.Timer;

public class Register extends AppCompatActivity {

    EditText etFirst, etSecond;

    String firstName, secondName;
    playArea obj;

    private Timer timer = new Timer();
    private final long DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        etFirst = (EditText) findViewById(R.id.etFirst);
        etSecond = (EditText) findViewById(R.id.etSecond);

    }

    public void startGame(View view) {

        if ((etFirst.getText().toString().equals(""))) {
            etFirst.setText("Player 1");
        }
        if ((etSecond.getText().toString().equals(""))) {
            etSecond.setText("Player 2");
        }
        firstName = etFirst.getText().toString();
        secondName = etSecond.getText().toString();
        Intent ii = new Intent(Register.this, playArea.class);
        ii.putExtra("name", firstName);
        ii.putExtra("name1", secondName);
        startActivity(ii);
//        callPopup();



    }


}
