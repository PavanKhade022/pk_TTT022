package com.pavan.pavankhade.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class singleplayer_register extends AppCompatActivity {


    EditText etFirst;
    TextView etSecond;

    String firstName,secondName;
    playArea obj;
    private Timer timer = new Timer();
    private final long DELAY = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(),"helllo",Toast.LENGTH_SHORT).show();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_singleplayer_register);

       etFirst=(EditText)findViewById(R.id.etFirst);
       etSecond=(TextView)findViewById(R.id.etSecond);

    }
    public void startGame(View view) {

        if ((etFirst.getText().toString().equals(""))   ) {
            etFirst.setText("Player 1");
        }

            firstName = etFirst.getText().toString();
            secondName = etSecond.getText().toString();
            Intent ii = new Intent(singleplayer_register.this, playwith_computer.class);
            ii.putExtra("name", firstName);
            ii.putExtra("name1", secondName);
            startActivity(ii);


    }
}

