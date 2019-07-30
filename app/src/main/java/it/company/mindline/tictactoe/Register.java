package it.company.mindline.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Timer;

public class Register extends AppCompatActivity {

    EditText etFirst,etSecond;
    AdView madView1;
    String firstName,secondName;
    playArea obj;
    private Timer timer = new Timer();
    private final long DELAY = 1000;
    String APP_ID = "ca-app-pub-8688181532532282~9160335881";
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        etFirst = (EditText) findViewById(R.id.etFirst);
        etSecond = (EditText) findViewById(R.id.etSecond);
//        madView = findViewById(R.id.sin_adView);
        madView1 = findViewById(R.id.mul_adView);

        MobileAds.initialize(this, APP_ID);

        AdRequest adRequest = new AdRequest.Builder().build();
//        madView.loadAd(adRequest);
        madView1.loadAd(adRequest);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-8688181532532282/6731389766");
        interstitialAd.loadAd(new AdRequest.Builder().build());

    }

    public void startGame(View view) {

        if ((etFirst.getText().toString().equals(""))   ) {
           etFirst.setText("Player 1");
        }
        if( (etSecond.getText().toString().equals(""))) {
            etSecond.setText("Player 2");
        }

            firstName = etFirst.getText().toString();
            secondName = etSecond.getText().toString();
            Intent ii = new Intent(Register.this, playArea.class);
            ii.putExtra("name", firstName);
            ii.putExtra("name1", secondName);
            startActivity(ii);


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
}
