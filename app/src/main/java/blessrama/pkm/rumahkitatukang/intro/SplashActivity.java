package blessrama.pkm.rumahkitatukang.intro;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import blessrama.pkm.rumahkitatukang.R;
import blessrama.pkm.rumahkitatukang.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initActivity();
    }

    private void initActivity() {
        startActivity(new Intent(this, MainActivity.class));
        //check if user is already logged in or not. if not, navigate to login, if yes, navigate to main activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 2000);
    }
}