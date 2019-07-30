package it.company.mindline.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class playwith_computer  extends AppCompatActivity implements View.OnClickListener {

    public Button button1, button2, button3, button4, button5, button6,
            button7, button8, button9, start, reset,replay;
    public RadioButton naught, cross;
    public TextView text,txtFirst,txtSecond;
    public computerLogic logic; // For when not using minimax
    public PerfectComputerLogic perfectLogic; // For when using minimax
    public Menu menu;
    public MenuItem item;
    public int player1points,player2points;
    String i,j;
    public boolean playerTurn = false;
    public boolean playerWon = false;
    public boolean computerWon = false;
    public boolean draw = false;
    public boolean winnable = true;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playwith_computer);

        initViews();
        logic = new computerLogic();
        perfectLogic = new PerfectComputerLogic();

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-8688181532532282/6731389766");
        interstitialAd.loadAd(new AdRequest.Builder().build());


        reset.setVisibility(View.INVISIBLE);
        replay.setVisibility(View.INVISIBLE);
        txtFirst=(TextView)findViewById(R.id.txtFirst);
        txtSecond=(TextView)findViewById(R.id.txtSecond);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            j =(String) b.get("name");
            txtFirst.setText(j +": ");
            i =(String) b.get("name1");
            txtSecond.setText(i +": ");

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }

    private void initViews() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        start = (Button) findViewById(R.id.start);
        reset = (Button) findViewById(R.id.reset);
        replay = (Button) findViewById(R.id.replay);
        naught = (RadioButton) findViewById(R.id.naught);
        cross = (RadioButton) findViewById(R.id.cross);
        text = (TextView) findViewById(R.id.text);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        start.setOnClickListener(this);
        reset.setOnClickListener(this);
        replay.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.winnable) {
            item.setChecked(!item.isChecked());
            winnable = item.isChecked();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                // Make the menu_main item and views required to start the game invisible

                replay.setVisibility(View.VISIBLE);
                reset.setVisibility(View.VISIBLE);
                start.setVisibility(View.INVISIBLE);
                naught.setVisibility(View.INVISIBLE);
                cross.setVisibility(View.INVISIBLE);
                if(cross.isChecked()) {
                    playerTurn = true;

                    text.setText("Player's turn");
                } else if(naught.isChecked()) {
                    playerTurn = false;

                    text.setText("Computer's turn");
                    logic.nextMove(this); // Place the first move in the center
                }

                break;
            case R.id.reset:
                // Make all the invisible views visible again

                start.setVisibility(View.VISIBLE);
                naught.setVisibility(View.VISIBLE);
                replay.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.INVISIBLE);
                cross.setVisibility(View.VISIBLE);
                text.setText("");
                playerTurn = false;
                playerWon = false;
                computerWon = false;
                draw = false;

                player1points=0;
                player2points=0;
                updatePointText();
                button1.setText("");
                button2.setText("");
                button3.setText("");
                button4.setText("");
                button5.setText("");
                button6.setText("");
                button7.setText("");
                button8.setText("");
                button9.setText("");

                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                break;

            case R.id.replay:

                naught.setVisibility(View.VISIBLE);
                cross.setVisibility(View.VISIBLE);
                start.setVisibility(View.VISIBLE);
                playerTurn = false;
                playerWon = false;
                computerWon = false;
                draw = false;
                text.setText("");
                updatePointText();
                button1.setText("");
                button2.setText("");
                button3.setText("");
                button4.setText("");
                button5.setText("");
                button6.setText("");
                button7.setText("");
                button8.setText("");
                button9.setText("");

                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                break;
            default:
                if(start.getVisibility() == View.VISIBLE)
                    break; // If a button is pressed without starting the game
                placeObject(v.getId());
                if(winnable)
                    logic.nextMove(this); // Don't use minimax
                else
                    perfectLogic.nextMove(this); // Use minimax
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);


        outState.putInt("player1points",player1points);
        outState.putInt("player2points",player2points);


    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);

        player1points=outState.getInt("player1points");
        player2points=outState.getInt("player2points");
    }
    public void placeObject(int id) {
        Button button = (Button) findViewById(id);
        if(!button.getText().equals(""))
            return;

        if(cross.isChecked() && playerTurn)
            button.setText("X");
        else if(naught.isChecked() && playerTurn)
            button.setText("O");

        check(); // check if the game is over
        if(playerWon && !draw) {
            text.setText("Player Won!");
            replay.setVisibility(View.VISIBLE);
            player1points++;
        }
        else if(computerWon && !draw) {
            text.setText("Computer Won!");
            replay.setVisibility(View.VISIBLE);
            player2points++;
        }
        else if(draw) {
            text.setText("It's a draw!");
            replay.setVisibility(View.VISIBLE);
        }
        else
            changeTurn();
    }

    public  void updatePointText(){
        txtFirst.setText(j   +" : "+ player1points);
        txtSecond.setText(i   +" : "+ player2points);
    }

    public void changeTurn() {
        playerTurn = !playerTurn;
        if(playerTurn)
            text.setText("Player's Turn");
        else
            text.setText("Computer's Turn");
    }

    public void check() {
        if (crossHorizontal() || crossVertical() || crossDiagonal()) {
            if(cross.isChecked())
                playerWon = true;
            else
                computerWon = true;
        } else if (naughtsHorizontal() || naughtsVertical()
                || naughtsDiagonal()) {
            if(naught.isChecked())
                playerWon = true;
            else
                computerWon = true;
        } else if(full() && !playerWon && !computerWon)
            draw = true;
    }

    public boolean full(){
        return !button1.getText().equals("")
                && !button2.getText().equals("")
                && !button3.getText().equals("")
                && !button4.getText().equals("")
                && !button5.getText().equals("")
                && !button6.getText().equals("")
                && !button7.getText().equals("")
                && !button8.getText().equals("")
                && !button9.getText().equals("");
    }

    public boolean isEmpty() {
        return button1.getText().equals("")
                && button2.getText().equals("")
                && button3.getText().equals("")
                && button4.getText().equals("")
                && button5.getText().equals("")
                && button6.getText().equals("")
                && button7.getText().equals("")
                && button8.getText().equals("")
                && button9.getText().equals("");
    }

    public boolean crossHorizontal() {
        return (button1.getText().equals("X") && button2.getText().equals("X") && button3.getText().equals("X")) ||
                (button4.getText().equals("X") && button5.getText().equals("X") && button6.getText().equals("X")) ||
                (button7.getText().equals("X") && button8.getText().equals("X") && button9.getText().equals("X"));
    }

    public boolean naughtsHorizontal() {
        return (button1.getText().equals("O") && button2.getText().equals("O") && button3.getText().equals("O")) ||
                (button4.getText().equals("O") && button5.getText().equals("O") && button6.getText().equals("O")) ||
                (button7.getText().equals("O") && button8.getText().equals("O") && button9.getText().equals("O"));
    }

    public boolean crossVertical() {
        return (button1.getText().equals("X") && button4.getText().equals("X") && button7.getText().equals("X")) ||
                (button2.getText().equals("X") && button5.getText().equals("X") && button8.getText().equals("X")) ||
                (button3.getText().equals("X") && button6.getText().equals("X") && button9.getText().equals("X"));
    }

    public boolean naughtsVertical() {
        return (button1.getText().equals("O") && button4.getText().equals("O") && button7.getText().equals("O")) ||
                (button2.getText().equals("O") && button5.getText().equals("O") && button8.getText().equals("O")) ||
                (button3.getText().equals("O") && button6.getText().equals("O") && button9.getText().equals("O"));
    }

    public boolean crossDiagonal() {
        return (button1.getText().equals("X") && button5.getText().equals("X") && button9.getText().equals("X")) ||
                (button3.getText().equals("X") && button5.getText().equals("X") && button7.getText().equals("X"));
    }

    public boolean naughtsDiagonal() {
        return (button1.getText().equals("O") && button5.getText().equals("O") && button9.getText().equals("O")) ||
                (button3.getText().equals("O") && button5.getText().equals("O") && button7.getText().equals("O"));
    }
}
