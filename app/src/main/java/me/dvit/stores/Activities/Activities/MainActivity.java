package me.dvit.stores.Activities.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.dvit.stores.R;

public class MainActivity extends AppCompatActivity {
int wait = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this ,SignupActivity.class);
                startActivity(intent);
                finish();

            }
        } , wait);

    }
}
